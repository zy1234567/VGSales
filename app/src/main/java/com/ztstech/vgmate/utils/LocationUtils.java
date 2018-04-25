package com.ztstech.vgmate.utils;

import android.location.Location;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ztstech.vgmate.base.BaseApplication;
import com.ztstech.vgmate.base.BaseApplicationLike;
import com.ztstech.vgmate.data.beans.LocationBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Map;

/**
 * Created by zhiyuan on 2017/9/17.
 */

public class LocationUtils {

    /**
     * 地址 - 编码
     */
    private static Map<String, String> nameCodeMap;

    /**
     * 二级编码 - 三级编码
     */
    private static Map<String,String> twiceCodeMap;

    private static List<LocationBean> locationBeanList;

    /** 只有二级市没有三级区县的再放到一个list里 */
    private static List<String> secondList = new ArrayList<>();

    public static void init() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                nameCodeMap = new HashMap<>();
                twiceCodeMap = new HashMap<>();
                String locationJson = CommonUtil.getDataFromAssets(
                        BaseApplicationLike.getApplicationInstance(), "location.txt");
                Log.e("locationJson",locationJson + "");
                locationBeanList = new Gson().fromJson(locationJson,
                        new TypeToken<List<LocationBean>>() {
                        }.getType());
                if (locationBeanList != null) {
                    for (LocationBean bean : locationBeanList) {
                        nameCodeMap.put(bean.getSname(), bean.getSid());
                        if (bean.getCity() != null) {
                            for (LocationBean.CityBean city : bean.getCity()) {
                                nameCodeMap.put(city.getSname(), city.getSid());
                                if (city.getSite() == null || city.getSite().size() == 0){
                                    secondList.add(city.getSid());
                                }
                                if (city.getSite() != null) {
                                    for (LocationBean.CityBean.SiteBean site : city.getSite()) {
                                        nameCodeMap.put(site.getSname(), site.getSid());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    public static List<LocationBean> getLocationList() {
        return locationBeanList;
    }

    public static Map<String, String> getData() {
        return nameCodeMap;
    }

    /**
     * 根据地区代码获取地区名称（不区分省市区）
     *
     * @param locationCode
     * @return
     */
    public static String getLocationNameByCode(String locationCode) {
        if (nameCodeMap == null){
            return "";
        }
        for (Map.Entry<String, String> item : nameCodeMap.entrySet()) {
            if (TextUtils.equals(locationCode, item.getValue())) {
                return item.getKey();
            }
        }
        return null;
    }

    /**
     * 根据三级编码获得二级编码
     */
    public static String getSecondCode(String code){
        if (TextUtils.isEmpty(code) || code.length() < 4){
            return "";
        }
        String cityCode = code.substring(0, 4) + "00";
        return cityCode;
    }

    /**
     * 根据地区名称获取地区代码（不区分省市区）
     *
     * @param locationName
     * @return
     */
    public static String getLocationCodeByName(String locationName) {
        return nameCodeMap.get(locationName);
    }

    /**
     * 根据区县代码获取市级名称
     *
     * @param areaCode
     * @return
     */
    public static String getCityNameByAreaCode(String areaCode) {
        return getLocationNameByCode(getSecondCode(areaCode));
    }

    /**
     * 根据区县代码获取省级名称
     *
     * @param areaCode
     * @return
     */
    public static String getProvinceNameByAreaCode(String areaCode) {
        String provinceCode = areaCode.substring(0, 2) + "0000";
        return getLocationNameByCode(provinceCode);
    }

    /**
     * 获取省
     * @param areaCode 区
     * @return
     */
    public static String getPName(@NonNull String areaCode) {
        String pCode = areaCode.substring(0, 2).concat("0000");
        if (locationBeanList == null){
            return "";
        }
        for (LocationBean bean : locationBeanList) {
            if (bean.getSid().equals(pCode)) {
                return bean.getSname();
            }
        }
        return "";
    }

    /**
     * 获取市
     * @param areaCode
     * @return
     */
    public static String getCName(@NonNull String areaCode) {
        String cCode,pCode;
        if (areaCode.startsWith("8")){
            // 是以8开头的 则是香港或澳门 香港和澳门只有二级市且二级市不是以00结尾 比较另类
            cCode = areaCode;
        }else {
            cCode = areaCode.substring(0, 4).concat("00");
        }
        pCode = areaCode.substring(0, 2).concat("0000");

        if (locationBeanList == null){
            return "";
        }
        for (LocationBean locationBean : locationBeanList) {
            if (locationBean.getSid().equals(pCode)) {
                //得到所在省
                for (LocationBean.CityBean city : locationBean.getCity()) {
                    //匹配市
                    if (cCode.equals(city.getSid())) {
                        //获取市名
                        return city.getSname();
                    }
                }
            }
        }
        return "";
    }

    /**
     * 获取区位置
     * @param areaCode
     * @return
     */
    public static String getAName(@NonNull String areaCode) {
        String pCode = areaCode.substring(0, 2).concat("0000");
        String cCode;
        if (secondList.indexOf(areaCode) != -1){
            // 如果这个地区没有三级县
            cCode = areaCode;
        }else {
            cCode = areaCode.substring(0, 4).concat("00");
        }
        if (locationBeanList == null){
            return "";
        }
        for (LocationBean locationBean : locationBeanList) {
            if (locationBean.getSid().equals(pCode)) {
                if (TextUtils.equals("710000",locationBean.getSid())){
                    // 台湾没有二级市
                    return locationBean.getSname();
                }
                //得到所在省
                for (LocationBean.CityBean city : locationBean.getCity()) {
                    //匹配市
                    if (cCode.equals(city.getSid())) {
                        //获取市名
                        if (secondList.indexOf(areaCode) != -1){
                            // 如果这个地区没有三级县
                            return city.getSname();
                        }
                        for (LocationBean.CityBean.SiteBean siteBean : city.getSite()) {
                            //匹配区
                            if (areaCode.equals(siteBean.getSid())) {
                                return siteBean.getSname();
                            }
                        }
                    }
                }
            }
        }
        return "";
    }

    /**
     * 获取使用 - 链接的地址
     * @param area
     * @return
     */
    public static String getFormedString(String area) {
        if (TextUtils.isEmpty(area)){
            return "";
        }
        if (TextUtils.isEmpty(getCName(area))){
            return getPName(area);
        }
        if (TextUtils.isEmpty(getAName(area))){
            return getPName(area) + "-" + getCName(area);
        }
        return getPName(area) + "-" + getCName(area) + "-" + getAName(area);
    }
}
