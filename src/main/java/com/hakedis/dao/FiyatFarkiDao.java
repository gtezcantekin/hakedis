package com.hakedis.dao;

import com.hakedis.model.FiyatFarki;
import com.hakedis.util.JDBCHelper;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FiyatFarkiDao {


        // INSERT + UPDATE (ON DUPLICATE KEY)
        public void saveOrUpdate(FiyatFarki ff) {

            String sql =
                    "INSERT INTO fiyatfarki " +
                            "(is_id, hakedis_id, isprogrami_id, hakedisno, isprogramidonem, " +
                            " isprogramitutar, hakedistutar, bkatsayi, pneksibir, fiyatfarki) " +
                            "VALUES (?,?,?,?,?,?,?,?,?,?) " +
                            "ON DUPLICATE KEY UPDATE " +
                            "hakedisno = VALUES(hakedisno), " +
                            "isprogramidonem = VALUES(isprogramidonem), " +
                            "isprogramitutar = VALUES(isprogramitutar), " +
                            "hakedistutar = VALUES(hakedistutar), " +
                            "bkatsayi = VALUES(bkatsayi), " +
                            "pneksibir = VALUES(pneksibir), " +
                            "fiyatfarki = VALUES(fiyatfarki)";

            try (Connection conn = JDBCHelper.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, ff.getIsId());
                ps.setInt(2, ff.getHakedisId());
                ps.setLong(3, ff.getIsprogramId());
                ps.setInt(4, ff.getHakedisNo());
                ps.setDate(5, Date.valueOf(ff.getIsProgramiDonem()));
                ps.setBigDecimal(6, ff.getIsProgramiTutar());
                ps.setBigDecimal(7, ff.getHakedisTutar());
                ps.setBigDecimal(8, ff.getbKatSayisi());
                ps.setBigDecimal(9, ff.getPnEksiBir());
                ps.setBigDecimal(10, ff.getFiyatFarki());

                ps.executeUpdate();

            } catch (Exception e) {
                System.out.println("Hata (fiyatfarki saveOrUpdate): " + e.getMessage());
            }
        }

    public List<FiyatFarki> findByIsIdAndHakNo(int isId, int hakNo) {
        List<FiyatFarki> liste = new ArrayList<>();
        String sql = """
                    SELECT is_id, hakedis_id, isprogrami_id, hakedisno, isprogramidonem,
                          isprogramitutar, hakedistutar, bkatsayi, pneksibir, fiyatfarki
                    FROM fiyatfarki
                    WHERE is_id = ?
                      AND hakedisno = ?
                """;

        try (Connection c = JDBCHelper.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, isId);
            ps.setInt(2, hakNo);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                FiyatFarki ff = new FiyatFarki();
                ff.setIsId(rs.getInt("is_id"));
                ff.setHakedisId(rs.getInt("hakedis_id"));
                ff.setIsprogramId(rs.getInt("isprogrami_id"));
                ff.setHakedisNo(rs.getInt("hakedisno"));
                ff.setIsProgramiDonem(rs.getObject("isprogramidonem", LocalDate.class));
                ff.setIsProgramiTutar(rs.getBigDecimal("isprogramitutar"));
                ff.setHakedisTutar(rs.getBigDecimal("hakedistutar"));
                ff.setbKatSayisi(rs.getBigDecimal("bkatsayi"));
                ff.setPnEksiBir(rs.getBigDecimal("pneksibir"));
                ff.setFiyatFarki(rs.getBigDecimal("fiyatfarki"));
                liste.add(ff);
            }

        } catch (Exception e) {
            System.out.println("fiyatfarki findByIsIdAndHakNo hatası: " + e.getMessage());
        }
        return liste;
    }

    public FiyatFarki findByHakIdIsProgId(int hakId, long isPId) {

        String sql = """
                    SELECT is_id, hakedis_id, isprogrami_id, hakedisno, isprogramidonem,
                          isprogramitutar, hakedistutar, bkatsayi, pneksibir, fiyatfarki
                    FROM fiyatfarki
                    WHERE hakedis_id = ?
                    AND isprogrami_id = ?
                    LIMIT 1
                """;

        try (Connection c = JDBCHelper.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, hakId);
            ps.setLong(2, isPId);


            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                FiyatFarki ff = new FiyatFarki();
                ff.setIsId(rs.getInt("is_id"));
                ff.setHakedisId(rs.getInt("hakedis_id"));
                ff.setIsprogramId(rs.getInt("isprogrami_id"));
                ff.setHakedisNo(rs.getInt("hakedisno"));
                ff.setIsProgramiDonem(rs.getObject("isprogramidonem", LocalDate.class));
                ff.setIsProgramiTutar(rs.getBigDecimal("isprogramitutar"));
                ff.setHakedisTutar(rs.getBigDecimal("hakedistutar"));
                ff.setbKatSayisi(rs.getBigDecimal("bkatsayi"));
                ff.setPnEksiBir(rs.getBigDecimal("pneksibir"));
                ff.setFiyatFarki(rs.getBigDecimal("fiyatfarki"));
                return ff;
            }

        } catch (Exception e) {
            System.out.println("fiyatfarki findByHakIdIsProgId hatası: " + e.getMessage());
        }
        return null;
    }


    public FiyatFarki findByIsIdAndHakId(int isId, int hakNo) {

        String sql = """
                    SELECT is_id, hakedis_id, isprogrami_id, hakedisno, isprogramidonem,
                          isprogramitutar, hakedistutar, bkatsayi, pneksibir, fiyatfarki
                    FROM fiyatfarki
                    WHERE is_id = ?
                    AND hakedisno = ?
                    LIMIT 1
                """;

        try (Connection c = JDBCHelper.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, isId);
            ps.setInt(2, hakNo);


            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                FiyatFarki ff = new FiyatFarki();
                ff.setIsId(rs.getInt("is_id"));
                ff.setHakedisId(rs.getInt("hakedis_id"));
                ff.setIsprogramId(rs.getInt("isprogrami_id"));
                ff.setHakedisNo(rs.getInt("hakedisno"));
                ff.setIsProgramiDonem(rs.getObject("isprogramidonem", LocalDate.class));
                ff.setIsProgramiTutar(rs.getBigDecimal("isprogramitutar"));
                ff.setHakedisTutar(rs.getBigDecimal("hakedistutar"));
                ff.setbKatSayisi(rs.getBigDecimal("bkatsayi"));
                ff.setPnEksiBir(rs.getBigDecimal("pneksibir"));
                ff.setFiyatFarki(rs.getBigDecimal("fiyatfarki"));
                return ff;
            }

        } catch (Exception e) {
            System.out.println("fiyatfarki findByIsIdAndIsProgId hatası: " + e.getMessage());
        }
        return null;
    }

    public List<FiyatFarki> findOncekilerByIsIdBeforeHakNo(int isId, int hakNo) {
        List<FiyatFarki> list = new ArrayList<>();
        String sql = "SELECT isprogrami_id, hakedistutar " +
                "FROM fiyatfarki " +
                "WHERE is_id = ? AND hakedisno < ?";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, isId);
            ps.setInt(2, hakNo);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    FiyatFarki ff = new FiyatFarki();
                    ff.setIsprogramId(rs.getLong("isprogrami_id"));
                    ff.setHakedisTutar(rs.getBigDecimal("hakedistutar"));
                    list.add(ff);
                }
            }
        } catch (Exception e) {
            System.out.println("Hata(findOncekilerByIsIdBeforeHakNo): " + e.getMessage());
        }
        return list;
    }

    public void deleteByIsIdAndHakNo(int isId, int hakNo) {
        String sql = "DELETE FROM fiyatfarki WHERE is_id = ? AND hakedisno < ?";
        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, isId);
            ps.setInt(2, hakNo);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Hata(deleteByIsIdAndHakNo): " + e.getMessage());
        }
    }


}
