package com.ztstech.vgmate.activitys.org_list.fragments.invalid;


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
import com.ztstech.vgmate.activitys.org_list.fragments.invalid.adapter.InvalidRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * 无效
 */
public class InvalidFragment extends MVPFragment<InvalidContract.Presenter> implements
        InvalidContract.View {

    public static final String TITLE = "无效";

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private InvalidRecyclerAdapter recyclerAdapter;

    public InvalidFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_invalid;
    }

    @Override
    protected InvalidContract.Presenter initPresenter() {
        return new InvalidPresenter(this);
    }

    @Override
    protected void onCreateViewFinish(@Nullable Bundle savedInstanceState) {
        super.onCreateViewFinish(savedInstanceState);
        recyclerAdapter = new InvalidRecyclerAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapter);

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

        recyclerAdapter.setListData(data);
        recyclerAdapter.notifyDataSetChanged();
    }
}
