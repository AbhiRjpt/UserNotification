package com.ranag.rest.bean.commons;

public enum MobileNotificationType {
    TEST_NOTIFICATION("TEST_NOTIFICATION"),
    PUBLIC_NOTIFICATION("PUBLIC_NOTIFICATION");
    String mobileNotificationType;

    private MobileNotificationType(String mobileNotificationType){
        this.mobileNotificationType = mobileNotificationType;
    }

    public String getMobileNotificationType() {
        return mobileNotificationType;
    }
}
