package com.malviya.demoaisha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by 23508 on 11/30/2016.
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService();
        finish();
    }

    private void startService() {
            Intent intent = new Intent(MainActivity.this,MainService.class);
            startService(intent);
    }
}
