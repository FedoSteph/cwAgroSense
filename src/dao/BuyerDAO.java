/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Buyer;
import util.DBConnection;
import java.sql.ResultSet;
/**
 *
 * @author Ameesha Fernando
 */
public class BuyerDAO {
    public boolean insertBuyer(Buyer buyer) {


String sql = "INSERT INTO buyer (uid, company_name) VALUES (?, ?)";


try {
Connection con = DBConnection.getConnection();
PreparedStatement ps = con.prepareStatement(sql);


ps.setInt(1, buyer.getUid());
ps.setString(2, buyer.getCompanyName());


return ps.executeUpdate() > 0;


} catch (Exception e) {
e.printStackTrace();
return false;
}

}
   public ResultSet getBuyersWithUser() throws Exception {
    String sql =
        "SELECT u.uid, u.name, b.company_name " +
        "FROM user u JOIN buyer b ON u.uid = b.uid";

    Connection con = DBConnection.getConnection();
    PreparedStatement ps = con.prepareStatement(sql);
    return ps.executeQuery();
} 
}
