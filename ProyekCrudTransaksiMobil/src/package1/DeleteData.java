package package1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteData {
    
    public void delete(int id){
        String sql = "DELETE FROM Transaksi_Mobil WHERE id_transaksi = ?";
        
        try(Connection conn = KoneksiDB.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1, id);
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0){
                System.out.println("Delete: Data Id " + id + " berhasil dihapus.");
            }else{
                System.out.println("Data Id " + id + " tidak ditemukan.");
            }
            
        }catch(SQLException e){
            System.out.println("Gagal menghapus data!");
            e.printStackTrace();
        }
    }
}