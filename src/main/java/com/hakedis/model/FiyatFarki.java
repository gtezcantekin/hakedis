package com.hakedis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FiyatFarki {

    private long id;
    private int isId;
    private int hakedisId;
    private long isprogramId;
    private int hakedisNo;
    private LocalDate isProgramiDonem;
    private BigDecimal isProgramiTutar;
    private BigDecimal hakedisTutar;
    private BigDecimal bKatSayisi;
    private BigDecimal pnEksiBir;
    private BigDecimal fiyatFarki;

    public FiyatFarki() {
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

    public long getIsprogramId() {
        return isprogramId;
    }

    public void setIsprogramId(long isprogramId) {
        this.isprogramId = isprogramId;
    }

    public int getHakedisNo() {
        return hakedisNo;
    }

    public void setHakedisNo(int hakedisNo) {
        this.hakedisNo = hakedisNo;
    }

    public LocalDate getIsProgramiDonem() {
        return isProgramiDonem;
    }

    public void setIsProgramiDonem(LocalDate isProgramiDonem) {
        this.isProgramiDonem = isProgramiDonem;
    }

    public BigDecimal getIsProgramiTutar() {
        return isProgramiTutar;
    }

    public void setIsProgramiTutar(BigDecimal isProgramiTutar) {
        this.isProgramiTutar = isProgramiTutar;
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
        return "FiyatFarki{" +
                "id=" + id +
                ", isId=" + isId +
                ", hakedisId=" + hakedisId +
                ", isprogramId=" + isprogramId +
                ", hakedisNo=" + hakedisNo +
                ", isProgramiDonem=" + isProgramiDonem +
                ", isProgramiTutar=" + isProgramiTutar +
                ", hakedisTutar=" + hakedisTutar +
                ", bKatSayisi=" + bKatSayisi +
                ", pnEksiBir=" + pnEksiBir +
                ", fiyatFarki=" + fiyatFarki +
                '}';
    }
}
