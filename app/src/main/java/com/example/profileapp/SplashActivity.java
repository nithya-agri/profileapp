package com.example.profileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        showList();
    }


    private void showList() {
        Intent intent = new Intent(this, ListProfilesActivity.class);
        startActivity(intent);
    }

}
