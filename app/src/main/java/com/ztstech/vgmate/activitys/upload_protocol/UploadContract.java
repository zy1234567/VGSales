package com.ztstech.vgmate.activitys.upload_protocol;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.dto.UploadProtocolData;

import java.io.File;

/**
 * Created by dongdong on 2018/3/26.
 */

public interface UploadContract {
    interface View extends BaseView{
        File[] getImgaeFiles();
        /**
         * 提交数据
         * @param errorMessage
         */
        void onSubmitFinish(String errorMessage);
    }
    interface Presenter extends BasePresenter<UploadContract.View> {
        void rtSubmie(UploadProtocolData uploadProtocolData,String flg);
    }
}
