package com.youfan.stream.task;

import com.youfan.analy.PindaoRD;
import com.youfan.input.KafkaMessage;
import com.youfan.stream.map.PindaoKafkaMap;
import com.youfan.stream.reduce.PindaoReduce;
import com.youfan.transfer.KafkaMessageSchema;
import com.youfan.transfer.KafkaMessageWatermarks;
import com.youfan.util.RedisUtil;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.streaming.api.datastream.IterativeStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;

/**
 * Created by Administrator on 2018/10/27 0027.
 */
public class IntervalProcessData {
    public static void main(String[] args) {

        args = new String[]{"--input-topic","test5","--bootstrap.servers","xf32:9092",
                "--zookeeper.connect","xf32:2181,xf33:2181,xf34:2181","--group.id","myconsumer1","--winsdows.size","50","--winsdows.slide","5"};

        final ParameterTool parameterTool = ParameterTool.fromArgs(args);

        if (parameterTool.getNumberOfParameters() < 6) {
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
       //将input的数据封装到map DataStream<PindaoRD> map中
        DataStream<PindaoRD> map = input.map(new PindaoKafkaMap());
        // 将map的数据,实现reduc的累加操作
        DataStream<PindaoRD> reduce = map.keyBy("pingdaoid").reduce(new PindaoReduce())   ;


      //.countWindow(Long.valueOf(parameterTool.getRequired("winsdows.size")),Long.valueOf(parameterTool.getRequired("winsdows.slide"))).reduce(new PindaoReduce());

         //将数据写到redis中去
        reduce.addSink(new SinkFunction<PindaoRD>() {
            @Override
            public void invoke(PindaoRD value) {
                long count = value.getCount();
                long pindaoid = value.getPingdaoid();
                System.out.println("输出==pindaoid"+pindaoid+":"+count);
                RedisUtil.jedis.lpush("pingdaord:"+pindaoid,count+"");
            }
        }).name("pdrdreduce");


        try {
            env.execute("pindaoredian");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
