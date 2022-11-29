package com.example.myaccount;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.myaccount.constant.Constant;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyService extends Service {
    public final String TAG = "TestLog";
    public ContentResolver resolver;
    public Uri usersUri;
    public Uri adminsUri;
    private Cursor usersCursor;
    private Cursor adminsCursor;
    private static final int USERS = 1;
    private static final int ADMINS = 2;
    private String tableUsers = "content://com.example.myaccount/users";
    private String tableAdmins = "content://com.example.myaccount/admins";

    public MyService () {
        usersUri = Uri.parse(tableUsers);
        adminsUri = Uri.parse(tableAdmins);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        resolver = getContentResolver();
        usersCursor = resolver.query(usersUri, null, null, null, null);
        adminsCursor = resolver.query(adminsUri, null, null, null, null);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(Constant.TAG, "MyService onBind!");
        return new MyBinder();
    }

    public class MyBinder extends IMyUser.Stub {
        //实现接口
        @SuppressLint("Range")
        @Override
        public String[] onLogin(int type, String theUser, String thePass) {
            String[] userString = new String[3];
            usersUri = Uri.parse(tableUsers);
            adminsUri = Uri.parse(tableAdmins);
            resolver = getContentResolver();
            Cursor cursor = null;
            switch (type) {
                case USERS:
                    cursor = resolver.query(usersUri, null, null, null, null);
                    cursor.moveToFirst(); //第一行
                    cursor.moveToPrevious(); //前一行
                    if (cursor != null) {
                        while (cursor.moveToNext()) {  //下一行 循环
                            String account1 = cursor.getString(cursor.getColumnIndex("account"));
                            String pass1 = cursor.getString(cursor.getColumnIndex("password"));
                            Log.i(Constant.TAG, String.format("MyService cursor user1 = %s pass1 = %s", account1, pass1));
                            if (theUser.equals(account1) && thePass.equals(pass1)) {
                                userString[0] = cursor.getString(cursor.getColumnIndex("_id"));
                                userString[1] = cursor.getString(cursor.getColumnIndex("username"));
                                userString[2] = cursor.getString(cursor.getColumnIndex("password"));
                            }
                        }
                    } else {
                        Log.i(Constant.TAG, "MyService cursor = null");
                    }
                    break;
                case ADMINS:
                    cursor = resolver.query(adminsUri, null, null, null, null);
                    cursor.moveToFirst(); //第一行
                    cursor.moveToPrevious(); //前一行
                    if (cursor != null) {
                        while (cursor.moveToNext()) {  //下一行 循环
                            String account1 = cursor.getString(cursor.getColumnIndex("account"));
                            String pass1 = cursor.getString(cursor.getColumnIndex("password"));
                            Log.i(Constant.TAG, String.format("MyService cursor user1 = %s pass1 = %s", account1, pass1));
                            if (theUser.equals(account1) && thePass.equals(pass1)) {
                                userString[0] = cursor.getString(cursor.getColumnIndex("_id"));
                                userString[1] = cursor.getString(cursor.getColumnIndex("username"));
                                userString[2] = cursor.getString(cursor.getColumnIndex("password"));
                            }
                        }
                    } else {
                        Log.i(Constant.TAG, "MyService cursor = null");
                    }
                    break;
            }
            return userString;
        }

        @Override
        public boolean onLoginVerify(int type, String theUser, String thePass) {
            Log.i(Constant.TAG, "MyService onLoginVerify! type = " + type);
            Boolean mBool = false;
            usersUri = Uri.parse(tableUsers);
            adminsUri = Uri.parse(tableAdmins);
            resolver = getContentResolver();
            Cursor cursor = null;
            switch (type) {
                case USERS:
                    cursor = resolver.query(usersUri, null, null, null, null);
                    cursor.moveToFirst(); //第一行
                    cursor.moveToPrevious(); //前一行
                    if (cursor != null) {
                        while (cursor.moveToNext()) {  //下一行 循环
                            @SuppressLint("Range") String account1 = cursor.getString(cursor.getColumnIndex("account"));
                            @SuppressLint("Range") String pass1 = cursor.getString(cursor.getColumnIndex("password"));
                            Log.i(Constant.TAG, String.format("MyService cursor user1 = %s pass1 = %s", account1, pass1));
                            if (theUser.equals(account1) && thePass.equals(pass1)) {
                                mBool = true;
                                break;
                            } else {
                                mBool = false;
                            }
                        }
                    } else {
                        Log.i(Constant.TAG, "MyService onLoginVerify cursor = null");
                    }
                    break;
                case ADMINS:
                    cursor = resolver.query(adminsUri, null, null, null, null);
                    cursor.moveToFirst(); //第一行
                    cursor.moveToPrevious(); //前一行
                    if (cursor != null) {
                        while (cursor.moveToNext()) {  //下一行 循环
                            @SuppressLint("Range") String account1 = cursor.getString(cursor.getColumnIndex("account"));
                            @SuppressLint("Range") String pass1 = cursor.getString(cursor.getColumnIndex("password"));
                            Log.i(Constant.TAG, String.format("MyService cursor user1 = %s pass1 = %s", account1, pass1));
                            if (theUser.equals(account1) && thePass.equals(pass1)) {
                                mBool = true;
                                break;
                            } else {
                                mBool = false;
                            }
                        }
                    } else {
                        Log.i(Constant.TAG, "MyService onLoginVerify cursor = null");
                    }
                    break;
            }
            Log.i(Constant.TAG, "MyService onLoginVerify mBool = " + mBool);
            return mBool;
        }

        @Override
        public int getListCount(int type) {
            Log.i(Constant.TAG, "MyService getListCount! type = " + type);
            resolver = getContentResolver();
            Cursor cursor = null;
            int count = 0;
            switch (type) {
                case USERS:
                    usersUri = Uri.parse(tableUsers);
                    cursor = resolver.query(usersUri, null, null, null, null, null);
                    count = cursor.getCount();
                    break;
                case ADMINS:
                    adminsUri = Uri.parse(tableAdmins);
                    cursor = resolver.query(adminsUri, null, null, null, null, null);
                    count = cursor.getCount();
                    break;
            }
            Log.i(Constant.TAG, "MyService getListCount count = " + count);
            return count;
        }

        @Override
        public void toFirst(int type) {
            Log.i(TAG, "MyService toFirst! type = " + type);
            switch (type) {
                case USERS:
                    usersCursor.moveToFirst();
                    usersCursor.moveToPrevious();
                    break;
                case ADMINS:
                    adminsCursor.moveToFirst();
                    adminsCursor.moveToPrevious();
                    break;
            }
        }

        @Override
        public void toNext(int type) {
            Log.i(TAG, "MyService toNext! type = " + type);
            switch (type) {
                case USERS:
                    usersCursor.moveToNext();
                    break;
                case ADMINS:
                    adminsCursor.moveToNext();
                    break;
            }
        }

        @SuppressLint("Range")
        @Override
        public void onUpdateQuery(int type) {
            switch (type) {
                case USERS:
                    usersCursor = resolver.query(usersUri, null, null, null, null);
                    break;
                case ADMINS:
                    adminsCursor = resolver.query(usersUri, null, null, null, null);
                    break;
            }
        }

        @SuppressLint("Range")
        @Override
        public String[] onQuery(int type){
                Log.i(TAG, "MyService onQurey! type = " + type);
                String[] userString = new String[4];
                switch (type) {
                    case USERS:
                        usersUri = Uri.parse(tableUsers);
                        resolver = getContentResolver();
                        toNext(USERS);
                        userString[0] = usersCursor.getString(usersCursor.getColumnIndex("_id"));
                        userString[1] = usersCursor.getString(usersCursor.getColumnIndex("username"));
                        userString[2] = usersCursor.getString(usersCursor.getColumnIndex("account"));
                        userString[3] = usersCursor.getString(usersCursor.getColumnIndex("password"));
                        Log.i(TAG, "MyService onQuery mString = " + userString[0] + " " + userString[1] + " " + userString[2] + " " + userString[3]);
                        break;
                    case ADMINS:
                        adminsUri = Uri.parse(tableAdmins);
                        resolver = getContentResolver();
                        toNext(ADMINS);
                        userString[0] = adminsCursor.getString(usersCursor.getColumnIndex("account"));
                        userString[1] = adminsCursor.getString(usersCursor.getColumnIndex("password"));
                        Log.i(TAG, "MyService onQuery String = " + userString[0] + " " + userString[1]);
                        break;
                }
                return userString;
            }

        @Override
        public void onRegister(int type, String username, String account, String password) {
            resolver = getContentResolver();
            ContentValues values = new ContentValues();
            switch (type) {
                case USERS:
                    values.put("username", username);
                    values.put("account", account);
                    values.put("password", password);
                    resolver.insert(usersUri, values);
                    Log.i(TAG, "MyService onRegister " + USERS + " " + values);
                    break;
                case ADMINS:
                    values.put("account", account);
                    values.put("password", password);
                    resolver.insert(adminsUri, values);
                    Log.i(TAG, "MyService onRegister " + ADMINS + " " + values);
                    break;
            }
        }

        @Override
        public int onUpdate(int type, String mId, String newPass) {
            resolver = getContentResolver();
            ContentValues values = new ContentValues();
            values.put("password", newPass);
            int id = 0;
            switch (type) {
                case USERS:
                    id = resolver.update(usersUri, values, "_id = ?", new String[]{mId});
                    Log.i(TAG, "MyService onUpdate id = " + id);
                    break;
                case ADMINS:
                    id = resolver.update(adminsUri, values, "_id = ?", new String[]{mId});
                    Log.i(TAG, "MyService onUpdate id = " + id);
                    break;
            }
            return id;
        }

        @Override
        public int onDeleteUser(String mId) {
            Log.i(TAG, "MyService onDeleteUser mId = " + mId);
            resolver = getContentResolver();
            return resolver.delete(usersUri, "_id = ?", new String[]{mId});
        }

        @Override
        public int onDeleteAllUser() {
            Log.i(TAG, "MyService onDeleteAllUser!");
            resolver = getContentResolver();
            return resolver.delete(usersUri, null, null);
        }

        @Override
        public boolean isNoUser(int type) {
            Log.i(Constant.TAG, "MyService isNoUser! type = " + type);
            resolver = getContentResolver();
            Cursor cursor = null;
            boolean noUser = false;
            switch (type) {
                case USERS:
                    cursor = resolver.query(usersUri, null, null, null, null);
                    if (!cursor.moveToFirst()) {
                        noUser = true;
                    } else {
                        noUser = false;
                    }
                    break;
                case ADMINS:
                    cursor = resolver.query(adminsUri, null, null, null, null);
                    if (!cursor.moveToFirst()) {
                        noUser = true;
                    } else {
                        noUser = false;
                    }
                    break;
            }
            return noUser;
        }

        @Override
        public boolean isExistUser(int type, String name) {
            usersUri = Uri.parse(tableUsers);
            boolean bool = false;
            resolver = getContentResolver();
            Cursor cursor = resolver.query(usersUri, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                cursor.moveToPrevious();
                for (int i = 0; i < cursor.getCount(); i++) {  //循环读取数据
                    cursor.moveToNext();
                    @SuppressLint("Range") String account = cursor.getString(cursor.getColumnIndex("account"));
                    Log.i(Constant.TAG, "MyService mExistUser account = " + account);
                    if (name.equals(account)) {
                        bool = true;
                        break;
                    } else {
                        bool = false;
                    }
                }
            }
            Log.i(Constant.TAG, "MyService mQueryUser bool = " + bool);
            return bool;
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(Constant.TAG, "MyService onUnbind!");
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(Constant.TAG, "MyService onRebind!");
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i(Constant.TAG, "MyService onDestroy!");
        super.onDestroy();
    }

}