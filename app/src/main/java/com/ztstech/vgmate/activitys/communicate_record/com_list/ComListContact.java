package com.ztstech.vgmate.activitys.communicate_record.com_list;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.GetComRecordBean;

import java.util.List;

/**
 * Created by smm on 2018/1/11.
 */

public class ComListContact {

    interface View extends BaseView{
        void setData(List<GetComRecordBean.ListBean> listData);

        void showError(String errorMessage);

        void setListData(List<GetComRecordBean.ListBean> listData);
    }

    interface Presenter extends BasePresenter<View>{

        void loadData(String rbiid );

        void appendData(String rbiid );

    }

}
