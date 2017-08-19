package com.ztstech.vgmate.activitys.complete_info;

import com.ztstech.vgmate.activitys.PresenterImpl;

/**
 * Created by zhiyuan on 2017/8/17.
 */

public class FillInfoPresenter extends PresenterImpl<FillInfoContract.View> implements
        FillInfoContract.Presenter {

    public FillInfoPresenter(FillInfoContract.View view) {
        super(view);
    }

    @Override
    public void uploadImage(String path) {

    }
}
