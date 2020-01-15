package com.ranag.rest.bean.response;

public class UserCreationResponseData extends OrgResponseData {
    private int userid;
    private String userKey;
    private String fname;
    private String lname;
    private String emailid;
    private String phone;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserCreationResponseData{" +
                "userid=" + userid +
                ", userKey='" + userKey + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", emailid='" + emailid + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
