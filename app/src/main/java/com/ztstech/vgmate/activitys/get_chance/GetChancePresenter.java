package com.ztstech.vgmate.activitys.get_chance;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.CommunicationHistoryBean;
import com.ztstech.vgmate.data.user_case.AddCommunicateByComid;
import com.ztstech.vgmate.data.user_case.AddCommunicateByRbiid;
import com.ztstech.vgmate.data.user_case.GetCommunicateHistory;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiyuan on 2017/8/25.
 */

public class GetChancePresenter extends PresenterImpl<GetChanceContract.View> implements
        GetChanceContract.Presenter {

    private int pageNo = 1;

    private int totalPage = 2;

    private List<CommunicationHistoryBean.ListBean> listItems = new ArrayList<>();

    public GetChancePresenter(GetChanceContract.View view) {
        super(view);
    }

    @Override
    public void refreshData(String comid, String rbiid) {
        request(1, comid, rbiid);
    }

    @Override
    public void loadData(String comid, String rbiid) {
        if (pageNo >= totalPage) {

        }else {
            request(pageNo + 1, comid, rbiid);
        }
    }

    @Override
    public void addCommunicateByRbiid(String rbiid, String msg) {
        new BasePresenterSubscriber<BaseRespBean>(mView) {

            @Override
            protected void childNext(BaseRespBean baseRespBean) {
                mView.onAddCommunicateFinish(baseRespBean.getErrmsg());
            }

        }.run(new AddCommunicateByRbiid(rbiid, msg).run());
    }

    @Override
    public void addCommunicateByComid(String status, String orgin, String comid, String msg) {
        new BasePresenterSubscriber<BaseRespBean>(mView) {

            @Override
            protected void childNext(BaseRespBean baseRespBean) {
                mView.onAddCommunicateFinish(baseRespBean.getErrmsg());
            }
        }.run(new AddCommunicateByComid(msg, orgin, comid, status).run());
    }


    private void request(final int page, String comid, String rbiid) {

        new BasePresenterSubscriber<CommunicationHistoryBean>(mView) {


            @Override
            protected void childError(Throwable e) {
                if (page == 1) {
                    mView.onRefreshFinish(listItems, "" + e.getLocalizedMessage());
                }else {
                    mView.onLoadFinish(listItems, "" + e.getLocalizedMessage());
                }
            }

            @Override
            protected void childNext(CommunicationHistoryBean baseRespBean) {
                if (baseRespBean.isSucceed()) {
                    pageNo = baseRespBean.pager.currentPage;
                    totalPage = baseRespBean.pager.totalPages;
                    if (pageNo == 1) {
                        listItems.clear();
                    }
                    listItems.addAll(baseRespBean.list);
                    if (pageNo == 1) {
                        mView.onRefreshFinish(listItems, null);
                    }else {
                        mView.onLoadFinish(listItems, null);
                    }
                }else {
                    if (page == 1) {
                        //停止刷新
                        mView.onRefreshFinish(listItems, baseRespBean.getErrmsg());
                    }else {
                        mView.onLoadFinish(listItems, baseRespBean.getErrmsg());
                    }
                }
            }

        }.run(new GetCommunicateHistory(page, comid, rbiid).run());
    }
}
