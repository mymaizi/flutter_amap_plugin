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
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin amap app'),
        ),
        body: Myamap(),
      ),
    );
  }
<<<<<<< HEAD
}
=======
}
>>>>>>> 0e8988f2d5996fe837286fa0892e625f87802d86
