package com.ztstech.vgmate.activitys.create_share_add_cover;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.CreateShareBean;
import com.ztstech.vgmate.data.beans.UploadImageBean;
import com.ztstech.vgmate.data.utils.RetrofitUtils;
import com.ztstech.vgmate.utils.PresenterSubscriber;

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
    public void submit(final CreateShareBean createShareBean) {
        mView.showLoading("请稍等");
        new PresenterSubscriber<UploadImageBean>() {

            @Override
            public void onNext(UploadImageBean uploadImageBean) {
                //上传头像结束
                if (uploadImageBean.isSucceed()) {
                    createShareBean.picurl = uploadImageBean.urls;
                    createShareBean.picsurl = uploadImageBean.suourls;

                    //上传内容图片
                    new PresenterSubscriber<UploadImageBean>() {

                        @Override
                        public void onNext(UploadImageBean uploadImageBean) {
                            if (uploadImageBean.isSucceed()) {
                                createShareBean.picsurl = uploadImageBean.suourls;
                                createShareBean.picurl = uploadImageBean.urls;


                                //上传数据
                                new PresenterSubscriber<BaseRespBean>() {

                                    @Override
                                    public void onNext(BaseRespBean baseRespBean) {
                                        mView.submitFinish(baseRespBean.getErrmsg());
                                    }

                                    @Override
                                    public void onCompleted() {
                                        super.onCompleted();
                                        mView.hideLoading(null);
                                    }

                                }.run(RetrofitUtils.createShare(createShareBean));


                            }else {
                                mView.hideLoading(uploadImageBean.getErrmsg());
                            }
                        }

                    }.run(RetrofitUtils.uploadFile(createShareBean.contentpicfiles));


                }else {
                    mView.hideLoading(uploadImageBean.getErrmsg());
                }

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.hideLoading(e.getMessage());
            }
        }.run(RetrofitUtils.uploadFile(new File[] {createShareBean.headFile}));
//        Observable<UploadImageBean> uploadHeadObs = null;
//        Observable<UploadImageBean> uploadContentObs = null;
//        if (createShareBean.headFile != null) {
//            //如果分享图片不为空
//            uploadHeadObs = RetrofitUtils.uploadFile(new File[]
//                    {createShareBean.headFile});
//        }
//        if (createShareBean.contentpicfiles != null && createShareBean.contentpicfiles.length > 0) {
//            uploadContentObs = RetrofitUtils.uploadFile(createShareBean.contentpicfiles);
//        }
//        boolean marged = false;
//        Observable<UploadImageBean> uploadImageBeanObservable = null;
//        if (uploadContentObs != null) {
//            if (uploadHeadObs != null) {
//                //如果两者都不为空
//                uploadImageBeanObservable = Observable.concat(uploadHeadObs, uploadContentObs);
//            }else {
//                //入果后者为空
//                uploadImageBeanObservable = uploadContentObs;
//            }
//        }
//        if (uploadImageBeanObservable != null) {
//            new PresenterSubscriber<UploadImageBean>(){
//                @Override
//                public void onNext(UploadImageBean uploadImageBean) {
//                    //上传图片成功
//                    if (uploadImageBean.isSucceed()) {
//
//                    }
//                }
//
//                @Override
//                public void onCompleted() {
//                    super.onCompleted();
//
//                }
//            }.run(uploadImageBeanObservable);
//        }
    }
}
