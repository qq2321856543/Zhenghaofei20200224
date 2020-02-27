package com.example.zhenghaofei20200224.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhenghaofei20200224.R;
import com.example.zhenghaofei20200224.bean.JsonBean;
import com.example.zhenghaofei20200224.bean.ListBean;
import com.example.zhenghaofei20200224.utils.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
/**
 * Zhenghaofei20200224
 * 适配器
 * 2020-02-24
 */
public class BaseAdapt extends android.widget.BaseAdapter {
    Context context;
    List<ListBean.ResultBean.RxxpBean.CommodityListBean> list;

    public BaseAdapt(Context context, List<ListBean.ResultBean.RxxpBean.CommodityListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder =null;
        if (convertView==null){
            convertView =View.inflate(context, R.layout.item,null);
            holder = new ViewHolder();
            holder.tu=convertView.findViewById(R.id.iv);
            holder.zi1=convertView.findViewById(R.id.tv1);
            holder.zi2=convertView.findViewById(R.id.tv2);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder) convertView.getTag();
        }
        ListBean.ResultBean.RxxpBean.CommodityListBean commodityListBean = list.get(position);
        String masterPic = commodityListBean.getMasterPic();
        String commodityName = commodityListBean.getCommodityName();
        Glide.with(context).load(masterPic).into(holder.tu);
        holder.zi1.setText(commodityName);

        return convertView;
    }
    public class ViewHolder{
        ImageView tu;
        TextView zi1;
        TextView zi2;

    }
}
