package com.ioserv.done;

import android.content.Context;
import android.widget.Toast;

public class LibCom {

    public void ErrorOccurred(Context c, String txt ) {
        Toast toast=Toast.makeText(c,txt,Toast.LENGTH_SHORT);
        toast.setMargin(50,50);
        toast.show();
    }

}
