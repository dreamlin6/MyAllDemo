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
    public Uri uri;
    private ReentrantReadWriteLock readWriteLock;
    private Cursor cursor;

    public MyService () {
        readWriteLock = new ReentrantReadWriteLock();
        uri = Uri.parse("content://com.example.myaccount/users");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        resolver = getContentResolver();
        cursor = resolver.query(uri, null, null, null, null);
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
        public String[] mLogin(String theUser, String thePass) {
            String[] mString = new String[3];
            uri = Uri.parse("content://com.example.myaccount/users");
            resolver = getContentResolver();
            Cursor cursor = resolver.query(uri, null, null, null, null);
            cursor.moveToFirst(); //第一行
            cursor.moveToPrevious(); //前一行
            if (cursor != null) {
                while (cursor.moveToNext()) {  //下一行 循环
                    String account1 = cursor.getString(cursor.getColumnIndex("account"));
                    String pass1 = cursor.getString(cursor.getColumnIndex("password"));
                    Log.i(Constant.TAG, String.format("MyService cursor user1 = %s pass1 = %s", account1, pass1));
                    if (theUser.equals(account1) && thePass.equals(pass1)) {
                        mString[0] = cursor.getString(cursor.getColumnIndex("_id"));
                        mString[1] = cursor.getString(cursor.getColumnIndex("username"));
                        mString[2] = cursor.getString(cursor.getColumnIndex("password"));
                        return mString;
                    }
                }
                return mString;
            } else {
                Log.i(Constant.TAG, "MyService cursor = null");
                return mString;
            }
        }

        @Override
        public boolean mLoginVerify(String theUser, String thePass) {
            Boolean mBool = false;
            uri = Uri.parse("content://com.example.myaccount/users");
            resolver = getContentResolver();
            Cursor cursor = resolver.query(uri, null, null, null, null);
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
                Log.i(Constant.TAG, "MyService mLoginVerify cursor = null");
            }
            Log.i(Constant.TAG, "MyService mLoginVerify mBool = " + mBool);
            return mBool;
        }

        @Override
        public int getListCount() {
            Log.i(Constant.TAG, "MyService getListCount!");
            uri = Uri.parse("content://com.example.myaccount/users");
            resolver = getContentResolver();
            Cursor cursor = resolver.query(uri, null, null, null, null, null);
            int count = cursor.getCount();
            Log.i(Constant.TAG, "MyService getListCount count = " + count);
            return count;
        }

        @Override
        public void toFirst() {
            Log.i(TAG, "MyService toFirst!");
            cursor.moveToFirst();
            cursor.moveToPrevious();
        }

        @Override
        public void toNext() {
            Log.i(TAG, "MyService toNext!");
            cursor.moveToNext();
        }

        @Override
        public void updateQuery(){
            cursor = resolver.query(uri, null, null, null, null);
        }

        @SuppressLint("Range")
        @Override
        public String[] mQurey() {
            Log.i(TAG, "MyService mQurey!");
            String[] mString = new String[4];
            uri = Uri.parse("content://com.example.myaccount/users");
            resolver = getContentResolver();
//            cursor = resolver.query(uri, null, null, null, null);
            toNext();
            mString[0] = cursor.getString(cursor.getColumnIndex("_id"));
            mString[1] = cursor.getString(cursor.getColumnIndex("username"));
            mString[2] = cursor.getString(cursor.getColumnIndex("account"));
            mString[3] = cursor.getString(cursor.getColumnIndex("password"));
            Log.i(TAG, "MyService mQurey mString = " + mString[0] + " " + mString[1] + " " + mString[2] + " " + mString[3]);
            return mString;
        }

        @Override
        public void mRegister(String username, String account, String password) {
            resolver = getContentResolver();
//            readWriteLock.writeLock().lock();
            ContentValues values = new ContentValues();
            values.put("username", username);
            values.put("account", account);
            values.put("password", password);
            resolver.insert(uri, values);
//            readWriteLock.writeLock().unlock();
            Log.i(TAG, "MyService mRegister " + uri + " " + values);
        }

        @Override
        public int mUpdate(String mId, String newPass) {
            resolver = getContentResolver();
//            readWriteLock.readLock().lock();
            ContentValues values = new ContentValues();
            values.put("password", newPass);
            int id = resolver.update(uri, values, "_id = ?", new String[]{mId});
//            readWriteLock.readLock().unlock();
            Log.i(TAG, "MyService mUpdate id = " + id);
            return id;
        }

        @Override
        public int mDeleteUser(String mId) {
            Log.i(TAG, "MyService mDeleteUser mId = " + mId);
            resolver = getContentResolver();
            return resolver.delete(uri, "_id = ?", new String[]{mId});
        }

        @Override
        public int mDeleteAllUser() {
            Log.i(TAG, "MyService mDeleteAllUser!");
            resolver = getContentResolver();
            return resolver.delete(uri, null, null);
        }

        @Override
        public boolean isNoUser() {
            Log.i(Constant.TAG, "MyService isNoUser!");
            uri = Uri.parse("content://com.example.myaccount/users");
            resolver = getContentResolver();
            Cursor cursor = resolver.query(uri, null, null, null, null);
            if (!cursor.moveToFirst()) {
                Log.i(Constant.TAG, "MyService isNoUser true");
                return true;
            } else {
                return false;
            }
        }

        @Override
        public boolean isExistUser(String name) {
            uri = Uri.parse("content://com.example.myaccount/users");
            boolean bool = false;
            resolver = getContentResolver();
            Cursor cursor = resolver.query(uri, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                cursor.moveToPrevious();
                for (int i = 0; i < cursor.getCount(); i++) {  //循环读取数据
                    cursor.moveToNext();
                    @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("username"));
                    Log.i(Constant.TAG, "MyService mExistUser username = " + username);
                    if (name.equals(username)) {
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