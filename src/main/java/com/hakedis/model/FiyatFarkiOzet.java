package com.hakedis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FiyatFarkiOzet {

    private long id;

    private int isId;
    private int hakedisId;
    private long isprogramiId;

    private Integer hakedisNo;              // nullable
    private LocalDate isprogramiDonem;      // DATE

    private BigDecimal isprogramiTutar;     // not null
    private BigDecimal hakedisTutar;        // nullable

    private BigDecimal bKatSayisi;          // nullable
    private BigDecimal pnEksiBir;           // nullable
    private BigDecimal fiyatFarki;          // nullable

    public FiyatFarkiOzet() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getIsprogramiId() {
        return isprogramiId;
    }

    public void setIsprogramiId(long isprogramiId) {
        this.isprogramiId = isprogramiId;
    }

    public Integer getHakedisNo() {
        return hakedisNo;
    }

    public void setHakedisNo(Integer hakedisNo) {
        this.hakedisNo = hakedisNo;
    }

    public LocalDate getIsprogramiDonem() {
        return isprogramiDonem;
    }

    public void setIsprogramiDonem(LocalDate isprogramiDonem) {
        this.isprogramiDonem = isprogramiDonem;
    }

    public BigDecimal getIsprogramiTutar() {
        return isprogramiTutar;
    }

    public void setIsprogramiTutar(BigDecimal isprogramiTutar) {
        this.isprogramiTutar = isprogramiTutar;
    }

    public BigDecimal getHakedisTutar() {
        return hakedisTutar;
    }

    public void setHakedisTutar(BigDecimal hakedisTutar) {
        this.hakedisTutar = hakedisTutar;
    }

    public BigDecimal getbKatSayisi() {
        return bKatSayisi;
    }

    public void setbKatSayisi(BigDecimal bKatSayisi) {
        this.bKatSayisi = bKatSayisi;
    }

    public BigDecimal getPnEksiBir() {
        return pnEksiBir;
    }

    public void setPnEksiBir(BigDecimal pnEksiBir) {
        this.pnEksiBir = pnEksiBir;
    }

    public BigDecimal getFiyatFarki() {
        return fiyatFarki;
    }

    public void setFiyatFarki(BigDecimal fiyatFarki) {
        this.fiyatFarki = fiyatFarki;
    }

    @Override
    public String toString() {
        return "FiyatFarkiOzet{" +
                "id=" + id +
                ", isId=" + isId +
                ", hakedisId=" + hakedisId +
                ", isprogramiId=" + isprogramiId +
                ", hakedisNo=" + hakedisNo +
                ", isprogramiDonem=" + isprogramiDonem +
                ", isprogramiTutar=" + isprogramiTutar +
                ", hakedisTutar=" + hakedisTutar +
                ", bKatSayisi=" + bKatSayisi +
                ", pnEksiBir=" + pnEksiBir +
                ", fiyatFarki=" + fiyatFarki +
                '}';
    }
}
