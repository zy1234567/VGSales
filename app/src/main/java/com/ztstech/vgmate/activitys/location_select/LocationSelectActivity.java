package com.ztstech.vgmate.activitys.location_select;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.location_select.adapter.AreaApapter;
import com.ztstech.vgmate.activitys.location_select.adapter.CityAdapter;
import com.ztstech.vgmate.activitys.location_select.adapter.ProvinceAdapter;
import com.ztstech.vgmate.data.beans.LocationBean;
import com.ztstech.vgmate.utils.CommonUtil;
import com.ztstech.vgmate.utils.HUDUtils;
import com.ztstech.vgmate.utils.ToastUtil;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by smm on 2017/6/23.
 * 三级地区选择界面
 */

public class LocationSelectActivity extends AppCompatActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.line_province)
    View lineProvince;
    @BindView(R.id.rl_province)
    RelativeLayout rlProvince;
    @BindView(R.id.line_city)
    View lineCity;
    @BindView(R.id.rl_city)
    RelativeLayout rlCity;
    @BindView(R.id.line_area)
    View lineArea;
    @BindView(R.id.rl_area)
    RelativeLayout rlArea;
    @BindView(R.id.tv_cuerrent_select)
    TextView tvCuerrentSelect;
    @BindView(R.id.listview_province)
    ListView lvProvince;
    @BindView(R.id.listview_city)
    ListView lvCity;
    @BindView(R.id.listview_area)
    ListView lvArea;

    ProvinceAdapter adapterProvince;
    CityAdapter adapterCity;
    AreaApapter adapterArea;

    List<LocationBean> list_province;
    List<LocationBean.CityBean> list_city;
    List<LocationBean.CityBean.SiteBean> list_area;

    /**
     * 当前所选择的tab 默认是选择省份
     */
    int position = 0;

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

    KProgressHUD kProgressHUD;
    /**
     * 来自机构修改信息界面（false为注册）
     */
    boolean updateOrgInfo = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_select);
        ButterKnife.bind(this);
        initData();
        initListener();
    }

    private void initData() {
        tvSave.setText("确认");
        title.setText("地区选择");
        String s = CommonUtil.getDataFromAssets(this, "location.txt");
        list_province = new Gson().fromJson(s, new TypeToken<List<LocationBean>>() {
        }.getType());
        updateOrgInfo = getIntent().getBooleanExtra("updateOrgInfo", false);
        if (updateOrgInfo) {
            kProgressHUD = HUDUtils.create(this);
        }

        list_city = new ArrayList<>();
        list_area = new ArrayList<>();
        adapterProvince = new ProvinceAdapter(list_province, this);
        adapterCity = new CityAdapter(list_city, this);
        adapterArea = new AreaApapter(list_area, this);
        lvProvince.setAdapter(adapterProvince);
        lvCity.setAdapter(adapterCity);
        lvArea.setAdapter(adapterArea);

    }

    @OnClick({R.id.img_back, R.id.tv_save, R.id.rl_province, R.id.rl_city, R.id.rl_area})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_save:
                commit();
                break;
            case R.id.rl_province:
                selectProvince();
                break;
            case R.id.rl_city:
                selectCity();
                break;
            case R.id.rl_area:
                selectArea();
                break;
        }
    }

    /**
     * 点击提交
     */
    private void commit() {
        if (pPosition == -1) {
            ToastUtil.toastCenter(this, "请选择地点!");
            return;
        }
        Intent intent = new Intent();
        if (province.contains("香港") || province.contains("澳门")  //港澳台目前只有一级选择
                || province.contains("台湾")) {
            intent.putExtra("value", province);
            intent.putExtra("code", psid);
            setResult(RESULT_OK, intent);
        } else {
            if (cPosition == -1) {
                ToastUtil.toastCenter(this, "请选择城市!");
                return;
            }
            if (aPosition == -1) {
                ToastUtil.toastCenter(this, "请选择区县!");
                return;
            }
            intent.putExtra("value", province + "-" + city + "-" + area);
            intent.putExtra("code", asid);
            setResult(RESULT_OK, intent);
        }
        if (updateOrgInfo) {
            return;
        }
        finish();
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
                tvCuerrentSelect.setText(province);
                //重新选择省把城市和地区置为未选状态
                city = "";
                area = "";
                cPosition = -1;
                aPosition = -1;
                selectCity();
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
                list_area.addAll(cBean.getSite());
                adapterArea.notifyDataSetChanged();
                city = cBean.getSname();
                csid = cBean.getSid();
                tvCuerrentSelect.setText(province + city);
                selectArea();
                //重新选择城市把地区置为未选状态
                area = "";
                aPosition = -1;
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
                tvCuerrentSelect.setText(province + city + area);
            }
        });
    }

    /**
     * 选择了省份tab
     */
    private void selectProvince() {
        lvProvince.setVisibility(View.VISIBLE);
        lvCity.setVisibility(View.GONE);
        lvArea.setVisibility(View.GONE);
        position = 0;
        lineProvince.setVisibility(View.VISIBLE);
        lineCity.setVisibility(View.INVISIBLE);
        lineArea.setVisibility(View.INVISIBLE);
        lvProvince.setVisibility(View.VISIBLE);
        lvCity.setVisibility(View.GONE);
        lvArea.setVisibility(View.GONE);
    }

    /**
     * 选择了城市tab
     */
    private void selectCity() {
        if (province == null || province.isEmpty()) {
            ToastUtil.toastCenter(this, "请先选择省份");
            return;
        }
        position = 1;
        lineProvince.setVisibility(View.INVISIBLE);
        lineCity.setVisibility(View.VISIBLE);
        lineArea.setVisibility(View.INVISIBLE);
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
            ToastUtil.toastCenter(this, "请先选择省份和城市");
            return;
        }
        position = 2;
        lineProvince.setVisibility(View.INVISIBLE);
        lineCity.setVisibility(View.INVISIBLE);
        lineArea.setVisibility(View.VISIBLE);
        lvProvince.setVisibility(View.GONE);
        lvCity.setVisibility(View.GONE);
        lvArea.setVisibility(View.VISIBLE);
    }

}
