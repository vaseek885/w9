package com.vsaf.service;
import javax.ejb.Stateless;


import com.vsaf.service.DataBaseOperations;



@Stateless
public class LoginService {

    public static final int LOGIN_SUCSESS = 0;
    public static final int LOGIN_FALIURE = 1;

    private DataBaseOperations dbOps = new DataBaseOperations("UserTable");

    public short login(String username, String password) {
        System.out.println("exists: " + username + " pass " + password);
        if(dbOps.user_exists(username,password)){
            System.out.println("User" + username + " With password " + password + " Exists.");
            return LOGIN_SUCSESS;
        } else {
            System.out.println("User" + username + " With password " + password + " Does not exist.");
            return LOGIN_FALIURE;
        }
    }
}