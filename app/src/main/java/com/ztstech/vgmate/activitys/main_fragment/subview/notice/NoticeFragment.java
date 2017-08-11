package com.ztstech.vgmate.activitys.main_fragment.subview.notice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticeFragment extends Fragment {


    public NoticeFragment() {
        // Required empty public constructor
    }

    public static NoticeFragment newInstance() {
        
        Bundle args = new Bundle();
        
        NoticeFragment fragment = new NoticeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notice, container, false);
    }

}
