package com.ztstech.vgmate.activitys.category_info;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.utils.ViewUtils;
import com.ztstech.vgmate.weigets.ExpandableLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryTagsActivity extends AppCompatActivity implements CategoryTagsContract.IView{

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.tv_hint1)
    TextView tvHint1;
    @BindView(R.id.layout_tags)
    ExpandableLayout layoutTags;
    @BindView(R.id.ll_listviews)
    LinearLayout llListViews;
    @BindView(R.id.listview_left)
    ListView listviewLeft;
    @BindView(R.id.listview_right)
    ListView listviewRight;
    @BindView(R.id.scrollview)
    ScrollView scrollView;
    @BindView(R.id.rl_hint2)
    RelativeLayout rlHint;

    private CategoryTagsPresenter presenter;
    private RelativeLayout.LayoutParams lpScrollview;
    private Intent feedBackData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_tags);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        title.setText("课程标签");
        lpScrollview = (RelativeLayout.LayoutParams) scrollView.getLayoutParams();

        String selectedIds = getIntent().getStringExtra("selectedIds");
        String selectedNames = getIntent().getStringExtra("selectedNames");
        presenter = new CategoryTagsPresenter(this, this, selectedIds, selectedNames);

        /** 使scrollView自动滑动到底部*/
        layoutTags.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                layoutTags.post(new Runnable() {
                    public void run() {
                        scrollView.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        });

    }

    @OnClick({R.id.img_back, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_save:
                feedBackData = presenter.getFeedBackData();
                if(getIntent().getBooleanExtra("updateOrgInfo",false)){
                    /* 机构修改资料*/
//                    presenter.updateOrgInfo();
                    throw new UnsupportedOperationException("不支持的操作");
                }else {
                    /* 注册*/
                    setResult(RESULT_OK, feedBackData);
                    finish();
                    break;
                }
        }
    }


    @Override
    public ExpandableLayout getExpandableLayout() {
        return layoutTags;
    }


    @Override
    public void setScrollViewMultiLines() {
        lpScrollview.height =  ViewUtils.dp2px(this, 110);
        scrollView.setLayoutParams(lpScrollview);
    }

    @Override
    public void setScrollViewSingleLine() {
        lpScrollview.height = ViewUtils.dp2px(this, 58);
        scrollView.setLayoutParams(lpScrollview);
    }

    private void initScrollViewAnim() {
        //scrollview 自动滑动到底部，再平缓的改变高度，不好看，取消动画
        //任何时候使用 getMeasuredHeight都要考虑是否为0
        //view.getLayoutParams 返回的类型是其父布局的类型 如RelativeLayout.Params
        //view的高度设为自适应 在代码中就没法通过setLayoutParams改变


/*        animExpand = ValueAnimator.ofInt(0, 58).setDuration(500);
        animExpand.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentValue = (int) animation.getAnimatedValue();
                Log.e("animatedValue", "currentAimatedValuew: " + currentValue);
                lpScrollview.height = SizeUtil.dip2px(context, 58 + currentValue);
                scrollView.setLayoutParams(lpScrollview);
            }
        });*/

        /*animExpand = ValueAnimator.ofInt(0, 58).setDuration(500);
        animExpand.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentValue = (int) animation.getAnimatedValue();
                Log.d("animatedValue","currentAimatedValuew: "- currentValue);
                lpScrollview.height = SizeUtil.dip2px(context,108 - currentValue);
                rlScrollview.setLayoutParams(lpScrollview);
            }
        });*/
    }

    @Override
    public ListView getListViewLeft() {
        return listviewLeft;
    }

    @Override
    public ListView getListViewRight() {
        return listviewRight;
    }

    @Override
    public void finishActivity() {
        setResult(RESULT_OK, feedBackData);
        finish();
    }


}
