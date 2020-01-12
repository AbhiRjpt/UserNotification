package com.ranag.dao.model;

public class UserMappedDbData {
    private int userkey;
    private String userid;
    private String pwd_hash;
    private String fname;
    private String lname;
    private String emailid;
    private String phone;

    public int getUserkey() {
        return userkey;
    }

    public UserMappedDbData setUserkey(int userkey) {
        this.userkey = userkey;
        return this;
    }

    public String getUserid() {
        return userid;
    }

    public UserMappedDbData setUserid(String userid) {
        this.userid = userid;
        return this;
    }

    public String getPwd_hash() {
        return pwd_hash;
    }

    public UserMappedDbData setPwd_hash(String pwd_hash) {
        this.pwd_hash = pwd_hash;
        return this;
    }

    public String getFname() {
        return fname;
    }

    public UserMappedDbData setFname(String fname) {
        this.fname = fname;
        return this;
    }

    public String getLname() {
        return lname;
    }

    public UserMappedDbData setLname(String lname) {
        this.lname = lname;
        return this;
    }

    public String getEmailid() {
        return emailid;
    }

    public UserMappedDbData setEmailid(String emailid) {
        this.emailid = emailid;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserMappedDbData setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public String toString() {
        return "UserMappedDbData{" +
                "userkey=" + userkey +
                ", userid='" + userid + '\'' +
                ", pwd_hash='" + pwd_hash + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", emailid='" + emailid + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
