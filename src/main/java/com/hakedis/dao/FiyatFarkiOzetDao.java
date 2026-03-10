package com.hakedis.dao;

import com.hakedis.model.FiyatFarki;
import com.hakedis.model.FiyatFarkiOzet;
import com.hakedis.util.JDBCHelper;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FiyatFarkiOzetDao {

    public void insert(FiyatFarkiOzet ffo) {

        String sql =
                "INSERT INTO fiyatfarki_ozet " +
                        "(is_id, hakedis_id, isprogrami_id, hakedisno, isprogramidonem, " +
                        " isprogramitutar, hakedistutar, bkatsayi, pneksibir, fiyatfarki) " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ffo.getIsId());
            ps.setInt(2, ffo.getHakedisId());
            ps.setLong(3, ffo.getIsprogramiId());
            ps.setInt(4, ffo.getHakedisNo());
            ps.setDate(5, Date.valueOf(ffo.getIsprogramiDonem()));
            ps.setBigDecimal(6, ffo.getIsprogramiTutar());

            if (ffo.getHakedisTutar() != null)
                ps.setBigDecimal(7, ffo.getHakedisTutar());
            else
                ps.setNull(7, Types.DECIMAL);

            if (ffo.getbKatSayisi() != null)
                ps.setBigDecimal(8, ffo.getbKatSayisi());
            else
                ps.setNull(8, Types.DECIMAL);

            if (ffo.getPnEksiBir() != null)
                ps.setBigDecimal(9, ffo.getPnEksiBir());
            else
                ps.setNull(9, Types.DECIMAL);

            if (ffo.getFiyatFarki() != null)
                ps.setBigDecimal(10, ffo.getFiyatFarki());
            else
                ps.setNull(10, Types.DECIMAL);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Hata (fiyatfarki_ozet insert): " + e.getMessage());
        }
    }

    // 1️⃣ Bu hakediş için snapshot var mı?
    public boolean existsByHakedisId(int hakedisId) {

        String sql = "SELECT 1 FROM fiyatfarki_ozet WHERE hakedis_id = ? LIMIT 1";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, hakedisId);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            System.out.println("Hata (existsByHakedisId): " + e.getMessage());
            return false;
        }
    }

    // 2️⃣ Çalışma tablosundan snapshot oluştur
    public int snapshotFromCalisma(int hakedisId) {

        if (existsByHakedisId(hakedisId)) {
            System.out.println("Snapshot zaten var.");
            return 0;
        }

        String sql =
                "INSERT INTO fiyatfarki_ozet " +
                        "(is_id, hakedis_id, isprogrami_id, hakedisno, isprogramidonem, " +
                        " isprogramitutar, hakedistutar, bkatsayi, pneksibir, fiyatfarki) " +
                        "SELECT is_id, hakedis_id, isprogrami_id, hakedisno, isprogramidonem, " +
                        "       isprogramitutar, hakedistutar, bkatsayi, pneksibir, fiyatfarki " +
                        "FROM fiyatfarki " +
                        "WHERE hakedis_id = ?";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, hakedisId);
            int inserted = ps.executeUpdate();

            System.out.println("Snapshot alındı. Satır sayısı: " + inserted);
            return inserted;

        } catch (Exception e) {
            System.out.println("Hata (snapshotFromCalisma): " + e.getMessage());
            return 0;
        }
    }

    // 3️⃣ Snapshot detaylarını getir
    public List<FiyatFarkiOzet> findByHakedisId(int hakedisId) {

        List<FiyatFarkiOzet> list = new ArrayList<>();

        String sql = "SELECT * FROM fiyatfarki_ozet WHERE hakedis_id = ?";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, hakedisId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                FiyatFarkiOzet ff = new FiyatFarkiOzet();

                ff.setId(rs.getLong("id"));
                ff.setIsId(rs.getInt("is_id"));
                ff.setHakedisId(rs.getInt("hakedis_id"));
                ff.setIsprogramiId(rs.getLong("isprogrami_id"));
                ff.setHakedisNo(rs.getInt("hakedisno"));
                ff.setIsprogramiDonem(rs.getObject("isprogramidonem", LocalDate.class));
                ff.setIsprogramiTutar(rs.getBigDecimal("isprogramitutar"));
                ff.setHakedisTutar(rs.getBigDecimal("hakedistutar"));
                ff.setbKatSayisi(rs.getBigDecimal("bkatsayi"));
                ff.setPnEksiBir(rs.getBigDecimal("pneksibir"));
                ff.setFiyatFarki(rs.getBigDecimal("fiyatfarki"));

                list.add(ff);
            }

        } catch (Exception e) {
            System.out.println("Hata (findByHakedisId): " + e.getMessage());
        }

        return list;
    }

    // 4️⃣ Snapshot toplam fiyat farkı
    public BigDecimal findToplamFiyatFarki(int hakedisId) {

        String sql = "SELECT SUM(fiyatfarki) FROM fiyatfarki_ozet WHERE hakedis_id = ?";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, hakedisId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                BigDecimal toplam = rs.getBigDecimal(1);
                return toplam != null ? toplam : BigDecimal.ZERO;
            }

        } catch (Exception e) {
            System.out.println("Hata (findToplamFiyatFarki): " + e.getMessage());
        }

        return BigDecimal.ZERO;
    }
    // 5 bir önceki hakediş fiyat farkı
    public List<FiyatFarkiOzet> findOncekilerByIsIdBeforeHakNo(int isId, int hakNo) {
        List<FiyatFarkiOzet> list = new ArrayList<>();
        String sql = "SELECT isprogrami_id, hakedistutar " +
                "FROM fiyatfarki_ozet " +
                "WHERE is_id = ? AND hakedisno < ?";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, isId);
            ps.setInt(2, hakNo);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    FiyatFarkiOzet ffo = new FiyatFarkiOzet();
                    ffo.setIsprogramiId(rs.getLong("isprogrami_id"));
                    ffo.setHakedisTutar(rs.getBigDecimal("hakedistutar"));
                    list.add(ffo);
                }
            }
        } catch (Exception e) {
            System.out.println("Hata(findOncekilerByIsIdBeforeHakNo): " + e.getMessage());
        }
        return list;
    }
}