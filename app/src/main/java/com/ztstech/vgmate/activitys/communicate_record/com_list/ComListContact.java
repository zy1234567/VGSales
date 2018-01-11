package com.ztstech.vgmate.activitys.communicate_record.com_list;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.CommunicateListBean;
import com.ztstech.vgmate.data.beans.WaitApproveMateListBean;

import java.util.List;

/**
 * Created by smm on 2018/1/11.
 */

public class ComListContact {

    interface View extends BaseView{
        void setData(List<CommunicateListBean.ListBean> listData);

        void showError(String errorMessage);
    }

    interface Presenter extends BasePresenter<View>{

        void loadData(String rbiid );

        void appendData(String rbiid );

    }

}
