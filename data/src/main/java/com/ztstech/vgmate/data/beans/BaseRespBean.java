package com.ztstech.vgmate.data.beans;

/**
 * Created by zhiyuan on 2017/8/22.
 * 通用返回值
 */

public class BaseRespBean {

    public int messageCode;

    public int status = -1;

    public String errmsg;

    /**
     * 是否为请求成功的bean
     * @return
     */
    public boolean isSucceed() {
        return status == 0;
    }

    /**
     * 获取错误信息
     * @return
     */
    public String getErrmsg() {
        return errmsg;
    }

}
