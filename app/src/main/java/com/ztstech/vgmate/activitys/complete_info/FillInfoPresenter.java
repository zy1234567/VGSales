package com.ztstech.vgmate.activitys.complete_info;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.model.fill_info.FillInfoModel;

/**
 * Created by zhiyuan on 2017/8/17.
 */

public class FillInfoPresenter extends PresenterImpl<FillInfoContract.View> implements
        FillInfoContract.Presenter {

    public FillInfoPresenter(FillInfoContract.View view) {
        super(view);
    }

    @Override
    public void saveInfo(FillInfoModel model) {


        mView.onSubmitSucceed();
    }

    @Override
    public boolean isInfoFilled() {
        return false;
    }


}
