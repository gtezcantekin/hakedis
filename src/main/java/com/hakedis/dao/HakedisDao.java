package com.hakedis.dao;

import com.hakedis.model.Hakedis;
import com.hakedis.util.JDBCHelper;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HakedisDao {

    public void insertHakedis(Hakedis hakedis) {
        String sqlKayit = "INSERT INTO hakedis (is_id, hakedisno, hakedistarih) VALUES (?,?,?)";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlKayit)) {

            ps.setInt(1, hakedis.getIs_id());
            ps.setInt(2, hakedis.getHakedisNo());
            if (hakedis.getTarih() != null)
                ps.setDate(3, Date.valueOf(hakedis.getTarih()));
            else ps.setNull(3, Types.DATE);


            ps.executeUpdate();
            System.out.println("Kayıt başarılı.");
        } catch (Exception e) {
            System.out.println("Hata : " + e.getMessage());
        }

    }

    public List<Hakedis> findByIsId(int isId) {
        List<Hakedis> liste = new ArrayList<>();
        String sqlSorgu = "SELECT * FROM hakedis WHERE is_id = ?";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {
            ps.setInt(1, isId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Hakedis hk = new Hakedis();
                hk.setId(rs.getInt("id"));
                hk.setIs_id(rs.getInt("is_id"));
                hk.setHakedisNo(rs.getInt("hakedisno"));
                hk.setTarih(rs.getObject("hakedistarih", LocalDate.class));
                liste.add(hk);
            }
        } catch (Exception e) {
            System.out.println("Hata : " + e.getMessage());
        }
        return liste;
    }

    public Hakedis findByHakedisId(int isId, int hakNo) {
        String sqlSorgu = "SELECT * FROM hakedis WHERE is_id = ? AND hakedisno = ?";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {
            ps.setInt(1, isId);
            ps.setInt(2, hakNo);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Hakedis hk = new Hakedis();
                hk.setId(rs.getInt("id"));
                hk.setIs_id(rs.getInt("is_id"));
                hk.setHakedisNo(rs.getInt("hakedisno"));
                hk.setTarih(rs.getObject("hakedistarih", LocalDate.class));
                return hk;
            }
        } catch (Exception e) {
            System.out.println("Hata : hakedisDao " + e.getMessage());
        }
        return null;
    }

    public List<Hakedis> tumHakedisleriListele() {
        List<Hakedis> liste = new ArrayList<>();
        String sqlSorgu = "SELECT id, is_id, hakedisno, hakedistarih FROM hakedis";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Hakedis hk = new Hakedis();
                hk.setId(rs.getInt("id"));
                hk.setIs_id(rs.getInt("is_id"));
                hk.setHakedisNo(rs.getInt("hakedisno"));
                hk.setTarih(rs.getObject("hakedistarih", LocalDate.class));
                liste.add(hk);
            }
        } catch (Exception e) {
            System.out.println("Hata : " + e.getMessage());
        }
        return liste;
    }

    public Hakedis findId(Hakedis h) {
        String sqlSorgu = "SELECT id FROM hakedis WHERE is_id = ? AND hakedisno = ? AND hakedistarih = ?";
        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {
            ps.setInt(1, h.getIs_id());
            ps.setInt(2, h.getHakedisNo());
            ps.setDate(3, Date.valueOf(h.getTarih()));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Hakedis hs = new Hakedis();
                hs.setId(rs.getInt("id"));
                return hs;
            }

        } catch (SQLException e) {
            System.out.println("Hata : " + e.getMessage());
        }
        return null;
    }

    public Hakedis findHakedisTarih(int isId, int hakNo) {
        String sqlSorgu = "SELECT * FROM hakedis WHERE is_id = ? AND hakedisno = ?";
        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {
            ps.setInt(1, isId);
            ps.setInt(2, hakNo);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Hakedis hs = new Hakedis();
                hs.setTarih(rs.getObject("hakedistarih", LocalDate.class));
                return hs;
            }

        } catch (SQLException e) {
            System.out.println("Hata hakedisDao tarih : " + e.getMessage());
        }
        return null;
    }
}
