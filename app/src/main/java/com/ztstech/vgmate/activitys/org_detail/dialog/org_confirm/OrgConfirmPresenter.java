package com.ztstech.vgmate.activitys.org_detail.dialog.org_confirm;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.user_case.ConfirmOrgPass;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 * Created by zhiyuan on 2017/10/10.
 */

public class OrgConfirmPresenter extends PresenterImpl<OrgConfirmContract.View> implements
        OrgConfirmContract.Presenter {

    public OrgConfirmPresenter(OrgConfirmContract.View view) {
        super(view);
    }


    @Override
    public void submit(String rbiid, String oname, String otype, String district, String address,
                       String gps, String contphone) {
        new BasePresenterSubscriber<BaseRespBean>(mView) {

            @Override
            protected void childNext(BaseRespBean baseRespBean) {
                mView.onFinish(baseRespBean.getErrmsg());
            }

        }.run(new ConfirmOrgPass(rbiid, oname, otype, district, address, gps, contphone).run());
    }
}
