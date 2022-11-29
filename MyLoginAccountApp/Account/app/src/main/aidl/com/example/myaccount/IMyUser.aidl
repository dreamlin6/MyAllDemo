// IMyUser.aidl
package com.example.myaccount;

// Declare any non-default types here with import statements

interface IMyUser {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

    //增加接口

    String[] onLogin(int type, String theUser, String thePass);

    void onRegister(int type, String username, String account, String password);

    String[] onQuery(int type);

    int onUpdate(int type, String mId, String newPass);

    int onDeleteUser(String mId);

    int onDeleteAllUser();

    boolean isExistUser(int type, String name);

    boolean isNoUser(int type);

    boolean onLoginVerify(int type, String theUser, String thePass);

    int getListCount(int type);

    void toFirst(int type);

    void toNext(int type);

    void onUpdateQuery(int type);
}