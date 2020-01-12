package com.ranag.service;

import com.ranag.dao.impl.UserDaoImpl;
import com.ranag.rest.bean.commons.UserData;
import com.ranag.rest.bean.response.OrgResponseData;
import com.ranag.rest.bean.response.UserDbResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    @Autowired
    UserDbResponseData responseData;

    private UserDaoImpl userDao;

    public UserService() {
        userDao = new UserDaoImpl();
    }

    public OrgResponseData getUserData() {
        List<UserData> userDataList = userDao.fetchUserData();
        responseData.setUserData(userDataList);
        return responseData;
    }
}
