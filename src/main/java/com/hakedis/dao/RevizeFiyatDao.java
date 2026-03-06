package com.hakedis.dao;

import com.hakedis.model.RevizeFiyat;
import com.hakedis.util.JDBCHelper;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RevizeFiyatDao {

    // UPSERT: is_id + birimfiyatlar_id unique olduğu için çalışır
    public void saveOrUpdate(RevizeFiyat r) {

        String sql = """
                INSERT INTO revizefiyat (is_id, hakedis_id, hakedis_no, birimfiyatlar_id,
                bfnoad, bfnoadrev, revize_bf_formul, revize_bf_manuel)
                               VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                               ON DUPLICATE KEY UPDATE
                               revize_bf_formul = VALUES(revize_bf_formul),
                               revize_bf_manuel = VALUES(revize_bf_manuel)
                """;

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, r.getIsId());
            ps.setInt(2, r.getHakedisId());
            ps.setInt(3, r.getHakedisNo());
            ps.setInt(4, r.getBfId());
            ps.setString(5, r.getBfNoAd());
            ps.setString(6, r.getBfNoAdRev());
            setBigDecimalOrNull(ps, 7, r.getRevizeBfFormul());
            setBigDecimalOrNull(ps, 8, r.getRevizeBfManuel());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("RevizeFiyatDao saveOrUpdate hata: " + e.getMessage());
        }
    }

    public List<RevizeFiyat> listeleByIsId(int isId) {
        List<RevizeFiyat> liste = new ArrayList<>();
        String sql =
                "SELECT id, is_id, hakedis_id, hakedis_no, birimfiyatlar_id, \n" +
                        "                bfnoad, bfnoadrev, revize_bf_formul, revize_bf_manuel " +
                        "FROM revizefiyat WHERE is_id=?";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, isId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RevizeFiyat r = new RevizeFiyat();
                    r.setId(rs.getInt("id"));
                    r.setIsId(rs.getInt("is_id"));
                    r.setHakedisId(rs.getInt("hakedis_id"));
                    r.setHakedisNo(rs.getInt("hakedis_no"));
                    r.setBfId(rs.getInt("birimfiyatlar_id"));
                    r.setBfNoAd(rs.getString("bfnoad"));
                    r.setBfNoAdRev(rs.getString("bfnoadrev"));
                    r.setRevizeBfFormul(rs.getBigDecimal("revize_bf_formul"));
                    r.setRevizeBfManuel(rs.getBigDecimal("revize_bf_manuel"));
                    liste.add(r);
                }
            }

        } catch (SQLException e) {
            System.out.println("RevizeFiyatDao find hata: " + e.getMessage());
        }
        return liste;
    }

    public List<RevizeFiyat> listeleByIsAndHakedisId(int isId, int hakNo) {
        List<RevizeFiyat> liste = new ArrayList<>();
        String sql =
                "SELECT id, is_id, hakedis_id, hakedis_no, birimfiyatlar_id," +
                        "bfnoad, bfnoadrev, revize_bf_formul, revize_bf_manuel " +
                        "FROM revizefiyat WHERE is_id=? AND hakedis_no=?";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, isId);
            ps.setInt(2, hakNo);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RevizeFiyat r = new RevizeFiyat();
                    r.setId(rs.getInt("id"));
                    r.setIsId(rs.getInt("is_id"));
                    r.setHakedisId(rs.getInt("hakedis_id"));
                    r.setHakedisNo(rs.getInt("hakedis_no"));
                    r.setBfId(rs.getInt("birimfiyatlar_id"));
                    r.setBfNoAd(rs.getString("bfnoad"));
                    r.setBfNoAdRev(rs.getString("bfnoadrev"));
                    r.setRevizeBfFormul(rs.getBigDecimal("revize_bf_formul"));
                    r.setRevizeBfManuel(rs.getBigDecimal("revize_bf_manuel"));
                    liste.add(r);
                }
            }

        } catch (SQLException e) {
            System.out.println("RevizeFiyatDao find hata: " + e.getMessage());
        }
        return liste;
    }

    public RevizeFiyat findByIsIdAndBfId(int isId, int bfId, int hakNo) {
        String sql =
                "SELECT id, is_id, hakedis_id, hakedis_no, birimfiyatlar_id, " +
                        "bfnoad, bfnoadrev, revize_bf_formul, revize_bf_manuel " +
                        "FROM revizefiyat WHERE is_id=? AND birimfiyatlar_id=? AND hakedis_no=?";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, isId);
            ps.setInt(2, bfId);
            ps.setInt(3, hakNo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    RevizeFiyat r = new RevizeFiyat();
                    r.setId(rs.getInt("id"));
                    r.setIsId(rs.getInt("is_id"));
                    r.setHakedisId(rs.getInt("hakedis_id"));
                    r.setHakedisNo(rs.getInt("hakedis_no"));
                    r.setBfId(rs.getInt("birimfiyatlar_id"));
                    r.setBfNoAd(rs.getString("bfnoad"));
                    r.setBfNoAdRev(rs.getString("bfnoadrev"));
                    r.setRevizeBfFormul(rs.getBigDecimal("revize_bf_formul"));
                    r.setRevizeBfManuel(rs.getBigDecimal("revize_bf_manuel"));
                    return r;
                }
            }

        } catch (SQLException e) {
            System.out.println("RevizeFiyatDao find hata: " + e.getMessage());
        }
        return null;
    }



    public BigDecimal findFormulBf(int isId, int hakNo, int bfId) {
        String sql = "SELECT revize_bf_formul FROM revizefiyat WHERE is_id=? AND hakedis_no=? AND birimfiyatlar_id=?";
        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, isId);
            ps.setInt(2, hakNo);
            ps.setInt(3, bfId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getBigDecimal(1);
            }

        } catch (SQLException e) {
            System.out.println("RevizeFiyatDao findFormulBf hata: " + e.getMessage());
        }
        return null;
    }
    /// / buradan aşağısını sonra düzenle *************
    public BigDecimal findManuelBf(int isId, int bfId) {
        String sql = "SELECT revize_bf_manuel FROM revizefiyat WHERE is_id=? AND birimfiyatlar_id=?";
        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, isId);
            ps.setInt(2, bfId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getBigDecimal(1);
            }

        } catch (SQLException e) {
            System.out.println("RevizeFiyatDao findManuelBf hata: " + e.getMessage());
        }
        return null;
    }

    // manuel revizeyi sonradan elle gireceğiniz için ayrı update metodu
    public void updateManuelBf(int isId, int bfId, BigDecimal manuel) {
        String sql = "UPDATE revizefiyat SET revize_bf_manuel=? WHERE is_id=? AND birimfiyatlar_id=?";
        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            setBigDecimalOrNull(ps, 1, manuel);
            ps.setInt(2, isId);
            ps.setInt(3, bfId);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("RevizeFiyatDao updateManuelBf hata: " + e.getMessage());
        }
    }

    public void delete(int isId, int bfId) {
        String sql = "DELETE FROM revizefiyat WHERE is_id=? AND birimfiyatlar_id=?";
        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, isId);
            ps.setInt(2, bfId);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("RevizeFiyatDao delete hata: " + e.getMessage());
        }
    }

    private void setBigDecimalOrNull(PreparedStatement ps, int idx, BigDecimal v) throws SQLException {
        if (v != null) ps.setBigDecimal(idx, v);
        else ps.setNull(idx, Types.DECIMAL);
    }
}
