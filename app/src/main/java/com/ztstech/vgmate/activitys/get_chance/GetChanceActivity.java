package com.ztstech.vgmate.activitys.get_chance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.get_chance.adapter.GetChanceRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 抢单、沟通记录
 */
public class GetChanceActivity extends MVPActivity<GetChanceContract.Presenter> implements
        GetChanceContract.View {

    /**
     * 传入机构id
     */
    public static final String ARG_ID = "arg_id";


    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private int id;

    private GetChanceRecyclerAdapter recyclerAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_get_chance;
    }

    @Override
    protected GetChanceContract.Presenter initPresenter() {
        return new GetChancePresenter(this);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();

        this.id = getIntent().getIntExtra(ARG_ID, -1);
        if (id == -1) {
            throw new RuntimeException("id 不能为空");
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new GetChanceRecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);

        mPresenter.refreshData(String.valueOf(id));

//        List<String> mFakeData = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            mFakeData.add("");
//        }
//        recyclerAdapter.setListData(mFakeData);
//        recyclerAdapter.notifyDataSetChanged();
    }
}
