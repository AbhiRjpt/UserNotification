package com.ranag.exception;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InternalException extends Exception {
    private String message = null;
    private int errorCode;

    public InternalException(String s, PreparedStatement preparedStatement, SQLException e) {
        super();
    }

    public InternalException(String message) {
        super(message);
        this.message = message;
    }

    public InternalException(String message, int errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

    public InternalException(Throwable t) {
        super(t);
        if(t instanceof InternalException) {
            this.errorCode = ((InternalException)t).getErrorCode();
            this.message = t.getMessage();
        }
    }


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isBadRequest(){
        switch (this.getErrorCode()){
            case InternalErrorCodes.INVALID_USER_ID:
            case InternalErrorCodes.NO_USERID_IN_REQUEST:
                return true;
        }
        return true;
    }

    public boolean isMethodNotAllowerd(){
        return this.getErrorCode() == InternalErrorCodes.NO_USERID_IN_REQUEST;
    }

    public boolean isUnAuthorized() {
        switch (this.getErrorCode()) {
            case InternalErrorCodes.INVALID_USER_CREDENTIALS:
            case InternalErrorCodes.NOT_COMPATIBLE_ROLE:
            case InternalErrorCodes.OPERATION_NOT_PERMITTED:
                return true;
        }
        return false;
    }

    public boolean isInternalServeerError(){
        return this.getErrorCode() == InternalErrorCodes.INVALID_REQUEST_DATA;
    }
}
