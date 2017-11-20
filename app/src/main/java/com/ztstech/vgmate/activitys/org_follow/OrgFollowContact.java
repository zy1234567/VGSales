package com.ztstech.vgmate.activitys.org_follow;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.OrgFollowlistBean;
import com.ztstech.vgmate.data.beans.WaitApproveMateListBean;

import java.util.List;

/**
 *
 * @author smm
 * @date 2017/11/14
 */

public interface OrgFollowContact {

    interface View extends BaseView{

        void setData(List<OrgFollowlistBean.ListBean> listData);

        void showError(String errorMessage);

        /**
         * 设置为没有更多数据
         * @param noreMoreData
         */
        void setNoreMoreData(boolean noreMoreData);
    }

    interface Presenter extends BasePresenter<View>{
        void loadData();

        void loadCacheData();

        void appendData();
    }

}
