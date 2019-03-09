package com.youfan.batch.analy;

import java.util.Date;

/**
 * Created by Administrator on 2018/11/3 0003.
 *
 */
public class OrderInfo {
     private long orderid;
    private long userid;
    private long  mechartid;
    private double  orderamount;
    private long paytype;
    private Date paytime;
    private long hbamount;
    private long djjamount;
    private long productid;
    private long huodongnumber;
private Date createtime;

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public long getOrderid() {
        return orderid;
    }

    public void setOrderid(long orderid) {
        this.orderid = orderid;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getMechartid() {
        return mechartid;
    }

    public void setMechartid(long mechartid) {
        this.mechartid = mechartid;
    }

    public double getOrderamount() {
        return orderamount;
    }

    public void setOrderamount(double orderamount) {
        this.orderamount = orderamount;
    }

    public long getPaytype() {
        return paytype;
    }

    public void setPaytype(long paytype) {
        this.paytype = paytype;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public long getHbamount() {
        return hbamount;
    }

    public void setHbamount(long hbamount) {
        this.hbamount = hbamount;
    }

    public long getDjjamount() {
        return djjamount;
    }

    public void setDjjamount(long djjamount) {
        this.djjamount = djjamount;
    }

    public long getProductid() {
        return productid;
    }

    public void setProductid(long productid) {
        this.productid = productid;
    }

    public long getHuodongnumber() {
        return huodongnumber;
    }

    public void setHuodongnumber(long huodongnumber) {
        this.huodongnumber = huodongnumber;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderid=" + orderid +
                ", userid=" + userid +
                ", mechartid=" + mechartid +
                ", orderamount=" + orderamount +
                ", paytype=" + paytype +
                ", paytime=" + paytime +
                ", hbamount=" + hbamount +
                ", djjamount=" + djjamount +
                ", productid=" + productid +
                ", huodongnumber=" + huodongnumber +
                ", createtime=" + createtime +
                '}';
    }
}
