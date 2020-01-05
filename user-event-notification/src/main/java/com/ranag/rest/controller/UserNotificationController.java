package com.ranag.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Calendar;

@RestController
@RequestMapping("/notification")
public class UserNotificationController {

    @GetMapping("/ping")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String getPing(){
        System.out.println("Ping Time stamp is : "+ Calendar.getInstance().getTime());
        return "Ping Time stamp is : "+ Calendar.getInstance().getTime();
    }
}
