package com.youfan.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by Administrator on 2018/11/3 0003.
 */
@SpringBootApplication
@EnableEurekaServer
public class DScenterserver {
    public static void main(String[] args) {
        SpringApplication.run(DScenterserver.class,args);
    }
}
