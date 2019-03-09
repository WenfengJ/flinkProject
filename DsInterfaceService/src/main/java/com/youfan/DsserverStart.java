package com.youfan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Created by Administrator on 2018/11/3 0003.
 */
@SpringBootApplication
public class DsserverStart {
    public static void main(String[] args) {
        SpringApplication.run(DsserverStart.class,args);
    }
}
