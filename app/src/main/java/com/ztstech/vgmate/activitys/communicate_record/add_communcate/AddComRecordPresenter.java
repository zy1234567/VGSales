package com.ztstech.vgmate.activitys.communicate_record.add_communcate;

import com.ztstech.appdomain.user_case.AddComRecord;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.UploadImageBean;
import com.ztstech.vgmate.data.dto.AddComRecordData;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 * Created by zhangruidong on 2018/1/15.
 */

public class AddComRecordPresenter  extends PresenterImpl<AddComRecordContact.View> implements
        AddComRecordContact.Presenter {
    public AddComRecordPresenter(AddComRecordContact.View view) {
        super(view);
    }

    public void submitdata(AddComRecordData addComRecordData) {
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
        }.run(new AddComRecord(addComRecordData).run());
    }
    /**
     * 上传内容图片
     */
    private void uploadContentPic(final AddComRecordData addComRecordData) {
        //上传内容图片
        new BasePresenterSubscriber<UploadImageBean>(mView) {

            @Override
            public void childNext(UploadImageBean uploadImageBean) {
                if (uploadImageBean.isSucceed()) {
                    addComRecordData.spotphotos =  uploadImageBean.urls;
                    submitdata(addComRecordData);
                }else {
                    mView.hideLoading(uploadImageBean.getErrmsg());
                }
            }

        }.run(RetrofitUtils.uploadFile(mView.getImgaeFiles()));

    }

    @Override
    public void submit(AddComRecordData addComRecordData) {
        if (mView.getImgaeFiles() != null && mView.getImgaeFiles().length > 0){
            uploadContentPic(addComRecordData);
        }else {
            submitdata(addComRecordData);
        }
    }
}
