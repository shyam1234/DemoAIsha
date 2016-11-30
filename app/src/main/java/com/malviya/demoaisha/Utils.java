package com.malviya.demoaisha;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by 23508 on 11/30/2016.
 */

public class Utils {


    //<uses-permission android:name="android.permission.READ_PHONE_STATE"/>

    /**
     * sometime IMEI number is black, so you can use device id
     * @return
     */
    public static String getIMEI() {
        String deviceId = "";
        try {
            TelephonyManager tm = (TelephonyManager) MyApplication.getInstance().getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = tm.getDeviceId();
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        }
        return deviceId;
    }


    /**
     * this method return device id
     * @return
     */
    public static String getDeviceId() {
        return Settings.Secure.getString(MyApplication.getInstance().getContentResolver(), Settings.Secure.ANDROID_ID);
    }




}
