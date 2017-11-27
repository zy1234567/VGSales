package com.ztstech.vgmate.activitys.share;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;

/**
 * Created by smm on 2017/11/27.
 */

public class ShareContact {

    public interface View extends BaseView{
        void showError(String msg);
        void onDeleteSuccess();
    }

    public interface Presenter extends BasePresenter<View>{
        void loadCacheData();
        void loadNetData();
        void appendData();
        void deleteShare(String sid);
        void priseShare(String sid);
    }

}
