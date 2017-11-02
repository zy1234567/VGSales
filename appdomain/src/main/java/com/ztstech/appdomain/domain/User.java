package com.ztstech.appdomain.domain;

import com.ztstech.vgmate.data.beans.UserBean;

/**
 * Created by zhiyuan on 2017/10/31.
 */

public interface User {

    /**
     * 获取用户信息
     * @return
     */
    UserBean getUserBean();

    /**
     * 允许编辑文章
     * @return
     */
    boolean canEditArticle();

    /**
     * 是否允许分享
     * @return
     */
    boolean enableShare();

}
