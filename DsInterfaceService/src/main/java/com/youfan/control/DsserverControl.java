package com.youfan.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/11/3 0003.
 */
@RestController
@RequestMapping(value = "dsserverControl")
public class DsserverControl {

    @RequestMapping("test")
    public  String test(){
        return  "hello world";
    }
}
