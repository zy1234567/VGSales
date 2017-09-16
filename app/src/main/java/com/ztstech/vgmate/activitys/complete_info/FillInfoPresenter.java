package com.ztstech.vgmate.activitys.complete_info;

import android.util.Log;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.constants.Constants;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.dto.UpdateUserInfoData;
import com.ztstech.vgmate.data.beans.UploadImageBean;
import com.ztstech.vgmate.data.repository.UserRepository;
import com.ztstech.vgmate.data.utils.RetrofitUtils;
import com.ztstech.vgmate.mapper.UserInfoBeanMapper;
import com.ztstech.vgmate.model.fill_info.FillInfoModel;
import com.ztstech.vgmate.utils.PresenterSubscriber;

import java.io.File;

/**
 * Created by zhiyuan on 2017/8/17.
 */

public class FillInfoPresenter extends PresenterImpl<FillInfoContract.View> implements
        FillInfoContract.Presenter {

    private UserRepository userRepository;

    public FillInfoPresenter(FillInfoContract.View view) {
        super(view);
        userRepository = UserRepository.getInstance();
    }

    @Override
    public void saveInfo(final FillInfoModel model) {
        mView.showLoading(null);

        //上传图片，然后上传资料
        File[] images = new File[] {model.headerFile, model.cardFile, model.idFile,
                model.idBackFile};
        new PresenterSubscriber<UploadImageBean>(mView){

            @Override
            public void onNext(UploadImageBean baseRespBean) {

                //上传图片成功上传数据
                Log.d("", "" + baseRespBean);
                if ("0".equals(baseRespBean.status)) {
                    //上传成功
                    String[] urls = baseRespBean.urls.split(",");

                    if (urls.length != 4) {
                        mView.onSubmitFailed("服务器返回数据异常");
                    }else {
                        UpdateUserInfoData updateUserInfoData =  new UserInfoBeanMapper().transform(model);
                        updateUserInfoData.picurl = urls[0];
                        updateUserInfoData.cardUrl = urls[1];
                        updateUserInfoData.didurl = new String[] {urls[2], urls[3]};

                        //上传资料
                        new PresenterSubscriber<BaseRespBean>() {
                            @Override
                            public void onNext(BaseRespBean baseRespBean) {
                                mView.hideLoading(null);
                                if (baseRespBean.status == Constants.STATUS_SUCCEED) {
                                    mView.onSubmitSucceed();
                                }else {
                                    mView.onSubmitFailed("提交失败");
                                }

                            }
                        }.run(userRepository.updateUserInfo(updateUserInfoData));

                    }

                }else {
                    mView.onSubmitFailed("上传图片失败：" + baseRespBean.errmsg);
                }

            }
        }.run(RetrofitUtils.uploadFile(images));
    }

    @Override
    public boolean isInfoFilled() {
        return false;
    }

    /**
     * 上传图片
     */
    private void uploadImage() {

    }


}
