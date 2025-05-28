/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import modal.Measure;

/**
 *
 * @author HP
 */
public interface MeasureService extends Remote{
    int registerMeasure(Measure measureObj) throws RemoteException;
    int updateMeasure(Measure measureObj) throws RemoteException;
    Measure search(int measureId) throws RemoteException;
    int deleteMeasure(Measure measureObj) throws RemoteException;
     
    
    
}
