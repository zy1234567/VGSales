package com.ztstech.vgmate.activitys.search_org.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.data.beans.OrgFollowlistBean;

/**
 * Created by smm on 2017/12/8.
 */

public class SearchOrgAdapter extends SimpleRecyclerAdapter<OrgFollowlistBean.ListBean>{

    private String keyword;

    @Override
    public SearchOrgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchOrgViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_org,parent,false),keyword);
    }

    public void setKeyWord(String keyword){
        this.keyword = keyword;
    }

}
