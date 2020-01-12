package com.ranag.rest.bean.request;

import com.ranag.rest.bean.commons.UserEventData;

public class UserEventRequestData extends OrgRequestData {
    private UserEventData userEventData;

    public UserEventData getUserEventData() {
        return userEventData;
    }

    public void setUserEventData(UserEventData userEventData) {
        this.userEventData = userEventData;
    }

    @Override
    public String toString() {
        return "UserEventRequestData{" +
                "userEventData=" + userEventData +
                '}';
    }
}
