package com.example.zhenghaofei20200224.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * Zhenghaofei20200224
 * 工具类
 * 2020-02-24
 */
public class Util {
    private static Util util=new Util();

    private Util() {

    }
    public static Util getInstance(){
        return util;
    }
    Handler handler=new Handler();
    public Boolean isWifi(Context context){
        ConnectivityManager cm= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info!=null){
            return true;
        }
        return false;
    }
    public void getJson(final String path, final Inter inter){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    Log.i("xxx","工具类"+path);
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setReadTimeout(5000);
                    conn.setConnectTimeout(7000);
                    int responseCode = conn.getResponseCode();
                    if (responseCode==200){
                        Log.i("xxx","响应成功");
                        InputStream inputStream = conn.getInputStream();
                        int len=0;
                        byte[] bytes = new byte[1024];
                        StringBuilder sb = new StringBuilder();
                        while ((len=inputStream.read(bytes))!=-1){
                            String s = new String(bytes, 0, len);
                            sb.append(s);
                        }
                        inputStream.close();
                        final String json = sb.toString();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                inter.Okk(json);
                            }
                        });
                    }else {
                        inter.No("响应失败");
                        Log.i("xxx","响应失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    public interface Inter{
        void Okk(String json);
        void No(String str);
    }
}
