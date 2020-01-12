package com.ranag.rest.bean.common;

public class UserEventData {
    private NounType noun;
    private int userid;
    private String ts;
    private String latlong;
    private Verb verb;
    private int timespent;
    private int billId;
    private int feedbackId;
    private PropertyData properties;

    public NounType getNoun() {
        return noun;
    }

    public void setNoun(NounType noun) {
        this.noun = noun;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getLatlong() {
        return latlong;
    }

    public void setLatlong(String latlong) {
        this.latlong = latlong;
    }

    public Verb getVerb() {
        return verb;
    }

    public void setVerb(Verb verb) {
        this.verb = verb;
    }

    public int getTimespent() {
        return timespent;
    }

    public void setTimespent(int timespent) {
        this.timespent = timespent;
    }

    public PropertyData getProperties() {
        return properties;
    }

    public void setProperties(PropertyData properties) {
        this.properties = properties;
    }

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
        return "UserEventData{" +
                "noun=" + noun +
                ", userid=" + userid +
                ", ts='" + ts + '\'' +
                ", latlong='" + latlong + '\'' +
                ", verb=" + verb +
                ", timespent=" + timespent +
                ", billId=" + billId +
                ", feedbackId=" + feedbackId +
                ", properties=" + properties +
                '}';
    }
}
