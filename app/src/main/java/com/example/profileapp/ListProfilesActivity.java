package com.example.profileapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import static com.example.profileapp.ProfileDataHelper.AGE_COLUMN_NAME;
import static com.example.profileapp.ProfileDataHelper.GENDER_COLUMN_NAME;
import static com.example.profileapp.ProfileDataHelper.NAME_COLUMN_NAME;

public class ListProfilesActivity extends AppCompatActivity {

    ProfileDataHelper profileDataHelper = new ProfileDataHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_profiles);

        Cursor cursor = profileDataHelper.getProfiles();

        ListView listView = findViewById(R.id.profiles_list_view);

        String[] columns = {
                NAME_COLUMN_NAME,
                AGE_COLUMN_NAME,
                GENDER_COLUMN_NAME
        };

        int[] resourceIds = {
                R.id.name,
                R.id.age,
                R.id.gender
        };

        ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.profile_row, cursor, columns, resourceIds, 0);

        listView.setAdapter(adapter);

    }

    public void showProfile() {
        Intent intent = new Intent (this, DetailProfileActivity.class);
        startActivity(intent);
    }
}
