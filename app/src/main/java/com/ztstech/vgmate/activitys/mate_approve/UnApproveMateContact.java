package com.ztstech.vgmate.activitys.mate_approve;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.vgmate.data.beans.UnApproveMateBean;
import com.ztstech.vgmate.data.beans.WaitApproveMateListBean;

import java.util.List;

/**
 * Created by smm on 2017/11/13.
 */

public interface UnApproveMateContact {

    interface View extends BaseView{

        void setData(List<WaitApproveMateListBean.ListBean> listData);

        void showError(String errorMessage);

        /**
         * 设置为没有更多数据
         * @param noreMoreData
         */
        void setNoreMoreData(boolean noreMoreData);

        void getMateDetailFinish(UnApproveMateBean bean);

    }

    interface Presenter extends BasePresenter<View>{

        void loadData(String myflg);

        void appendData(String myflg);

        void loadCacheData();

        void findMateDetail(String saleid);

    }

}
