package com.hakedis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Isler {

    private int id;
    private int firma_id;
    private String isinadi;
    private LocalDate ihaletarihi;
    private BigDecimal ihaleBedeli;
    private LocalDate sozlesmetarihi;
    private LocalDate yerteslimitarihi;
    private int isinsuresi;
    private LocalDate sozlesmebitimtarihi;

    public Isler() {
    }

    public Isler(int firma_id, String isinadi, LocalDate ihaletarihi,
                 BigDecimal ihaleBedeli, LocalDate sozlesmetarihi,
                 LocalDate yerteslimitarihi, int isinsuresi, LocalDate sozlesmebitimtarihi) {
        this.firma_id = firma_id;
        this.isinadi = isinadi;
        this.ihaletarihi = ihaletarihi;
        this.ihaleBedeli = ihaleBedeli;
        this.sozlesmetarihi = sozlesmetarihi;
        this.yerteslimitarihi = yerteslimitarihi;
        this.isinsuresi = isinsuresi;
        this.sozlesmebitimtarihi = sozlesmebitimtarihi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFirma_id() {
        return firma_id;
    }

    public void setFirma_id(int firma_id) {
        this.firma_id = firma_id;
    }

    public String getIsinadi() {
        return isinadi;
    }

    public void setIsinadi(String isinadi) {
        this.isinadi = isinadi;
    }

    public LocalDate getIhaletarihi() {
        return ihaletarihi;
    }

    public void setIhaletarihi(LocalDate ihaletarihi) {
        this.ihaletarihi = ihaletarihi;
    }

    public LocalDate getSozlesmetarihi() {
        return sozlesmetarihi;
    }

    public void setSozlesmetarihi(LocalDate sozlesmetarihi) {
        this.sozlesmetarihi = sozlesmetarihi;
    }

    public BigDecimal getIhaleBedeli() {
        return ihaleBedeli;
    }

    public void setIhaleBedeli(BigDecimal ihaleBedeli) {
        this.ihaleBedeli = ihaleBedeli;
    }

    public LocalDate getYerteslimitarihi() {
        return yerteslimitarihi;
    }

    public void setYerteslimitarihi(LocalDate yerteslimitarihi) {
        this.yerteslimitarihi = yerteslimitarihi;
    }

    public int getIsinsuresi() {
        return isinsuresi;
    }

    public void setIsinsuresi(int isinsuresi) {
        this.isinsuresi = isinsuresi;
    }

    public LocalDate getSozlesmebitimtarihi() {
        return sozlesmebitimtarihi;
    }

    public void setSozlesmebitimtarihi(LocalDate sozlesmebitimtarihi) {
        this.sozlesmebitimtarihi = sozlesmebitimtarihi;
    }
}

