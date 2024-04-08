package com.revAIsorTest.web.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import com.revAIsorTest.model.ContactFormModel;
import com.revAIsorTest.service.EmailService;

@RestController
public class ContactFormController {    
    
    @Autowired
    private EmailService emailService;

    @GetMapping("/contact-form")    
    public ModelAndView showContactForm() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("contact");
        mv.addObject("contact", new ContactFormModel());
        return mv;
    }

    @PostMapping("/submit-contact-form")
    public ResponseEntity<String> submitContactForm(@RequestBody ContactFormModel contactFormModel) {
        try {
            // Validate null or empty values
            if (contactFormModel.getName() == null || contactFormModel.getName().isEmpty() ||
                contactFormModel.getEmail() == null || contactFormModel.getEmail().isEmpty() ||
                contactFormModel.getMessage() == null || contactFormModel.getMessage().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name, email, and message are required");
            }
    
            // Validate email format
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            if (!contactFormModel.getEmail().matches(emailRegex)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email format");
            }
    
            // Validate length restrictions
            if (contactFormModel.getName().length() < 3 || contactFormModel.getName().length() > 50) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name must be between 3 and 50 characters");
            }
    
            if (contactFormModel.getMessage().length() < 10 || contactFormModel.getMessage().length() > 200) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message must be between 10 and 200 characters");
            }
    
            // If all validations pass, send the email
            emailService.sendEmail(contactFormModel.getName(), contactFormModel.getEmail(), contactFormModel.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body("Email sent successfully");
            
        } catch (Exception e) {
            System.out.println("Error sending email: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email");
        }
    }

    @GetMapping("/success")
    public ModelAndView showSuccessPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("success");
        return mv;
    }
}

