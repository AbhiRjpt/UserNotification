package com.ranag.rest.bean.common;

import com.ranag.rest.bean.commons.MobileNotificationType;

public class MobileNotificationData {
    private String data;
    private MobileNotificationType notificationType;
    private int id;
    private String imageUrl;
    private String orgId;

    public MobileNotificationData() {
    }

    public MobileNotificationData(String data) {
        super();
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public MobileNotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(MobileNotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Override
    public String toString() {
        return "MobileNotificationData{" +
                "data='" + data + '\'' +
                ", notificationType=" + notificationType +
                ", id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", orgId='" + orgId + '\'' +
                '}';
    }
}
