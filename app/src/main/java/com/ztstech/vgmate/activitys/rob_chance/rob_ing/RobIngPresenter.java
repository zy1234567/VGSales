package com.ztstech.vgmate.activitys.rob_chance.rob_ing;

import com.ztstech.appdomain.constants.Constants;
import com.ztstech.appdomain.user_case.RefuseOrPassReason;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.dto.RefuseOrPassData;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 * Created by dongdong on 2018/4/19.
 */

public class RobIngPresenter extends PresenterImpl<RobIngContract.View>
        implements RobIngContract.Presenter {
    public RobIngPresenter(RobIngContract.View view) {
        super(view);
    }
    @Override
    public void refuse0rPassCommit(RefuseOrPassData refuseCalimData,int type) {
        new BasePresenterSubscriber<BaseRespBean>(mView){
            @Override
            protected void childNext(BaseRespBean baseRespBean) {
                if (baseRespBean.isSucceed()){
                   mView.Success();
                   mView.showError(baseRespBean.errmsg);
                }
            }

            @Override
            protected void childError(Throwable e) {
                mView.showError("出错:".concat(e.getMessage()));
            }
        }.run(new RefuseOrPassReason(refuseCalimData, Constants.NORMAL_REGISTER).run());
    }



    @Override
    public void refuseRegisterCommit(RefuseOrPassData orgRegisterRefuseData,int type) {
        new BasePresenterSubscriber<BaseRespBean>(mView){
            @Override
            protected void childNext(BaseRespBean baseRespBean) {
                if (baseRespBean.isSucceed()){
                    mView.Success();
                    mView.showError(baseRespBean.errmsg);
                }
            }

            @Override
            protected void childError(Throwable e) {
                mView.showError("出错:".concat(e.getMessage()));
            }
        }.run(new RefuseOrPassReason(orgRegisterRefuseData,type).run());
    }
}
