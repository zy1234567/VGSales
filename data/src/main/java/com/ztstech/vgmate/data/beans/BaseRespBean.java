package com.ztstech.vgmate.data.beans;

import com.ztstech.vgmate.data.constants.NetConstants;

/**
 * Created by zhiyuan on 2017/8/22.
 * 通用返回值
 */

public class BaseRespBean {

    public int messageCode;

    public int status = -1;

    public String errmsg = "";

    /**
     * 是否为请求成功的bean
     * @return
     */
    public boolean isSucceed() {
        return status == NetConstants.STATUS_SUCCEED;
    }

    /**
     * 获取错误信息
     * @return
     */
    public String getErrmsg() {
        if (isSucceed()) {
            return null;
        }else {
            return errmsg;
        }
    }

}
