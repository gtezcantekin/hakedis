package com.hakedis.dao;

import com.hakedis.model.Icmal;
import com.hakedis.util.JDBCHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IcmalDao {

    public void saveOrUpdateIcmal(Icmal icmal) {
        String sqlKayit = """ 
                INSERT INTO icmal (is_id, hakedis_id, hakedisNo, birimfiyatlar_id, satir_tip,
                bfNo, bfRevNo, bfTanim, bfTutar, metrajturu_id, topHakMiktar, oncekiHakMiktar, buHakMiktar,
                topHakTutar, oncekiHakTutar, buHakTutar)
                VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
                
                ON DUPLICATE KEY UPDATE
                hakedisNo        = VALUES(hakedisNo),
                bfNo             = VALUES(bfNo),
                bfRevNo          = VALUES(bfRevNo),
                bfTanim          = VALUES(bfTanim),
                bfTutar          = VALUES(bfTutar),
                metrajturu_id    = VALUES(metrajturu_id),
                topHakMiktar     = VALUES(topHakMiktar),
                oncekiHakMiktar  = VALUES(oncekiHakMiktar),
                buHakMiktar      = VALUES(buHakMiktar),
                topHakTutar      = VALUES(topHakTutar),
                oncekiHakTutar   = VALUES(oncekiHakTutar),
                buHakTutar       = VALUES(buHakTutar)
                """;


        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlKayit)) {


            ps.setInt(1, icmal.getIsId());
            ps.setInt(2, icmal.getHakId());
            ps.setInt(3, icmal.getHakNo());
            ps.setInt(4, icmal.getBfId());
            ps.setInt(5, icmal.getSatirTip()); // 0 normal, 1 rev

            ps.setString(6, icmal.getBfNo());
            ps.setString(7, icmal.getBfRevNo());   // normalde null olabilir
            ps.setString(8, icmal.getBfTanim());
            ps.setBigDecimal(9, icmal.getBfTutar());
            ps.setInt(10, icmal.getMetrajTuruId());

            ps.setBigDecimal(11, icmal.getTopHakMiktar());
            ps.setBigDecimal(12, icmal.getOncekiHakMiktar());
            ps.setBigDecimal(13, icmal.getBuHakMiktar());

            ps.setBigDecimal(14, icmal.getTopHakTutar());
            ps.setBigDecimal(15, icmal.getOncekiHakTutar());
            ps.setBigDecimal(16, icmal.getBuHakTutar());


            ps.executeUpdate();

            System.out.println("Kayıt başarılı");
        } catch (Exception e) {
            System.out.println("Hata (icmal kayıt hata) : " + e.getMessage());
        }
    }

    public List<Icmal> listeleByIsId(int isId) {
        List<Icmal> liste = new ArrayList<>();
        String sqlSorgu = "SELECT * FROM icmal WHERE is_id = ?";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {

            ps.setInt(1,isId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Icmal i = new Icmal();
                i.setId(rs.getInt("id"));
                i.setIsId(rs.getInt("is_id"));
                i.setHakId(rs.getInt("hakedis_id"));
                i.setHakNo(rs.getInt("hakedisNo"));
                i.setBfId(rs.getInt("birimfiyatlar_id"));
                i.setSatirTip(rs.getInt("satir_tip"));
                i.setBfNo(rs.getString("bfNo"));
                i.setBfRevNo(rs.getString("bfRevNo"));
                i.setBfTanim(rs.getString("bfTanim"));
                i.setBfTutar(rs.getBigDecimal("bfTutar"));
                i.setMetrajTuruId(rs.getInt("metrajturu_id"));
                i.setTopHakMiktar(rs.getBigDecimal("topHakMiktar"));
                i.setOncekiHakMiktar(rs.getBigDecimal("oncekiHakMiktar"));
                i.setBuHakMiktar(rs.getBigDecimal("buHakMiktar"));
                i.setTopHakTutar(rs.getBigDecimal("topHakTutar"));
                i.setOncekiHakTutar(rs.getBigDecimal("oncekiHakTutar"));
                i.setBuHakTutar(rs.getBigDecimal("buHakTutar"));
                liste.add(i);
            }

        } catch (Exception e) {
            System.out.println("Hata (icmal sorgu) : " + e.getMessage());
        }


        return liste;
    }

    public List<Icmal> listeleByIsIdAndHakNo(int isId, int hakNo) {
        List<Icmal> liste = new ArrayList<>();
        String sqlSorgu = "SELECT * FROM icmal WHERE is_id = ? AND hakedisNo = ?";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {

            ps.setInt(1,isId);
            ps.setInt(2,hakNo);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Icmal i = new Icmal();
                i.setId(rs.getInt("id"));
                i.setIsId(rs.getInt("is_id"));
                i.setHakId(rs.getInt("hakedis_id"));
                i.setHakNo(rs.getInt("hakedisNo"));
                i.setBfId(rs.getInt("birimfiyatlar_id"));
                i.setSatirTip(rs.getInt("satir_tip"));
                i.setBfNo(rs.getString("bfNo"));
                i.setBfRevNo(rs.getString("bfRevNo"));
                i.setBfTanim(rs.getString("bfTanim"));
                i.setBfTutar(rs.getBigDecimal("bfTutar"));
                i.setMetrajTuruId(rs.getInt("metrajturu_id"));
                i.setTopHakMiktar(rs.getBigDecimal("topHakMiktar"));
                i.setOncekiHakMiktar(rs.getBigDecimal("oncekiHakMiktar"));
                i.setBuHakMiktar(rs.getBigDecimal("buHakMiktar"));
                i.setTopHakTutar(rs.getBigDecimal("topHakTutar"));
                i.setOncekiHakTutar(rs.getBigDecimal("oncekiHakTutar"));
                i.setBuHakTutar(rs.getBigDecimal("buHakTutar"));
                liste.add(i);
            }

        } catch (Exception e) {
            System.out.println("Hata (icmal sorgu) : " + e.getMessage());
        }


        return liste;
    }

}
