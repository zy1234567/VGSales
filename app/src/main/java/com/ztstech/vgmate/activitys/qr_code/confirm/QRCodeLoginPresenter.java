package com.ztstech.vgmate.activitys.qr_code.confirm;

import com.ztstech.appdomain.user_case.LoginWeb;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 * Created by zhiyuan on 2017/11/2.
 */

public class QRCodeLoginPresenter extends PresenterImpl<QRCodeLoginContract.View> implements
        QRCodeLoginContract.Presenter {

    public QRCodeLoginPresenter(QRCodeLoginContract.View view) {
        super(view);
    }


    @Override
    public void login(String uuid) {
        new BasePresenterSubscriber<BaseRespBean>(mView) {

            @Override
            protected void childNext(BaseRespBean baseRespBean) {
                mView.loginFinish(baseRespBean.getErrmsg());
            }

        }.run(new LoginWeb(uuid).run());
    }
}
