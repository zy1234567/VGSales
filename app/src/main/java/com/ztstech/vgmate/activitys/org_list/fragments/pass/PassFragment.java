package com.ztstech.vgmate.activitys.org_list.fragments.pass;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.org_list.fragments.pass.adapter.PassRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * 通过
 */
public class PassFragment extends MVPFragment<PassContract.Presenter> implements PassContract.View {

    public static final String TITLE = "通过";

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private PassRecyclerAdapter passRecyclerAdapter;

    public PassFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_pass;
    }

    @Override
    protected PassContract.Presenter initPresenter() {
        return new PassPresenter(this);
    }

    @Override
    protected void onCreateViewFinish(@Nullable Bundle savedInstanceState) {
        super.onCreateViewFinish(savedInstanceState);

        passRecyclerAdapter = new PassRecyclerAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(passRecyclerAdapter);

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

        passRecyclerAdapter.setListData(data);
        passRecyclerAdapter.notifyDataSetChanged();


    }

}
