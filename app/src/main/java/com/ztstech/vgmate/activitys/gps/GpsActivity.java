package com.ztstech.vgmate.activitys.gps;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.AMapGestureListener;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.BaseActivity;
import com.ztstech.vgmate.weigets.TopBar;

import butterknife.BindView;
import rx.functions.Action1;

/**
 * 选择gps
 */
public class GpsActivity extends BaseActivity implements  GeocodeSearch.OnGeocodeSearchListener {

    /**经纬度和地址的key和向GpsACtivity传的标志位的key*/
    public final static String SHOW_ORG_FLG = "show_org_flg";
    public final static String ARG_LA = "la";
    public final static String ARG_LO = "lo";
    public final static String ARG_ADDRESS = "address";

    /**延时*/
    public static final int DELAY = 200;

    /**纬度*/
    public static final String RESULT_LATITUDE = "result_latitude";
    /**经度*/
    public static final String RESULT_LONGITUDE = "result_longitude";
    /**地址*/
    public static final String RESULT_LOCATION = "result_location";





    @BindView(R.id.map)
    MapView mapView;
    @BindView(R.id.top_bar)
    TopBar topBar;

    private AMap aMap;
    private MyLocationStyle myLocationStyle;
    private Marker marker;
    private GeocodeSearch geocoderSearch;

    private KProgressHUD progressHUD;
    /**纬度*/
    private double la;
    /**精度*/
    private double lo;
    /**选中的地址*/
    private String location;
    /**根据gps定位位置信息以及标志位和地址信息*/
    String strla,strlo,address;
    boolean showorgflg = false;

    private Runnable getLocationRunnable = new Runnable() {
        @Override
        public void run() {
            LatLng latLng = aMap.getCameraPosition().target;

            // 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
            RegeocodeQuery query = new RegeocodeQuery(new LatLonPoint(latLng.latitude, latLng.longitude),
                    100,GeocodeSearch.GPS);
            if (!showorgflg){
                la = latLng.latitude;
                lo = latLng.longitude;
            }
            geocoderSearch.getFromLocationAsyn(query);
        }
    };


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_gps;
    }

    @Override
    protected void onViewBindFinish(final @Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);
        mapView.onCreate(savedInstanceState);

        progressHUD = new KProgressHUD(this);
//        initMap(savedInstanceState);
        //请求权限
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            initMap(savedInstanceState);
                        }else {
                            Toast.makeText(GpsActivity.this, "无权限", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        showorgflg = getIntent().getBooleanExtra(SHOW_ORG_FLG,false);
        if (showorgflg) {
            strla = getIntent().getStringExtra(ARG_LA);
            strlo = getIntent().getStringExtra(ARG_LO);
            address = getIntent().getStringExtra(ARG_ADDRESS);
            la = Double.parseDouble(strla);
            lo = Double.parseDouble(strlo);
            marker = aMap.addMarker(new MarkerOptions().position(new LatLng(la, lo))
                    .title(address));
            marker.setPosition(new LatLng(la, lo));
            marker.showInfoWindow();
            marker.setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.gps));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (marker == null) {
                        aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(la, lo), 16));
                    }
                }
            },1000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mapView != null) {
            mapView.onDestroy();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mapView != null) {
            mapView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mapView != null) {
            mapView.onResume();
        }
    }

    @Override
    public void onRegeocodeSearched(final RegeocodeResult regeocodeResult, int i) {
        if (isFinishing()) {
            return;
        }
        if (regeocodeResult != null) {
            progressHUD.dismiss();
            if (showorgflg){
                return;
            }
            LatLng latLng = new LatLng(la, lo);
            if (regeocodeResult.getRegeocodeQuery().getPoint().getLatitude() == la &&
                    regeocodeResult.getRegeocodeQuery().getPoint().getLongitude() == lo) {
                location = regeocodeResult.getRegeocodeAddress().getFormatAddress();
                if (marker == null) {
                    marker = aMap.addMarker(new MarkerOptions().position(latLng)
                            .title(regeocodeResult.getRegeocodeAddress().getFormatAddress()));
                    marker.setPosition(latLng);
                    marker.showInfoWindow();
                    marker.setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.gps));
                    marker.setPositionByPixels(
                            GpsActivity.this.getResources().getDisplayMetrics().widthPixels / 2,
                            GpsActivity.this.getResources().getDisplayMetrics().heightPixels / 2);
                    aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
                }else {
                    // FIXME: 2017/9/29 傻逼高德地图marker上textview只变大不变小
                    marker.setTitle(regeocodeResult.getRegeocodeAddress().getFormatAddress());
                    marker.showInfoWindow();
                }

            }
        }
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }


    private void initMap(Bundle savedInstanceState) {
        if (aMap == null) {
            aMap = mapView.getMap();
        }

        geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(this);


        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE) ;//定位一次，且将视角移动到地图中心点。
        myLocationStyle.showMyLocation(true);
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true); //设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setInfoWindowAdapter(new AMap.InfoWindowAdapter() {
            TextView infoWindow = null;
            @Override
            public View getInfoWindow(Marker marker) {
                if(infoWindow == null) {
                    infoWindow = new TextView(GpsActivity.this);
                }
                render(marker, infoWindow);
                return infoWindow;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }

            /**
             * 自定义infowinfow窗口
             */
            public void render(Marker marker, View view) {
                String title = marker.getTitle();
                ((TextView) view).setText(title);
            }
        });


        aMap.setAMapGestureListener(new AMapGestureListener() {
            @Override
            public void onDoubleTap(float v, float v1) {

            }

            @Override
            public void onSingleTap(float v, float v1) {

            }

            @Override
            public void onFling(float v, float v1) {

            }

            @Override
            public void onScroll(float v, float v1) {

            }

            @Override
            public void onLongPress(float v, float v1) {

            }

            @Override
            public void onDown(float v, float v1) {

            }

            @Override
            public void onUp(float v, float v1) {

            }

            @Override
            public void onMapStable() {
                if (marker != null) {
                    marker.setTitle("正在定位");
                }
//                mapView.postDelayed(getLocationRunnable, DELAY);
//                progressHUD.show();
            }
        });

        aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                Log.e("onCameraChange","onCameraChange");
            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                mapView.postDelayed(getLocationRunnable, DELAY);
                Log.e("onCameraChange","onCameraChangeFinish");
            }
        });

        topBar.getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (la == 0 && lo == 0) {
                    setResult(RESULT_CANCELED);
                    finish();
                }else {
                    Intent it = new Intent();
                    it.putExtra(RESULT_LATITUDE, la);
                    it.putExtra(RESULT_LONGITUDE, lo);
                    it.putExtra(RESULT_LOCATION, location);
                    setResult(RESULT_OK, it);
                    finish();
                }
            }
        });

    }
}
