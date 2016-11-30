package com.malviya.demoaisha;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by 23508 on 11/30/2016.
 */

public class CustomDialogbox extends Dialog {

    public final static int TYPE_OK = 1;
    public final static int TYPE_YES_NO = 2;
    public final static int TYPE_YES_NO_SKIP = 3;
    public final static int TYPE_NO_BTN = 4;
    public final static int TYPE_INPUT = 5;

    public CustomDialogbox(Context context, int pType) {
        super(context);
        switch (pType) {
            case TYPE_OK:
                setContentView(R.layout.dialog_ok
                );
                break;
            case TYPE_INPUT:
                break;
            case TYPE_NO_BTN:
                break;
            case TYPE_YES_NO:
                break;
            case TYPE_YES_NO_SKIP:
                break;
        }
    }
}
