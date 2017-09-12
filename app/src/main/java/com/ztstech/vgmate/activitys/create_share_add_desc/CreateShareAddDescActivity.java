package com.ztstech.vgmate.activitys.create_share_add_desc;

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
 * 分享增加描述
 */
public class CreateShareAddDescActivity extends BaseActivity {

    /**
     * intent 传出参数
     */
    public static final String ARG_DESC = "arg_desc";

    @BindView(R.id.top_bar)
    TopBar topBar;

    @BindView(R.id.et_desc)
    EditText etDesc;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_create_share_add_desc;
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();

        topBar.getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = etDesc.getText().toString();
                Intent it = getIntent();
                it.putExtra(ARG_DESC, text);
                setResult(RESULT_OK, it);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}
