package com.ztstech.vgmate.data.dto;

import retrofit2.http.Query;

/**
 * Created by zhiyuan on 2017/9/16.
 * 增加销售伙伴
 */

public class AddSellMateData {

    public String phone;

    public String uname;

    public String did;

    public String wprovince;

    public String wcity;

    public String wdistrict;

    /**是否发送验证码*/
    public boolean sendCode;
}
