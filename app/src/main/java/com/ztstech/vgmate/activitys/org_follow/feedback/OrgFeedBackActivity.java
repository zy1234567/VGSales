package com.ztstech.vgmate.activitys.org_follow.feedback;

import com.ztstech.appdomain.user_case.GetOrgFollow;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.org_follow.OrgFollowListFragment;
import com.ztstech.vgmate.base.BaseActivity;

/**
 * 机构反馈界面
 * @author smm
 * @date 2017/12/5
 */

public class OrgFeedBackActivity extends BaseActivity{

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        OrgFollowListFragment fragment = OrgFollowListFragment.newInstance();
        fragment.setIndexStatus(GetOrgFollow.STATUS_INDEX_FEEDBACK);
        getSupportFragmentManager().beginTransaction().add(R.id.linerlayout,fragment).commit();
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_org_feedback;
    }
}
