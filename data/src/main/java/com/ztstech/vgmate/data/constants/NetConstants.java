package com.ztstech.vgmate.data.constants;

import com.ztstech.vgmate.data.BuildConfig;

/**
 * Created by zhiyuan on 2017/7/27.
 */

public interface NetConstants {

    /**服务器地址，debug时使用测试服务器，release使用正式服务器*/
    String BASE_URL = BuildConfig.URL_BASE;

//    String BASE_URL = "http://bigc.verygrow.com";

//    String BASE_URL = "http://www.008box.com/";

//    String BASE_URL = "http://192.168.1.180/tom/";

//    String BASE_URL = "http://192.168.1.119:8080/tom/";

//    String BASE_URL = "http://192.168.1.129:8080/tom/";
    /**
     * 上传文件
     */
    String UPLOAD_FILES = "http://www.008box.com/" + "static/uploadFiles";


    int STATUS_SUCCEED = 0;

    /** 身份过期 */
    int STATUS_IDENTIFY_OUT_DATE = 2;

    String PARAM_AUTHID = "authId";



}
