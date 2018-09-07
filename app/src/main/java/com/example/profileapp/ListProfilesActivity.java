package com.example.profileapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import static com.example.profileapp.ProfileDataHelper.AGE_COLUMN_NAME;
import static com.example.profileapp.ProfileDataHelper.GENDER_COLUMN_NAME;
import static com.example.profileapp.ProfileDataHelper.ID_COLUMN_NAME;
import static com.example.profileapp.ProfileDataHelper.NAME_COLUMN_NAME;

public class ListProfilesActivity extends AppCompatActivity {

    public static final String EXTRA_PROFILE_ID = "com.example.profileapp._ID";

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

        listView.setOnItemClickListener(profileListOnItemClickListener);

    }

    private AdapterView.OnItemClickListener profileListOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(getBaseContext(), DetailProfileActivity.class);
            intent.putExtra(EXTRA_PROFILE_ID, String.valueOf(l));
            startActivity(intent);
        }
    };
}
