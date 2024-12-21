package com.rbnb.rbnb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = "com.rbnb.rbnb")
@PropertySource("classpath:application.yml")
public class RbnbApplication {

    public static void main(String[] args) {
        SpringApplication.run(RbnbApplication.class, args);
    }

}
