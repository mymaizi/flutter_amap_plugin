import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class Myamap extends StatefulWidget {
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