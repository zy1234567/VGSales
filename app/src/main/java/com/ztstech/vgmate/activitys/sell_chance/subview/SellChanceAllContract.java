package com.ztstech.vgmate.activitys.sell_chance.subview;

import android.support.annotation.Nullable;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.SellChanceBean;

import java.util.List;

/**
 * Created by zhiyuan on 2017/8/24.
 */

interface SellChanceAllContract {

    interface View extends BaseView {

        void onRefreshFinish(List<SellChanceBean.ListBean> result, @Nullable String errmsg);

        void onLoadFinish(List<SellChanceBean.ListBean> result, @Nullable String errmsg);
    }

    interface Presenter extends BasePresenter<View> {

        void refreshData(String status);

        void appendData(String status);
    }

}
