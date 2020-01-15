package com.ranag.service;

import com.mysql.jdbc.StringUtils;
import com.ranag.common.Helper;
import com.ranag.common.restClient.OrgRestClient;
import com.ranag.common.restClient.OrgRestClientService;
import com.ranag.dao.impl.UserDaoImpl;
import com.ranag.exception.ExceptionData;
import com.ranag.exception.InternalErrorCodes;
import com.ranag.exception.InternalException;
import com.ranag.rest.bean.commons.*;
import com.ranag.rest.bean.request.SendPushNotificationRequestData;
import com.ranag.rest.bean.request.UserCreationRequestData;
import com.ranag.rest.bean.request.UserEventRequestData;
import com.ranag.rest.bean.response.OrgResponseData;
import com.ranag.rest.bean.response.UserCreationResponseData;
import com.ranag.rest.bean.response.UserDbResponseData;
import com.ranag.rest.bean.response.UserEventResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.util.*;

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

    public OrgResponseData submitUserEvent(UserEventRequestData requestData) throws Exception {
        UserEventResponseData responseData = new UserEventResponseData();
        UserEventData eventData = requestData.getUserEventData();
         userDao = new UserDaoImpl();
        int billId = 0;
        int feedBackId = 0;
        if((requestData != null) && (eventData != null)){
            if((!StringUtils.isNullOrEmpty(requestData.getFcmToken())) || (!StringUtils.isNullOrEmpty(requestData.getDeviceToken()))) {
                this.userDao.updateUserFcmAndDeviceToken(requestData.getUserid(), requestData.getFcmToken(), requestData.getDeviceToken());
            }
            ExceptionData exceptionData = new ExceptionData();
            if((NounType.bill.equals(eventData.getNoun())) && (Verb.pay.equals(eventData.getVerb()))){
               billId = userDao.storeUserBillPaymentData(eventData);
               if(billId >0) {
                   exceptionData.setMessage("Bill paid successfully. Thank you and please submit feedback for services.");
                   exceptionData.setHttpStatus(Response.Status.OK);
               }


               int billCount = userDao.getBillCountForUser(eventData.getUserid());
                SendPushNotificationRequestData notificationRequestData;
                String message = "";
               if(billCount == 1){
                   message = "Dear User, you very first bill payment has been successfully done. Keep enjoying our services.";
                   notificationRequestData = createNotificationData(eventData.getUserid(),message);
                   sendNotificationToUser(notificationRequestData);
               }else {

                   Map<Integer,Double> userPaymentAndTimeStampMap = userDao.getUserPaymentAndTimeStampMap(eventData.getUserid());
                   double userPaymentTotalValue = 0;
                    billCount = 0;
                   List<Integer> userBillIdList = new LinkedList<>();
                   for(Map.Entry<Integer,Double> userPaymentAndTimeMap : userPaymentAndTimeStampMap.entrySet()){
                       billCount++;
                       if(userPaymentTotalValue == 0) {
                           userBillIdList.add(userPaymentAndTimeMap.getKey());
                       }
                       userPaymentTotalValue = userPaymentTotalValue + userPaymentAndTimeMap.getValue();
                       if(userPaymentTotalValue >= 20000.0){
                           userBillIdList.add(userPaymentAndTimeMap.getKey());
                           break;
                       }
                   }

                   if((billCount>=5) && (userPaymentTotalValue >= 20000.0)){
                       List<Long> userTimeStampList = userDao.getUserPaymentTimeStamp(eventData.getUserid(),userBillIdList.get(0),userBillIdList.get(1));
                       if((userTimeStampList.get(0) - userTimeStampList.get(1))<= 300000){
                           message = "Dear User, you have made payment of "+userPaymentTotalValue+" within 5 mins.";
                           notificationRequestData = createNotificationData(eventData.getUserid(),message);
                           sendNotificationToUser(notificationRequestData);
                       }
                   }
               }

            }else if((NounType.fdbk.equals(eventData.getNoun())) && (Verb.post.equals(eventData.getVerb()))){
                if(eventData.getBillId() != 0) {
                    if(new RequestValidationService().validateBillId(eventData.getBillId(),eventData.getUserid())) {
                        billId = eventData.getBillId();
                        feedBackId = userDao.storeUserFeedBackData(eventData);
                        if(feedBackId >0) {
                            exceptionData.setMessage("Thank you for sharing your valuable feedback.");
                            exceptionData.setHttpStatus(Response.Status.OK);
                        }
                    }else {
                        throw new InternalException("Bill_Id is not valid from feedback data.Please check again",InternalErrorCodes.INVALID_BILL_ID);
                    }
                }else {
                    throw new InternalException("Bill_Id is missing from feedback data.Please check again",InternalErrorCodes.BILL_ID_MISSING);
                }
            }
            responseData.setExceptionData(exceptionData);
        }
        responseData.setBillId(billId);
        responseData.setFeedbackId(feedBackId);


        return responseData;
    }

    private SendPushNotificationRequestData createNotificationData(int userid, String message) {
        return new SendPushNotificationRequestData(message, MobileNotificationType.PUBLIC_NOTIFICATION,null, Arrays.asList(userid),"DEV_ENV",0);
    }

    public OrgResponseData createUser(UserCreationRequestData requestData) throws Exception {
        UserCreationResponseData responseData = new UserCreationResponseData();
        if((!StringUtils.isNullOrEmpty(requestData.getUserkey())) && (!StringUtils.isNullOrEmpty(requestData.getPassword()))){
            requestData.setPassword(Helper.getMD5(requestData.getPassword()));
            int userId = this.userDao.createUser(requestData);
            if(userId <= 0){
                throw new InternalException("Error while creating user. Please try again",InternalErrorCodes.USER_CREATION_FAILED);
            }
            settingUpUserCreationResponseData(requestData, responseData, userId);

        }else {
            throw new InternalException("UserKey or Password is empty, please try again",InternalErrorCodes.USERKEY_OR_PASSWORD_MISSING);
        }

        return responseData;
    }

    private void settingUpUserCreationResponseData(UserCreationRequestData requestData, UserCreationResponseData responseData, int userId) {
        responseData.setUserid(userId);
        responseData.setUserKey(requestData.getUserkey());
        responseData.setFname(requestData.getFname());
        responseData.setLname(requestData.getLname());
        responseData.setPhone(requestData.getPhone());
        responseData.setEmailid(requestData.getEmailid());
    }
}
