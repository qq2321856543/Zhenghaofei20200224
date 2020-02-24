package com.example.zhenghaofei20200224.model;

import android.util.Log;

import com.example.zhenghaofei20200224.conterat.IConterat;
import com.example.zhenghaofei20200224.utils.Util;
/**
 * Zhenghaofei20200224
 * M层
 * 2020-02-24
 */
public class HomeModel implements IConterat.IModel {

    @Override
    public void getJs(String path, final IConterat.Inerfa inerfa) {
        //调工具类
        Util.getInstance().getJson(path, new Util.Inter() {
            @Override
            public void Okk(String json) {
                inerfa.Ok(json);
            }

            @Override
            public void No(String str) {
                inerfa.Ok(str);
            }
        });
    }
}
