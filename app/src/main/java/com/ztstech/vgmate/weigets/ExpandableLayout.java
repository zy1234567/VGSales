package com.ztstech.vgmate.weigets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.category_info.CategoryTagsContract;
import com.ztstech.vgmate.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhiyuan on 2017/9/2.
 */

public class ExpandableLayout extends LinearLayout {
    private Context context;
    /**
     * 被添加到控件的所有layout
     */
    private List<LinearLayout> linesList;
    /**
     * 每一行layout的剩余空间宽度，与linesList对应
     */
    private List<Integer> leftWidthsList;
    /**
     * 被添加到layout的所有childView
     */
    private List<View> childsList;

    /**
     * 用于在行数变化时通知界面 更改scrollview显示的高度
     */
    CategoryTagsContract.IPresenter presenter;

    private boolean initFinished = false;
    private int screenWidth;
    private boolean defaultViewExist = false;

    public ExpandableLayout(Context context) {
        super(context);
        this.context = context;
        this.setOrientation(VERTICAL);
        linesList = new ArrayList<>();
        childsList = new ArrayList<>();
        leftWidthsList = new ArrayList<>();

        initANewLine();
    }

    public ExpandableLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.setOrientation(VERTICAL);
        linesList = new ArrayList<>();
        childsList = new ArrayList<>();
        leftWidthsList = new ArrayList<>();

        initANewLine();
    }

    public ExpandableLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.setOrientation(VERTICAL);
        linesList = new ArrayList<>();
        childsList = new ArrayList<>();
        leftWidthsList = new ArrayList<>();

        initANewLine();
    }

    /**
     * 初始化时添加一行，添加一个默认view，获取屏幕宽度
     */
    private void initANewLine() {
        DisplayMetrics dm2 = getResources().getDisplayMetrics();
        screenWidth = dm2.widthPixels- ViewUtils.dp2px(context,6);
        initFinished = false;
        addANewLineView();
        linesList.get(0).addView(LayoutInflater.from(context).inflate(R.layout.layout_item_expandable_child, this, false));
        defaultViewExist = true;
    }


    /**
     * 为控件添加一行LinearLayout，用于放置更多的childView
     * <p>
     * 由于动态获取困难，给了layout默认宽度
     * 需要的话可以和addNewChildView一样传入参数，符合更多需求
     *
     * @return
     */
    public LinearLayout addANewLineView() {
        final LinearLayout layout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.layout_item_expandable_line, this, false);
        layout.setId(generateAViewId());
        this.addView(layout);
        linesList.add(layout);
        leftWidthsList.add(screenWidth - ViewUtils.dp2px(context, 9));//要看到的是屏幕宽度能放下多少view，这里减多少待定
        return layout;
    }

    /**
     * 向layout内添加一个view
     *
     * @return
     */
    public View addANewChildView(View view, CategoryTagsContract.IPresenter presenter) {
        if (!initFinished) {
            linesList.get(0).removeAllViews();
            defaultViewExist = false;
            this.presenter = presenter;
            initFinished = true;
        }
        if(defaultViewExist){
            linesList.get(0).removeAllViews();
            defaultViewExist = false;
        }
        putViewInLayout(view);
        childsList.add(view);
        return view;
    }

    /**
     * 从控件移除一行layout
     *
     * @param layout
     */
    public void removeLineView(LinearLayout layout) {
        /*从layout中移除所有childView*/
        layout.removeAllViews();
        /*从控件中移除layout*/
        this.removeView(layout);
        /*将这行剩余空间的记录删除*/
        leftWidthsList.remove(linesList.indexOf(layout));
        /*从list中移除layout*/
        linesList.remove(layout);
    }

    /**
     * 从layout移除一个view
     *
     * @param view
     */
    public void removeChildView(View view) {
        /*从layout中移除view*/
        LinearLayout layout = (LinearLayout) view.getTag();
        layout.removeView(view);
        /*从list中移除view*/
        childsList.remove(view);
        /*将占用的宽度拿回*/
        int leftWidth = leftWidthsList.get(linesList.indexOf(layout)) + view.getMeasuredWidth();
        leftWidthsList.set(linesList.indexOf(layout), leftWidth);
        /*如果这一行layout为空了，移除，第一行除外*/
        if (layout.getChildCount() == 0 && layout!=this.getChildAt(0)) {
            removeLineView(layout);
        }
        refreshLayouts();
    }

    /**
     * 将view添加至第一行空间足够的layout,都没有空间就新增一行layout
     *
     * @param view
     */
    private void putViewInLayout(View view) {
        LinearLayout layout;
        int leftWidth;
        boolean added = false;
        /** 动态获取要添加的view的宽度 */
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int viewWidth = view.getMeasuredWidth();
        /** 遍历每一行的剩余宽度 充足的话放入 */
        for (int i = 0; i < linesList.size(); i++) {
            leftWidth = leftWidthsList.get(i);
            if (leftWidth > viewWidth) {
                layout = linesList.get(i);
                view.setTag(layout);//为了方便之后根据view获得其所在的行layout
                layout.addView(view);
                leftWidth -= viewWidth;
                leftWidthsList.set(i, leftWidth);
                added = true;
                return;
            }
        }
        /** 每一行都没有足够的空间，新增一行*/
        if (!added) {
            addANewLineView();
            LinearLayout lastLine = linesList.get(linesList.size() - 1);
            view.setTag(lastLine);
            lastLine.addView(view);
            /** 添加view后记录剩余宽度*/
            leftWidth = leftWidthsList.get(linesList.size() - 1) - view.getMeasuredWidth();
            leftWidthsList.set(linesList.size() - 1, leftWidth);
            presenter.afterLineAdded();
        }
    }

    /**
     * 刷新控件，重新填充childList中的view，例如在删除了某个view之后
     *
     * @return
     */
    public void refreshLayouts() {
        /** 将每个view从layout中移除 */
        for (View view : childsList) {
            LinearLayout layout = (LinearLayout) view.getTag();
            layout.removeView(view);
            view.setTag(null);
        }
        /** 移除第一行之外的layout */
        for (int i = linesList.size()-1; i > 0; i--) {
            removeLineView(linesList.get(i));
        }
        /** 将所有leftWidth记录清空,给第一行默认值*/
        leftWidthsList.clear();
        leftWidthsList.add(screenWidth);
        /** 重新将view们填入*/
        for (View view : childsList) {
            putViewInLayout(view);
        }
        /** 如果view都被删除了 添加默认view */
        if (childsList.size() == 0) {
            linesList.get(0).addView(LayoutInflater.from(context).inflate(R.layout.layout_item_expandable_child, this, false));
            defaultViewExist = true;
        }
        presenter.afterLineDeleted();
    }


    /**
     * 向控件内添加一堆childView
     *
     * @param childViews
     */
    public void addNewChildViews(List<View> childViews) {
        /** 将每个view从layout中移除 */
        for (View view : childsList) {
            LinearLayout layout = (LinearLayout) view.getTag();
            layout.removeView(view);
            view.setTag(null);
        }
        /** 移除第一行之外的layout */
        for (int i = linesList.size()-1; i > 0; i--) {
            removeLineView(linesList.get(i));
        }
        /** 将所有leftWidth记录清空,给第一行默认值*/
        leftWidthsList.clear();
        leftWidthsList.add(screenWidth);
        childsList.clear();
        childsList.addAll(childViews);
        /** 重新将view们填入*/
        for (View view : childsList) {
            putViewInLayout(view);
        }
        /** 如果view都被删除了 添加默认view */
        if (childsList.size() == 0) {
            linesList.get(0).addView(LayoutInflater.from(context).inflate(R.layout.layout_item_expandable_child, this, false));
            defaultViewExist = true;
        }
        presenter.afterLineDeleted();
    }

    /**
     * 清除所有view，到可以直接添加view的状态
     */
    public void clearAllChildViews(){
        this.removeAllViews();
        linesList.clear();
        childsList.clear();
        leftWidthsList.clear();
        addANewLineView();
    }

    /**
     * 获取子view在控件中保存的索引
     * @param view
     * @return
     */
    public int getChildViewIndex(View view){
        return childsList.indexOf(view);
    }

    /**
     * 行layout数量变化监听
     */
    public interface LinesChangedListener {
        void afterLineAdded();

        void afterLineDeleted();
    }

    /**
     * 生成id
     * api17以上包含的方法，为了兼容，复制方法代码在此应用
     */
    private final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    public int generateAViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    /**
     * 需要添加渲染的childView的id
     */
    private int childResource = 0;

    public void setChildResource(int resource) {
        childResource = resource;
    }

    public int getChildResource() {
        return childResource;
    }

    public List<View> getChildViewList() {
        return childsList;
    }
}