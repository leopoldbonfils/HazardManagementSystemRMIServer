/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modal.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public class UserDao {
    public int registerUser(User userObj){
        if (userObj == null || userObj.getEmail() == null || userObj.getEmail().trim().isEmpty()) {
            return 0; 
        }
        try{
           Session ss= HibernateUtil.getSessionFactory().openSession();
           Transaction tr=ss.beginTransaction();
            
           ss.save(userObj);
           tr.commit();
           ss.close();
           return 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return 0;
    }
    
    public boolean confirmUser(String token) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("FROM User WHERE confirmationToken = :token");
            query.setParameter("token", token);
            User user = (User) query.uniqueResult();

            if (user != null) {
                if (user.isConfirmed()) {
                    System.out.println("User is already confirmed.");
                    return false;
                }

                user.setConfirmed(true);
                session.update(user);
                tx.commit();
                System.out.println("User confirmed successfully.");
                return true;
            }

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return false;
    }

    
    public boolean loginUser(String username, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("FROM User WHERE userName = :username AND password = :password");
            query.setParameter("username", username);
            query.setParameter("password", password);
            User user = (User) query.uniqueResult();
            return user != null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }
    
    
}
