package com.hakedis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IsProgrami {

    private long id;
    private int isId;
    private LocalDate donem;
    private BigDecimal aylikTutar;
    private BigDecimal kumulatif;

    public IsProgrami() {
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

    public BigDecimal getAylikTutar() {
        return aylikTutar;
    }

    public void setAylikTutar(BigDecimal aylikTutar) {
        this.aylikTutar = aylikTutar;
    }

    public LocalDate getDonem() {
        return donem;
    }

    public void setDonem(LocalDate donem) {
        this.donem = donem;
    }

    public BigDecimal getKumulatif() {
        return kumulatif;
    }

    public void setKumulatif(BigDecimal kumulatif) {
        this.kumulatif = kumulatif;
    }

    @Override
    public String toString() {
        return "IsProgrami{" +
                "id=" + id +
                ", isId=" + isId +
                ", donem=" + donem +
                ", aylikTutar=" + aylikTutar +
                ", kumulatif=" + kumulatif +
                '}';
    }
}
