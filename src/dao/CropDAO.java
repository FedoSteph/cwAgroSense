/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;
import java.util.*;
import model.Crop;
import util.DBConnection;
/**
 *
 * @author Ameesha Fernando
 */
public class CropDAO {
    public List<Crop> getAllCrops() {

        List<Crop> list = new ArrayList<>();
        String sql = "SELECT cid, cname, season FROM crop";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Crop c = new Crop();
                c.setCid(rs.getInt("cid"));
                c.setCname(rs.getString("cname"));
                c.setSeason(rs.getString("season"));
                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
