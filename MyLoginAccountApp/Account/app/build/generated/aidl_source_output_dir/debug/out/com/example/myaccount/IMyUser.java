/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package com.example.myaccount;
// Declare any non-default types here with import statements

public interface IMyUser extends android.os.IInterface
{
  /** Default implementation for IMyUser. */
  public static class Default implements com.example.myaccount.IMyUser
  {
    /**
         * Demonstrates some basic types that you can use as parameters
         * and return values in AIDL.
         *///增加接口

    @Override public java.lang.String[] mLogin(java.lang.String theUser, java.lang.String thePass) throws android.os.RemoteException
    {
      return null;
    }
    @Override public void mRegister(java.lang.String username, java.lang.String account, java.lang.String password) throws android.os.RemoteException
    {
    }
    @Override public java.lang.String[] mQurey() throws android.os.RemoteException
    {
      return null;
    }
    @Override public int mUpdate(java.lang.String mId, java.lang.String newPass) throws android.os.RemoteException
    {
      return 0;
    }
    @Override public int mDeleteUser(java.lang.String mId) throws android.os.RemoteException
    {
      return 0;
    }
    @Override public int mDeleteAllUser() throws android.os.RemoteException
    {
      return 0;
    }
    @Override public boolean isExistUser(java.lang.String name) throws android.os.RemoteException
    {
      return false;
    }
    @Override public boolean isNoUser() throws android.os.RemoteException
    {
      return false;
    }
    @Override public boolean mLoginVerify(java.lang.String theUser, java.lang.String thePass) throws android.os.RemoteException
    {
      return false;
    }
    @Override public int getListCount() throws android.os.RemoteException
    {
      return 0;
    }
    @Override public void toFirst() throws android.os.RemoteException
    {
    }
    @Override public void toNext() throws android.os.RemoteException
    {
    }
    @Override public void updateQuery() throws android.os.RemoteException
    {
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements com.example.myaccount.IMyUser
  {
    private static final java.lang.String DESCRIPTOR = "com.example.myaccount.IMyUser";
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an com.example.myaccount.IMyUser interface,
     * generating a proxy if needed.
     */
    public static com.example.myaccount.IMyUser asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof com.example.myaccount.IMyUser))) {
        return ((com.example.myaccount.IMyUser)iin);
      }
      return new com.example.myaccount.IMyUser.Stub.Proxy(obj);
    }
    @Override public android.os.IBinder asBinder()
    {
      return this;
    }
    @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
    {
      java.lang.String descriptor = DESCRIPTOR;
      switch (code)
      {
        case INTERFACE_TRANSACTION:
        {
          reply.writeString(descriptor);
          return true;
        }
        case TRANSACTION_mLogin:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          java.lang.String _arg1;
          _arg1 = data.readString();
          java.lang.String[] _result = this.mLogin(_arg0, _arg1);
          reply.writeNoException();
          reply.writeStringArray(_result);
          return true;
        }
        case TRANSACTION_mRegister:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          java.lang.String _arg1;
          _arg1 = data.readString();
          java.lang.String _arg2;
          _arg2 = data.readString();
          this.mRegister(_arg0, _arg1, _arg2);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_mQurey:
        {
          data.enforceInterface(descriptor);
          java.lang.String[] _result = this.mQurey();
          reply.writeNoException();
          reply.writeStringArray(_result);
          return true;
        }
        case TRANSACTION_mUpdate:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          java.lang.String _arg1;
          _arg1 = data.readString();
          int _result = this.mUpdate(_arg0, _arg1);
          reply.writeNoException();
          reply.writeInt(_result);
          return true;
        }
        case TRANSACTION_mDeleteUser:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          int _result = this.mDeleteUser(_arg0);
          reply.writeNoException();
          reply.writeInt(_result);
          return true;
        }
        case TRANSACTION_mDeleteAllUser:
        {
          data.enforceInterface(descriptor);
          int _result = this.mDeleteAllUser();
          reply.writeNoException();
          reply.writeInt(_result);
          return true;
        }
        case TRANSACTION_isExistUser:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          boolean _result = this.isExistUser(_arg0);
          reply.writeNoException();
          reply.writeInt(((_result)?(1):(0)));
          return true;
        }
        case TRANSACTION_isNoUser:
        {
          data.enforceInterface(descriptor);
          boolean _result = this.isNoUser();
          reply.writeNoException();
          reply.writeInt(((_result)?(1):(0)));
          return true;
        }
        case TRANSACTION_mLoginVerify:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          java.lang.String _arg1;
          _arg1 = data.readString();
          boolean _result = this.mLoginVerify(_arg0, _arg1);
          reply.writeNoException();
          reply.writeInt(((_result)?(1):(0)));
          return true;
        }
        case TRANSACTION_getListCount:
        {
          data.enforceInterface(descriptor);
          int _result = this.getListCount();
          reply.writeNoException();
          reply.writeInt(_result);
          return true;
        }
        case TRANSACTION_toFirst:
        {
          data.enforceInterface(descriptor);
          this.toFirst();
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_toNext:
        {
          data.enforceInterface(descriptor);
          this.toNext();
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_updateQuery:
        {
          data.enforceInterface(descriptor);
          this.updateQuery();
          reply.writeNoException();
          return true;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }
    private static class Proxy implements com.example.myaccount.IMyUser
    {
      private android.os.IBinder mRemote;
      Proxy(android.os.IBinder remote)
      {
        mRemote = remote;
      }
      @Override public android.os.IBinder asBinder()
      {
        return mRemote;
      }
      public java.lang.String getInterfaceDescriptor()
      {
        return DESCRIPTOR;
      }
      /**
           * Demonstrates some basic types that you can use as parameters
           * and return values in AIDL.
           *///增加接口

      @Override public java.lang.String[] mLogin(java.lang.String theUser, java.lang.String thePass) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String[] _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(theUser);
          _data.writeString(thePass);
          boolean _status = mRemote.transact(Stub.TRANSACTION_mLogin, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().mLogin(theUser, thePass);
          }
          _reply.readException();
          _result = _reply.createStringArray();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public void mRegister(java.lang.String username, java.lang.String account, java.lang.String password) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(username);
          _data.writeString(account);
          _data.writeString(password);
          boolean _status = mRemote.transact(Stub.TRANSACTION_mRegister, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().mRegister(username, account, password);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public java.lang.String[] mQurey() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String[] _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_mQurey, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().mQurey();
          }
          _reply.readException();
          _result = _reply.createStringArray();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public int mUpdate(java.lang.String mId, java.lang.String newPass) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        int _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(mId);
          _data.writeString(newPass);
          boolean _status = mRemote.transact(Stub.TRANSACTION_mUpdate, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().mUpdate(mId, newPass);
          }
          _reply.readException();
          _result = _reply.readInt();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public int mDeleteUser(java.lang.String mId) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        int _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(mId);
          boolean _status = mRemote.transact(Stub.TRANSACTION_mDeleteUser, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().mDeleteUser(mId);
          }
          _reply.readException();
          _result = _reply.readInt();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public int mDeleteAllUser() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        int _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_mDeleteAllUser, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().mDeleteAllUser();
          }
          _reply.readException();
          _result = _reply.readInt();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public boolean isExistUser(java.lang.String name) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        boolean _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(name);
          boolean _status = mRemote.transact(Stub.TRANSACTION_isExistUser, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().isExistUser(name);
          }
          _reply.readException();
          _result = (0!=_reply.readInt());
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public boolean isNoUser() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        boolean _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_isNoUser, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().isNoUser();
          }
          _reply.readException();
          _result = (0!=_reply.readInt());
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public boolean mLoginVerify(java.lang.String theUser, java.lang.String thePass) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        boolean _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(theUser);
          _data.writeString(thePass);
          boolean _status = mRemote.transact(Stub.TRANSACTION_mLoginVerify, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().mLoginVerify(theUser, thePass);
          }
          _reply.readException();
          _result = (0!=_reply.readInt());
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public int getListCount() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        int _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getListCount, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getListCount();
          }
          _reply.readException();
          _result = _reply.readInt();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public void toFirst() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_toFirst, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().toFirst();
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void toNext() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_toNext, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().toNext();
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void updateQuery() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_updateQuery, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().updateQuery();
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      public static com.example.myaccount.IMyUser sDefaultImpl;
    }
    static final int TRANSACTION_mLogin = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_mRegister = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_mQurey = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    static final int TRANSACTION_mUpdate = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
    static final int TRANSACTION_mDeleteUser = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
    static final int TRANSACTION_mDeleteAllUser = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
    static final int TRANSACTION_isExistUser = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
    static final int TRANSACTION_isNoUser = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
    static final int TRANSACTION_mLoginVerify = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
    static final int TRANSACTION_getListCount = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
    static final int TRANSACTION_toFirst = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
    static final int TRANSACTION_toNext = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
    static final int TRANSACTION_updateQuery = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
    public static boolean setDefaultImpl(com.example.myaccount.IMyUser impl) {
      // Only one user of this interface can use this function
      // at a time. This is a heuristic to detect if two different
      // users in the same process use this function.
      if (Stub.Proxy.sDefaultImpl != null) {
        throw new IllegalStateException("setDefaultImpl() called twice");
      }
      if (impl != null) {
        Stub.Proxy.sDefaultImpl = impl;
        return true;
      }
      return false;
    }
    public static com.example.myaccount.IMyUser getDefaultImpl() {
      return Stub.Proxy.sDefaultImpl;
    }
  }
  /**
       * Demonstrates some basic types that you can use as parameters
       * and return values in AIDL.
       *///增加接口

  public java.lang.String[] mLogin(java.lang.String theUser, java.lang.String thePass) throws android.os.RemoteException;
  public void mRegister(java.lang.String username, java.lang.String account, java.lang.String password) throws android.os.RemoteException;
  public java.lang.String[] mQurey() throws android.os.RemoteException;
  public int mUpdate(java.lang.String mId, java.lang.String newPass) throws android.os.RemoteException;
  public int mDeleteUser(java.lang.String mId) throws android.os.RemoteException;
  public int mDeleteAllUser() throws android.os.RemoteException;
  public boolean isExistUser(java.lang.String name) throws android.os.RemoteException;
  public boolean isNoUser() throws android.os.RemoteException;
  public boolean mLoginVerify(java.lang.String theUser, java.lang.String thePass) throws android.os.RemoteException;
  public int getListCount() throws android.os.RemoteException;
  public void toFirst() throws android.os.RemoteException;
  public void toNext() throws android.os.RemoteException;
  public void updateQuery() throws android.os.RemoteException;
}
