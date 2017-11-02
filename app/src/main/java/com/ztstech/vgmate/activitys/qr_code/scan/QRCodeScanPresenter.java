package com.ztstech.vgmate.activitys.qr_code.scan;

import com.ztstech.appdomain.user_case.CheckLoginWebUUID;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 * Created by zhiyuan on 2017/11/2.
 */

public class QRCodeScanPresenter extends PresenterImpl<QRCodeScanContract.View> implements
        QRCodeScanContract.Presenter {

    public QRCodeScanPresenter(QRCodeScanContract.View view) {
        super(view);
    }

    @Override
    public void checkUUID(final String uuid) {
        new BasePresenterSubscriber<BaseRespBean>(mView) {

            @Override
            protected void childNext(BaseRespBean baseRespBean) {
                mView.checkUUIDFinish(uuid, baseRespBean.getErrmsg());
            }
        }.run(new CheckLoginWebUUID(uuid).run());
    }
}

