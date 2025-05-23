/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.Query;
import modal.Hazard;
import modal.Measure;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public class MeasureDao {
     public int registerMeasure(Measure measureObj) {
        Session ss = null;
        Transaction tr = null;
        
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            tr = ss.beginTransaction();
            
            // Debug: Print the hazard information
            if (measureObj.getHazard() != null) {
                System.out.println("Attempting to save measure with hazard_id: " + measureObj.getHazard().getHazardId());
                
                // Verify the hazard exists in the database
                Hazard existingHazard = (Hazard) ss.get(Hazard.class, measureObj.getHazard().getHazardId());
                if (existingHazard == null) {
                    System.err.println("ERROR: Hazard with ID " + measureObj.getHazard().getHazardId() + " does not exist in database!");
                    tr.rollback();
                    return 0;
                }
                
                // Use the managed hazard object from the current session
                measureObj.setHazard(existingHazard);
                System.out.println("Hazard verified and set: " + existingHazard.getHazardId());
            } else {
                System.err.println("ERROR: No hazard provided for the measure!");
                tr.rollback();
                return 0;
            }
            
            // Save the measure
            ss.save(measureObj);
            tr.commit();
            System.out.println("Measure saved successfully with ID: " + measureObj.getMeasureId());
            return 1;
            
        } catch (Exception ex) {
            System.err.println("Error in registerMeasure: " + ex.getMessage());
            ex.printStackTrace();
            
            if (tr != null) {
                try {
                    tr.rollback();
                } catch (Exception rollbackEx) {
                    System.err.println("Error during rollback: " + rollbackEx.getMessage());
                }
            }
            return 0;
        } finally {
            if (ss != null && ss.isOpen()) {
                ss.close();
            }
        }
    }
    
    // Helper method to verify hazard exists
    public boolean hazardExists(int hazardId) {
        Session ss = null;
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            Hazard hazard = (Hazard) ss.get(Hazard.class, hazardId);
            return hazard != null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (ss != null && ss.isOpen()) {
                ss.close();
            }
        }
    }
    
    // Method to get all hazards for debugging
    public void printAllHazards() {
        Session ss = null;
        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Query query = ss.createQuery("FROM Hazard");
            List<Hazard> hazards = query.list();
            
            System.out.println("=== Available Hazards in Database ===");
            for (Hazard h : hazards) {
                System.out.println("ID: " + h.getHazardId() + ", Type: " + h.getHazardType() + 
                                 ", Description: " + h.getDescription());
            }
            System.out.println("=====================================");
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (ss != null && ss.isOpen()) {
                ss.close();
            }
        }
    }
    
    public int updateMeasure(Measure measureObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = (Transaction) ss.beginTransaction();
            ss.update(measureObj);
            tr.commit();
            ss.close();
            return 1;
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }
    
     public Measure search(int measureId) {
        Measure measure = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            measure= (Measure) session.get(Measure.class, measureId);  
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return measure;
    }
}
