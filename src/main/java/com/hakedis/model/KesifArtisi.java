package com.hakedis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class KesifArtisi {

    private int id;
    private int is_id;
    private int hakedis_id;
    private int hakedis_no;
    private String aciklama;
    private BigDecimal kesifArtisi;
    private BigDecimal toplamBedel;

    public KesifArtisi() {
    }

    public KesifArtisi(int is_id, int hakedis_id, int hakedis_no, String aciklama,
                       BigDecimal kesifArtisi, BigDecimal toplamBedel) {
        this.is_id = is_id;
        this.hakedis_id = hakedis_id;
        this.hakedis_no = hakedis_no;
        this.aciklama = aciklama;
        this.kesifArtisi = kesifArtisi;
        this.toplamBedel = toplamBedel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIs_id() {
        return is_id;
    }

    public void setIs_id(int is_id) {
        this.is_id = is_id;
    }

    public int getHakedis_id() {
        return hakedis_id;
    }

    public void setHakedis_id(int hakedis_id) {
        this.hakedis_id = hakedis_id;
    }

    public int getHakedis_no() {
        return hakedis_no;
    }

    public void setHakedis_no(int hakedis_no) {
        this.hakedis_no = hakedis_no;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public BigDecimal getKesifArtisi() {
        return kesifArtisi;
    }

    public void setKesifArtisi(BigDecimal kesifArtisi) {
        this.kesifArtisi = kesifArtisi;
    }

    public BigDecimal getToplamBedel() {
        return toplamBedel;
    }

    public void setToplamBedel(BigDecimal toplamBedel) {
        this.toplamBedel = toplamBedel;
    }
}
