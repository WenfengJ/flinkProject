package com.youfan.util;

import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2018/10/28 0028.
 */
public class RedisUtil {
    public static final Jedis jedis = new Jedis("xf31",6379);

    public  static String getBykey (String key){
       return jedis.get(key);
    }

    public static void main(String[] args) {
        jedis.set("test2","test22");
        String value = jedis.get("test2");
        System.out.println(value);
    }
}
