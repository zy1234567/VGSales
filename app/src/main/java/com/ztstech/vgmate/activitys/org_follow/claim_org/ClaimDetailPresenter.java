package com.ztstech.vgmate.activitys.org_follow.claim_org;

import com.ztstech.appdomain.user_case.ApproveClaimOrg;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 *
 * @author smm
 * @date 2017/12/6
 */

public class ClaimDetailPresenter extends PresenterImpl<ClaimOrgDetailContact.View> implements ClaimOrgDetailContact.Presenter{

    public ClaimDetailPresenter(ClaimOrgDetailContact.View view) {
        super(view);
    }

    @Override
    public void approveOrg(String rbiid, String calid,String identtype,String status,String type,String yesorno,String testorg) {
        new BasePresenterSubscriber<BaseRespBean>(mView){
            @Override
            protected void childNext(BaseRespBean baseRespBean) {
                if (baseRespBean.isSucceed()){
                    mView.onApproveSuccess();
                }else {
                    mView.showError(baseRespBean.errmsg);
                }
            }

            @Override
            protected void childError(Throwable e) {
                mView.showError("审批出错:".concat(e.getMessage()));
            }
        }.run(new ApproveClaimOrg(rbiid,status,calid,identtype,type,yesorno,testorg).run());
    }
}
