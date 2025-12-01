package com.example.projetm2;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "app_session";
    private static final String KEY_LOGGED = "logged_in";
    private static final String KEY_USERNAME = "username";

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public SessionManager(Context ctx) {
        prefs = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void createSession(String username) {
        editor.putBoolean(KEY_LOGGED, true);
        editor.putString(KEY_USERNAME, username);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean(KEY_LOGGED, false);
    }

    public String getUsername() {
        return prefs.getString(KEY_USERNAME, "username");
    }

    public void logout() {
        editor.clear();
        editor.commit();
    }
}
