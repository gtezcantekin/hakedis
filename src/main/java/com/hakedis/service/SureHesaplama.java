package com.hakedis.service;

import com.hakedis.model.Metrajlar;
import com.hakedis.model.SureUzatimi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SureHesaplama {

    public SureHesaplama() {
    }

    public static LocalDate sozGoreBitisTarihiHesapla(LocalDate yerTeslimTarihi, int sureGun) {
        if (yerTeslimTarihi == null) return null;
        if (sureGun <= 0) return yerTeslimTarihi;
        return yerTeslimTarihi.plusDays(sureGun - 1);
    }

    public static LocalDate suzaGoreBitisTarihiHesapla(LocalDate sonTarih, int sureUGun) {
        if (sureUGun <= 0) return null;
        return sonTarih.plusDays(sureUGun);
    }

    public static int toplamGunHesapla(List<SureUzatimi> liste){
        int toplam = 0 ;
        for (SureUzatimi s : liste) {
            toplam+= (s!=null ? s.getGunuzatma() : 0);
        }
        return toplam;
    }

}