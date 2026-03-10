package com.hakedis.app;

import com.hakedis.dao.*;
import com.hakedis.model.*;
import com.hakedis.service.*;

import javax.xml.stream.events.StartDocument;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    static void main(String[] args) {


        //******HAKEDİŞ PROGRAMI*******//

        Scanner scanner = new Scanner(System.in);

        UserDao userDao = new UserDao();
        int userId = 0;
        FirmaDao fDao = new FirmaDao();
        IslerDao iDao = new IslerDao();
        BirimFiyatlarDao bfDao = new BirimFiyatlarDao();
        HakedisDao hDao = new HakedisDao();
        SureUzatimiDao suzaDao = new SureUzatimiDao();
        KesifArtisiDao kaDao = new KesifArtisiDao();
        MetrajlarDao mDao = new MetrajlarDao();
        RevizeFiyatDao revDao = new RevizeFiyatDao();
        IcmalDao icmalDao = new IcmalDao();
        HakedisOzetDao hoDao = new HakedisOzetDao();
        IsProgramiDao ipDao = new IsProgramiDao();
        AgirlikKatsayisiDao akDao = new AgirlikKatsayisiDao();
        EndeksTanimDao etDao = new EndeksTanimDao();
        EndeksDegerDao edDao = new EndeksDegerDao();
        AkaryakitDegerDao akarDao = new AkaryakitDegerDao();
        FiyatFarkiDao ffDao = new FiyatFarkiDao();
        FiyatFarkiOzetDao ffoDao = new FiyatFarkiOzetDao();


        while (true) {
            System.out.println("""
                    ---HAKEDİŞ MENU---
                    1) GİRİŞ YAP
                    2) FİRMA GİRİŞİ YAP
                    3) İŞİN BİLGİLERİNİ GİR
                    4) BİRİM FİYATLARI GİRİNİZ
                    5) İŞ PROGRAMINI GİRİNİZ
                    6) AĞIRLIK KATSAYILARINI GİRİNİZ
                    7) HAKEDİŞ OLUŞTUR
                    8) METRAJ EKLE
                    9) REVİZE FİYAT KONTROL
                    10) SÜRE UZATIMI
                    11) KEŞİF ARTIŞI
                    12) İCMAL
                    13) TOPLAMLAR
                    14) FİYAT FARKI
                    15) HAKEDİŞ RAPORU
                    
                    0) ÇIKIŞ
                    """);

            System.out.println("Seçim : ");
            String secim = scanner.nextLine().trim();

            switch (secim) {
                case "1" -> girisYap(scanner, userDao);
                case "2" -> firmaKayit(scanner, userId, fDao);
                case "3" -> isKayit(scanner, iDao);
                case "4" -> birimFiyatEkle(scanner, bfDao);
                case "5" -> isProgramiEkle(scanner, iDao, ipDao);
                case "6" -> agirlikKatsayisiEkle(scanner, iDao, etDao, akDao, edDao, akarDao);
                case "7" -> hakedisOlustur(scanner, hDao);
                case "8" -> metrajEkle(scanner, hDao, mDao);
                case "9" -> revizeFiyatKontrol(scanner, bfDao, revDao, mDao, iDao, hDao);
                case "10" -> sureUzatimiEkle(scanner, suzaDao, iDao, hDao);
                case "11" -> kesifArtisiEkle(scanner, kaDao, iDao, hDao);
                case "12" -> yapilanIslerListesi(scanner, bfDao, mDao, hDao, iDao, revDao, icmalDao);
                case "13" -> icmalToplamlar(scanner, hDao, iDao, icmalDao, hoDao);
                case "14" -> fiyatFarki(scanner, hDao, iDao, icmalDao, hoDao, ffDao, ipDao, akDao, ffoDao);
                case "15" -> hakedisRapor(scanner, bfDao, mDao, hDao, iDao, suzaDao, kaDao, revDao, icmalDao);
                case "0" -> {
                    System.out.println("Çıkış yapıldı");
                    return;
                }
                default -> System.out.println("Geçersiz seçim!");
            }
        }
    }

    public static void girisYap(Scanner scanner, UserDao user) {

        System.out.println("İsim giriniz : ");
        String username = scanner.nextLine().trim();

        System.out.println("Şifre giriniz : ");
        String password = scanner.nextLine().trim();

        Users kullanici1 = new Users();
        kullanici1.setUserName(username);
        kullanici1.setPassWord(password);

        user.insertUsers(kullanici1);
        System.out.println("Kullanıcı girişi yapıldı.");

    }

    public static void firmaKayit(Scanner scanner, int userId, FirmaDao fDao) {
        System.out.println("Kullanıcı id giriniz : ");
        int kullaniciId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Firma ünvanını giriniz : ");
        String firmaAdi = scanner.nextLine().trim();

        Firmalar firma1 = new Firmalar();
        firma1.setUserId(kullaniciId);
        firma1.setFirmaAdi(firmaAdi);

        fDao.insertFirma(firma1);
    }

    public static void isKayit(Scanner scanner, IslerDao iDao) {

        System.out.println("Firma id giriniz : ");
        int fId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("İşin adını giriniz : ");
        String iIsim = scanner.nextLine().trim();

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        System.out.println("İhale tarihini giriniz (Boş girilebilir) : ");
        String tarih0 = scanner.nextLine().trim();
        LocalDate iTarih = tarih0.isBlank() ? null : LocalDate.parse(tarih0, fmt);

        System.out.println("İhale bedelini giriniz (Boş girilebilir) : ");
        String bedel = scanner.nextLine().trim();
        BigDecimal iBedeli = bedel.isBlank() ? null : new BigDecimal(bedel.replace(",", "."));

        System.out.println("Sözleşme tarihini giriniz : ");
        String tarih1 = scanner.nextLine().trim();
        LocalDate sTarih = tarih1.isBlank() ? null : LocalDate.parse(tarih1, fmt);

        System.out.println("Yerteslimi tarihini giriniz : ");
        String tarih2 = scanner.nextLine().trim();
        LocalDate ytTarih = tarih2.isBlank() ? null : LocalDate.parse(tarih2, fmt);

        System.out.println("İşin süresi (Boş(0) girilebilir) : ");
        String iSuresiStr = scanner.nextLine().trim();
        int iSuresi = iSuresiStr.isBlank() ? 0 : Integer.parseInt(iSuresiStr);
        LocalDate sgbTarih = SureHesaplama.sozGoreBitisTarihiHesapla(ytTarih, iSuresi);


        Isler i = new Isler();
        i.setFirma_id(fId);
        i.setIsinadi(iIsim);
        i.setIhaletarihi(iTarih);
        i.setIhaleBedeli(iBedeli);
        i.setSozlesmetarihi(sTarih);
        i.setYerteslimitarihi(ytTarih);
        i.setIsinsuresi(iSuresi);
        i.setSozlesmebitimtarihi(sgbTarih);

        IslerDao ikDao = new IslerDao();
        ikDao.insertIsler(i);

    }

    public static void birimFiyatEkle(Scanner scanner, BirimFiyatlarDao bfDao) {
        System.out.println("İş id giriniz : ");
        int isId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Birim Fiyat No giriniz : ");
        String bfNo = scanner.nextLine();
        System.out.println("Birim Fiyat adını giriniz : ");
        String bfAd = scanner.nextLine();
        System.out.println("Birim Fiyat bedelini giriniz : ");
        BigDecimal bedel = new BigDecimal(scanner.nextLine().trim().replace(",", "."));
        System.out.println("Birim fiyat miktarını giriniz (Boş bırakılabilir) : ");
        String miktarStr = scanner.nextLine().trim();
        BigDecimal miktar = miktarStr.isBlank() ? null : new BigDecimal(miktarStr.replace(",", "."));
        System.out.println("Metraj türünü/birimini giriniz : ");
        int mTuru = scanner.nextInt();

        BirimFiyatlar bfFiyat = new BirimFiyatlar();

        bfFiyat.setIsId(isId);
        bfFiyat.setBfiyatno(bfNo);
        bfFiyat.setAd(bfAd);
        bfFiyat.setTutari(bedel);
        bfFiyat.setMiktari(miktar);
        bfFiyat.setMetrajturuId(mTuru);

        bfDao.insertBFiyatlar(bfFiyat);

    }

    public static void isProgramiEkle(Scanner scanner, IslerDao iDao, IsProgramiDao ipDao) {

        System.out.println("İş id giriniz : ");
        int isId = Integer.parseInt(scanner.nextLine().trim());
        Isler is = iDao.findById(isId);
        if (is == null) {
            System.out.println("İş bulunamadı.");
            return;
        }

        System.out.println("İş programı için bilgileri giriniz. ");

        int i = 1;
        BigDecimal kumulatif = BigDecimal.ZERO;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM.yyyy");

        while (true) {

            System.out.println("İş programının " + i + ". ayını giriniz (aa.yyyy) (bitir=çıkış):");
            String tarih = scanner.nextLine().trim();

            if (tarih.equalsIgnoreCase("bitir")) {
                break;
            }

            YearMonth ym;
            try {
                ym = YearMonth.parse(tarih, fmt);
            } catch (Exception e) {
                System.out.println("Tarih formatı hatalı. Örn: 02.2025");
                continue;
            }

            LocalDate iTarih = ym.atDay(1);

            System.out.println(i + ". aylık tutarı giriniz : ");
            String bedel = scanner.nextLine().trim();

            BigDecimal iBedeli;
            try {
                iBedeli = bedel.isBlank() ? null : new BigDecimal(bedel.replace(",", "."));
            } catch (Exception e) {
                System.out.println("Tutar formatı hatalı. Örn: 12345,67");
                continue;
            }

            kumulatif = iBedeli.add(kumulatif);

            IsProgrami isProgrami = new IsProgrami();
            isProgrami.setIsId(isId);
            isProgrami.setDonem(iTarih);
            isProgrami.setAylikTutar(iBedeli);
            isProgrami.setKumulatif(kumulatif);

            ipDao.saveOrUpdate(isProgrami);

            i++; // ✅ sıradaki ay
        }
    }

    public static void agirlikKatsayisiEkle(Scanner scanner,
                                            IslerDao iDao,
                                            EndeksTanimDao etDao,
                                            AgirlikKatsayisiDao akDao,
                                            EndeksDegerDao edDao,
                                            AkaryakitDegerDao akarDao) {

        System.out.println("İş id giriniz : ");
        int isId;
        try {
            isId = Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Geçersiz iş id!");
            return;
        }

        Isler is = iDao.findById(isId);
        if (is == null) {
            System.out.println("İş bulunamadı.");
            return;
        }

        // ihale tarihi boş olamaz (temel dönem buradan)
        LocalDate ihaleTarihi = is.getIhaletarihi();
        if (ihaleTarihi == null) {
            System.out.println("Bu işin ihale tarihi boş. Temel endeks için ihale tarihi gerekli!");
            return;
        }

        int temelYil = ihaleTarihi.getYear();
        int temelAy = ihaleTarihi.getMonthValue();
        LocalDate temelDonem = ihaleTarihi.withDayOfMonth(1);

        while (true) {

            System.out.println("Endeks kodunu giriniz (bitir=çıkış) : ");
            String kod = scanner.nextLine().trim();

            if (kod.equalsIgnoreCase("bitir")) {
                BigDecimal toplam = akDao.sumAgirlikByIsId(isId);
                if (toplam == null) toplam = BigDecimal.ZERO;

                // "tam 1" kontrolü: scale farkı vs yüzünden şaşmasın diye böyle daha güvenli
                if (toplam.compareTo(BigDecimal.ONE) == 0) {
                    System.out.println("✔ Giriş tamamlandı.");
                    break;
                } else {
                    System.out.println("Yanlış ağırlık toplamı: " + toplam + " (1 olmalı). Girilen kayıtlar siliniyor.");
                    akDao.deleteByIsId(isId);
                    System.out.println("Tekrar deneyin");
                    continue;
                }
            }

            Long endeksId = etDao.findIdByKod(kod);
            if (endeksId == null) {
                System.out.println("Böyle bir endeks kodu yok: " + kod);
                continue;
            }

            System.out.print("Ağırlık Katsayısı: ");
            String agirlikStr = scanner.nextLine().trim();
            BigDecimal agirlik;
            try {
                agirlik = new BigDecimal(agirlikStr.replace(",", "."));
            } catch (Exception e) {
                System.out.println("Geçersiz sayı.");
                continue;
            }

            // Temel endeksi bul (önce endeks_deger, yoksa akaryakit)
            BigDecimal temelEndeks = edDao.findDeger(endeksId, temelYil, temelAy);
            if (temelEndeks == null || temelEndeks.compareTo(BigDecimal.ZERO) == 0) {
                temelEndeks = akarDao.findDeger(endeksId, temelYil, temelAy);
            }

            if (temelEndeks == null || temelEndeks.compareTo(BigDecimal.ZERO) == 0) {
                System.out.println("Temel endeks bulunamadı! kod=" + kod + " yıl=" + temelYil + " ay=" + temelAy);
                continue;
            }

            AgirlikKatsayisi k = new AgirlikKatsayisi();
            k.setIsId(isId);
            k.setEndeksTanimId(endeksId);
            k.setEndeksKodu(kod);
            k.setAgirlik(agirlik);
            k.setTemeldonem(temelDonem);
            k.setTemelendeks(temelEndeks);

            try {
                akDao.saveOrUpdateAgirlikKatsayi(k);
                System.out.println("Kaydedildi.");
            } catch (Exception e) {
                System.out.println("Kayıt hatası : " + e.getMessage());
            }
        }
    }


    public static void hakedisOlustur(Scanner scanner, HakedisDao hDao) {
        System.out.println("İş id gir : ");
        int isId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Hakediş no : ");
        int hNo = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Hakediş tarihi : ");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String tarih = scanner.nextLine().trim();
        LocalDate iTarih = tarih.isBlank() ? null : LocalDate.parse(tarih, fmt);

        Hakedis hakedis = new Hakedis();
        hakedis.setIs_id(isId);
        hakedis.setHakedisNo(hNo);
        hakedis.setTarih(iTarih);

        hDao.insertHakedis(hakedis);

        Hakedis newId = hDao.findId(hakedis);
        System.out.println("Hakediş id : " + newId.getId());

    }

    public static void metrajEkle(Scanner scanner, HakedisDao hDao, MetrajlarDao mDao) {

        System.out.println("İş id :");
        int isId = scanner.nextInt();

        System.out.println("Hakediş no :");
        int hakNo = scanner.nextInt();
        scanner.nextLine();
        Hakedis hakedis = hDao.findByHakedisId(isId, hakNo);
        if (hakedis == null) {
            System.out.println("Hakediş bulunamadı.");
            return;
        }

        System.out.println("Birim Fiyat id : ");
        int birimFiyatId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Metraj türünü gir (ADET(6)/UZUNLUK(1)/ALAN(2)/HACIM(3)/AGIRLIK(ton)(4)/AGIRLIK(kg)(5)) : ");
        int metrajturu = scanner.nextInt();
        scanner.nextLine();


        System.out.println("Açıklama gir : ");
        String aciklama = scanner.nextLine();

        Metrajlar metraj = new Metrajlar();
        metraj.setIsId(isId);
        metraj.setHakedisId(hakedis.getId());
        metraj.setHakedisNo(hakNo);
        metraj.setBirimfiyatId(birimFiyatId);
        metraj.setMetrajturuId(metrajturu);
        metraj.setAciklama(aciklama);


        System.out.println("Adet (Boş = 1) : ");
        String adetStr = scanner.nextLine().trim();
        metraj.setAdet(adetStr.isBlank() ? 1 : Integer.parseInt(adetStr));

        System.out.println("En (Boş = 0) : ");
        String enStr = scanner.nextLine().trim();
        metraj.setEn(enStr.isBlank() ? null : new BigDecimal(enStr.replace(",", ".")));

        System.out.println("Boy (Boş = 0) : ");
        String boyStr = scanner.nextLine().trim();
        metraj.setBoy(boyStr.isBlank() ? null : new BigDecimal(boyStr.replace(",", ".")));

        System.out.println("Yükseklik (Boş = 0) : ");
        String yukseklikStr = scanner.nextLine().trim();
        metraj.setYukseklik(yukseklikStr.isBlank() ? null : new BigDecimal(yukseklikStr.replace(",", ".")));

        System.out.println("Ağırlık (boş = 0) : ");
        String agirlikStr = scanner.nextLine().trim();
        metraj.setAgirlik(agirlikStr.isBlank() ? null : new BigDecimal(agirlikStr.replace(",", ".")));

        int secim = metrajturu;
        MetrajTuru tur = null;
        switch (secim) {
            case 1 -> tur = MetrajTuru.UZUNLUK;
            case 2 -> tur = MetrajTuru.ALAN;
            case 3 -> tur = MetrajTuru.HACIM;
            case 4 -> tur = MetrajTuru.AGIRLIK;
            case 5 -> tur = MetrajTuru.AGIRLIK;
            case 6 -> tur = MetrajTuru.ADET;
            default -> {
                System.out.println("Geçersiz seçim");
                return;
            }
        }
        BigDecimal miktar = MetrajHesaplama.miktarHesapla(
                tur,
                metraj.getAdet(),
                metraj.getEn(),
                metraj.getBoy(),
                metraj.getYukseklik(),
                metraj.getAgirlik()
        );
        metraj.setMiktar(miktar);

        mDao.insertMetrajlar(metraj);

        System.out.println("Metrajlar eklendi. Miktar : " + miktar);
    }

    public static void revizeFiyatKontrol(Scanner scanner,
                                          BirimFiyatlarDao bfDao,
                                          RevizeFiyatDao revDao,
                                          MetrajlarDao mDao,
                                          IslerDao iDao,
                                          HakedisDao hDao) {
        System.out.println("İş id giriniz : ");
        int isId = Integer.parseInt(scanner.nextLine().trim());
        Isler is = iDao.findById(isId);
        if (is == null) {
            System.out.println("İş bulunamadı.");
            return;
        }
        System.out.println("Hakediş no giriniz: ");
        int hakNo = Integer.parseInt(scanner.nextLine().trim());
        Hakedis hakedis = hDao.findByHakedisId(isId, hakNo);
        if (hakedis == null) {
            System.out.println("Hakediş bulunamadı.");
            return;
        }


        BigDecimal ihaleBedeli = is.getIhaleBedeli();
        if (ihaleBedeli == null) ihaleBedeli = BigDecimal.ZERO;

        List<Metrajlar> tumMetrajlar = mDao.listeleByIsVeHakedisKadar(isId, hakNo);
        for (Metrajlar metrajlar : tumMetrajlar) {

        }

        List<BirimFiyatlar> bfs = bfDao.listeleByIsId(isId);
        if (bfs == null || bfs.isEmpty()) {
            System.out.println("Bu işe ait birim fiyat bulunamadı.");
            return;
        }

        int kayit = 0;
        for (BirimFiyatlar bf : bfs) {

            BigDecimal toplam = BedelHesaplama.toplamMiktarByBirimFiyat(tumMetrajlar, isId, bf.getId());
            if (toplam == null || toplam.compareTo(BigDecimal.ZERO) == 0) continue;

            BigDecimal deger = RevizeFiyatHesaplama.revizeFiyatHesaplama(is.getIhaleBedeli(),
                    bf.getTutari(), bf.getMiktari(), toplam);

            // revize yoksa kaydetme
            if (deger == null || deger.compareTo(bf.getTutari()) == 0) continue;

            RevizeFiyat rf = new RevizeFiyat();
            rf.setIsId(isId);
            rf.setBfId(bf.getId());
            rf.setHakedisId(hakedis.getId());
            rf.setHakedisNo(hakNo);
            rf.setBfNoAd(bf.getBfiyatno());
            rf.setBfNoAdRev(bf.getBfiyatno() + "REV");
            rf.setRevizeBfFormul(deger);
            System.out.println(
                    bf.getBfiyatno() +
                            " birim fiyat " + FormatUtil.para(rf.getRevizeBfFormul()) + " olarak revizeye girdi. Eğer daha düşük bir fiyat varsa giriniz (Yoksa boş geçiniz): "
            );

            String giris = scanner.nextLine().trim();

            BigDecimal manuelRevFiyat = null;

            if (!giris.isEmpty()) {
                manuelRevFiyat = new BigDecimal(giris);
            }

            rf.setRevizeBfManuel(manuelRevFiyat);


            revDao.saveOrUpdate(rf);

            BigDecimal revizeFiyat = RevizeFiyatHesaplama.revizeFiyat(rf.getRevizeBfFormul(), rf.getRevizeBfManuel());

            BigDecimal limit120 = bf.getMiktari().multiply(BigDecimal.valueOf(1.2));
            BigDecimal tutarLimit120 = limit120.multiply(bf.getTutari());
            BigDecimal kalan = limit120.subtract(bf.getMiktari());
            BigDecimal tutarKalan = kalan.multiply(revizeFiyat);


            System.out.printf(
                    "%-15s | %25s | %12s | %18s%n",
                    "Birim Fiyat No",
                    "Birim Fiyat Tutarı",
                    "Miktar",
                    "Tutar"
            );

            System.out.printf(
                    "%-15s - %25s x %12s = %18s%n",
                    bf.getBfiyatno(),
                    FormatUtil.para(bf.getTutari()),
                    FormatUtil.sayi(limit120),
                    FormatUtil.para(tutarLimit120)
            );

            System.out.printf(
                    "%-15s - %25s x %12s = %18s%n",
                    rf.getBfNoAdRev(),
                    FormatUtil.para(revizeFiyat),
                    FormatUtil.sayi(kalan),
                    FormatUtil.para(tutarKalan)
            );


            kayit++;


        }
        System.out.println("Revize fiyat tablosuna " + kayit + " satır eklendi.");


    }

    public static void sureUzatimiEkle(Scanner scanner, SureUzatimiDao suDao, IslerDao iDao, HakedisDao hDao) {
        System.out.println("İş id gir : ");
        int isId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Hakediş no gir : ");
        int hNo = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Başkanlık oluru gir : ");
        String a = scanner.nextLine();

        System.out.println("Süre uzatımı gün sayısını giriniz (Boş(0) girilebilir) : ");
        String iSureUzStr = scanner.nextLine().trim();
        int iSureUz = iSureUzStr.isBlank() ? 0 : Integer.parseInt(iSureUzStr);

        Isler is = iDao.findById(isId);
        Hakedis h = hDao.findByHakedisId(isId, hNo);

        SureUzatimi su = new SureUzatimi();
        su.setIs_id(isId);
        su.setHakedis_id(h.getIs_id());
        su.setHakedis_no(hNo);
        su.setAciklama(a);
        su.setGunuzatma(iSureUz);

        suDao.insertSureUzatimi(su);

    }

    public static void kesifArtisiEkle(Scanner scanner, KesifArtisiDao kaDao, IslerDao iDao, HakedisDao hDao) {

        System.out.println("İş id gir : ");
        int isId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Hakediş no gir : ");
        int hNo = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Başkanlık oluru gir : ");
        String a = scanner.nextLine();

        System.out.println("Keşif Artışı bedelini giriniz (Boş girilebilir) : ");
        String kabedelStr = scanner.nextLine().trim();
        BigDecimal kaBedeli = kabedelStr.isBlank() ? null : new BigDecimal(kabedelStr.replace(",", "."));

        Isler is = iDao.findById(isId);
        Hakedis h = hDao.findByHakedisId(isId, hNo);

        KesifArtisi ka = new KesifArtisi();
        ka.setIs_id(isId);
        ka.setHakedis_id(h.getId());
        ka.setHakedis_no(hNo);
        ka.setAciklama(a);
        ka.setKesifArtisi(kaBedeli);

        kaDao.insertKesifArtisi(ka);


    }

    public static void yapilanIslerListesi(Scanner scanner,
                                           BirimFiyatlarDao bfDao,
                                           MetrajlarDao mDao,
                                           HakedisDao hDao,
                                           IslerDao iDao,
                                           RevizeFiyatDao revDao,
                                           IcmalDao icmalDao) {
        System.out.println("İş id : ");
        int isId = Integer.parseInt(scanner.nextLine().trim());
        Isler is = iDao.findById(isId);
        if (is == null) {
            System.out.println("İş bulunamadı.");
            return;
        }

        System.out.println("Hakedis no : ");
        int hakNo = Integer.parseInt(scanner.nextLine().trim());
        Hakedis h = hDao.findByHakedisId(isId, hakNo);
        if (h == null) {
            System.out.println("Hakediş bulunamadı.");
            return;
        }


        List<Metrajlar> buMetrajlar = mDao.listeleByIsVeHakedisKadar(isId, hakNo);
        List<BirimFiyatlar> liste = bfDao.listeleByIsId(isId);


        if (liste == null || liste.isEmpty()) {
            System.out.println("Bu işe ait birim fiyat bulunamadı.");
            return;
        }

        for (BirimFiyatlar birimFiyatlar : liste) {

            BigDecimal metrajlar = BedelHesaplama.toplamMiktarByBirimFiyatByHakedis(buMetrajlar, isId, birimFiyatlar.getId(), h.getHakedisNo());
            BigDecimal revizeDegeri = RevizeFiyatHesaplama.revizeFiyatHesaplama(is.getIhaleBedeli(), birimFiyatlar.getTutari(), birimFiyatlar.getMiktari(), metrajlar);

            // revize yoksa kaydetme
            if (revizeDegeri == null || revizeDegeri.compareTo(birimFiyatlar.getTutari()) == 0) {
                metrajlar = BedelHesaplama.toplamMiktarByBirimFiyatByHakedis(buMetrajlar, isId, birimFiyatlar.getId(), hakNo);
            } else {


                // revize kayıt
                RevizeFiyat rf = new RevizeFiyat();
                rf.setIsId(isId);
                rf.setBfId(birimFiyatlar.getId());
                rf.setHakedisId(h.getId());
                rf.setHakedisNo(hakNo);
                rf.setBfNoAd(birimFiyatlar.getBfiyatno());
                rf.setBfNoAdRev(birimFiyatlar.getBfiyatno() + "REV");
                rf.setRevizeBfFormul(revizeDegeri);
                System.out.println(
                        birimFiyatlar.getBfiyatno() +
                                " birim fiyat " + FormatUtil.para(rf.getRevizeBfFormul()) + " olarak revizeye girdi. Eğer daha düşük bir fiyat varsa giriniz (Yoksa boş geçiniz): "
                );

                String giris = scanner.nextLine().trim();

                BigDecimal manuelRevFiyat = null;

                if (!giris.isEmpty()) {
                    manuelRevFiyat = new BigDecimal(giris);
                }

                rf.setRevizeBfManuel(manuelRevFiyat);

                revDao.saveOrUpdate(rf);

                BigDecimal revizeFiyat = RevizeFiyatHesaplama.revizeFiyat(rf.getRevizeBfFormul(), rf.getRevizeBfManuel());


                BigDecimal limit120 = birimFiyatlar.getMiktari().multiply(BigDecimal.valueOf(1.2));

                BigDecimal tutarLimit120 = limit120.multiply(birimFiyatlar.getTutari());

                BigDecimal kalan = limit120.subtract(birimFiyatlar.getMiktari());

                BigDecimal tutarKalan = kalan.multiply(revizeFiyat);

            }

        }


        for (BirimFiyatlar bf : liste) {

            BigDecimal toplamMiktar = BedelHesaplama.toplamMiktarByBirimFiyat(buMetrajlar, isId, bf.getId());
            BigDecimal oncekiMiktar = BedelHesaplama.toplamMiktarByBirimFiyatByHakedis(buMetrajlar, isId, bf.getId(), (hakNo - 1));
            BigDecimal buMiktar = toplamMiktar.subtract(oncekiMiktar);


//             1) normal satır

            Icmal i = new Icmal();

            i.setIsId(isId);
            i.setHakId(h.getId());
            i.setHakNo(h.getHakedisNo());
            i.setBfId(bf.getId());

            i.setSatirTip(0); // NORMAL

            i.setBfNo(bf.getBfiyatno());
            i.setBfRevNo(null);
            i.setBfTanim(bf.getAd());
            i.setBfTutar(bf.getTutari());
            i.setMetrajTuruId(bf.getMetrajturuId());
            i.setTopHakMiktar(toplamMiktar);
            i.setOncekiHakMiktar(oncekiMiktar);
            i.setBuHakMiktar(buMiktar);
            i.setTopHakTutar(toplamMiktar.multiply(bf.getTutari()));
            i.setOncekiHakTutar(oncekiMiktar.multiply(bf.getTutari()));
            i.setBuHakTutar(buMiktar.multiply(bf.getTutari()));
            icmalDao.saveOrUpdateIcmal(i);

//             rev kaydı varsa bul
            RevizeFiyat rf = revDao.findByIsIdAndBfId(isId, bf.getId(), hakNo);

            BigDecimal revizeFiyat = null;
            String revNo = null;


            if (rf != null) {
                revNo = rf.getBfNoAdRev();
                revizeFiyat = RevizeFiyatHesaplama.revizeFiyat(rf.getRevizeBfFormul(), rf.getRevizeBfManuel());


                // %120 limit
                BigDecimal limit120 = bf.getMiktari().multiply(new BigDecimal("1.20"));

                // normal (120’ye kadar) miktarlar
                BigDecimal norToplam = toplamMiktar.min(limit120);
                BigDecimal norOnceki = oncekiMiktar.min(limit120);
                BigDecimal norBu = norToplam.subtract(norOnceki);


                // normal tutarlar
                BigDecimal norToplamTutar = BedelHesaplama.satirTutar(norToplam, bf.getTutari());
                BigDecimal norOncekiTutar = BedelHesaplama.satirTutar(norOnceki, bf.getTutari());
                BigDecimal norBuTutar = norToplamTutar.subtract(norOncekiTutar);


                // 1) normal satır


                Icmal normal = new Icmal();
                normal.setIsId(isId);
                normal.setHakId(h.getId());
                normal.setHakNo(h.getHakedisNo());
                normal.setBfId(bf.getId());

                normal.setSatirTip(0); // NORMAL

                normal.setBfNo(bf.getBfiyatno());
                normal.setBfRevNo(null);
                normal.setBfTanim(bf.getAd());
                normal.setBfTutar(bf.getTutari());
                normal.setMetrajTuruId(bf.getMetrajturuId());
                normal.setTopHakMiktar(norToplam);
                normal.setOncekiHakMiktar(norOnceki);
                normal.setBuHakMiktar(norBu);

                normal.setTopHakTutar(norToplamTutar);
                normal.setOncekiHakTutar(norOncekiTutar);
                normal.setBuHakTutar(norBuTutar);

                icmalDao.saveOrUpdateIcmal(normal);

//            }

                // 2) rev satırı (120’yi aşan kısım)
                if (revizeFiyat != null && toplamMiktar.compareTo(limit120) > 0) {

                    BigDecimal revToplam = toplamMiktar.subtract(limit120);
                    if (revToplam.compareTo(BigDecimal.ZERO) < 0) {
                        revToplam = BigDecimal.ZERO;
                    }

                    BigDecimal revOnceki = oncekiMiktar.subtract(limit120);
                    if (revOnceki.compareTo(BigDecimal.ZERO) < 0) {
                        revOnceki = BigDecimal.ZERO;
                    }

                    BigDecimal revBu = revToplam.subtract(revOnceki);


                    BigDecimal revToplamTutar = BedelHesaplama.satirTutar(revToplam, revizeFiyat);

                    BigDecimal oncekiHakNo = BigDecimal.valueOf(h.getHakedisNo() - 1);

                    BigDecimal revizeBFiyat = revDao.findFormulBf(isId, oncekiHakNo.intValue(), bf.getId());

                    BigDecimal revOncekiTutar;

                    if (revizeBFiyat != null) {
                        revOncekiTutar = BedelHesaplama.satirTutar(revOnceki, revizeBFiyat);
                    } else {
                        revOncekiTutar = BedelHesaplama.satirTutar(oncekiMiktar, bf.getTutari()).subtract(norOncekiTutar);
                    }
                    BigDecimal revBuTutar = revToplamTutar.subtract(revOncekiTutar);


                    Icmal rev = new Icmal();
                    rev.setIsId(isId);
                    rev.setHakId(h.getId());
                    rev.setHakNo(h.getHakedisNo());
                    rev.setBfId(bf.getId());

                    rev.setSatirTip(1); // REVIZE

                    rev.setBfNo(bf.getBfiyatno());
                    rev.setBfRevNo(rf.getBfNoAdRev()); // sadece bilgi amaçlı
                    rev.setBfTanim(bf.getAd());
                    rev.setBfTutar(revizeFiyat);               // rev satırında uygulanan bf
                    rev.setMetrajTuruId(bf.getMetrajturuId());

                    rev.setTopHakMiktar(revToplam);
                    rev.setOncekiHakMiktar(revOnceki);
                    rev.setBuHakMiktar(revBu);

                    rev.setTopHakTutar(revToplamTutar);
                    rev.setOncekiHakTutar(revOncekiTutar);
                    rev.setBuHakTutar(revBuTutar);

                    icmalDao.saveOrUpdateIcmal(rev);


                }
            }

        }

    }


    public static void icmalToplamlar(Scanner scanner,
                                      HakedisDao hDao,
                                      IslerDao iDao,
                                      IcmalDao icmalDao,
                                      HakedisOzetDao hoDao) {

        System.out.println("İş id : ");
        int isId = Integer.parseInt(scanner.nextLine().trim());
        Isler is = iDao.findById(isId);
        if (is == null) {
            System.out.println("İş bulunamadı.");
            return;
        }

        System.out.println("Hakedis no : ");
        int hakNo = Integer.parseInt(scanner.nextLine().trim());
        Hakedis h = hDao.findByHakedisId(isId, hakNo);
        if (h == null) {
            System.out.println("Hakediş bulunamadı.");
            return;
        }


        List<Icmal> liste = icmalDao.listeleByIsIdAndHakNo(isId, hakNo);

        BigDecimal icmalToplam = IcmalListe.genelToplamTutar(liste);

        BigDecimal icmalOnceki = IcmalListe.oncekiToplamTutar(liste);

        BigDecimal icmalbu = IcmalListe.buHakedisTutar(liste);

        HakedisOzet ho = new HakedisOzet();
        ho.setIsId(isId);
        ho.setHakedisId(h.getId());
        ho.setHakedisNo(hakNo);
        ho.setToplam(icmalToplam);
        ho.setOnceki(icmalOnceki);
        ho.setBu(icmalbu);


        hoDao.saveOrUpdate(ho);


    }

    public static void fiyatFarki(Scanner scanner,
                                  HakedisDao hDao,
                                  IslerDao iDao,
                                  IcmalDao icmalDao,
                                  HakedisOzetDao hoDao,
                                  FiyatFarkiDao ffDao,
                                  IsProgramiDao ipDao,
                                  AgirlikKatsayisiDao akDao,
                                  FiyatFarkiOzetDao ffoDao) {

        System.out.println("İş id : ");
        int isId = Integer.parseInt(scanner.nextLine().trim());
        Isler is = iDao.findById(isId);
        if (is == null) {
            System.out.println("İş bulunamadı.");
            return;
        }

        System.out.println("Hakedis no : ");
        int hakNo = Integer.parseInt(scanner.nextLine().trim());
        Hakedis h = hDao.findByHakedisId(isId, hakNo);
        if (h == null) {
            System.out.println("Hakediş bulunamadı.");
            return;
        }


        BigDecimal kalanHakedis = hoDao.findByIsIdAndHakNo(isId, hakNo).getBu();
        if (kalanHakedis == null) kalanHakedis = BigDecimal.ZERO;

// cutoff: hakediş tarihinin bir önceki ayı (ayın 1'i)
        LocalDate hakTarih = h.getTarih();
        LocalDate cutoff = hakTarih.minusMonths(1).withDayOfMonth(1);


// önceki hakediş kayıtları (hakNo'dan küçük) - SUM yok, Java ile toplayacağız
        List<FiyatFarkiOzet> oncekiKayitlar = ffoDao.findOncekilerByIsIdBeforeHakNo(isId, hakNo);

        Map<Long, BigDecimal> oncekiToplamMap = new HashMap<>();
        for (FiyatFarkiOzet k : oncekiKayitlar) {
            long ipId = k.getIsprogramiId();
            BigDecimal tutar = (k.getHakedisTutar() != null) ? k.getHakedisTutar() : BigDecimal.ZERO;
            oncekiToplamMap.put(ipId, oncekiToplamMap.getOrDefault(ipId, BigDecimal.ZERO).add(tutar));
        }

// iş programı listesi
        List<IsProgrami> isProgramiList = ipDao.findByIsId(isId);

// cutoff'a kadar olan son iş programını hatırlayacağız
        IsProgrami sonUygunIsProgDonem = null;

        for (IsProgrami ip : isProgramiList) {

            if (ip.getDonem().isAfter(cutoff)) continue; // cutoff sonrası dağıtma yok

            sonUygunIsProgDonem = ip; // en son geçerli ip

            if (kalanHakedis.compareTo(BigDecimal.ZERO) <= 0) break;

            BigDecimal oncekiToplam = oncekiToplamMap.getOrDefault(ip.getId(), BigDecimal.ZERO);
            BigDecimal kapasite = ip.getAylikTutar().subtract(oncekiToplam);
            if (kapasite.compareTo(BigDecimal.ZERO) <= 0) continue;


            BigDecimal atanacak;
            if (ip.getDonem().isEqual(h.getTarih().minusMonths(1).withDayOfMonth(1))) {
                atanacak = kalanHakedis;
            } else {
                atanacak = kalanHakedis.min(kapasite);
            }


            FiyatFarki ff = new FiyatFarki();
            ff.setIsId(isId);
            ff.setHakedisId(h.getId());
            ff.setIsprogramId(ip.getId());
            ff.setHakedisNo(hakNo);
            ff.setIsProgramiDonem(ip.getDonem());
            ff.setIsProgramiTutar(ip.getAylikTutar());
            ff.setHakedisTutar(atanacak);
/*
            // B katsayısı = 0,9

            BigDecimal B = BigDecimal.valueOf(0.9);

            // Pn ve (Pn - 1)

            System.out.println("********************************");
            List<AgirlikKatsayisi> list = akDao.listByIsId(isId);
            ArrayList<BigDecimal> listeDeneme = new ArrayList<>();
            for (AgirlikKatsayisi agirlikKatsayisi : list) {
                listeDeneme.add(agirlikKatsayisi.getTemelendeks());
                System.out.println(agirlikKatsayisi.getTemelendeks());
            }
            BigDecimal min = Collections.min(listeDeneme);
            System.out.println("en küçük değer : " + min);
            System.out.println("********************************");

*/
            ffDao.saveOrUpdate(ff);

            kalanHakedis = kalanHakedis.subtract(atanacak);
            oncekiToplamMap.put(ip.getId(), oncekiToplam.add(atanacak));

        }

// ✅ Kural: kalan para cutoff sonrası aylara dağıtılmayacak, son dönemde kalacak
        if (kalanHakedis.compareTo(BigDecimal.ZERO) > 0) {

            System.out.println("kalan hakediş son" + kalanHakedis);
            if (sonUygunIsProgDonem == null) {
                System.out.println("Uyarı: Hakediş için cutoff'a kadar iş programı dönemi yok. Kalan: " + kalanHakedis);
            } else {

                // kalan parayı sonUygunIsProgDonem'e ekstra satır olarak ekle (kapasite aşabilir)
                FiyatFarki ffKalan = new FiyatFarki();
                ffKalan.setIsId(isId);
                ffKalan.setHakedisId(h.getId());
                ffKalan.setIsprogramId(sonUygunIsProgDonem.getId());
                ffKalan.setHakedisNo(hakNo);
                ffKalan.setIsProgramiDonem(sonUygunIsProgDonem.getDonem());
                ffKalan.setIsProgramiTutar(sonUygunIsProgDonem.getAylikTutar());
                ffKalan.setHakedisTutar(kalanHakedis);

                ffDao.saveOrUpdate(ffKalan);

                kalanHakedis = BigDecimal.ZERO;
            }

        }

        List<FiyatFarki> kayitListesi = ffDao.findByIsIdAndHakNo(isId, hakNo);


        for (FiyatFarki fiyatFarki : kayitListesi) {

            FiyatFarkiOzet fiyatFarkiOzet = new FiyatFarkiOzet();
            fiyatFarkiOzet.setIsId(isId);
            fiyatFarkiOzet.setHakedisId(h.getId());
            fiyatFarkiOzet.setIsprogramiId(fiyatFarki.getIsprogramId());
            fiyatFarkiOzet.setHakedisNo(hakNo);
            fiyatFarkiOzet.setIsprogramiDonem(fiyatFarki.getIsProgramiDonem());
            fiyatFarkiOzet.setIsprogramiTutar(fiyatFarki.getIsProgramiTutar());
            fiyatFarkiOzet.setHakedisTutar(fiyatFarki.getHakedisTutar());

            ffoDao.insert(fiyatFarkiOzet);

        }
        ffDao.deleteByIsIdAndHakNo(isId,hakNo);

    }

    public static void hakedisRapor(Scanner scanner,
                                    BirimFiyatlarDao bfDao,
                                    MetrajlarDao mDao,
                                    HakedisDao hDao,
                                    IslerDao iDao,
                                    SureUzatimiDao suDao,
                                    KesifArtisiDao kaDao,
                                    RevizeFiyatDao revDao,
                                    IcmalDao icmalDao) {


        System.out.println("İş id : ");
        int isId = Integer.parseInt(scanner.nextLine().trim());
        Isler is = iDao.findById(isId);
        if (is == null) {
            System.out.println("İş bulunamadı.");
            return;
        }

        System.out.println("Hakedis no : ");
        int hakNo = Integer.parseInt(scanner.nextLine().trim());
        Hakedis h = hDao.findByHakedisId(isId, hakNo);
        if (h == null) {
            System.out.println("Hakediş bulunamadı.");
            return;
        }

        List<Metrajlar> buMetrajlar = mDao.listeleByIsAndHakedisId(isId, hakNo);
        List<Metrajlar> tumMetrajlar = mDao.listeleByIsVeHakedisKadar(h.getIs_id(), hakNo);
        List<BirimFiyatlar> bfs = bfDao.listeleByIsId(h.getIs_id());

        BigDecimal genelToplam = BigDecimal.ZERO;
        BigDecimal oncekiToplam = BigDecimal.ZERO;
        BigDecimal buToplam = BigDecimal.ZERO;


        List<SureUzatimi> su = suDao.listByIsId(isId);
        List<KesifArtisi> ka = kaDao.listByIsId(isId);


        System.out.println("=======================================================================");
        System.out.println("                           HAKEDİŞ RAPORU                              ");
        System.out.println("=======================================================================");
        System.out.println("İşin Adı           : " + is.getIsinadi());
        System.out.println("Hakediş No         : " + h.getHakedisNo());
        System.out.println("Hakediş Tarihi     : " + h.getTarih());
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("İhale Tarihi       : " + is.getIhaletarihi());
        System.out.println("Sözleşme Bedeli    : " + FormatUtil.para(is.getIhaleBedeli()));
        System.out.println("Sözleşme Tarihi    : " + is.getSozlesmetarihi());
        System.out.println("Yer Teslimi Tarihi : " + is.getYerteslimitarihi());
        System.out.println("İşin süresi        : " + is.getIsinsuresi());
        System.out.println("Sözleşmeye göre");
        System.out.println("Bitiş Tarihi       : " + is.getSozlesmebitimtarihi());
        System.out.println("------------------------------------------------------------------------");

        LocalDate sozBitis = is.getSozlesmebitimtarihi();
        System.out.println("----------Süre Uzatımı----------");
        for (SureUzatimi sureUzatimi : su) {
            if (sureUzatimi.getHakedis_no() <= hakNo) {
                List<SureUzatimi> suList = suDao.listByIsIdAndHakedisNo(isId, hakNo);
                int ekGun = SureHesaplama.toplamGunHesapla(suList);
                LocalDate yeniBitisTarihi = sozBitis.plusDays(ekGun);
                sozBitis = yeniBitisTarihi;

                System.out.println("Başkanlık Oluru    : " + sureUzatimi.getAciklama());
                System.out.println("Süre Uzatımı       : " + sureUzatimi.getGunuzatma());
                System.out.println("İşin Bitiş Tarihi  : " + yeniBitisTarihi);
                System.out.println("------------------------");
            }
        }
        System.out.println("----------Keşif Artışı----------");
        BigDecimal ihaleBedeli = is.getIhaleBedeli();
        for (KesifArtisi kesifArtisi : ka) {
            if (kesifArtisi.getHakedis_no() <= hakNo) {
                BigDecimal topArtis = ihaleBedeli.add(kesifArtisi.getKesifArtisi());
                ihaleBedeli = topArtis;
                System.out.println("Başkanlık Oluru    : " + kesifArtisi.getAciklama());
                System.out.println("Keşif Artışı       : " + FormatUtil.para(kesifArtisi.getKesifArtisi()));
                System.out.println("Toplam Bedel       : " + FormatUtil.para(topArtis));
                System.out.println("------------------------");
            }
        }

        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");


        if (ihaleBedeli == null) ihaleBedeli = BigDecimal.ZERO;


        if (bfs == null || bfs.isEmpty()) {
            System.out.println("Bu işe ait birim fiyat bulunamadı.");
            return;
        }

        int kayit = 0;
        for (BirimFiyatlar bf : bfs) {

            BigDecimal toplam = BedelHesaplama.toplamMiktarByBirimFiyat(tumMetrajlar, bf.getIsId(), bf.getId());
            if (toplam == null || toplam.compareTo(BigDecimal.ZERO) == 0) continue;

            BigDecimal deger = RevizeFiyatHesaplama.revizeFiyatHesaplama(is.getIhaleBedeli(),
                    bf.getTutari(), bf.getMiktari(), toplam);

            // revize yoksa kaydetme
            if (deger == null || deger.compareTo(bf.getTutari()) == 0) continue;

            // revize kayıt
            RevizeFiyat rf = new RevizeFiyat();
            rf.setIsId(isId);
            rf.setBfId(bf.getId());
            rf.setHakedisId(h.getId());
            rf.setBfNoAd(bf.getBfiyatno());
            rf.setBfNoAdRev(bf.getBfiyatno() + "REV");
            rf.setRevizeBfFormul(deger);
            System.out.println(
                    bf.getBfiyatno() +
                            " birim fiyat " + FormatUtil.para(rf.getRevizeBfFormul()) + " olarak revizeye girdi. Eğer daha düşük bir fiyat varsa giriniz (Yoksa boş geçiniz): "
            );

            String giris = scanner.nextLine().trim();

            BigDecimal manuelRevFiyat = null;

            if (!giris.isEmpty()) {
                manuelRevFiyat = new BigDecimal(giris);
            }

            rf.setRevizeBfManuel(manuelRevFiyat);


            revDao.saveOrUpdate(rf);

            BigDecimal revizeFiyat = RevizeFiyatHesaplama.revizeFiyat(rf.getRevizeBfFormul(), rf.getRevizeBfManuel());

            BigDecimal limit120 = bf.getMiktari().multiply(BigDecimal.valueOf(1.2));
            BigDecimal tutarLimit120 = limit120.multiply(bf.getTutari());
            BigDecimal kalan = limit120.subtract(bf.getMiktari());
            BigDecimal tutarKalan = kalan.multiply(revizeFiyat);


            kayit++;


        }
        System.out.println("Revize fiyat tablosuna " + kayit + " satır eklendi.");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf(
                "%-12s %-25s %25s %15s %15s %15s %18s %18s %18s%n",
                "No", "Adı", "BF", "Toplam", "Önceki", "Bu", "Toplam Tutar", "Önceki Tutar", "Bu Tutar"
        );

        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");


        for (BirimFiyatlar bf : bfs) {

            BigDecimal toplamMiktar = BedelHesaplama.toplamMiktarByBirimFiyat(tumMetrajlar, isId, bf.getId());
            if (toplamMiktar.compareTo(BigDecimal.ZERO) == 0) continue;

            BigDecimal oncekiMiktar = BedelHesaplama.toplamMiktarByBirimFiyatByHakedis(tumMetrajlar, isId, bf.getId(), (hakNo - 1));
            BigDecimal buMiktar = toplamMiktar.subtract(oncekiMiktar);


            // rev kaydı varsa bul
            RevizeFiyat rf = revDao.findByIsIdAndBfId(isId, bf.getId(), h.getHakedisNo());

            BigDecimal revizeFiyat = null;
            String revNo = null;

            if (rf != null) {
                revNo = rf.getBfNoAdRev();
                revizeFiyat = RevizeFiyatHesaplama.revizeFiyat(rf.getRevizeBfFormul(), rf.getRevizeBfManuel());


                // %120 limit
                BigDecimal limit120 = bf.getMiktari().multiply(new BigDecimal("1.20"));

                // normal (120’ye kadar) miktarlar
                BigDecimal norToplam = toplamMiktar.min(limit120);
                BigDecimal norOnceki = oncekiMiktar.min(limit120);
                BigDecimal norBu = norToplam.subtract(norOnceki);

                // normal tutarlar
                BigDecimal norToplamTutar = BedelHesaplama.satirTutar(norToplam, bf.getTutari());
                BigDecimal norOncekiTutar = BedelHesaplama.satirTutar(norOnceki, bf.getTutari());
                BigDecimal norBuTutar = norToplamTutar.subtract(norOncekiTutar);

                // 1) normal satır
                System.out.printf(
                        "%-12s %-25s %25s %15s %15s %15s %18s %18s %18s%n",
                        bf.getBfiyatno(),
                        bf.getAd(),
                        FormatUtil.para(bf.getTutari()),
                        FormatUtil.sayi(norToplam),
                        FormatUtil.sayi(norOnceki),
                        FormatUtil.sayi(norBu),
                        FormatUtil.para(norToplamTutar),
                        FormatUtil.para(norOncekiTutar),
                        FormatUtil.para(norBuTutar)
                );


                // 2) rev satırı (120’yi aşan kısım)
                if (revizeFiyat != null && toplamMiktar.compareTo(limit120) > 0) {


                    BigDecimal revToplam = toplamMiktar.subtract(limit120);
                    if (revToplam.compareTo(BigDecimal.ZERO) < 0) {
                        revToplam = BigDecimal.ZERO;
                    }

                    BigDecimal revOnceki = oncekiMiktar.subtract(limit120);
                    if (revOnceki.compareTo(BigDecimal.ZERO) < 0) {
                        revOnceki = BigDecimal.ZERO;
                    }

                    BigDecimal revBu = revToplam.subtract(revOnceki);


                    BigDecimal revToplamTutar = BedelHesaplama.satirTutar(revToplam, revizeFiyat);
                    BigDecimal oncekiHakNo = BigDecimal.valueOf(h.getHakedisNo() - 1);

                    BigDecimal revizeBFiyat = revDao.findFormulBf(isId, oncekiHakNo.intValue(), bf.getId());

                    BigDecimal revOncekiTutar;

                    if (revizeBFiyat != null) {
                        revOncekiTutar = BedelHesaplama.satirTutar(revOnceki, revizeBFiyat);
                    } else {
                        revOncekiTutar = BedelHesaplama.satirTutar(oncekiMiktar, bf.getTutari()).subtract(norOncekiTutar);
                    }

                    BigDecimal revBuTutar = revToplamTutar.subtract(revOncekiTutar);

                    System.out.printf(
                            "%-12s %-25s %25s %15s %15s %15s %18s %18s %18s%n",
                            (revNo != null ? revNo : rf.getBfNoAdRev()),
                            bf.getAd(),
                            FormatUtil.para(revizeFiyat),
                            FormatUtil.sayi(revToplam),
                            FormatUtil.sayi(revOnceki),
                            FormatUtil.sayi(revBu),
                            FormatUtil.para(revToplamTutar),
                            FormatUtil.para(revOncekiTutar),
                            FormatUtil.para(revBuTutar)
                    );
                }
            }
            if (rf == null) {
                BigDecimal sonToplamTutar = BedelHesaplama.satirTutar(toplamMiktar, bf.getTutari());
                BigDecimal sonOncekiTutar = BedelHesaplama.satirTutar(oncekiMiktar, bf.getTutari());
                BigDecimal sonBuTutar = sonToplamTutar.subtract(sonOncekiTutar);

                System.out.printf(
                        "%-12s %-25s %25s %15s %15s %15s %18s %18s %18s%n",
                        bf.getBfiyatno(),
                        bf.getAd(),
                        FormatUtil.para(bf.getTutari()),
                        FormatUtil.sayi(toplamMiktar),
                        FormatUtil.sayi(oncekiMiktar),
                        FormatUtil.sayi(buMiktar),
                        FormatUtil.para(sonToplamTutar),
                        FormatUtil.para(sonOncekiTutar),
                        FormatUtil.para(sonBuTutar)
                );
            }

        }


        List<Icmal> liste = icmalDao.listeleByIsIdAndHakNo(isId, hakNo);

        BigDecimal icmalToplam = IcmalListe.genelToplamTutar(liste);

        BigDecimal icmalOnceki = IcmalListe.oncekiToplamTutar(liste);

        BigDecimal icmalbu = IcmalListe.buHakedisTutar(liste);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%110s %20s %18s %18s%n",
                "GENEL TOPLAM",
                FormatUtil.para(icmalToplam),
                FormatUtil.para(icmalOnceki),
                FormatUtil.para(icmalbu));


        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }
}



