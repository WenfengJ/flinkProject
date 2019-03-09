package com.youfan.transfer;

import com.youfan.input.KafkaMessage;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;

import javax.annotation.Nullable;

/**
 * Created by Administrator on 2018/10/27 0027.
 */
public class KafkaMessageWatermarks implements AssignerWithPeriodicWatermarks<KafkaMessage> {

    private long currentTimestamp = Long.MIN_VALUE;

    @Override
    public long extractTimestamp(KafkaMessage event, long previousElementTimestamp) {
        // the inputs are assumed to be of format (message,timestamp)
        this.currentTimestamp = event.getTimestamp();
        return event.getTimestamp();
    }

    @Nullable
    @Override
    public Watermark getCurrentWatermark() {
        Watermark watermark = new Watermark
                (currentTimestamp == Long.MIN_VALUE ? Long.MIN_VALUE : currentTimestamp - 1);
        return watermark;
    }

}
