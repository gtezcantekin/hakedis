package com.hakedis.dao;

import com.hakedis.model.SureUzatimi;
import com.hakedis.util.JDBCHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SureUzatimiDao {

    public int insertSureUzatimi(SureUzatimi su){

        String sqlKayit = "INSERT INTO sureuzatimi" +
                " (is_id, hakedis_id, hakedis_no, aciklama, gunuzatma) VALUES (?,?,?,?,?)";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlKayit)){
            ps.setInt(1,su.getIs_id());
            ps.setInt(2,su.getHakedis_id());
            ps.setInt(3,su.getHakedis_no());
            ps.setString(4,su.getAciklama());
            ps.setInt(5,su.getGunuzatma());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Hata (süre uzatımı): " + e.getMessage());
        } return 0;
    }

    public SureUzatimi findByisIdAndHakId (int isId, int hakNo) {
        String sqlSorgu = "SELECT id, is_id, hakedis_id, hakedis_ no, aciklama, gunuzatma " +
                "FROM sureuzatimi WHERE is_id = ? AND hakedis_no = ?";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {
            ps.setInt(1, isId);
            ps.setInt(2,hakNo);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                SureUzatimi su = new SureUzatimi();
                su.setId(rs.getInt("id"));
                su.setIs_id(rs.getInt("is_id"));
                su.setHakedis_id(rs.getInt("hakedis_id"));
                su.setHakedis_no(rs.getInt("hakedis_no"));
                su.setAciklama(rs.getString("aciklama"));
                su.setGunuzatma(rs.getInt("gunuzatma"));
                return su;

            }
        } catch (Exception e) {
            System.out.println("Hata : " + e.getMessage());
        }
        return null;
    }
    public List<SureUzatimi> listByIsId(int isId){
        List<SureUzatimi> liste = new ArrayList<>();
        String sqlSorgu = "SELECT id, is_id, hakedis_id, hakedis_no, aciklama, gunuzatma " +
                "FROM sureuzatimi WHERE is_id = ?";

        try (Connection conn = JDBCHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {
            ps.setInt(1,isId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                SureUzatimi su = new SureUzatimi();
                su.setId(rs.getInt("id"));
                su.setIs_id(rs.getInt("is_id"));
                su.setHakedis_id(rs.getInt("hakedis_id"));
                su.setHakedis_no(rs.getInt("hakedis_no"));
                su.setAciklama(rs.getString("aciklama"));
                su.setGunuzatma(rs.getInt("gunuzatma"));
                liste.add(su);
            }
        } catch (Exception e) {
            System.out.println("Hata : " + e.getMessage());
        }
        return liste;
    }

    public List<SureUzatimi> listByIsIdAndHakedisNo(int isId, int hakedisNo){
        List<SureUzatimi> liste = new ArrayList<>();
        String sqlSorgu = "SELECT id, is_id, hakedis_id, hakedis_no, aciklama, gunuzatma " +
                "FROM sureuzatimi WHERE is_id = ? AND hakedis_no = ? ORDER BY id";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {
            ps.setInt(1,isId);
            ps.setInt(2,hakedisNo);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                SureUzatimi su = new SureUzatimi();
                su.setId(rs.getInt("id"));
                su.setIs_id(rs.getInt("is_id"));
                su.setHakedis_id(rs.getInt("hakedis_id"));
                su.setHakedis_no(rs.getInt("hakedis_no"));
                su.setAciklama(rs.getString("aciklama"));
                su.setGunuzatma(rs.getInt("gunuzatma"));
                liste.add(su);
            }
        } catch (Exception e) {
            System.out.println("Hata (Sure uzatımı 1): " + e.getMessage());
        }
        return liste;
    }


}
