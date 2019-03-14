package com.maizi.myamap_example;

import android.os.Bundle;

import com.maizi.myamap.MyamapPlugin;

import io.flutter.app.FlutterActivity;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    GeneratedPluginRegistrant.registerWith(this);
    MyamapPlugin.registerWith(savedInstanceState);
  }
}
