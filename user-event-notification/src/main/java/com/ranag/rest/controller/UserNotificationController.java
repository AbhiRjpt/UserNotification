package com.ranag.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ranag.rest.bean.request.SendPushNotificationRequestData;
import com.ranag.rest.bean.response.OrgResponseData;
import com.ranag.rest.bean.response.SimpleResponseData;
import com.ranag.rest.service.PushNotificationService;
import com.ranag.service.RestResponseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Calendar;

@RestController
@RequestMapping("/notification")
public class UserNotificationController {
    public static final Logger log = LogManager.getLogger(UserNotificationController.class);
    @Autowired
    RestResponseService restResponseService;

    @Autowired
    PushNotificationService pushNotificationService;

    @GetMapping("/ping")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public OrgResponseData getPing() {
        System.out.println("Ping Time stamp is : " + Calendar.getInstance().getTime());
        return restResponseService.createSuccessResponse();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PostMapping("/push")
    public Response sendPushNotion(@RequestBody SendPushNotificationRequestData pushNotificationRequestData) throws JsonProcessingException {
        log.debug("RequestData:{}",pushNotificationRequestData);
        System.out.println(new ObjectMapper().writeValueAsString(pushNotificationRequestData));
        Response response = null;
        OrgResponseData orgResponseData = new SimpleResponseData();
        try {
            System.out.println("------>>> NOTIFICATION SERVER TO SEND NOTIFICATION <<<------");
            pushNotificationService.sendPushNotificationToUser(pushNotificationRequestData);
            response = restResponseService.createSuccessResponse(orgResponseData);
            return response;
        }catch (Exception e){
            response = restResponseService.createFailureResponse(e,orgResponseData);
            return response;
        }

    }


}
