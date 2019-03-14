package com.maizi.myamap;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.amap.api.maps.MapView;

import io.flutter.plugin.common.MessageCodec;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;

/** MyamapPlugin */
public class MyamapPlugin {
 static Registrar _registrar;
  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    _registrar=registrar;
  }
  public static void registerWith(Bundle savedInstanceState) {
    MapView _mapView = new MapView(_registrar.activity());
    _mapView.onCreate(savedInstanceState);
    _registrar.platformViewRegistry().registerViewFactory("MyAmapView", new MyAmapViewFactory(_mapView, new StandardMessageCodec()));
  }

  static class MyAmapViewFactory extends PlatformViewFactory {
    MapView _mapView;

    MyAmapViewFactory(MapView mapView, MessageCodec<Object> messageCodec) {
      super(messageCodec);
      _mapView = mapView;
    }

    @Override
    public PlatformView create(Context context, int i, Object o) {
      return new PlatformView() {
        @Override
        public View getView() {
          _mapView.getMap();
          return _mapView;
        }

        @Override
        public void dispose() {

        }
      };
    }
  }
}
