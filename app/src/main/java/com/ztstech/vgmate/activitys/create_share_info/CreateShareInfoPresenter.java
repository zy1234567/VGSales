package com.ztstech.vgmate.activitys.create_share_info;

import com.ztstech.vgmate.activitys.PresenterImpl;

/**
 * Created by zhiyuan on 2017/9/12.
 */

public class CreateShareInfoPresenter extends PresenterImpl<CreateShareInfoContract.View> implements
        CreateShareInfoContract.Presenter {

    public CreateShareInfoPresenter(CreateShareInfoContract.View view) {
        super(view);
    }

//    @Override
//    public void submit(CreateShareData createShareBean) {
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
//            new BasePresenterSubscriber<UploadImageBean>(){
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
//
//    }



}
