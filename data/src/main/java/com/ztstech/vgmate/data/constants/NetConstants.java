package com.ztstech.vgmate.data.constants;

import com.ztstech.vgmate.data.BuildConfig;

/**
 *
 * @author zhiyuan
 * @date 2017/7/27
 */

public interface NetConstants {

    /**服务器地址，debug时使用测试服务器，release使用正式服务器*/
//    String BASE_URL = BuildConfig.URL_BASE;

    /** 扫码登录的两个接口用的是这个请求地址*/
    String BASE_QRCODE_URL = "http://www.we17.com";

//    String BASE_URL = "http://bigc.verygrow.com";

    String BASE_URL = "http://www.008box.com/";

//    String BASE_URL = "http://192.168.1.105:8080/tom/";

//    String BASE_URL = "http://192.168.1.129/tom/";

//    String BASE_URL = "http://192.168.1.129:8080/tom/";
    /**
     * 上传文件
     */
    String UPLOAD_FILES = BuildConfig.URL_BASE + "exempt/AppSaleuploadFiles";


    int STATUS_SUCCEED = 0;

    /** 身份过期 */
    int STATUS_IDENTIFY_OUT_DATE = 2;

    String PARAM_AUTHID = "authId";

    String INTERNET_ERROR_HINT = "当前网络不可用";


}
