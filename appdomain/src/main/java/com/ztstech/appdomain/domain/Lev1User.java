package com.ztstech.appdomain.domain;

import com.ztstech.vgmate.data.beans.UserBean;

/**
 * Created by zhiyuan on 2017/11/2.
 */

public class Lev1User implements User {

    private UserBean userBean;

    public Lev1User(UserBean userBean) {
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
        return true;
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
