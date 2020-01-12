package com.ranag.rest.bean.common;

public class PropertyData {
    private String bank;
    private int merchantid;
    private double value;
    private PaymentType mode;
    private String text;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public int getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(int merchantid) {
        this.merchantid = merchantid;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public PaymentType getMode() {
        return mode;
    }

    public void setMode(PaymentType mode) {
        this.mode = mode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "PropertyData{" +
                "bank='" + bank + '\'' +
                ", merchantid=" + merchantid +
                ", value=" + value +
                ", mode=" + mode +
                ", text='" + text + '\'' +
                '}';
    }
}
