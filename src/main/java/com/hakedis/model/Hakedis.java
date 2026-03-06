package com.hakedis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Hakedis {

    private int id;
    private int is_id;
    private int hakedisNo;
    private LocalDate tarih;
//    private int sureuzatimi;            acaba buraya alınabilir mi?
//    private LocalDate suzGoreBt;
//    private BigDecimal kesifartis;
//    private BigDecimal kAileToplamBedel;


    public Hakedis() {
    }

    public Hakedis(int is_id, int hakedisNo, LocalDate tarih) {
        this.is_id = is_id;
        this.hakedisNo = hakedisNo;
        this.tarih = tarih;
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

    public int getHakedisNo() {
        return hakedisNo;
    }

    public void setHakedisNo(int hakedisNo) {
        this.hakedisNo = hakedisNo;
    }

    public LocalDate getTarih() {
        return tarih;
    }

    public void setTarih(LocalDate tarih) {
        this.tarih = tarih;
    }


    @Override
    public String toString() {
        return "Hakedis{" +
                "id=" + id +
                ", is_id=" + is_id +
                ", hakedisNo=" + hakedisNo +
                ", tarih=" + tarih +
                '}';
    }
}
