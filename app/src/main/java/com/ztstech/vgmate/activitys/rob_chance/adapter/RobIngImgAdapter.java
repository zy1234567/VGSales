package com.ztstech.vgmate.activitys.rob_chance.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.data.beans.RobChanceBean;
import com.ztstech.vgmate.utils.ViewUtils;

/**
 * Created by Administrator on 2018/4/24.
 */

public class RobIngImgAdapter  extends BaseAdapter {
    Context context;
    String []imgUrl=null;
    RobChanceBean.ListBean bean;
    public RobIngImgAdapter(Context context, RobChanceBean.ListBean bean){
        this.context=context;
        this.bean=bean;
        if(bean.aptitudeurl!=null&&!TextUtils.isEmpty(bean.aptitudeurl)) {
            imgUrl = bean.aptitudeurl.split(",");
        }
    }
    @Override
    public int getCount() {
        if(imgUrl==null){
            return 0;
        }else {
            return imgUrl.length;
        }
    }

    @Override
    public Object getItem(int i) {
        return imgUrl[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.layout_robing_img,viewGroup,false);
            viewHolder=new ViewHolder();
            viewHolder.imageView=view.findViewById(R.id.img_robing);
            view.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) view.getTag();
        }
        ViewGroup.LayoutParams para =viewHolder.imageView.getLayoutParams();
        para.width=( ViewUtils.getScreenWidth(context)- ViewUtils.dp2px(context,40))/4;;
        para.height=ViewUtils.dp2px(80);
        viewHolder.imageView.setLayoutParams(para);
        Glide.with(context).load(imgUrl[i]).into(viewHolder.imageView);
        return view;
    }
    class ViewHolder{
        ImageView imageView;
    }
}
