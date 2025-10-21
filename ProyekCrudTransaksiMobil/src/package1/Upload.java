package package1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Upload {
    public static void uploadCSV(String filePath) {
        String sql = "INSERT INTO Transaksi_Mobil (id_transaksi, merk_mobil, harga, tanggal_transaksi, nama_pembeli) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = KoneksiDB.getConnection();
             BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length < 5) continue;

                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, Integer.parseInt(values[0].trim()));
                    pstmt.setString(2, values[1].trim());
                    pstmt.setDouble(3, Double.parseDouble(values[2].trim()));

                    // Ubah string ke java.sql.Date agar sesuai tipe kolom DATE PostgreSQL
                    java.sql.Date sqlDate = java.sql.Date.valueOf(values[3].trim()); // Format harus yyyy-MM-dd
                    pstmt.setDate(4, sqlDate);

                    pstmt.setString(5, values[4].trim());
                    pstmt.executeUpdate();
                }
            }

            System.out.println("Data CSV berhasil diupload ke database.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}