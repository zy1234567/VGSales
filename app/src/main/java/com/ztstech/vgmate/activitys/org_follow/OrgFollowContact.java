package com.ztstech.vgmate.activitys.org_follow;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.OrgFollowNumBean;

/**
 * Created by smm on 2017/11/24.
 */

public class OrgFollowContact {

    interface View extends BaseView{
        void onGetFollowNumSucced(OrgFollowNumBean bean);
    }

    interface Presenter extends BasePresenter<View>{
        void loadFollowOrgNum(String uid);
    }

}
