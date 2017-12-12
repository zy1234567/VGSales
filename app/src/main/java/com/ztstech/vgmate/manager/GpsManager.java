package com.ztstech.vgmate.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * Created by smm on 2017/12/9.
 * 定位管理类
 */

public class GpsManager implements AMapLocationListener {

    public static final String KEY_GPS = "gps_info";

    public static final String KEY_DISTRICT = "district";

    /** 声明mlocationClient对象 */
    private AMapLocationClient mlocationClient;

    /**声明mLocationOption对象 */
    private AMapLocationClientOption mLocationOption;

    /** sp存取定位信息 */
    private SharedPreferences preferences;

    private Context context;

    public GpsManager(Context context){
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void getGpsInfo(){

        mlocationClient = new AMapLocationClient(context);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位监听
        mlocationClient.setLocationListener(this);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(1000);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        mlocationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                Log.e("Amapsuccess", "gps--"
                        + amapLocation.getLongitude() + "-" + amapLocation.getLatitude() + "district--"
                        + amapLocation.getDistrict() + "AdCode--" + amapLocation.getAdCode());
                //定位成功回调信息，存储相关消息
                preferences.edit().putString(KEY_GPS,amapLocation.getLongitude() + "," + amapLocation.getLatitude()).apply();
                preferences.edit().putString(KEY_DISTRICT,amapLocation.getAdCode()).apply();
                mlocationClient.stopLocation();
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }

    /**
     * 获得存储的地区编码
     * @return
     */
    public String getSaveDistrict(){
        return preferences.getString(KEY_DISTRICT,"");
    }

    /**
     * 获得存储的gps
     * @return
     */
    public String getSaveGps(){
        return preferences.getString(KEY_GPS,"");
    }

}
