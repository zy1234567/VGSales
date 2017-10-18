package com.ztstech.vgmate.activitys.enroll_tag;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;

import butterknife.BindView;

/**
 * 招生标签
 */
public class EnrollTagActivity extends MVPActivity<EnrollTagContract.Presenter> implements
        EnrollTagContract.View, TextWatcher {

    /**传入标签信息*/
    public static final String ARG_TAG = "arg_tag";

    /**长度限制*/
    public static final int LENGTH_LIMIT = 6;

    @BindView(R.id.et_1)
    EditText et1;
    @BindView(R.id.et_2)
    EditText et2;
    @BindView(R.id.et_3)
    EditText et3;
    @BindView(R.id.et_4)
    EditText et4;
    @BindView(R.id.et_5)
    EditText et5;
    @BindView(R.id.et_6)
    EditText et6;
    @BindView(R.id.et_7)
    EditText et7;
    @BindView(R.id.et_8)
    EditText et8;
    @BindView(R.id.et_9)
    EditText et9;

    @BindView(R.id.tv_limit_1)
    TextView tvLimit1;
    @BindView(R.id.tv_limit_2)
    TextView tvLimit2;
    @BindView(R.id.tv_limit_3)
    TextView tvLimit3;
    @BindView(R.id.tv_limit_4)
    TextView tvLimit4;
    @BindView(R.id.tv_limit_5)
    TextView tvLimit5;
    @BindView(R.id.tv_limit_6)
    TextView tvLimit6;
    @BindView(R.id.tv_limit_7)
    TextView tvLimit7;
    @BindView(R.id.tv_limit_8)
    TextView tvLimit8;
    @BindView(R.id.tv_limit_9)
    TextView tvLimit9;

    private EditText ets[];
    private TextView tvs[];


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_enroll_tag;
    }

    @Override
    protected EnrollTagContract.Presenter initPresenter() {
        return new EnrollTagPresenter(this);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        ets = new EditText[] {et1, et2, et3, et4, et5, et6, et7, et8, et9};
        tvs = new TextView[] {tvLimit1, tvLimit2, tvLimit3, tvLimit4, tvLimit5, tvLimit6, tvLimit7,
                tvLimit8, tvLimit9};

        for (EditText et : ets) {
            et.addTextChangedListener(this);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void afterTextChanged(Editable editable) {
        for (int i = 0; i < ets.length; i++) {
            if (ets[i].getText() == editable) {
                if (editable.length() > LENGTH_LIMIT) {
                    tvs[i].setVisibility(View.VISIBLE);
                }else {
                    tvs[i].setVisibility(View.GONE);
                }
            }
        }
    }
}
