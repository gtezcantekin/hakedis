package com.hakedis.service;
import org.apache.commons.csv.*;

import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

public class CsvHeaderImportService {

    private final Connection conn;

    public CsvHeaderImportService(Connection conn) {
        this.conn = conn;
    }

    public void importHeader(Path csvPath) throws Exception {
        // 1) UTF-8 dene, bozuksa Windows-1254'e düş
        try {
            importHeaderWithCharset(csvPath, StandardCharsets.UTF_8);
        } catch (BadTurkishEncodingException ex) {
            importHeaderWithCharset(csvPath, Charset.forName("windows-1254"));
        }
    }

    private void importHeaderWithCharset(Path csvPath, Charset cs) throws Exception {

        try (Reader reader = Files.newBufferedReader(csvPath, cs);
             CSVParser parser = CSVFormat.DEFAULT
                     .withDelimiter(';')
                     .withQuote('"')
                     .parse(reader)) {

            var it = parser.iterator();

            if (!it.hasNext()) throw new RuntimeException("1. satır yok (kodlar)");
            CSVRecord kodSatiri = it.next();

            if (!it.hasNext()) throw new RuntimeException("2. satır yok (açıklamalar)");
            CSVRecord adSatiri = it.next();

            // Türkçe bozuldu mu kontrol et (basit ama işe yarar)
            // "iş" kelimesi "iÅŸ" gibi görünürse encoding yanlıştır.
            String test = adSatiri.size() > 0 ? adSatiri.get(0) : "";
            if (looksBrokenTurkish(test)) {
                throw new BadTurkishEncodingException("Türkçe karakterler bozuk görünüyor. Charset: " + cs);
            }

            conn.setAutoCommit(false);

            String sqlFind = "SELECT id FROM endeks_tanim WHERE kod=?";
            String sqlInsert = "INSERT INTO endeks_tanim(kod, ad) VALUES(?,?)";

            try (PreparedStatement psFind = conn.prepareStatement(sqlFind);
                 PreparedStatement psIns = conn.prepareStatement(sqlInsert)) {

                for (int i = 0; i < kodSatiri.size(); i++) {

                    String kod = kodSatiri.get(i);
                    String ad  = (adSatiri.size() > i) ? adSatiri.get(i) : null;

                    kod = (kod == null) ? "" : kod.trim().replace("\uFEFF", "");
                    ad  = (ad  == null) ? "" : ad.trim();

                    // ✅ "0" dahil, sadece boş kodu atla
                    if (kod.isEmpty()) continue;

                    psFind.setString(1, kod);
                    try (ResultSet rs = psFind.executeQuery()) {
                        if (!rs.next()) {
                            psIns.setString(1, kod);
                            psIns.setString(2, ad.isEmpty() ? null : ad);
                            psIns.executeUpdate();
                        } else {
                            // İstersen mevcut kaydın ad'ı boşsa güncelle
                            // (Önceden 0 yokken import ettiysen ad boş kalmış olabilir)
                            // Bu opsiyonel: updateBlankAd(kod, ad);
                        }
                    }
                }

                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    private static boolean looksBrokenTurkish(String s) {
        if (s == null) return false;
        // UTF-8 bytes CP1254 ile okununca sık görülen bozulma örnekleri:
        return s.contains("Ã") || s.contains("Å") || s.contains("Ä") || s.contains("Ã¼") || s.contains("Ã¶");
    }

    private static class BadTurkishEncodingException extends RuntimeException {
        public BadTurkishEncodingException(String msg) { super(msg); }
    }
}

