package com.user.userinfoservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.user.userinfoservice.dao")
public class UserinfoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserinfoServiceApplication.class, args);
    }

}
