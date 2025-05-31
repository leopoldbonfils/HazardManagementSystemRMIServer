package service.implementation;

import dao.UserDao;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import modal.User;
import service.EmailService;
import service.UserService;
import util.OTPGenerator;

public class UserServiceImpl extends UnicastRemoteObject implements UserService {

    public UserServiceImpl() throws Exception {
        super();
    }
    private EmailService emailService = new EmailServiceImpl();

    @Override
    public int registerUser(User userObj) throws RemoteException {
        try {
            
            String otp = OTPGenerator.generateOTP(6);
            userObj.setConfirmationToken(otp);
            userObj.setConfirmed(false);

            UserDao userDao = new UserDao();
            int result = userDao.registerUser(userObj);

           if (result > 0) {
               
                emailService.sendOTP(userObj.getEmail(), userObj.getConfirmationToken());
            }

            return result;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean loginUser(String username, String password) throws RemoteException {
        try {
            UserDao userDao = new UserDao();
            return userDao.loginUser(username, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean confirmUser(String token) throws RemoteException {
        try {
            UserDao userDao = new UserDao();
            return userDao.confirmUser(token); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    
    private String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); 
        return String.valueOf(otp);
    }
}
