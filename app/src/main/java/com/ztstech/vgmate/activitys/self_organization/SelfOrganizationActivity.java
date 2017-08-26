package com.ztstech.vgmate.activitys.self_organization;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.self_organization.adapter.SelfOrganizationRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 自行开拓机构界面
 */
public class SelfOrganizationActivity extends MVPActivity<SelfOrganizationContract.Presenter>
        implements SelfOrganizationContract.View {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private SelfOrganizationRecyclerAdapter recyclerAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_self_organization;
    }

    @Override
    protected SelfOrganizationContract.Presenter initPresenter() {
        return new SelfOrganizationPresenter(this);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();

        recyclerAdapter = new SelfOrganizationRecyclerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);

        List<String> fakeData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            fakeData.add("");
        }
        recyclerAdapter.setListData(fakeData);
        recyclerAdapter.notifyDataSetChanged();
    }
}
