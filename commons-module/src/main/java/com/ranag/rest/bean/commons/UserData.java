package com.ranag.rest.bean.commons;

public class UserData {
   private int userid;
   private String userkey;
   private String pwd_hash;
   private String fname;
   private String lname;
   private String emailid;
   private String location;
   private String devicePlatformName;
   private String deviceIdentity;
   private String avatar;
   private String registerDateTime;
   private String deviceToken;
   private String fcmToken;
   private String phone;
   private String gender;
   private int age;
   private String birthDate;
   private String userLanguage;
   private String area;
   private String city;
   private String state;
   private String country;
   private String appVersion;
   private String dob;
   private boolean isdeleted;
   private String deactivatedDate;
   private boolean testUser;
   private boolean operator;

    public int getUserid() {
        return userid;
    }

    public UserData setUserid(int userid) {
        this.userid = userid;
        return this;
    }

    public String getUserkey() {
        return userkey;
    }

    public UserData setUserkey(String userkey) {
        this.userkey = userkey;
        return this;
    }

    public String getPwd_hash() {
        return pwd_hash;
    }

    public UserData setPwd_hash(String pwd_hash) {
        this.pwd_hash = pwd_hash;
        return this;
    }

    public String getFname() {
        return fname;
    }

    public UserData setFname(String fname) {
        this.fname = fname;
        return this;
    }

    public String getLname() {
        return lname;
    }

    public UserData setLname(String lname) {
        this.lname = lname;
        return this;
    }

    public String getEmailid() {
        return emailid;
    }

    public UserData setEmailid(String emailid) {
        this.emailid = emailid;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public UserData setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getDevicePlatformName() {
        return devicePlatformName;
    }

    public UserData setDevicePlatformName(String devicePlatformName) {
        this.devicePlatformName = devicePlatformName;
        return this;
    }

    public String getDeviceIdentity() {
        return deviceIdentity;
    }

    public UserData setDeviceIdentity(String deviceIdentity) {
        this.deviceIdentity = deviceIdentity;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public UserData setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getRegisterDateTime() {
        return registerDateTime;
    }

    public UserData setRegisterDateTime(String registerDateTime) {
        this.registerDateTime = registerDateTime;
        return this;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public UserData setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
        return this;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public UserData setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserData setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public UserData setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserData setAge(int age) {
        this.age = age;
        return this;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public UserData setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getUserLanguage() {
        return userLanguage;
    }

    public UserData setUserLanguage(String userLanguage) {
        this.userLanguage = userLanguage;
        return this;
    }

    public String getArea() {
        return area;
    }

    public UserData setArea(String area) {
        this.area = area;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserData setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public UserData setState(String state) {
        this.state = state;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public UserData setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public UserData setAppVersion(String appVersion) {
        this.appVersion = appVersion;
        return this;
    }

    public String getDob() {
        return dob;
    }

    public UserData setDob(String dob) {
        this.dob = dob;
        return this;
    }

    public boolean isIsdeleted() {
        return isdeleted;
    }

    public UserData setIsdeleted(boolean isdeleted) {
        this.isdeleted = isdeleted;
        return this;
    }

    public String getDeactivatedDate() {
        return deactivatedDate;
    }

    public UserData setDeactivatedDate(String deactivatedDate) {
        this.deactivatedDate = deactivatedDate;
        return this;
    }

    public boolean isTestUser() {
        return testUser;
    }

    public UserData setTestUser(boolean testUser) {
        this.testUser = testUser;
        return this;
    }

    public boolean isOperator() {
        return operator;
    }

    public UserData setOperator(boolean operator) {
        this.operator = operator;
        return this;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userid=" + userid +
                ", userkey='" + userkey + '\'' +
                ", pwd_hash='" + pwd_hash + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", emailid='" + emailid + '\'' +
                ", location='" + location + '\'' +
                ", devicePlatformName='" + devicePlatformName + '\'' +
                ", deviceIdentity='" + deviceIdentity + '\'' +
                ", avatar='" + avatar + '\'' +
                ", registerDateTime='" + registerDateTime + '\'' +
                ", deviceToken='" + deviceToken + '\'' +
                ", fcmToken='" + fcmToken + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", birthDate='" + birthDate + '\'' +
                ", userLanguage='" + userLanguage + '\'' +
                ", area='" + area + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", dob='" + dob + '\'' +
                ", isdeleted=" + isdeleted +
                ", deactivatedDate='" + deactivatedDate + '\'' +
                ", testUser=" + testUser +
                ", operator=" + operator +
                '}';
    }
}
