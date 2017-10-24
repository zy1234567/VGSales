package com.ztstech.vgmate.activitys.org_list.fragments.item;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.GetOrgListItemsBean;
import com.ztstech.vgmate.data.user_case.GetOrgListItems;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by zhiyuan on 2017/9/23.
 */

public class OrglistitemPresenter extends PresenterImpl<OrglistItemContract.View> implements
        OrglistItemContract.Presenter {

    /**当前页数*/
    private int pageNo = 1;
    private int maxPage;

    private GetOrgListItems getOrgListItems;

    private List<GetOrgListItemsBean.ListBean> listItems = new ArrayList<>();

    public OrglistitemPresenter(OrglistItemContract.View view) {
        super(view);
        getOrgListItems = new GetOrgListItems();
    }

    @Override
    public void refreshList(String locationId, String status) {
        //刷新
        requestData(locationId, status, 1,new Subscriber<GetOrgListItemsBean>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                mView.onRefreshFinish(listItems, e.getLocalizedMessage());
            }

            @Override
            public void onNext(GetOrgListItemsBean getOrgListItemsBean) {
                if (getOrgListItemsBean.isSucceed()) {
                    listItems.clear();
                    listItems.addAll(getOrgListItemsBean.list);
                    pageNo = getOrgListItemsBean.pager.currentPage;
                    maxPage = getOrgListItemsBean.pager.totalPages;
                    mView.onRefreshFinish(listItems, null);
                }else {
                    mView.onRefreshFinish(listItems, getOrgListItemsBean.getErrmsg());
                }
            }

        });

    }

    @Override
    public void appendList(String locationId, String status) {
        //加载

        if (pageNo >= maxPage) {
            //如果当前已经是最大页数
            mView.onLoadMoreFinish(listItems, null);
            return;
        }


        requestData(locationId, status, pageNo + 1,new Subscriber<GetOrgListItemsBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onLoadMoreFinish(listItems, e.getLocalizedMessage());
            }

            @Override
            public void onNext(GetOrgListItemsBean getOrgListItemsBean) {
                if (getOrgListItemsBean.isSucceed()) {
                    listItems.addAll(getOrgListItemsBean.list);
                    pageNo = getOrgListItemsBean.pager.currentPage;
                    maxPage = getOrgListItemsBean.pager.totalPages;
                    mView.onLoadMoreFinish(listItems, null);
                }else {
                    mView.onLoadMoreFinish(listItems, getOrgListItemsBean.getErrmsg());
                }
            }

        });

    }

    private void requestData(String locationId, String status, int page,
                             final Subscriber<GetOrgListItemsBean>
            action1) {
        getOrgListItems.setQueryInfo(status, locationId, page);
        new BasePresenterSubscriber<GetOrgListItemsBean>(mView){

            @Override
            public void childNext(GetOrgListItemsBean getOrgListItemsBean) {
                action1.onNext(getOrgListItemsBean);
            }

            @Override
            protected void childError(Throwable e) {
                action1.onError(e);
            }

        }.run(getOrgListItems.run());
    }
}
