package com.youfan.analy;

/**
 * Created by Administrator on 2018/11/3 0003.
 */
public class Userbrowser {

    private String brower;
    private long count;
    private long newcount;
    private long oldcount;
    private long timestamp;
    private String timestring;

    @Override
    public String toString() {
        return "Userbrowser{" +
                "brower='" + brower + '\'' +
                ", count=" + count +
                ", newcount=" + newcount +
                ", oldcount=" + oldcount +
                ", timestamp=" + timestamp +
                ", timestring='" + timestring + '\'' +
                '}';
    }

    public String getBrower() {
        return brower;
    }

    public void setBrower(String brower) {
        this.brower = brower;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
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
}
