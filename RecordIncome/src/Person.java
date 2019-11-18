/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acer
 */
public class Person {
    private final String user;
    private final String pass;
    
    public Person(String iuser, String ipass){
        user = iuser;
        pass = ipass;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
}
