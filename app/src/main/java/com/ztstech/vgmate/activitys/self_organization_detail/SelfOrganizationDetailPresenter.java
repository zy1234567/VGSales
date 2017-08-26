package com.ztstech.vgmate.activitys.self_organization_detail;

import com.ztstech.vgmate.activitys.PresenterImpl;

/**
 * Created by zhiyuan on 2017/8/26.
 */

public class SelfOrganizationDetailPresenter extends
        PresenterImpl<SelfOrganizationDetailContract.View> implements
        SelfOrganizationDetailContract.Presenter {
    public SelfOrganizationDetailPresenter(SelfOrganizationDetailContract.View view) {
        super(view);
    }
}
