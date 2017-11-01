package com.ztstech.vgmate.activitys.category_info;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import com.ztstech.vgmate.weigets.ExpandableLayout;

import java.util.List;

/**
 * Created by zhiyuan on 2017/9/2.
 */


public class CategoryTagsContract {
    interface IView {
        /**
         * 获取左侧listView
         */
        ListView getListViewLeft();

        /**
         * 获取右侧listView
         */
        ListView getListViewRight();

        /**
         * 获取自定义控件
         */
        ExpandableLayout getExpandableLayout();
        /**
         * 通过将hint布局和listview布局平移来改变scrollview的可见高度
         */
        void setScrollViewMultiLines();
        /**
         * 通过将hint布局和listview布局平移来改变scrollview的可见高度
         */
        void setScrollViewSingleLine();

        void finishActivity();
    }

    public interface IPresenter {
        /**
         * 向ExpandableLayout添加一个view
         */
        View addAView(View view);

        /**
         * 从ExpandableLayout移除一个view
         */
        void removeAView(View view);
        /**
         * 获取子view在控件中保存的索引
         */
        int getChildViewIndex(View view);

        void clearAllChildViews();
        void addNewChildViews(List<View> childViews);

        /**
         * 获取自定义控件
         */
        ExpandableLayout getLayoutTags();
        /**
         * 当自定义控件添加了一行layout 增加scrollview的显示高度
         */
        void afterLineAdded();
        /**
         * 当自定义控件刷新 判断是否只剩一行 是的话减少scrollview显示高度
         */
        void afterLineDeleted();
        /**
         * 将所选标签数据返回
         */
        Intent getFeedBackData();
    }
}