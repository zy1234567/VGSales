package com.ztstech.vgmate.activitys.sell_mate_list;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.MatelistBean;

import java.util.List;

/**
 * Created by smm on 2017/11/23.
 */

public interface SellMateContact {

    interface View extends BaseView{
        void setListData(List<MatelistBean.ListBean> listData);
        void setNoMoreData(boolean noMoreData);
        void showError(String msg);

    }
    interface Presenter extends BasePresenter<View> {
        void loadCacheData();
        void loadNetData(String myflg,String filtername);
        void appendData(String myflg,String filtername);
    }

}
