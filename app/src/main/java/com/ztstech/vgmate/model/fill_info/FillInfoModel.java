package com.ztstech.vgmate.model.fill_info;

import android.graphics.Bitmap;

import java.io.File;

/**
 * Created by zhiyuan on 2017/8/17.
 * 填写资料model
 */

public class FillInfoModel {

    public File headerFile;

    public String name;

    public String sex;

    public String birthday;

    public String location;

    public String locationId;

    public String id;

    /**持卡人*/
    public String cardMaster;
    /**卡号*/
    public String cardNo;

    public String cardBank;


    public File idFile;

    public File idBackFile;

    public File cardFile;

    /**服务器返回url*/
    public String headUrl;
    public String headsUrl;
    public String idUrl;
    public String idBackUrl;
    public String cardUrl;


}
