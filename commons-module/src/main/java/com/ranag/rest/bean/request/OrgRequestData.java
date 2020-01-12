package com.ranag.rest.bean.request;

import com.ranag.rest.constant.DeviceInfoData;

public class OrgRequestData {
    protected String userId;
    protected int version;
    protected String appVersion;
    protected DeviceInfoData deviceInfoData;
    protected String devicePlatformName;
    protected String deviceIdentity;
    protected String deviceToken;
    protected String fcmToken;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public DeviceInfoData getDeviceInfoData() {
        return deviceInfoData;
    }

    public void setDeviceInfoData(DeviceInfoData deviceInfoData) {
        this.deviceInfoData = deviceInfoData;
    }

    public String getDevicePlatformName() {
        return devicePlatformName;
    }

    public void setDevicePlatformName(String devicePlatformName) {
        this.devicePlatformName = devicePlatformName;
    }

    public String getDeviceIdentity() {
        return deviceIdentity;
    }

    public void setDeviceIdentity(String deviceIdentity) {
        this.deviceIdentity = deviceIdentity;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    @Override
    public String toString() {
        return "OrgRequestData{" +
                "userId='" + userId + '\'' +
                ", version=" + version +
                ", appVersion='" + appVersion + '\'' +
                ", deviceInfoData=" + deviceInfoData +
                ", devicePlatformName='" + devicePlatformName + '\'' +
                ", deviceIdentity='" + deviceIdentity + '\'' +
                ", deviceToken='" + deviceToken + '\'' +
                ", fcmToken='" + fcmToken + '\'' +
                '}';
    }
}
