package com.ztstech.vgmate.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.ztstech.vgmate.activitys.article_detail.ArticleDetailActivity;
import com.ztstech.vgmate.activitys.create_share.create_share_info.CreateShareInfoActivity;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.vgmate.data.dto.CreateShareData;

/**
 * Created by smm on 2017/11/7.
 * 跳转到编辑资讯、公告工具类
 */

public class Go2EditShareUtils {

    /**
     *  编辑分享
     */
    public static void editShareInfo(Context context,MainListBean.ListBean data){
        Intent it = new Intent(context, CreateShareInfoActivity.class);
        String jsonStr = new Gson().toJson(transData(data));
        it.putExtra(CreateShareInfoActivity.ARG_EDIT_DATA, jsonStr);
        ((Activity)context).startActivityForResult(it, ArticleDetailActivity.REQ_EDIT);
    }

    /**
     * 服务器反的对象类型与自己定义的对象雷翔转换
     */
    public static CreateShareData transData(MainListBean.ListBean data) {
        CreateShareData createShareData = new CreateShareData();
        createShareData.contentpicurl = data.contentpicsurl;
        createShareData.contentpicsurl = data.contentpicurl;
        createShareData.picurl = data.picurl;
        createShareData.picsurl = data.picsurl;
        createShareData.title = data.title;
        createShareData.url = data.url;
        createShareData.nid = data.nid;
        createShareData.picdescribe = data.picdescribe;
        createShareData.ntype = data.ntype;
        createShareData.type = data.type;
        createShareData.summary = data.summary;
        return createShareData;
    }

}
