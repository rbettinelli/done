package com.ioserv.done;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import com.google.android.material.textfield.TextInputLayout;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;

public class MainLogin extends AppCompatActivity implements LoadWebServices.OnNetworkCallCompleteListener {

    // web Services Model
    private LibCom comLib = new LibCom();
    private LoadWebServices modelWebServices;
    JSONObject requestData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        Button button = findViewById(R.id.saveButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loginStart();
            }
        });

    }

    public void loginStart() {
        // Check Login Here.

        TextInputLayout textInputLayoutU = findViewById(R.id.eMail);
        String textUser = textInputLayoutU.getEditText().getText().toString();

        TextInputLayout textInputLayoutP = findViewById(R.id.userPass);
        String textPass = textInputLayoutP.getEditText().getText().toString();


        // Web Service - JSON CALL //
        HashMap<String,Object> param = new HashMap<>();
        param.put("U",textUser);
        param.put("P",textPass);
        modelWebServices = new VolleyGet(this,"spg_UserVerify",param);
        modelWebServices.setOnNetworkCallCompleteListener(this);
    }

    public void onClickForgot(android.view.View view) {
        //startActivity(new Intent(MainActivity.this, MainLogin.class));
    }

    public void onClickRegister(android.view.View view) {
        startActivity(new Intent(MainLogin.this, MainRegister.class));
    }

    // FROM REST API WITH JSON.
    @Override
    public void dataReady() {
        requestData = modelWebServices.getResponseData();

        try {
            //JSONObject jObject = requestData;

            JSONObject jsonObject = requestData;
            JSONArray dataArray = jsonObject.getJSONArray("Table");
            JSONObject logonDetail = dataArray.getJSONObject(0);

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putInt("id", logonDetail.getInt("ID"));
            edit.putString("type",logonDetail.getString("typ"));
            edit.putBoolean("valid",logonDetail.getBoolean("Ok"));
            edit.apply();



            if (logonDetail.getBoolean("Ok")) {
                if (logonDetail.getString("typ").equals("Parent")) {
                    //Parent
                    startActivity(new Intent(MainLogin.this, AdminMasterProfile.class));
                } else {
                    //Kid
                    //startActivity(new Intent(MainLogin.this, KidTasks.class));
                }
            }else{
                comLib.ErrorOccurred(getApplicationContext(),"Invalid Login");
            }

        } catch (Exception e) {
            //idx = "Json DOA";
        }
    }

    @Override
    public void errorOccurred(String errorMessage) {
        comLib.ErrorOccurred(getApplicationContext(),"Connection Fault");
    }

}