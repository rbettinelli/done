package com.ioserv.done;

import android.content.Context;
import android.widget.Toast;

import javax.crypto.SecretKey;

public class Global {

    public static SecretKey mySk;
    public static Boolean   myValidUser;
    public static String    myValidUserType;
    public static int       myValidUserID;

    public static void errorOccurred(Context context, String errorMessage) {
        Toast toast=Toast.makeText(context ,errorMessage,Toast.LENGTH_SHORT);
        toast.setMargin(50,50);
        toast.show();
    }

}
