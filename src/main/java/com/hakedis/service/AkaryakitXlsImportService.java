package com.hakedis.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.*;

public class AkaryakitXlsImportService {

    private final Connection conn;

    // Excel C..F sırası -> endeks_tanim.kod
    // Sende endeks_tanim tablosunda A1..A4 var (ekrandaki gibi).
    private static final List<String> KODLAR = List.of("A1", "A2", "A3", "A4");

    public AkaryakitXlsImportService(Connection conn) {
        this.conn = conn;
    }

    public void importXls(Path xlsPath) throws Exception {

        Map<String, Long> kodToId = loadOrCreateEndeksTanimIds(KODLAR);

        String upsertSql =
                "INSERT INTO akaryakit (endeks_tanim_id, yil, ay, deger, kdv) " +
                        "VALUES (?,?,?,?,?) " +
                        "ON DUPLICATE KEY UPDATE deger = VALUES(deger), kdv = VALUES(kdv)";

        conn.setAutoCommit(false);

        DataFormatter fmt = new DataFormatter(new Locale("tr", "TR"));

        try (InputStream in = Files.newInputStream(xlsPath);
             Workbook wb = new HSSFWorkbook(in);
             PreparedStatement ps = conn.prepareStatement(upsertSql)) {

            Sheet sh = wb.getSheetAt(0);
            int batch = 0;

            for (Row row : sh) {
                if (row == null) continue;

                String yilStr = cellToString(row.getCell(0), fmt);
                String ayStr  = cellToString(row.getCell(1), fmt);

                if (yilStr.isEmpty() || ayStr.isEmpty()) continue;

                int yil;
                try {
                    yil = Integer.parseInt(yilStr.trim());
                } catch (NumberFormatException e) {
                    // başlık satırı vs.
                    continue;
                }

                int ay = ayToInt(ayStr);
                if (ay == 0) continue;

                // G sütunu KDV (index 6) - boşsa 0.18
                BigDecimal kdv = readKdv(row.getCell(6), fmt);

                // C..F (2..5): 4 kolon
                for (int col = 2; col <= 5; col++) {

                    String kod = KODLAR.get(col - 2);          // C->A1, D->A2, E->A3, F->A4
                    Long tanimId = kodToId.get(kod);

                    String raw = cellToString(row.getCell(col), fmt);
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
                    ps.setBigDecimal(5, kdv);

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

    private Map<String, Long> loadOrCreateEndeksTanimIds(List<String> kodlar) throws SQLException {
        Map<String, Long> map = new HashMap<>();

        // 1) Var olanları çek
        String selectSql = "SELECT id, kod FROM endeks_tanim WHERE kod IN (" + placeholders(kodlar.size()) + ")";
        try (PreparedStatement ps = conn.prepareStatement(selectSql)) {
            for (int i = 0; i < kodlar.size(); i++) ps.setString(i + 1, kodlar.get(i));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    map.put(rs.getString("kod"), rs.getLong("id"));
                }
            }
        }

        // 2) Eksik olanları ekle (istersen ad alanını da doldurabiliriz)
        String insertSql = "INSERT INTO endeks_tanim(kod, ad) VALUES(?, ?)";

        for (String kod : kodlar) {
            if (map.containsKey(kod)) continue;

            try (PreparedStatement ps = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, kod);
                ps.setString(2, "Akaryakıt " + kod); // basit ad
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        map.put(kod, keys.getLong(1));
                    }
                }
            }
        }

        // Son kontrol: hala eksik var mı?
        for (String kod : kodlar) {
            if (!map.containsKey(kod)) {
                throw new IllegalStateException("endeks_tanim içinde kod bulunamadı/eklenemedi: " + kod);
            }
        }

        return map;
    }

    private static String placeholders(int n) {
        return String.join(",", Collections.nCopies(n, "?"));
    }

    private static String cellToString(Cell cell, DataFormatter fmt) {
        if (cell == null) return "";
        String v = fmt.formatCellValue(cell);
        return v == null ? "" : v.trim();
    }

    private static BigDecimal readKdv(Cell kdvCell, DataFormatter fmt) {
        String raw = cellToString(kdvCell, fmt);
        if (raw.isEmpty() || raw.equals("-")) return new BigDecimal("0.18");

        BigDecimal kdv = parseDecimal(raw);

        // Excel bazen 18 diye verebilir → 0.18 yap
        if (kdv.compareTo(BigDecimal.ONE) > 0) {
            kdv = kdv.divide(new BigDecimal("100"));
        }
        return kdv;
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

        // "1.234,56" -> "1234.56"
        if (x.contains(".") && x.contains(",")) {
            x = x.replace(".", "");
            x = x.replace(",", ".");
        } else {
            x = x.replace(",", ".");
        }

        return new BigDecimal(x);
    }
}
