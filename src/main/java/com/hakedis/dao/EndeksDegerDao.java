package com.hakedis.dao;

import com.hakedis.util.JDBCHelper;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EndeksDegerDao {

    public BigDecimal findDeger(Long endeksTanimId, int yil, int ay) {

        String sql = """
            SELECT deger
            FROM endeks_deger
            WHERE endeks_tanim_id = ?
              AND yil = ?
              AND ay = ?
            LIMIT 1
        """;

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, endeksTanimId);
            ps.setInt(2, yil);
            ps.setInt(3, ay);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getBigDecimal("deger");
            }

        } catch (Exception e) {
            System.err.println("Endeks değeri bulunamadı: " + e.getMessage());
        }

        return null;
    }

}
