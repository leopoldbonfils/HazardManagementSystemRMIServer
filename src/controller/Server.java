/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import service.implementation.HazardServiceImpl;
import service.implementation.LocationServiceImpl;
import service.implementation.MeasureServiceImpl;
import service.implementation.ReporterServiceImpl;
import service.implementation.UserServiceImpl;

/**
 *
 * @author HP
 */
public class Server {
    public static void main(String[] args) {
        try{
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            Registry reg = LocateRegistry.createRegistry(5050);
            reg.rebind("hazard", new HazardServiceImpl());
            reg.rebind("user", new UserServiceImpl());
            reg.rebind("location", new LocationServiceImpl());
            reg.rebind("reporter", new ReporterServiceImpl());
            reg.rebind("measure", new MeasureServiceImpl());
            
            System.out.println("Server is running on port 5050");
        
        }catch( Exception ex){
            ex.printStackTrace();
        
        }
    }
}
