package com.ztstech.vgmate.activitys.create_share_add_cover;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.dto.CreateShareData;
import com.ztstech.vgmate.data.beans.UploadImageBean;
import com.ztstech.vgmate.data.utils.RetrofitUtils;
import com.ztstech.vgmate.utils.PresenterSubscriber;

import java.io.File;

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
        new PresenterSubscriber<UploadImageBean>(mView) {

            @Override
            public void childNext(UploadImageBean uploadImageBean) {
                //上传头像结束
                if (uploadImageBean.isSucceed()) {
                    createShareData.picurl = uploadImageBean.urls;
                    createShareData.picsurl = uploadImageBean.suourls;

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
                super.onError(e);
                mView.hideLoading(e.getMessage());
            }
        }.run(RetrofitUtils.uploadFile(new File[] {createShareData.headFile}));

    }

    /**
     * 上传内容图片
     * @param createShareData
     */
    private void uploadContentPic(final CreateShareData createShareData) {
        //上传内容图片
        new PresenterSubscriber<UploadImageBean>(mView) {

            @Override
            public void childNext(UploadImageBean uploadImageBean) {
                if (uploadImageBean.isSucceed()) {
                    createShareData.contentpicurl = uploadImageBean.urls;
                    createShareData.contentpicsurl = uploadImageBean.suourls;


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
        new PresenterSubscriber<BaseRespBean>(mView) {

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
