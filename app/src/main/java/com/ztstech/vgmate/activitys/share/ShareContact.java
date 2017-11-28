package com.ztstech.vgmate.activitys.share;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.ShareListBean;

import java.util.List;

/**
 * Created by smm on 2017/11/27.
 */

public class ShareContact {

    public interface View extends BaseView{
        void showError(String msg);
        void onDeleteSuccess();
        void setListData(List<ShareListBean.ListBean> listData);
    }

    public interface Presenter extends BasePresenter<View>{
        void loadCacheData();
        void loadNetData();
        void appendData();
        void deleteShare(String sid);
        void priseShare(ShareListBean.ListBean data);
    }

}
