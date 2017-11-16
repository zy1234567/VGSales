package com.ztstech.vgmate.activitys.mate_approve;

import com.ztstech.appdomain.user_case.GetUnApproveMateList;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.activitys.org_follow.OrgFollowContact;
import com.ztstech.vgmate.data.beans.WaitApproveMateListBean;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author smm
 * @date 2017/11/13
 */

public class WaitApproveMatePresenter extends PresenterImpl<WaitApproveMateContact.View> implements WaitApproveMateContact.Presenter {

    private int currentPage;
    private int totalPage;

    private List<WaitApproveMateListBean.ListBean> listBeanList = new ArrayList<>();

    public WaitApproveMatePresenter(WaitApproveMateContact.View view){
        super(view);
    }

    @Override
    public void loadData() {
        requestData(1);
    }

    @Override
    public void appendData() {
        if (totalPage == currentPage){
        }else {
            requestData(currentPage + 1);
        }
    }


    private void requestData(final int page) {
        new BasePresenterSubscriber<WaitApproveMateListBean>(mView,false) {

            @Override
            protected void childError(Throwable e) {
                mView.showError("查询跟进机构列表出错：".concat(e.getMessage()));
            }

            @Override
            public void childNext(WaitApproveMateListBean bean) {
                if (bean.isSucceed()) {
                    currentPage = bean.pager.currentPage;
                    totalPage = bean.pager.totalPages;

                    if (currentPage == 1) {
                        //刷新
                        listBeanList.clear();
                    }
                    listBeanList.addAll(bean.list);
                    mView.setData(listBeanList);
                }else {
                    //如果失败
                    mView.showError(bean.getErrmsg());
                }

            }
        }.run(new GetUnApproveMateList(page).run());
    }

}
