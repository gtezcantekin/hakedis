package com.hakedis.dao;

import com.hakedis.model.BirimFiyatlar;
import com.hakedis.model.Hakedis;
import com.hakedis.model.IsProgrami;
import com.hakedis.util.JDBCHelper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IsProgramiDao {

    public void saveOrUpdate (IsProgrami isProgrami){
        String sqlKayit = "INSERT INTO isprogrami (is_id, donem, ayliktutar,kumulatif) " +
                "VALUES (?,?,?,?) " +
                "ON DUPLICATE KEY UPDATE ayliktutar = VALUES (ayliktutar);";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlKayit)){

            ps.setInt(1,isProgrami.getIsId());
            ps.setDate(2, Date.valueOf(isProgrami.getDonem()));
            ps.setBigDecimal(3,isProgrami.getAylikTutar());
            ps.setBigDecimal(4,isProgrami.getKumulatif());

            ps.executeUpdate();

            System.out.println("Kayıt başarılı");
        } catch (Exception e) {
            System.out.println("Hata (İş programı kayıtında hata) : " + e.getMessage());
        }
    }

    public List<IsProgrami> findByIsId(int isId) {

        List<IsProgrami> liste = new ArrayList<>();

        String sqlSorgu = "SELECT id, is_id, donem, ayliktutar, kumulatif " +
                "FROM isprogrami WHERE is_id = ? ORDER BY donem";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {

            ps.setInt(1, isId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                IsProgrami ip = new IsProgrami();

                // ✅ ID mutlaka dolmalı
                ip.setId(rs.getLong("id"));

                ip.setIsId(rs.getInt("is_id"));

                // ✅ DATE -> LocalDate güvenli dönüşüm
                Date d = rs.getDate("donem");
                if (d != null) {
                    ip.setDonem(d.toLocalDate());
                }

                ip.setAylikTutar(rs.getBigDecimal("ayliktutar"));
                ip.setKumulatif(rs.getBigDecimal("kumulatif"));

                liste.add(ip);
            }

        } catch (Exception e) {
            System.out.println("Hata (iş programı findByIsId) : " + e.getMessage());
            e.printStackTrace();
        }

        return liste;
    }


    public IsProgrami findByDonem(int isId) {
        List<IsProgrami> liste = new ArrayList<>();
        String sqlSorgu = "SELECT * FROM isprogrami WHERE is_id = ?";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {
            ps.setInt(1, isId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                IsProgrami ip = new IsProgrami();
                ip.setId(rs.getLong("id"));
                ip.setId(rs.getInt("is_id"));
                ip.setDonem(rs.getObject("donem", LocalDate.class));
                ip.setAylikTutar(rs.getBigDecimal("aylikTutar"));
                ip.setKumulatif(rs.getBigDecimal("kumulatif"));
                return ip;
            }
        } catch (Exception e) {
            System.out.println("Hata (iş programı 3) : " + e.getMessage());
        }
        return null;
    }
}
