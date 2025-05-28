/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.implementation;

import dao.LocationDao;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import modal.Location;
import service.LocationService;

/**
 *
 * @author HP
 */
public class LocationServiceImpl extends UnicastRemoteObject implements LocationService{
     public LocationServiceImpl() throws Exception{
      super();
    }

    @Override
    public int registerLocation(Location locationObj) throws RemoteException {
        try{
            LocationDao locationDao = new LocationDao();
            return locationDao.registerLocation(locationObj);
            
        }catch(Exception ex){
          ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateLocation(Location locationObj) throws RemoteException {
        try{
            LocationDao locationDao = new LocationDao();
            return locationDao.updateLocation(locationObj);
        
        }catch(Exception ex){
          ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public Location search(int locationId) throws RemoteException {
        try{
            LocationDao locationDao = new LocationDao();
            return locationDao.search(locationId);
        
        }catch(Exception ex){
          ex.printStackTrace();
        }
        return null;
        
    }

    @Override
    public int deleteLocation(Location locationObj) throws RemoteException {
        try{
            LocationDao locationDao = new LocationDao();
            return locationDao.deleteLocation(locationObj);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }
    
    
}
