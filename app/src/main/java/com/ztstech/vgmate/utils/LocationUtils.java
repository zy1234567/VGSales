package com.ztstech.vgmate.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ztstech.vgmate.base.BaseApplication;
import com.ztstech.vgmate.data.beans.LocationBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhiyuan on 2017/9/17.
 */

public class LocationUtils {
    private static Map<String, String> codeNameMap;
    private static Map<String, String> nameCodeMap;

    static {
        codeNameMap = new HashMap<>();
        nameCodeMap = new HashMap<>();
        String locationJson = CommonUtil.getDataFromAssets(
                BaseApplication.getApplicationInstance().getApplicationContext(), "location.txt");
        List<LocationBean> locationBeanList = new Gson().fromJson(locationJson,
                new TypeToken<List<LocationBean>>() {
        }.getType());
        if (locationBeanList != null) {
            for (LocationBean bean : locationBeanList) {
                codeNameMap.put(bean.getSid(), bean.getSname());
                nameCodeMap.put(bean.getSname(), bean.getSid());
                if (bean.getCity() != null) {
                    for (LocationBean.CityBean city : bean.getCity()) {
                        codeNameMap.put(city.getSid(), city.getSname());
                        nameCodeMap.put(city.getSname(), city.getSid());
                        if (city.getSite() != null) {
                            for (LocationBean.CityBean.SiteBean site : city.getSite()) {
                                codeNameMap.put(site.getSid(), site.getSname());
                                nameCodeMap.put(site.getSname(), site.getSid());
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 根据地区代码获取地区名称（不区分省市区）
     *
     * @param locationCode
     * @return
     */
    public static String getLocationNameByCode(String locationCode) {
        return codeNameMap.get(locationCode);
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
        String cityCode = areaCode.substring(0, 4) + "00";
        return getLocationNameByCode(cityCode);
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
}
