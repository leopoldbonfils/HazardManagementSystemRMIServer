/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import modal.Hazard;
import org.hibernate.Session;
import org.hibernate.Transaction;
import modal.HazardReporterDTO;
import org.hibernate.SQLQuery;
import java.text.SimpleDateFormat;

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
     
    public int deleteHazard(Hazard hazardObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = (Transaction) ss.beginTransaction();
            ss.delete(hazardObj);
            tr.commit();
            ss.close();
            return 1;
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }
    
    
    public List<Hazard> findAllHazards() {
      try{
          Session ss = HibernateUtil.getSessionFactory().openSession();
          List<Hazard>hazard = ss.createQuery("SELECT haz FROM Hazard haz").list();
          ss.close();
          return hazard;
      }catch(Exception ex){
          ex.printStackTrace();
      }
      return null;
    }
     
     public List<HazardReporterDTO> getAllHazardReports() {
        List<HazardReporterDTO> list = new ArrayList<>();
    Session ss = null;
    Transaction tx = null;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Define the date format you want

    try {
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();

        String sql = "SELECT h.hazard_id, h.hazard_type, h.severity_level, h.date_reported, " +
                     "m.description AS measure_description, " +
                     "CONCAT(l.district, '/', l.province) AS location, " +
                     "r.full_name AS reporter_name, " +
                     "m.status AS measure_status " +
                     "FROM hazard h " +
                     "LEFT JOIN measures m ON h.hazard_id = m.hazard_id " +
                     "JOIN location l ON h.location_id = l.location_id " +
                     "JOIN reporter r ON h.reporter_id = r.reporter_id";

        SQLQuery query = ss.createSQLQuery(sql);
        List<Object[]> results = query.list();

        for (Object[] row : results) {
            // Cast date_reported correctly
            java.sql.Date dateReported = (java.sql.Date) row[3];
            String dateReportedStr = (dateReported != null) ? sdf.format(dateReported) : "N/A";

            HazardReporterDTO dto = new HazardReporterDTO(
                    ((Number) row[0]).intValue(),
                    (String) row[1],
                    (String) row[2],
                    dateReportedStr,
                    row[4] != null ? (String) row[4] : "N/A",
                    (String) row[5],
                    (String) row[6],
                    row[7] != null ? (String) row[7] : "N/A"
            );
            list.add(dto);
        }

        tx.commit();
    } catch (Exception ex) {
        if (tx != null) tx.rollback();
        ex.printStackTrace();
    } finally {
        if (ss != null) ss.close();
    }

    return list;
     }
     
    public List<HazardReporterDTO> searchHazardReportsByType(String keyword) {
        List<HazardReporterDTO> list = new ArrayList<>();
        Session ss = null;
        Transaction tx = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            ss = HibernateUtil.getSessionFactory().openSession();
            tx = ss.beginTransaction();

            if (keyword == null) keyword = "";
            keyword = keyword.trim().toLowerCase();

            String sql = "SELECT h.hazard_id, h.hazard_type, h.severity_level, h.date_reported, " +
                         "m.description AS measure_description, " +
                         "CONCAT(l.district, '/', l.province) AS location, " +
                         "r.full_name AS reporter_name, " +
                         "m.status AS measure_status " +
                         "FROM hazard h " +
                         "LEFT JOIN measures m ON h.hazard_id = m.hazard_id " +
                         "JOIN location l ON h.location_id = l.location_id " +
                         "JOIN reporter r ON h.reporter_id = r.reporter_id " +
                         "WHERE LOWER(h.hazard_type) LIKE :keyword";

            SQLQuery query = ss.createSQLQuery(sql);
            query.setParameter("keyword", "%" + keyword + "%");

            List<Object[]> results = query.list();

            for (Object[] row : results) {
                java.sql.Date dateReported = (java.sql.Date) row[3];
                String dateReportedStr = (dateReported != null) ? sdf.format(dateReported) : "N/A";

                HazardReporterDTO dto = new HazardReporterDTO(
                    ((Number) row[0]).intValue(),     // hazard_id
                    (String) row[1],                  // hazard_type
                    (String) row[2],                  // severity_level
                    dateReportedStr,                  // date_reported
                    row[4] != null ? (String) row[4] : "N/A", // measure_description
                    (String) row[5],                  // location
                    (String) row[6],                  // reporter_name
                    row[7] != null ? (String) row[7] : "N/A"  // measure_status
                );
                list.add(dto);
            }

            tx.commit();
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
        } finally {
            if (ss != null) ss.close();
        }

        return list;
    }
    
     public long countHazards() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        long count = 0;
        try {
            tx = session.beginTransaction();
            count = (Long) session.createQuery("select count(h.id) from Hazard h").uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return count;
    }
}
