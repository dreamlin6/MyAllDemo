package com.example.myaccount;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

import com.example.myaccount.constant.Constant;

public class MyServiceManager {

    private Context mContext;
    private final String TAG = "TestLog";
    private IMyUser iMyUser;
    private ServiceConnection connection;

    public MyServiceManager(Context context){
        int count = 0;
        Log.i(Constant.TAG, "MyServiceManager MyServiceManager!");
        mContext = context;
        if (connection == null) {
            Log.i(Constant.TAG, "MyServiceManager MyServiceManager connection == null");
            connection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    Log.i(Constant.TAG, "MyServiceManager onServiceConnected!");
                    iMyUser = IMyUser.Stub.asInterface(iBinder);
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    Log.i(Constant.TAG, "MyServiceManager onServiceDisconnected! name = " + name);
                }
            };
        }

        try {
            while (iMyUser == null) {
                Intent intent = new Intent();
                intent.setAction("com.example.service.action");
                intent.setPackage("com.example.myaccount");
                boolean res = mContext.bindService(intent, connection, Context.BIND_AUTO_CREATE); //在bindService后马上调用Service中的方法，结果返回了空指针，因为bindservice是异步操作，有时候没有办法马上绑定服务就可以用
                Log.i(Constant.TAG, "MyServiceManager bindService res = " + res);

                if (count++ == 2) {
                    return;
                }
                Thread.sleep(200);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void unBindService() {
        if (connection != null) {
            Log.i(Constant.TAG, "MyServiceManager unBindService!");
            mContext.unbindService(connection);
        }
    }

    public int getCount() throws RemoteException {
        return iMyUser.getListCount();
    }

    public void updateQuery() throws RemoteException {
        iMyUser.updateQuery();
    }

    public int deleteUser(String str) throws RemoteException {
        return iMyUser.mDeleteUser(str);
    }

    public int deleteAllUser() throws RemoteException {
        return iMyUser.mDeleteAllUser();
    }

    public boolean isNoUser() throws RemoteException {
        return iMyUser.isNoUser();
    }

    public void toFirst() throws RemoteException {
        iMyUser.toFirst();
    }

    public void toNext() throws RemoteException {
        iMyUser.toNext();
    }

    public String[] mQuery() throws RemoteException {
        return iMyUser.mQurey();
    }

    public int mUpdate(String mId, String newPass) throws RemoteException {
        return iMyUser.mUpdate(mId, newPass);
    }

    public boolean mLoginVerify(String theUser, String thePass) throws RemoteException {
        return iMyUser.mLoginVerify(theUser, thePass);
    }

    public String[] mLogin(String theUser, String thePass) throws RemoteException {
        return iMyUser.mLogin(theUser, thePass);
    }

    public boolean isExistUser(String name) throws RemoteException {
        return iMyUser.isExistUser(name);
    }

    public void mRegister(String username, String account, String password) throws RemoteException {
        iMyUser.mRegister(username, account, password);
    }
}
