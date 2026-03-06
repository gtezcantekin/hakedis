package com.hakedis.model;

import java.math.BigDecimal;

public class Icmal {

    private int id;
    private int isId;
    private int hakId;
    private int hakNo;
    private int bfId;
    private int satirTip;   // 0 normal, 1 revize
    private String bfNo;
    private String bfRevNo;
    private String bfTanim;
    private BigDecimal bfTutar;
    private int metrajTuruId;
    private BigDecimal topHakMiktar;
    private BigDecimal oncekiHakMiktar;
    private BigDecimal buHakMiktar;
    private BigDecimal topHakTutar;
    private BigDecimal oncekiHakTutar;
    private BigDecimal buHakTutar;


    public Icmal() {
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

    public int getHakId() {
        return hakId;
    }

    public void setHakId(int hakId) {
        this.hakId = hakId;
    }

    public int getHakNo() {
        return hakNo;
    }

    public void setHakNo(int hakNo) {
        this.hakNo = hakNo;
    }

    public int getBfId() {
        return bfId;
    }

    public void setBfId(int bfId) {
        this.bfId = bfId;
    }

    public int getSatirTip() {
        return satirTip;
    }

    public void setSatirTip(int satirTip) {
        this.satirTip = satirTip;
    }

    public String getBfNo() {
        return bfNo;
    }

    public void setBfNo(String bfNo) {
        this.bfNo = bfNo;
    }

    public String getBfRevNo() {
        return bfRevNo;
    }

    public void setBfRevNo(String bfRevNo) {
        this.bfRevNo = bfRevNo;
    }

    public String getBfTanim() {
        return bfTanim;
    }

    public void setBfTanim(String bfTanim) {
        this.bfTanim = bfTanim;
    }

    public BigDecimal getBfTutar() {
        return bfTutar;
    }

    public void setBfTutar(BigDecimal bfTutar) {
        this.bfTutar = bfTutar;
    }

    public int getMetrajTuruId() {
        return metrajTuruId;
    }

    public void setMetrajTuruId(int metrajTuruId) {
        this.metrajTuruId = metrajTuruId;
    }

    public BigDecimal getTopHakMiktar() {
        return topHakMiktar;
    }

    public void setTopHakMiktar(BigDecimal topHakMiktar) {
        this.topHakMiktar = topHakMiktar;
    }

    public BigDecimal getOncekiHakMiktar() {
        return oncekiHakMiktar;
    }

    public void setOncekiHakMiktar(BigDecimal oncekiHakMiktar) {
        this.oncekiHakMiktar = oncekiHakMiktar;
    }

    public BigDecimal getBuHakMiktar() {
        return buHakMiktar;
    }

    public void setBuHakMiktar(BigDecimal buHakMiktar) {
        this.buHakMiktar = buHakMiktar;
    }

    public BigDecimal getTopHakTutar() {
        return topHakTutar;
    }

    public void setTopHakTutar(BigDecimal topHakTutar) {
        this.topHakTutar = topHakTutar;
    }

    public BigDecimal getOncekiHakTutar() {
        return oncekiHakTutar;
    }

    public void setOncekiHakTutar(BigDecimal oncekiHakTutar) {
        this.oncekiHakTutar = oncekiHakTutar;
    }

    public BigDecimal getBuHakTutar() {
        return buHakTutar;
    }

    public void setBuHakTutar(BigDecimal buHakTutar) {
        this.buHakTutar = buHakTutar;
    }

    @Override
    public String toString() {
        return "Icmal{" +
                "id=" + id +
                ", isId=" + isId +
                ", hakId=" + hakId +
                ", hakNo=" + hakNo +
                ", bfId=" + bfId +
                ", satirTip=" + satirTip +
                ", bfNo='" + bfNo + '\'' +
                ", bfRevNo='" + bfRevNo + '\'' +
                ", bfTanim='" + bfTanim + '\'' +
                ", bfTutar=" + bfTutar +
                ", metrajTuruId=" + metrajTuruId +
                ", topHakMiktar=" + topHakMiktar +
                ", oncekiHakMiktar=" + oncekiHakMiktar +
                ", buHakMiktar=" + buHakMiktar +
                ", topHakTutar=" + topHakTutar +
                ", oncekiHakTutar=" + oncekiHakTutar +
                ", buHakTutar=" + buHakTutar +
                '}';
    }
}
