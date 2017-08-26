package com.ztstech.vgmate.activitys.self_organization_detail.request_records;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestRecordsFragment extends MVPFragment<RequestRecordsContract.Presenter> implements
        RequestRecordsContract.View  {


    public RequestRecordsFragment() {
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_request_records;
    }

    @Override
    protected RequestRecordsContract.Presenter initPresenter() {
        return new RequestRecordsPresenter(this);
    }
}
