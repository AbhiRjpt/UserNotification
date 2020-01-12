package com.ranag.rest.service;

import com.ranag.dao.impl.UserDaoImpl;
import com.ranag.rest.bean.common.MobileNotificationData;
import com.ranag.rest.bean.request.SendPushNotificationRequestData;
import security.FirebaseSecurity;
import org.json.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class PushNotificationService {

    public static enum Platform {
        // Apple Push Notification Service
        APNS,
        // Sandbox version of Apple Push Notification Service
        APNS_SANDBOX,
        // Google Cloud Messaging
        GCM,
    }


    public static final String TITLE = "IFANOW";
    public void sendPushNotificationToUser(SendPushNotificationRequestData pushNotificationRequestData) {
        if(pushNotificationRequestData != null){
            if(pushNotificationRequestData.getUserList() != null && pushNotificationRequestData.getUserList().size()>0){
                for (int userId : pushNotificationRequestData.getUserList()){
                    String fcmToken = new UserDaoImpl().getUserFcmToken(userId);
                    sendPushNotification(fcmToken,pushNotificationRequestData);
                }
            }
        }
    }

    private void sendPushNotification(String fcmToken, SendPushNotificationRequestData pushNotificationRequestData) {
        try {
            URL url = new URL(FirebaseSecurity.FIREBASE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization","key="+FirebaseSecurity.AUTH_KEY);
            conn.setRequestProperty("Content-Type", "application/json");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("to",fcmToken.trim());

            JSONObject infoNotification = new JSONObject();
            infoNotification.put("title", TITLE); // Notification title
            infoNotification.put("body", pushNotificationRequestData.getMessageText()); // Notification
            infoNotification.put("sound","default");

            MobileNotificationData mobileNotificationData = new MobileNotificationData();
            mobileNotificationData.setNotificationType(pushNotificationRequestData.getMobileNotificationType());
            mobileNotificationData.setId(pushNotificationRequestData.getId());
            mobileNotificationData.setImageUrl(pushNotificationRequestData.getImageURL());
            mobileNotificationData.setData(pushNotificationRequestData.getMessageText());

            jsonObject.put("data",getAndroidData(mobileNotificationData));
            try{
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(jsonObject.toString());
                wr.flush();

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String output;
                while ((output = br.readLine()) != null){
                    System.out.println(output);
                }

            }catch (Exception e){
                e.printStackTrace();
            }


        }catch (Exception e){

        }
    }

    private  Map<String,Object> getAndroidData(MobileNotificationData mobileNotificationData) {
        Map<String,Object> payload = new HashMap<>();
        payload.put("message",mobileNotificationData.getData());
        payload.put("title",getGameNotificationMessage(Platform.GCM, mobileNotificationData));
        payload.put("type",mobileNotificationData.getNotificationType().toString());
        payload.put("id",mobileNotificationData.getId());
        payload.put("imageUrl",mobileNotificationData.getImageUrl());
        return payload;
    }

    private Object getGameNotificationMessage(Platform platform, MobileNotificationData mobileNotificationData) {
        StringBuilder builder = new StringBuilder();
        switch (mobileNotificationData.getNotificationType()){
            case TEST_NOTIFICATION:
                if(platform == Platform.APNS){
                    builder.append(mobileNotificationData.getData());
                }else if (platform == Platform.GCM){
                    builder.append("IFANOW");
                }

                break;
            case PUBLIC_NOTIFICATION:
                if(platform == Platform.APNS){
                    builder.append(mobileNotificationData.getData());
                }else if (platform == Platform.GCM){
                    builder.append("IFANOW");
                }

                if (builder.toString().isEmpty()){
                    builder.append("IFANOW");
                }
                break;
        }
        return builder.toString();
    }
}
