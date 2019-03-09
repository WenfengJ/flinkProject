package com.youfan.stream.map;

import com.alibaba.fastjson.JSON;
import com.youfan.analy.UserState;
import com.youfan.analy.Userbrowser;
import com.youfan.analy.Usernetwork;
import com.youfan.dao.PdvisterDao;
import com.youfan.input.KafkaMessage;
import com.youfan.log.UserscanLog;
import com.youfan.util.DateUtil;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;


/**
 * Created by Administrator on 2018/10/27 0027.
 */
public class UserbrowserMap implements FlatMapFunction<KafkaMessage,Userbrowser> {

    @Override
    public void flatMap(KafkaMessage value, Collector<Userbrowser> out) throws Exception {
        String jsonstring = value.getJsonmessage();
        long timestamp = value.getTimestamp();


        String hourtimestamp = DateUtil.getDateby(timestamp,"yyyyMMddhh");//小时
        String daytimestamp = DateUtil.getDateby(timestamp,"yyyyMMdd");//天
        String monthtimestamp = DateUtil.getDateby(timestamp,"yyyyMM");//月

        /**
         *找到浏览器的日志信息 z
         */
        UserscanLog userscanLog = JSON.parseObject(jsonstring, UserscanLog.class);
        // 用户id + browser+ 用户访问的状态

        long userid = userscanLog.getUserid();
        String browser = userscanLog.getLiulanqitype();
        UserState userState = PdvisterDao.getUserSatebyvistertime(userid+"",timestamp);


        boolean isnew = userState.isnew();
        boolean isFirsthour = userState.isFisrthour();
        boolean isFisrtday = userState.isFisrtday();
        boolean isFisrtmonth = userState.isFisrtmonth();

        Userbrowser userbrowser = new Userbrowser();
        userbrowser.setBrower(browser);
        userbrowser.setTimestamp(timestamp);
        userbrowser.setCount(1l);
        long newuser= 0l;
        if(isnew){
            newuser= 1l;
        }
        userbrowser.setNewcount(newuser);

        //小时
        long oldcount = 0l;
        if(isFirsthour){
            oldcount = 1l;
        }
        userbrowser.setOldcount(oldcount);
        userbrowser.setTimestring(hourtimestamp);
        out.collect(userbrowser);
        System.out.println("小时=="+userbrowser);

        //天
        oldcount = 0l;
        if(isFisrtday){
            oldcount = 1l;
        }
        userbrowser.setOldcount(oldcount);
        userbrowser.setTimestring(daytimestamp);
        System.out.println("天=="+userbrowser);
        out.collect(userbrowser);
        //月
        oldcount = 0l;
        if(isFisrtmonth){
            oldcount = 1l;
        }
        userbrowser.setOldcount(oldcount);
        userbrowser.setTimestring(monthtimestamp);
        System.out.println("月=="+userbrowser);
        out.collect(userbrowser);
    }
}
