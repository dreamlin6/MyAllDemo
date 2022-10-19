package com.example.myaccount.bean;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbOpenhelper extends SQLiteOpenHelper {

    public MyDbOpenhelper(Context context) {
        super(context, "mydb", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users (_id INTEGER PRIMARY KEY AUTOINCREMENT, username text, account text, [password] text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }
}
