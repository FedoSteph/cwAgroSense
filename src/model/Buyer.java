/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Ameesha Fernando
 */
public class Buyer extends User {

    private int bid;
private int uid;
private String companyName;


public int getBid() {
return bid;
}


public void setBid(int bid) {
this.bid = bid;
}


public int getUid() {
return uid;
}


public void setUid(int uid) {
this.uid = uid;
}


public String getCompanyName() {
return companyName;
}


public void setCompanyName(String companyName) {
this.companyName = companyName;
}
}
