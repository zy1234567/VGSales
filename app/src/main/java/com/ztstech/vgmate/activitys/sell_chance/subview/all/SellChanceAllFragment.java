package com.ztstech.vgmate.activitys.sell_chance.subview.all;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.get_chance.GetChanceActivity;
import com.ztstech.vgmate.activitys.sell_chance.subview.all.adapter.SellChanceAllRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class SellChanceAllFragment extends MVPFragment<SellChanceAllContract.Presenter> implements
        SellChanceAllContract.View {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private SellChanceAllRecyclerAdapter adapter;

    @Override
    protected SellChanceAllContract.Presenter initPresenter() {
        return new SellChanceAllPresenter(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_sell_chance_all;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new SellChanceAllRecyclerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        List<String> mFakeData = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            mFakeData.add("");
        }
        adapter.setListData(mFakeData);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(new SimpleRecyclerAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(String item, int index) {
                startActivity(new Intent(getActivity(), GetChanceActivity.class));
            }
        });
    }
}
