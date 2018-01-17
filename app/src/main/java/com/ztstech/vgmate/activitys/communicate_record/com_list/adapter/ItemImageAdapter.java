package com.ztstech.vgmate.activitys.communicate_record.com_list.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.photo_browser.PhotoBrowserActivity;
import com.ztstech.vgmate.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/1/17.
 */

public class ItemImageAdapter extends RecyclerView.Adapter<ItemImageAdapter.MyViewHolder> {
    Context context;
    String[] photosurl;
    List list;
    public ItemImageAdapter(Context context , String[] photosurl){
        this.context = context;
        this.photosurl = photosurl;
        list = new ArrayList();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_image,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Glide.with(context)
                .load(photosurl[position])
                .error(R.mipmap.ic_launcher)
                .into(holder.imgLocationPhoto);
        holder.imgLocationPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PhotoBrowserActivity.class);
                intent.putExtra(PhotoBrowserActivity.KEY_POSITION, position);
                intent.putStringArrayListExtra(PhotoBrowserActivity.KEY_LIST,
                        (ArrayList<String>) CommonUtil.arraytolist(photosurl,list));
               context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photosurl.length;
    }

    /**
     * 实地照片recycleview适配
     */
    static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imgLocationPhoto;
        public MyViewHolder(View itemView) {
            super(itemView);
            imgLocationPhoto = itemView.findViewById(R.id.img_location_photo);
        }
    }
}
