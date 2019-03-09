package com.youfan.contorl;

import com.alibaba.fastjson.JSON;
import com.youfan.input.KafkaMessage;
import com.youfan.log.UserscanLog;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by Administrator on 2018/10/14 0014.
 */
@Controller
@RequestMapping("DsInfoSJservice")
public class DsInfoSJservice {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping(value="webInfoSJService",method = RequestMethod.POST)
    public void webInfoSJService(@RequestBody String jsonstr, HttpServletRequest request, HttpServletResponse response){
        System.out.println("hello Jin来了==未转换kafkamessage之前的=="+jsonstr);
        KafkaMessage kafkaMessage = new KafkaMessage();
        kafkaMessage.setJsonmessage(jsonstr);
        kafkaMessage.setCount(1);
        kafkaMessage.setTimestamp(new Date().getTime());
        jsonstr = JSON.toJSONString(kafkaMessage);
        System.out.println("hello Jin来了==转换kafkamessage之后的=="+jsonstr);
        //业务开始
        kafkaTemplate.send("xf03","key",jsonstr);
        //业务结束
        PrintWriter printWriter = getWriter(response);
        response.setStatus(HttpStatus.OK.value());
        printWriter.write("success");
        closeprintwriter(printWriter);

    }

    private PrintWriter getWriter(HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        OutputStream out = null;
        PrintWriter printWriter = null;
        try {
            out = response.getOutputStream();
            printWriter = new PrintWriter(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return printWriter;
    }

    private void closeprintwriter(PrintWriter printWriter){
        printWriter.flush();
        printWriter.close();
    }
}
