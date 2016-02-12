package com.parceltracking.user;

/**
 * Created by ioan.contiu on 2/3/2016.
 */
public class LoginUser {
    private String email;
    private String password;

    LoginUser(){
        email=null;
        password=null;
    }
    LoginUser(String email, String password){
        this.email=email;
        this.password=password;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

}
