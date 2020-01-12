package com.ranag.rest.service;

import com.ranag.dao.impl.UserDaoImpl;

public class RequestValidationService {

    public boolean validateUserId(String userId){
        UserDaoImpl userDao = new UserDaoImpl();
        if(userDao.getUserKey(userId) > 0){
            return true;
        }
        return false;
    }
}
