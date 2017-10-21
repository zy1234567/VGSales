package com.ztstech.vgmate.activitys.org_detail.dialog.org_audit;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.GetOrgListItemsBean;
import com.ztstech.vgmate.data.beans.RepeatOrgBean;
import com.ztstech.vgmate.data.user_case.GetRepeatOrg;
import com.ztstech.vgmate.utils.PresenterSubscriber;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by zhiyuan on 2017/9/25.
 */

public class OrgAuditDialogPresenter extends PresenterImpl<OrgAuditDialogContract.View> implements
        OrgAuditDialogContract.Presenter {

    private int pageNo;

    private int maxPage = 2;

    private List<RepeatOrgBean.ListBean> mDatas = new ArrayList<>();

    public OrgAuditDialogPresenter(OrgAuditDialogContract.View view) {
        super(view);
    }

    @Override
    public void loadRepeatData(GetOrgListItemsBean.ListBean bean) {
        load(1, new Action1<RepeatOrgBean>() {
            @Override
            public void call(RepeatOrgBean bean) {
                if (bean == null) {
                    mView.appendFinish(mDatas, "网络请求失败");
                }else {
                    if (bean.isSucceed()) {
                        mDatas.clear();
                        mDatas.addAll(bean.list);
                        mView.loadRepeatDataFinish(mDatas, null);
                    }else {
                        mView.loadRepeatDataFinish(mDatas, bean.getErrmsg());
                    }
                }
            }
        }, bean);
    }

    @Override
    public void appendData(GetOrgListItemsBean.ListBean bean) {
        if (pageNo < maxPage) {
            load(pageNo + 1, new Action1<RepeatOrgBean>() {
                @Override
                public void call(RepeatOrgBean bean) {
                    if (bean == null) {
                        mView.appendFinish(mDatas, "网络请求失败");
                    }else {
                        if (bean.isSucceed()) {
                            mDatas.addAll(bean.list);
                            mView.appendFinish(mDatas, null);
                        }else {
                            mView.appendFinish(mDatas, bean.getErrmsg());
                        }
                    }

                }
            }, bean);
        }else {
            mView.appendFinish(mDatas, null);
        }
    }

    private void load(int page, final Action1<RepeatOrgBean> action1, GetOrgListItemsBean.ListBean bean) {
        new PresenterSubscriber<RepeatOrgBean>(null){
            @Override
            public void next(RepeatOrgBean bean) {
                if (bean.isSucceed()) {
                    pageNo = bean.pager.currentPage;
                    maxPage = bean.pager.totalPages;
                }
                action1.call(bean);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                action1.call(null);
            }
        }.run(new GetRepeatOrg(bean.rbidistrict, bean.rbioname, bean.rbiid, page).run());

    }
}
