package com.maizi.myamap;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.ServiceSettings;

import java.io.Console;

import io.flutter.plugin.common.MessageCodec;
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
            //获取一次定位结果：
            //该方法默认为false。
            mLocationOption.setOnceLocation(true);

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
            switch (methodCall.method) {
                //地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                case "getAddress":
                    result.success(aMapLocation.getAddress());
                    break;
                default:
                    result.notImplemented();
                    break;
            }
        }
    }
}
