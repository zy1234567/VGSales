package com.ztstech.vgmate.activitys.setting;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.repository.UserRepository;
import com.ztstech.vgmate.utils.PresenterSubscriber;

/**
 * Created by zhiyuan on 2017/9/9.
 *
 */

public class SettingPresenter extends PresenterImpl<SettingContract.View> implements
        SettingContract.Presenter {


    private UserRepository userRepository;

    public SettingPresenter(SettingContract.View view) {
        super(view);
        userRepository = UserRepository.getInstance();
    }

    @Override
    public void logout() {
        new PresenterSubscriber<BaseRespBean>(mView) {

            @Override
            public void childNext(BaseRespBean baseRespBean) {
                mView.hideLoading(null);
                if (baseRespBean.isSucceed()) {
                    mView.onLogoutFinish(null);
                }else {
                    mView.onLogoutFinish(baseRespBean.getErrmsg());
                }
            }
        }.run(userRepository.logout());
    }

    @Override
    public String getPhone() {
        return userRepository.getUser().info.phone;
    }
}
