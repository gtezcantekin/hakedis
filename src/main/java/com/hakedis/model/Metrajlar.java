package com.hakedis.model;

import java.math.BigDecimal;

public class Metrajlar {

    private int id;
    private int isId;
    private int hakedisId;
    private int hakedisNo;
    private int birimfiyatId;
    private String aciklama;
    private int adet;
    private BigDecimal en;
    private BigDecimal boy;
    private BigDecimal yukseklik;
    private BigDecimal agirlik;
    private BigDecimal miktar;
    private int metrajturuId;

    public Metrajlar() {
    }

    public Metrajlar(int isId, int hakedisId, int hakedisNo, int birimfiyatId, String aciklama,
                     int adet, BigDecimal en, BigDecimal boy, BigDecimal yukseklik, BigDecimal agirlik,
                     BigDecimal miktar, int metrajturuId) {
        this.isId = isId;
        this.hakedisId = hakedisId;
        this.hakedisNo = hakedisNo;
        this.birimfiyatId = birimfiyatId;
        this.aciklama = aciklama;
        this.adet = adet;
        this.en = en;
        this.boy = boy;
        this.yukseklik = yukseklik;
        this.agirlik = agirlik;
        this.miktar = miktar;
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

    public int getHakedisId() {
        return hakedisId;
    }

    public void setHakedisId(int hakedisId) {
        this.hakedisId = hakedisId;
    }

    public int getHakedisNo() {
        return hakedisNo;
    }

    public void setHakedisNo(int hakedisNo) {
        this.hakedisNo = hakedisNo;
    }

    public int getBirimfiyatId() {
        return birimfiyatId;
    }

    public void setBirimfiyatId(int birimfiyatId) {
        this.birimfiyatId = birimfiyatId;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }

    public BigDecimal getEn() {
        return en;
    }

    public void setEn(BigDecimal en) {
        this.en = en;
    }

    public BigDecimal getBoy() {
        return boy;
    }

    public void setBoy(BigDecimal boy) {
        this.boy = boy;
    }

    public BigDecimal getYukseklik() {
        return yukseklik;
    }

    public void setYukseklik(BigDecimal yukseklik) {
        this.yukseklik = yukseklik;
    }

    public BigDecimal getAgirlik() {
        return agirlik;
    }

    public void setAgirlik(BigDecimal agirlik) {
        this.agirlik = agirlik;
    }

    public BigDecimal getMiktar() {
        return miktar;
    }

    public void setMiktar(BigDecimal miktar) {
        this.miktar = miktar;
    }

    public int getMetrajturuId() {
        return metrajturuId;
    }

    public void setMetrajturuId(int metrajturuId) {
        this.metrajturuId = metrajturuId;
    }
}
