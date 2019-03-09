package com.youfan.stream.task;

import com.youfan.analy.PidaoXinXianDu;
import com.youfan.input.KafkaMessage;
import com.youfan.stream.map.PindaoXinXianDuMap;
import com.youfan.stream.reduce.PindaoXinXiandusinkreduce;
import com.youfan.stream.reduce.PindaoXinxianduReduce;
import com.youfan.transfer.KafkaMessageSchema;
import com.youfan.transfer.KafkaMessageWatermarks;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;

/**
 * Created by Administrator on 2018/10/27 0027.
 */

/**
 * 频道地区分布
 */
public class SSDQFBProcessData {
    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir","E:\\soft\\hadoop-2.6.0-cdh5.5.1\\hadoop-2.6.0-cdh5.5.1");
        args = new String[]{"--input-topic","test1","--bootstrap.servers","xf32:9092",
                "--zookeeper.connect","xf32:2181,xf33:2181,xf34:2181","--group.id","myconsumer1","--winsdows.size","50"};

        final ParameterTool parameterTool = ParameterTool.fromArgs(args);

        if (parameterTool.getNumberOfParameters() < 5) {
            System.out.println("Missing parameters!\n" +
                    "Usage: Kafka --input-topic <topic>" +
                    "--bootstrap.servers <kafka brokers> " +
                    "--zookeeper.connect <zk quorum> --group.id <some id>");
            return;
        }

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.getConfig().disableSysoutLogging();
        env.getConfig().setRestartStrategy(RestartStrategies.fixedDelayRestart(4, 10000));
        env.enableCheckpointing(5000); // create a checkpoint every 5 seconds
        env.getConfig().setGlobalJobParameters(parameterTool); // make parameters available in the web interface
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);


        FlinkKafkaConsumer010  flinkKafkaConsumer = new FlinkKafkaConsumer010<KafkaMessage>(parameterTool.getRequired("input-topic"), new KafkaMessageSchema(), parameterTool.getProperties());
        DataStream<KafkaMessage> input = env.addSource(flinkKafkaConsumer.assignTimestampsAndWatermarks(new KafkaMessageWatermarks()));
        DataStream<PidaoXinXianDu> map = input.flatMap(new PindaoXinXianDuMap());
        DataStream<PidaoXinXianDu> reduce = map.keyBy("groupbyfield").countWindow(Long.valueOf(parameterTool.getRequired("winsdows.size"))).reduce(new PindaoXinxianduReduce());
//        reduce.print();
        reduce.addSink(new PindaoXinXiandusinkreduce()).name("pdxinxiandureduce");
        try {
            env.execute("pindaossfx");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
