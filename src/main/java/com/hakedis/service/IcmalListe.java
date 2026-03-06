package com.hakedis.service;

import com.hakedis.dao.*;
import com.hakedis.model.Icmal;
import com.hakedis.model.Metrajlar;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;

public class IcmalListe {

    private IcmalListe() {
    }

    private static final int SCALE = 2;

    public static BigDecimal round(BigDecimal v) {
        return v.setScale(SCALE, RoundingMode.HALF_UP);
    }

    public static BigDecimal genelToplamTutar(List<Icmal> icmaller) {
        BigDecimal toplam = BigDecimal.ZERO;

        for (Icmal i : icmaller) {
            BigDecimal v = i.getTopHakTutar();   // <-- GENEL TOPLAM alanı
            if (v != null) toplam = toplam.add(v);
        }
        return round(toplam);
    }


    public static BigDecimal oncekiToplamTutar(List<Icmal> icmaller) {
        BigDecimal toplam = BigDecimal.ZERO;

        for (Icmal i : icmaller) {
            BigDecimal v = i.getOncekiHakTutar();
            if (v != null) toplam = toplam.add(v);
        }
        return round(toplam);
    }


    public static BigDecimal buHakedisTutar(List<Icmal> icmaller) {
        BigDecimal toplam = BigDecimal.ZERO;

        for (Icmal i : icmaller) {
            BigDecimal v = i.getBuHakTutar();
            if (v != null) toplam = toplam.add(v);
        }
        return round(toplam);
    }

}
