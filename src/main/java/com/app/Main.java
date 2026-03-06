package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/hakedisapp1?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "Adt818216";

        String sql = "SELECT 1";

        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                System.out.println("Baglanti OK. SELECT sonucu = " + rs.getInt(1));
            }

        } catch (Exception e) {
            System.out.println("Baglanti HATA: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

