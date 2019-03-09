package com.youfan.batch.task;

import com.youfan.analy.PidaoXinXianDu;
import com.youfan.batch.analy.ProductAnaly;
import com.youfan.batch.map.ProductanalyMap;
import com.youfan.batch.reduce.ProductanalyReduce;
import com.youfan.stream.map.PindaoXinXianDuMap;
import com.youfan.stream.reduce.PindaoXinXiandusinkreduce;
import com.youfan.stream.reduce.PindaoXinxianduReduce;
import com.youfan.util.HbaseUtil;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/11/3 0003.
 */
public class ProductAnalyproces {
    public static void main(String[] args) {
        final ParameterTool params = ParameterTool.fromArgs(args);

        // set up the execution environment
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // make parameters available in the web interface
        env.getConfig().setGlobalJobParameters(params);

        // get input data
        DataSet<String> text = env.readTextFile(params.get("input"));
        DataSet<ProductAnaly> map = text.flatMap(new ProductanalyMap());
        DataSet<ProductAnaly> reduce = map.groupBy("groupbyfield").reduce(new ProductanalyReduce());
        try {
            List<ProductAnaly> list = reduce.collect();
            for(ProductAnaly value :list){
                long productid = value.getProductid();
                String datatime = value.getDateString();
                long chengjiaocount = value.getChengjiaocount();
                long weichengjiaocount = value.getWeichegnjiao();

                Map<String,String> datamap = new HashMap<String,String>();
                datamap.put("chengjiaocount",chengjiaocount+"");
                datamap.put("weichengjiaocount",weichengjiaocount+"");
                HbaseUtil.put("pindaoinfo",productid+"=="+datatime,"info",datamap);
            }
            env.execute("pindaossfx");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
