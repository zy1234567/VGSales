package com.ztstech.vgmate.activitys.question.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.QuestionListBean;

/**
 *
 * @author smm
 * @date 2017/11/16
 */

public class QuestionListAdapter extends SimpleRecyclerAdapter<QuestionListBean.ListBean> {

    private QuestionViewHolder.ClickCallBack callBack;

    private QuestionViewHolder questionViewHolder;

    /** 搜索列表搜索的词 */
    private String searchText;

    public QuestionListAdapter(QuestionViewHolder.ClickCallBack callBack){
        this.callBack = callBack;
    }

    public QuestionListAdapter(String searchText,QuestionViewHolder.ClickCallBack callBack){
        this.callBack = callBack;
        this.searchText = searchText;
    }


    @Override
    public SimpleViewHolder<QuestionListBean.ListBean> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new QuestionViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_question,parent,false),searchText,callBack);
    }

    public void setSearchText(String keyword){
        this.searchText = keyword;
//        questionViewHolder.setSearchText(keyword);
    }

}
