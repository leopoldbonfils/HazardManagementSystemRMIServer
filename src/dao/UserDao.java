/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modal.User;
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
    
    
    
}
