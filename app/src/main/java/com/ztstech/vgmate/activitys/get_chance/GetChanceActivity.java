package com.ztstech.vgmate.activitys.get_chance;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.get_chance.adapter.GetChanceRecyclerAdapter;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 抢单、沟通记录
 */
public class GetChanceActivity extends MVPActivity<GetChanceContract.Presenter> implements
        GetChanceContract.View {

    /**
     * 传入机构id
     */
    public static final String ARG_ID = "arg_id";

    /**
     * 传入机构标题
     */
    public static final String ARG_TITLE = "arg_title";


    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.top_bar)
    TopBar topBar;

    @BindView(R.id.et)
    EditText editText;

    @BindView(R.id.btn_commit)
    Button btSubmit;

    private String id;

    private GetChanceRecyclerAdapter recyclerAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_get_chance;
    }

    @Override
    protected GetChanceContract.Presenter initPresenter() {
        return new GetChancePresenter(this);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();

        this.id = getIntent().getStringExtra(ARG_ID);
        if (TextUtils.isEmpty(id)) {
            throw new RuntimeException("id 不能为空");
        }

        String title = getIntent().getStringExtra(ARG_TITLE);
        if (!TextUtils.isEmpty(title)) {
            topBar.setTitle(title);
        }


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new GetChanceRecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                btSubmit.setEnabled(s.length() != 0);
            }
        });

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText.getText().toString();
                if (TextUtils.isEmpty(msg)) {
                    ToastUtil.toastCenter(GetChanceActivity.this, "请输入沟通内容！");
                    return;
                }
                btSubmit.setEnabled(false);
                mPresenter.addCommunicate(id, msg);
            }
        });
        mPresenter.refreshData(id);
    }

    @Override
    public void onAddCommunicateFinish(@Nullable String errmsg) {
        editText.setText("");
        btSubmit.setEnabled(true);
        if (errmsg == null) {
            ToastUtil.toastCenter(this, "提交成功");
        }else {
            ToastUtil.toastCenter(this, "提交失败：" + errmsg);
        }
    }
}
