/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.implementation;

import dao.HazardDao;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import modal.Hazard;
import modal.HazardReporterDTO;
import service.HazardService;

/**
 *
 * @author HP
 */
public class HazardServiceImpl extends UnicastRemoteObject implements HazardService{
    
    public HazardServiceImpl() throws RemoteException{
        super();
    }

    @Override
    public int registerHazard(Hazard hazardObj) throws RemoteException {
        try{
            HazardDao hazardDao = new HazardDao();
           return hazardDao.registerHazard(hazardObj);
            
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateHazard(Hazard hazardObj) throws RemoteException {
        try{
           HazardDao hazardDao = new HazardDao();
           return hazardDao.updateHazard(hazardObj);
            
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
        
    }

    @Override
    public Hazard search(int hazardId) throws RemoteException {
        try{
            HazardDao hazardDao = new HazardDao();
           return hazardDao.search(hazardId);
            
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
        
    }

    @Override
    public int deleteHazard(Hazard hazardObj) throws RemoteException {
        try{
            HazardDao hazardDao = new HazardDao();
            return hazardDao.deleteHazard(hazardObj);
            
        }catch(Exception ex){
            ex.printStackTrace();   
        }
        return 0;
    }

    @Override
    public List<Hazard> findAllHazards() throws RemoteException {
        try{
            HazardDao hazardDao = new HazardDao();
            return hazardDao.findAllHazards();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<HazardReporterDTO> getAllHazardReports() throws RemoteException {
        
        try {
            HazardDao hazardDao = new HazardDao();
            return hazardDao.getAllHazardReports();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
        
    }

    @Override
    public List<HazardReporterDTO> searchHazardReportsByType(String keyword) throws RemoteException {
         try {
            HazardDao hazardDao = new HazardDao();
            return hazardDao.searchHazardReportsByType(keyword);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;   
    }

    @Override
    public long getHazardCount() throws RemoteException {
        try {
            HazardDao hazardDao = new HazardDao();
            return hazardDao.countHazards();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0; 
    }
    
}
