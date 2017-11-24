package com.ztstech.vgmate.activitys.org_follow;

import com.ztstech.appdomain.user_case.GetOrgFollow;
import com.ztstech.appdomain.user_case.GetOrgFollowNum;
import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.OrgFollowNumBean;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 * Created by smm on 2017/11/24.
 */

public class OrgFollowNumPresenter extends PresenterImpl<OrgFollowNumContact.View> implements OrgFollowNumContact.Presenter{

    public OrgFollowNumPresenter(OrgFollowNumContact.View view) {
        super(view);
    }

    @Override
    public void loadFollowOrgNum() {
        new BasePresenterSubscriber<OrgFollowNumBean>(mView){

            @Override
            protected void childNext(OrgFollowNumBean orgFollowNumBean) {
                mView.onGetFollowNumSucced(orgFollowNumBean.info);
            }
        }.run(new GetOrgFollowNum().run());
    }
}
