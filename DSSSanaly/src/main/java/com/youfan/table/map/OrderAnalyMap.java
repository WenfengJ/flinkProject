package com.youfan.table.map;

import com.alibaba.fastjson.JSONObject;
import com.youfan.batch.analy.OrderInfo;
import com.youfan.batch.analy.OrderInfotable;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

/**
 * Created by Administrator on 2018/11/3 0003.
 */
public class OrderAnalyMap  implements FlatMapFunction<String, OrderInfotable> {
    @Override
    public void flatMap(String value, Collector<OrderInfotable> out) throws Exception {
        String [] temparray = value.split("\t");
        String orderid = temparray[0];
        String userid = temparray[1];
        String  mechartid = temparray[2];
        double orderamount = Double.valueOf(temparray[3]);
        String paytype = temparray[4];
         String paytime = temparray[5];
        String hbamount = temparray[6];
        String djjamount = temparray[7];
        String productid = temparray[8];
        String huodongnumber = temparray[9];
        String createtime = temparray[10];

        OrderInfotable orderInfo = new OrderInfotable(orderid, userid, mechartid,  orderamount, paytype,  paytime, hbamount,djjamount,productid, huodongnumber, createtime);
        out.collect(orderInfo);
    }
}
