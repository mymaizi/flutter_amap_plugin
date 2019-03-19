import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:myamap/myamap.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _address = "";
  Future<void> getAddress() async {
    String address;
    try {
      address = await Myamap.getAddress;
    } on PlatformException {
      address = 'error address';
    }
    setState(() {
      _address = address;
    });
  }

  setMarkers() {
    var markers = List<Marker>();
    markers.add(Marker(29.57, 106.55,"重庆市"));
    Myamap.setMarkers = markers;
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin amap app'),
        ),
        body: Column(
          children: <Widget>[
            Container(
              child: Row(
                children: <Widget>[
                  RaisedButton(
                    child: Text("获取当前地址:$_address"),
                    onPressed: () {
                      setMarkers();
                    },
                  )
                ],
              ),
            ),
            Expanded(
              child: Myamap(),
            )
          ],
        ),
      ),
    );
  }
}
