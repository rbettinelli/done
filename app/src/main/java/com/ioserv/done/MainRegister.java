package com.ioserv.done;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);
    }

    public void onClickReg(android.view.View view) {
        startActivity(new Intent(MainRegister.this, MainLogin.class));
    }

}