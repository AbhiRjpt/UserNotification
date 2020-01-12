package com.ranag.rest.bean.response;

import com.ranag.rest.bean.commons.UserData;

import java.util.List;

public class UserDbResponseData extends OrgResponseData {
   private List<UserData> userData;

    public List<UserData> getUserData() {
        return userData;
    }

    public void setUserData(List<UserData> userData) {
        this.userData = userData;
    }

    @Override
    public String toString() {
        return "UserDbResponseData{" +
                "userData=" + userData +
                '}';
    }
}
