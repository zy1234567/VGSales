package com.ztstech.vgmate.activitys.category_info;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.ztstech.vgmate.activitys.category_info.adapter.FatCategoriesAdapter;
import com.ztstech.vgmate.activitys.category_info.adapter.SubCategoriesAdapter;
import com.ztstech.vgmate.data.beans.CategoriesBean;
import com.ztstech.vgmate.utils.HUDUtils;
import com.ztstech.vgmate.weigets.ExpandableLayout;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;

/**
 * Created by zhiyuan on 2017/9/2.
 */
public class CategoryTagsPresenter implements CategoryTagsContract.IPresenter, ExpandableLayout.LinesChangedListener {



    Context context;
    ExpandableLayout layoutTags;
    CategoryTagsContract.IView iView;

    private ListView listViewLeft;
    private ListView listViewRight;
    private FatCategoriesAdapter fatAdapter;
    private SubCategoriesAdapter subAdapter;
    private List<CategoriesBean> fatDatas;
    private List<CategoriesBean.HobbiesBean> subDatas;
    private int fatPositionNow = 0;
    private boolean allSubSelected = false;
    String selectedIds, selectedNames;
    String feedBackIds, feedBackNames;
    private KProgressHUD kProgressHUD;

    public CategoryTagsPresenter(Context context, CategoryTagsContract.IView iView, String selectedIds, String selectedNames) {
        this.context = context;
        this.iView = iView;
        listViewLeft = iView.getListViewLeft();
        listViewRight = iView.getListViewRight();
        layoutTags = iView.getExpandableLayout();
//        dataSource = new MineDataSource();
        kProgressHUD = HUDUtils.create(context);
        this.selectedIds = selectedIds;
        this.selectedNames = selectedNames;

        initActivity();
    }

    private void initActivity() {
        /**
         * 从assets文件中获取json串，准备list数据
         */
        fatDatas = new Gson().fromJson(getDataFromAssets("categories.txt"), new TypeToken<List<CategoriesBean>>() {
        }.getType());
        /**
         * 使子类默认显示第一类
         */
        subDatas = new ArrayList<>();
        ///////////全部 2 //////////subDatas.add(CategoriesBean.getTotalSubBean());
        subDatas.addAll(fatDatas.get(0).getHobbies());

        initListViews();

        //layoutTags.addNewTextView(null);
    }

    /**
     * 初始化listView
     */
    private void initListViews() {
        fatAdapter = new FatCategoriesAdapter(fatDatas, context);
        subAdapter = new SubCategoriesAdapter(subDatas, context, this);
        if (!TextUtils.isEmpty(selectedIds)) {
            subAdapter.initTags(selectedIds);
        }
        listViewLeft.setAdapter(fatAdapter);
        listViewRight.setAdapter(subAdapter);

        listViewLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fatPositionNow = position;
                /*为子类根据父类点击更换对应列表数据*/
                subDatas.clear();
                ////////// 全部 1 /////////subDatas.add(CategoriesBean.getTotalSubBean());
                subDatas.addAll(fatDatas.get(position).getHobbies());
                subAdapter.notifyDataSetChanged();
                /*为父类列表切换样式*/
                fatAdapter.notifyDataSetChanged(fatPositionNow);
            }
        });

    }

    /**
     * 从assets文件中获得Json串
     *
     * @param fileName
     * @return
     */
    public String getDataFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public View addAView(View view) {
        return layoutTags.addANewChildView(view, this);
    }

    @Override
    public void removeAView(View view) {
        layoutTags.removeChildView(view);
    }

    @Override
    public int getChildViewIndex(View view) {
        return layoutTags.getChildViewIndex(view);
    }

    @Override
    public void clearAllChildViews() {
        layoutTags.clearAllChildViews();
    }

    @Override
    public void addNewChildViews(List<View> childViews) {
        layoutTags.addNewChildViews(childViews);
    }

    @Override
    public ExpandableLayout getLayoutTags() {
        return layoutTags;
    }

    /**
     * 将所选标签数据返回
     */
    public Intent getFeedBackData() {
        Intent feedBackIntent = new Intent();
        StringBuilder builder = new StringBuilder();
        for (String id : subAdapter.getSelectedIds()) {
            builder.append(id);
            builder.append(",");
        }
        if (!builder.toString().isEmpty()){
            feedBackIds = builder.toString().substring(0, builder.length() - 1);
        }
        builder.delete(0, builder.length());
        for (String name : subAdapter.getSelectedNames()) {
            builder.append(name);
            builder.append("、");
        }
        if (!builder.toString().isEmpty()) {
            feedBackNames = builder.toString().substring(0, builder.length() - 1);
        }
        feedBackIntent.putExtra(CategoryTagsActivity.PARAM_ID, feedBackIds);
        feedBackIntent.putExtra(CategoryTagsActivity.PARAM_NAME, feedBackNames);
        return feedBackIntent;
    }



    /**
     * scrollview 是否展开
     */
    private boolean hasExpand = false;

    /**
     * 当自定义控件添加了一行layout 增加scrollview的显示高度
     */
    @Override
    public void afterLineAdded() {
        if (!hasExpand) {
            int count = layoutTags.getChildCount();
            if (count > 1) {
                iView.setScrollViewMultiLines();
                hasExpand = true;
            }
        }
    }

    /**
     * 当自定义控件刷新 判断是否只剩一行 是的话减少scrollview显示高度
     */
    @Override
    public void afterLineDeleted() {
        if (hasExpand) {
            int count = layoutTags.getChildCount();
            if (count == 1) {
                iView.setScrollViewSingleLine();
                hasExpand = false;
            }
        }
    }
}