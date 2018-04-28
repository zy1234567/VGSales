package com.ztstech.vgmate.activitys.add_certification.PhoneCertification_next;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.dto.OrgPassData;

import java.io.File;

/**
 * Created by Administrator on 2018/4/20.
 */

public interface PhoneCertificationContract  {
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

        void onSuccend();
    }

    interface Presenter extends BasePresenter<PhoneCertificationContract.View> {
        /**
         * 提交图片
         */
        void submitimg(OrgPassData orgPassData,int type);
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
