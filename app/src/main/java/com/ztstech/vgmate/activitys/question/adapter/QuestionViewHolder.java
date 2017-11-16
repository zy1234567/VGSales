package com.ztstech.vgmate.activitys.question.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.QuestionListBean;
import com.ztstech.vgmate.utils.ViewUtils;

import butterknife.BindView;

/**
 * @author smm
 * @date 2017/11/16
 */

public class QuestionViewHolder extends SimpleViewHolder<QuestionListBean.ListBean> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_answer_num)
    TextView tvAnswerNum;
    @BindView(R.id.rl_body)
    RelativeLayout rlBody;

    /** 搜索列表搜索的词 */
    private String searchText;

    private ClickCallBack callBack;

    public QuestionViewHolder(View itemView, String searchText,ClickCallBack callBack) {
        super(itemView);
        this.searchText = searchText;
        this.callBack = callBack;
    }

    @Override
    protected void refreshView(QuestionListBean.ListBean data) {
        super.refreshView(data);
        if (!TextUtils.isEmpty(searchText)){
            //不为空则是搜索列表
            ViewUtils.setKeyWordLight(searchText,tvTitle.getText().toString(),tvTitle);
        }
        rlBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onItemClick();
            }
        });
        rlBody.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                callBack.onItemLongClick();
                return false;
            }
        });
    }

    public interface ClickCallBack{
        void onItemClick();
        void onItemLongClick();
    }

}
