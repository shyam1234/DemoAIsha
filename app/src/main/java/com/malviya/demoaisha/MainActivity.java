package com.malviya.demoaisha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by 23508 on 11/30/2016.
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService();
        Toast.makeText(this, "device id "+Utils.getIMEI(), Toast.LENGTH_SHORT).show();
        Log.d("ITC","getIMEI "+Utils.getIMEI());
        Log.d("ITC","device id "+Utils.getDeviceId());
        finish();

    }

    private void startService() {
            Intent intent = new Intent(MainActivity.this,MainService.class);
            startService(intent);
    }
}
