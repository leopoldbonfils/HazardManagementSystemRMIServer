/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.implementation;


import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author HP
 */
public class EmailSender {
    
    
    public static void sendConfirmationEmail(String toEmail, String confirmationToken) {
        final String fromEmail = "leopoldbonfils@gmail.com";
        final String password = "ozwovevikldaogf";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Account Confirmation");

            String confirmLink = "http://localhost:8080/confirm?token=" + confirmationToken;
            String content = "Click the following link to confirm your account:\n" + confirmLink;

            message.setText(content);

            Transport.send(message);

            System.out.println("Confirmation email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    
}
