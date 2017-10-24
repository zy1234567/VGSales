package com.ztstech.vgmate.activitys.complete_org_info_v2;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.OrgInfoBean;
import com.ztstech.vgmate.data.user_case.GetOrgInfo;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 * Created by zhiyuan on 2017/10/9.
 */

public class CompleteOrgInfoV2Presenter extends PresenterImpl<CompleteOrgInfoV2Contract.View>
        implements CompleteOrgInfoV2Contract.Presenter {

    public CompleteOrgInfoV2Presenter(CompleteOrgInfoV2Contract.View view) {
        super(view);
    }

    @Override
    public void loadOrgInfo(int rbiid) {
        new BasePresenterSubscriber<OrgInfoBean>(mView){

            @Override
            public void childNext(OrgInfoBean orgInfoBean) {
                if (orgInfoBean.isSucceed()) {
                    mView.showOrgInfo(orgInfoBean.info);
                }else {
                    mView.onLoadOrgInfoError(orgInfoBean.getErrmsg());
                }
            }

        }.run(new GetOrgInfo(rbiid).run());
    }

    @Override
    public void editOrgInfo(OrgInfoBean.InfoBean bean) {

    }
}
