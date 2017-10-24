package com.ztstech.vgmate.activitys.get_chance;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.CommunicationHistoryBean;
import com.ztstech.vgmate.data.user_case.GetCommunicateHistory;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 * Created by zhiyuan on 2017/8/25.
 */

public class GetChancePresenter extends PresenterImpl<GetChanceContract.View> implements
        GetChanceContract.Presenter {

    private int pageNo = 1;

    private int totalPage = 2;

    public GetChancePresenter(GetChanceContract.View view) {
        super(view);
    }

    @Override
    public void refreshData(String comid) {
        request(1, comid);
    }

    @Override
    public void loadData(String comid) {

    }

    private void request(int pageNo, String comid) {

        new BasePresenterSubscriber<CommunicationHistoryBean>(mView) {

            @Override
            protected void childNext(CommunicationHistoryBean baseRespBean) {

            }
        }.run(new GetCommunicateHistory(pageNo, comid).run());
    }
}
