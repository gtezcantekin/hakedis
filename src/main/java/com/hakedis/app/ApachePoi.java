package com.hakedis.app;

import com.hakedis.service.AkaryakitXlsImportService;
import com.hakedis.service.CsvHeaderImportService;
import com.hakedis.service.EndeksXlsImportService;
import com.hakedis.util.JDBCHelper;

import java.nio.file.Path;
import java.sql.*;

public class ApachePoi {
    static void main(String[] args) {


//        Path csv = Path.of("C:/Users/Tezcan/Desktop/hakedis/basliklar.csv");
//
//        try (Connection conn = JDBCHelper.getConnection()) {
//
//            CsvHeaderImportService svc = new CsvHeaderImportService(conn);
//            svc.importHeader(csv);
//
//            System.out.println("Header import tamam.");
//        } catch (Exception e) {
//            System.out.println("Hata (apachepoi başlık) : " + e.getMessage());
//        }


//        Path xlsPath = Path.of("C:\\Users\\Tezcan\\Desktop\\hakedis\\endeksler.xls");
//
//        try (Connection conn = JDBCHelper.getConnection()) {
//
//            System.out.println("DB OK");
//
//            new EndeksXlsImportService(conn).importXls(xlsPath);
//
//            System.out.println("XLS IMPORT TAMAM ✅");
//            System.out.println("Endeks değerleri import edildi ✅");
//
//        } catch (Exception e) {
//            System.out.println("Import hatası ❌");
//            e.printStackTrace();
//        }

        Path xlsPath = Path.of(
                "C:\\Users\\Tezcan\\Desktop\\hakedis\\akaryakit.xls"
        );

        try (Connection conn = JDBCHelper.getConnection()) {

            System.out.println("DB OK ✅");

            AkaryakitXlsImportService service =
                    new AkaryakitXlsImportService(conn);

            service.importXls(xlsPath);

            System.out.println("XLS IMPORT TAMAM ✅");
            System.out.println("Akaryakıt endeksleri yüklendi ✅");

        } catch (Exception e) {

            System.out.println("Import hatası ❌");
            e.printStackTrace();
        }
    }
}