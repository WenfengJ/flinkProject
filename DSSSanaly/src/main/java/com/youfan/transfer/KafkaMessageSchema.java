package com.youfan.transfer;

import com.alibaba.fastjson.JSON;
import com.youfan.input.KafkaMessage;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;

/**
 * Created by Administrator on 2018/10/27 0027.
 */
public class KafkaMessageSchema implements DeserializationSchema<KafkaMessage>, SerializationSchema<KafkaMessage> {

    @Override
    public KafkaMessage deserialize(byte[] message) throws IOException {
        String jsonString = new String(message);
        KafkaMessage kafkaMessage = JSON.parseObject(jsonString,KafkaMessage.class);
        return kafkaMessage;
    }

    @Override
    public byte[] serialize(KafkaMessage element) {
        String jsonstring = JSON.toJSONString(element);
        return jsonstring.getBytes();
    }

    @Override
    public boolean isEndOfStream(KafkaMessage nextElement) {
        return false;
    }



    @Override
    public TypeInformation<KafkaMessage> getProducedType() {
        return TypeInformation.of(KafkaMessage.class);
    }
}
