package com.ztstech.vgmate.activitys.complete_org_info_v2.subview.org_logo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.BaseActivity;
import com.ztstech.vgmate.weigets.TopBar;

import butterknife.BindView;

/**
 * 编辑机构logo
 */
public class EditOrgLogoActivity extends BaseActivity {

    /**传入机构当前url*/
    public static final String ARG_LOGO_URL = "arg_logo_url";

    /**获取的机构结果路径*/
    public static final String RESULT_IMAGE_PATH = "result_img_path";

    @BindView(R.id.top_bar)
    TopBar topBar;

    @BindView(R.id.img)
    ImageView imgLogo;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_edit_org_logo;
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        topBar.getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
