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

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

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

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new GetChanceRecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);

        List<String> mFakeData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mFakeData.add("");
        }
        recyclerAdapter.setListData(mFakeData);
        recyclerAdapter.notifyDataSetChanged();
    }
}
