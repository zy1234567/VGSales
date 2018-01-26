package com.ztstech.vgmate.activitys.location_select;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.location_select.adapter.AreaApapter;
import com.ztstech.vgmate.activitys.location_select.adapter.CityAdapter;
import com.ztstech.vgmate.activitys.location_select.adapter.ProvinceAdapter;
import com.ztstech.vgmate.data.beans.LocationBean;
import com.ztstech.vgmate.utils.HUDUtils;
import com.ztstech.vgmate.utils.LocationUtils;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhiyuan on 2017/9/22.
 */

public class LocationSelectDialog extends Dialog implements View.OnClickListener {


    /**返回结果代码*/
    public static final String RESULT_CODE = "code";
    /**返回结果名称*/
    public static final String RESULT_NAME = "value";
    /**省*/
    public static final String RESULT_P = "result_p";
    /**市*/
    public static final String RESULT_C = "result_c";
    /**区*/
    public static final String RESULT_A = "result_A";


    View lineProvince;
    RelativeLayout rlProvince;
    View lineCity;
    RelativeLayout rlCity;
    View lineArea;
    RelativeLayout rlArea;
    TextView tvCuerrentSelect;
    ListView lvProvince;
    ListView lvCity;
    ListView lvArea;
    /**灰色背景区域*/
    private LinearLayout llParent;

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

    private OnLocationSelectListener listener;


    public LocationSelectDialog(@NonNull Context context) {
        super(context,  R.style.dialog);
        ViewUtils.setDialogFullScreen(this);

        View content = getLayoutInflater().inflate(R.layout.dialog_location_info_select, null);

        lineProvince = content.findViewById(R.id.line_province);
        rlProvince = content.findViewById(R.id.rl_province);
        lineCity = content.findViewById(R.id.line_city);
        rlCity = content.findViewById(R.id.rl_city);
        lineArea = content.findViewById(R.id.line_area);
        rlArea = content.findViewById(R.id.rl_area);
        tvCuerrentSelect = content.findViewById(R.id.tv_cuerrent_select);
        lvProvince = content.findViewById(R.id.listview_province);
        lvCity = content.findViewById(R.id.listview_city);
        lvArea = content.findViewById(R.id.listview_area);
        llParent = content.findViewById(R.id.ll_parent);

        Window window = getWindow();
        if (window != null) {
            window.setWindowAnimations(0);
        }



        setContentView(content);

        kProgressHUD = HUDUtils.create(context);
        kProgressHUD.setLabel("正在初始化");
        kProgressHUD.show();

        Observable.create(new ObservableOnSubscribe<Void>() {
            @Override
            public void subscribe(@io.reactivex.annotations.NonNull ObservableEmitter<Void> e) throws Exception {
                initData();
                e.onNext(null);
                e.onComplete();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Void>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull Void aVoid) {
                        kProgressHUD.dismiss();
                        initListener();
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void setOnLocationSelectedListener(OnLocationSelectListener listener) {
        this.listener = listener;
    }

    private void initData() {
//        String s = CommonUtil.getDataFromAssets(getContext(), "location.txt");
//        list_province = new Gson().fromJson(s, new TypeToken<List<LocationBean>>() {
//        }.getType());
        list_province = LocationUtils.getLocationList();
    }

    @OnClick({R.id.img_back, R.id.tv_save, R.id.rl_province, R.id.rl_city, R.id.rl_area})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                dismiss();
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
            default:
                break;
        }
    }

    /**
     * 点击提交
     */
    private void commit() {
        if (pPosition == -1) {
            ToastUtil.toastCenter(getContext(), "请选择地点!");
            return;
        }
        Intent intent = new Intent();
        if (province.contains("香港") || province.contains("澳门")  //港澳台目前只有一级选择
                || province.contains("台湾")) {
            intent.putExtra(RESULT_NAME, province);
            intent.putExtra(RESULT_CODE, psid);
            intent.putExtra(RESULT_P, psid);
            intent.putExtra(RESULT_C, csid);
            intent.putExtra(RESULT_A, asid);

            if (listener != null) {
                listener.onLocationSelected(province, psid, csid, asid);
            }
            dismiss();
        } else {
            if (cPosition == -1) {
                ToastUtil.toastCenter(getContext(), "请选择城市!");
                return;
            }
            if (aPosition == -1) {
                ToastUtil.toastCenter(getContext(), "请选择区县!");
                return;
            }
            intent.putExtra(RESULT_NAME, province + "-" + city + "-" + area);
            intent.putExtra(RESULT_CODE, asid);
            intent.putExtra(RESULT_P, psid);
            intent.putExtra(RESULT_C, csid);
            intent.putExtra(RESULT_A, asid);

            if (listener != null) {
                listener.onLocationSelected(province + "-" + city + "-" + area, psid, csid, asid);
            }
            dismiss();
        }

    }



    private void initListener() {
        list_city = new ArrayList<>();
        list_area = new ArrayList<>();
        adapterProvince = new ProvinceAdapter(list_province, getContext());
        adapterCity = new CityAdapter(list_city, getContext());
        adapterArea = new AreaApapter(list_area, getContext());
        lvProvince.setAdapter(adapterProvince);
        lvCity.setAdapter(adapterCity);
        lvArea.setAdapter(adapterArea);

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
                for (int i = 0;i < list_province.size();i++){
                    list_province.get(i).setSelected(false);
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

                if (province.contains("香港") || province.contains("澳门")  //港澳台目前只有一级选择
                        || province.contains("台湾")){
                    commit();
                }else {
                    selectCity();
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

                commit();
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
            ToastUtil.toastCenter(getContext(), "请先选择省份");
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
            ToastUtil.toastCenter(getContext(), "请先选择省份和城市");
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

    @Override
    public void onClick(View view) {
        if (view == llParent) {
            dismiss();
        }
    }

    public interface OnLocationSelectListener {

        /**
         * 地址选中事件
         * @param locationName 地址名称
         * @param locP  地址，省
         * @param locC  地址，市
         * @param locA  地址，区
         */
        void onLocationSelected(String locationName, String locP, String locC, String locA);
    }
}
