package Controller;

import Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Controller {
    static DatabaseHandler conn = new DatabaseHandler();

    // SELECT ALL from table users
    public static ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM DataDiri";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setNim(rs.getString("NIM"));
                user.setNama(rs.getString("Nama"));
                user.setUmur(rs.getInt("Umur"));
                user.setAlamat(rs.getString("Alamat"));
                user.setTelepon(rs.getString("Telepon"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (users);
    }

    // SELECT WHERE
    public static User getUser(String nama, String umur) {
        conn.connect();
        String query = "SELECT * FROM DataDiri WHERE Nama='" + nama + "'&&Umur='" + umur + "'";
        User user = new User();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                user.setNim(rs.getString("NIM"));
                user.setNama(rs.getString("Nama"));
                user.setUmur(rs.getInt("Umur"));
                user.setAlamat(rs.getString("Alamat"));
                user.setTelepon(rs.getString("Telepon"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (user);
    }

    // INSERT
    public static boolean insertNewUser(User user) {
        conn.connect();
        String query = "INSERT INTO DataDiri VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, user.getNim());
            stmt.setString(2, user.getNama());
            stmt.setInt(3, user.getUmur());
            stmt.setString(4, user.getAlamat());
            stmt.setString(5, user.getTelepon());
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    // UPDATE
    public static boolean updateUser(User user) {
        conn.connect();
        String query = "UPDATE DataDiri SET Nama='" + user.getNama() + "', "
                + "Umur='" + user.getUmur() + "', "
                + "Alamat='" + user.getAlamat() + "' "
                + "Telepon='" + user.getTelepon() + "' "
                + " WHERE NIM='" + user.getNim() + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    // DELETE
    public static boolean deleteUser(String nama) {
        conn.connect();

        String query = "DELETE FROM DataDiri WHERE Nama='" + nama + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
}
