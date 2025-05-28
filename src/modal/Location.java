/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author HP
 */@Entity
 @Table(name = "location")
public class Location implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="location_id")
    private int locationId;
    @Column(name="province")
    private String province;
    @Column(name="district")
    private String district;
    @Column(name="sector")
    private String sector;
    @Column(name="cell")
    private String cell;
    @Column(name="village")
    private String village;
    @OneToMany(mappedBy = "location")
    private List<Hazard> hazards;

    public Location() {
    }

    public Location(int locationId) {
        this.locationId = locationId;
    }

    public Location(int locationId, String province, String district, String sector, String cell, String village, List<Hazard> hazards) {
        this.locationId = locationId;
        this.province = province;
        this.district = district;
        this.sector = sector;
        this.cell = cell;
        this.village = village;
        this.hazards = hazards;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public List<Hazard> getHazards() {
        return hazards;
    }

    public void setHazards(List<Hazard> hazards) {
        this.hazards = hazards;
    }
    
}
