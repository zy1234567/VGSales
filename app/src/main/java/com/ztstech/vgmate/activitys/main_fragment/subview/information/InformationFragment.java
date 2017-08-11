package com.ztstech.vgmate.activitys.main_fragment.subview.information;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.main_fragment.subview.information.adapter.InformationRecyclerAdapter;
import com.ztstech.vgmate.model.information.InformationModel;

import java.util.List;

import butterknife.BindView;


public class InformationFragment extends MVPFragment<InformationContract.Presenter> implements
        InformationContract.View {


    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private InformationRecyclerAdapter recyclerAdapter;


    public static InformationFragment newInstance() {

        InformationFragment fragment = new InformationFragment();
        return fragment;
    }

    @Override
    protected InformationContract.Presenter initPresenter() {
        return new InformationPresenter(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_information;
    }


    @Override
    protected void onCreateViewFinish(@Nullable Bundle savedInstanceState) {
        super.onCreateViewFinish(savedInstanceState);
        recyclerAdapter = new InformationRecyclerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(recyclerAdapter);

        mPresenter.loadListData();

    }

    @Override
    public void setListData(List<InformationModel> informationModels) {
        recyclerAdapter.setListData(informationModels);
        recyclerAdapter.notifyDataSetChanged();
    }
}
