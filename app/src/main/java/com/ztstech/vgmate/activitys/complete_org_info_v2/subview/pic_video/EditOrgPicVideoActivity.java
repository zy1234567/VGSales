package com.ztstech.vgmate.activitys.complete_org_info_v2.subview.pic_video;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jph.takephoto.model.TResult;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.create_share.create_share_add_desc.CreateShareAddDescActivity;
import com.ztstech.vgmate.base.BaseActivity;
import com.ztstech.vgmate.utils.TakePhotoHelperWapper;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.CustomGridView;
import com.ztstech.vgmate.weigets.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 编辑视频、图片
 */
public class EditOrgPicVideoActivity extends BaseActivity implements View.OnClickListener{

    /**图片原始地址*/
    public static final String ARG_PIC_URLS = "arg_pic_urls";
    /**图片描述地址*/
    public static final String ARG_PIC_DESCS = "arg_pic_descs";
    /**图片文件地址*/
    public static final String RESULT_IMG_FILE_PATH = "result_path";
    /**图片描述*/
    public static final String RESULT_IMG_DESC = "result_desc";


    /**请求添加图片描述*/
    public static final int REQ_DESC = 1;

    /**最多12张图*/
    public static final int MAX_PIC_COUNT = 12;


    @BindView(R.id.cgv)
    CustomGridView customGridView;

    @BindView(R.id.top_bar)
    TopBar topBar;

    private ImageView imgAddImg;
    private TextView tvCurrentDesc;

    private TakePhotoHelperWapper takePhotoHelperWapper;

    /**图片文件地址*/
    private List<String> imageFiles = new ArrayList<>();


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_edit_org_pic_video;
    }

    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);

        takePhotoHelperWapper = new TakePhotoHelperWapper(this) {
            @Override
            public void takeSuccess(TResult result) {
                if (result != null) {
                    final String uri = result.getImage().getOriginalPath();
                    addItem(uri, null);
                }
            }
        };

        takePhotoHelperWapper.onCreate(savedInstanceState);

        topBar.getRightTextView().setOnClickListener(this);

        //增加添加按钮
        addDefaultImage();

        //增加默认图片
        String defaultUrls = getIntent().getStringExtra(ARG_PIC_URLS);
        if (!TextUtils.isEmpty(defaultUrls)){
            String[] urls = defaultUrls.split(",");
            String[] defaultDescs = null;
            String defaultDesc = getIntent().getStringExtra(ARG_PIC_DESCS);
            if (!TextUtils.isEmpty(defaultDesc)) {
                defaultDescs = new Gson().fromJson(defaultDesc, String[].class);
            }
            if (urls.length > 0) {

                for (int i = 0; i < urls.length; i++) {
                    String tmpDesc = null;
                    if (defaultDescs != null && i < defaultDescs.length) {
                        tmpDesc = defaultDescs[i];
                    }
                    addItem(urls[i], tmpDesc);
                }
            }
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
        if (RESULT_OK == resultCode && requestCode == REQ_DESC) {
            if (tvCurrentDesc != null) {
                String text = data.getStringExtra(CreateShareAddDescActivity.ARG_DESC);
                tvCurrentDesc.setText(text);
                tvCurrentDesc.setTag(text);     //记录描述为tag
            }
        }
    }

    /**
     * 增加默认图片
     */
    private void addDefaultImage() {
        imgAddImg = new ImageView(this);
        imgAddImg.setImageResource(R.mipmap.add_img);
        imgAddImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imgAddImg.setOnClickListener(this);
        customGridView.addView(imgAddImg);
        customGridView.requestLayout();
    }

    @Override
    public void onClick(View view) {
        if (view == imgAddImg) {
            takePhotoHelperWapper.showPickImage();
        }else if (view == topBar.getRightTextView()) {
            //点击保存
            save();
        }
    }


    private void save() {
        if (imageFiles != null && imageFiles.size() > MAX_PIC_COUNT) {
            ToastUtil.toastCenter(this, "最多只能选择12张图片");
            return;
        }

        Intent it = new Intent();
        if (imageFiles != null) {
            String newFiles = "";
            for (String path: imageFiles) {
                newFiles = newFiles.concat(path).concat(",");
            }
            if (!TextUtils.isEmpty(newFiles)) {
                newFiles = newFiles.substring(0, newFiles.length() - 1);
            }
            it.putExtra(RESULT_IMG_FILE_PATH, newFiles);
        }
        if (customGridView.getChildCount() > 1) {
            String[] descs = new String[customGridView.getChildCount() - 1];
            for (int i = 0; i < customGridView.getChildCount() - 1; i++) {
                Object tag = ((TextView) customGridView.getChildAt(i).findViewById(R.id.tv_desc))
                        .getTag();
                if (tag != null && tag instanceof String) {
                    descs[i] = (String) tag;
                }else {
                    descs[i] = "";
                }
            }
            it.putExtra(RESULT_IMG_DESC, new Gson().toJson(descs));
        }

        setResult(RESULT_OK, it);
        finish();
    }

    private void addItem(final String imgPath, String desc) {
        final View itemView = LayoutInflater.from(EditOrgPicVideoActivity.this)
                .inflate(R.layout.item_img_with_del,
                        customGridView, false);
        ImageView img = itemView.findViewById(R.id.img);
        Glide.with(EditOrgPicVideoActivity.this).load(imgPath).into(img);
        View del = itemView.findViewById(R.id.del);

        final TextView tvAddDesc = itemView.findViewById(R.id.tv_desc);

        if (!TextUtils.isEmpty(desc)) {
            tvAddDesc.setText(desc);
            tvAddDesc.setTag(desc);
        }

        tvAddDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //请求图片描述
                Intent it = new Intent(EditOrgPicVideoActivity.this,
                        CreateShareAddDescActivity.class);

                it.putExtra(CreateShareAddDescActivity.ARG_DESC, tvAddDesc.getText());
                //存储当前描述位置，在请求成功返回时更新view
                tvCurrentDesc = tvAddDesc;
                startActivityForResult(it, REQ_DESC);
            }
        });

        imageFiles.add(imgPath);
        customGridView.addView(itemView, 0);

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customGridView.removeView(itemView);
                imageFiles.remove(imgPath);
            }
        });
    }
}
