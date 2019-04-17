package com.example.abhinav.projecthp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManagement {
    SharedPreferences pref;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "Reg";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_EMAIL = "Email";
    public static final String KEY_PASSWORD = "Password";

    public SessionManagement(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String email, String password){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.commit();
    }

    public void createForgotPasswordSession(String email){
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }
}