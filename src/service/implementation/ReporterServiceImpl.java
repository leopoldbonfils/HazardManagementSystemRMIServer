/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.implementation;

import dao.ReporterDao;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import modal.Reporter;
import service.ReporterService;

/**
 *
 * @author HP
 */
public class ReporterServiceImpl extends UnicastRemoteObject implements ReporterService{
    public ReporterServiceImpl() throws RemoteException{
      super();
     
    }

    @Override
    public int registerReporter(Reporter reporterObj) throws RemoteException {
        try{
            ReporterDao reporterDao = new ReporterDao();
            return reporterDao.registerReporter(reporterObj);
            
        }catch(Exception ex){
          ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateReporter(Reporter reporterObj) throws RemoteException {
         try{
            ReporterDao reporterDao = new ReporterDao();
            return reporterDao.updateReporter(reporterObj);
            
        }catch(Exception ex){
          ex.printStackTrace();
        }
        return 0;
        
    }

    @Override
    public Reporter search(int reporterId) throws RemoteException {
         try{
            ReporterDao reporterDao = new ReporterDao();
            return reporterDao.search(reporterId);
            
        }catch(Exception ex){
          ex.printStackTrace();
        }
        return null;
    }
    
}
