package com.youfan.analy;

/**
 * Created by Administrator on 2018/10/29 0029.
 */
public class PidaoDiQuFenBu {
    private long pingdaoid;
    private String area;//地区
    private long pv;
    private  long uv;
    private long newcount;
    private long oldcount;
    private long timestamp;
    private String timestring;
    private String groupbyfield;

    public long getPingdaoid() {
        return pingdaoid;
    }

    public void setPingdaoid(long pingdaoid) {
        this.pingdaoid = pingdaoid;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public long getPv() {
        return pv;
    }

    public void setPv(long pv) {
        this.pv = pv;
    }

    public long getUv() {
        return uv;
    }

    public void setUv(long uv) {
        this.uv = uv;
    }

    public long getNewcount() {
        return newcount;
    }

    public void setNewcount(long newcount) {
        this.newcount = newcount;
    }

    public long getOldcount() {
        return oldcount;
    }

    public void setOldcount(long oldcount) {
        this.oldcount = oldcount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestring() {
        return timestring;
    }

    public void setTimestring(String timestring) {
        this.timestring = timestring;
    }

    public String getGroupbyfield() {
        return groupbyfield;
    }

    public void setGroupbyfield(String groupbyfield) {
        this.groupbyfield = groupbyfield;
    }

    @Override
    public String toString() {
        return "PidaoDiQuFenBu{" +
                "pingdaoid=" + pingdaoid +
                ", area='" + area + '\'' +
                ", pv=" + pv +
                ", uv=" + uv +
                ", newcount=" + newcount +
                ", oldcount=" + oldcount +
                ", timestamp=" + timestamp +
                ", timestring='" + timestring + '\'' +
                ", groupbyfield='" + groupbyfield + '\'' +
                '}';
    }
}
