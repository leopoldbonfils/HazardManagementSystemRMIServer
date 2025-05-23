/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import modal.Hazard;
import modal.User;

/**
 *
 * @author HP
 */
public interface UserService extends Remote{
    int registerUser(User userObj) throws RemoteException;
    
}
