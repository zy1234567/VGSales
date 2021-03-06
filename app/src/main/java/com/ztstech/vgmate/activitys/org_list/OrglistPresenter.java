package com.ztstech.vgmate.activitys.org_list;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.GetOrgListCountBean;
import com.ztstech.appdomain.repository.UserPreferenceManager;
import com.ztstech.appdomain.user_case.GetOrgListCount;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

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
        UserPreferenceManager.getInstance().saveUserSelectArea(locId);  //保存用户筛选的地址
        new BasePresenterSubscriber<GetOrgListCountBean>(mView){

            @Override
            public void childNext(GetOrgListCountBean getOrgListCountBean) {
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

    @Override
    public String getUserSelectedLocation() {
        return UserPreferenceManager.getInstance().getUserSelectArea();
    }
}
