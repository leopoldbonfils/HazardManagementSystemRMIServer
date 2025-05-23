/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import modal.Reporter;


/**
 *
 * @author HP
 */
public interface ReporterService extends Remote{
    int registerReporter(Reporter reporterObj) throws RemoteException;
    int updateReporter(Reporter reporterObj) throws RemoteException;
    Reporter search(int reporterId) throws RemoteException;
    
}
