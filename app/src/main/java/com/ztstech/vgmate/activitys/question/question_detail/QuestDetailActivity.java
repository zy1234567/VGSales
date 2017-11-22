package com.ztstech.vgmate.activitys.question.question_detail;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.question.adapter.AnwsetListAdapter;
import com.ztstech.vgmate.data.beans.AnwserListBean;
import com.ztstech.vgmate.data.beans.QuestionListBean;
import com.ztstech.vgmate.utils.TimeUtils;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.AutoLinearLayoutManager;
import com.ztstech.vgmate.weigets.TopBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 问题详情界面
 *
 * @author smm
 * @date 2017/11/16
 */

public class QuestDetailActivity extends MVPActivity<QuestionDetailContact.Presenter> implements QuestionDetailContact.View {

    public static final String KEY_BEAN = "bean";

    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.tv_describe)
    TextView tvDes;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.et_comment)
    EditText etComment;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.rl_comment)
    RelativeLayout rlComment;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    /** 所要显示的问题id */
    private String qid;

    /** 要显示的问题实体类 */
    private QuestionListBean.ListBean bean;

    AnwsetListAdapter adapter;

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        bean = (QuestionListBean.ListBean) getIntent().getSerializableExtra(KEY_BEAN);
        if (bean != null){
            tvDes.setText(bean.descrption);
            tvTime.setText(TimeUtils.InformationTime(bean.createtime));
        }
        adapter = new AnwsetListAdapter();
        recycler.setLayoutManager(new AutoLinearLayoutManager(this));
        recycler.setAdapter(adapter);
        recycler.setNestedScrollingEnabled(false);
        scrollView.smoothScrollBy(0,0);
        mPresenter.loadListData();

        etComment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    etComment.setHint("写回复...");
                    //清除评论条目信息
                    etComment.setTag(null);
                    etComment.setTag(R.id.tag_0, null);
                    etComment.setTag(R.id.tag_1, null);
                }
            }
        });
    }

    @Override
    protected QuestionDetailContact.Presenter initPresenter() {
        return new QuestionDetailPresenter(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_quest_detail;
    }


    @OnClick(R.id.tv_submit)
    public void onClick() {
        String content = etComment.getText().toString();
        if (!TextUtils.isEmpty(content)){
            mPresenter.reply();
        }
    }

    @Override
    public void setListData(List<AnwserListBean.ListBean> listData) {
        adapter.setListData(listData);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {
        ToastUtil.toastCenter(this,msg);
    }

    @Override
    public String getqid() {
        if (bean != null){
            return bean.queid;
        }
        return null;
    }

    @Override
    public String getContent() {
        return etComment.getText().toString();
    }

    @Override
    public String getQuid() {
        if (bean != null){
            return bean.uid;
        }
        return null;
    }

    @Override
    public void onReplySuccess() {
        ToastUtil.toastCenter(this,"回复成功");
        mPresenter.loadListData();
    }
}
