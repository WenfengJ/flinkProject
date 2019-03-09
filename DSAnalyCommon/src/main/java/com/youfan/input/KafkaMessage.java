package com.youfan.input;

/**
 * Created by Administrator on 2018/10/27 0027.
 */
public class KafkaMessage {
    private String jsonmessage;//json格式的消息内容
    private int count;//消息的次数
    private Long timestamp;//消息的时间

    public String getJsonmessage() {
        return jsonmessage;
    }

    public void setJsonmessage(String jsonmessage) {
        this.jsonmessage = jsonmessage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
