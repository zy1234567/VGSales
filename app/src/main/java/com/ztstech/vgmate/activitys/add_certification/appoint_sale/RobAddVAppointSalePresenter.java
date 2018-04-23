package com.ztstech.vgmate.activitys.add_certification.appoint_sale;

import android.text.TextUtils;

import com.ztstech.appdomain.user_case.OrgPass;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.UploadImageBean;
import com.ztstech.vgmate.data.dto.AddVAppointSaleData;
import com.ztstech.vgmate.data.dto.OrgPassData;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

import java.awt.font.TextAttribute;

/**
 * Created by Administrator on 2018/4/23.
 */

public class RobAddVAppointSalePresenter extends PresenterImpl<RobAddVAppointSaleContract.View>
        implements RobAddVAppointSaleContract.Presenter {
    public static  String IMG_VIDIO="img_vidio";
    public static String IMG_LOCATION="img_location";
    public static String IMG_POSTION="img_position";
    public RobAddVAppointSalePresenter(RobAddVAppointSaleContract.View view) {
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
                        orgPassData.spotphotos=uploadImageBean.urls;
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
    public void uploadimg(final OrgPassData orgPassData, final int type) {
        new BasePresenterSubscriber<UploadImageBean>(mView) {

            @Override
            public void childNext(UploadImageBean uploadImageBean) {
                if (uploadImageBean.isSucceed()) {
                    if(type == 1) {
                        orgPassData.videopicurl= uploadImageBean.urls;
                    }else if (type == 0){
                        orgPassData.positionpicurl=uploadImageBean.urls;
                    }
                }else {
                    mView.hideLoading(uploadImageBean.getErrmsg());
                }
            }

        }.run(RetrofitUtils.uploadFile(mView.getImgaeFiles()));

    }
}
