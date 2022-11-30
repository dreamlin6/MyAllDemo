package com.example.myaccount;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.myaccount.constant.Constant;

public class MyServiceManager {

    private Context mContext;
    private final String TAG = "TestLog";
    private IMyUser iMyUser;
    private static final int USERS = 1;
    private static final int ADMINS = 2;
    private ServiceConnection connection;

    public MyServiceManager(Context context){
        Log.i(Constant.TAG, "MyServiceManager MyServiceManager!");
        mContext = context;
    }

    public void bindService() {
        Log.i(Constant.TAG, "MyServiceManager mBindService!");
        if (connection == null) {
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

        if (iMyUser == null) {
            Intent intent = new Intent();
            intent.setAction("com.example.service.action");
            intent.setPackage("com.example.myaccount");
            boolean res = mContext.bindService(intent, connection, Context.BIND_AUTO_CREATE); //在bindService后马上调用Service中的方法，结果返回了空指针，因为bindservice是异步操作，有时候没有办法马上绑定服务就可以用
            Log.i(Constant.TAG, "MyServiceManager bindService res = " + res);
        }
    }

    public void unBindService() {
        if (connection != null) {
            Log.i(Constant.TAG, "MyServiceManager unBindService!");
            mContext.unbindService(connection);
        }
    }

    public int getCount() throws RemoteException {
        return iMyUser.getListCount(USERS);
    }

    public void onUpdateQuery() throws RemoteException {
        iMyUser.onUpdateQuery(USERS);
    }

    public int onDeleteUser(String str) throws RemoteException {
        return iMyUser.onDeleteUser(str);
    }

    public int onDeleteAllUser() throws RemoteException {
        return iMyUser.onDeleteAllUser();
    }

    public boolean isNoUser() throws RemoteException {
        return iMyUser.isNoUser(USERS);
    }

    public void toFirst() throws RemoteException {
        iMyUser.toFirst(USERS);
    }

    public void toNext() throws RemoteException {
        iMyUser.toNext(USERS);
    }

    public String[] onQuery() throws RemoteException {
        return iMyUser.onQuery(USERS);
    }

    public int onUpdate(String mId, String newPass) throws RemoteException {
        return iMyUser.onUpdate(USERS, mId, newPass);
    }

    public boolean onLoginVerify(String theUser, String thePass) throws RemoteException {
        return iMyUser.onLoginVerify(USERS, theUser, thePass);
    }

    public String[] onLogin(String theUser, String thePass) throws RemoteException {
        return iMyUser.onLogin(USERS, theUser, thePass);
    }

    public boolean isExistUser(String name) throws RemoteException {
        return iMyUser.isExistUser(USERS, name);
    }

    public void onRegister(String username, String account, String password) throws RemoteException {
        iMyUser.onRegister(USERS, username, account, password);
    }

    public void onAdminRegister(String account, String password) throws RemoteException {
        iMyUser.onRegister(ADMINS, null, account, password);
    }

    public boolean onAdminLoginVerify(String theUser, String thePass) throws RemoteException {
        return iMyUser.onLoginVerify(ADMINS, theUser, thePass);
    }

    public int onAdminUpdate(String mId, String newPass) throws RemoteException {
        return iMyUser.onUpdate(ADMINS, mId, newPass);
    }

    public int getAdminCount() throws RemoteException {
        return iMyUser.getListCount(ADMINS);
    }
}
