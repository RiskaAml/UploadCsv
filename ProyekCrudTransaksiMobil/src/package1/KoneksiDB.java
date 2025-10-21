package package1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class KoneksiDB {
    
    // GANTI USER DAN PASS SESUAI KONFIGURASI POSTGRESQL ANDA
    private static final String URL = "jdbc:postgresql://localhost:5432/TugasPbo";
    private static final String USER = "postgres";
    private static final String PASS = "amelcantik"; 
    
    // Metode ini membuat koneksi baru setiap dipanggil (efisien dan aman)
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASS);
    }
    
    // Metode untuk memastikan koneksi berhasil dan tabel sudah ada
    public static void initialize() {
        try (Connection conn = getConnection()) {
            System.out.println("Koneksi ke database berhasil!");
            createTable(conn);
        } catch (SQLException e) {
            System.err.println("Gagal inisialisasi. Cek driver, kredensial, dan pastikan database 'db_proyek_crud' sudah dibuat.");
        }
    }
    
    // Metode untuk membuat tabel jika belum ada
    public static void createTable(Connection conn) throws SQLException {
        String sql = """
                      CREATE TABLE IF NOT EXISTS Transaksi_Mobil (
                          id_transaksi SERIAL PRIMARY KEY,
                          merk_mobil VARCHAR(100) NOT NULL,
                          harga NUMERIC (15,2) NOT NULL,
                          tanggal_transaksi DATE NOT NULL, 
                          nama_pembeli VARCHAR (100) NOT NULL
                      );
                      """;
        try (Statement stmt = conn.createStatement()){
            stmt.execute(sql);
            System.out.println("Tabel Transaksi_Mobil siap digunakan.");
        }
    }
}