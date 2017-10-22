package com.ztstech.vgmate.activitys.org_list.fragments.unapprove;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.OrglistUnApproveBean;
import com.ztstech.vgmate.data.repository.UserPreferenceManager;
import com.ztstech.vgmate.data.user_case.GetUnApproveList;
import com.ztstech.vgmate.utils.PresenterSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiyuan on 2017/10/14.
 */

public class OrglistUnApprovePresenter extends PresenterImpl<OrglistUnApproveContract.View>
        implements OrglistUnApproveContract.Presenter {

    private int pageNo;
    private int totalPage;

    private List<OrglistUnApproveBean.ListBean> listItems = new ArrayList<>();


    public OrglistUnApprovePresenter(OrglistUnApproveContract.View view) {
        super(view);
    }

    @Override
    public void refresh() {
        loadData(1);
    }

    @Override
    public void loadMore() {
        if (pageNo < totalPage) {
            loadData(pageNo + 1);
        }else {
            // TODO: 2017/10/14 结束刷新

        }

    }

    private void loadData(final int page) {
        new PresenterSubscriber<OrglistUnApproveBean>(mView) {

            @Override
            public void childNext(OrglistUnApproveBean orglistUnApproveBean) {
                if (orglistUnApproveBean.isSucceed()) {
                    totalPage = orglistUnApproveBean.pager.totalPages;
                    pageNo = orglistUnApproveBean.pager.currentPage;

                    if (pageNo == 1) {
                        listItems.clear();
                    }
                    listItems.addAll(orglistUnApproveBean.list);

                    if (pageNo == 1) {
                        mView.onRefreshFinish(listItems, null);
                    }else {
                        mView.onLoadFinsh(listItems, null);
                    }
                }else {
                    if (page == 1) {
                        mView.onRefreshFinish(listItems, orglistUnApproveBean.getErrmsg());
                    }else {
                        mView.onLoadFinsh(listItems, orglistUnApproveBean.getErrmsg());
                    }
                }

            }

            @Override
            protected void childError(Throwable e) {
                super.onError(e);
                mView.onLoadFinsh(listItems, "网络访问出错".concat(e.getLocalizedMessage()));
            }
        }.run(new GetUnApproveList(getCurrentSelectedLocation(), page).run());
    }

    /**
     * 获取当前用户选中的地址
     * @return
     */
    private String getCurrentSelectedLocation() {
        return UserPreferenceManager.getInstance().getUserSelectArea();
    }
}
