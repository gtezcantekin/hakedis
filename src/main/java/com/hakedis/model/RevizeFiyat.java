package com.hakedis.model;

import java.math.BigDecimal;

public class RevizeFiyat {

    private int id;
    private int isId;
    private int hakedisId;
    private int hakedisNo;
    private int bfId;
    private String bfNoAd;
    private String bfNoAdRev;
    private BigDecimal revizeBfFormul;
    private BigDecimal revizeBfManuel;

    public RevizeFiyat() {
    }

    public RevizeFiyat(int isId, int hakedisId, int hakedisNo, int bfId, String bfNoAd,
                       String bfNoAdRev, BigDecimal revizeBfFormul, BigDecimal revizeBfManuel) {
        this.isId = isId;
        this.hakedisId = hakedisId;
        this.hakedisNo = hakedisNo;
        this.bfId = bfId;
        this.bfNoAd = bfNoAd;
        this.bfNoAdRev = bfNoAdRev;
        this.revizeBfFormul = revizeBfFormul;
        this.revizeBfManuel = revizeBfManuel;
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

    public int getBfId() {
        return bfId;
    }

    public void setBfId(int bfId) {
        this.bfId = bfId;
    }

    public String getBfNoAd() {
        return bfNoAd;
    }

    public void setBfNoAd(String bfNoAd) {
        this.bfNoAd = bfNoAd;
    }

    public String getBfNoAdRev() {
        return bfNoAdRev;
    }

    public void setBfNoAdRev(String bfNoAdRev) {
        this.bfNoAdRev = bfNoAdRev;
    }

    public BigDecimal getRevizeBfFormul() {
        return revizeBfFormul;
    }

    public void setRevizeBfFormul(BigDecimal revizeBfFormul) {
        this.revizeBfFormul = revizeBfFormul;
    }

    public BigDecimal getRevizeBfManuel() {
        return revizeBfManuel;
    }

    public void setRevizeBfManuel(BigDecimal revizeBfManuel) {
        this.revizeBfManuel = revizeBfManuel;
    }
}