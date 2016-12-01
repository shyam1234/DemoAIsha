package com.malviya.demoaisha;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by 23508 on 11/30/2016.
 */

public class MainActivity extends Activity {
    private static final int SPEECH_REQUEST_CODE = 1;
    private static final String TAG = "ITC";
    private SpeechRecognizer speechRecognizer;
    private Intent recognizerIntent;

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
