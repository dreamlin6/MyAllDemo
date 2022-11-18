package com.example.myaccount.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UserProvider extends ContentProvider {
    private SQLiteDatabase db;
    private MyDbOpenhelper dbOpenhelper;

    static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        matcher.addURI("com.example.myaccount","users",100); //URI统一资源标识符
    }
    public UserProvider() {

    }

    @Override
    public boolean onCreate() {
        dbOpenhelper = new MyDbOpenhelper(getContext());
        db = dbOpenhelper.getWritableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return db.query("users",null,null,null,null,null,null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public synchronized Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long id = db.insert("users", null, values);
        if (id != -1) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return uri;
    }

    @Override
    public synchronized int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int resultId = 0;
        resultId = db.delete("users", selection, selectionArgs);	//返回删除成功的行号值,失败返回-1
        getContext().getContentResolver().notifyChange(uri, null);
        return resultId;
    }

    @Override
    public synchronized int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int resultId = 0;
        resultId = db.update("users", values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return resultId;
    }
}
