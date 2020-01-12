package com.ranag.exception;

import javax.ws.rs.core.Response;

public class ExceptionData {
    private String message;
    private int internalErrorCode;
    private int httpErrorCode;
    private Response.Status httpStatus;

    public ExceptionData() {
    }

    public int getHttpErrorCode() {
        return httpErrorCode;
    }

    public void setHttpErrorCode(int httpErrorCode) {
        this.httpErrorCode = httpErrorCode;
    }

    public ExceptionData(String message, int internalErrorCode) {
        this.message = message;
        this.internalErrorCode = internalErrorCode;
    }

    public ExceptionData(String message, int internalErrorCode, int httpErrorCode, Response.Status httpStatus) {
        this.message = message;
        this.internalErrorCode = internalErrorCode;
        this.httpErrorCode = httpErrorCode;
        this.httpStatus = httpStatus;
    }

    public Response.Status getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Response.Status httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getInternalErrorCode() {
        return internalErrorCode;
    }

    public void setInternalErrorCode(int internalErrorCode) {
        this.internalErrorCode = internalErrorCode;
    }

    @Override
    public String toString() {
        return "ExceptionData{" +
                "message='" + message + '\'' +
                ", internalErrorCode=" + internalErrorCode +
                ", httpErrorCode=" + httpErrorCode +
                ", httpStatus=" + httpStatus +
                '}';
    }
}
