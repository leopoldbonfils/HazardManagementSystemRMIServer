/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modal.Location;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public class LocationDao {
    public int registerLocation(Location locationObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = (Transaction) ss.beginTransaction();
            ss.save(locationObj);
            tr.commit();
            ss.close();
            return 1;
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    
    }
    public int updateLocation(Location locationObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = (Transaction) ss.beginTransaction();
            ss.update(locationObj);
            tr.commit();
            ss.close();
            return 1;
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    
    }
     
    public Location search(int locationId) {
        Location location = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            location = (Location) session.get(Location.class, locationId);  
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }
     
    public int deleteLocation(Location locationObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = (Transaction) ss.beginTransaction();
            ss.delete(locationObj);
            tr.commit();
            ss.close();
            return 1;
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    
    }  
    
    public List<Location> findAllLocations() {
      try{
          Session ss = HibernateUtil.getSessionFactory().openSession();
          List<Location>location = ss.createQuery("SELECT loc FROM Location loc").list();
          ss.close();
          return location;
      }catch(Exception ex){
          ex.printStackTrace();
      }
      return null;
    }
}
