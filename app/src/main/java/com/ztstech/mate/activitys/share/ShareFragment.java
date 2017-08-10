package com.ztstech.mate.activitys.share;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ztstech.mate.R;

/**
 * 分享
 */
public class ShareFragment extends Fragment {


    public static ShareFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ShareFragment fragment = new ShareFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_share, container, false);
    }

}
