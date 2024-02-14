package com.example.shoppingmanagement.activitys;

import java.util.ArrayList;

public class user {

    private String email;
    private String Password;
    private String Phone;


    public user(String email, String password, String phone) {
        this.email = email;
        Password = password;
        Phone = phone;
    }

    public String toString() {
        return "Email: " + email + ", Password: " + Password + ", Phone: " + Phone;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return Password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
