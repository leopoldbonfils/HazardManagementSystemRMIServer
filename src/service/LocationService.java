/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import modal.Location;

/**
 *
 * @author HP
 */
public interface LocationService extends Remote{
    int registerLocation(Location locationObj) throws RemoteException;
    int updateLocation(Location locationObj) throws RemoteException;
    Location search(int locationId) throws RemoteException;
    int deleteLocation(Location locationObj) throws RemoteException;
    List<Location> findAllLocations() throws RemoteException;
    
}
