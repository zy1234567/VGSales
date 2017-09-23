package com.ztstech.vgmate.activitys.org_list;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.GetOrgListCountBean;
import com.ztstech.vgmate.data.user_case.GetOrgListCount;
import com.ztstech.vgmate.utils.PresenterSubscriber;

/**
 * Created by zhiyuan on 2017/9/8.
 *
 */

public class OrglistPresenter extends PresenterImpl<OrgListContract.View> implements
        OrgListContract.Presenter {

    private GetOrgListCount getOrgListCount;

    private boolean isLoadingCount = false;

    public OrglistPresenter(OrgListContract.View view) {
        super(view);
    }

    @Override
    public void loadCount(String locId) {
        if (isLoadingCount) {
            return;
        }
        isLoadingCount = true;
        getOrgListCount = new GetOrgListCount(locId);
        new PresenterSubscriber<GetOrgListCountBean>(mView){

            @Override
            public void onNext(GetOrgListCountBean getOrgListCountBean) {
                mView.hideLoading(null);
                if (getOrgListCountBean.isSucceed()) {
                    mView.onLoadCountFinish(getOrgListCountBean, null);
                }else {
                    mView.onLoadCountFinish(null, getOrgListCountBean.getErrmsg());
                }
                isLoadingCount = false;
            }

        }.run(getOrgListCount.run());

    }
}
