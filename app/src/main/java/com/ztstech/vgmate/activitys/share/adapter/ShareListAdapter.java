package com.ztstech.vgmate.activitys.share.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.mikephil.charting.formatter.IFillFormatter;
import com.ztstech.appdomain.constants.Constants;
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

    BaseShareViewHolder.ClickCallback callback;


    public ShareListAdapter(BaseShareViewHolder.ClickCallback callback){
        this.callback = callback;
    }

    @Override
    public SimpleViewHolder<ShareListBean.ListBean> onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_SHARE_IMG:
                // 文字加图片
                return new PicsAndWordViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.zts_alter_shareitem_pic_word,parent,false),callback);
            case TYPE_SHATE_IMG_WHILE:
                // 多张纯图片
                return new PicsViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.zts_alter_shareitem_pic,parent,false),callback);
            case TYPE_SHARE_LINK:
                // 连接加文字
                return new LinkAndWordViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.zts_alter_shareitem,parent,false),callback);
            case TYPE_SHARE_LINK_WHILE:
                // 纯链接
                return new LinkViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.zts_alter_shareitem_link,parent,false),callback);
            case TYPE_SHARE_WORD:
                // 纯文字
                return new WordViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.zts_alter_shareitem_word,parent,false),callback);
            case TYPE_SHARE_SIMPLE_PICANDWOED:
                // 单张图片加文字
                return new OnePicAndWordViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.zts_alter_shareitem_simplepic_and_word,parent,false),callback);
            case TYPE_SHARE_SIMPLE_PIC:
                // 单张纯图片
                return new OnePicViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.zts_alter_shareitem_simplepic,parent,false),callback);
            default:
                return new WordViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.zts_alter_shareitem_word,parent,false),callback);
        }
    }

    @Override
    public int getItemViewType(int position) {
        String ntype = mListData.get(position).ntype;
        if (TextUtils.equals(ntype, Constants.SHARE_ONLY_WORD)){
            return TYPE_SHARE_WORD;
        }
        if (TextUtils.equals(ntype,Constants.SHARE_ONLY_LINK)){
            return TYPE_SHARE_LINK_WHILE;
        }
        if (TextUtils.equals(ntype,Constants.SHARE_WORD_LINK)){
            return TYPE_SHARE_LINK;
        }
        if (TextUtils.equals(ntype,Constants.SHARE_ONLY_IMAGE)){
            // 图片类型分为单张图片和多张图片
            String contentpicurl = mListData.get(position).contentpicurl;
            if (contentpicurl.contains(",")){
                //多图
                return TYPE_SHATE_IMG_WHILE;
            }else {
                // 单图
                return TYPE_SHARE_SIMPLE_PIC;
            }
        }
        if (TextUtils.equals(ntype,Constants.SHARE_WORD_IMAGE)){
            // 图片类型分为单张图片加文字和多张图片加文字
            String contentpicurl = mListData.get(position).contentpicurl;
            if (contentpicurl.contains(",")){
                //多图加文字
                return TYPE_SHARE_IMG;
            }else {
                // 单图加文字
                return TYPE_SHARE_SIMPLE_PICANDWOED;
            }
        }
        return TYPE_SHARE_WORD;
    }

}
