package com.hakedis.model;

import java.math.BigDecimal;

public class BirimFiyatlar {

    private int id;
    private int isId;
    private String bfiyatno;
    private String ad;
    private BigDecimal tutari;
    private BigDecimal miktari;
    private int metrajturuId;

    public BirimFiyatlar() {
    }

    public BirimFiyatlar(int isId, String bfiyatno, String ad, BigDecimal tutari, BigDecimal miktari, int metrajturuId) {
        this.isId = isId;
        this.bfiyatno = bfiyatno;
        this.ad = ad;
        this.tutari = tutari;
        this.miktari = miktari;
        this.metrajturuId = metrajturuId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsId() {
        return isId;
    }

    public void setIsId(int isId) {
        this.isId = isId;
    }

    public String getBfiyatno() {
        return bfiyatno;
    }

    public void setBfiyatno(String bfiyatno) {
        this.bfiyatno = bfiyatno;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public BigDecimal getTutari() {
        return tutari;
    }

    public void setTutari(BigDecimal tutari) {
        this.tutari = tutari;
    }

    public BigDecimal getMiktari() {
        return miktari;
    }

    public void setMiktari(BigDecimal miktari) {
        this.miktari = miktari;
    }

    public int getMetrajturuId() {
        return metrajturuId;
    }

    public void setMetrajturuId(int metrajturuId) {
        this.metrajturuId = metrajturuId;
    }

    @Override
    public String toString() {
        return "BirimFiyatlar{" +
                "id=" + id +
                ", isId=" + isId +
                ", bfiyatno='" + bfiyatno + '\'' +
                ", ad='" + ad + '\'' +
                ", tutari=" + tutari +
                ", miktari=" + miktari +
                ", metrajturuId=" + metrajturuId +
                '}';
    }
}
