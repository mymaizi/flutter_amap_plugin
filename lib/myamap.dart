import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class Myamap extends StatefulWidget {
  static const MethodChannel _methodChannel = const MethodChannel("myamap");

  //地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
  static Future<String> get getAddress async {
    final String result = await _methodChannel.invokeMethod('getAddress');
    return result;
  }

  //获取当前定位结果来源，如网络定位结果，详见定位类型表
  static Future<String> get getLocationType async {
    final String result = await _methodChannel.invokeMethod('getLocationType');
    return result;
  }

  //获取纬度
  static Future<String> get getLatitude async {
    final String result = await _methodChannel.invokeMethod('getLatitude');
    return result;
  }

  //获取经度
  static Future<String> get getLongitude async {
    final String result = await _methodChannel.invokeMethod('getLongitude');
    return result;
  }

  //获取精度信息
  static Future<String> get getAccuracy async {
    final String result = await _methodChannel.invokeMethod('getAccuracy');
    return result;
  }

  //国家信息
  static Future<String> get getCountry async {
    final String result = await _methodChannel.invokeMethod('getCountry');
    return result;
  }

  //省信息
  static Future<String> get getProvince async {
    final String result = await _methodChannel.invokeMethod('getProvince');
    return result;
  }

  //城市信息
  static Future<String> get getCity async {
    final String result = await _methodChannel.invokeMethod('getCity');
    return result;
  }

  //城区信息
  static Future<String> get getDistrict async {
    final String result = await _methodChannel.invokeMethod('getDistrict');
    return result;
  }

  //街道信息
  static Future<String> get getStreet async {
    final String result = await _methodChannel.invokeMethod('getStreet');
    return result;
  }

  //街道门牌号信息
  static Future<String> get getStreetNum async {
    final String result = await _methodChannel.invokeMethod('getStreetNum');
    return result;
  }

  //城市编码
  static Future<String> get getCityCode async {
    final String result = await _methodChannel.invokeMethod('getCityCode');
    return result;
  }

  //地区编码
  static Future<String> get getAdCode async {
    final String result = await _methodChannel.invokeMethod('getAdCode');
    return result;
  }

  //获取当前定位点的AOI信息
  static Future<String> get getAoiName async {
    final String result = await _methodChannel.invokeMethod('getAoiName');
    return result;
  }

  //获取当前室内定位的建筑物Id
  static Future<String> get getBuildingId async {
    final String result = await _methodChannel.invokeMethod('getBuildingId');
    return result;
  }

  //获取当前室内定位的楼层
  static Future<String> get getFloor async {
    final String result = await _methodChannel.invokeMethod('getFloor');
    return result;
  }

  //获取GPS的当前状态
  static Future<String> get getGpsAccuracyStatus async {
    final String result =
        await _methodChannel.invokeMethod('getGpsAccuracyStatus');
    return result;
  }

  //获取定位时间
  static Future<String> get getTime async {
    final String result = await _methodChannel.invokeMethod('getTime');
    return result;
  }

  //销毁客户端
  static Future<bool> get onDestroy async {
    final bool result = await _methodChannel.invokeMethod<bool>('onDestroy');
    return result;
  }

  //开始定位
  static Future<bool> get startLocation async {
    final bool result =
        await _methodChannel.invokeMethod<bool>('startLocation');
    return result;
  }

  //停止定位
  static Future<bool> get stopLocation async {
    final bool result = await _methodChannel.invokeMethod<bool>('stopLocation');
    return result;
  }

  //绘制点标记
  static set setMarkers(List<Marker> markers) {
    var ms = new List<Map>();
    for (var item in markers) {
      var m = {};
      m["title"] = item.title;
      m["lat"] = item.lat;
      m["lng"] = item.lng;
      m["snippet"] = item.snippet;
      ms.add(m);
    }
    _methodChannel.invokeMethod("setMarkers", ms);
  }

  @override
  State<StatefulWidget> createState() {
    return MyamapState();
  }
}

class MyamapState extends State<Myamap> {
  @override
  Widget build(BuildContext context) {
    return AndroidView(viewType: "MyAmapView");
  }
}

class Marker {
  String title;
  double lat;
  double lng;
  String snippet;
  Marker(@required this.lat, @required this.lng, @required this.title,
      {this.snippet});
}
