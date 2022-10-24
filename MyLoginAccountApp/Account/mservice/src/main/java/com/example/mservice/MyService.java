package com.example.mservice;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
    private ContentResolver resolver;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MyBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public final class MyBinder extends Binder {
        public void mLogin() {
        }
    }

    public void mLogin() {

    }

    public void mRegister() {

    }

    public void mChange() {

    }

}