/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author HP
 */
public interface EmailService {
    boolean sendOTP(String recipientEmail, String otp);
    
}
