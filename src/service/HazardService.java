/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import modal.Hazard;

/**
 *
 * @author HP
 */
public interface HazardService extends Remote{
    
    int registerHazard(Hazard hazardObj)throws RemoteException;
    int updateHazard(Hazard hazardObj)throws RemoteException;
    Hazard search(int hazardId)throws RemoteException;
    
    
}
