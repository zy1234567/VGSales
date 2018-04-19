package com.ztstech.vgmate.activitys.upload_protocol;

import android.text.TextUtils;

import com.ztstech.appdomain.user_case.AddComRecord;
import com.ztstech.appdomain.user_case.UploadProtocol;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.UploadImageBean;
import com.ztstech.vgmate.data.dto.AddComRecordData;
import com.ztstech.vgmate.data.dto.UploadProtocolData;
import com.ztstech.vgmate.data.events.UploadProtocolEvent;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by dongdong on 2018/3/26.
 */

public class UploadPresenter extends PresenterImpl<UploadContract.View> implements UploadContract.Presenter{
    public static final String UPLOAD_NAD = "upload_nad";
    public static final String UPLOAD_FOC = "upload_foc";
    public UploadPresenter(UploadContract.View view) {
        super(view);
    }

    @Override
    public void rtSubmie(final UploadProtocolData uploadProtocolData, final String flg) {
        mView.showLoading("正在上传");
        new BasePresenterSubscriber<UploadImageBean>(mView) {

            @Override
            public void childNext(UploadImageBean uploadImageBean) {
                if (uploadImageBean.isSucceed()) {
                    if (TextUtils.equals(flg,UPLOAD_NAD)){
                        uploadProtocolData.protocolMap.teamworkprotocalpicurl = null;
                        uploadProtocolData.protocolMap.secretagreementpicurl = "";
                        uploadProtocolData.protocolMap.secretagreementpicurl = uploadImageBean.urls;
                    }else{
                        uploadProtocolData.protocolMap.teamworkprotocalpicurl = null;
                        uploadProtocolData.protocolMap.promisebookpicurl = "";
                        uploadProtocolData.protocolMap.promisebookpicurl =  uploadImageBean.urls;
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
