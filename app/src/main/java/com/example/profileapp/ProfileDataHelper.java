package com.example.profileapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProfileDataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "profile.db";
    private static final int DATABASE_VERSION = 1;

    private static final String PROFILE_TABLE_NAME = "profile";
    public static final String ID_COLUMN_NAME = "_id";
    public static final String NAME_COLUMN_NAME = "name";
    public static final String AGE_COLUMN_NAME = "age";
    public static final String GENDER_COLUMN_NAME = "gender";
    private static final String PHOTO_COLUMN_NAME = "photo";


    public static final int MALE_GENDER = 1;
    public static final int FEMALE_GENDER = 2;
    public static final int OTHER_GENDER = 3;

    public ProfileDataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createProfileTable = "CREATE TABLE " + PROFILE_TABLE_NAME + " (" +
                ID_COLUMN_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME_COLUMN_NAME + " TEXT NOT NULL, " +
                AGE_COLUMN_NAME + " INTEGER, " +
                GENDER_COLUMN_NAME + " INTEGER, " +
                PHOTO_COLUMN_NAME + "BLOB);";
        sqLiteDatabase.execSQL(createProfileTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropTable = "DROP TABLE IF EXISTS " + PROFILE_TABLE_NAME + ";";
        sqLiteDatabase.execSQL(dropTable);
        onCreate(sqLiteDatabase);
    }

    public boolean writeProfiles() {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_COLUMN_NAME, "Nithya");
        contentValues.put(AGE_COLUMN_NAME, 27);
        contentValues.put(GENDER_COLUMN_NAME, FEMALE_GENDER);

        long result = db.insert(PROFILE_TABLE_NAME, null, contentValues);
        return result > -1;
    }

    public Cursor getProfiles() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + PROFILE_TABLE_NAME + ";";
        return db.rawQuery(query, null);
    }

    public Cursor getProfileForID(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + PROFILE_TABLE_NAME + " WHERE " + ID_COLUMN_NAME + "=" + id + ";";
        return db.rawQuery(query, null);
    }

    public String getVerboseGender(int i) {
        if (i == 1) {
            return "Male";
        } else if (i == 2) {
            return "Female";
        } else if (i == 3) {
            return "Other";
        } else {
            return "N/A";
        }
    }

    public boolean insertProfile(String name, int age, int gender) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_COLUMN_NAME, name);
        contentValues.put(AGE_COLUMN_NAME, age);
        contentValues.put(GENDER_COLUMN_NAME, gender);

        long l = db.insert(PROFILE_TABLE_NAME, null, contentValues);
        return l > -1;
    }
}
