package com.ioserv.done;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.widget.Button;

public class AdminMasterProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_master_profile);

        Button btnP = findViewById(R.id.btnP);
        btnP.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragCont, AdminProfileFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("adminProfileFrag") // name can be null
                    .commit();
        });


        Button btnR = findViewById(R.id.btnR);
        btnR.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragCont, AdminRewardFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("adminProfileFrag") // name can be null
                    .commit();
        });

        Button btnT = findViewById(R.id.btnT);
        btnT.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragCont, AdminTaskFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("adminProfileFrag") // name can be null
                    .commit();
        });

    }

}