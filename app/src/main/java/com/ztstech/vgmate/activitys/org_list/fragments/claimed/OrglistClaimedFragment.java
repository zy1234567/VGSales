package com.ztstech.vgmate.activitys.org_list.fragments.claimed;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.org_detail.OrgDetailActivity;
import com.ztstech.vgmate.activitys.org_list.fragments.adapter.OrglistRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * 已认领
 */
public class OrglistClaimedFragment extends MVPFragment<OrglistClaimedContract.Presenter> implements
        OrglistClaimedContract.View {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_orglist_claimed;
    }

    @Override
    protected OrglistClaimedContract.Presenter initPresenter() {
        return new OrglistClaimedPresenter(this);
    }

    @Override
    protected void onCreateViewFinish(@Nullable Bundle savedInstanceState) {
        super.onCreateViewFinish(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        OrglistRecyclerAdapter adapter = new OrglistRecyclerAdapter();
        recyclerView.setAdapter(adapter);

        List<String> data = new ArrayList<>(10);
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");

        adapter.setOnItemClickListener(new SimpleRecyclerAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(String item, int index) {
                startActivity(new Intent(getActivity(), OrgDetailActivity.class));
            }
        });

        adapter.setListData(data);
        adapter.notifyDataSetChanged();

    }
}
