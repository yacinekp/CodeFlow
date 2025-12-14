package com.example.projetm2;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME = "user_session";
    private static final String KEY_USERNAME = "username";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    // Save username after login
    public void createSession(String username) {
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    // Return logged username
    public String getUsername() {
        return preferences.getString(KEY_USERNAME, null);
    }

    // True if user is logged in
    public boolean isLoggedIn() {
        return getUsername() != null;
    }

    // Logout
    public void logout() {
        editor.clear();
        editor.apply();
    }
}