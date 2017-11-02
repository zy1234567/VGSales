package com.ztstech.appdomain.domain;

import com.ztstech.vgmate.data.beans.UserBean;

/**
 * Created by zhiyuan on 2017/10/31.
 */

public class NormalUser implements User {

    private UserBean userBean;

    public NormalUser(UserBean userBean) {
        this.userBean = userBean;
    }

    @Override
    public UserBean getUserBean() {
        return userBean;
    }

    @Override
    public boolean canEditArticle() {
        return false;
    }

    @Override
    public boolean enableShare() {
        return false;
    }

    @Override
    public boolean enableDeleteArticle() {
        return false;
    }

    @Override
    public boolean enableDeleteComment() {
        return false;
    }


}
