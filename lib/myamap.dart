import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class Myamap extends StatefulWidget {
  static const MethodChannel _methodChannel = const MethodChannel("myamap");

  static Future<String> get getAddress async {
    final String address = await _methodChannel.invokeMethod('getAddress');
    return address;
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
