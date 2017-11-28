package com.ztstech.vgmate.activitys.share.create;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;

import java.io.File;

/**
 * Created by smm on 2017/11/27.
 */

public class CreateShareContact {

    interface View extends BaseView{
        String getShareTitle();
        String getShareContent();
        String getLinkUrl();
        File[] getImgaeFiles();
        String getPicdescribe();
        void onCommitFinsih();
        void showError(String msg);
    }

    interface Presenter extends BasePresenter<View>{
        void commit();
    }

}
