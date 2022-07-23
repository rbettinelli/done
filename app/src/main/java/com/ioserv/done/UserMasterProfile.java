package com.ioserv.done;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Button;

public class UserMasterProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_master_profile);

        Button btnP = findViewById(R.id.btnP);
        btnP.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragCont, UserProfileFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("userProfileFrag") // name can be null
                    .commit();
        });


        Button btnR = findViewById(R.id.btnR);
        btnR.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragCont, UserRewardFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("userRewardFrag") // name can be null
                    .commit();
        });

        Button btnT = findViewById(R.id.btnT);
        btnT.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragCont, UserTasksFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("userTaskFrag") // name can be null
                    .commit();
        });

    }
}