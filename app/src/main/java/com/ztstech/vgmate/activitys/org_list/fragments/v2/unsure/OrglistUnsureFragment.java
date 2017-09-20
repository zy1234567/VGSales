package com.ztstech.vgmate.activitys.org_list.fragments.v2.unsure;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;

/**
 * A simple {@link Fragment} subclass.
 * 待确认
 */
public class OrglistUnsureFragment extends Fragment {


    public OrglistUnsureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_orglist_unsure, container, false);
    }

}
