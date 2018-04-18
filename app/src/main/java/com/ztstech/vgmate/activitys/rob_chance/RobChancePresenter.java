package com.ztstech.vgmate.activitys.rob_chance;

import com.ztstech.appdomain.user_case.RobChance;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.GetComRecordBean;
import com.ztstech.vgmate.data.beans.RobChanceBean;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongdong on 2018/4/18.
 */

public class RobChancePresenter extends PresenterImpl<RobChanceContract.View> implements RobChanceContract.Presenter {
    private int currentpage = 1,totalpage;
    private List<RobChanceBean.ListBean> listBean = new ArrayList<>();
    public RobChancePresenter(RobChanceContract.View view) {
        super(view);
    }

    @Override
    public void loadData() {
        currentpage = 1;
        requestData();
    }

    @Override
    public void appendData() {

    }
    private void requestData(){
        new BasePresenterSubscriber<RobChanceBean>(mView,false){

            @Override
            protected void childNext(RobChanceBean getComRecordBean) {
                if (getComRecordBean.isSucceed()) {
                    currentpage = getComRecordBean.pager.currentPage;
                    totalpage = getComRecordBean.pager.totalPages;

                    if (currentpage == 1) {
                        //刷新
                        listBean.clear();
                    }
                    listBean.addAll(getComRecordBean.list);
                    mView.setData(listBean);
                    mView.setDataPage(getComRecordBean.pager);
                }else {
                    //如果失败
                    mView.showError(getComRecordBean.getErrmsg());
                }
            }
        }.run(new RobChance(currentpage).run());
    }
}
