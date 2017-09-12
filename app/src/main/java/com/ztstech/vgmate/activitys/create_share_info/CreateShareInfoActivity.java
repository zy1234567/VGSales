package com.ztstech.vgmate.activitys.create_share_info;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.create_share_add_cover.CreateShareAddCoverActivity;
import com.ztstech.vgmate.activitys.create_share_add_desc.CreateShareAddDescActivity;
import com.ztstech.vgmate.utils.TakePhotoHelper;
import com.ztstech.vgmate.weigets.CustomGridView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 分享资讯
 */
public class CreateShareInfoActivity extends MVPActivity<CreateShareInfoContract.Presenter>
        implements CreateShareInfoContract.View, View.OnClickListener, InvokeListener,
        TakePhoto.TakeResultListener {

    /**请求图片描述*/
    public static final int REQ_DESC = 1;


    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.cgv)
    CustomGridView customGridView;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.btn_link)
    Button btInsertLink;

    private ImageView imgAddImg;

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    /**图片文件*/
    private List<File> imageFiles = new ArrayList<>();

    /**当前正在请求描述的textView*/
    private TextView tvCurrentReqDesc;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_create_share_info;
    }

    @Override
    protected CreateShareInfoContract.Presenter initPresenter() {
        return new CreateShareInfoPresenter(this);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();

        tvNext.setEnabled(true);
        tvNext.setOnClickListener(this);

        addDefaultImage();
    }

    @Override
    public void onClick(View view) {
        if (view == imgAddImg) {
            showPickImage();
        }else if (view == tvNext) {
            startActivity(new Intent(this, CreateShareAddCoverActivity.class));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQ_DESC) {
            if (tvCurrentReqDesc != null) {
                String text = data.getStringExtra(CreateShareAddDescActivity.ARG_DESC);
                tvCurrentReqDesc.setText(text);
            }
        }else {
            takePhoto.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onSuperCreateFinish(@Nullable Bundle savedInstanceState) {
        super.onSuperCreateFinish(savedInstanceState);
        takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this)
                .bind(new TakePhotoImpl(this,this));
        takePhoto.onCreate(savedInstanceState);
    }

    /**
     * 增加底部添加图片图片
     */
    private void addDefaultImage() {
        imgAddImg = new ImageView(this);
        imgAddImg.setImageResource(R.mipmap.add_img);
        imgAddImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imgAddImg.setOnClickListener(this);
        customGridView.addView(imgAddImg);
        customGridView.requestLayout();
    }


    /**
     * 显示选取图片
     */
    private void showPickImage() {
        new TakePhotoHelper(this, takePhoto, true).show();
    }

    @Override
    public void takeSuccess(TResult result) {
        if (result != null) {
            String uri = result.getImage().getOriginalPath();
            final File f = new File(uri);
            final View itemView = LayoutInflater.from(this).inflate(R.layout.item_img_with_del,
                    customGridView, false);
            ImageView img = itemView.findViewById(R.id.img);
            Glide.with(this).load(f).into(img);
            View del = itemView.findViewById(R.id.del);
            final TextView tvAddDesc = itemView.findViewById(R.id.tv_desc);

            tvAddDesc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tvCurrentReqDesc = tvAddDesc;       //记录当前textView
                    //请求图片描述
                    Intent it = new Intent(CreateShareInfoActivity.this,
                            CreateShareAddDescActivity.class);
                    it.putExtra(CreateShareAddDescActivity.ARG_DESC, tvAddDesc.getText());
                    //存储当前描述位置，在请求成功返回时更新view
                    startActivityForResult(it, REQ_DESC);
                }
            });

            imageFiles.add(f);
            customGridView.addView(itemView, 0);

            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    customGridView.removeView(itemView);
                    imageFiles.remove(f);
                }
            });


        }
    }

    @Override
    public void takeFail(TResult result, String msg) {}

    @Override
    public void takeCancel() {}

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(
                TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam = invokeParam;
        }
        return type;
    }
}
