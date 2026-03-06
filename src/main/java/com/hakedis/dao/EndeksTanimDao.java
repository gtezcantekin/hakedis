package com.hakedis.dao;

import com.hakedis.model.EndeksTanim;
import com.hakedis.util.JDBCHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EndeksTanimDao {


    public Long findIdByKod(String kod) {
        String sqlSorgu = "SELECT id FROM endeks_tanim WHERE kod = ? LIMIT 1";
        try (Connection connection = JDBCHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlSorgu)) {

            ps.setString(1, kod);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                EndeksTanim et = new EndeksTanim();
                et.setId(rs.getLong("id"));
                return et.getId();
            }


        } catch (Exception e) {
            System.err.println("Ağırlık id bulunurken hata : " + e.getMessage());
        }
        return null;
    }

    public List<EndeksTanim> listAll(){
        List<EndeksTanim> liste = new ArrayList<>();
        String sqlSorgu = "SELECT id, kod, ad FROM endeks_tanim ORDER BY id";
        try (Connection connection = JDBCHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlSorgu)){

            ResultSet rs = ps.executeQuery();
            while (rs.next()){

                EndeksTanim e = new EndeksTanim();

                e.setId(rs.getLong("id"));
                e.setKod(rs.getString("kod"));
                e.setAd(rs.getString("ad"));

                liste.add(e);


            }
        } catch (Exception e) {
            System.err.println("Ağırlık endeksi liste sorgu hatası : " + e.getMessage());
        }
        return liste;
    }

    // Yoksa ekle (import için)
    public long insertIfNotExists(String kod, String ad) throws SQLException {

        Long id = findIdByKod(kod);

        if (id != null) return id;

        String sql = "INSERT INTO endeks_tanim(kod, ad) VALUES(?, ?)";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, kod.trim());
            ps.setString(2, ad);

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getLong(1);
            }
        }

        throw new SQLException("Endeks eklenemedi: " + kod);
    }

}

