package com.ranag.rest.bean.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ranag.exception.ExceptionData;

public class OrgResponseData {
    protected boolean success;
    protected ExceptionData exceptionData;
    protected String error;
    protected String userDisplayName;
    protected int errorCode;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ExceptionData getExceptionData() {
        return exceptionData;
    }

    public void setExceptionData(ExceptionData exceptionData) {
        this.exceptionData = exceptionData;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }


    public String toJson(){
        try{
            return new ObjectMapper().writeValueAsString(this);
        }catch (JsonProcessingException e){
            throw new RuntimeException("Can't convert reponse to Json: "+e.getMessage());
        }
    }


    @Override
    public String toString() {
        return "OrgResponseData{" +
                "success=" + success +
                ", exceptionData=" + exceptionData +
                ", error='" + error + '\'' +
                ", userDisplayName='" + userDisplayName + '\'' +
                ", errorCode=" + errorCode +
                '}';
    }
}
