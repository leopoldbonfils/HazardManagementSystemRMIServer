/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.implementation;

import dao.UserDao;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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
            UserDao userDao = new UserDao();
            return userDao.registerUser(userObj);
           
        }catch(Exception ex){
          ex.printStackTrace();
        }
        return 0;
    }
    
}
