package com.example.myaccount;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import com.example.myaccount.constant.Constant;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyService extends Service {
    private final String TAG = "TestLog";
    private ContentResolver resolver;
    private Uri uri;
    private ReentrantReadWriteLock readWriteLock;

    public MyService () {
        readWriteLock = new ReentrantReadWriteLock();
        uri = Uri.parse("content://com.example.myaccount/users");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends IMyUser.Stub {
        //实现接口
        @Override
        public int mLogin(String theUser, String thePass) {
            if ("".equals(theUser) && "".equals(thePass)) {
                return 1;
            } else {
                return 2;
            }
        }

        @Override
        public void mRegister(String username, String account, String password) {
            resolver = getContentResolver();
            readWriteLock.writeLock().lock();
            ContentValues values = new ContentValues();
            values.put("username", username);
            values.put("account", account);
            values.put("password", password);
            resolver.insert(uri, values);
            readWriteLock.writeLock().unlock();
            Log.i(TAG, "MyService mRegister" + uri + values);
        }

        @Override
        public void mQurey() {
            Log.i(TAG, "MyService mQurey");
            resolver = getContentResolver();
            Cursor cursor = resolver.query(uri, null, null, null, null);
        }

        @Override
        public int mUpdate(String mId) {
            resolver = getContentResolver();
            readWriteLock.readLock().lock();
            int id = resolver.update(uri, null, "_id = ?", new String[]{mId});
            readWriteLock.readLock().unlock();
            Log.i(TAG, "MyService mUpdate id = " + id);
            return id;
        }

        @Override
        public int mDeleteUser(String mId) {
            resolver = getContentResolver();
            Log.i(TAG, "MyService mDeleteUser mId = " + mId);
            return resolver.delete(uri, "_id = ?", new String[]{mId});
        }

        @Override
        public int mDeleteAllUser() {
            resolver = getContentResolver();
            Log.i(TAG, "MyService mDeleteAllUser");
            return resolver.delete(uri, null, null);
        }

        @Override
        public boolean isExistUser(String name) {
            Uri uri = Uri.parse("content://com.example.myaccount/users");
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
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}