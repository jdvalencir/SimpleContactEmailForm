package com.revAIsorTest.model;

public class ContactFormModel {
    private String name; 
    private String email;
    private String message;

    // Getters
    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }
}
