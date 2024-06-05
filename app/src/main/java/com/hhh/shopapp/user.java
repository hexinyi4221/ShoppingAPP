package com.hhh.shopapp;

/**
 * 用户类 存储用户账号密码用作登录注册
 */

public class user {
    String username;
    String password;

    public user(){

    }

    public user(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
