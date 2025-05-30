/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.implementation;

import dao.MeasureDao;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import modal.Measure;
import service.MeasureService;

/**
 *
 * @author HP
 */
public class MeasureServiceImpl extends UnicastRemoteObject implements MeasureService{
    
    public MeasureServiceImpl() throws RemoteException {
        super();
    }
    
    @Override
    public int registerMeasure(Measure measureObj) throws RemoteException {
        try {
            
            if (measureObj == null) {
                System.err.println("ERROR: Measure object is null");
                return 0;
            }
            
           
            if (measureObj.getHazard() == null) {
                System.err.println("ERROR: No hazard associated with measure");
                return 0;
            }
            
            int hazardId = measureObj.getHazard().getHazardId();
            if (hazardId <= 0) {
                System.err.println("ERROR: Invalid hazard ID: " + hazardId);
                return 0;
            }
            
           
            MeasureDao measureDao = new MeasureDao();
            if (!measureDao.hazardExists(hazardId)) {
                System.err.println("ERROR: Hazard with ID " + hazardId + " does not exist");
                System.out.println("Available hazards:");
                measureDao.printAllHazards();
                return 0;
            }
            
          
            if (measureObj.getMeasureType() == null || measureObj.getMeasureType().trim().isEmpty()) {
                System.err.println("ERROR: Measure type is required");
                return 0;
            }
            
            if (measureObj.getDescription() == null || measureObj.getDescription().trim().isEmpty()) {
                System.err.println("ERROR: Measure description is required");
                return 0;
            }
            
            System.out.println("Registering measure for hazard ID: " + hazardId);
            return measureDao.registerMeasure(measureObj);
            
        } catch (Exception ex) {
            System.err.println("Error in MeasureServiceImpl.registerMeasure: " + ex.getMessage());
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateMeasure(Measure measureObj) throws RemoteException {
        try{
            MeasureDao measureDao = new MeasureDao();
            return measureDao.updateMeasure(measureObj);
            
        }catch(Exception ex){
          ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public Measure search(int measureId) throws RemoteException {
        try{
            MeasureDao measureDao = new MeasureDao();
            return measureDao.search(measureId);
            
        }catch(Exception ex){
          ex.printStackTrace();
        }
        return null;
    }

    @Override
    public int deleteMeasure(Measure measureObj) throws RemoteException {
        try{
            MeasureDao measureDao = new MeasureDao();
            return measureDao.deleteMeasure(measureObj);
        }catch(Exception ex){
            ex.printStackTrace();
        
        }
        return 0;
    }

    @Override
    public List<Measure> findAllMeasures() throws RemoteException {
        try{
            MeasureDao measureDao = new MeasureDao();
            return measureDao.findAllMeasures();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public long countMeasures() throws RemoteException {
         try{
            MeasureDao measureDao = new MeasureDao();
            return measureDao.countMeasures();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
       
    }
    
}
