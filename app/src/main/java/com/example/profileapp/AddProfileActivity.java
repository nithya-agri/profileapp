package com.example.profileapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import static com.example.profileapp.ProfileDataHelper.FEMALE_GENDER;
import static com.example.profileapp.ProfileDataHelper.MALE_GENDER;
import static com.example.profileapp.ProfileDataHelper.OTHER_GENDER;

public class AddProfileActivity extends AppCompatActivity {

    ProfileDataHelper profileDataHelper = new ProfileDataHelper(this);
    private int selectedGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);
    }

    public void submitProfile(View view) {
        EditText nameView = findViewById(R.id.profile_form_name_field);
        String name = nameView.getText().toString();

        EditText ageView = findViewById(R.id.profile_form_age_field);
        int age = Integer.parseInt(ageView.getText().toString());

        boolean inserted = profileDataHelper.insertProfile(name, age, selectedGender);
        if (inserted)
            Toast.makeText(getBaseContext(), "Added", Toast.LENGTH_LONG).show();
    }

    public void onGenderButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.gender_radio_male:
                if (checked)
                    selectedGender = MALE_GENDER;
                break;
            case R.id.gender_radio_female:
                if (checked)
                    selectedGender = FEMALE_GENDER;
                break;

            case R.id.gender_radio_other:
                if (checked)
                    selectedGender = OTHER_GENDER;
                break;
        }
    }
}
