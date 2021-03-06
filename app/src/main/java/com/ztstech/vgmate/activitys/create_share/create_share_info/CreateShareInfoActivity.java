package com.ztstech.vgmate.activitys.create_share.create_share_info;

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
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.create_share.create_share_add_cover.CreateShareAddCoverActivity;
import com.ztstech.vgmate.activitys.create_share.create_share_add_desc.CreateShareAddDescActivity;
import com.ztstech.appdomain.constants.Constants;
import com.ztstech.vgmate.data.constants.NetConstants;
import com.ztstech.vgmate.data.dto.CreateShareData;
import com.ztstech.vgmate.utils.StringUtils;
import com.ztstech.vgmate.utils.TakePhotoHelper;
import com.ztstech.vgmate.weigets.CustomGridView;
import com.ztstech.vgmate.weigets.TopBar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 分享资讯，分享资讯模块第一个界面
 */
public class CreateShareInfoActivity extends MVPActivity<CreateShareInfoContract.Presenter>
        implements CreateShareInfoContract.View, View.OnClickListener, InvokeListener,
        TakePhoto.TakeResultListener {

    /**请求图片描述*/
    public static final int REQ_DESC = 1;

    /**请求跳转到封面*/
    public static final int REQ_ADD_COVER = 2;

    /**分享类型*/
    public static final String ARG_TYPE = "arg_type";

    /**编辑时传入数据*/
    public static final String ARG_EDIT_DATA = "arg_edit_data";

    /**最大内容数*/
    public static final int MAX_CONTENT_COUNT = 10000;


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
    @BindView(R.id.ll_grid)
    LinearLayout llGrid;
    @BindView(R.id.ll_webview)
    LinearLayout llWebView;
    @BindView(R.id.tv_none_link)
    TextView tvNoneLink;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.top_bar)
    TopBar topBar;

    private ImageView imgAddImg;

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;


    private CreateShareData createShareData;

    /**资讯或者公告内容*/
    private String createShareType;

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

        createShareType = getIntent().getStringExtra(ARG_TYPE);

        tvNext.setOnClickListener(this);
        btInsertLink.setOnClickListener(this);

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
                if (TextUtils.isEmpty(etContent.toString()) || TextUtils.isEmpty(etTitle.getText())) {
                    tvNext.setEnabled(false);
                }else {
                    tvNext.setEnabled(true);
                }
                tvCount.setText(String.valueOf(etContent.getText().length()) + "/" + MAX_CONTENT_COUNT);
            }
        });

        etTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(etContent.toString()) || TextUtils.isEmpty(etTitle.getText())) {
                    tvNext.setEnabled(false);
                }else {
                    tvNext.setEnabled(true);
                }
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);

        addDefaultImage();

        createShareData = new Gson().fromJson(getIntent().getStringExtra(ARG_EDIT_DATA),
                CreateShareData.class);
        if (createShareData != null) {
            setDataToView(createShareData);
            createShareType = createShareData.type;
        }else {
            createShareData = new CreateShareData();
        }


        if (Constants.DATA_TYPE_NOTICE.equals(createShareType)) {
            topBar.setTitle("通告内容编辑");
        }else if (Constants.DATA_TYPE_INFORMATION.equals(createShareType)) {
            topBar.setTitle("资讯内容编辑");
        }

        topBar.getCenterTextView().requestFocus();
    }

    @Override
    public void onClick(View view) {
        if (view == imgAddImg) {
            showPickImage();
        }else if (view == tvNext) {

            if (etContent.getText().length() > MAX_CONTENT_COUNT) {
                Toast.makeText(this, "内容超出预定数量", Toast.LENGTH_SHORT).show();
                return;
            }

            //实例化存储数据类
            //类型
            createShareData.type = createShareType;
            createShareData.title = etTitle.getText().toString();
            createShareData.summary = etContent.getText().toString();

            if (View.VISIBLE == llGrid.getVisibility()) {
                //如果是发布图片

                //获取图片
                createShareData.contentpicfiles = new File[imageFiles.size()];
                for (int i = 0; i < imageFiles.size(); i++) {
                    createShareData.contentpicfiles[i] = imageFiles.get(i);
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
                    createShareData.picdescribe = new Gson().toJson(descs);
                }
            }else {
                //如果是插入链接
                createShareData.url = getLinkUrl();
            }



            Intent it = new Intent(this, CreateShareAddCoverActivity.class);
            it.putExtra(CreateShareAddCoverActivity.ARG_CREATE_SHARE_BEAN,
                    new Gson().toJson(createShareData));
            startActivityForResult(it,REQ_ADD_COVER);
        }else if (view == btInsertLink) {
            //点击插入链接
            toggleUrlMode();
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
        // 发布资讯公告成功
        if (requestCode == REQ_ADD_COVER){
            finish();
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
     * 切换url模式
     */
    private void toggleUrlMode() {
        if (View.VISIBLE == llGrid.getVisibility()) {
            //如果没有隐藏图片，切换到链接模式
            llGrid.setVisibility(View.GONE);
            llWebView.setVisibility(View.VISIBLE);

            btInsertLink.setSelected(true);

            String url = getLinkUrl();
            if (url == null) {
                tvNoneLink.setVisibility(View.VISIBLE);
            }else {
                tvNoneLink.setVisibility(View.INVISIBLE);
                webView.loadUrl(url);
            }

        }else {
            llWebView.setVisibility(View.GONE);
            llGrid.setVisibility(View.VISIBLE);
            btInsertLink.setSelected(false);

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
    private String getLinkUrl() {
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
                Intent it = new Intent(CreateShareInfoActivity.this,
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
     * 增加一张图片
     * @param imageUrl 图片网址
     * @param desc 图片描述
     */
    private void addImage(final String imageUrl, String desc) {
        final View itemView = LayoutInflater.from(this).inflate(R.layout.item_img_with_del,
                customGridView, false);
        ImageView img = itemView.findViewById(R.id.img);
        Glide.with(this).load(imageUrl).into(img);
        View del = itemView.findViewById(R.id.del);
        final TextView tvAddDesc = itemView.findViewById(R.id.tv_desc);
        if (!TextUtils.isEmpty(desc)) {
            tvAddDesc.setText(desc);
        }
        img.setTag(imageUrl);

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
        customGridView.addView(itemView, 0);

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customGridView.removeView(itemView);

                //删除网络图片，包括原图与缩略图
                String[] orignUrls = createShareData.contentpicsurl.split(",");
                String[] orignSUrls = createShareData.contentpicsurl.split(",");
                List<String> imgUrllist = new ArrayList<String>();
                List<String> imgSUrllist = new ArrayList<String>();
                for (int i = 0; i < orignUrls.length; i++) {
                    if (TextUtils.equals(orignUrls[i], imageUrl)) {
                        //如果当前图片为传入图片
                        continue;
                    }
                    imgUrllist.add(orignUrls[i]);
                    imgSUrllist.add(orignSUrls[i]);
                }
                createShareData.contentpicurl = StringUtils.concatWith(imgUrllist, ",");
                createShareData.contentpicsurl = StringUtils.concatWith(imgSUrllist, ",");
            }
        });
    }


    /**
     * 将data设置到view
     * @param data
     */
    private void setDataToView(CreateShareData data) {
        etTitle.setText(data.title);
        if (data.summary != null) {
            etContent.setText(data.summary);
        }
        if (Constants.SHARE_TYPE_TEXT.equals(data.ntype)) {
            //类型为文本
            etContent.setText(data.summary);
        }else if (Constants.SHARE_TYPE_IMG.equals(data.ntype)) {
            //类型为图片
            String[] imgUrls = data.contentpicsurl.split(",");
            String[] descs = new Gson().fromJson(data.picdescribe, String[].class);
            for (int i = 0; i < imgUrls.length; i++) {
                if (TextUtils.isEmpty(imgUrls[i])) {
                    continue;
                }
                String desc = "添加描述";
                if (i < descs.length) {
                    if (!TextUtils.isEmpty(descs[i])) {
                        //如果当前描述不为空
                        desc = descs[i];
                    }
                }
                addImage(imgUrls[i], desc);
            }
        }else if (Constants.SHARE_TYPE_URL.equals(data.ntype)) {
            //url类型
            //切换到url模式
            toggleUrlMode();
            webView.loadUrl(NetConstants.BASE_URL
                    .concat("jsp/webh5/inforDetails.jsp?type=01"
                            .concat("&nid=").concat(data.nid)));
        }else if (Constants.SHARE_TYPE_TEXT_IMG.equals(data.ntype)) {
            //文本图片
            etContent.setText(data.summary);

            String[] imgUrls = data.contentpicsurl.split(",");
            String[] descs = new Gson().fromJson(data.picdescribe, String[].class);
            for (int i = 0; i < imgUrls.length; i++) {
                if (TextUtils.isEmpty(imgUrls[i])) {
                    continue;
                }
                String desc = "添加描述";
                if (i < descs.length) {
                    if (!TextUtils.isEmpty(descs[i])) {
                        //如果当前描述不为空
                        desc = descs[i];
                    }
                }
                addImage(imgUrls[i], desc);
            }
        }else if (Constants.SHARE_TYPE_TEXT_URL.equals(data.ntype)) {
            etContent.setText(data.summary);

            toggleUrlMode();
            webView.loadUrl(NetConstants.BASE_URL
                    .concat("jsp/webh5/inforDetails.jsp?type=01"
                            .concat("&nid=").concat(data.nid)));
        }

    }
}
