package com.ztstech.vgmate.activitys.org_list.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;

/**
 * A simple {@link Fragment} subclass.
 * 通过
 */
public class PassFragment extends Fragment {

    public static final String TITLE = "通过";

    public PassFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pass, container, false);
    }

}
