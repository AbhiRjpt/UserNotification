package com.ranag.rest.controller;

import com.ranag.exception.InternalErrorCodes;
import com.ranag.exception.InternalException;
import com.ranag.rest.bean.request.UserCreationRequestData;
import com.ranag.rest.bean.request.UserEventRequestData;
import com.ranag.rest.bean.response.OrgResponseData;
import com.ranag.service.RequestValidationService;
import com.ranag.service.RestResponseService;
import com.ranag.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            System.out.println("-----------------USER DATA-------------------");
            orgResponseData = userService.getUserData();
            response = restResponseService.createSuccessResponse(orgResponseData);
            return response;
        } catch (Exception e) {
            response = restResponseService.createFailureResponse(e, orgResponseData);
            return response;
        }

    }

    @PostMapping("/userEvent")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response submitUserEvent(@RequestBody UserEventRequestData requestData) {
        Response response = null;
        OrgResponseData orgResponseData = null;
        try {

            System.out.println("-----------------USER DATA-------------------");
            if((!requestValidationService.validateUserId(requestData.getUserid())) && (!requestValidationService.validateUserId(requestData.getUserEventData().getUserid()))) {
                throw new InternalException("UserId is not valid,Please try again.", InternalErrorCodes.INVALID_USER_ID);
            }
            orgResponseData = userService.submitUserEvent(requestData);
            response = restResponseService.createSuccessResponse(orgResponseData);
            return response;
        } catch (Exception e) {
            response = restResponseService.createFailureResponse(e, orgResponseData);
            return response;
        }

    }


    @PostMapping("/createUser")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response createUser(@RequestBody UserCreationRequestData requestData) {
        Response response = null;
        OrgResponseData orgResponseData = null;
        try {

            System.out.println("-----------------USER DATA-------------------");
            if(requestData == null){
                throw new InternalException("Request data is empty,Please try again.", InternalErrorCodes.INVALID_REQUEST_DATA);
            }
            orgResponseData = userService.createUser(requestData);
            response = restResponseService.createSuccessResponse(orgResponseData);
            return response;
        } catch (Exception e) {
            response = restResponseService.createFailureResponse(e, orgResponseData);
            return response;
        }

    }



}
