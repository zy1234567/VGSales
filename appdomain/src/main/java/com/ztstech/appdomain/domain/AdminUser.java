package com.ztstech.appdomain.domain;

import com.ztstech.vgmate.data.beans.UserBean;

/**
 * Created by zhiyuan on 2017/10/31.
 * 管理员用户
 */

public class AdminUser implements User {

    private UserBean userBean;

    public AdminUser(UserBean userBean) {
        this.userBean = userBean;
    }

    @Override
    public UserBean getUserBean() {
        return userBean;
    }

    @Override
    public boolean canEditArticle() {
        return true;
    }

    @Override
    public boolean enableShare() {
        return true;
    }
}
