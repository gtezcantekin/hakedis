package com.hakedis.dao;

import com.hakedis.model.Hakedis;
import com.hakedis.model.Metrajlar;
import com.hakedis.util.JDBCHelper;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class MetrajlarDao {

    public void insertMetrajlar(Metrajlar metraj) {
        String sqlKayit = "INSERT INTO metrajhesaplama" +
                " (is_id, hakedis_id, hakedis_no, birimfiyat_id, metrajturu_id, aciklama, adet, en, boy, yukseklik," +
                "  agirlik, miktar) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlKayit)) {
            ps.setInt(1, metraj.getIsId());
            ps.setInt(2, metraj.getHakedisId());
            ps.setInt(3,metraj.getHakedisNo());
            ps.setInt(4, metraj.getBirimfiyatId());
            ps.setInt(5, metraj.getMetrajturuId());
            ps.setString(6, metraj.getAciklama());
            ps.setInt(7, metraj.getAdet());
            if (metraj.getEn() != null)
                ps.setBigDecimal(8, metraj.getEn());
            else ps.setNull(8, Types.DECIMAL);
            if (metraj.getBoy() != null)
                ps.setBigDecimal(9, metraj.getBoy());
            else ps.setNull(9, Types.DECIMAL);
            if (metraj.getYukseklik() != null)
                ps.setBigDecimal(10, metraj.getYukseklik());
            else ps.setNull(10, Types.DECIMAL);
            if (metraj.getAgirlik() != null)
                ps.setBigDecimal(11, metraj.getAgirlik());
            else ps.setNull(11, Types.DECIMAL);
            ps.setBigDecimal(12, metraj.getMiktar());


            ps.executeUpdate();
            System.out.println("Kayıt Başarılı");
        } catch (Exception e) {
            System.out.println("Hata : " + e.getMessage());
        }
    }

    public Metrajlar findById(int id) {
        String sqlSorgu = "SELECT * FROM metrajhesaplama WHERE id = ?";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Metrajlar m = new Metrajlar();
                m.setId(rs.getInt("id"));
                m.setIsId(rs.getInt("is_id"));
                m.setHakedisId(rs.getInt("hakedis_id"));
                m.setHakedisNo(rs.getInt("hakedis_no"));
                m.setBirimfiyatId(rs.getInt("birimfiyat_id"));
                m.setMetrajturuId(rs.getInt("metrajturu_id"));
                m.setAciklama(rs.getString("aciklama"));
                m.setAdet((Integer) rs.getInt("adet"));
                m.setEn(rs.getBigDecimal("en"));
                m.setBoy(rs.getBigDecimal("boy"));
                m.setYukseklik(rs.getBigDecimal("yukseklik"));
                m.setAgirlik(rs.getBigDecimal("agirlik"));
                m.setMiktar(rs.getBigDecimal("miktar"));

                return m;
            }

        } catch (Exception e) {
            System.out.println("Hata : " + e.getMessage());
        }
        return null;
    }

    public List<Metrajlar> hakedisById(int hakedisNo) {
        List<Metrajlar> liste = new ArrayList<>();
        String sqlSorgu = "SELECT * FROM metrajhesaplama WHERE hakedis_no = ?";
        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {

            ps.setInt(1, hakedisNo);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Metrajlar m = new Metrajlar();
                m.setId(rs.getInt("id"));
                m.setIsId(rs.getInt("is_id"));
                m.setHakedisId(rs.getInt("hakedis_id"));
                m.setHakedisNo(rs.getInt("hakedis_no"));
                m.setBirimfiyatId(rs.getInt("birimfiyat_id"));
                m.setMetrajturuId(rs.getInt("metrajturu_id"));
                m.setAciklama(rs.getString("aciklama"));
                m.setAdet((Integer) rs.getInt("adet"));
                m.setEn(rs.getBigDecimal("en"));
                m.setBoy(rs.getBigDecimal("boy"));
                m.setYukseklik(rs.getBigDecimal("yukseklik"));
                m.setAgirlik(rs.getBigDecimal("agirlik"));
                m.setMiktar(rs.getBigDecimal("miktar"));

                liste.add(m);
            }

        } catch (Exception e) {
            System.out.println("Hata : " + e.getMessage());
        }
        return liste;
    }

    public List<Metrajlar> listeleByIsAndHakedisId(int isId, int hakedisNo) {
        List<Metrajlar> liste = new ArrayList<>();
        String sqlSorgu = "SELECT id, is_id, hakedis_id, hakedis_no, birimfiyat_id, miktar FROM metrajhesaplama " +
                "WHERE is_id = ? AND hakedis_no = ? ";
        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {

            ps.setInt(1, isId);
            ps.setInt(2, hakedisNo);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Metrajlar m = new Metrajlar();
                m.setId(rs.getInt("id"));
                m.setIsId(rs.getInt("is_id"));
                m.setHakedisId(rs.getInt("hakedis_id"));
                m.setHakedisNo(rs.getInt("hakedis_no"));
                m.setBirimfiyatId(rs.getInt("birimfiyat_id"));
                m.setMiktar(rs.getBigDecimal("miktar"));
                liste.add(m);
            }

        } catch (Exception e) {
            System.out.println("Hata : metrajlardao " + e.getMessage());
        }
        return liste;
    }

    public List<Metrajlar> listeleByIsVeHakedisKadar(int isId, int hakedisNo) {
        List<Metrajlar> liste = new ArrayList<>();
        String sqlSorgu = """
                        SELECT m.*
                        FROM metrajhesaplama m
                        JOIN hakedis h ON h.id = m.hakedis_id
                        WHERE h.is_id = ?
                        AND h.hakedisno <= ?
                        ORDER BY h.hakedisno, m.id
                        """;

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {

            ps.setInt(1, isId);
            ps.setInt(2, hakedisNo);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Metrajlar m = new Metrajlar();
                m.setId(rs.getInt("id"));
                m.setIsId(rs.getInt("is_id"));
                m.setHakedisId(rs.getInt("hakedis_id"));
                m.setHakedisNo(rs.getInt("hakedis_no"));
                m.setBirimfiyatId(rs.getInt("birimfiyat_id"));
                m.setMiktar(rs.getBigDecimal("miktar"));
                liste.add(m);
            }

        } catch (Exception e) {
            System.out.println("Hata : MetrajlarDao " + e.getMessage());
        }
        return liste;
    }

    public List<Metrajlar> listeleByIs(int isId) {
        List<Metrajlar> liste = new ArrayList<>();
        String sqlSorgu = "SELECT m.* FROM metrajhesaplama m JOIN hakedis h ON m.hakedis_id = h.id " +
                "WHERE h.is_id = ? ORDER BY h.id;";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {

            ps.setInt(1, isId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Metrajlar m = new Metrajlar();
                m.setId(rs.getInt("id"));
                m.setIsId(rs.getInt("is_id"));
                m.setHakedisId(rs.getInt("hakedis_id"));
                m.setBirimfiyatId(rs.getInt("birimfiyat_id"));
                m.setMiktar(rs.getBigDecimal("miktar"));
                liste.add(m);
            }

        } catch (Exception e) {
            System.out.println("Hata : " + e.getMessage());
        }
        return liste;
    }

    public List<Metrajlar> listeleByIsVeBfId(int isId, int bfId) {
        List<Metrajlar> liste = new ArrayList<>();
        String sqlSorgu = "SELECT m.* FROM metrajhesaplama m JOIN hakedis h ON m.hakedis_id = h.id " +
                "WHERE h.is_id = ? AND h.id <= ? ORDER BY h.id;";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {

            ps.setInt(1, isId);
            ps.setInt(2, bfId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Metrajlar m = new Metrajlar();
                m.setId(rs.getInt("id"));
                m.setHakedisId(rs.getInt("hakedis_id"));
                m.setBirimfiyatId(rs.getInt("birimfiyat_id"));
                m.setMiktar(rs.getBigDecimal("miktar"));
                liste.add(m);
            }

        } catch (Exception e) {
            System.out.println("Hata : " + e.getMessage());
        }
        return liste;
    }

}
