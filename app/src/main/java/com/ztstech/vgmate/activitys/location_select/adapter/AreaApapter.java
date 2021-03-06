package com.ztstech.vgmate.activitys.location_select.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.data.beans.LocationBean;

import java.util.List;


/**
 * Created by smm on 2017/6/23.
 */

public class AreaApapter extends BaseAdapter {

    private List<LocationBean.CityBean.SiteBean> cityList;
    private Context context;

    /** 所选择的项在列表中的位置 */
    int position;

    public AreaApapter(List<LocationBean.CityBean.SiteBean> cityList, Context context) {
        this.cityList = cityList;
        this.context = context;
        this.position = position;
    }

    @Override
    public int getCount() {
        if(cityList == null){
            return 0;
        }
        return cityList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
//        if (convertView == null){
            convertView = View.inflate(context, R.layout.list_item_selection,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
//        }else{
//            holder = (ViewHolder) convertView.getTag();
//        }
        holder.textView.setText(cityList.get(i).getSname());

        if (cityList.get(i).isSelected()){
            holder.textView.setTextColor(context.getResources().getColor(R.color.weilai_color_003));
        }

        return convertView;
    }

    static class ViewHolder{

        private TextView textView;

        public ViewHolder(View convertView) {
            textView = (TextView) convertView.findViewById(R.id.txt);
        }
    }
}
