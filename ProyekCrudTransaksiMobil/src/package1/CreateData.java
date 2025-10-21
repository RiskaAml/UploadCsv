package package1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateData {

    public void insert(String merk_mobil, double harga, String tanggal_transaksi, String nama_pembeli) {
        String sql = """
            INSERT INTO Transaksi_Mobil (merk_mobil, harga, tanggal_transaksi, nama_pembeli)
            VALUES (?, ?, ?, ?)
            RETURNING id_transaksi;
            """;

        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, merk_mobil);
            pstmt.setDouble(2, harga);
            pstmt.setDate(3, java.sql.Date.valueOf(tanggal_transaksi)); // Format: "YYYY-MM-DD"
            pstmt.setString(4, nama_pembeli);

            ResultSet rs = pstmt.executeQuery(); // karena RETURNING menghasilkan data

            if (rs.next()) {
                int id_transaksi = rs.getInt("id_transaksi");
                System.out.println("Create: Data dengan ID " + id_transaksi + " berhasil dimasukkan.");
            }

        } catch (SQLException e) {
            System.out.println("Gagal insert data! Database Error.");
            e.printStackTrace();
        }
    }
}
