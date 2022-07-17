package com.ioserv.done;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Global.mySk = generateKey();
        } catch (NoSuchAlgorithmException e) {
            errorOccurred("Security Key Fail.");
        }

        WebView myWebView = findViewById(R.id.MyWeb1);
        myWebView.loadUrl("https://bettinelli.ca");

        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        GameSound gs = new GameSound();
        //gs.playSound(this,R.raw.zapsplat_multimedia_game_retro_musical_success_004);

    }

    public void onClickPlay(android.view.View view) {
        startActivity(new Intent(MainActivity.this, MainLogin.class));
    }

    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        // Generate a 256-bit key
        final int outputKeyLength = 256;

        SecureRandom secureRandom = new SecureRandom();
        // Do *not* seed secureRandom! Automatically seeded from system entropy.
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(outputKeyLength, secureRandom);
        return keyGenerator.generateKey();
    }

    public void errorOccurred(String errorMessage) {
        Toast toast=Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_SHORT);
        toast.setMargin(50,50);
        toast.show();
    }

}