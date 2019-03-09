package com.youfan.analy;

/**
 * Created by Administrator on 2018/10/29 0029.
 */
public class Usernetwork {
    private String network;
    private long count;
    private long newcount;
    private long oldcount;
    private long timestamp;
    private String timestring;

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
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

    @Override
    public String toString() {
        return "Usernetwork{" +
                "network='" + network + '\'' +
                ", count=" + count +
                ", newcount=" + newcount +
                ", oldcount=" + oldcount +
                ", timestamp=" + timestamp +
                ", timestring='" + timestring + '\'' +
                '}';
    }
}
