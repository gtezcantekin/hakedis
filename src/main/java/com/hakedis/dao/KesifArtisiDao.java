package com.hakedis.dao;

import com.hakedis.model.KesifArtisi;
import com.hakedis.util.JDBCHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KesifArtisiDao {

    public int insertKesifArtisi(KesifArtisi ka) {

        String sqlKayit = "INSERT INTO kesifartisi" +
                " (is_id, hakedis_id, hakedis_no, aciklama, artismiktari) VALUES (?,?,?,?,?)";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlKayit)) {

            ps.setInt(1, ka.getIs_id());
            ps.setInt(2, ka.getHakedis_id());
            ps.setInt(3,ka.getHakedis_no());
            ps.setString(4, ka.getAciklama());
            ps.setBigDecimal(5, ka.getKesifArtisi());

            ps.executeUpdate();


        } catch (Exception e) {
            System.out.println("Hata (süre uzatımı): " + e.getMessage());

        }
        return 0;
    }

    public KesifArtisi findByisIdAndHakNo(int isId, int hakNo) {
        String sqlSorgu = "SELECT id, is_id, hakedis_id, hakedis_no aciklama, artismiktari " +
                "FROM kesifartisi WHERE is_id = ? AND hakedis_no = ?";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {
            ps.setInt(1, isId);
            ps.setInt(2, hakNo);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                KesifArtisi k = new KesifArtisi();
                k.setId(rs.getInt("id"));
                k.setIs_id(rs.getInt("is_id"));
                k.setHakedis_id(rs.getInt("hakedis_id"));
                k.setHakedis_no(rs.getInt("hakedis_no"));
                k.setAciklama(rs.getString("aciklama"));
                k.setKesifArtisi(rs.getBigDecimal("artismiktari"));
                return k;
            }
        } catch (Exception e) {
            System.out.println("Hata : " + e.getMessage());
        }
        return null;
    }

    public List<KesifArtisi> listByIsId(int isId) {
        List<KesifArtisi> liste = new ArrayList<>();
        String sqlSorgu = "SELECT id, is_id, hakedis_id, hakedis_no, aciklama, artismiktari " +
                "FROM kesifartisi WHERE is_id = ? ORDER BY id";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {
            ps.setInt(1, isId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KesifArtisi k = new KesifArtisi();
                k.setId(rs.getInt("id"));
                k.setIs_id(rs.getInt("is_id"));
                k.setHakedis_id(rs.getInt("hakedis_id"));
                k.setHakedis_no(rs.getInt("hakedis_no"));
                k.setAciklama(rs.getString("aciklama"));
                k.setKesifArtisi(rs.getBigDecimal("artismiktari"));
                liste.add(k);
            }
        } catch (Exception e) {
            System.out.println("Hata : " + e.getMessage());
        }
        return liste;
    }

    public List<KesifArtisi> listByIsIdAndHakedisNo(int isId, int hakNo) {
        List<KesifArtisi> liste = new ArrayList<>();
        String sqlSorgu = "SELECT id, is_id, hakedis_id, hakedis_no aciklama, artismiktari " +
                "FROM kesifartisi WHERE is_id = ? AND hakedis_no = ? ORDER BY id";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {
            ps.setInt(1, isId);
            ps.setInt(2, hakNo);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KesifArtisi k = new KesifArtisi();
                k.setId(rs.getInt("id"));
                k.setIs_id(rs.getInt("is_id"));
                k.setHakedis_id(rs.getInt("hakedis_id"));
                k.setHakedis_no(rs.getInt("hakedis_no"));
                k.setAciklama(rs.getString("aciklama"));
                k.setKesifArtisi(rs.getBigDecimal("artismiktari"));
                liste.add(k);
            }
        } catch (Exception e) {
            System.out.println("Hata : " + e.getMessage());
        }
        return liste;
    }


}
