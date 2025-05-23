/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.*;
/**
 *
 * @author HP
 */
@Entity
@Table(name = "measures")
public class Measure implements Serializable{
    
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "measure_id")
    private int measureId;
    
    @Column(name = "measure_type")
    private String measureType;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "responsible_authority")
    private String responsibleAuthority;
    
    @Column(name = "implementation_date")
    @Temporal(TemporalType.DATE)
    private Date implementationDate;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "effectiveness_rating")
    private String effectivenessRating;
    
    @ManyToOne
    @JoinColumn(name = "hazard_id")
    private Hazard hazard;
    
    public Measure() {
    }
    
    public Measure(int measureId) {
        this.measureId = measureId;
    }
    
    public Measure(int measureId, String measureType, String description, String responsibleAuthority, Date implementationDate, String status, String effectivenessRating, Hazard hazard) {
        this.measureId = measureId;
        this.measureType = measureType;
        this.description = description;
        this.responsibleAuthority = responsibleAuthority;
        this.implementationDate = implementationDate;
        this.status = status;
        this.effectivenessRating = effectivenessRating;
        this.hazard = hazard;
    }
    
    public int getMeasureId() {
        return measureId;
    }
    
    public void setMeasureId(int measureId) {
        this.measureId = measureId;
    }
    
    public String getMeasureType() {
        return measureType;
    }
    
    public void setMeasureType(String measureType) {
        this.measureType = measureType;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getResponsibleAuthority() {
        return responsibleAuthority;
    }
    
    public void setResponsibleAuthority(String responsibleAuthority) {
        this.responsibleAuthority = responsibleAuthority;
    }
    
    public Date getImplementationDate() {
        return implementationDate;
    }
    
    public void setImplementationDate(Date implementationDate) {
        this.implementationDate = implementationDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getEffectivenessRating() {
        return effectivenessRating;
    }
    
    public void setEffectivenessRating(String effectivenessRating) {
        this.effectivenessRating = effectivenessRating;
    }
    
    public Hazard getHazard() {
        return hazard;
    }
    
    public void setHazard(Hazard hazard) {
        this.hazard = hazard;
    }
}