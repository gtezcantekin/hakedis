package com.hakedis.model;

public class Firmalar {

    private int id;
    private int userId;
    private String firmaAdi;

    public Firmalar() {
    }

    public Firmalar(int userId, String firmaAdi) {
        this.userId = userId;
        this.firmaAdi = firmaAdi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirmaAdi() {
        return firmaAdi;
    }

    public void setFirmaAdi(String firmaAdi) {
        this.firmaAdi = firmaAdi;
    }

}
