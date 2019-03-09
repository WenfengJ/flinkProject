package com.youfan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Created by Administrator on 2018/10/28 0028.
 */
@SpringBootApplication
@EnableFeignClients
public class StartViewService {
    public static void main(String[] args) {
        SpringApplication.run(StartViewService.class,args);
    }
}
