package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Farmer;
import util.DBConnection;

public class FarmerDAO  {

    /// Get all farmers (SELECT)
public ArrayList<Farmer> getAllFarmers() {


ArrayList<Farmer> list = new ArrayList<>();


String sql = "SELECT fid, uid, contact_no, location FROM farmer";


try {
Connection con = DBConnection.getConnection();
PreparedStatement ps = con.prepareStatement(sql);
ResultSet rs = ps.executeQuery();


while (rs.next()) {
Farmer f = new Farmer();
f.setFid(rs.getInt("fid"));
f.setuid(rs.getInt("uid"));
f.setContactNo(rs.getString("contact_no"));
f.setLocation(rs.getString("location"));
list.add(f);
}


} catch (Exception e) {
e.printStackTrace();
}


return list;
}


// Insert new farmer (INSERT)
public boolean insertFarmer(Farmer farmer) {


String sql = "INSERT INTO farmer (uid, contact_no, location) VALUES (?, ?, ?)";


try {
Connection con = DBConnection.getConnection();
PreparedStatement ps = con.prepareStatement(sql);


ps.setInt(1, farmer.getuid()); 
ps.setString(2, farmer.getContactNo());
ps.setString(3, farmer.getLocation());


return ps.executeUpdate() > 0;


} catch (Exception e) {
e.printStackTrace();
return false;
}
}


//  Get farmer by ID
public Farmer getFarmerById(int fid) {


String sql = "SELECT fid, uid, contact_no, location FROM farmer WHERE fid = ?";
Farmer f = null;


try {
Connection con = DBConnection.getConnection();
PreparedStatement ps = con.prepareStatement(sql);
ps.setInt(1, fid);
ResultSet rs = ps.executeQuery();


if (rs.next()) {
f = new Farmer();
f.setFid(rs.getInt("fid"));
f.setuid(rs.getInt("uid"));
f.setContactNo(rs.getString("contact_no"));
f.setLocation(rs.getString("location"));
}


} catch (Exception e) {
e.printStackTrace();
}


return f;
}

public ResultSet getFarmersWithUser() throws Exception {
    String sql =
        "SELECT u.uid, u.name, f.contact_no, f.location " +
        "FROM user u JOIN farmer f ON u.uid = f.uid";

    Connection con = DBConnection.getConnection();
    PreparedStatement ps = con.prepareStatement(sql);
    return ps.executeQuery();
}
}

