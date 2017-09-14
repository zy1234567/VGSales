package com.ztstech.vgmate.activitys.create_share_add_cover;

import android.support.annotation.Nullable;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.CreateShareBean;

/**
 * Created by zhiyuan on 2017/9/12.
 */

interface CreateShareAddCoverContract {

    interface View extends BaseView {

        void submitFinish(@Nullable String errorMessage);

    }

    interface Presenter extends BasePresenter<View> {

        void submit(CreateShareBean createShareBean);
    }
}
