package com.example.zhenghaofei20200224.model;

import android.util.Log;

import com.example.zhenghaofei20200224.conterat.IConterat;
import com.example.zhenghaofei20200224.utils.Util;
import com.example.zhenghaofei20200224.utils.VollerUtil;

/**
 * Zhenghaofei20200224
 * M层
 * 2020-02-24
 */
public class HomeModel implements IConterat.IModel {

    @Override
    public void getJs(String path, final IConterat.Inerfa inerfa) {
        Log.i("xxx","路径"+path);
        //调工具类
//        Util.getInstance().getJson(path, new Util.Inter() {
//            @Override
//            public void Okk(String json) {
//                inerfa.Ok(json);
//            }
//
//            @Override
//            public void No(String str) {
//                inerfa.Ok(str);
//            }
//        });
        VollerUtil.getInstance().doGet(path, new VollerUtil.Callback() {
            @Override
            public void getSuccess(String json) {
                Log.i("xxx","json"+json);
                inerfa.Ok(json);
            }

            @Override
            public void getError(String err) {
                Log.i("xxx","jsonsss");
            }
        });
    }
}
