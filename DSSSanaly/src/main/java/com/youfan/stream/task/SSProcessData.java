package com.youfan.stream.task;

import com.youfan.analy.PidaoPvUv;
import com.youfan.analy.PindaoRD;
import com.youfan.input.KafkaMessage;
import com.youfan.stream.map.PindaoKafkaMap;
import com.youfan.stream.map.PindaopvuvMap;
import com.youfan.stream.reduce.PindaoReduce;
import com.youfan.stream.reduce.PindaopvuvReduce;
import com.youfan.stream.reduce.Pindaopvuvsinkreduce;
import com.youfan.transfer.KafkaMessageSchema;
import com.youfan.transfer.KafkaMessageWatermarks;
import com.youfan.util.RedisUtil;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.util.Collector;

import java.util.List;

/**
 * Created by Administrator on 2018/10/27 0027.
 */
public class SSProcessData {
    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir","/Users/jiangwenfeng/Downloads/大数据学习/flink/资料/课程资料/第17讲/hbase-1.0.0-cdh5.5.1");
        args = new String[]{"--input-topic","xf03","--bootstrap.servers","xf32:9092",
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
        DataStream<PidaoPvUv> map = input.flatMap(new PindaopvuvMap());
        DataStream<PidaoPvUv> reduce = map.keyBy("groupbyfield").countWindow(Long.valueOf(parameterTool.getRequired("winsdows.size"))).reduce(new PindaopvuvReduce());
//        reduce.print();
        reduce.addSink(new Pindaopvuvsinkreduce()).name("pdpvuvreduce");
        try {
            env.execute("pindaossfx");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
