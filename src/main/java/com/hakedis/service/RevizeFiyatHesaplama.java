package com.hakedis.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RevizeFiyatHesaplama {

    private RevizeFiyatHesaplama() {
    }

    private static final double KATSAYI120 = 1.2;
    private static final double KATSAYI001 = 0.01;


    private static final int SCALE = 2;

    public static BigDecimal round(BigDecimal v) {
        return v.setScale(SCALE, RoundingMode.HALF_UP);
    }

    public static BigDecimal revizeFiyatHesaplama(BigDecimal ihaleBedeli,
                                                  BigDecimal birimFiyat,
                                                  BigDecimal sozlesmeMiktari,
                                                  BigDecimal toplamMiktar) {

        if (ihaleBedeli == null || birimFiyat == null || sozlesmeMiktari == null || toplamMiktar == null) {
            return birimFiyat;
        }

        BigDecimal revizeBirimFiyat = birimFiyat;


        BigDecimal limit120 = sozlesmeMiktari.multiply(BigDecimal.valueOf(KATSAYI120));
        BigDecimal limit001 = ihaleBedeli.multiply(BigDecimal.valueOf(KATSAYI001));

        boolean sart1 = toplamMiktar.compareTo(limit120) > 0;
        boolean sart2 = (toplamMiktar.subtract(sozlesmeMiktari)).multiply(birimFiyat).compareTo(limit001) > 0;

        if (sart1 && sart2) {

//            R  =   F  x  [ 1 – (A x F) / S ]
//            S  =  Sözleşme bedeli ( TL),
//            F  =  İş kaleminin birim fiyatı (TL / ….),
//            A  =  İş kaleminde meydana gelen artış miktarı (Adet, mt, m2 vb.),
//            R  =  Revize birim fiyat (TL / ….).”

            BigDecimal a = toplamMiktar.subtract(sozlesmeMiktari); // (A = tm - sm)
            BigDecimal af = a.multiply(birimFiyat); // A x F
            BigDecimal oran = af.divide(ihaleBedeli,10,RoundingMode.HALF_UP); // (AxF)/S
            BigDecimal katsayi = BigDecimal.ONE.subtract(oran); // 1- ((AxF)/S)
            revizeBirimFiyat = birimFiyat.multiply(katsayi); // Fx(1- ((AxF)/S))

            revizeBirimFiyat = round(revizeBirimFiyat);
        }

        return revizeBirimFiyat;
    }

    public static BigDecimal revizeFiyat(BigDecimal formul, BigDecimal manuel) {

        if (formul == null && manuel == null) return null;

        if (formul == null) return manuel;
        if (manuel == null) return formul;

        return formul.min(manuel); // Küçüğü döndürür

    }
}
