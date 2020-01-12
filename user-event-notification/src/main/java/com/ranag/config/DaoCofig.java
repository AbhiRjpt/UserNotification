package com.ranag.config;

import com.ranag.dao.impl.UserDaoImpl;
import com.ranag.service.RequestValidationService;
import com.ranag.service.RestResponseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoCofig {
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
}
