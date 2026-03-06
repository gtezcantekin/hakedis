package com.hakedis.model;

public class EndeksTanim {

    private long id;
    private String kod;
    private String ad;
    private int aktif = 1;

    public EndeksTanim() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public int getAktif() {
        return aktif;
    }

    public void setAktif(int aktif) {
        this.aktif = aktif;
    }
}
