package com.ztstech.vgmate.activitys.setting.change_phone;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.UserBean;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 * Created by zhiyuan on 2017/10/28.
 *
 */

public class ChangePhonePresenter extends PresenterImpl<ChangePhoneContract.View> implements
        ChangePhoneContract.Presenter {

    public ChangePhonePresenter(ChangePhoneContract.View view) {
        super(view);
    }


    @Override
    public void submit(String code, String phone, final String newPhone) {
        new BasePresenterSubscriber<UserBean>(mView) {

            @Override
            protected void childNext(UserBean userBean) {
                mView.onSubmitFinish(userBean.getErrmsg(), newPhone);
            }
        }.run(UserRepository.getInstance().updatePhone(phone, code, newPhone));
    }
}
