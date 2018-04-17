package com.ztstech.vgmate.weigets;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.location_select.adapter.AreaApapter;
import com.ztstech.vgmate.activitys.location_select.adapter.CityAdapter;
import com.ztstech.vgmate.activitys.location_select.adapter.ProvinceAdapter;
import com.ztstech.vgmate.data.beans.LocationBean;
import com.ztstech.vgmate.utils.LocationUtils;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.utils.ViewUtils;

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

    private ProvinceAdapter adapterProvince;
    private CityAdapter adapterCity;
    private AreaApapter adapterArea;

    private List<LocationBean> list_province;
    private List<LocationBean.CityBean> list_city;
    private List<LocationBean.CityBean.SiteBean> list_area;

    private SelectLocationCallBack callback;

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

    ListView lvProvince,lvCity,lvArea;

    /** 所选择的市是否有三级区县 */
    boolean isHasThree = true;

    public LocationSelectDialog(Context context,int position,String code,SelectLocationCallBack callback){
        super(context,R.style.transdialog);
        this.context = context;
        this.code = code;
        this.position = position;
        this.callback = callback;
        if (code != null && code.length() == 6){
            psid = code.substring(0,2) + "0000";
            csid = code.substring(0,4) + "00";
            asid = code;
            province = LocationUtils.getProvinceNameByAreaCode(code);
            city = LocationUtils.getCityNameByAreaCode(code);
            area = LocationUtils.getAName(code);
        }
        initView();
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
        ViewUtils.setDialogFullScreen(this);
        View topView = content.findViewById(R.id.topView);
        lvProvince = content.findViewById(R.id.listview_province);
        lvCity = content.findViewById(R.id.listview_city);
        lvArea = content.findViewById(R.id.listview_area);
        topView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        list_province = LocationUtils.getLocationList();
        if (list_province == null){
            ToastUtil.toastCenter(getContext(),"数据解析未完成，请稍后再试");
            return;
        }
        list_city = new ArrayList<>();
        list_area = new ArrayList<>();
        pPosition = findProPositionBypid(psid);
        if (pPosition < list_province.size()){
            list_city.addAll(list_province.get(pPosition).getCity());
        }
        cPosition = findCityCositionBycid(csid);
        if (cPosition < list_city.size()){
            list_area.addAll(list_city.get(cPosition).getSite());
        }
        aPosition = findDisCositionBycid(asid);
        if (aPosition < list_area.size()) {
            list_area.get(aPosition).setSelected(true);
        }
        adapterProvince = new ProvinceAdapter(list_province, context);
        adapterCity = new CityAdapter(list_city, context);
        adapterArea = new AreaApapter(list_area, context);
        lvProvince.setAdapter(adapterProvince);
        lvCity.setAdapter(adapterCity);
        lvArea.setAdapter(adapterArea);
        if (position == 0) {
            selectProvince();
        }else if (position == 1){
            selectCity();
        }else{
            selectArea();
        }
        Window window = getWindow();
        if (window != null) {
            window.setWindowAnimations(0);
        }
        initListener();
        setContentView(content);
        show();
//        Window dialogWindow = getWindow();
//        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
//        layoutParams.width= ViewGroup.LayoutParams.MATCH_PARENT;
//        layoutParams.height= ViewUtils.getPhoneHeight(context) - ViewUtils.dp2px(context,50);
//        dialogWindow.setGravity(Gravity.TOP);
////        layoutParams.alpha = 0f;
////        layoutParams.x = 0;
////        layoutParams.y = ViewUtils.dp2px(context,150);
//        dialogWindow.setAttributes(layoutParams);
    }

    private void initListener() {
        lvProvince.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list_city == null) {
                    list_city = new ArrayList<>();
                } else {
                    list_city.clear();
                }
                if (pPosition != -1) {
                    list_province.get(pPosition).setSelected(false);
                }
                LocationBean pBean = list_province.get(position);
                pBean.setSelected(true);
                pPosition = position;
                adapterProvince.notifyDataSetChanged();
                list_city.addAll(pBean.getCity());
                adapterCity.notifyDataSetChanged();
                psid = pBean.getSid();
                province = pBean.getSname();
                //重新选择省把城市和地区置为未选状态
                city = "";
                area = "";
                cPosition = -1;
                aPosition = -1;
                selectCity();
                if (province.contains("香港") || province.contains("澳门")  //港澳台目前只有一级选择
                        || province.contains("台湾")){
                    commit();
                    dismiss();
                }
            }
        });
        lvCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list_area == null) {
                    list_area = new ArrayList<>();
                } else {
                    list_area.clear();
                }
                if (cPosition != -1) {
                    list_city.get(cPosition).setSelected(false);
                }
                LocationBean.CityBean cBean = list_city.get(position);
                cBean.setSelected(true);
                cPosition = position;
                adapterCity.notifyDataSetChanged();
                adapterArea.notifyDataSetChanged();
                city = cBean.getSname();
                csid = cBean.getSid();
                selectArea();
                //重新选择城市把地区置为未选状态
                area = "";
                aPosition = -1;
                if (cBean.getSite() != null) {
                    list_area.addAll(cBean.getSite());
                    isHasThree = true;
                }else {
                    isHasThree = false;
                    dismiss();
                    commit();
                }
            }
        });
        lvArea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (aPosition != -1) {
                    //把上一个选择的标志置为未选状态
                    list_area.get(aPosition).setSelected(false);
                }
                LocationBean.CityBean.SiteBean aBean = list_area.get(position);
                aPosition = position;
                adapterArea.notifyDataSetChanged();
                aBean.setSelected(true);
                area = aBean.getSname();
                asid = aBean.getSid();
                city = LocationUtils.getCityNameByAreaCode(asid);
                commit();
            }
        });
    }

    /**
     * 点击提交
     */
    private void commit() {
        if (pPosition == -1) {
//            ToastUtil.toastCenter(context, "请选择地点!");
//            return;
        }
        Intent intent = new Intent();
        if (province.contains("香港") || province.contains("澳门")  //港澳台目前只有一级选择
                || province.contains("台湾")) {
            callback.onSelectLocation(province,city,area,psid);
        } else if (!isHasThree){  //部分市没有三级区
            callback.onSelectLocation(province,city,area,csid);
        }else {
//            if (cPosition == -1) {
//                ToastUtil.toastCenter(context, "请选择城市!");
//                return;
//            }
//            if (aPosition == -1) {
//                ToastUtil.toastCenter(context, "请选择区县!");
//                return;
//            }
            callback.onSelectLocation(province,city,area,asid);
        }
        dismiss();
    }

    /**
     * 选择了省份tab
     */
    private void selectProvince() {
        lvProvince.setVisibility(View.VISIBLE);
        lvCity.setVisibility(View.GONE);
        lvArea.setVisibility(View.GONE);
        position = 0;
        lvProvince.setVisibility(View.VISIBLE);
        lvCity.setVisibility(View.GONE);
        lvArea.setVisibility(View.GONE);
    }

    /**
     * 选择了城市tab
     */
    private void selectCity() {
//        if (province == null || province.isEmpty()) {
//            ToastUtil.toastCenter(context, "请先选择省份");
//            return;
//        }
        position = 1;
        lvProvince.setVisibility(View.GONE);
        lvCity.setVisibility(View.VISIBLE);
        lvArea.setVisibility(View.GONE);

    }

    /**
     * 选择了县区tab
     */
    private void selectArea() {
        if (province == null || province.isEmpty()
                || city == null || city.isEmpty()) {
//            ToastUtil.toastCenter(context, "请先选择省份和城市");
//            return;
        }
        position = 2;
        lvProvince.setVisibility(View.GONE);
        lvCity.setVisibility(View.GONE);
        lvArea.setVisibility(View.VISIBLE);
    }

    public interface SelectLocationCallBack{
        void onSelectLocation(String province,String city,String district,String code);
    }

    /**
     * 根据编码找到在list中位置,用于在adapter中显示
     */
    private int findProPositionBypid(String code){
        if (list_province == null || list_province.size() == 0){
            return 0;
        }
        for (int i = 0;i < list_province.size(); i++){
            if (TextUtils.equals(code,list_province.get(i).getSid())){
                pPosition = i;
                return i;
            }
        }
        return 0;
    }

    /**
     * 根据编码找到在list中位置,用于在adapter中显示
     */
    private int findCityCositionBycid(String code){
        if (list_city == null || list_city.size() == 0){
            return 0;
        }
        for (int i = 0;i < list_city.size(); i++){
            if (TextUtils.equals(code,list_city.get(i).getSid())){
                cPosition = i;
                return i;
            }
        }
        return 0;
    }

    /**
     * 根据编码找到在list中位置,用于在adapter中显示
     */
    private int findDisCositionBycid(String code){
        if (list_area == null || list_area.size() == 0){
            return 0;
        }
        for (int i = 0;i < list_area.size(); i++){
            if (TextUtils.equals(code,list_area.get(i).getSid())){
                aPosition = i;
                return i;
            }
        }
        return 0;
    }

}
