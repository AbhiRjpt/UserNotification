package com.ranag.rest.controller;

import com.ranag.rest.bean.response.OrgResponseData;
import com.ranag.service.RequestValidationService;
import com.ranag.service.RestResponseService;
import com.ranag.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Calendar;

@RestController
@RequestMapping("/user")
public class UserServiceApiController {
    @Autowired
    RestResponseService restResponseService;

    @Autowired
    RequestValidationService requestValidationService;

    @Autowired
    UserService userService;

    @GetMapping("/ping")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public OrgResponseData getPing() {
        System.out.println("Ping Time stamp is : " + Calendar.getInstance().getTime());
        return restResponseService.createSuccessResponse();
    }

    @GetMapping("/userData")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserData() {
        Response response = null;
        OrgResponseData orgResponseData = null;
        try {
            orgResponseData = userService.getUserData();
            response = restResponseService.createSuccessResponse(orgResponseData);
            return response;
        } catch (Exception e) {
            response = restResponseService.createFailureResponse(e, orgResponseData);
            return response;
        }

    }

}