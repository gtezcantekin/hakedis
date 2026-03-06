package com.hakedis.dao;

import com.hakedis.model.AgirlikKatsayisi;
import com.hakedis.util.JDBCHelper;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AgirlikKatsayisiDao {

    public void saveOrUpdateAgirlikKatsayi(AgirlikKatsayisi ks) {
        String sql =
                "INSERT INTO agirlikkatsayisi (is_id, endeks_tanim_id, endeks_kodu, " +
                        "agirlik, temeldonem, temelendeks) " +
                        "VALUES (?,?,?,?,?,?) " +
                        "ON DUPLICATE KEY UPDATE " +
                        "agirlik=VALUES(agirlik), " +
                        "temeldonem=VALUES(temeldonem)," +
                        "temelendeks =VALUES(temelendeks)";


        try (Connection connection = JDBCHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {


            ps.setInt(1, ks.getIsId());
            ps.setLong(2, ks.getEndeksTanimId());
            ps.setString(3, ks.getEndeksKodu());
            ps.setBigDecimal(4, ks.getAgirlik());
            ps.setDate(5, Date.valueOf(ks.getTemeldonem()));
            ps.setBigDecimal(6,ks.getTemelendeks());
            ps.executeUpdate();

        } catch (Exception e) {
            System.err.println(" Ağırlık katsayisi kayıt hatası : " + e.getMessage());
        }
    }

    public AgirlikKatsayisi findByIsAndEndeksTanim(int isId, String endeksKodu) {
        String sqlSorgu = "SELECT * FROM agirlikkatsayisi WHERE is_id=? AND endeks_kodu=?";
        try (Connection connection = JDBCHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlSorgu)) {

            ps.setInt(1, isId);
            ps.setString(2,endeksKodu);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                AgirlikKatsayisi k = new AgirlikKatsayisi();

                k.setId(rs.getInt("id"));
                k.setIsId(rs.getInt("is_id"));
                k.setEndeksTanimId(rs.getLong("endeks_tanim_id"));
                k.setEndeksKodu(rs.getString("endeks_kodu"));
                k.setAgirlik(rs.getBigDecimal("agirlik"));
                k.setTemeldonem(rs.getDate("temeldonem").toLocalDate());
                k.setTemelendeks(rs.getBigDecimal("temelendeks"));
                return k;

            }

        } catch (Exception e) {
            System.err.println("Ağırlık katsayısı kod sorgu hatası : " + e.getMessage());
        }
        return null;
    }


    public AgirlikKatsayisi findByIsId(int isId) {
        String sqlSorgu = "SELECT * FROM agirlikkatsayisi WHERE is_id=?";
        try (Connection connection = JDBCHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlSorgu)) {

            ps.setInt(1, isId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                AgirlikKatsayisi k = new AgirlikKatsayisi();

                k.setId(rs.getInt("id"));
                k.setIsId(rs.getInt("is_id"));
                k.setEndeksTanimId(rs.getLong("endeks_tanim_id"));
                k.setEndeksKodu(rs.getString("endeks_kodu"));
                k.setAgirlik(rs.getBigDecimal("agirlik"));
                k.setTemeldonem(rs.getDate("temeldonem").toLocalDate());
                k.setTemelendeks(rs.getBigDecimal("temelendeks"));
                return k;

            }

        } catch (Exception e) {
            System.err.println("Ağırlık katsayısı kod sorgu hatası : " + e.getMessage());
        }
        return null;
    }

    public List<AgirlikKatsayisi> listByIsId(int isId) {
        List<AgirlikKatsayisi> liste = new ArrayList<>();
        String sqlSorgu =
                "SELECT * FROM agirlikkatsayisi WHERE is_id=? ORDER BY id";

        try (Connection connection = JDBCHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlSorgu)) {

            ps.setInt(1, isId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                AgirlikKatsayisi k = new AgirlikKatsayisi();

                k.setId(rs.getInt("id"));
                k.setIsId(rs.getInt("is_id"));
                k.setEndeksTanimId(rs.getLong("endeks_tanim_id"));
                k.setEndeksKodu(rs.getString("endeks_kodu"));
                k.setAgirlik(rs.getBigDecimal("agirlik"));
                k.setTemeldonem(rs.getDate("temeldonem").toLocalDate());
                k.setTemelendeks(rs.getBigDecimal("temelendeks"));
                liste.add(k);

            }


        } catch (Exception e) {
            System.err.println("Pn katsayısı hakediş no sorgu hatası : " + e.getMessage());
        }
        return liste;
    }

    public BigDecimal sumAgirlikByIsId(int isId) {
        String sql = "SELECT COALESCE(SUM(agirlik),0) AS toplam FROM agirlikkatsayisi WHERE is_id=?";
        try (Connection c = JDBCHelper.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, isId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getBigDecimal("toplam");
            }
        } catch (Exception e) {
            System.err.println("Ağırlık toplam sorgu hatası: " + e.getMessage());
        }
        return BigDecimal.ZERO;
    }

    public void deleteByIsId(int isId) {

        String sql = "DELETE FROM agirlikkatsayisi WHERE is_id = ?";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, isId);
            ps.executeUpdate();

        } catch (Exception e) {
            System.err.println("Ağırlık silme hatası: " + e.getMessage());
        }
    }


}
