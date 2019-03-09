package com.youfan.control;

import com.youfan.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/11/3 0003.
 */
@RestController
@RequestMapping("test")
public class TestControl {

    @Autowired
    TestService testService;

    @RequestMapping("test")
    public String test(){
        return testService.test();
    }
}
