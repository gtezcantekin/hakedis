package com.hakedis.service;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EndeksXlsImportService {

    private final Connection conn;

    public EndeksXlsImportService(Connection conn) {
        this.conn = conn;
    }

    /**
     * endeksler.xls yapısı:
     * 0: YIL
     * 1: AY (Ocak, Şubat...)
     * 2..: değerler
     *
     * Kolon(2) -> endeks_tanim ORDER BY id ilk kayıt
     * Kolon(3) -> ikinci kayıt ... şeklinde eşler.
     */
    public void importXls(Path xlsPath) throws Exception {

        List<Long> tanimIds = loadTanimIdsOrdered();

        String upsertSql =
                "INSERT INTO endeks_deger(endeks_tanim_id, yil, ay, deger) " +
                        "VALUES (?,?,?,?) " +
                        "ON DUPLICATE KEY UPDATE deger = VALUES(deger)";

        conn.setAutoCommit(false);

        try (InputStream in = Files.newInputStream(xlsPath);
             Workbook wb = new HSSFWorkbook(in);
             PreparedStatement ps = conn.prepareStatement(upsertSql)) {

            Sheet sh = wb.getSheetAt(0);

            int batch = 0;

            for (Row row : sh) {

                // boş satır geç
                if (row == null) continue;

                String yilStr = cellToString(row.getCell(0));
                String ayStr  = cellToString(row.getCell(1));

                if (yilStr.isEmpty() || ayStr.isEmpty()) continue;

                int yil;
                try {
                    yil = Integer.parseInt(yilStr);
                } catch (NumberFormatException e) {
                    // başlık satırı vs varsa atla
                    continue;
                }

                int ay = ayToInt(ayStr);
                if (ay == 0) continue;

                // 2. kolondan itibaren değerler
                int lastCol = row.getLastCellNum();
                if (lastCol <= 2) continue;

                for (int col = 2; col < lastCol; col++) {

                    int idx = col - 2; // tanim index
                    if (idx < 0 || idx >= tanimIds.size()) continue;

                    Long tanimId = tanimIds.get(idx);

                    String raw = cellToString(row.getCell(col));
                    if (raw.isEmpty() || raw.equals("-")) continue;

                    BigDecimal deger;
                    try {
                        deger = parseDecimal(raw);
                    } catch (Exception ex) {
                        continue;
                    }

                    ps.setLong(1, tanimId);
                    ps.setInt(2, yil);
                    ps.setInt(3, ay);
                    ps.setBigDecimal(4, deger);

                    ps.addBatch();
                    batch++;

                    if (batch % 2000 == 0) ps.executeBatch();
                }
            }

            ps.executeBatch();
            conn.commit();

        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    private List<Long> loadTanimIdsOrdered() throws SQLException {
        List<Long> ids = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement("SELECT id FROM endeks_tanim ORDER BY id");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) ids.add(rs.getLong(1));
        }
        return ids;
    }

    private static String cellToString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                // 274.44 gibi
                double d = cell.getNumericCellValue();
                // Excel 274,44 gösterebilir ama burada noktalı gelir
                return BigDecimal.valueOf(d).stripTrailingZeros().toPlainString();
            case FORMULA:
                try {
                    return cell.getStringCellValue().trim();
                } catch (Exception ignore) {
                    try {
                        double dv = cell.getNumericCellValue();
                        return BigDecimal.valueOf(dv).stripTrailingZeros().toPlainString();
                    } catch (Exception ignore2) {
                        return "";
                    }
                }
            case BLANK:
            case ERROR:
            case BOOLEAN:
            default:
                return "";
        }
    }

    private static int ayToInt(String ay) {
        String a = ay.toLowerCase(new Locale("tr", "TR")).trim();
        switch (a) {
            case "ocak": return 1;
            case "şubat":
            case "subat": return 2;
            case "mart": return 3;
            case "nisan": return 4;
            case "mayıs":
            case "mayis": return 5;
            case "haziran": return 6;
            case "temmuz": return 7;
            case "ağustos":
            case "agustos": return 8;
            case "eylül":
            case "eylul": return 9;
            case "ekim": return 10;
            case "kasım":
            case "kasim": return 11;
            case "aralık":
            case "aralik": return 12;
            default: return 0;
        }
    }

    private static BigDecimal parseDecimal(String s) {
        String x = s.trim().replace(" ", "");
        // "274,44" -> "274.44"
        if (x.contains(".") && x.contains(",")) {
            x = x.replace(".", "");
            x = x.replace(",", ".");
        } else {
            x = x.replace(",", ".");
        }
        return new BigDecimal(x);
    }
}
