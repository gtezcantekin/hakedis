package com.hakedis.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AgirlikKatsayisi {

    private int id;                 // INT
    private int isId;               // INT
    private long endeksTanimId;      // BIGINT
    private String endeksKodu;       // VARCHAR(50)  (ISCILIK / DEMIR / AKARYAKIT...)
    private BigDecimal agirlik;      // DECIMAL(10,6)
    private LocalDate temeldonem;
    private BigDecimal temelendeks;

    public AgirlikKatsayisi() {
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

    public long getEndeksTanimId() {
        return endeksTanimId;
    }

    public void setEndeksTanimId(long endeksTanimId) {
        this.endeksTanimId = endeksTanimId;
    }

    public String getEndeksKodu() {
        return endeksKodu;
    }

    public void setEndeksKodu(String endeksKodu) {
        this.endeksKodu = endeksKodu;
    }

    public BigDecimal getAgirlik() {
        return agirlik;
    }

    public void setAgirlik(BigDecimal agirlik) {
        this.agirlik = agirlik;
    }

    public LocalDate getTemeldonem() {
        return temeldonem;
    }

    public void setTemeldonem(LocalDate temeldonem) {
        this.temeldonem = temeldonem;
    }

    public BigDecimal getTemelendeks() {
        return temelendeks;
    }

    public void setTemelendeks(BigDecimal temelendeks) {
        this.temelendeks = temelendeks;
    }


}
