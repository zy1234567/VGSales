package com.ztstech.vgmate.activitys.setting.send_code;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.UserBean;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 * Created by zhiyuan on 2017/10/28.
 *
 */

public class ChangePhoneSendCodePresenter extends PresenterImpl<ChangePhoneSendCodeContract.View>
        implements ChangePhoneSendCodeContract.Presenter {

    private int seconds = 60;

    public ChangePhoneSendCodePresenter(ChangePhoneSendCodeContract.View view) {
        super(view);
    }

    @Override
    public void sendCode(String phone) {
        new BasePresenterSubscriber<BaseRespBean>(mView) {

            @Override
            protected void childNext(BaseRespBean baseRespBean) {
                mView.onSendCodeFinish(baseRespBean.getErrmsg());
            }
        }.run(UserRepository.getInstance().sendChangePhoneCode(phone));
    }

    @Override
    public void checkCode(String phone, final String code) {
        new BasePresenterSubscriber<UserBean>(mView) {

            @Override
            protected void childNext(UserBean userBean) {
                mView.onCheckCodeFinish(userBean.getErrmsg(), code);
            }
        }.run(UserRepository.getInstance().checkChangePhoneCode(phone, code));
    }
}
