package com.ztstech.vgmate.activitys.rob_chance;

import android.widget.TextView;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.activitys.communicate_record.com_list.ComListContact;
import com.ztstech.vgmate.data.beans.GetComRecordBean;
import com.ztstech.vgmate.data.beans.RobChanceBean;

import java.util.List;

/**
 * Created by dongdong on 2018/4/18.
 */

public class RobChanceContract {
    interface View extends BaseView {
        void setData(List<RobChanceBean.ListBean> listData);
        void setDataPage(RobChanceBean.PagerBean pagerBean);
        void showError(String errorMessage);
        void onSubmitFinish(String errorMessage, TextView textView,String object,int i,String j);
//        void setListData(List<GetComRecordBean.ListBean> listData);
    }

    interface Presenter extends BasePresenter<RobChanceContract.View> {

        void loadData( );

        void appendData();

        /**
         * 锁定抢单
         * @param rbiid
         */
        void lockOrg(String rbiid,TextView textView,String object,int i,String j);
    }
}
