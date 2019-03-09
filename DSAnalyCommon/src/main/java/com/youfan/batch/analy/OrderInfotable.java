package com.youfan.batch.analy;

import java.util.Date;

/**
 * Created by Administrator on 2018/11/3 0003.
 *
 */
public class OrderInfotable {
     public String orderid;
    public String userid;
    public String  mechartid;
    public double  orderamount;
    public String paytype;
    public String paytime;
    public String hbamount;
    public String djjamount;
    public String productid;
    public String huodongnumber;
    public String createtime;

    public OrderInfotable(){}

    public OrderInfotable(String orderid, String userid, String mechartid, double orderamount, String paytype, String paytime, String hbamount, String djjamount, String productid, String huodongnumber, String createtime) {
        this.orderid = orderid;
        this.userid = userid;
        this.mechartid = mechartid;
        this.orderamount = orderamount;
        this.paytype = paytype;
        this.paytime = paytime;
        this.hbamount = hbamount;
        this.djjamount = djjamount;
        this.productid = productid;
        this.huodongnumber = huodongnumber;
        this.createtime = createtime;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMechartid() {
        return mechartid;
    }

    public void setMechartid(String mechartid) {
        this.mechartid = mechartid;
    }

    public double getOrderamount() {
        return orderamount;
    }

    public void setOrderamount(double orderamount) {
        this.orderamount = orderamount;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }

    public String getHbamount() {
        return hbamount;
    }

    public void setHbamount(String hbamount) {
        this.hbamount = hbamount;
    }

    public String getDjjamount() {
        return djjamount;
    }

    public void setDjjamount(String djjamount) {
        this.djjamount = djjamount;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getHuodongnumber() {
        return huodongnumber;
    }

    public void setHuodongnumber(String huodongnumber) {
        this.huodongnumber = huodongnumber;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
