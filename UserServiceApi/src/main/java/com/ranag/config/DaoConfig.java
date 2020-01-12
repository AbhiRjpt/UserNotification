package com.ranag.config;

import com.ranag.dao.impl.UserDaoImpl;
import com.ranag.rest.bean.response.UserDbResponseData;
import com.ranag.service.RequestValidationService;
import com.ranag.service.RestResponseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {

    @Bean
    public UserDaoImpl userDao(){
        return new UserDaoImpl();
    }

    @Bean
    public RestResponseService restResponseService(){
        return new RestResponseService();
    }

    @Bean
    public RequestValidationService requestValidationService(){
        return new RequestValidationService();
    }

    @Bean
    public UserDbResponseData userDbResponseData(){
        return new UserDbResponseData();
    }

}
