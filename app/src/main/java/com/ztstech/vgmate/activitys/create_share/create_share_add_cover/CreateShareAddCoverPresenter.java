package com.ztstech.vgmate.activitys.create_share.create_share_add_cover;

import android.text.TextUtils;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.dto.CreateShareData;
import com.ztstech.vgmate.data.beans.UploadImageBean;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

import java.io.File;

import rx.Observable;

/**
 * Created by zhiyuan on 2017/9/12.
 */

public class CreateShareAddCoverPresenter extends PresenterImpl<CreateShareAddCoverContract.View>
        implements CreateShareAddCoverContract.Presenter {

    public CreateShareAddCoverPresenter(CreateShareAddCoverContract.View view) {
        super(view);
    }

    @Override
    public void submit(final CreateShareData createShareData) {
        mView.showLoading("请稍等");

        new BasePresenterSubscriber<UploadImageBean>(mView) {

            @Override
            public void childNext(UploadImageBean uploadImageBean) {
                //上传头像结束
                if (uploadImageBean == null || uploadImageBean.isSucceed()) {
                    //如果没有上传头像或者上传成功
                    if (uploadImageBean != null) {
                        createShareData.picurl = uploadImageBean.urls;
                        createShareData.picsurl = uploadImageBean.suourls;
                    }

                    if (createShareData.contentpicfiles != null &&
                            createShareData.contentpicfiles.length > 0) {
                        uploadContentPic(createShareData);
                    }else {
                        uploadData(createShareData);
                    }

                }else {
                    mView.hideLoading(uploadImageBean.getErrmsg());
                }

            }

            @Override
            protected void childError(Throwable e) {
                mView.hideLoading(e.getMessage());
            }
        }.run(RetrofitUtils.uploadIfExist(new File[] {createShareData.headFile}));

    }


    /**
     * 上传内容图片
     * @param createShareData
     */
    private void uploadContentPic(final CreateShareData createShareData) {
        //上传内容图片
        new BasePresenterSubscriber<UploadImageBean>(mView) {

            @Override
            public void childNext(UploadImageBean uploadImageBean) {
                if (uploadImageBean.isSucceed()) {
                    //如果已经存在线上网址，拼接
                    if (TextUtils.isEmpty(createShareData.contentpicurl)) {
                        createShareData.contentpicurl = uploadImageBean.urls;
                    }else {
                        //如果原本不为空
                        createShareData.contentpicurl = uploadImageBean.urls
                                .concat(",")
                                .concat(createShareData.contentpicurl);
                    }
                    if (TextUtils.isEmpty(createShareData.contentpicsurl)) {
                        createShareData.contentpicsurl = uploadImageBean.suourls;
                    }else {
                        createShareData.contentpicsurl = uploadImageBean.suourls
                                .concat(",")
                                .concat(createShareData.contentpicsurl);
                    }

                    uploadData(createShareData);


                }else {
                    mView.hideLoading(uploadImageBean.getErrmsg());
                }
            }

        }.run(RetrofitUtils.uploadFile(createShareData.contentpicfiles));

    }


    /**
     * 上传数据
     * @param createShareData
     */
    private void uploadData(CreateShareData createShareData) {
        //上传数据
        new BasePresenterSubscriber<BaseRespBean>(mView) {

            @Override
            public void childNext(BaseRespBean baseRespBean) {
                mView.submitFinish(baseRespBean.getErrmsg());
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                mView.hideLoading(null);
            }

        }.run(RetrofitUtils.createShare(createShareData));
    }
}
