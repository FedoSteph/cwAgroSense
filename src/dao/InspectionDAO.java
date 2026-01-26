/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Inspection;
import util.DBConnection;
/**
 *
 * @author Ameesha Fernando
 */
public class InspectionDAO {
    public boolean insertInspection(Inspection ins) {

        String sql = "INSERT INTO inspection " +
                     "(scid, inspection_date, result, remarks) " +
                     "VALUES (?, ?, ?, ?)";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, ins.getScid());
            ps.setDate(2, new java.sql.Date(ins.getInspectionDate().getTime()));
            ps.setString(3, ins.getResult());
            ps.setString(4, ins.getRemarks());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
