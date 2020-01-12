package com.ranag.startup;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.ranag"})
public class UserApiServiceApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }
    private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder){
        System.out.println("-----------=========== INITIALIZING SPRINGBOOT APPLICATION For UserServiceApi =========---------------");
        return builder.sources(UserApiServiceApplication.class);
    }

}
