/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.Date;
/**
 *
 * @author Ameesha Fernando
 */
public class SeasonalCrop {
    private int scid;
    private int fid;
    private int cid;
    private Date plantingDate;
    private int expectedYield;
    private String status;

    public int getscid() {
        return scid;
    }

    public void setscid(int scid) {
        this.scid = scid;
    }

    public int getfid() {
        return fid;
    }

    public void setfid(int fid) {
        this.fid = fid;
    }

    public int getcid() {
        return cid;
    }

    public void setcid(int cid) {
        this.cid = cid;
    }

    public Date getPlantingDate() {
        return plantingDate;
    }

    public void setPlantingDate(Date plantingDate) {
        this.plantingDate = plantingDate;
    }

    public int getExpectedYield() {
        return expectedYield;
    }

    public void setExpectedYield(int expectedYield) {
        this.expectedYield = expectedYield;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
@Override
public String toString() {
    return "Seasonal Crop ID: " + scid;
}

}
