package com.ztstech.vgmate.activitys.org_follow;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.OrgFollowNumBean;

/**
 * Created by smm on 2017/11/24.
 */

public class OrgFollowNumContact {

    interface View extends BaseView{
        void onGetFollowNumSucced(OrgFollowNumBean.InfoBean bean);
    }

    interface Presenter extends BasePresenter<View>{
        void loadFollowOrgNum();
    }

}
