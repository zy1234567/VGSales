package com.ztstech.vgmate.activitys.category_info.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.category_info.CategoryTagsContract;
import com.ztstech.vgmate.data.beans.CategoriesBean;
import com.ztstech.vgmate.utils.CategoryUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiyuan on 2017/9/2.
 */

public class SubCategoriesAdapter extends BaseAdapter {

    private List<CategoriesBean.HobbiesBean> subDataList;
    private Context context;
    private CategoryTagsContract.IPresenter presenter;

    /* 已选择的分类 id name*/
    private List<String> selectedIdList;
    private List<String> selectedNameList;

    public SubCategoriesAdapter(List<CategoriesBean.HobbiesBean> subDataList, Context context, CategoryTagsContract.IPresenter presenter) {
        this.subDataList = subDataList;
        this.context = context;
        this.presenter = presenter;
        selectedIdList = new ArrayList<>();
        selectedNameList = new ArrayList<>();
    }


    public void setSubDataList(List<CategoriesBean.HobbiesBean> newDataList) {
        subDataList = newDataList;
    }

    @Override
    public int getCount() {
        return subDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return subDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final CategoriesBean.HobbiesBean bean = (CategoriesBean.HobbiesBean) getItem(position);
        final SubHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_categories_right_add, parent, false);
            holder = new SubHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (SubHolder) convertView.getTag();
        }
        if (selectedIdList.contains(bean.getLid())) {//|| bean.isSelected()
            holder.setAdded();
        } else {
            holder.setAddDefault();
        }
        holder.tvAdd.setClickable(true);
        holder.tvName.setText(subDataList.get(position).getLname());
        holder.tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.setAdded();
                if (selectedIdList.size() == 0) {
                    View view = createPassTextView(bean.getLname(), bean.getLid());
                    presenter.addAView(view);
                } else {
                    View view = createWaitTextView(bean.getLname(), bean.getLid());
                    presenter.addAView(view);
                }
                selectedIdList.add(bean.getLid());
                //bean.setSelected(true);
                selectedNameList.add(bean.getLname());
                holder.tvAdd.setClickable(false);
            }
        });
        return convertView;
    }

    class SubHolder {
        TextView tvName;
        TextView tvAdd;
        TextView tvAdded;

        SubHolder(View view) {
            tvName = (TextView) view.findViewById(R.id.tv_right);
            tvAdd = (TextView) view.findViewById(R.id.tv_add);
            tvAdded = (TextView) view.findViewById(R.id.tv_added);

            tvAdd.setClickable(true);
            tvAdded.setClickable(false);
        }

        void setAddDefault() {
            tvAdd.setVisibility(View.VISIBLE);
            tvAdded.setVisibility(View.GONE);
        }

        void setAdded() {
            tvAdd.setVisibility(View.GONE);
            tvAdded.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 创建一个待审批标签
     *
     * @param name
     * @param id   所选择类别的id
     * @return
     */
    private View createWaitTextView(String name, String id) {
        final View view = LayoutInflater.from(context).inflate(R.layout.layout_item_expandable_child_wait, presenter.getLayoutTags(), false);
        final TextView tvTag = (TextView) view.findViewById(R.id.tv_tag);
        tvTag.setText(name);
        tvTag.setTag(id);
        ImageView imgDelete = (ImageView) view.findViewById(R.id.img_delete);
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedIdList.remove(tvTag.getTag());
                selectedNameList.remove(tvTag.getText());
                refreshTags(selectedIdList);
                notifyDataSetChanged();
            }
        });
        return view;
    }

    /**
     * 创建一个待审批标签
     *
     * @param name
     * @param id   所选择类别的id
     * @return
     */
    private View createPassTextView(String name, String id) {
        final View view = LayoutInflater.from(context).inflate(R.layout.layout_item_expandable_child_pass, presenter.getLayoutTags(), false);
        final TextView tvTag = (TextView) view.findViewById(R.id.tv_tag);
        tvTag.setText(name);
        tvTag.setTag(id);
        ImageView imgDelete = (ImageView) view.findViewById(R.id.img_delete);
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedIdList.remove(tvTag.getTag());
                selectedNameList.remove(tvTag.getText());
                refreshTags(selectedIdList);
                notifyDataSetChanged();
            }
        });
        return view;
    }

    public void initTags(String selectedIds) {
        String[] idsArray = selectedIds.split(",");
        for (int i = 0; i < idsArray.length; i++) {
            String id = idsArray[i];
            String name = CategoryUtil.getCategoryName(id);
            if (!"".equals(idsArray[i])) {
                if (i == 0) {
                    presenter.addAView(createPassTextView(name, id));
                } else {
                    presenter.addAView(createWaitTextView(name, id));
                }
                selectedIdList.add(id);
                selectedNameList.add(name);
            }
        }
    }

    public void refreshTags(List<String> selectedIds) {
        List<View> refreshViews = new ArrayList<>();
        for (int i = 0; i < selectedIds.size(); i++) {
            String id = selectedIds.get(i);
            String name = CategoryUtil.getCategoryName(id);
            if (!"".equals(id)) {
                if (i == 0) {
                    refreshViews.add(createPassTextView(name, id));
                } else {
                    refreshViews.add(createWaitTextView(name, id));
                }
            }
        }
        presenter.addNewChildViews(refreshViews);
    }


    public List<String> getSelectedIds() {
        return selectedIdList;
    }

    public List<String> getSelectedNames() {
        return selectedNameList;
    }

    //添加几个标签后 点击第一个标签移除  新的第一个标签不是蓝色

}