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

public class MainRegister extends AppCompatActivity implements LoadWebServices.OnNetworkCallCompleteListener {

    private final LibCom comLib = new LibCom();
    // web Services Model.
    private LoadWebServices modelWebServices;
    JSONObject requestData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);

        Button button = findViewById(R.id.saveButton);
        button.setOnClickListener(v -> register());

    }

    public void register() {

        TextInputLayout textInputLayoutU = findViewById(R.id.userEmailLayout);
        String textUser = textInputLayoutU.getEditText().getText().toString();

        TextInputLayout textInputLayoutP1 = findViewById(R.id.userPass1Layout);
        String textPass1 = textInputLayoutP1.getEditText().getText().toString();

        TextInputLayout textInputLayoutP2 = findViewById(R.id.userPass2Layout);
        String textPass2 = textInputLayoutP2.getEditText().getText().toString();

        if (textPass1.equals(textPass2)) {

            // Web Service - JSON CALL.
            HashMap<String,Object> param = new HashMap<>();
            param.put("U",textUser);
            param.put("P",textPass1);

            modelWebServices = new VolleyGet(this,"spi_RegisterUser",param);
            modelWebServices.setOnNetworkCallCompleteListener(this);

        }else {
            comLib.ErrorOccurred(getApplicationContext(),"Error!");
        }

    }

    // FROM REST API WITH JSON.
    @Override
    public void dataReady() {
        requestData = modelWebServices.getResponseData();

        try {
            JSONObject jsonObject = requestData;
            JSONArray dataArray = jsonObject.getJSONArray("Table");
            JSONObject regDetail = dataArray.getJSONObject(0);

            if (regDetail.getBoolean("Ok")) {

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor edit = preferences.edit();
                edit.putInt("id", regDetail.getInt("ID"));
                edit.putString("type",regDetail.getString("typ"));
                edit.putBoolean("valid",regDetail.getBoolean("Ok"));
                edit.apply();

                startActivity(new Intent(MainRegister.this, MainLogin.class));
            } else {
                comLib.ErrorOccurred(getApplicationContext(), "Invalid Registration");
            }

        } catch (Exception e) {
            //String x = e.toString();
            comLib.ErrorOccurred(getApplicationContext(), "App Fault.");
        }
    }

    @Override
    public void errorOccurred(String errorMessage) {
        comLib.ErrorOccurred(getApplicationContext(),"Connection Fault");
    }

}