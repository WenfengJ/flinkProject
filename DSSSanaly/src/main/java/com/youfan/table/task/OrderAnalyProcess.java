package com.youfan.table.task;

import com.youfan.batch.analy.OrderInfo;
import com.youfan.batch.analy.OrderInfotable;
import com.youfan.table.analy.MetchartOrder;
import com.youfan.table.map.OrderAnalyMap;
import com.youfan.util.HbaseUtil;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.java.BatchTableEnvironment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2018/11/3 0003.
 */
public class OrderAnalyProcess {
    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir","E:\\soft\\hadoop-2.6.0-cdh5.5.1\\hadoop-2.6.0-cdh5.5.1");
        args = new String[]{"--input","hdfs://192.168.253.151:9000//user/hive/warehouse/order/part-m-00000"};
        final ParameterTool params = ParameterTool.fromArgs(args);

        // set up the execution environment
         ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        BatchTableEnvironment tEnv = TableEnvironment.getTableEnvironment(env);

        // make parameters available in the web interface
        env.getConfig().setGlobalJobParameters(params);

        // get input data
        DataSet<String> text = env.readTextFile(params.get("input"));
        DataSet<OrderInfotable> map = text.flatMap(new OrderAnalyMap());

        tEnv.registerDataSet("OrderInfotable", map, "orderid, userid, mechartid, orderamount, " +
                "paytype, paytime, hbamount, djjamount, productid, huodongnumber");

        Table table = tEnv.sqlQuery(
                "SELECT mechartid,sum(orderamount) AS orderamout,count(1) AS count FROM OrderInfotable where paytime is not null GROUP BY mechartid ");

        DataSet<MetchartOrder> result = tEnv.toDataSet(table, MetchartOrder.class);

        try {
            result.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            List<MetchartOrder> list = result.collect();
            for(MetchartOrder metchartOrder:list){
                Map<String,String> datamap = new HashMap<String,String>();
                datamap.put("mechertordercount",metchartOrder.getCount()+"");
                datamap.put("mechertorderamount",metchartOrder.getOrderamout()+"");
                HbaseUtil.put("orderinfo",metchartOrder.getMechartid()+"","info",datamap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
