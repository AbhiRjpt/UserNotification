package com.ranag.rest.bean.commons;

public class CommonData {
    private int ckMId;
    private String bname;
    private int RunsMade;
    private String MatchDate;

    public int getCkMId() {
        return ckMId;
    }

    public void setCkMId(int ckMId) {
        this.ckMId = ckMId;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public int getRunsMade() {
        return RunsMade;
    }

    public void setRunsMade(int runsMade) {
        RunsMade = runsMade;
    }

    public String getMatchDate() {
        return MatchDate;
    }

    public void setMatchDate(String matchDate) {
        MatchDate = matchDate;
    }

    @Override
    public String toString() {
        return "CommonData{" +
                "ckMId=" + ckMId +
                ", bname='" + bname + '\'' +
                ", RunsMade=" + RunsMade +
                ", MatchDate='" + MatchDate + '\'' +
                '}';
    }
}
