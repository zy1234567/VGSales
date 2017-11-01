package com.ztstech.vgmate.data.dto;

import java.io.File;

/**
 * Created by zhiyuan on 2017/9/13.
 * 创建分享bean
 */
public class CreateShareData {


//    @param title 标题
//  * @param summary 内容
//  * @param contentpicurl 内容图片地址
//  * @param contentpicsurl 内容图片缩略地址
//  * @param picurl 封面图片
//  * @param picsurl 封面图片缩略
//  * @param picdescribe 图片描述
//  * @param type 资讯类型（00：资讯，01公告）
//            * @param url 资讯链接

    public String title;

    public String summary;

    public String contentpicurl;

    public String contentpicsurl;

    public String picurl;

    public String picsurl;

    /**
     * json数组
     */
    public String picdescribe;

    public String type;

    public String url;

    /***
     * 中间文件
     */
    public File[] contentpicfiles;

    /**
     * 头像文件
     */
    public File headFile;

    /**
     * 资讯id，编辑模式传入
     */
    public String nid;

    /**资讯类型（01纯文字；02纯图；03字加图；04纯网址；05字加网址），编辑资讯传入*/
    public String ntype;

}
