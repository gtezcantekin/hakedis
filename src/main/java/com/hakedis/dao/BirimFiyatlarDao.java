package com.hakedis.dao;

import com.hakedis.model.BirimFiyatlar;
import com.hakedis.util.JDBCHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BirimFiyatlarDao {

    public void insertBFiyatlar (BirimFiyatlar birimFiyat){
        String sqlKayit = "INSERT INTO birimfiyatlar (is_id, bfiyatno, ad, tutari, miktari, metrajturu_id) " +
                "VALUES (?,?,?,?,?,?) ";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlKayit)){

            ps.setInt(1,birimFiyat.getIsId());
            ps.setString(2, birimFiyat.getBfiyatno());
            ps.setString(3, birimFiyat.getAd());
            ps.setBigDecimal(4,birimFiyat.getTutari());
            ps.setBigDecimal(5,birimFiyat.getMiktari());
            ps.setInt(6,birimFiyat.getMetrajturuId());

            ps.executeUpdate();

            System.out.println("Kayıt başarılı");
        } catch (Exception e) {
            System.out.println("Hata (Kayıtta hata) : " + e.getMessage());
        }
    }

    public BirimFiyatlar findById (int id) {
        String sqlSorgu = "SELECT * FROM birimfiyatlar WHERE id = ? ";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {

            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                BirimFiyatlar bf = new BirimFiyatlar();
                bf.setId(rs.getInt("id"));
                bf.setIsId(rs.getInt("is_id"));
                bf.setBfiyatno(rs.getString("bfiyatno"));
                bf.setAd(rs.getString("ad"));
                bf.setTutari(rs.getBigDecimal("tutari"));
                bf.setMetrajturuId(rs.getInt("metrajturu_id"));

                return bf;
            }


        } catch (Exception e) {
            System.out.println("Hata : " + e.getMessage());
        }
        return null;
    }

    public List<BirimFiyatlar> listeleTumBirimFiyatlar () {
        List<BirimFiyatlar> liste = new ArrayList<>();
        String sqlSorgu = "SELECT id, is_id, bfiyatno, ad, tutari, miktari, metrajturu_id FROM birimfiyatlar";

        try (Connection conn = JDBCHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BirimFiyatlar bf = new BirimFiyatlar();
                    bf.setId(rs.getInt("id"));
                    bf.setIsId(rs.getInt("is_id"));
                    bf.setBfiyatno(rs.getString("bfiyatno"));
                    bf.setAd(rs.getString("ad"));
                    bf.setTutari(rs.getBigDecimal("tutari"));
                    bf.setMiktari(rs.getBigDecimal("miktari"));
                    bf.setMetrajturuId(rs.getInt("metrajturu_id"));
                    liste.add(bf);
                }
            }

        } catch (Exception e) {
            System.out.println("Hata : " + e.getMessage());
        }

        return liste;
    }

    public List<BirimFiyatlar> listeleByIsId(int isId){
        List<BirimFiyatlar> liste = new ArrayList<>();
        String sqlSorgu = "SELECT * FROM birimfiyatlar WHERE is_id = ?";

        try (Connection conn = JDBCHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {
            ps.setInt(1,isId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BirimFiyatlar bf = new BirimFiyatlar();
                    bf.setId(rs.getInt("id"));
                    bf.setIsId(rs.getInt("is_id"));
                    bf.setBfiyatno(rs.getString("bfiyatno"));
                    bf.setAd(rs.getString("ad"));
                    bf.setTutari(rs.getBigDecimal("tutari"));
                    bf.setMiktari(rs.getBigDecimal("miktari"));
                    bf.setMetrajturuId(rs.getInt("metrajturu_id"));
                    liste.add(bf);
                }
            }
        } catch (Exception e) {
            System.out.println("Hata : " + e.getMessage());
        }
        return liste;
    }
}
