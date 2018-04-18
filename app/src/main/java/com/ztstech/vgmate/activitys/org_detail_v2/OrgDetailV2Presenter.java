package com.ztstech.vgmate.activitys.org_detail_v2;

import com.ztstech.appdomain.user_case.RequestUploadProtocol;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.activitys.org_follow.OrgFollowListContact;
import com.ztstech.vgmate.data.dto.UploadProtocolData;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 * Created by dongdong on 2018/3/27.
 */

public class OrgDetailV2Presenter extends PresenterImpl<OrgDetailV2Contract.View> implements OrgDetailV2Contract.Presenter {
    public OrgDetailV2Presenter(OrgDetailV2Contract.View view) {
        super(view);
    }

    @Override
    public void loadData(String orgid) {
        requestdata(orgid);
    }
    //请求数据
    private void requestdata(String orgid) {
        new BasePresenterSubscriber<UploadProtocolData>(mView, false) {
            @Override
            protected void childNext(UploadProtocolData uploadProtocolData) {
                if (uploadProtocolData.isSucceed()) {
                    mView.setData(uploadProtocolData);
                }
            }
        }.run(new RequestUploadProtocol(orgid).run());
    }
}
