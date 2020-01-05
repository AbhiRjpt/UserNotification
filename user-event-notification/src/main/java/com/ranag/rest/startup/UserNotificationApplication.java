package com.ranag.rest.startup;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.ranag"})
public class UserNotificationApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }

    private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder){
        System.out.println("-----------=========== INITIALIZING SPRINGBOOT APPLICATION =========---------------");
        return builder.sources(UserNotificationApplication.class);
    }

    public static void main(String[] args) {
        configureApplication(new SpringApplicationBuilder()).run(args);
    }
}
