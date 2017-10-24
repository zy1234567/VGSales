package com.ztstech.vgmate.activitys.org_detail.dialog.org_delete;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.user_case.DeleteOrg;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 * Created by zhiyuan on 2017/10/24.
 * 删除组织
 */

public class OrgDeletePresenter extends PresenterImpl<OrgDeleteContract.View> implements
        OrgDeleteContract.Presenter {

    public OrgDeletePresenter(OrgDeleteContract.View view) {
        super(view);
    }

    @Override
    public void deleteOrg(String rbiid, String message) {
        new BasePresenterSubscriber<BaseRespBean>(mView) {

            @Override
            protected void childNext(BaseRespBean baseRespBean) {
                mView.onFinish(baseRespBean.getErrmsg());
            }
        }.run(new DeleteOrg(rbiid, message).run());
    }
}
