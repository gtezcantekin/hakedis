package com.hakedis.service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class FormatUtil {

    private static final Locale TR = new Locale("tr","TR");

    public static String para (BigDecimal v){
        if (v==null) v = BigDecimal.ZERO;

        NumberFormat nf = NumberFormat.getNumberInstance(TR);
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);

        return nf.format(v) + "TL";
    }

    public static String sayi (BigDecimal v){
        if (v==null) v=BigDecimal.ZERO;

        NumberFormat nf = NumberFormat.getNumberInstance(TR);
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);

        return nf.format(v);

    }

    public static String kisa(String s, int max) {
        if (s == null) return "";
        s = s.trim();
        if (s.length() <= max) return s;
        return s.substring(0, Math.max(0, max - 3)) + "...";
    }

}
