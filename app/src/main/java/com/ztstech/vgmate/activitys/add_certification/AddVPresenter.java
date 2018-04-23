package com.ztstech.vgmate.activitys.add_certification;

import android.app.Activity;
import android.widget.TextView;

import com.ztstech.appdomain.user_case.AddComRecord;
import com.ztstech.appdomain.user_case.AddVcertification;
import com.ztstech.appdomain.user_case.LastTime;
import com.ztstech.appdomain.user_case.OrgPass;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.LastTimeBean;
import com.ztstech.vgmate.data.beans.UploadImageBean;
import com.ztstech.vgmate.data.dto.AddComRecordData;
import com.ztstech.vgmate.data.dto.AddVData;
import com.ztstech.vgmate.data.dto.OrgPassData;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 * Created by Administrator on 2018/4/20.
 */

public class AddVPresenter  extends PresenterImpl<AddVContract.View> implements
        AddVContract.Presenter{

        public AddVPresenter(AddVContract.View view) {
                super(view);
        }

        /**
         * 上传内容图片
         */
        private void uploadContentPic(final OrgPassData orgPassData) {
                //上传内容图片
                new BasePresenterSubscriber<UploadImageBean>(mView) {

                        @Override
                        public void childNext(UploadImageBean uploadImageBean) {
                                if (uploadImageBean.isSucceed()) {
                                        orgPassData.spotphotos =  uploadImageBean.urls;
                                        uploadData(orgPassData);
                                }else {
                                        mView.hideLoading(uploadImageBean.getErrmsg());
                                }
                        }

                }.run(RetrofitUtils.uploadFile(mView.getImgaeFiles()));

        }
        //上传沟通记录内容和通过加v认证
        private void uploadData(OrgPassData orgPassData){
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
        public void submit(OrgPassData orgPassData) {
                if (mView.getImgaeFiles() != null && mView.getImgaeFiles().length > 0){
                        uploadContentPic(orgPassData);
                }else {
                        uploadData(orgPassData);
                }
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
}
