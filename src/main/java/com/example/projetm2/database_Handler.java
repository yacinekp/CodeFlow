package com.example.projetm2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database_Handler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 2;

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
        if (oldVersion < 2) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            onCreate(db);
        }
    }

    // Register user with email
    public boolean registerUser(String username, String email, String passwordHash) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USERNAME, username);
        values.put(COL_EMAIL, email);
        values.put(COL_PASSWORD, passwordHash);
        long result = db.insert(TABLE_USERS, null, values);
        return result != -1;
    }

    // Check if username exists
    public boolean usernameExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE username = ?",
                new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // Check if email exists
    public boolean emailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE email = ?",
                new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // Login with username OR email
    public boolean loginUser(String usernameOrEmail, String passwordHash) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;

        // Check if input is email format
        if (usernameOrEmail.contains("@")) {
            // Try login with email
            cursor = db.rawQuery(
                    "SELECT * FROM " + TABLE_USERS + " WHERE email = ? AND password = ?",
                    new String[]{usernameOrEmail, passwordHash});
        } else {
            // Try login with username
            cursor = db.rawQuery(
                    "SELECT * FROM " + TABLE_USERS + " WHERE username = ? AND password = ?",
                    new String[]{usernameOrEmail, passwordHash});
        }

        boolean success = cursor.getCount() > 0;
        cursor.close();
        return success;
    }

    // Get username by email
    public String getUsernameByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT " + COL_USERNAME + " FROM " + TABLE_USERS + " WHERE email = ?",
                new String[]{email});
        String username = null;
        if (cursor.moveToFirst()) {
            username = cursor.getString(0);
        }
        cursor.close();
        return username;
    }

    // Get email by username
    public String getEmailByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT " + COL_EMAIL + " FROM " + TABLE_USERS + " WHERE username = ?",
                new String[]{username});
        String email = null;
        if (cursor.moveToFirst()) {
            email = cursor.getString(0);
        }
        cursor.close();
        return email;
    }

    // Update password (for reset)
    public boolean updatePassword(String usernameOrEmail, String newPasswordHash) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_PASSWORD, newPasswordHash);

        int rowsAffected;
        if (usernameOrEmail.contains("@")) {
            // Update by email
            rowsAffected = db.update(TABLE_USERS, values, COL_EMAIL + "=?",
                    new String[]{usernameOrEmail});
        } else {
            // Update by username
            rowsAffected = db.update(TABLE_USERS, values, COL_USERNAME + "=?",
                    new String[]{usernameOrEmail});
        }

        return rowsAffected > 0;
    }

    // Update last active time
    public void updateLastActive(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_LAST_ACTIVE, System.currentTimeMillis());
        db.update(TABLE_USERS, values, COL_USERNAME + "=?", new String[]{username});
    }

    // ADD THIS METHOD - For TimeTrackingService
    public void addTime(String username, long seconds) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Update the total_time column by adding seconds
        db.execSQL("UPDATE " + TABLE_USERS + " SET " + COL_TIME + " = " + COL_TIME + " + ?" +
                " WHERE " + COL_USERNAME + " = ?", new Object[]{seconds, username});
    }

    // Optional: Get study time for a user
    public long getStudyTime(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT " + COL_TIME + " FROM " + TABLE_USERS + " WHERE username = ?",
                new String[]{username});
        long time = 0;
        if (cursor.moveToFirst()) {
            time = cursor.getLong(0);
        }
        cursor.close();
        return time;
    }

    // Get user info by username or email
    public String[] getUserInfo(String input) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] result = new String[2]; // [0] = username, [1] = email

        Cursor cursor = db.rawQuery(
                "SELECT " + COL_USERNAME + ", " + COL_EMAIL +
                        " FROM " + TABLE_USERS + " WHERE username = ? OR email = ?",
                new String[]{input, input});

        if (cursor.moveToFirst()) {
            result[0] = cursor.getString(0); // username
            result[1] = cursor.getString(1); // email
            cursor.close();
            return result;
        }
        cursor.close();
        return null;
    }
}