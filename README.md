# **📘 Praktikum PBO 10: Upload CSV ke Database PostgreSQL**

## **🧩 Deskripsi Proyek**
Proyek ini dibuat menggunakan **NetBeans 27** dengan **JDK 24**. Tujuan utamanya adalah untuk menambahkan fitur *Upload CSV* ke dalam aplikasi **CRUD** yang telah dibuat sebelumnya. Fitur ini memungkinkan pengguna untuk mengimpor data dari file `.csv` ke tabel database PostgreSQL secara otomatis.

---

## **🧠 Konsep Dasar**
Fitur upload CSV memanfaatkan:
- **JFileChooser** → untuk memilih file dari komputer.
- **BufferedReader** → membaca isi file CSV baris demi baris.
- **PreparedStatement** → untuk memasukkan data ke database secara aman.

---

## **⚙️ Struktur Proyek**
```bash
src/
├── package1/
│   ├── KoneksiDB.java          # Class koneksi database PostgreSQL
│   ├── Upload.java           # Class untuk proses baca & upload CSV
│   └── TransaksiForm.java    # JFrame dengan tombol Upload
```

---

## **📄 Contoh File CSV**
Simpan dengan nama `transaksi_data.csv`:

```csv
id_transaksi,nama_pelanggan,tipe_mobil,harga,tanggal_transaksi
1,Andi,Avanza,200000000,2024-10-01
2,Budi,Brio,180000000,2024-10-02
3,Citra,Fortuner,550000000,2024-10-03
4,Dewi,Innova,350000000,2024-10-04
5,Eko,HRV,320000000,2024-10-05
```

---

## **💻 Class Upload.java**
```java
package package1;

import java.io.*;
import java.sql.*;

public class Upload {
    public static void uploadCSV(String filePath) {
        String query = "INSERT INTO Transaksi_Mobil (id_transaksi, nama_pelanggan, tipe_mobil, harga, tanggal_transaksi) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             BufferedReader br = new BufferedReader(new FileReader(filePath));
             PreparedStatement pst = conn.prepareStatement(query)) {

            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                pst.setInt(1, Integer.parseInt(data[0]));
                pst.setString(2, data[1]);
                pst.setString(3, data[2]);
                pst.setDouble(4, Double.parseDouble(data[3]));
                pst.setDate(5, java.sql.Date.valueOf(data[4]));
                pst.executeUpdate();
            }

            System.out.println("✅ Data CSV berhasil diupload!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

## **🖱️ Pemanggilan pada Tombol Upload**
Di file `TransaksiForm.java`:

```java
private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showOpenDialog(this);

    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        Upload.uploadCSV(selectedFile.getAbsolutePath());
    }
}
```

---

## **🧪 Cara Menjalankan Program**
1. Buka **NetBeans 27**.
2. Pastikan database PostgreSQL sudah aktif.
3. Jalankan program dan klik tombol **Upload**.
4. Pilih file `transaksi_data.csv`.
5. Lihat hasilnya di tabel `Transaksi_Mobil` di pgAdmin.

---

## **🚀 Hasil Akhir**
Jika berhasil, terminal akan menampilkan pesan:

```bash
Koneksi ke database berhasil!
✅ Data CSV berhasil diupload!
```

---

## **📚 Catatan Tambahan**
> ⚠️ Pastikan format tanggal di file CSV menggunakan pola **YYYY-MM-DD** agar tidak terjadi error tipe data di PostgreSQL.

> 💡 Gunakan **batch insert** jika ingin mempercepat proses upload data dalam jumlah besar.

---

### **Penulis:** _Mahasiswa Sistem Informasi UIN Sunan Ampel Surabaya_
### **Tanggal Praktikum:** _Oktober 2025_
