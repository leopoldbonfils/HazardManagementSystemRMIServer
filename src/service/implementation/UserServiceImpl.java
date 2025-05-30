/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.implementation;

import dao.UserDao;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;
import modal.User;
import service.UserService;

/**
 *
 * @author HP
 */
public class UserServiceImpl extends UnicastRemoteObject implements UserService{
    
    public UserServiceImpl() throws Exception{
      super();
    }

    @Override
    public int registerUser(User userObj) throws RemoteException {
        try{
            
            String token = UUID.randomUUID().toString();
            userObj.setConfirmationToken(token);
            userObj.setConfirmed(false);
        
            UserDao userDao = new UserDao();
            int result = userDao.registerUser(userObj);

            if (result == 1) {
                EmailSender.sendConfirmationEmail(userObj.getEmail(), token);
            }

            return result;
           
        }catch(Exception ex){
          ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean loginUser(String username, String password) throws RemoteException {
        try{
            UserDao userDao = new UserDao();
            return userDao.loginUser(username, password);
           
        }catch(Exception ex){
          ex.printStackTrace();
        }
        return false;
        
    }

    @Override
    public boolean confirmUser(String token) throws RemoteException {
       try {
            UserDao userDao = new UserDao();
            return userDao.confirmUser(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
