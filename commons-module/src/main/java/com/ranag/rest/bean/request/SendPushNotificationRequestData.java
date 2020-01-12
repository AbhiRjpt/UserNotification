package com.ranag.rest.bean.request;

import com.ranag.rest.bean.commons.MobileNotificationType;

import java.util.List;

public class SendPushNotificationRequestData extends OrgRequestData {
    private String messageText;
    private MobileNotificationType mobileNotificationType;
    private String imageURL;
    private List<Integer> userList;
    private String environment;
    private int id;

    public SendPushNotificationRequestData() {}

    public SendPushNotificationRequestData(String messageText, MobileNotificationType mobileNotificationType, String imageURL, List<Integer> userList, String environment,int id) {
        this.messageText = messageText;
        this.mobileNotificationType = mobileNotificationType;
        this.imageURL = imageURL;
        this.userList = userList;
        this.environment = environment;
        this.id = id;
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

    public List<Integer> getUserList() {
        return userList;
    }

    public void setUserList(List<Integer> userList) {
        this.userList = userList;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SendPushNotificationRequestData{" +
                "messageText='" + messageText + '\'' +
                ", mobileNotificationType=" + mobileNotificationType +
                ", imageURL='" + imageURL + '\'' +
                ", userList=" + userList +
                ", environment='" + environment + '\'' +
                ", id=" + id +
                '}';
    }
}
