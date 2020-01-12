package com.ranag.rest.controller;

import com.ranag.rest.bean.response.OrgResponseData;
import com.ranag.rest.bean.response.SimpleResponseData;
import com.ranag.service.RestResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Calendar;

@RestController
@RequestMapping("/notification")
public class UserNotificationController {
    @Autowired
    RestResponseService restResponseService;

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
    public Response sendPushNotion(){
        Response response = null;
        OrgResponseData orgResponseData = new SimpleResponseData();
        try {
            System.out.println("------>>> NOTIFICATION SERVER TO SEND NOTIFICATION <<<------");
            response = restResponseService.createSuccessResponse(orgResponseData);
            return response;
        }catch (Exception e){
            response = restResponseService.createFailureResponse(e,orgResponseData);
            return response;
        }

    }


}
