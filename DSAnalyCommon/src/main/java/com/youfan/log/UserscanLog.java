package com.youfan.log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/14 0014.
 * 用户浏览记录
 */
public class UserscanLog {

    private Long pingdaoid;//频道id;
    private Long leibieid;//产品类别id
    private Long chanpinid;//产品id
    private String contry;//国家
    private String province;//省份
    private String city;//城市
    private String network;//网络方式
    private String sources;//来源方式
    private String liulanqitype;//浏览器类型
    private Long starttime;//打开时间
    private Long endetime;//离开时间
    private Long userid;//用户id

    public Long getPingdaoid() {
        return pingdaoid;
    }

    public void setPingdaoid(Long pingdaoid) {
        this.pingdaoid = pingdaoid;
    }

    public Long getLeibieid() {
        return leibieid;
    }

    public void setLeibieid(Long leibieid) {
        this.leibieid = leibieid;
    }

    public Long getChanpinid() {
        return chanpinid;
    }

    public void setChanpinid(Long chanpinid) {
        this.chanpinid = chanpinid;
    }

    public String getContry() {
        return contry;
    }

    public void setContry(String contry) {
        this.contry = contry;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public String getLiulanqitype() {
        return liulanqitype;
    }

    public void setLiulanqitype(String liulanqitype) {
        this.liulanqitype = liulanqitype;
    }

    public Long getStarttime() {
        return starttime;
    }

    public void setStarttime(Long starttime) {
        this.starttime = starttime;
    }

    public Long getEndetime() {
        return endetime;
    }

    public void setEndetime(Long endetime) {
        this.endetime = endetime;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
