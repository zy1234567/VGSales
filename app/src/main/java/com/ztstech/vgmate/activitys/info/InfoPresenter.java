package com.ztstech.vgmate.activitys.info;

import com.ztstech.vgmate.activitys.PresenterImpl;

/**
 * Created by zhiyuan on 2017/9/8.
 */

public class InfoPresenter extends PresenterImpl<InfoContract.View> implements
        InfoContract.Presenter {

    public InfoPresenter(InfoContract.View view) {
        super(view);
    }
}
