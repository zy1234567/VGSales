package com.ztstech.vgmate.activitys.category_info.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.data.beans.CategoriesBean;

import java.util.List;

/**
 * Created by zhiyuan on 2017/9/2.
 */

public class FatCategoriesAdapter extends BaseAdapter {
    private List<CategoriesBean> fatDataList;
    private Context context;
    private int selectedPosition;

    public FatCategoriesAdapter(List<CategoriesBean> fatDataList, Context context) {
        this.fatDataList = fatDataList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return fatDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return fatDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FatHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_categories_left, parent, false);
            holder = new FatHolder();
            holder.tvFatName = (TextView) convertView.findViewById(R.id.tv_left);
            convertView.setTag(holder);
        } else {
            holder = (FatHolder) convertView.getTag();
        }
        TextView textView = holder.tvFatName;
        textView.setText(fatDataList.get(position).getLname());
        if(selectedPosition==position){
            textView.setTextColor(ContextCompat.getColor(context, R.color.weilai_color_003));
            textView.setBackgroundColor(ContextCompat.getColor(context, R.color.weilai_color_002));
        }else {
            textView.setTextColor(ContextCompat.getColor(context,R.color.weilai_color_005));
            textView.setBackgroundColor(ContextCompat.getColor(context,R.color.weilai_color_001));
        }
        return convertView;
    }

    class FatHolder {
        TextView tvFatName;
    }

    public void notifyDataSetChanged(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        super.notifyDataSetChanged();
    }
}
