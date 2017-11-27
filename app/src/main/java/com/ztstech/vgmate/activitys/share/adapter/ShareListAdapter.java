package com.ztstech.vgmate.activitys.share.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.ShareListBean;

/**
 *
 * @author smm
 * @date 2017/11/27
 */

public class ShareListAdapter extends SimpleRecyclerAdapter<ShareListBean.ListBean>{

    /** 几种布局 */
    public static final int TYPE_SHARE_TOTAL_COUNT = 7;
    /** 纯文本 */
    public static final int TYPE_SHARE_WORD = 0;
    /** 文字+图片 */
    public static final int TYPE_SHARE_IMG = 1;
    /** 文字+链接 */
    public static final int TYPE_SHARE_LINK = 2;
    /** 纯图片 */
    public static final int TYPE_SHATE_IMG_WHILE = 3;
    /** 纯链接 */
    public static final int TYPE_SHARE_LINK_WHILE = 4;
    /** 单张图片 */
    public static final int TYPE_SHARE_SIMPLE_PIC = 5;
    /** 单张图片+文字 */
    public static final int TYPE_SHARE_SIMPLE_PICANDWOED = 6;

    @Override
    public SimpleViewHolder<ShareListBean.ListBean> onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_SHARE_IMG:
                // 文字加图片
                return new PicsAndWordViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.zts_alter_shareitem_pic_word,parent,false));
            case TYPE_SHATE_IMG_WHILE:
                // 多张纯图片
                return new PicsViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.zts_alter_shareitem_pic,parent,false));
            case TYPE_SHARE_LINK:
                // 连接加文字
                return new LinkAndWordViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.zts_alter_shareitem,parent,false));
            case TYPE_SHARE_LINK_WHILE:
                // 纯链接
                return new LinkViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.zts_alter_shareitem_link,parent,false));
            case TYPE_SHARE_WORD:
                // 纯文字
                return new WordViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.zts_alter_shareitem_word,parent,false));
            case TYPE_SHARE_SIMPLE_PICANDWOED:
                // 单张图片加文字
                return new OnePicAndWordViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.zts_alter_shareitem_simplepic_and_word,parent,false));
            case TYPE_SHARE_SIMPLE_PIC:
                // 单张纯图片
                return new OnePicViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.zts_alter_shareitem_simplepic,parent,false));
            default:
                return new WordViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.zts_alter_shareitem_word,parent,false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
