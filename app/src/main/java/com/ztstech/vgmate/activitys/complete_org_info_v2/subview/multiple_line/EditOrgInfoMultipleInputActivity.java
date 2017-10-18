package com.ztstech.vgmate.activitys.complete_org_info_v2.subview.multiple_line;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.BaseActivity;
import com.ztstech.vgmate.weigets.TopBar;

import butterknife.BindView;

/**
 * 多行文本输入编辑
 */
public class EditOrgInfoMultipleInputActivity extends BaseActivity {

    /**传入title参数*/
    public static final String ARG_TITLE = "arg_title";
    /**传入默认显示文本*/
    public static final String ARG_DATA = "arg_data";

    /**回传的result*/
    public static final String RESULT_TEXT = "result_text";

    @BindView(R.id.et_content)
    EditText etContent;

    @BindView(R.id.top_bar)
    TopBar topBar;

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_multiple_line_input_activity;
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();

        topBar.setTitle(getIntent().getStringExtra(ARG_TITLE));
        topBar.getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击保存
                Intent it = new Intent();
                it.putExtra(RESULT_TEXT, etContent.getText().toString());
                setResult(RESULT_OK, it);
                finish();
            }
        });
        etContent.setText(getIntent().getStringExtra(ARG_DATA));
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}
