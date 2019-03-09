package com.youfan.table.analy;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/11/3 0003.
 */
public class MetchartOrder implements Serializable{
    private String mechartid;
    private double orderamout;
    private long count;

    public String getMechartid() {
        return mechartid;
    }

    public void setMechartid(String mechartid) {
        this.mechartid = mechartid;
    }

    public double getOrderamout() {
        return orderamout;
    }

    public void setOrderamout(double orderamout) {
        this.orderamout = orderamout;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
