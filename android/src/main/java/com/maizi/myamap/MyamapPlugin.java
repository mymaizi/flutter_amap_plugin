package com.maizi.myamap;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.flutter.plugin.common.JSONUtil;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;

/**
 * MyamapPlugin
 */
public class MyamapPlugin {
    static Registrar _registrar;
    static MapView _mapView;
    static AMap _aMap;

    /**
     * Plugin registration.
     */
    public static void registerWith(Registrar registrar) {
        _registrar = registrar;
    }

    public static void registerWith(Bundle savedInstanceState) {
        _mapView = new MapView(_registrar.activity());
        _mapView.onCreate(savedInstanceState);
        _aMap = _mapView.getMap();
        _registrar.platformViewRegistry().registerViewFactory("MyAmapView", new MyAmapViewFactory());
    }

    static class MyAmapViewFactory extends PlatformViewFactory {
        MyAmapViewFactory() {
            super(StandardMessageCodec.INSTANCE);
        }

        @Override
        public PlatformView create(Context context, int i, Object o) {
            return new MyAmapPlatformView();
        }
    }

    static class MyAmapPlatformView implements PlatformView {
        MyAmapPlatformView() {
            final MethodChannel methodChannel = new MethodChannel(_registrar.messenger(), "myamap");
            methodChannel.setMethodCallHandler(new MyAmapMethodChannel());
        }

        @Override
        public View getView() {
            return _mapView;
        }

        @Override

        public void dispose() {
        }
    }

    static class MyAmapMethodChannel implements MethodCallHandler {
        AMapLocationClient mLocationClient;
        AMapLocation aMapLocation;

        MyAmapMethodChannel() {
            //声明AMapLocationClient类对象
            mLocationClient = new AMapLocationClient(_registrar.activity().getApplicationContext());
            //声明定位回调监听器
            mLocationClient.setLocationListener(new AMapLocationListener() {
                @Override
                public void onLocationChanged(AMapLocation _aMapLocation) {
                    aMapLocation = _aMapLocation;
                }
            });
            AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
            mLocationClient.setLocationOption(mLocationOption);
            //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //获取最近3s内精度最高的一次定位结果：
            //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
            mLocationOption.setOnceLocationLatest(true);
            //设置是否返回地址信息（默认返回地址信息）
            mLocationOption.setNeedAddress(true);
            //给定位客户端对象设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
            //启动定位
            mLocationClient.startLocation();
        }

        @Override
        public void onMethodCall(MethodCall methodCall, Result result) {
            try {
                switch (methodCall.method) {
                    //地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    case "getAddress":
                        result.success(aMapLocation.getAddress());
                        break;
                    //获取当前定位结果来源，如网络定位结果，详见定位类型表
                    case "getLocationType":
                        result.success(aMapLocation.getLocationType());
                        break;
                    //获取纬度
                    case "getLatitude":
                        result.success(aMapLocation.getLatitude());
                        break;
                    //获取经度
                    case "getLongitude":
                        result.success(aMapLocation.getLongitude());
                        break;
                    //获取精度信息
                    case "getAccuracy":
                        result.success(aMapLocation.getAccuracy());
                        break;
                    //国家信息
                    case "getCountry":
                        result.success(aMapLocation.getCountry());
                        break;
                    //省信息
                    case "getProvince":
                        result.success(aMapLocation.getProvince());
                        break;
                    //城市信息
                    case "getCity":
                        result.success(aMapLocation.getCity());
                        break;
                    //城区信息
                    case "getDistrict":
                        result.success(aMapLocation.getDistrict());
                        break;
                    //街道信息
                    case "getStreet":
                        result.success(aMapLocation.getStreet());
                        break;
                    //街道门牌号信息
                    case "getStreetNum":
                        result.success(aMapLocation.getStreetNum());
                        break;
                    //城市编码
                    case "getCityCode":
                        result.success(aMapLocation.getCityCode());
                        break;
                    //地区编码
                    case "getAdCode":
                        result.success(aMapLocation.getAdCode());
                        break;
                    //获取当前定位点的AOI信息
                    case "getAoiName":
                        result.success(aMapLocation.getAoiName());
                        break;
                    //获取当前室内定位的建筑物Id
                    case "getBuildingId":
                        result.success(aMapLocation.getBuildingId());
                        break;
                    //获取当前室内定位的楼层
                    case "getFloor":
                        result.success(aMapLocation.getFloor());
                        break;
                    //获取GPS的当前状态
                    case "getGpsAccuracyStatus":
                        result.success(aMapLocation.getGpsAccuracyStatus());
                        break;
                    //获取定位时间
                    case "getTime":
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(aMapLocation.getTime());
                        result.success(df.format(date));
                        break;
                    //销毁客户端
                    case "onDestroy":
                        mLocationClient.onDestroy();
                        result.success(true);
                        break;
                    //开始定位
                    case "startLocation":
                        mLocationClient.startLocation();
                        result.success(true);
                        break;
                    //停止定位
                    case "stopLocation":
                        mLocationClient.stopLocation();
                        result.success(true);
                        break;
                    //绘制点标记
                    case "setMarkers":
                        JSONArray markers = (JSONArray) JSONUtil.wrap(methodCall.arguments);
                        ArrayList<MarkerOptions> markerOptions = new ArrayList<>();
                        for (int i = 0; i < markers.length(); i++) {
                            JSONObject marker = markers.getJSONObject(i);
                            String title= marker.getString("title");
                            String snippet= marker.getString("snippet");
                            double lat=marker.getDouble("lat");
                            double lng=marker.getDouble("lng");
                            if(snippet=="null"){
                                StringBuilder sb=new StringBuilder();
                                sb.append("(经度:"+lng).append(",").append("纬度:"+lat).append(")");
                                snippet=sb.toString();
                            }
                            markerOptions.add(new MarkerOptions().position(new LatLng(lat,lng)).title(title).snippet(snippet));
                        }
                        _aMap.addMarkers(markerOptions, false);
                        break;
                    default:
                        result.notImplemented();
                        break;
                }
            } catch (Exception e) {
                result.error(e.getMessage(), null, null);
            }
        }
    }
}
