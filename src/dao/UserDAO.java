/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;
import util.DBConnection;
/**
 *
 * @author Ameesha Fernando
 */
public class UserDAO {
     public User login(String email, String password) {

        User user = null;
        String sql = "SELECT * FROM user WHERE email=? AND password=?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setuid(rs.getInt("uid"));
                user.setUsername(rs.getString("name"));
                user.setRole(rs.getString("role"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
    
     
     public boolean insertUser(User user) {

    String sql = "INSERT INTO user (uid, name, email, password, role) VALUES (?, ?, ?, ?, ?)";

    try {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, user.getuid());   
        ps.setString(2, user.getUsername());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getPassword());
        ps.setString(5, user.getRole());

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
     
     
     
     public boolean uidExists(int uid) {
    String sql = "SELECT uid FROM user WHERE uid=?";
    try {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, uid);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
     
   public ResultSet getAllUsers() throws Exception {
    String sql = "SELECT uid, name, email, role FROM user";
    Connection con = DBConnection.getConnection();
    PreparedStatement ps = con.prepareStatement(sql);
    return ps.executeQuery();
}
   
   public boolean updateUser(User user) {

    String sql = "UPDATE user SET name=?, email=?, role=? WHERE uid=?";

    try {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, user.getUsername());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getRole());
        ps.setInt(4, user.getuid());

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
   
   public boolean deleteUser(int uid) {

    String sql = "DELETE FROM user WHERE uid=?";

    try {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, uid);

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
}


