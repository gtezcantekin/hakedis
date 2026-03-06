package com.hakedis.dao;

import com.hakedis.model.Isler;
import com.hakedis.util.JDBCHelper;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IslerDao {


    public void insertIsler(Isler isler) {
        String sqlKayit = "INSERT INTO isler (firma_id, isinadi, ihaletarihi, ihalebedeli, sozlesmetarihi, " +
                "yerteslimitarihi, isinsuresi, sozlesmebitimtarihi " +
                ") VALUES (?,?,?,?,?,?,?,?)";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlKayit)) {

            ps.setInt(1, isler.getFirma_id());
            ps.setString(2, isler.getIsinadi());
            if (isler.getIhaletarihi() != null)
                ps.setDate(3, Date.valueOf(isler.getIhaletarihi()));
            else ps.setNull(3, Types.DATE);
            ps.setBigDecimal(4, isler.getIhaleBedeli());
            if (isler.getSozlesmetarihi() != null)
                ps.setDate(5, Date.valueOf(isler.getSozlesmetarihi()));
            else ps.setNull(5, Types.DATE);
            if (isler.getYerteslimitarihi() != null)
                ps.setDate(6, Date.valueOf(isler.getYerteslimitarihi()));
            else ps.setNull(6, Types.DATE);
            ps.setInt(7, isler.getIsinsuresi());
            if (isler.getSozlesmebitimtarihi() != null)
                ps.setDate(8, Date.valueOf(isler.getSozlesmebitimtarihi()));
            else ps.setNull(8, Types.DATE);

            ps.executeUpdate();
            System.out.println("Kayıt başarılı");
        } catch (Exception e) {
            System.out.println("Hata (kayıtta hata) : " + e.getMessage());
        }
    }

    public Isler findById (int id){
        String sqlSorgu = "SELECT * FROM isler WHERE id = ? ";

        try(Connection conn = JDBCHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {

            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                Isler is = new Isler();
                is.setId(rs.getInt("id"));
                is.setFirma_id(rs.getInt("firma_id"));
                is.setIsinadi(rs.getString("isinadi"));
                is.setIhaletarihi(rs.getObject("ihaletarihi", LocalDate.class));
                is.setIhaleBedeli(rs.getBigDecimal("ihalebedeli"));
                is.setSozlesmetarihi(rs.getObject("sozlesmetarihi", LocalDate.class));
                is.setYerteslimitarihi(rs.getObject("yerteslimitarihi", LocalDate.class));
                is.setIsinsuresi(rs.getInt("isinsuresi"));
                is.setSozlesmebitimtarihi(rs.getObject("sozlesmebitimtarihi", LocalDate.class));

                return is;
            }


        } catch (Exception e) {
            System.out.println("Hata : " + e.getMessage());
        }

        return null;
    }

    public List<Isler> listeleTumIsler() {
        List<Isler> liste = new ArrayList<>();
        String sqlSorgu = "SELECT id, firma_id, isinadi, ihaletarihi, ihalebedeli, sozlesmetarihi, " +
                "yerteslimitarihi, isinsuresi, sozlesmebitimtarihi, sureuzatimi, sureuzatımınagoresbt, " +
                "kesifartisi, kailetoplambedel FROM isler";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Isler is = new Isler();
                    is.setId(rs.getInt("id"));
                    is.setFirma_id(rs.getInt("firma_id"));
                    is.setIsinadi(rs.getString("isinadi"));
                    is.setIhaletarihi(rs.getObject("ihaletarihi", LocalDate.class));
                    is.setIhaleBedeli(rs.getBigDecimal("ihalebedeli"));
                    is.setSozlesmetarihi(rs.getObject("sozlesmetarihi", LocalDate.class));
                    is.setYerteslimitarihi(rs.getObject("yerteslimitarihi", LocalDate.class));
                    is.setIsinsuresi(rs.getInt("isinsuresi"));
                    is.setSozlesmebitimtarihi(rs.getObject("sozlesmebitimtarihi", LocalDate.class));

                    liste.add(is);

                }
            }

        } catch (Exception e) {
            System.out.println("Hata (Listelemede hata) : " + e.getMessage());
        } return  liste;
    }

    public List<Isler> listeleByFirmaId ( int firmaId){
        List<Isler> liste = new ArrayList<>();
        String sqlSorgu = "SELECT id, firma_id, isinadi, ihaletarihi, ihalebedeli, sozlesmetarihi, " +
                "yerteslimitarihi, isinsuresi, sozlesmebitimtarihi, sureuzatimi, sureuzatımınagoresbt, " +
                "kesifartisi, kailetoplambedel FROM isler WHERE firma_id = ?";

        try ( Connection conn = JDBCHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(sqlSorgu)){
            ps.setInt(1,firmaId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    Isler is = new Isler();
                    is.setId(rs.getInt("id"));
                    is.setFirma_id(rs.getInt("firma_id"));
                    is.setIsinadi(rs.getString("isinadi"));
                    is.setIhaletarihi(rs.getObject("ihaletarihi", LocalDate.class));
                    is.setIhaleBedeli(rs.getBigDecimal("ihalebedeli"));
                    is.setSozlesmetarihi(rs.getObject("sozlesmetarihi", LocalDate.class));
                    is.setYerteslimitarihi(rs.getObject("yerteslimitarihi", LocalDate.class));
                    is.setIsinsuresi(rs.getInt("isinsuresi"));
                    is.setSozlesmebitimtarihi(rs.getObject("sozlesmebitimtarihi", LocalDate.class));

                    liste.add(is);

                }
            }

        } catch (Exception e) {
            System.out.println("Hata : " + e.getMessage());
        }
        return liste;
    }
}

