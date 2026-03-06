package com.hakedis.model;

import java.math.BigDecimal;

public class HakedisOzet {

    private int id;
    private int isId;
    private int hakedisId;
    private int hakedisNo;

    private BigDecimal onceki;
    private BigDecimal bu;
    private BigDecimal toplam;

    public HakedisOzet() {
    }

    public HakedisOzet(int isId, int hakedisId, int hakedisNo, BigDecimal onceki, BigDecimal bu, BigDecimal toplam) {
        this.isId = isId;
        this.hakedisId = hakedisId;
        this.hakedisNo = hakedisNo;
        this.onceki = onceki;
        this.bu = bu;
        this.toplam = toplam;
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

    public BigDecimal getOnceki() {
        return onceki;
    }

    public void setOnceki(BigDecimal onceki) {
        this.onceki = onceki;
    }

    public BigDecimal getBu() {
        return bu;
    }

    public void setBu(BigDecimal bu) {
        this.bu = bu;
    }

    public BigDecimal getToplam() {
        return toplam;
    }

    public void setToplam(BigDecimal toplam) {
        this.toplam = toplam;
    }
}
