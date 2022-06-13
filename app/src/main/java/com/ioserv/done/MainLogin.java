package com.ioserv.done;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
    }

    public void onClickLogin(android.view.View view) {
        // Check Login Here.

        TextView textUser = findViewById(R.id.userName);
        // val inputUser = textUser.getText();

        TextView textPass = findViewById(R.id.userPass);
        // val inputPass = textPass.getText();

        // startActivity(new Intent(MainActivity.this, MainLogin.class));
    }

    public void onClickForgot(android.view.View view) {
        //startActivity(new Intent(MainActivity.this, MainLogin.class));
    }

    public void onClickRegister(android.view.View view) {
        startActivity(new Intent(MainLogin.this, MainRegister.class));
    }



}