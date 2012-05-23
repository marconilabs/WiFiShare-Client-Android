package it.mmo.wifisher;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class WiFiScanReceiver extends BroadcastReceiver {
  private static final String TAG = "WiFiScanReceiver";
  WiFiSherActivity wifiDemo;

  public WiFiScanReceiver(WiFiSherActivity wifiDemo) {
    super();
    this.wifiDemo = wifiDemo;
  }

  @Override
  public void onReceive(Context c, Intent intent) {
    List<ScanResult> results = wifiDemo.wifi.getScanResults();
    ScanResult bestSignal = null;
    StringBuilder sb = new StringBuilder();
    for (ScanResult result : results) {
    	sb.append(result.toString());
    	sb.append('\n');
    	Log.d(TAG, result.toString());
      if (bestSignal == null
          || WifiManager.compareSignalLevel(bestSignal.level, result.level) < 0)
        bestSignal = result;
    }

    //tv.setText(sb.toString());
    String message = String.format("%s networks found. %s is the strongest.",
        results.size(), bestSignal.SSID);
    Toast.makeText(wifiDemo, message, Toast.LENGTH_LONG).show();

    wifiDemo.writeScan(sb.toString());
    Log.d(TAG, "onReceive() message: " + message);
  }

}