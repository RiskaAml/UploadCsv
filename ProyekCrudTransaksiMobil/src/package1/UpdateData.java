package package1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateData {
    
    public void update(int id_transaksi, String merk_mobil, double harga, String tanggal_transaksi, String nama_pembeli) {
        String sql = """
                     UPDATE Transaksi_Mobil
                     SET merk_mobil = ?, harga = ?, tanggal_transaksi = ?, nama_pembeli = ?
                     WHERE id_transaksi = ?;
                     """;
        
        try (Connection conn = KoneksiDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, merk_mobil);
            pstmt.setDouble(2, harga);
            pstmt.setDate(3, java.sql.Date.valueOf(tanggal_transaksi)); // Format: "YYYY-MM-DD"
            pstmt.setString(4, nama_pembeli);
            pstmt.setInt(5, id_transaksi);
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Update: Data ID " + id_transaksi + " berhasil diperbarui.");
            } else {
                System.out.println("Data ID " + id_transaksi + " tidak ditemukan.");
            }
            
        } catch (SQLException e) {
            System.out.println("Gagal update data!");
            e.printStackTrace();
        }
    }
}
