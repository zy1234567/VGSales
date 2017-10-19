package com.ztstech.vgmate.activitys.complete_org_info_v2.subview.teacher.list;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.complete_org_info_v2.subview.teacher.EditOrgInfoAddTeacherActivity;
import com.ztstech.vgmate.activitys.complete_org_info_v2.subview.teacher.list.adapter.EditInfoTeacherRecyclerAdapter;
import com.ztstech.vgmate.base.BaseActivity;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.data.beans.TeacherListBean;
import com.ztstech.vgmate.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

/**
 * 机构教师列表界面
 */
public class EditOrgInfoTeacherActivity extends MVPActivity<EditOrgInfoTeacherContract.Presenter>
        implements EditOrgInfoTeacherContract.View {

    /**跳转添加界面*/
    public static final int REQ_ADD = 1;

    /**
     * 传入rbiid参数
     */
    public static final String ARG_RBIID = "arg_rbiid";

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.srl)
    SmartRefreshLayout smartRefreshLayout;

    private EditInfoTeacherRecyclerAdapter recyclerAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_edit_org_info_teacher;
    }



    @Override
    protected EditOrgInfoTeacherContract.Presenter initPresenter() {
        return new EditOrgInfoTeacherPresenter(this);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        final int rbiid = getIntent().getIntExtra(ARG_RBIID, -1);
        if (rbiid == -1) {
            throw new RuntimeException("必须传入rbiid");
        }

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.loadTeachers(rbiid);
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.loadTeachers(rbiid);
            }
        });

        recyclerAdapter = new EditInfoTeacherRecyclerAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);

        recyclerAdapter.setOnItemClickListener(new SimpleRecyclerAdapter
                .OnItemClickListener<TeacherListBean.ListBean>() {
            @Override
            public void onItemClick(TeacherListBean.ListBean item, int index) {
                Intent it = new Intent(EditOrgInfoTeacherActivity.this,
                        EditOrgInfoAddTeacherActivity.class);
                it.putExtra(EditOrgInfoAddTeacherActivity.ARG_TEACHER_INFO,
                        new Gson().toJson(item));
                startActivityForResult(it, REQ_ADD);
            }
        });

        mPresenter.loadTeachers(rbiid);
    }

    @Override
    public void loadTeacherFinish(List<TeacherListBean.ListBean> items, @Nullable String errmsg) {
        smartRefreshLayout.finishRefresh();
        if (errmsg == null) {
            recyclerAdapter.setListData(items);
            recyclerAdapter.notifyDataSetChanged();
        }else {
            ToastUtil.toastCenter(this, "请求失败：" + errmsg);
        }
    }

    @Override
    public void appendTeacherFinish(List<TeacherListBean.ListBean> items, @Nullable String errmsg) {
        smartRefreshLayout.finishLoadmore();
        if (errmsg == null) {
            recyclerAdapter.setListData(items);
            recyclerAdapter.notifyDataSetChanged();
        }else {
            ToastUtil.toastCenter(this, "请求失败：" + errmsg);
        }
    }
}
