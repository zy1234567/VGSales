package com.ztstech.vgmate.activitys.complete_org_info_v2.subview.teacher.list;

import android.os.Handler;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.TeacherListBean;
import com.ztstech.vgmate.data.user_case.GetTeacherList;
import com.ztstech.vgmate.utils.PresenterSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiyuan on 2017/10/19.
 *
 */

public class EditOrgInfoTeacherPresenter extends PresenterImpl<EditOrgInfoTeacherContract.View>
        implements EditOrgInfoTeacherContract.Presenter{


    private List<TeacherListBean.ListBean> items = new ArrayList<>();

    private int pageNo = 1;
    private int maxPage = 2;

    private Handler handler = new Handler();

    public EditOrgInfoTeacherPresenter(EditOrgInfoTeacherContract.View view) {
        super(view);
    }

    @Override
    public void loadTeachers(int rbiid) {
        load(rbiid, 1);
    }

    @Override
    public void appendTeachers(int rbiid) {
        if (pageNo >= maxPage) {
            mView.appendTeacherFinish(items, null);
        }
        load(rbiid, pageNo + 1);
    }

    private void load(int rbiid, final int pageNo) {
        new PresenterSubscriber<TeacherListBean>(mView) {

            @Override
            public void next(TeacherListBean bean) {
                if (bean.isSucceed()) {
                    EditOrgInfoTeacherPresenter.this.pageNo = bean.pager.currentPage;
                    maxPage = bean.pager.totalPages;
                    if (pageNo == 1) {
                        items.clear();
                    }
                    items.addAll(bean.list);
                    mView.loadTeacherFinish(items, null);
                }else {
                    mView.loadTeacherFinish(items, bean.getErrmsg());
                }
            }

            @Override
            public void onError(final Throwable e) {
                super.onError(e);
                if (mView == null || mView.isViewFinish()) {
                    return;
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (pageNo == 1) {
                            mView.loadTeacherFinish(items, e.getLocalizedMessage());
                        }else {
                            mView.appendTeacherFinish(items, e.getLocalizedMessage());
                        }
                    }
                });

            }

        }.run(new GetTeacherList(pageNo, rbiid).run());
    }
}
