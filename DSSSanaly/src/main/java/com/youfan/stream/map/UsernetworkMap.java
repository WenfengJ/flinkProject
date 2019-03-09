package com.youfan.stream.map;

import com.alibaba.fastjson.JSON;
import com.youfan.analy.PidaoPvUv;
import com.youfan.analy.UserState;
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
public class UsernetworkMap implements FlatMapFunction<KafkaMessage,Usernetwork> {

    @Override
    public void flatMap(KafkaMessage value, Collector<Usernetwork> out) throws Exception {
        String jsonstring = value.getJsonmessage();
        long timestamp = value.getTimestamp();


        String hourtimestamp = DateUtil.getDateby(timestamp,"yyyyMMddhh");//小时
        String daytimestamp = DateUtil.getDateby(timestamp,"yyyyMMdd");//天
        String monthtimestamp = DateUtil.getDateby(timestamp,"yyyyMM");//月

        UserscanLog userscanLog = JSON.parseObject(jsonstring, UserscanLog.class);
        long userid = userscanLog.getUserid();
        String network = userscanLog.getNetwork();
          // 用户状态

        UserState userState = PdvisterDao.getUserSatebyvistertime(userid+"",timestamp);


        boolean isnew = userState.isnew();
        boolean isFirsthour = userState.isFisrthour();
        boolean isFisrtday = userState.isFisrtday();
        boolean isFisrtmonth = userState.isFisrtmonth();

        Usernetwork usernetwork = new Usernetwork();
        usernetwork.setNetwork(network);
        usernetwork.setTimestamp(timestamp);
        usernetwork.setCount(1l);

        long newuser= 0l;
        if(isnew){
            newuser= 1l;
        }
        usernetwork.setNewcount(newuser);

        //小时
        long oldcount = 0l;
        if(isFirsthour){
            oldcount = 1l;
        }
        usernetwork.setOldcount(oldcount);
        usernetwork.setTimestring(hourtimestamp);
        out.collect(usernetwork);
        System.out.println("小时=="+usernetwork);

        //天
        oldcount = 0l;
        if(isFisrtday){
            oldcount = 1l;
        }
        usernetwork.setOldcount(oldcount);
        usernetwork.setTimestring(daytimestamp);
        System.out.println("天=="+usernetwork);
        out.collect(usernetwork);
        //月
        oldcount = 0l;
        if(isFisrtmonth){
            oldcount = 1l;
        }
        usernetwork.setOldcount(oldcount);
        usernetwork.setTimestring(monthtimestamp);
        System.out.println("月=="+usernetwork);
        out.collect(usernetwork);
    }
}
