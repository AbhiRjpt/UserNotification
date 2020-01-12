package com.ranag.rest.bean.response;

public class UserEventResponseData extends OrgResponseData {
    private int billId;
    private int feedbackId;

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    @Override
    public String toString() {
        return "UserEventResponseData{" +
                "billId=" + billId +
                ", feedbackId=" + feedbackId +
                '}';
    }
}
