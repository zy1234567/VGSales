package com.ztstech.vgmate.activitys.complete_org_info_v2.subview.org_logo;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jph.takephoto.model.TResult;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.BaseActivity;
import com.ztstech.vgmate.utils.TakePhotoHelperWapper;
import com.ztstech.vgmate.weigets.TopBar;

import java.io.File;

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

    private String selectPath;

    private TakePhotoHelperWapper takePhotoHelperWapper;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_edit_org_logo;
    }

    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);
        takePhotoHelperWapper = new TakePhotoHelperWapper(this) {
            @Override
            public void takeSuccess(TResult result) {
                if (result != null) {
                    String uri = result.getImage().getOriginalPath();
                    final File f = new File(uri);
                    selectPath = f.getPath();
                    Glide.with(EditOrgLogoActivity.this).load(f).into(imgLogo);
                }
            }
        };

        takePhotoHelperWapper.onCreate(savedInstanceState);

        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhotoHelperWapper.showPickImage();
            }
        });

        topBar.getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent();
                it.putExtra(RESULT_IMAGE_PATH, selectPath);
                setResult(RESULT_OK, it);
                finish();
            }
        });

        String url = getIntent().getStringExtra(ARG_LOGO_URL);
        if (!TextUtils.isEmpty(url)) {
            Glide.with(this).load(url).into(imgLogo);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        takePhotoHelperWapper.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        takePhotoHelperWapper.onActivityResult(requestCode, resultCode, data);
    }
}
