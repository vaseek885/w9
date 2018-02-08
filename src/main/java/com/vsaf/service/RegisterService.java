package com.vsaf.service;
import javax.ejb.Stateless;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.vsaf.service.DataBaseOperations;



@Stateless
public class RegisterService {

    public static final int REGISTER_SUCSESS = 0;
    public static final int REGISTER_FALIURE = 1;
    public static final int REGISTER_USREXISTS = 2;

    private DataBaseOperations dbOps = new DataBaseOperations("UserTable");

    public int register(String username, String password) {
        System.out.println("Registering user" + username + " with password "+ password);
        if(username.equalsIgnoreCase("UserTable")){
            return REGISTER_USREXISTS;
        } else{
            return dbOps.add_user(username,password);
            
        }

    }

}



// byte[] data = password.getBytes();

// MessageDigest md = null;
// try {
//     md = MessageDigest.getInstance("SHA-1");
// } catch (NoSuchAlgorithmException e) {
//     e.printStackTrace();
// }
// if(md != null){
//     md.update(data);
//     byte[] hash = md.digest();
//     return dbOps.add_user(username,password);
// }