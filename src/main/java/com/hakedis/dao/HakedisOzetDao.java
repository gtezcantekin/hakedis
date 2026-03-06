package com.hakedis.dao;

import com.hakedis.model.HakedisOzet;
import com.hakedis.util.JDBCHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HakedisOzetDao {

    public void saveOrUpdate(HakedisOzet ozet) {

        String sql = """
        INSERT INTO hakedisozet
        (is_id, hakedis_id, hakedis_no,
         onceki_toplam, bu_toplam, genel_toplam)

        VALUES (?,?,?,?,?,?)

        ON DUPLICATE KEY UPDATE

            onceki_toplam = VALUES(onceki_toplam),
            bu_toplam     = VALUES(bu_toplam),
            genel_toplam  = VALUES(genel_toplam)
        """;

        try (Connection c = JDBCHelper.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {


            ps.setInt(1, ozet.getIsId());
            ps.setInt(2, ozet.getHakedisId());
            ps.setInt(3, ozet.getHakedisNo());

            ps.setBigDecimal(4, ozet.getOnceki());
            ps.setBigDecimal(5, ozet.getBu());
            ps.setBigDecimal(6, ozet.getToplam());

            ps.executeUpdate();

            System.out.println("Kayıt Başarılı ");

        } catch (Exception e) {
            System.out.println("Hata (Hakedis özeti kaydedilmedi) : " + e.getMessage());
        }
    }


    public HakedisOzet findByIsIdAndHakNo(int isId, int hakNo) {

        String sql = """
                    SELECT is_id, hakedis_id, hakedis_no,
                           onceki_toplam, bu_toplam, genel_toplam
                    FROM hakedisozet
                    WHERE is_id = ?
                      AND hakedis_no = ?
                    LIMIT 1
                """;

        try (Connection c = JDBCHelper.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, isId);
            ps.setInt(2, hakNo);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;

                HakedisOzet o = new HakedisOzet();
                o.setIsId(rs.getInt("is_id"));
                o.setHakedisId(rs.getInt("hakedis_id"));
                o.setHakedisNo(rs.getInt("hakedis_no"));

                o.setOnceki(rs.getBigDecimal("onceki_toplam"));
                o.setBu(rs.getBigDecimal("bu_toplam"));
                o.setToplam(rs.getBigDecimal("genel_toplam"));

                return o;
            }

        } catch (Exception e) {
            System.out.println("HakedisOzet findByIsIdAndHakNo hatası: " + e.getMessage());
        }return null;
    }
}
