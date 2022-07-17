package com.ioserv.done;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.material.textfield.TextInputLayout;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class MainRegister extends AppCompatActivity implements LoadWebServices.OnNetworkCallCompleteListener {

    // web Services Model.
    private LoadWebServices modelWebServices;
    JSONObject requestData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);

        Button button = findViewById(R.id.saveButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                register();
            }
        });

    }

    public void register() {

        TextInputLayout textInputLayoutU = findViewById(R.id.eMail);
        String textUser = textInputLayoutU.getEditText().getText().toString();

        TextInputLayout textInputLayoutP1 = findViewById(R.id.userPass);
        String textUser1 = textInputLayoutP1.getEditText().getText().toString();

        TextInputLayout textInputLayoutP2 = findViewById(R.id.userPass2);
        String textUser2 = textInputLayoutP2.getEditText().getText().toString();


        if (textUser1.equals(textUser2)) {

            // Web Service - JSON CALL.
            HashMap<String,Object> param = new HashMap<>();
            param.put("U",textUser);
            param.put("P",textUser1);

            modelWebServices = new VolleyGet(this,"VerifyUser",param);
            modelWebServices.setOnNetworkCallCompleteListener(this);

        }else {
            Global.errorOccurred(getApplicationContext(),"Error!");
        }

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

            if (logonDetail.getBoolean("Ok")) {
                Global.myValidUser = logonDetail.getBoolean("Ok");
                Global.myValidUserID = logonDetail.getInt("ID");
                Global.myValidUserType = logonDetail.getString("typ");

                startActivity(new Intent(MainRegister.this, MainLogin.class));
            } else {
                Global.errorOccurred(getApplicationContext(), "Invalid Registration");
            }

        } catch (Exception e) {
            //idx = "Json DOA";
        }
    }

    @Override
    public void errorOccurred(String errorMessage) {
        Global.errorOccurred(getApplicationContext(),"Connection Fault");
    }



}