package com.example.zhenghaofei20200224.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhenghaofei20200224.R;
import com.example.zhenghaofei20200224.adapter.BaseAdapt;
import com.example.zhenghaofei20200224.bean.JsonBean;
import com.example.zhenghaofei20200224.bean.ListBean;
import com.example.zhenghaofei20200224.conterat.IConterat;
import com.example.zhenghaofei20200224.presenter.HomePresenter;
import com.example.zhenghaofei20200224.utils.Util;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

/**
 * Zhenghaofei20200224
 * 主类
 * 2020-02-24
 */
public class MainActivity extends AppCompatActivity implements IConterat.IView {


    private XBanner xb;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取控件
        xb = findViewById(R.id.xb);
        lv = findViewById(R.id.lv);
        //创建实例
        HomePresenter presenter = new HomePresenter(this);
        String path="http://blog.zhaoliang5156.cn/api/news/news_data.json";
        //掉方法
        presenter.getBanner(path);

        //列表
        String url="http://mobile.bwstudent.com/small/commodity/v1/commodityList";
        presenter.getList(url);

    }

    //成功返回的方法
    @Override
    public void getSuccess(String str) {


        Boolean wifi = Util.getInstance().isWifi(this);
        //判断网络
        if (wifi){


        Gson gson = new Gson();
        JsonBean jsonBean = gson.fromJson(str, JsonBean.class);
        List<JsonBean.ResultsBean> results = jsonBean.getResults();
        JsonBean.ResultsBean resultsBean = results.get(0);
        final List<JsonBean.ResultsBean.BannerBean> banne = resultsBean.getBanner();
        //设置轮播图
        xb.setBannerData(banne);
        xb.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                JsonBean.ResultsBean.BannerBean bannerBean = banne.get(position);
                String imageurl = bannerBean.getImageurl();
                Picasso.get().load(imageurl).into((ImageView) view);
            }
        });
//        final List<JsonBean.ResultsBean.NewsistBean> newsist = resultsBean.getNewsist();
//        BaseAdapt baseAdapt = new BaseAdapt(this, newsist);
//        //设置适配器
//        lv.setAdapter(baseAdapt);

        }
    }

    @Override
    public void getListSuccess(String str) {
        Gson gson = new Gson();
        ListBean listBean = gson.fromJson(str, ListBean.class);
        ListBean.ResultBean result = listBean.getResult();
        ListBean.ResultBean.RxxpBean rxxp = result.getRxxp();
        final List<ListBean.ResultBean.RxxpBean.CommodityListBean> commodityList = rxxp.getCommodityList();
        BaseAdapt baseAdapt = new BaseAdapt(this, commodityList);
        lv.setAdapter(baseAdapt);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListBean.ResultBean.RxxpBean.CommodityListBean commodityListBean = commodityList.get(position);
                int commodityId = commodityListBean.getCommodityId();
                Toast.makeText(MainActivity.this, ""+commodityId, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                intent.putExtra("title",commodityId+"");

                startActivity(intent);
            }
        });
    }
}
