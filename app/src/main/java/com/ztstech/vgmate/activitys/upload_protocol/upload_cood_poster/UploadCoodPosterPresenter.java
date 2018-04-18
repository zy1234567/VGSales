package com.ztstech.vgmate.activitys.upload_protocol.upload_cood_poster;

import android.text.TextUtils;

import com.ztstech.appdomain.user_case.UploadProtocol;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.activitys.upload_protocol.UploadContract;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.UploadImageBean;
import com.ztstech.vgmate.data.dto.UploadProtocolData;
import com.ztstech.vgmate.data.events.UploadProtocolEvent;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by dongdong on 2018/3/27.
 */

public class UploadCoodPosterPresenter  extends PresenterImpl<UploadCoodPosterContract.View> implements UploadCoodPosterContract.Presenter {

    public static final String UPLOAD_TEAM = "upload_team";
    public static final String UPLOAD_POSTER = "upload_poster";
    public UploadCoodPosterPresenter(UploadCoodPosterContract.View view) {
        super(view);
    }

    @Override
    public void rtSubmie(final UploadProtocolData uploadProtocolData, final String flg) {
        mView.showLoading("正在上传");
        new BasePresenterSubscriber<UploadImageBean>(mView) {

            @Override
            public void childNext(UploadImageBean uploadImageBean) {
                if (uploadImageBean.isSucceed()) {
                    if (TextUtils.equals(flg,UPLOAD_TEAM)){
                        uploadProtocolData.protocolMap.teamworkprotocalpicurl = "";
                        uploadProtocolData.protocolMap.teamworkprotocalpicurl = uploadImageBean.urls;
                    }else{
                        uploadProtocolData.protocolMap.teamworkprotocalpicurl = null;
                        uploadProtocolData.protocolMap.posterpicurl = "";
                        uploadProtocolData.protocolMap.posterpicurl =  uploadImageBean.urls;
                    }

                    uploadprotocol(uploadProtocolData);
                }else {
                    mView.hideLoading(uploadImageBean.getErrmsg());
                }
            }

        }.run(RetrofitUtils.uploadFile(mView.getImgaeFiles()));
    }

    /**
     * 上传图片地址
     */
    private void uploadprotocol(final UploadProtocolData uploadProtocolData) {
        new BasePresenterSubscriber<BaseRespBean>(mView) {

            @Override
            public void childNext(BaseRespBean baseRespBean) {
                mView.hideLoading(null);

                if (baseRespBean.isSucceed()) {
                    //防止后台在正确情况下返回errmsg
                    mView.onSubmitFinish(null);
                    EventBus.getDefault().post(new UploadProtocolEvent());
                }else {
                    mView.onSubmitFinish(baseRespBean.getErrmsg());
                }

            }
        }.run(new UploadProtocol(uploadProtocolData).run());
    }
}
