package com.ranag.dao.impl;

import com.ranag.dao.template.impl.QueryParameter;
import com.ranag.dao.template.impl.QueryTemplateImpl;
import com.ranag.dao.template.impl.SingleRowQueryTemplateImpl;
import com.ranag.rest.bean.commons.UserData;

import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl {

    public int getUserKey(String userId) {
        final int[] userKey = {0};
        String sql = "Select userkey from UserData where userId = ?";
        QueryParameter queryParameter = new QueryParameter().setString(userId);
        new SingleRowQueryTemplateImpl(sql, queryParameter) {
            @Override
            public void processResult() throws Exception {
                userKey[0] = this.resultSet.getInt("userkey");
            }
        };
        return userKey[0];
    }

    public List<UserData> fetchUserData() {
        List<UserData> userDataList = new LinkedList<>();
        String sql = "Select * from UserData WHERE isdeleted = ?";
        QueryParameter queryParameter = new QueryParameter().setBoolean(false);
        new QueryTemplateImpl(sql,queryParameter) {
            @Override
            public void processResult() throws Exception {
                userDataList.add(new UserData()
                        .setUserkey(this.resultSet.getInt("userkey"))
                        .setUserid(this.resultSet.getString("userid"))
                        .setPwd_hash(this.resultSet.getString("pwd_hash"))
                        .setFname(this.resultSet.getString("fname"))
                        .setLname(this.resultSet.getString("lname"))
                        .setEmailid(this.resultSet.getString("emailid"))
                        .setLocation(this.resultSet.getString("location"))
                        .setDevicePlatformName(this.resultSet.getString("devicePlatformName"))
                        .setDeviceIdentity(this.resultSet.getString("deviceIdentity"))
                        .setAvatar(this.resultSet.getString("avatar"))
                        .setRegisterDateTime(this.resultSet.getString("registerDateTime"))
                        .setDeviceToken(this.resultSet.getString("deviceToken"))
                        .setFcmToken(this.resultSet.getString("fcmToken"))
                        .setPhone(this.resultSet.getString("phone"))
                        .setGender(this.resultSet.getString("gender"))
                        .setAge(this.resultSet.getInt("age"))
                        .setBirthDate(this.resultSet.getString("birthDate"))
                        .setUserLanguage(this.resultSet.getString("userLanguage"))
                        .setArea(this.resultSet.getString("area"))
                        .setCity(this.resultSet.getString("city"))
                        .setState(this.resultSet.getString("state"))
                        .setCountry(this.resultSet.getString("country"))
                        .setAppVersion(this.resultSet.getString("appVersion"))
                        .setIsdeleted(this.resultSet.getBoolean("isdeleted"))
                        .setDeactivatedDate(this.resultSet.getString("deactivatedDate"))
                        .setTestUser(this.resultSet.getBoolean("testUser")));
            }
        };
        return userDataList;
    }
}
