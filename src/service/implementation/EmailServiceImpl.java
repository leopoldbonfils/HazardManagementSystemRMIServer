package service.implementation;

import service.EmailService;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

/**
 * EmailService implementation with SSL certificate handling
 * @author HP
 */
public class EmailServiceImpl implements EmailService {
    
    private final String senderEmail = "leopordbonfils@gmail.com";
    private final String senderPassword = "zepsrgkoqvhdvbus"; // App Password from Gmail
    
    @Override
    public boolean sendOTP(String recipientEmail, String otp) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        // SSL certificate handling options - choose one approach:
        
        // APPROACH 1: Trust all certificates (NOT recommended for production)
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
        // APPROACH 2: Disable SSL certificate checking (NOT recommended for production)
        // props.put("mail.smtp.starttls.enable", "true");
        // props.put("mail.smtp.ssl.checkserveridentity", "false");
        // props.put("mail.smtp.ssl.trust", "*");
        
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        
        // Enable debug mode to see detailed SMTP communication
        session.setDebug(false); // Set to true for debugging
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(recipientEmail)
            );
            message.setSubject("Your OTP Code");
            message.setText("Dear User,\n\nYour OTP code is: " + otp + "\n\nThank you!");
            
            // Send message
            Transport.send(message);
            System.out.println("OTP sent successfully to " + recipientEmail);
            return true;
            
        } catch (MessagingException e) {
            System.err.println("Failed to send email: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Alternative method with custom SSL context (for advanced users)
     * This method creates a trust-all SSL context
     */
    private void setupTrustAllSSLContext() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() { return null; }
                    public void checkClientTrusted(X509Certificate[] certs, String authType) { }
                    public void checkServerTrusted(X509Certificate[] certs, String authType) { }
                }
            };
            
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}