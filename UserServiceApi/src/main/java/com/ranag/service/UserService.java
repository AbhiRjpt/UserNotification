package com.ranag.service;

import org.springframework.stereotype.Component;

@Component
public class UserService {

    private int userkey;
    private String userid;
    private String pwd_hash;
    private String fname;
    private String lname;
    private String emailid;
    private String phone;

    public UserService setData(int userkey, String userid, String pwd_hash, String fname, String lname, String emailid, String phone) {
        this.userkey = userkey;
        this.userid = userid;
        this.pwd_hash = pwd_hash;
        this.fname = fname;
        this.lname = lname;
        this.emailid = emailid;
        this.phone = phone;
        return this;
    }

   public void fetchUserData(){


   }

}
