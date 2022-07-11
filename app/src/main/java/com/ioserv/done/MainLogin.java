package com.ioserv.done;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainLogin extends AppCompatActivity implements LoadWebServices.OnNetworkCallCompleteListener {

    // web Services Model.
    String url;
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

        TextInputLayout textInputLayoutU = findViewById(R.id.kidName);
        String textUser = textInputLayoutU.getEditText().getText().toString();

        TextInputLayout textInputLayoutP = findViewById(R.id.userPass);
        String textPass = textInputLayoutP.getEditText().getText().toString();


        // Web Service - JSON CALL.
        url = "http://donenextapp.com/MyWebService.asmx/VerifyUser?U=" + textUser + "&P=" + textPass;
        modelWebServices = new VolleyGet(this,url);
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

            Global.myValidUser = logonDetail.getBoolean("Ok");
            Global.myValidUserID = logonDetail.getInt("ID");
            Global.myValidUserType = logonDetail.getString("typ");

            if (Global.myValidUser) {
                if (Global.myValidUserType == "Parent") {
                    //Parent
                    startActivity(new Intent(MainLogin.this, AdminProfile.class));
                } else {
                    //Kid
                    //startActivity(new Intent(MainLogin.this, KidTasks.class));
                }
            }else{
                errorOccurred("Invalid Login");
            }

        } catch (Exception e) {
            //idx = "Json DOA";
        }
    }

    @Override
    public void errorOccurred(String errorMessage) {
        Toast toast=Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_SHORT);
        toast.setMargin(50,50);
        toast.show();
    }

}