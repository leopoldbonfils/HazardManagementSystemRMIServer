/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import modal.Hazard;
import modal.HazardReporterDTO;

/**
 *
 * @author HP
 */
public interface HazardService extends Remote{
    
    int registerHazard(Hazard hazardObj)throws RemoteException;
    int updateHazard(Hazard hazardObj)throws RemoteException;
    Hazard search(int hazardId)throws RemoteException;
    int deleteHazard(Hazard hazardObj) throws RemoteException;
    List<Hazard> findAllHazards() throws RemoteException;
    List<HazardReporterDTO> getAllHazardReports() throws RemoteException;
    List<HazardReporterDTO> searchHazardReportsByType(String keyword) throws RemoteException;
    long getHazardCount() throws RemoteException;
    
    
}
