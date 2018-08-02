package com.beyond.passwordwallet;

public class InfoAccount {


    private  String title, account,password;


    public InfoAccount(){};


    public InfoAccount (String title, String account, String password)
    {

        this.title = title;
        this.account = account;
        this.password = password;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getTitle() {
        return title;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }
}
