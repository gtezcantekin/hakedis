package com.hakedis.model;

import java.sql.Time;

public class Users {

    private int id;
    private String userName;
    private String passWord;

    public Users() {
    }

    public Users(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userNAme) {
        this.userName = userNAme;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
