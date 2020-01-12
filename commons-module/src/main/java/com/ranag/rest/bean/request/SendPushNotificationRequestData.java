package com.ranag.rest.bean.request;

import com.ranag.rest.bean.commons.MobileNotificationType;

import java.util.List;

public class SendPushNotificationRequestData extends OrgRequestData {
    private String messageText;
    private MobileNotificationType mobileNotificationType;
    private String imageURL;
    private List<String> userList;
    private String environment;
    private int notificationRequestId;

    public SendPushNotificationRequestData() {}

    public SendPushNotificationRequestData(String messageText, MobileNotificationType mobileNotificationType, String imageURL, List<String> userList, String environment, int notificationRequestId) {
        this.messageText = messageText;
        this.mobileNotificationType = mobileNotificationType;
        this.imageURL = imageURL;
        this.userList = userList;
        this.environment = environment;
        this.notificationRequestId = notificationRequestId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public MobileNotificationType getMobileNotificationType() {
        return mobileNotificationType;
    }

    public void setMobileNotificationType(MobileNotificationType mobileNotificationType) {
        this.mobileNotificationType = mobileNotificationType;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public List<String> getUserList() {
        return userList;
    }

    public void setUserList(List<String> userList) {
        this.userList = userList;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public int getNotificationRequestId() {
        return notificationRequestId;
    }

    public void setNotificationRequestId(int notificationRequestId) {
        this.notificationRequestId = notificationRequestId;
    }

    @Override
    public String toString() {
        return "SendPushNotificationRequestData{" +
                "messageText='" + messageText + '\'' +
                ", mobileNotificationType=" + mobileNotificationType +
                ", imageURL='" + imageURL + '\'' +
                ", userList=" + userList +
                ", environment='" + environment + '\'' +
                ", notificationRequestId=" + notificationRequestId +
                '}';
    }
}
