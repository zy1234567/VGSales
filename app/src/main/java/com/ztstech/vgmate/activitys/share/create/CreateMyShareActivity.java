package com.ztstech.vgmate.activitys.share.create;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.ztstech.appdomain.constants.Constants;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.create_share.create_share_add_desc.CreateShareAddDescActivity;
import com.ztstech.vgmate.activitys.create_share.create_share_info.CreateShareInfoActivity;
import com.ztstech.vgmate.data.constants.NetConstants;
import com.ztstech.vgmate.data.dto.CreateShareData;
import com.ztstech.vgmate.utils.StringUtils;
import com.ztstech.vgmate.utils.TakePhotoHelper;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.CustomGridView;
import com.ztstech.vgmate.weigets.TopBar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 发布个人分享界面
 * @author smm
 * @date 2017/11/27
 */

public class CreateMyShareActivity extends MVPActivity<CreateShareContact.Presenter> implements CreateShareContact.View,
            InvokeListener, TakePhoto.TakeResultListener{

    /** 发送分享成功返回 */
    public static final int RES_SHARE = 2;
    /**请求图片描述*/
    public static final int REQ_DESC = 1;
    /**最大内容数*/
    public static final int MAX_CONTENT_COUNT = 10000;

    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.btn_link)
    Button btInsertLink;
    @BindView(R.id.tv_none_link)
    TextView tvNoneLink;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.ll_webview)
    LinearLayout llWebview;
    @BindView(R.id.cgv)
    CustomGridView customGridView;
    @BindView(R.id.ll_grid)
    LinearLayout llGrid;

    private ImageView imgAddImg;

    private TakePhoto takePhoto;

    /**图片文件*/
    private List<File> imageFiles = new ArrayList<>();

    /**当前正在请求描述的textView*/
    private TextView tvCurrentReqDesc;

    private InvokeParam invokeParam;

    /** 图片描述 */
    private String picdescribe;

    /** 连接地址 */
    private String linkUrl;

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        topBar.setTitle("发布分享");
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }
        });

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                etTitle.setText(title);
            }
        });
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                judgeCanCommit();
                tvCount.setText(String.valueOf(etContent.getText().length()) + "/" + MAX_CONTENT_COUNT);
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);

        addDefaultImage();
        topBar.getCenterTextView().requestFocus();
    }

    @Override
    protected void onSuperCreateFinish(@Nullable Bundle savedInstanceState) {
        super.onSuperCreateFinish(savedInstanceState);
        takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this)
                .bind(new TakePhotoImpl(this,this));
        takePhoto.onCreate(savedInstanceState);
    }

    @Override
    public String getShareTitle() {
        return etTitle.getText().toString();
    }

    @Override
    public String getShareContent() {
        return etContent.getText().toString();
    }

    @Override
    public String getLinkUrl() {
        return linkUrl;
    }

    @Override
    public File[] getImgaeFiles() {
        File[] files = new File[imageFiles.size()];
        for (int i = 0; i < imageFiles.size(); i++) {
            files[i] = imageFiles.get(i);
        }
        return files;
    }

    @Override
    public String getPicdescribe() {
        return picdescribe;
    }

    @Override
    public void onCommitFinsih() {
        ToastUtil.toastCenter(this,"分享成功");
        setResult(RES_SHARE);
        finish();
    }

    @Override
    public void showError(String msg) {
        ToastUtil.toastCenter(this,msg);
    }

    @Override
    protected CreateShareContact.Presenter initPresenter() {
        return new CreateSharePresenter(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_my_share;
    }


    @OnClick({R.id.tv_next, R.id.btn_link})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_next:
                if (etContent.getText().length() > MAX_CONTENT_COUNT) {
                    Toast.makeText(this, "内容超出预定数量", Toast.LENGTH_SHORT).show();
                    return;
                }
                //获取图片描述
                if (customGridView.getChildCount() > 1) {
                    //如果选择了图片
                    String[] descs = new String[customGridView.getChildCount() - 1];

                    for (int i = 0; i < customGridView.getChildCount() - 1; i++) {
                        String text = ((TextView)customGridView.getChildAt(i)
                                .findViewById(R.id.tv_desc)).getText().toString();
                        if (TextUtils.equals("添加描述", text)) {
                            text = "";
                        }
                        descs[i] = text;
                    }
                    picdescribe = new Gson().toJson(descs);
                }
                mPresenter.commit();
                break;
            case R.id.btn_link:
                //点击插入链接
                toggleUrlMode();
                break;
            default:
                break;
        }
    }

    /**
     * 增加底部添加图片图片
     */
    private void addDefaultImage() {
        imgAddImg = new ImageView(this);
        imgAddImg.setImageResource(R.mipmap.add_img);
        imgAddImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imgAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPickImage();
            }
        });
        customGridView.addView(imgAddImg);
        customGridView.requestLayout();
    }


    /**
     * 切换url模式
     */
    private void toggleUrlMode() {
        if (View.VISIBLE == llGrid.getVisibility()) {
            //如果没有隐藏图片，切换到链接模式
            llGrid.setVisibility(View.GONE);
            llWebview.setVisibility(View.VISIBLE);

            btInsertLink.setSelected(true);

            linkUrl = getClipboardLinkUrl();
            if (linkUrl == null) {
                tvNoneLink.setVisibility(View.VISIBLE);
            }else {
                tvNoneLink.setVisibility(View.INVISIBLE);
                webView.loadUrl(linkUrl);
            }
        }else {
            linkUrl = "";
            llWebview.setVisibility(View.GONE);
            llGrid.setVisibility(View.VISIBLE);
            btInsertLink.setSelected(false);
        }
        judgeCanCommit();
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

    /**
     * 显示选取图片
     */
    private void showPickImage() {
        new TakePhotoHelper(this, takePhoto, false).showPickDialog();
    }

    /**
     * 获取剪切板url
     * @return
     */
    private String getClipboardLinkUrl() {
        String tempStr = null;
        ClipboardManager clipboardManager=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        if(clipboardManager==null) {
            return null;
        }
        if(clipboardManager.getText()!=null) {
            tempStr=clipboardManager.getText().toString();
        }
        return tempStr;
    }

    @Override
    public void takeSuccess(TResult result) {
        if (result != null) {
            String uri = result.getImage().getOriginalPath();
            final File f = new File(uri);
            addImage(f);
            judgeCanCommit();
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

    /**
     * 增加一张图片
     * @param f
     */
    private void addImage(final File f) {
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
                Intent it = new Intent(CreateMyShareActivity.this,
                        CreateShareAddDescActivity.class);
                it.putExtra(CreateShareAddDescActivity.ARG_DESC, tvAddDesc.getText());
                //存储当前描述位置，在请求成功返回时更新view
                startActivityForResult(it, REQ_DESC);
            }
        });

        imageFiles.add(0, f);
        customGridView.addView(itemView, 0);

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customGridView.removeView(itemView);
                imageFiles.remove(f);
            }
        });
    }


    /**
     * 判断是否可以提交
     */
    private void judgeCanCommit(){
        boolean isHasContent = !etContent.getText().toString().isEmpty();
        boolean isHasImage = imageFiles != null && imageFiles.size() > 0;
        boolean ishasLink = !TextUtils.isEmpty(getLinkUrl())
                && getLinkUrl().indexOf("http://") != -1 && getLinkUrl().indexOf("https://") != -1;
        if (isHasContent || isHasImage || ishasLink){
            tvNext.setEnabled(true);
        }else {
            tvNext.setEnabled(false);
        }
    }

}


