package com.ztstech.vgmate.activitys.upload_protocol.upload_cood_poster;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.activitys.upload_protocol.UploadContract;
import com.ztstech.vgmate.data.dto.UploadProtocolData;

import java.io.File;

/**
 * Created by dongdong on 2018/3/27.
 */

public interface UploadCoodPosterContract {
    interface View extends BaseView {
        File[] getImgaeFiles();
        /**
         * 提交数据
         * @param errorMessage
         */
        void onSubmitFinish(String errorMessage);
    }
    interface Presenter extends BasePresenter<UploadCoodPosterContract.View> {
        void rtSubmie(UploadProtocolData uploadProtocolData, String flg);
    }
}
