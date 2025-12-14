package com.example.projetm2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database_Handler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS = "users";
    public static final String COL_ID = "id";
    public static final String COL_USERNAME = "username";
    public static final String COL_EMAIL = "email";
    public static final String COL_PASSWORD = "password";
    public static final String COL_LAST_ACTIVE = "last_active";
    public static final String COL_TIME = "total_time";

    public database_Handler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_USERNAME + " TEXT UNIQUE, "
                + COL_EMAIL + " TEXT, "
                + COL_PASSWORD + " TEXT, "
                + COL_LAST_ACTIVE + " INTEGER DEFAULT 0, "
                + COL_TIME + " INTEGER DEFAULT 0"
                + ");";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // REGISTER USER
    public boolean registerUser(String username, String email, String passwordHash) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USERNAME, username);
        values.put(COL_EMAIL, email);
        values.put(COL_PASSWORD, passwordHash);

        long result = db.insert(TABLE_USERS, null, values);
        return result != -1;
    }

    // CHECK IF USERNAME EXISTS
    public boolean usernameExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE username = ?",
                new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // LOGIN USER
    public boolean loginUser(String username, String passwordHash) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_USERS + " WHERE username = ? AND password = ?",
                new String[]{username, passwordHash});
        boolean success = cursor.getCount() > 0;
        cursor.close();
        return success;
    }

    // UPDATE LAST ACTIVE TIME
    public void updateLastActive(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_LAST_ACTIVE, System.currentTimeMillis());
        db.update(TABLE_USERS, values, COL_USERNAME + "=?", new String[]{username});
    }

    // ADD USER SESSION TIME
    public void addTime(String username, long seconds) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COL_TIME + " FROM " + TABLE_USERS + " WHERE " + COL_USERNAME + " = ?",
                new String[]{username});
        if (cursor != null && cursor.moveToFirst()) {
            long currentTime = cursor.getLong(cursor.getColumnIndexOrThrow(COL_TIME));
            long newTime = currentTime + seconds;
            ContentValues values = new ContentValues();
            values.put(COL_TIME, newTime);
            db.update(TABLE_USERS, values, COL_USERNAME + "=?", new String[]{username});
        }
        if (cursor != null) cursor.close();
    }
}
