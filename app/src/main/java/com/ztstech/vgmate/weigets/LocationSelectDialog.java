package com.ztstech.vgmate.weigets;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.location_select.adapter.AreaApapter;
import com.ztstech.vgmate.activitys.location_select.adapter.CityAdapter;
import com.ztstech.vgmate.activitys.location_select.adapter.ProvinceAdapter;
import com.ztstech.vgmate.data.beans.LocationBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smm on 2017/12/11.
 * 地区选择dialog
 */

public class LocationSelectDialog extends Dialog {

    private Context context;

    /** 所要显示的是几级地区 */
    private int position;

    /** 当前选择的地区编码 */
    private String code;

    ProvinceAdapter adapterProvince;
    CityAdapter adapterCity;
    AreaApapter adapterArea;

    List<LocationBean> list_province;
    List<LocationBean.CityBean> list_city;
    List<LocationBean.CityBean.SiteBean> list_area;


    /**
     * 所选择的省市区
     */
    String province = "", city = "", area = "";

    /**
     * 所选择省市区sid
     */
    String psid = "", csid = "", asid = "";

    /**
     * 所选择项在列表中的位置
     */
    int pPosition = -1, cPosition = -1, aPosition = -1;

    public LocationSelectDialog(Context context,int position,String code){
        super(context);
        this.context = context;
        this.position = position;
        this.code = code;
    }

    public LocationSelectDialog(Context context) {
        super(context);
    }

    public LocationSelectDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LocationSelectDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private void initView(){
        View content = LayoutInflater.from(context).inflate(R.layout.dialog_select_loction,null);
        ListView lvProvince = content.findViewById(R.id.listview_province);
        ListView lvCity = content.findViewById(R.id.listview_city);
        ListView lvArea = content.findViewById(R.id.listview_area);

        list_city = new ArrayList<>();
        list_area = new ArrayList<>();
        adapterProvince = new ProvinceAdapter(list_province, context);
        adapterCity = new CityAdapter(list_city, context);
        adapterArea = new AreaApapter(list_area, context);
        lvProvince.setAdapter(adapterProvince);
        lvCity.setAdapter(adapterCity);
        lvArea.setAdapter(adapterArea);

    }

}
