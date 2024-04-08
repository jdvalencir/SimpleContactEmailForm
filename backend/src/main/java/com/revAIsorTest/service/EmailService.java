package com.revAIsorTest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import java.util.Date;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
        
    @Autowired
    private JavaMailSender javaMailSender;

    // Here you can change the emailTo variable to your email    
    private String emailTo = "info@revaisor.com";
        
    public void sendEmail(String name, String email, String message) throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(emailTo);
        mimeMessageHelper.setSubject("Contact Form Submission from: " + email);
        mimeMessageHelper.setText("<!DOCTYPE html>\n" +
        "<html lang=\"en\">\n" +
        "<head>\n" +
        "    <meta charset=\"UTF-8\">\n" +
        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
        "    <title>Julian David Valencia's Test</title>\n" +
        "</head>\n" +
        "<body>\n" +
        "    <h2>Julian David Valencia's Test</h2>\n" +
        "    <p><strong>Name:</strong> " + name + "</p>\n" +
        "    <p><strong>Email:</strong> " + email + "</p>\n" +
        "    <p><strong>Message:</strong> " + message + "</p>\n"+
        "</body>\n" +
        "</html>", true);       
         mimeMessageHelper.setSentDate(new Date());      
        javaMailSender.send(mimeMessage);

        // Send email logic
        System.out.println("Name: " + name);
        System.out.println("Sending email from" + email);
        System.out.println("Email to: " + emailTo);
        System.out.println("Message: " + message);
    }
}
