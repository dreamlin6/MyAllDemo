package com.example.myaccount;

import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

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
        public int mLogin(String editUser, String editPass, String theUser, String thePass) {
            if (editUser.equals(theUser) && editPass.equals(thePass)) {
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
            resolver.query(uri, null, null, null, null);
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