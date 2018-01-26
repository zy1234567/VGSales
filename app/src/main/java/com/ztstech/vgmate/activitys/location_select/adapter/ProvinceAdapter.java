package com.ztstech.vgmate.activitys.location_select.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.data.beans.LocationBean;

import java.util.List;


/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class ProvinceAdapter extends BaseAdapter {

    private List<LocationBean> entityList;
    private Context context;

    /** 当前选择的项在列表中的位置 */
    private int position;

    public ProvinceAdapter(List<LocationBean> entityList, Context context) {
        this.entityList = entityList;
        this.context = context;
        this.position = position;
    }

    @Override
    public int getCount() {
        if(entityList == null){
            return 0;
        }
        return entityList.size();
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
            convertView = View.inflate(context,R.layout.list_item_selection,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
//        }else{
//            holder = (ViewHolder) convertView.getTag();
//        }
        holder.textView.setText(entityList.get(i).getSname());
        if (entityList.get(i).isSelected()){
            holder.textView.setTextColor(context.getResources().getColor(R.color.weilai_color_003));
        }else {

        }
//        holder.img_selected.setVisibility(View.VISIBLE);
        return convertView;
    }

    static class ViewHolder{
        private TextView textView;
        private ImageView img_selected;
        public ViewHolder(View convertView) {
            textView = (TextView) convertView.findViewById(R.id.txt);
            img_selected = (ImageView) convertView.findViewById(R.id.img_selected);
        }
    }

    public void setPosition(int pi){

    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
