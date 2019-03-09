package com.youfan.contorl;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2018/10/14 0014.
 */
public class DsClienttest {
    public static void main(String[] args) {
        String message = "kafkatest";
        String adrress = "http://127.0.0.1:8088/DsInfoSJservice/webInfoSJService";
        try {
            URL url = new URL(adrress);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setAllowUserInteraction(true);
            conn.setUseCaches(false);
            conn.setReadTimeout(6*1000);
            conn.setRequestProperty("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
            conn.setRequestProperty("Content-Type","application/json");
           // conn.setRequestProperty("Content-Type","text/html;charset=utf-8");
            conn.connect();
            OutputStream outputStream = conn.getOutputStream();
            BufferedOutputStream out = new BufferedOutputStream(outputStream);
            out.write(message.getBytes());
            out.flush();
            String temp = "";
            InputStream in = conn.getInputStream();
            byte[] tempbytes = new byte[1024];
            while(in.read(tempbytes,0,1024) != -1){
                temp+=new String(tempbytes);
            }
            System.out.println(conn.getResponseCode());
            System.out.println(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
