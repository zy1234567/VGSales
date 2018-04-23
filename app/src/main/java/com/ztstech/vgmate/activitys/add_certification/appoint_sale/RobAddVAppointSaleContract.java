package com.ztstech.vgmate.activitys.add_certification.appoint_sale;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.dto.OrgPassData;

import java.io.File;

/**
 * Created by Administrator on 2018/4/23.
 */

public interface RobAddVAppointSaleContract {
    interface  View extends BaseView{
        File[] getImgaeFiles();
        /**
         * 提交数据
         * @param errorMessage
         */
        void onSubmitFinish(String errorMessage);

    }
    interface  Presenter extends BasePresenter<RobAddVAppointSaleContract.View>{
        /**
         * 提交数据
         * @param orgPassData
         */
        void submit(OrgPassData orgPassData);

        /**
         * 上传图片
         */
        void uploadimg(OrgPassData orgPassData,int type);
    }
}
