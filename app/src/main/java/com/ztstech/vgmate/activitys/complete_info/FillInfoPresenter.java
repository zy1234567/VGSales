package com.ztstech.vgmate.activitys.complete_info;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.UpdateUserInfoBean;
import com.ztstech.vgmate.data.repository.UserRepository;
import com.ztstech.vgmate.mapper.UserInfoBeanMapper;
import com.ztstech.vgmate.model.fill_info.FillInfoModel;
import com.ztstech.vgmate.utils.PresenterSubscriber;

import rx.Emitter;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

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

        //// TODO: 2017/8/24 增加上传接口 
        UpdateUserInfoBean updateUserInfoBean =  new UserInfoBeanMapper().transform(model);

                new PresenterSubscriber<BaseRespBean>() {
                    @Override
                    public void onNext(BaseRespBean baseRespBean) {
                        mView.hideLoading(null);
                        if (baseRespBean.status == 0) {
                            mView.onSubmitSucceed();
                        }else {
                            mView.onSubmitFailed("提交失败");
                        }

                    }
                }.run(userRepository.updateUserInfo(updateUserInfoBean));
//            }
//        });


    }

    @Override
    public boolean isInfoFilled() {
        return false;
    }


}
