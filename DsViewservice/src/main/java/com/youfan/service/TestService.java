package com.youfan.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/11/3 0003.
 */
@FeignClient(value = "DsInterfaceService")
public interface TestService {

    // 通过前段服务  去掉用接口服务


    @RequestMapping("/dsserverControl/test")
    public  String test();
}
