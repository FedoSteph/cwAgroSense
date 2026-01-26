/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.SeasonalCrop;
import util.DBConnection;
/**
 *
 * @author Ameesha Fernando
 */
public class SeasonalCropDAO {
    public ArrayList<SeasonalCrop> getAllSeasonalCrops() {

        ArrayList<SeasonalCrop> list = new ArrayList<>();
        String sql = "SELECT scid FROM seasonal_crop";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SeasonalCrop sc = new SeasonalCrop();
                sc.setscid(rs.getInt("scid"));
                list.add(sc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean insertSeasonalCrop(SeasonalCrop sc) {

        String sql = "INSERT INTO seasonal_crop " +
                     "(fid, cid, planting_date, expected_yield, status) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, sc.getfid());
            ps.setInt(2, sc.getcid());
            ps.setDate(3, new java.sql.Date(sc.getPlantingDate().getTime()));
            ps.setInt(4, sc.getExpectedYield());
            ps.setString(5, sc.getStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
