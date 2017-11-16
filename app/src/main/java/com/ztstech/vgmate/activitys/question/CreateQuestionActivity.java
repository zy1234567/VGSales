package com.ztstech.vgmate.activitys.question;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author smm
 * @date 2017/11/16
 */

public class CreateQuestionActivity extends MVPActivity {

    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.et_other)
    EditText etOther;
    @BindView(R.id.top_bar)
    TopBar topBar;

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etContent.getText().length() != 0){
                    topBar.getRightTextView().setTextColor(getResources().getColor(R.color.color_001));
                }else {
                    topBar.getRightTextView().setTextColor(getResources().getColor(R.color.color_103));
                }
            }
        });

        topBar.getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etContent.getText().length() != 0){
                    finish();
                }else {
                    ToastUtil.toastCenter(CreateQuestionActivity.this,"您还没有添加问题描述");
                }
            }
        });
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_create_question;
    }

}
