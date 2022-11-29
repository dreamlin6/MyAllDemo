package com.example.myaccount.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UserProvider extends ContentProvider {
    private SQLiteDatabase db;
    private MyDbOpenhelper dbOpenhelper;
    private static final int Users_Code = 1;
    private static final int Admins_Code = 2;
    public static final String AUTOHORITY = "com.example.myaccount";

    private static UriMatcher matcher;

    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTOHORITY, "users", Users_Code);
        matcher.addURI(AUTOHORITY,"admins", Admins_Code);
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
        String table = getTableName(uri);

        return db.query(table, null, null, null, null, null, null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public synchronized Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        String table = getTableName(uri);
        long id = db.insert(table, null, values);
        if (id != -1) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return uri;
    }

    @Override
    public synchronized int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        String table = getTableName(uri);
        int resultId = 0;
        resultId = db.delete(table, selection, selectionArgs);	//返回删除成功的行号值,失败返回-1
        getContext().getContentResolver().notifyChange(uri, null);
        return resultId;
    }

    @Override
    public synchronized int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        String table = getTableName(uri);
        int resultId = 0;
        resultId = db.update(table, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return resultId;
    }

    private String getTableName(Uri uri){
        String tableName = null;
        switch (matcher.match(uri)) {
            case Users_Code:
                tableName = dbOpenhelper.TAB_USERS;
                break;
            case Admins_Code:
                tableName = dbOpenhelper.TAB_ADMINS;
                break;
        }
        return tableName;
    }
}
