package com.example.myaccount.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyContentProvider extends ContentProvider {

    private static final String AUTHORITY = "com.example.myaccount";
    //数据路径
    public static final String PATH_USERS = "user";
    //访问ContentProvider的URL
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH_USERS);
    //返回代码
    public static final int USER_INFO = 1;
    //创建UriMatcher对象
    private static UriMatcher uriMatcher;

    static { //创建静态代码块
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, PATH_USERS, USER_INFO);
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
