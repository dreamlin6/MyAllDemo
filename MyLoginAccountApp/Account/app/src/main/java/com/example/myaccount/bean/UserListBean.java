package com.example.myaccount.bean;

public class UserListBean {
    private String mId, userName, account, passWord;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getAccount() {
        return account;
    }

    public String getmId() {
        return mId;
    }
}
