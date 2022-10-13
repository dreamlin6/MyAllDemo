package com.example.myaccount.bean;

public class UserListBean {
    private String userName;
    private String account;
    private String passWord;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
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
}
