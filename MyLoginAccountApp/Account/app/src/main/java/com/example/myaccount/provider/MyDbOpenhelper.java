package com.example.myaccount.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbOpenhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyDb";
    private static final int DATABASE_VERSION = 1;
    public static final String TAB_USERS = "users";
    public static final String TAB_ADMINS = "admins";

    public MyDbOpenhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TAB_USERS + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, username text, account text, [password] text);");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TAB_ADMINS + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, account text, [password] text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TAB_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TAB_ADMINS);
        onCreate(db);
    }

    public boolean deleteDatabase(Context context) {
        return context.deleteDatabase(DATABASE_NAME);
    }
}
