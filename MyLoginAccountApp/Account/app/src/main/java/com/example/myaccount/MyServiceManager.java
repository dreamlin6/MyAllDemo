package com.example.myaccount;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

public class MyServiceManager {

    private Context mContext;
    private final String TAG = "MyServiceManager";
    private IMyUser iMyUser;
    private Handler mMsgHander;
    private static final int SERVICE_CONNECTED = 1;
    private static final int BIND_SERVICE = 2;

    public MyServiceManager(Context context) {
        mContext = context;
    }

    public void bindService() {
        if (iMyUser == null) {
            Intent intent = new Intent();
            intent.setAction("com.example.service.action");
            intent.setPackage("com.example.myaccount");
            mContext.bindService(intent, connection, Context.BIND_AUTO_CREATE);
        }
    }

    public void unBindService() {
        mContext.unbindService(connection);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override

        public void onServiceDisconnected(ComponentName name) {
            if (mMsgHander != null) {
                mMsgHander.sendEmptyMessage(SERVICE_CONNECTED);
            }
        }

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iMyUser = IMyUser.Stub.asInterface(iBinder);
            if (iMyUser != null) {

            } else {
            }
            if (mMsgHander != null) {
                mMsgHander.sendEmptyMessage(BIND_SERVICE);
            }
        }
    };

    public void handleMessage(Message msg) {
        int what = msg.what;
        switch (what) {
            case BIND_SERVICE:
                Intent intent = new Intent();
                intent.setAction("com.example.service.action");
                intent.setPackage("com.example.myaccount");
                mContext.bindService(intent, connection, Context.BIND_AUTO_CREATE);
                break;
            case SERVICE_CONNECTED:
                break;
        }
    }
}
