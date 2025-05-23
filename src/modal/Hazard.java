/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "hazard")
public class Hazard implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hazard_id")
    private int hazardId;
    @Column(name="hazard_type")
    private String hazardType;
    @Column(name="description")
    private String description;
    @Column(name="date_reported")
    private String date;
    @Column(name="severity_level")
    private String severity;
    @OneToMany(mappedBy = "hazard")
    private List<Measure> measures;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
    @ManyToOne
    @JoinColumn(name = "reporter_id")
    private Reporter reporter;

    public Hazard() {
    }

    public Hazard(int hazardId) {
        this.hazardId = hazardId;
    }

    public Hazard(int hazardId, String hazardType, String description, String date, String severity, List<Measure> measures, Location location, Reporter reporter) {
        this.hazardId = hazardId;
        this.hazardType = hazardType;
        this.description = description;
        this.date = date;
        this.severity = severity;
        this.measures = measures;
        this.location = location;
        this.reporter = reporter;
    }

    public int getHazardId() {
        return hazardId;
    }

    public void setHazardId(int hazardId) {
        this.hazardId = hazardId;
    }

    public String getHazardType() {
        return hazardType;
    }

    public void setHazardType(String hazardType) {
        this.hazardType = hazardType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Reporter getReporter() {
        return reporter;
    }

    public void setReporter(Reporter reporter) {
        this.reporter = reporter;
    }

   
   
}
