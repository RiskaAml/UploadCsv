package package1;

import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

public class CetakLaporan {

    public static void cetakTransaksi() {
        try {
            // Lokasi file .jrxml (bisa kamu ubah sesuai lokasi kamu)
            String reportPath = "src/package1/Laporan.jrxml";

            // 1️⃣ Kompilasi file JRXML ke JasperReport
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);

            // 2️⃣ Dapatkan koneksi ke database PostgreSQL
            Connection conn = KoneksiDB.getConnection();

            // 3️⃣ Kirim parameter (kalau mau filter bisa taruh di sini)
            HashMap<String, Object> params = new HashMap<>();
            // Contoh: params.put("id_transaksi", 1);
            
            
            // 4️⃣ Isi laporan dengan data dari database
            JasperPrint print = JasperFillManager.fillReport(jasperReport, params, conn);

            // 5️⃣ Tampilkan laporan
            JasperViewer viewer = new JasperViewer(print, false);
            viewer.setTitle("Laporan Transaksi Mobil");
            viewer.setVisible(true);

        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, 
                "Gagal membuat laporan: " + e.getMessage(), 
                "JasperReport Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "Terjadi kesalahan: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}