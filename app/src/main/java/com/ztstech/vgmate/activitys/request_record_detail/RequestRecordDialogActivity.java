package com.ztstech.vgmate.activitys.request_record_detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.get_chance.adapter.GetChanceRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 机构申请记录对话框
 */
public class RequestRecordDialogActivity extends MVPActivity<RequestRecordDialogContract.Presenter> implements
        RequestRecordDialogContract.View {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_request_record_dialog;
    }

    @Override
    protected RequestRecordDialogContract.Presenter initPresenter() {
        return new RequestRecordDialogPresenter(this);
    }


    @Override
    protected void onSuperCreateFinish(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onSuperCreateFinish(savedInstanceState);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();

        setTitle(null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GetChanceRecyclerAdapter recyclerAdapter = new GetChanceRecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);

    }
}
