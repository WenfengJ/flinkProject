package com.youfan.analy;

/**
 * Created by Administrator on 2018/10/27 0027.
 * 频道热点
 */
public class PindaoRD {

    private Long pingdaoid;
    private Long count;

    public Long getPingdaoid() {
        return pingdaoid;
    }

    public void setPingdaoid(Long pingdaoid) {
        this.pingdaoid = pingdaoid;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "PindaoRD{" +
                "pingdaoid=" + pingdaoid +
                ", count=" + count +
                '}';
    }
}
