package com.ztstech.vgmate.activitys.add_certification.PhoneCertification_next;

import com.ztstech.appdomain.user_case.LastTime;
import com.ztstech.appdomain.user_case.OrgPass;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.LastTimeBean;
import com.ztstech.vgmate.data.beans.UploadImageBean;
import com.ztstech.vgmate.data.dto.OrgPassData;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 * Created by Administrator on 2018/4/20.
 */

public class PhoneCertificationPresenter extends PresenterImpl<PhoneCertificationContract.View>implements PhoneCertificationContract.Presenter {
    public PhoneCertificationPresenter(PhoneCertificationContract.View view) {
        super(view);
    }

    @Override
    public void submitimg(OrgPassData orgPassData,int type) {
        uploadContentPic(orgPassData,type);
    }

    @Override
    public void submit(OrgPassData orgPassData) {
        mView.showLoading("请稍等");
        new BasePresenterSubscriber<BaseRespBean>(mView) {

            @Override
            public void childNext(BaseRespBean baseRespBean) {
                mView.hideLoading(null);

                if (baseRespBean.isSucceed()) {
                    //防止后台在正确情况下返回errmsg
                    mView.onSubmitFinish(null);


                }else {
                    mView.onSubmitFinish(baseRespBean.getErrmsg());
                }

            }
        }.run(new OrgPass(orgPassData).run());
    }

    @Override
    public void lastTime(String rbiid) {
        requestlastTime(rbiid);
    }
    /**
     * 请求剩余时间
     */
    private void requestlastTime(String rbiid){
        new BasePresenterSubscriber<LastTimeBean>(mView,false){

            @Override
            protected void childNext(LastTimeBean getComRecordBean) {
                if (getComRecordBean.isSucceed()) {
                    if (getComRecordBean.lasttimeMillis > 30) {
                        mView.setLastTime(getComRecordBean.lasttimeMillis);
                    }else{
                        return;
                    }
                }else {
                    //如果失败
                    mView.showError(getComRecordBean.getErrmsg());
                }
            }
        }.run(new LastTime(rbiid).run());
    }
    /**
     * 上传内容图片
     */
    private void uploadContentPic(final OrgPassData orgPassData, final int type) {
        //上传内容图片
        new BasePresenterSubscriber<UploadImageBean>(mView) {

            @Override
            public void childNext(UploadImageBean uploadImageBean) {
                if (uploadImageBean.isSucceed()) {
                    if (type == 1){
                        orgPassData.videopicurl =  uploadImageBean.urls;
                    }else{
                        orgPassData.positionpicurl = uploadImageBean.urls;
                    }

                }else {
                    mView.hideLoading(uploadImageBean.getErrmsg());
                }
            }

        }.run(RetrofitUtils.uploadFile(mView.getImgaeFiles()));

    }
}
