package com.ztstech.vgmate.activitys.communicate_record.add_communcate;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.dto.AddComRecordData;

import java.io.File;

/**
 * Created by zhangruidong on 2018/1/15.
 */

public interface AddComRecordContact {
    interface View extends BaseView {
        File[] getImgaeFiles();
        /**
         * 提交数据
         * @param errorMessage
         */
        void onSubmitFinish(String errorMessage);
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 提交数据
         * @param addComRecordData
         */
        void submit(AddComRecordData addComRecordData);
    }
}
