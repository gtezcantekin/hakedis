package com.hakedis.dao;

import com.hakedis.model.Firmalar;
import com.hakedis.model.Users;
import com.hakedis.util.JDBCHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FirmaDao {


    public void insertFirma(Firmalar firma){

        String sqlKayit = "INSERT INTO firmalar (users_id, firmaadi) VALUES (?, ?)";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlKayit)){

            ps.setInt(1,firma.getUserId());
            ps.setString(2,firma.getFirmaAdi());

            ps.executeUpdate();

            System.out.println("Firma eklendi : " + firma.getFirmaAdi());
        } catch (Exception e) {
            System.out.println("Hata (Firma kayıt hatası) : " + e.getMessage());
        }
    }

    public Firmalar findById (int id, int userId){

        String sqlSorgu = "SELECT * FROM firmalar WHERE id = ? AND users_id = ?";
        try ( Connection conn = JDBCHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {
            ps.setInt(1, id);
            ps.setInt(2, userId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Firmalar f = new Firmalar();
                f.setId(rs.getInt("id"));
                f.setUserId(rs.getInt("users_id"));
                f.setFirmaAdi(rs.getString("firmaadi"));
                return f;
            }
        } catch (Exception e) {
            System.out.println("Hata : " + e.getMessage());
        } return null;
    }

    public List<Firmalar> listeleTumFirmalar() {
        List<Firmalar> liste = new ArrayList<>();
        String sqlSorgu = "SELECT id, users_id, firmaadi FROM firmalar";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Firmalar f = new Firmalar();
                    f.setId(rs.getInt("id"));
                    f.setUserId(rs.getInt("users_id"));
                    f.setFirmaAdi(rs.getString("firmaadi"));

                    liste.add(f);
                }

            }
        } catch (Exception e) {
            System.out.println("Hata (Listelenirken hata oldu) : " + e.getMessage());
        } return liste;
    }


    public List<Firmalar> listeleByUserId(int userId) {
        List<Firmalar> liste = new ArrayList<>();
        String sqlSorgu = "SELECT id, users_id, firmaadi FROM firmalar WHERE users_id = ?";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {
                ps.setInt(1,userId);
            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Firmalar f = new Firmalar();
                    f.setId(rs.getInt("id"));
                    f.setUserId(rs.getInt("users_id"));
                    f.setFirmaAdi(rs.getString("firmaadi"));

                    liste.add(f);
                }

            }
        } catch (Exception e) {
            System.out.println("Hata (Listelenirken hata oldu) : " + e.getMessage());
        } return liste;
    }
}
