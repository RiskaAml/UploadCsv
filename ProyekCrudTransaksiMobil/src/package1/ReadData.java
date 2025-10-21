package package1;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ReadData {
    
    public void displayAll(){
        String sql = "SELECT id_transaksi, merk_mobil, harga, tanggal_transaksi, nama_pembeli FROM Transaksi_Mobil ORDER BY id_transaksi";
        
        System.out.println("\n--- DAFTAR TRANSAKSI MOBIL ---");
        
        try (Connection conn = KoneksiDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            boolean dataFound = false;
            
            // Header kolom
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.printf("%-5s | %-20s | %-12s | %-15s | %-20s\n", 
                              "ID", "Merk Mobil", "Harga", "Tanggal", "Nama Pembeli");
            System.out.println("---------------------------------------------------------------------------------------------");
            
            while (rs.next()) {
                System.out.printf("%-5d | %-20s | %-12.2f | %-15s | %-20s\n",
                                  rs.getInt("id_transaksi"),
                                  rs.getString("merk_mobil"),
                                  rs.getDouble("harga"),
                                  rs.getDate("tanggal_transaksi"),
                                  rs.getString("nama_pembeli"));
                dataFound = true;
            }
            
            if (!dataFound) {
                System.out.println("Tabel Transaksi_Mobil masih kosong.");
            }
            System.out.println("---------------------------------------------------------------------------------------------");
            
        } catch (SQLException e) {
            System.out.println("Gagal membaca data.");
            e.printStackTrace();
        }
    }
}
