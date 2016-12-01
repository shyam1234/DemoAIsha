package com.malviya.demoaisha;

import android.app.Activity;
import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static android.app.Activity.RESULT_OK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by 23508 on 11/30/2016.
 */

public class MainService extends Service {

    private TextToSpeech mTextToSpeech;
    private static final int SPEECH_REQUEST_CODE = 0;
    private Handler mHandler;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;


    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(MainService.this, "Service Started", Toast.LENGTH_SHORT).show();
        //registerReceiver(new MyReceiver(),new IntentFilter(RecognizerIntent.ACTION_RECOGNIZE_SPEECH));

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

        speech = SpeechRecognizer.createSpeechRecognizer(MainService.this);
        speech.setRecognitionListener(new Listner());
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getApplicationContext().getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);


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
//                            mTextToSpeech.speak("Hello Maalviya. How are you? Now, Time is  "
//                                    + Calendar.getInstance().get(Calendar.HOUR)
//                                    + " : " + Calendar.getInstance().get(Calendar.MINUTE)
//                                    + "Thank you.", TextToSpeech.QUEUE_FLUSH, null);
                            displaySpeechRecognizer();

                        }
                    }, 5000, 20000/*1000 * 60 * 60*/);

                }
            }
        });

        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                speech.startListening(recognizerIntent);
            }
        };
    }




    private void displaySpeechRecognizer() {
        mHandler.sendEmptyMessage(0);
    }


    @Override
    public void onDestroy() {
        if (mTextToSpeech != null) {
            mTextToSpeech.stop();
            mTextToSpeech.shutdown();
        }

        if (speech != null) {
            speech.stopListening();
            speech.destroy();
            Log.i("ITC", "destroy");
        }
        super.onDestroy();
    }


    private class Listner implements RecognitionListener {

        @Override
        public void onReadyForSpeech(Bundle bundle) {

        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float v) {

        }

        @Override
        public void onBufferReceived(byte[] bytes) {

        }

        @Override
        public void onEndOfSpeech() {
            Log.i("ITC", "onEndOfSpeech ");
            speech.stopListening();
        }

        @Override
        public void onError(int errorCode) {
            Log.i("ITC", "results " + errorCode);
            String errorMessage = VoiceRecognitionActivity.getErrorText(errorCode);
            Log.i("ITC", "results " + errorMessage);
            speech.stopListening();
        }


        @Override
        public void onPartialResults(Bundle bundle) {

        }

        @Override
        public void onEvent(int i, Bundle bundle) {
            Log.i("ITC", "results " + i);
        }

        @Override
        public void onResults(Bundle results) {
            Log.i("ITC", "results " + results);
            ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            String text = matches.get(0).toString();
            for (String result : matches) {
                text += result + "\n";
                Log.i("ITC", "results " + result);
            }
            Log.d("ITC", "final text: " + text);


            rectAIsha(text);

        }
    }

    private void rectAIsha(String text) {
        if (text.contains("time")){
            mTextToSpeech.speak("Time is  "
                                    + Calendar.getInstance().get(Calendar.HOUR)
                                    + " : " + Calendar.getInstance().get(Calendar.MINUTE)
                                    + "Thank you.", TextToSpeech.QUEUE_FLUSH, null);
        }else if (text.contains("how are you")){
            mTextToSpeech.speak("Hello Maalviya, I am good. How are you?", TextToSpeech.QUEUE_FLUSH, null);
        }else if(text.contains("plan")){
            mTextToSpeech.speak("I do not have any special plan today. You say", TextToSpeech.QUEUE_FLUSH, null);
        }else{
            mTextToSpeech.speak("Sorry, I did not get you", TextToSpeech.QUEUE_FLUSH, null);
        }

    }


}
