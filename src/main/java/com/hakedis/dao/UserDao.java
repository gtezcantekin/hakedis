package com.hakedis.dao;

import com.hakedis.model.Users;
import com.hakedis.util.JDBCHelper;
import com.mysql.cj.jdbc.JdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public Users findByUserName(String username) {
        String sql = "SELECT id, username, password FROM users WHERE username = ?";

        try (Connection con = JDBCHelper.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Users u = new Users();
                    u.setId(rs.getInt("id"));
                    u.setUserName((rs.getString("username")));
                    u.setPassWord(rs.getString("password"));
                    return u;
                }

            }

        } catch (Exception e) {
            System.out.println("Hata (login hatası) : " + e.getMessage());
        }
        return null;
    }

    public void insertUsers(Users user) {
        String sqlKayit = "INSERT INTO users (username,password) VALUES (?, ?)";

        try (Connection connection = JDBCHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlKayit)) {

            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassWord());

            ps.executeUpdate();
            System.out.println("Kayıt başarılı");

        } catch (Exception e) {
            System.out.println("Hata (Kayıt başarısız) : " + e.getMessage());
        }

    }

    public List<Users> listeleTumUsers() {
        List<Users> liste = new ArrayList<>();
        String sqlSorgu = "SELECT id, username, password FROM users";

        try (Connection conn = JDBCHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSorgu)) {

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Users u = new Users();
                    u.setId(rs.getInt("id"));
                    u.setUserName(rs.getString("username"));
                    u.setPassWord(rs.getString("password"));

                    liste.add(u);
                }

            }
        } catch (Exception e) {
            System.out.println("Hata (Listelenirken hata oldu) : " + e.getMessage());
        } return liste;
    }
}
