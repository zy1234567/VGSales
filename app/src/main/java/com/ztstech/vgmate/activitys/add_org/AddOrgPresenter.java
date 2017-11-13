package com.ztstech.vgmate.activitys.add_org;

import com.ztstech.appdomain.user_case.AddOrg;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.dto.AddOrgData;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 * Created by zhiyuan on 2017/9/27.
 */

public class AddOrgPresenter extends PresenterImpl<AddOrgContract.View> implements
        AddOrgContract.Presenter{

    public AddOrgPresenter(AddOrgContract.View view) {
        super(view);

    }

    @Override
    public void commit(AddOrgData data) {
        mView.showLoading("请稍等");
        new BasePresenterSubscriber<BaseRespBean>(mView) {

            @Override
            public void childNext(BaseRespBean baseRespBean) {
                mView.hideLoading(null);

                if (baseRespBean.isSucceed()) {
                    //防止后台在正确情况下返回errmsg
                    mView.onSubmitFinish(null);
                }else {
                    mView.onSubmitFinish(baseRespBean.getErrmsg());
                }

            }
        }.run(new AddOrg(data).run());
    }
}
