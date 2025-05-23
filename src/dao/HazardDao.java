/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modal.Hazard;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public class HazardDao {
    public int registerHazard(Hazard hazardObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = (Transaction) ss.beginTransaction();
            ss.save(hazardObj);
            tr.commit();
            ss.close();
            return 1;
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    
    }
    
     public int updateHazard(Hazard hazardObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = (Transaction) ss.beginTransaction();
            ss.update(hazardObj);
            tr.commit();
            ss.close();
            return 1;
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    
    }
     
     public Hazard search(int hazardId) {
        Hazard hazard = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            hazard = (Hazard) session.get(Hazard.class, hazardId);  
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hazard;
    }
    
}
