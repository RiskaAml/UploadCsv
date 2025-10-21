package package1;

import java.io.File;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JFileChooser;

public class TransaksiForm extends javax.swing.JFrame {
    private void loadTableData() {
        DefaultTableModel model = new DefaultTableModel();
        jTableTransaksi.setModel(model);

        try (Connection conn = KoneksiDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT id_transaksi, merk_mobil, harga, tanggal_transaksi, nama_pembeli FROM Transaksi_Mobil ORDER BY id_transaksi")) {

            // Header kolom di GUI (gunakan spasi seperti desain kamu)
            model.addColumn("ID Transaksi");
            model.addColumn("Merk");
            model.addColumn("Harga");
            model.addColumn("Tanggal");
            model.addColumn("Nama Pembeli");

            // Masukkan data dari database ke model
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id_transaksi"),
                    rs.getString("merk_mobil"),
                    rs.getDouble("harga"),
                    rs.getDate("tanggal_transaksi"),
                    rs.getString("nama_pembeli")
                };
                model.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                "Gagal memuat data dari database: " + e.getMessage(),
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public TransaksiForm() {
        initComponents();
        loadTableData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTransaksi = new javax.swing.JTable();
        btnInsert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClearAll = new javax.swing.JButton();
        btnCetak = new javax.swing.JButton();
        btnUpload = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Transaksi", "Merk", "Harga", "Tanggal", "Nama Pembeli"
            }
        ));
        jScrollPane1.setViewportView(jTableTransaksi);

        btnInsert.setText("Insert");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClearAll.setText("Clear All");
        btnClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearAllActionPerformed(evt);
            }
        });

        btnCetak.setText("Cetak");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });

        btnUpload.setText("Upload");
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnInsert, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addComponent(btnCetak)
                        .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(btnClearAll)
                    .addComponent(btnUpload))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(btnInsert)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClearAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpload)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCetak)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTableTransaksi.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris data yang ingin dihapus terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Ambil ID dan Merk Mobil dari baris yang dipilih
        int idToDelete = (int) jTableTransaksi.getValueAt(selectedRow, 0); 
        String merkMobil = (String) jTableTransaksi.getValueAt(selectedRow, 1); 

        // Konfirmasi dan eksekusi
        int konfirmasi = JOptionPane.showConfirmDialog(this, 
                "Apakah Anda yakin ingin menghapus data ID " + idToDelete + " (" + merkMobil + ")?", 
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (konfirmasi == JOptionPane.YES_OPTION) {
            DeleteData delete = new DeleteData();
            delete.delete(idToDelete); 

            JOptionPane.showMessageDialog(this, "Data ID " + idToDelete + " berhasil dihapus.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            loadTableData(); // Refresh tampilan tabel
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:
        // Mode INSERT: Kirim ID = -1 (penanda), data lainnya kosong
        TransaksiDialog dialog = new TransaksiDialog(this, true, -1, "", 0.0, "", "");
        dialog.setVisible(true);
        loadTableData(); // Refresh tabel setelah dialog ditutup

    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
    int selectedRow = jTableTransaksi.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih baris yang ingin diubah.", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Ambil data dari tabel
    int id = (int) jTableTransaksi.getValueAt(selectedRow, 0);
    String merk = (String) jTableTransaksi.getValueAt(selectedRow, 1);
    double harga = Double.parseDouble(jTableTransaksi.getValueAt(selectedRow, 2).toString());
    String tanggal = jTableTransaksi.getValueAt(selectedRow, 3).toString();
    String pembeli = (String) jTableTransaksi.getValueAt(selectedRow, 4);

    // Buka dialog update
    TransaksiDialog dialog = new TransaksiDialog(this, true, id, merk, harga, tanggal, pembeli);
    dialog.setVisible(true);
    loadTableData(); // Refresh tabel setelah dialog ditutup
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearAllActionPerformed
        // TODO add your handling code here:
        // Logika Clear All untuk tabel Transaksi_Mobil
        int konfirmasi = JOptionPane.showConfirmDialog(this, 
                "PERINGATAN! Tindakan ini akan menghapus SEMUA data dan mereset ID. Lanjutkan?", 
                "Konfirmasi TRUNCATE", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE); 

        if (konfirmasi == JOptionPane.YES_OPTION) {
            try (Connection conn = KoneksiDB.getConnection();
                 Statement stmt = conn.createStatement()) {

                // Hapus semua data dan reset auto-increment ID
                stmt.execute("TRUNCATE TABLE Transaksi_Mobil RESTART IDENTITY");

                JOptionPane.showMessageDialog(this, 
                        "Semua data transaksi berhasil dikosongkan.", 
                        "Sukses", JOptionPane.INFORMATION_MESSAGE);

                loadTableData(); // Refresh tabel di GUI

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, 
                        "Gagal mengosongkan tabel: " + e.getMessage(), 
                        "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_btnClearAllActionPerformed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        package1.CetakLaporan.cetakTransaksi();
    }//GEN-LAST:event_btnCetakActionPerformed

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        Upload.uploadCSV(selectedFile.getAbsolutePath());
        JOptionPane.showMessageDialog(this, "File berhasil diupload ke database!");
        loadTableData(); // panggil method untuk refresh tabel jika kamu sudah punya
    }        // TODO add your handling code here:
    }//GEN-LAST:event_btnUploadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnClearAll;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpload;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTransaksi;
    // End of variables declaration//GEN-END:variables
}
