package com.hakedis.model;

import java.math.BigDecimal;

public class EndeksDeger {

    private Long id;
    private Long endeksTanimId;
    private int yil;
    private int ay;
    private BigDecimal deger;

    public EndeksDeger() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEndeksTanimId() {
        return endeksTanimId;
    }

    public void setEndeksTanimId(Long endeksTanimId) {
        this.endeksTanimId = endeksTanimId;
    }

    public int getYil() {
        return yil;
    }

    public void setYil(int yil) {
        this.yil = yil;
    }

    public int getAy() {
        return ay;
    }

    public void setAy(int ay) {
        this.ay = ay;
    }

    public BigDecimal getDeger() {
        return deger;
    }

    public void setDeger(BigDecimal deger) {
        this.deger = deger;
    }
}
