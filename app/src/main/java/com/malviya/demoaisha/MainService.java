package com.malviya.demoaisha;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 23508 on 11/30/2016.
 */

public class MainService extends Service {

    private TextToSpeech mTextToSpeech;


    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(MainService.this, "Service Started", Toast.LENGTH_SHORT).show();

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        doWorkOnBackground();
        return Service.START_REDELIVER_INTENT;
    }

    private void doWorkOnBackground() {
        init();
    }

    private void init() {


        mTextToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    mTextToSpeech.setLanguage(Locale.ENGLISH);
                    mTextToSpeech.speak("Hello Maalviya", TextToSpeech.QUEUE_FLUSH, null);
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            mTextToSpeech.speak("Hello Maalviya. How are you? Now, Time is  "
                                    + Calendar.getInstance().get(Calendar.HOUR)
                                    + " : " + Calendar.getInstance().get(Calendar.MINUTE)
                                    + "Thank you.", TextToSpeech.QUEUE_FLUSH, null);

                            //Log.d("ITC","time "+Calendar.getInstance().get(Calendar.HOUR)+":"+Calendar.getInstance().get(Calendar.MINUTE));
                        }
                    }, 5000, 1000 * 60 * 60);

                }
            }
        });
    }


    @Override
    public void onDestroy() {
        if (mTextToSpeech != null) {
            mTextToSpeech.stop();
            mTextToSpeech.shutdown();
        }
        super.onDestroy();
    }

}
