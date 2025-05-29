/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal;

import java.io.Serializable;

/**
 *
 * @author HP
 */
public class HazardReporterDTO implements Serializable {
    private int hazardId;
    private String hazardType;
    private String severityLevel;
    private String dateReported;
    private String measureDescription;
    private String location;
    private String reporterName;
    private String measureStatus;

    public HazardReporterDTO() {
    }

    public HazardReporterDTO(int hazardId) {
        this.hazardId = hazardId;
    }

    public HazardReporterDTO(int hazardId, String hazardType, String severityLevel, String dateReported, String measureDescription, String location, String reporterName, String measureStatus) {
        this.hazardId = hazardId;
        this.hazardType = hazardType;
        this.severityLevel = severityLevel;
        this.dateReported = dateReported;
        this.measureDescription = measureDescription;
        this.location = location;
        this.reporterName = reporterName;
        this.measureStatus = measureStatus;
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

    public String getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }

    public String getDateReported() {
        return dateReported;
    }

    public void setDateReported(String dateReported) {
        this.dateReported = dateReported;
    }

    public String getMeasureDescription() {
        return measureDescription;
    }

    public void setMeasureDescription(String measureDescription) {
        this.measureDescription = measureDescription;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getMeasureStatus() {
        return measureStatus;
    }

    public void setMeasureStatus(String measureStatus) {
        this.measureStatus = measureStatus;
    }
    
    
    
}
