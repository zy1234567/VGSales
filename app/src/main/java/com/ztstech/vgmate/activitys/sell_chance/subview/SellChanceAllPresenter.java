package com.ztstech.vgmate.activitys.sell_chance.subview;

import android.os.Handler;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.SellChanceBean;
import com.ztstech.vgmate.data.user_case.GetSellChance;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiyuan on 2017/8/24.
 * 提供销售机会界面presenter
 */

public class SellChanceAllPresenter extends PresenterImpl<SellChanceAllContract.View> implements
        SellChanceAllContract.Presenter {

    private int currentPage = 1;
    private int totalPage = 2;

    private Handler handler = new Handler();

    private List<SellChanceBean.ListBean> listData = new ArrayList<>();

    public SellChanceAllPresenter(SellChanceAllContract.View view) {
        super(view);
    }


    @Override
    public void refreshData(String status) {
        loadData(1, status);
    }

    @Override
    public void appendData(String status) {
        if (currentPage < totalPage) {
            loadData(currentPage + 1, status);
        }

    }

    private void loadData(final int pageNo, String status) {
        new BasePresenterSubscriber<SellChanceBean>(mView) {

            @Override
            public void childNext(SellChanceBean sellChanceBean) {

                if (currentPage == 1) {
                    listData.clear();
                    listData.addAll(sellChanceBean.list);
                    //刷新成功
                    mView.onRefreshFinish(listData, sellChanceBean.getErrmsg());
                }else {
                    listData.addAll(sellChanceBean.list);
                    mView.onLoadFinish(listData, sellChanceBean.getErrmsg());
                }
            }

            @Override
            protected void childError(final Throwable e) {
                if (mView == null || mView.isViewFinish()) {
                    return;
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (pageNo == 1) {
                            mView.onRefreshFinish(null, e.getLocalizedMessage());
                        }else {
                            mView.onLoadFinish(null, e.getLocalizedMessage());
                        }
                    }
                });

            }
        }.run(new GetSellChance(currentPage, status).run());
    }
}
