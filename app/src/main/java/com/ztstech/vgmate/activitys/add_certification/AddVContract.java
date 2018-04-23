package com.ztstech.vgmate.activitys.add_certification;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.activitys.communicate_record.add_communcate.AddComRecordContact;
import com.ztstech.vgmate.data.dto.AddComRecordData;
import com.ztstech.vgmate.data.dto.AddOrgData;
import com.ztstech.vgmate.data.dto.AddVData;
import com.ztstech.vgmate.data.dto.OrgPassData;

import java.io.File;

/**
 * Created by Administrator on 2018/4/20.
 */

interface AddVContract {
    interface View extends BaseView {
        File[] getImgaeFiles();
        /**
         * 提交数据
         * @param errorMessage
         */
        void onSubmitFinish(String errorMessage);
        //绑定剩余时间
        void setLastTime(double lasttime);
        void showError(String errorMessage);

    }

    interface Presenter extends BasePresenter<AddVContract.View> {

        /**
         * 提交数据
         * @param orgPassData
         */
        void submit(OrgPassData orgPassData);
        /**
         * 获取剩余时间
         */
        void lastTime(String rbiid);
    }
}
