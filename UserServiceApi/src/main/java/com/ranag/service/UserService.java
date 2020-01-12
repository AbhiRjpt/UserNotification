package com.ranag.service;

import com.ranag.common.restClient.OrgRestClient;
import com.ranag.common.restClient.OrgRestClientService;
import com.ranag.dao.impl.UserDaoImpl;
import com.ranag.exception.InternalErrorCodes;
import com.ranag.exception.InternalException;
import com.ranag.rest.bean.commons.UserData;
import com.ranag.rest.bean.request.SendPushNotificationRequestData;
import com.ranag.rest.bean.request.UserEventRequestData;
import com.ranag.rest.bean.response.OrgResponseData;
import com.ranag.rest.bean.response.UserDbResponseData;
import com.ranag.rest.bean.response.UserEventResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static com.ranag.rest.bean.commons.MobileNotificationType.*;

@Component
public class UserService {
    @Autowired
    UserDbResponseData responseData;

    private UserDaoImpl userDao;

    public UserService() {
        userDao = new UserDaoImpl();
    }

    public OrgResponseData getUserData() throws Exception {
        List<UserData> userDataList = userDao.fetchUserData();
        System.out.println("UserDataList: "+userDataList);
        responseData.setUserData(userDataList);
//        sendNotificationToUser();

        return responseData;
    }

    private void sendNotificationToUser(SendPushNotificationRequestData pushNotificationRequestData) throws Exception {
        System.out.println("---------------SENDING NOTIFICATION-------------");
//        SendPushNotificationRequestData pushNotificationRequestData = new SendPushNotificationRequestData("TestMessage", TEST_NOTIFICATION,"testImg.png", Arrays.asList(1),"DEV_ENVIRONMENT",0);
        OrgRestClient orgRestClient = new OrgRestClientService().getRestClient();
        String uri = "/push";
        OrgResponseData responseData = orgRestClient.post(uri,pushNotificationRequestData,OrgResponseData.class);
        checkResponse(responseData);
    }

    private void checkResponse(OrgResponseData resp) throws InternalException {
        System.out.println("resp: "+ resp);
        if (resp == null || !resp.isSuccess()) {
            String error = "INTERNAL SERVER ERROR";
            if (resp != null) {
                error = resp.getError();
            }
            throw new InternalException("Operation Failed: " + error, InternalErrorCodes.API_FAILED);
        }
    }

    public OrgResponseData submitUserEvent(UserEventRequestData requestData) {
        UserEventResponseData responseData = new UserEventResponseData();
        if((requestData != null) && (requestData.getUserEventData() != null)){
            


        }
        return responseData;
    }
}
