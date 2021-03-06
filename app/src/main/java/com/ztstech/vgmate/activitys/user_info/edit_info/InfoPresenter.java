package com.ztstech.vgmate.activitys.user_info.edit_info;

import android.content.SharedPreferences;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.user_case.ApproveMate;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.UnApproveMateBean;
import com.ztstech.vgmate.data.beans.UploadImageBean;
import com.ztstech.vgmate.data.beans.UserBean;
import com.ztstech.vgmate.data.dto.UpdateUserInfoData;
import com.ztstech.vgmate.mapper.FillInfoModelMapper;
import com.ztstech.vgmate.mapper.MateInfoModelMapper;
import com.ztstech.vgmate.mapper.UserInfoBeanMapper;
import com.ztstech.vgmate.model.fill_info.FillInfoModel;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

import java.io.File;

import rx.functions.Action1;

/**
 * Created by zhiyuan on 2017/9/8.
 */

public class InfoPresenter extends PresenterImpl<InfoContract.View> implements
        InfoContract.Presenter {

    private UserRepository userRepository;

    public InfoPresenter(InfoContract.View view) {
        super(view);
        userRepository = UserRepository.getInstance();
    }

    @Override
    public void loadUserModule() {
        UserBean userBean = userRepository.getUser().getUserBean();
        FillInfoModel model = new FillInfoModelMapper().transform(userBean);
        mView.setUserModule(model);

    }

    @Override
    public void loadMateModule(UnApproveMateBean bean) {
        FillInfoModel model = new MateInfoModelMapper().transform(bean);
        mView.setUserModule(model);
    }

    @Override
    public void saveInfo(final FillInfoModel model) {
        mView.showLoading(null);


        uploadIfExist(model.headerFile, new Action1<UploadImageBean>() {
            @Override
            public void call(final UploadImageBean uploadImageBean) {
                if (uploadImageBean == null || uploadImageBean.isSucceed()) {
                    //没有图片，或者上传成功
                    if (uploadImageBean != null && uploadImageBean.isSucceed()) {
                        model.headUrl = uploadImageBean.urls;
                        model.headsUrl = uploadImageBean.suourls;
                    }


                    uploadIfExist(model.cardFile, new Action1<UploadImageBean>() {
                        @Override
                        public void call(UploadImageBean uploadImageBean2) {
                            if (uploadImageBean2 == null || uploadImageBean2.isSucceed()) {
                                if (uploadImageBean2 != null && uploadImageBean2.isSucceed()) {
                                    model.cardUrl = uploadImageBean2.urls;
                                }


                                uploadCard(model.idFile, new Action1<UploadImageBean>() {
                                    @Override
                                    public void call(UploadImageBean uploadImageBean3) {
                                        if (uploadImageBean3 == null || uploadImageBean3.isSucceed()) {

                                            if (uploadImageBean3 != null && uploadImageBean3.isSucceed()) {
                                                model.idUrl = uploadImageBean3.urls;
                                            }

                                            uploadCard(model.idBackFile, new Action1<UploadImageBean>() {
                                                @Override
                                                public void call(UploadImageBean uploadImageBean4) {

                                                    if (uploadImageBean4 == null || uploadImageBean4.isSucceed()) {
                                                        if (uploadImageBean4 != null && uploadImageBean4.isSucceed()) {
                                                            model.idBackUrl = uploadImageBean4.urls;
                                                        }
                                                        //全部图片上传结束
                                                        UpdateUserInfoData updateUserInfoData =  new UserInfoBeanMapper().transform(model);

                                                        //上传资料
                                                        new BasePresenterSubscriber<BaseRespBean>(mView) {
                                                            @Override
                                                            public void childNext(BaseRespBean baseRespBean) {
                                                                mView.hideLoading(null);
                                                                if (baseRespBean.isSucceed()) {
                                                                    mView.onSubmitSucceed();
                                                                }else {
                                                                    mView.onSubmitFailed("提交失败");
                                                                }

                                                            }
                                                        }.run(userRepository.updateUserInfo(updateUserInfoData));

                                                    }else {
                                                        //上传图片失败
                                                        mView.hideLoading(null);
                                                        mView.onSubmitFailed("上传图片失败：" + uploadImageBean4.getErrmsg());

                                                    }


                                                }
                                            });


                                        }else {
                                            //上传图片失败
                                            mView.hideLoading(null);
                                            mView.onSubmitFailed("上传图片失败：" + uploadImageBean3.getErrmsg());
                                        }

                                    }
                                });


                            }else {
                                //上传图片失败
                                mView.hideLoading(null);
                                mView.onSubmitFailed("上传图片失败：" + uploadImageBean2.getErrmsg());
                            }
                        }
                    });

                }else {
                    //上传图片失败
                    mView.hideLoading(null);
                    mView.onSubmitFailed("上传图片失败：" + uploadImageBean.getErrmsg() );
                }
            }
        });


//        //上传图片，然后上传资料
//        File[] images = new File[] {model.headerFile, model.cardFile, model.idFile,
//                model.idBackFile};


//        new BasePresenterSubscriber<UploadImageBean>(mView){
//
//            @Override
//            public void onNext(UploadImageBean baseRespBean) {
//
//                //上传图片成功上传数据
//                Log.d("", "" + baseRespBean);
//                if (baseRespBean.isSucceed()) {
//                    //上传成功
//                    String[] urls = baseRespBean.urls.split(",");
//
//                    if (urls.length != 4) {
//                        mView.onSubmitFailed("服务器返回数据异常");
//                    }else {
//                        UpdateUserInfoData updateUserInfoData =  new UserInfoBeanMapper().transform(model);
//                        updateUserInfoData.picurl = urls[0];
//                        updateUserInfoData.cardUrl = urls[1];
//                        updateUserInfoData.didurl = new String[] {urls[2], urls[3]};
//
//                        //上传资料
//                        new BasePresenterSubscriber<BaseRespBean>() {
//                            @Override
//                            public void onNext(BaseRespBean baseRespBean) {
//                                mView.hideLoading(null);
//                                if (baseRespBean.status == Constants.STATUS_SUCCEED) {
//                                    mView.onSubmitSucceed();
//                                }else {
//                                    mView.onSubmitFailed("提交失败");
//                                }
//
//                            }
//                        }.run(userRepository.updateUserInfo(updateUserInfoData));
//
//                    }
//
//                }else {
//                    mView.onSubmitFailed("上传图片失败：" + baseRespBean.errmsg);
//                }
//
//            }
//        }.run(RetrofitUtils.uploadFile(images));
    }

    @Override
    public void approveMate(String uid, String status) {
        new BasePresenterSubscriber<BaseRespBean>(mView) {

            @Override
            protected void childError(Throwable e) {
                mView.showError("审批销售出错：".concat(e.getMessage()));
            }

            @Override
            public void childNext(BaseRespBean bean) {
                if (bean.isSucceed()) {
                    mView.onApproveSucceed();
                }else {
                    //如果失败
                    mView.showError(bean.getErrmsg());
                }
            }
        }.run(new ApproveMate(uid,status).run());
    }


    private void uploadIfExist(File file,final Action1<UploadImageBean> callback) {
        if (file != null) {
            new BasePresenterSubscriber<UploadImageBean>(mView){

                @Override
                public void childNext(UploadImageBean uploadImageBean) {

                    callback.call(uploadImageBean);
                }
            }.run(RetrofitUtils.uploadFile(new File[]{file}));

        }else {
            callback.call(null);
        }
    }
    private void uploadCard(File file,final Action1<UploadImageBean> callback){
        if (file != null) {
            new BasePresenterSubscriber<UploadImageBean>(mView){

                @Override
                public void childNext(UploadImageBean uploadImageBean) {

                    callback.call(uploadImageBean);
                }
            }.run(RetrofitUtils.uploadCard(new File[]{file}));

        }else {
            callback.call(null);
        }
    }
}
