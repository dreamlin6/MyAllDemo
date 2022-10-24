package com.example.mservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import java.util.List;

import javax.security.auth.callback.Callback;

public class MyService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new LocalBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public final class LocalBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

    protected void sendContentBroadcast(String name) {
        // TODO Auto-generated method stub
        Intent intent=new Intent();
        intent.setAction("com.example.service.action");
        intent.putExtra("name", name);
        sendBroadcast(intent);
    }
}