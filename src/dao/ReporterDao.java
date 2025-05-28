/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modal.Reporter;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public class ReporterDao {
    public int registerReporter(Reporter reporterObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = (Transaction) ss.beginTransaction();
            ss.save(reporterObj);
            tr.commit();
            ss.close();
            return 1;
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    
    }
    public int updateReporter(Reporter reporterObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = (Transaction) ss.beginTransaction();
            ss.update(reporterObj);
            tr.commit();
            ss.close();
            return 1;
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    
    }
     
    public Reporter search(int reporterId) {
        Reporter reporter = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            reporter= (Reporter) session.get(Reporter.class, reporterId);  
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reporter;
    }
    
    public int deleteReporter(Reporter reporterObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = (Transaction) ss.beginTransaction();
            ss.delete(reporterObj);
            tr.commit();
            ss.close();
            return 1;
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    
    }  
    
     public List<Reporter> findAllReporters() {
      try{
          Session ss = HibernateUtil.getSessionFactory().openSession();
          List<Reporter>reporter = ss.createQuery("SELECT rep FROM Reporter rep").list();
          ss.close();
          return reporter;
      }catch(Exception ex){
          ex.printStackTrace();
      }
      return null;
    }
}
