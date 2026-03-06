package com.hakedis.model;

import java.time.LocalDate;

public class SureUzatimi {

    private int id;
    private int is_id;
    private int hakedis_id;
    private int hakedis_no;
    private String aciklama;
    private int gunuzatma;
    private LocalDate uzamatarihi;

    public SureUzatimi() {
    }

    public SureUzatimi(int is_id, int hakedis_id, int hakedis_no, String aciklama,
                       int gunuzatma, LocalDate uzamatarihi) {
        this.is_id = is_id;
        this.hakedis_id = hakedis_id;
        this.hakedis_no = hakedis_no;
        this.aciklama = aciklama;
        this.gunuzatma = gunuzatma;
        this.uzamatarihi = uzamatarihi;
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

    public int getGunuzatma() {
        return gunuzatma;
    }

    public void setGunuzatma(int gunuzatma) {
        this.gunuzatma = gunuzatma;
    }

    public LocalDate getUzamatarihi() {
        return uzamatarihi;
    }

    public void setUzamatarihi(LocalDate uzamatarihi) {
        this.uzamatarihi = uzamatarihi;
    }
}
