package com.example.profileapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.net.URL;

public class SplashActivity extends AppCompatActivity {

    ProfileDataHelper profileDataHelper = new ProfileDataHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ProfileLoadingTask task = new ProfileLoadingTask();
        task.execute();
    }


    private void showList() {
        Intent intent = new Intent(this, ListProfilesActivity.class);
        startActivity(intent);
    }

    private class ProfileLoadingTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            profileDataHelper.writeProfiles();
            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            showList();
        }
    }
}
