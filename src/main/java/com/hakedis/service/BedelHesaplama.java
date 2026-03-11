package com.hakedis.service;

import com.hakedis.model.Metrajlar;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class BedelHesaplama {

    private BedelHesaplama() {
    }

    private static final int SCALE = 2;

    public static BigDecimal round(BigDecimal v) {
        return v.setScale(SCALE, RoundingMode.HALF_UP);
    }


    public static BigDecimal toplamMiktarByBirimFiyat(List<Metrajlar> metrajlar, int isId, int birimFiyatId) {
        BigDecimal toplam = BigDecimal.ZERO;

        for (Metrajlar m : metrajlar) {
            if (m.getBirimfiyatId() == birimFiyatId && m.getIsId() == isId) {
                if (m.getMiktar() != null) {
                    toplam = toplam.add(m.getMiktar());
                }
            }
        }
        return round(toplam);
    }

    public static BigDecimal toplamMiktarByBirimFiyatByHakedis(List<Metrajlar> metrajlar,
                                                               int isId,
                                                               int birimFiyatId,
                                                               int hakedisNo) {
        BigDecimal toplam = BigDecimal.ZERO;

        for (Metrajlar m : metrajlar) {
            if (m.getBirimfiyatId() == birimFiyatId && m.getHakedisNo()<=hakedisNo && m.getIsId()== isId ) {
                if (m.getMiktar() != null) {
                    toplam = toplam.add(m.getMiktar());
                }
            }
        }
        return round(toplam);
    }



    public static BigDecimal satirTutar(BigDecimal toplamMiktar, BigDecimal birimFiyatBedel) {
        if (toplamMiktar == null) toplamMiktar = BigDecimal.ZERO;
        if (birimFiyatBedel == null) birimFiyatBedel = BigDecimal.ZERO;
        return round(toplamMiktar.multiply(birimFiyatBedel));
    }
}
