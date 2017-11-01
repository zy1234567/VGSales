package com.ztstech.vgmate.activitys.self_organization;

import com.ztstech.vgmate.activitys.PresenterImpl;

/**
 * Created by zhiyuan on 2017/8/26.
 */

public class SelfOrganizationPresenter extends PresenterImpl<SelfOrganizationContract.View>
        implements SelfOrganizationContract.Presenter {

    public SelfOrganizationPresenter(SelfOrganizationContract.View view) {
        super(view);
    }
}
