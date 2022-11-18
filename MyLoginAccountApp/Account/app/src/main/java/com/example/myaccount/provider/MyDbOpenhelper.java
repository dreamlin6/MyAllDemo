package com.example.myaccount.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbOpenhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyDb";
    private static final int DATABASE_VERSION = 2;
    public static final String Tab_User = "create table table1name (\"User\");";
    public static final String Tab_Admin = "create table table1name (\"Admin\");";

    public MyDbOpenhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users (_id INTEGER PRIMARY KEY AUTOINCREMENT, username text, account text, [password] text);");
        db.execSQL("create table admins (_id INTEGER PRIMARY KEY AUTOINCREMENT, account text, [password] text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS admins");
        onCreate(db);
    }

    public boolean deleteDatabase(Context context) {
        return context.deleteDatabase(DATABASE_NAME);
    }
}
