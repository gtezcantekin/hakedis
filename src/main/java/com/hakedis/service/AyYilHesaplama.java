package com.hakedis.service;

import java.util.Date;

public class AyYilHesaplama {

    public static int[] parseAyYil(String s) {
        // "02.2025" gibi
        String[] p = s.trim().split("\\.");
        if (p.length != 2) throw new IllegalArgumentException("Format aa.yyyy olmalı");

        int ay = Integer.parseInt(p[0]);
        int yil = Integer.parseInt(p[1]);

        if (ay < 1 || ay > 12) throw new IllegalArgumentException("Ay 1-12 olmalı");
        return new int[]{yil, ay};
    }





}
