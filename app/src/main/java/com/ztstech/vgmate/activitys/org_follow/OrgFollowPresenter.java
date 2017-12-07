package com.ztstech.vgmate.activitys.org_follow;

import com.ztstech.appdomain.user_case.GetOrgFollowNum;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.OrgFollowNumBean;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 *
 * @author smm
 * @date 2017/11/24
 */

public class OrgFollowPresenter extends PresenterImpl<OrgFollowContact.View> implements OrgFollowContact.Presenter{

    public OrgFollowPresenter(OrgFollowContact.View view) {
        super(view);
    }

    @Override
    public void loadFollowOrgNum() {
        new BasePresenterSubscriber<OrgFollowNumBean>(mView){

            @Override
            protected void childNext(OrgFollowNumBean orgFollowNumBean) {
                mView.onGetFollowNumSucced(orgFollowNumBean);
            }
        }.run(new GetOrgFollowNum().run());
    }
}
