package com.example.zhenghaofei20200224.conterat;


/**
 * Zhenghaofei20200224
 * 契约类
 * 2020-02-24
 */
public interface IConterat {
    interface IView{
        void getSuccess(String str);

        void getListSuccess(String str);
    }

    interface IPresenter{
        void getBanner(String url);
    }
    interface IModel{
        void getJs(String path,Inerfa inerfa);
    }
    interface Inerfa{
        void Ok(String json);
    }
}
