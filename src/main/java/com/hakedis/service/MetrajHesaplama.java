package com.hakedis.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MetrajHesaplama {

    private static final int SCALE = 2;

    private static BigDecimal nz(BigDecimal v){
        return v == null ? BigDecimal.ZERO : v;
    }

    private static BigDecimal nzInt(Integer v){
        return v == null ? BigDecimal.ZERO : BigDecimal.valueOf(v);
    }

    private static BigDecimal oneIfEmpty(BigDecimal v) {
        return (v == null || v.compareTo(BigDecimal.ZERO) <= 0)
                ? BigDecimal.ONE
                : v;
    }


    private static BigDecimal round(BigDecimal v){
        return v.setScale(SCALE, RoundingMode.HALF_UP);
    }

    public static BigDecimal miktarHesapla(MetrajTuru tur, Integer adet, BigDecimal en,
                                           BigDecimal boy, BigDecimal yukseklik, BigDecimal agirlik){

        BigDecimal a = nzInt(adet);
        BigDecimal e = nz(en);
        BigDecimal b = nz(boy);
        BigDecimal y = nz(yukseklik);
        BigDecimal ag = nz(agirlik);

        BigDecimal sonuc;

        switch (tur) {

            case ADET -> sonuc = a;

            case UZUNLUK -> {
                BigDecimal bb = oneIfEmpty(b);
                sonuc = a.multiply(bb);
            }

            case ALAN -> {
                BigDecimal ee = oneIfEmpty(e);
                BigDecimal bb = oneIfEmpty(b);
                BigDecimal yy = oneIfEmpty(y);

                sonuc = a.multiply(ee).multiply(bb).multiply(yy);
            }

            case HACIM -> {
                BigDecimal ee = oneIfEmpty(e);
                BigDecimal bb = oneIfEmpty(b);
                BigDecimal yy = oneIfEmpty(y);

                sonuc = a.multiply(ee).multiply(bb).multiply(yy);
            }

            case AGIRLIK -> {
                BigDecimal agg = oneIfEmpty(ag);
                sonuc = a.multiply(agg);
            }

            default -> sonuc = BigDecimal.ZERO;
        }

        return round(sonuc);
    }


}


