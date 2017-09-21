package com.ztstech.vgmate.activitys.org_detail;

import com.ztstech.vgmate.activitys.PresenterImpl;

/**
 * Created by zhiyuan on 2017/9/21.
 */

public class OrgDetailPresenter extends PresenterImpl<OrgDetailContract.View> implements
        OrgDetailContract.Presenter {

    public OrgDetailPresenter(OrgDetailContract.View view) {
        super(view);
    }
}
