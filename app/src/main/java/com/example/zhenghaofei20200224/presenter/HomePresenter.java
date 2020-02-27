package com.example.zhenghaofei20200224.presenter;

import android.util.Log;

import com.example.zhenghaofei20200224.conterat.IConterat;
import com.example.zhenghaofei20200224.model.HomeModel;
/**
 * Zhenghaofei20200224
 * P层
 * 2020-02-24
 */
public class HomePresenter implements IConterat.IPresenter {

    private final HomeModel model;
    IConterat.IView mView;
    public HomePresenter(IConterat.IView View) {
        mView=View;
        model = new HomeModel();
    }

    @Override
    public void getBanner(String url) {
        //掉方法
        model.getJs(url, new IConterat.Inerfa() {
            @Override
            public void Ok(String json) {
                //返回数据
                mView.getSuccess(json);
            }
        });
    }

    @Override
    public void getList(String url) {
        //掉方法
        model.getJs(url, new IConterat.Inerfa() {
            @Override
            public void Ok(String json) {
                //返回数据
                mView.getListSuccess(json);
            }
        });
    }
}
