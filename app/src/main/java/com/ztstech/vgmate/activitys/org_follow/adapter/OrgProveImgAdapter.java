package com.ztstech.vgmate.activitys.org_follow.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.photo_browser.PhotoBrowserActivity;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.utils.ViewUtils;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by smm on 2017/12/5.
 * 显示机构上传资质证明图片的适配器
 */

public class OrgProveImgAdapter extends SimpleRecyclerAdapter<String>{


    @Override
    public SimpleViewHolder<String> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_prove_img,parent,false));
    }

    class ViewHolder extends SimpleViewHolder<String> {

        @BindView(R.id.img_prove)
        ImageView imageView;
        /** 要显示的图片边长 */
        private int width;

        public ViewHolder(View itemView) {
            super(itemView);
            width = (ViewUtils.getPhoneWidth(getContext()) - ViewUtils.dp2px(getContext(),40)) / 3;
        }

        @Override
        protected void refreshView(String data) {
            super.refreshView(data);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            lp.width = width;
            lp.height = width;
            imageView.setLayoutParams(lp);
            Glide.with(getContext()).load(data).error(R.mipmap.pre_default_image).into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), PhotoBrowserActivity.class);
                    intent.putExtra(PhotoBrowserActivity.KEY_POSITION, getPosition());
                    intent.putStringArrayListExtra(PhotoBrowserActivity.KEY_LIST,
                            (ArrayList<String>) mListData);
                    getContext().startActivity(intent);
                }
            });
        }
    }

}
