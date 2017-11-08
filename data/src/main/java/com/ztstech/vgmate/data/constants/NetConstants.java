package com.ztstech.vgmate.data.constants;

import com.ztstech.vgmate.data.BuildConfig;

/**
 * Created by zhiyuan on 2017/7/27.
 */

public interface NetConstants {

    /**服务器地址，debug时使用测试服务器，release使用正式服务器*/
    String BASE_URL = BuildConfig.URL_BASE;

    /**
     * 上传文件
     */
    String UPLOAD_FILES = BASE_URL + "static/uploadFiles";


    int STATUS_SUCCEED = 0;

    String PARAM_AUTHID = "authId";



}
