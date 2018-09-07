package com.example.profileapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static com.example.profileapp.ListProfilesActivity.EXTRA_PROFILE_ID;
import static com.example.profileapp.ProfileDataHelper.AGE_COLUMN_NAME;
import static com.example.profileapp.ProfileDataHelper.GENDER_COLUMN_NAME;
import static com.example.profileapp.ProfileDataHelper.NAME_COLUMN_NAME;

public class DetailProfileActivity extends AppCompatActivity {

    ProfileDataHelper profileDataHelper = new ProfileDataHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_profile);

        Intent intent = getIntent();
        String l = intent.getStringExtra(EXTRA_PROFILE_ID);

        showDetailProfile(Integer.parseInt(l));

    }

    private void showDetailProfile(int id) {
        TextView nameView = findViewById(R.id.detail_name);
        TextView ageView = findViewById(R.id.detail_age);
        TextView genderView = findViewById(R.id.detail_gender);

        Cursor cursor = profileDataHelper.getProfileForID(id);

        cursor.moveToFirst();

        int nameColumnIndex = cursor.getColumnIndex(NAME_COLUMN_NAME);
        String name = cursor.getString(nameColumnIndex);
        int ageColumnIndex = cursor.getColumnIndex(AGE_COLUMN_NAME);
        String age = cursor.getString(ageColumnIndex);
        int genderColumnIndex = cursor.getColumnIndex(GENDER_COLUMN_NAME);
        String gender = profileDataHelper.getVerboseGender(cursor.getInt(genderColumnIndex));

        nameView.setText(name);
        ageView.setText(age);
        genderView.setText(gender);
    }
}
