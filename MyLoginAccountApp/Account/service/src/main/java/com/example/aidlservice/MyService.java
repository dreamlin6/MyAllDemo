package com.example.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "---AIDLTest---";
    public MyService() {
    }

    public static String mString = "service";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Service onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d(TAG, "Service onBind");
        return new Mybinder();
    }

    class Mybinder extends IMyAidlInterface.Stub{
        @Override
        public String getStringFromService() throws RemoteException {
            Log.d(TAG, "Service to Client");
            return mString;
        }
    }
}