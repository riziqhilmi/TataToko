/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Utama;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.management.remote.JMXConnectorFactory.connect;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author user
 */
public class Transaksi extends javax.swing.JPanel {

    DefaultTableModel table = new DefaultTableModel();
    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel model2 = new DefaultTableModel();

    koneksi db = new koneksi();
    java.sql.Connection con;
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/tatatoko";
    private final String user = "root";
    private final String pwd = "";

    /**
     * Creates new form Transaksi
     */
    public Transaksi(String nama) {
        initComponents();

        totalnya();
        tanggal();
        Barcode();

        tb_keranjang.setModel(table);
        table.addColumn("ID");
        table.addColumn("Nama Barang");
        table.addColumn("Harga");
        table.addColumn("Jumlah");
        table.addColumn("Total Harga");

        model.addColumn("ID");
        model.addColumn("Nama Barang");
        model.addColumn("Jenis Barang");
        model.addColumn("Jumlah Barang");
        model.addColumn("Harga Jual");
        model.addColumn("Satuan");
        model.addColumn("Status");

        table_barang.setModel(model);
        tampilDataS();
        tampilData();

        model2.addColumn("Tanggal");
        model2.addColumn("ID Transaksi");
        model2.addColumn("Nama Pelanggan");
        model2.addColumn("Total Harga");
        model2.addColumn("Bayar");
        model2.addColumn("Metode Pembayaran");
        table_Riwayat.setModel(model2);

        Lb_Nama_Kasir.setText(nama);

        table_Riwayat.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table_Riwayat.getSelectedRow();
                if (selectedRow != -1) {
                    // Ambil id_transaksi dari baris yang dipilih
                    String id_transaksi = table_Riwayat.getValueAt(selectedRow, 1).toString();
                    // Panggil fungsi untuk memuat detail transaksi berdasarkan id_transaksi
                    loadDetailTransaksi(id_transaksi);
                }
            }
        });
        

    }

    public void tanggal() {
        Date now = new Date();
        tgl_transaksi.setDate(now);
    }

    public void tampilDataS() {

        ResultSet hasil = db.ambilData("SELECT * FROM barang");
        try {
            while (hasil.next()) {
                model.addRow(new Object[]{hasil.getString("id_barang"),
                    hasil.getString("nama_barang"), hasil.getString("jenis"),
                    hasil.getString("jumlah"),
                    hasil.getString("harga_jual"), hasil.getString("satuan"),
                    hasil.getString("status")});
            }
            table_barang.setModel(model);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void getDataT() {

        ResultSet hasil = db.ambilData("SELECT * FROM transaksi");
        try {
            while (hasil.next()) {
                model2.addRow(new Object[]{hasil.getString("tgl_transaksi"),
                    hasil.getString("id_transaksi"), hasil.getString("pelanggan"), hasil.getString("total_bayar"),
                    hasil.getString("bayar"), hasil.getString("metode")});
            }
            table_Riwayat.setModel(model2);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private void tampilData() {
        //untuk mengahapus baris setelah input
        int row = tb_keranjang.getRowCount();
        for (int a = 0; a < row; a++) {
            table.removeRow(0);
        }

        String procedures = "CALL `total_harga_transaksi`()";

        ResultSet hasil = db.ambilData("SELECT * FROM keranjang");
        try {
            while (hasil.next()) {
                int harga = hasil.getInt("harga");
                int total = hasil.getInt("total");
                table.addRow(new Object[]{hasil.getString("id_transaksi"),
                    hasil.getString("nama_barang"), "Rp." + harga,
                    hasil.getString("jumlah"), "Rp." + total,});
            }
            tb_keranjang.setModel(table);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private void refresh() {
        model.setRowCount(0);

        // Ambil data stok barang dari database
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);

            String queryGetData = "SELECT * FROM barang";
            PreparedStatement psGetData = con.prepareStatement(queryGetData);
            ResultSet rsGetData = psGetData.executeQuery();

            // Isi model tabel dengan data dari database
            while (rsGetData.next()) {
                String idBarang = rsGetData.getString("id_barang");
                String namaBarang = rsGetData.getString("nama_barang");
                String jenis = rsGetData.getString("jenis");
                int jumlah = rsGetData.getInt("jumlah");
                int harga = rsGetData.getInt("harga_jual");
                String satuan = rsGetData.getString("satuan");
                String status = rsGetData.getString("status");

                // Tambahkan baris baru ke model tabel
                model.addRow(new Object[]{idBarang, namaBarang, jenis, jumlah, harga, satuan, status});
            }

            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }

    private void totalnya() {
        String procedures = "CALL `total_harga_transaksi`()";

        try {
            java.sql.Connection connect = koneksi.getConnection();//memanggil koneksi
            Statement sttmnt = connect.createStatement();//membuat statement
            ResultSet rslt = sttmnt.executeQuery(procedures);//menjalanakn query\
            while (rslt.next()) {
                Field_Total_Semua.setText(rslt.getString(1));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void total() {
        String hargaText = txt_harga2.getText();
        String jumlahText = txt_jumlah3.getText();

        // Menghilangkan "Rp." dan spasi dari string harga
        String hargaClean = hargaText.replaceAll("[^\\d]", "");

        try {
            int harga = Integer.parseInt(hargaClean);
            int jumlah = Integer.parseInt(jumlahText);

            int total = harga * jumlah;
            String totalHargaText = "Rp. " + total;

            txt_totalharga.setText(totalHargaText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Isi Menggunakan Angka!");
            txt_totalharga.setText("Rp. 0");
        }
    }

    private void reset() {
        txt_uang.setText(null);
    }

    private void kembalian() {
        String totalText = Field_Total_Semua.getText();
        String uangText = txt_uang.getText();

        // Menghilangkan "Rp." dan spasi dari string total dan uang
        String totalClean = totalText.replaceAll("[^\\d]", "");
        String uangClean = uangText.replaceAll("[^\\d]", "");

        try {
            // Hapus teks di txt_kembalian jika uangText kosong atau panjangnya kurang dari yang diinginkan
            if (uangClean.isEmpty() || uangClean.length() <= 3) {
                txt_kembalian.setText("");
                return;
            }

            // Pastikan total dan uang tidak kosong dan merupakan angka yang valid
            if (!totalClean.isEmpty() && !uangClean.isEmpty()) {
                int total = Integer.parseInt(totalClean);
                int uang = Integer.parseInt(uangClean);

                // Hanya lakukan perhitungan jika panjang uangClean lebih dari 5 digit
                if (uang >= total) {
                    int kembali = uang - total;
                    String kembaliText = "Rp. " + NumberFormat.getNumberInstance(Locale.GERMAN).format(kembali);
                    txt_kembalian.setText(kembaliText);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Input: Pastikan masukan hanya berupa angka");
        }
    }

    private void clear() {
        Field_Kode_Barang_Transaksi.setText(null);
        Field__Transaksi_Barang.setText(null);
        txt_harga2.setText(null);
        txt_jumlah3.setText(null);
        txt_totalharga.setText(null);
    }

    public void autoIn() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);

            String sqlquery = "SELECT MAX(RIGHT(id_transaksi,9)) AS no_auto FROM transaksi";
            PreparedStatement st = con.prepareStatement(sqlquery);
            ResultSet rs = st.executeQuery();

            String no_a, no_p = "";
            int p;
            if (rs.next()) {
                no_a = Integer.toString(rs.getInt(1) + 1);
                p = no_a.length();

                for (int i = 1; i <= 9 - p; i++) {
                    no_p = no_p + "0";
                }
                Field__Transaksi_ID.setText(no_p + no_a);
            }

        } catch (Exception e) {
            Field__Transaksi_ID.setText("");
            e.printStackTrace();
        }

    }

    // Variabel global untuk menyimpan nomor terakhir
    private int lastTransaksiNumber = 0;

    public void autoInN() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);

            String sqlquery = "SELECT MAX(RIGHT(id_transaksi,9)) AS no_auto FROM keranjang";
            PreparedStatement st = con.prepareStatement(sqlquery);
            ResultSet rs = st.executeQuery();

            String no_a, no_p = "";
            int p;
            if (rs.next()) {
                no_a = Integer.toString(rs.getInt(1) + 1);
                p = no_a.length();

                for (int i = 1; i <= 9 - p; i++) {
                    no_p = no_p + "0";
                }
                Field_Keranjang_ID.setText(no_p + no_a);
            }

        } catch (Exception e) {
            Field_Keranjang_ID.setText("");
            e.printStackTrace();
        }
    }

    private boolean kurangiStokBarang(String id_b, int jumlah) {
        try {
            // Ambil stok barang saat ini dari database
            ResultSet rs = db.ambilData("SELECT jumlah FROM barang WHERE id_barang = '" + id_b + "'");
            int stokSaatIni = 0;
            if (rs.next()) {
                stokSaatIni = rs.getInt("jumlah");
            }

            // Hitung stok baru setelah dikurangi jumlah yang dibeli
            int stokBaru = stokSaatIni - jumlah;

            // Periksa apakah stok cukup
            if (stokBaru < 0) {
                JOptionPane.showMessageDialog(null, "Stok tidak cukup. Stok saat ini: " + stokSaatIni);
                return false;
            } else {
                // Update stok barang di database
                db.aksi("UPDATE barang SET jumlah = " + stokBaru + " WHERE id_barang = '" + id_b + "'");
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mengurangi stok barang: " + e.getMessage());
            return false;
        }
    }

    private void keranjang() {
        String id_T = Field_Keranjang_ID.getText();
        String id_t = Field__Transaksi_ID.getText();
        String id_b = Field_Kode_Barang_Transaksi.getText();
        String nama = Field__Transaksi_Barang.getText();
        String harga = txt_harga2.getText();
        String hargaClean = harga.replaceAll("[^\\d]", "");
        int hargaInt = Integer.parseInt(hargaClean);
        String jumlah = txt_jumlah3.getText();
        String total = txt_totalharga.getText();
        String totalClean = total.replaceAll("[^\\d]", "");
        int totalInt = Integer.parseInt(totalClean);
        String metode = String.valueOf(Field_Metode.getSelectedItem());
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = String.valueOf(date.format(tgl_transaksi.getDate()));

        try {

            boolean stokCukup = kurangiStokBarang(id_b, Integer.parseInt(jumlah));

            if (!stokCukup) {
                return;
            }

            db.aksi("INSERT INTO keranjang VALUES ('" + id_T + "','" + id_b + "','" + nama + "','" + tanggal + "','" + hargaInt + "','" + jumlah + "','" + totalInt + "')");

            table.setRowCount(0);
            tb_keranjang.setModel(table);

            tampilData();
            autoInN();
            JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditambahkan: " + e.getMessage());
        } finally {
            clear();
        }

        TotalHarga();
        totalnya();
    }

    public void transaksi() {
        String id_t = Field__Transaksi_ID.getText();
        String id_b = Field_Kode_Barang_Transaksi.getText();
        String pelanggan = Field_Nama_Pelanggan.getText();
        String kembaliInt = txt_kembalian.getText();
        String kembaliC = kembaliInt.replaceAll("[^\\d]", "");
        int kembali = Integer.parseInt(kembaliC);
        String total = Field_Total_Semua.getText();
        String totalClean = total.replaceAll("[^\\d]", "");
        int total_bayar = Integer.parseInt(totalClean);

        String bayar = txt_uang.getText();
        String metode = String.valueOf(Field_Metode.getSelectedItem());

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = String.valueOf(date.format(tgl_transaksi.getDate()));

        try {
            db.aksi("START TRANSACTION");

            db.aksi("INSERT INTO transaksi (tgl_transaksi, id_transaksi, total_bayar, bayar, kembali, pelanggan, metode) "
                    + "VALUES ('" + tanggal + "','" + id_t + "', '" + total_bayar + "','" + bayar + "','" + kembali + "','" + pelanggan + "','" + metode + "')");

            db.aksi("INSERT INTO detail_transaksi (id_transaksi, id_barang, nama_barang, tanggal, harga, jumlah, total) "
                    + "SELECT '" + id_t + "', id_barang, nama_barang, '" + tanggal + "', harga, jumlah, total FROM keranjang");

            // Commit transaksi
            db.aksi("COMMIT");
            JOptionPane.showMessageDialog(null, "Transaksi Berhasil ");
        } catch (Exception e) {
            try {
                // Rollback transaksi jika terjadi kesalahan
                db.aksi("ROLLBACK");
            } catch (Exception rollbackException) {
                JOptionPane.showMessageDialog(null, "Rollback Gagal: " + rollbackException.getMessage());
            }
            JOptionPane.showMessageDialog(null, "Data Gagal Ditambahkan: " + e.getMessage());
        }
        
    }

    private void loadDetailTransaksi(String id_transaksi) {
        // Bersihkan tabel detail transaksi sebelum memuat data baru
        DefaultTableModel modelDetail = (DefaultTableModel) Tbl_Detail_Transaksi.getModel();
        modelDetail.setRowCount(0);

        // Ambil data dari tabel detail_transaksi berdasarkan id_transaksi
        ResultSet hasilDetail = db.ambilData("SELECT * FROM detail_transaksi WHERE id_transaksi = '" + id_transaksi + "'");
        try {
            while (hasilDetail.next()) {
                modelDetail.addRow(new Object[]{
                    //hasilDetail.getString("id_transaksi"),
                    hasilDetail.getString("id_barang"),
                    hasilDetail.getString("nama_barang"),
                    hasilDetail.getString("tanggal"),
                    hasilDetail.getString("harga"),
                    hasilDetail.getString("jumlah"),
                    hasilDetail.getString("total")
                });
            }
            Tbl_Detail_Transaksi.setModel(modelDetail);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void TotalHarga() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);

            String sqlquery = "SELECT SUM(total) FROM keranjang";
            PreparedStatement st = con.prepareStatement(sqlquery);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int totalHarga = rs.getInt(1); // Assuming harga is an integer column
                Field_Total_Semua.setText(String.valueOf("Rp. " + totalHarga)); // Set the total price to your field
            }
        } catch (Exception e) {
            // Handle exceptions here
            e.printStackTrace();
        }
    }

    private void hapusData() {
        //ambill data no pendaftaran
        int i = tb_keranjang.getSelectedRow();

        String kode = table.getValueAt(i, 0).toString();

        java.sql.Connection connect = koneksi.getConnection();

        String query = "DELETE FROM `keranjang` WHERE `keranjang`.`id_transaksi` = '" + kode + "' ";
        try {
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(query);
            ps.execute();
        } catch (SQLException | HeadlessException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus");
        } finally {
            tampilData();
            clear();
        }
        totalnya();
    }

    private void Barcode() {
        Field_Cari_Stok.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterData(Field_Cari_Stok.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterData(Field_Cari_Stok.getText()); // Call filterData on removeUpdate
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterData(Field_Cari_Stok.getText()); // Call filterData on changedUpdate
            }

            private void filterData(String keyword) {
                TableRowSorter<TableModel> sorter = new TableRowSorter<>(table_barang.getModel());
                table_barang.setRowSorter(sorter);

                if (keyword.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword)); // Filter data sesuai dengan kata kunci (ignore case)
                }
            }

        });

        Field_Cari_Transaksi.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterData(Field_Cari_Transaksi.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterData(Field_Cari_Transaksi.getText()); // Call filterData on removeUpdate
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterData(Field_Cari_Transaksi.getText()); // Call filterData on changedUpdate
            }

            private void filterData(String keyword) {
                TableRowSorter<TableModel> sorter = new TableRowSorter<>(table_Riwayat.getModel());
                table_Riwayat.setRowSorter(sorter);

                if (keyword.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword)); // Filter data sesuai dengan kata kunci (ignore case)
                }
            }

        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel_Utama = new javax.swing.JPanel();
        Panel_Transaksi = new javax.swing.JPanel();
        Field_Keranjang_ID = new javax.swing.JTextField();
        Field_Kode_Barang_Transaksi = new javax.swing.JTextField();
        Field__Transaksi_Barang = new javax.swing.JTextField();
        txt_totalharga = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txt_harga2 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txt_uang = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_kembalian = new javax.swing.JTextField();
        tgl_transaksi = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_keranjang = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        Field_Total_Semua = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        Field__Transaksi_ID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Field_Metode = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_jumlah3 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        Lb_Nama_Kasir = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Field_Nama_Pelanggan = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        Panel_Stok = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        Field_Cari_Stok = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_barang = new javax.swing.JTable();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle3 = new rojerusan.RSMaterialButtonRectangle();
        Panel_Detail_Riwayat = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        rSMaterialButtonRectangle4 = new rojerusan.RSMaterialButtonRectangle();
        Field_Cari_Transaksi = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_Riwayat = new javax.swing.JTable();
        rSMaterialButtonRectangle5 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle6 = new rojerusan.RSMaterialButtonRectangle();
        jScrollPane4 = new javax.swing.JScrollPane();
        Tbl_Detail_Transaksi = new javax.swing.JTable();

        setLayout(new java.awt.CardLayout());

        Panel_Utama.setLayout(new java.awt.CardLayout());

        Panel_Transaksi.setBackground(new java.awt.Color(255, 255, 255));
        Panel_Transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Panel_TransaksiMouseClicked(evt);
            }
        });

        Field_Keranjang_ID.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Field_Keranjang_ID.setEnabled(false);
        Field_Keranjang_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Field_Keranjang_IDActionPerformed(evt);
            }
        });
        Field_Keranjang_ID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Field_Keranjang_IDKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Field_Keranjang_IDKeyTyped(evt);
            }
        });

        Field_Kode_Barang_Transaksi.setEditable(false);
        Field_Kode_Barang_Transaksi.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Field_Kode_Barang_Transaksi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Field_Kode_Barang_Transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Field_Kode_Barang_TransaksiActionPerformed(evt);
            }
        });

        Field__Transaksi_Barang.setEditable(false);
        Field__Transaksi_Barang.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Field__Transaksi_Barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Field__Transaksi_BarangActionPerformed(evt);
            }
        });

        txt_totalharga.setEditable(false);
        txt_totalharga.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_totalharga.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txt_totalhargaMouseReleased(evt);
            }
        });
        txt_totalharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalhargaActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton1.setText("  PAYMENT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton3.setText("  SEARCH DATA");
        jButton3.setToolTipText("");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txt_harga2.setEditable(false);
        txt_harga2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_harga2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_harga2ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 526, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        txt_uang.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txt_uang.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_uang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_uangMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txt_uangMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txt_uangMouseReleased(evt);
            }
        });
        txt_uang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_uangActionPerformed(evt);
            }
        });
        txt_uang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_uangKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_uangKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_uangKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel3.setText("Nama Barang");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel4.setText("Harga");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel5.setText("Jumlah");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel11.setText("Total Harga");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("BAYAR");

        txt_kembalian.setEditable(false);
        txt_kembalian.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        txt_kembalian.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_kembalian.setEnabled(false);
        txt_kembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kembalianActionPerformed(evt);
            }
        });

        tgl_transaksi.setDateFormatString("dd-MM-yyyy");
        tgl_transaksi.setEnabled(false);
        tgl_transaksi.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jButton2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton2.setText("  PRINT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tb_keranjang.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        tb_keranjang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_keranjang.setRowHeight(30);
        tb_keranjang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_keranjangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_keranjang);

        jButton5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton5.setText("  ADD");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        Field_Total_Semua.setEditable(false);
        Field_Total_Semua.setFont(new java.awt.Font("Dialog", 1, 22)); // NOI18N
        Field_Total_Semua.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Field_Total_Semua.setEnabled(false);
        Field_Total_Semua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Field_Total_SemuaMouseReleased(evt);
            }
        });
        Field_Total_Semua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Field_Total_SemuaActionPerformed(evt);
            }
        });
        Field_Total_Semua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Field_Total_SemuaKeyReleased(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton6.setText("HAPUS");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton7.setText("  RESET");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        Field__Transaksi_ID.setEditable(false);
        Field__Transaksi_ID.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Field__Transaksi_ID.setEnabled(false);
        Field__Transaksi_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Field__Transaksi_IDActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel6.setText("ID Transaksi");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("KEMBALIAN");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("TOTAL");

        Field_Metode.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Field_Metode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "QRIS" }));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel12.setText("Metode Pembayaran");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel10.setText("ID Keranjang");

        txt_jumlah3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txt_jumlah3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jumlah3ActionPerformed(evt);
            }
        });
        txt_jumlah3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_jumlah3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_jumlah3KeyTyped(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton8.setText("Riwayat");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        Lb_Nama_Kasir.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        Lb_Nama_Kasir.setText("Nama");

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel13.setText("Nama Pelanggan");

        Field_Nama_Pelanggan.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N

        jButton9.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton9.setText("Tambah Transaksi");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_TransaksiLayout = new javax.swing.GroupLayout(Panel_Transaksi);
        Panel_Transaksi.setLayout(Panel_TransaksiLayout);
        Panel_TransaksiLayout.setHorizontalGroup(
            Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_TransaksiLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Panel_TransaksiLayout.createSequentialGroup()
                        .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(Panel_TransaksiLayout.createSequentialGroup()
                                .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(Field_Nama_Pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(Panel_TransaksiLayout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Field_Kode_Barang_Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Panel_TransaksiLayout.createSequentialGroup()
                                .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_uang)
                                        .addComponent(Field_Total_Semua)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
                                        .addGroup(Panel_TransaksiLayout.createSequentialGroup()
                                            .addGap(187, 187, 187)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Panel_TransaksiLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Lb_Nama_Kasir, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(tgl_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Panel_TransaksiLayout.createSequentialGroup()
                        .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(Panel_TransaksiLayout.createSequentialGroup()
                                    .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel5))
                                    .addGap(631, 631, 631))
                                .addGroup(Panel_TransaksiLayout.createSequentialGroup()
                                    .addComponent(txt_jumlah3)
                                    .addGap(269, 269, 269)))
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10)
                            .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txt_harga2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                                .addComponent(Field__Transaksi_Barang, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Field_Keranjang_ID, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Field__Transaksi_ID, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(Panel_TransaksiLayout.createSequentialGroup()
                                .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_totalharga, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Field_Metode, javax.swing.GroupLayout.Alignment.LEADING, 0, 450, Short.MAX_VALUE))
                                    .addComponent(jLabel12))
                                .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Panel_TransaksiLayout.createSequentialGroup()
                                        .addGap(230, 230, 230)
                                        .addComponent(txt_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_TransaksiLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(156, 156, 156))
        );
        Panel_TransaksiLayout.setVerticalGroup(
            Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_TransaksiLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_TransaksiLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6))
                    .addGroup(Panel_TransaksiLayout.createSequentialGroup()
                        .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tgl_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Lb_Nama_Kasir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_TransaksiLayout.createSequentialGroup()
                        .addComponent(Field__Transaksi_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Field_Keranjang_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Field__Transaksi_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_harga2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel_TransaksiLayout.createSequentialGroup()
                        .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Field_Kode_Barang_Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(65, 65, 65)
                        .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(Panel_TransaksiLayout.createSequentialGroup()
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_TransaksiLayout.createSequentialGroup()
                        .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel_TransaksiLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_jumlah3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(Panel_TransaksiLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Field_Total_Semua, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)))
                .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_TransaksiLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_uang, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel_TransaksiLayout.createSequentialGroup()
                        .addComponent(txt_totalharga, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addGap(25, 25, 25)
                        .addComponent(Field_Nama_Pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Panel_TransaksiLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Panel_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(102, 102, 102))
                    .addGroup(Panel_TransaksiLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Field_Metode, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90))))
        );

        Panel_Utama.add(Panel_Transaksi, "card2");

        Panel_Stok.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(0, 51, 153));
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DAFTAR MENU");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addContainerGap())
        );

        rSMaterialButtonRectangle1.setText("Cari");
        rSMaterialButtonRectangle1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });

        Field_Cari_Stok.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        Field_Cari_Stok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Field_Cari_StokActionPerformed(evt);
            }
        });

        table_barang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        table_barang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_barang.setRowHeight(35);
        table_barang.setSelectionBackground(new java.awt.Color(0, 51, 153));
        table_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_barangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_barang);

        rSMaterialButtonRectangle2.setBackground(new java.awt.Color(255, 255, 51));
        rSMaterialButtonRectangle2.setForeground(new java.awt.Color(0, 0, 0));
        rSMaterialButtonRectangle2.setText("Refresh");
        rSMaterialButtonRectangle2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });

        rSMaterialButtonRectangle3.setBackground(new java.awt.Color(255, 153, 0));
        rSMaterialButtonRectangle3.setForeground(new java.awt.Color(0, 0, 0));
        rSMaterialButtonRectangle3.setText("Kembali");
        rSMaterialButtonRectangle3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        rSMaterialButtonRectangle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_StokLayout = new javax.swing.GroupLayout(Panel_Stok);
        Panel_Stok.setLayout(Panel_StokLayout);
        Panel_StokLayout.setHorizontalGroup(
            Panel_StokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Panel_StokLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(Field_Cari_Stok, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(Panel_StokLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124)
                .addGroup(Panel_StokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                    .addComponent(rSMaterialButtonRectangle3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        Panel_StokLayout.setVerticalGroup(
            Panel_StokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_StokLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(Panel_StokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Panel_StokLayout.createSequentialGroup()
                        .addGroup(Panel_StokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Field_Cari_Stok, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(67, 67, 67)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(Panel_StokLayout.createSequentialGroup()
                        .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(rSMaterialButtonRectangle3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(324, Short.MAX_VALUE))
        );

        Panel_Utama.add(Panel_Stok, "card2");

        Panel_Detail_Riwayat.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(0, 51, 153));
        jPanel6.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Riwayat Transaksi");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rSMaterialButtonRectangle4.setText("Cari");
        rSMaterialButtonRectangle4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        rSMaterialButtonRectangle4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle4ActionPerformed(evt);
            }
        });

        Field_Cari_Transaksi.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N

        table_Riwayat.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        table_Riwayat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_Riwayat.setRowHeight(35);
        table_Riwayat.setSelectionBackground(new java.awt.Color(0, 51, 153));
        table_Riwayat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_RiwayatMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(table_Riwayat);

        rSMaterialButtonRectangle5.setBackground(new java.awt.Color(255, 255, 51));
        rSMaterialButtonRectangle5.setForeground(new java.awt.Color(0, 0, 0));
        rSMaterialButtonRectangle5.setText("Print");
        rSMaterialButtonRectangle5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        rSMaterialButtonRectangle5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle5ActionPerformed(evt);
            }
        });

        rSMaterialButtonRectangle6.setBackground(new java.awt.Color(255, 153, 0));
        rSMaterialButtonRectangle6.setForeground(new java.awt.Color(0, 0, 0));
        rSMaterialButtonRectangle6.setText("Kembali");
        rSMaterialButtonRectangle6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        rSMaterialButtonRectangle6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle6ActionPerformed(evt);
            }
        });

        Tbl_Detail_Transaksi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Tbl_Detail_Transaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Barang", "Nama Barang", "Tanggal", "Harga", "Jumlah", "Total"
            }
        ));
        Tbl_Detail_Transaksi.setRowHeight(35);
        jScrollPane4.setViewportView(Tbl_Detail_Transaksi);

        javax.swing.GroupLayout Panel_Detail_RiwayatLayout = new javax.swing.GroupLayout(Panel_Detail_Riwayat);
        Panel_Detail_Riwayat.setLayout(Panel_Detail_RiwayatLayout);
        Panel_Detail_RiwayatLayout.setHorizontalGroup(
            Panel_Detail_RiwayatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Panel_Detail_RiwayatLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(Field_Cari_Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rSMaterialButtonRectangle4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(Panel_Detail_RiwayatLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(Panel_Detail_RiwayatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1261, Short.MAX_VALUE))
                .addGap(124, 124, 124)
                .addGroup(Panel_Detail_RiwayatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rSMaterialButtonRectangle5, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                    .addComponent(rSMaterialButtonRectangle6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        Panel_Detail_RiwayatLayout.setVerticalGroup(
            Panel_Detail_RiwayatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_Detail_RiwayatLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addGroup(Panel_Detail_RiwayatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Panel_Detail_RiwayatLayout.createSequentialGroup()
                        .addComponent(rSMaterialButtonRectangle5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(rSMaterialButtonRectangle6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(643, Short.MAX_VALUE))
                    .addGroup(Panel_Detail_RiwayatLayout.createSequentialGroup()
                        .addGroup(Panel_Detail_RiwayatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Field_Cari_Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSMaterialButtonRectangle4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(67, 67, 67)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133))))
        );

        Panel_Utama.add(Panel_Detail_Riwayat, "card2");

        add(Panel_Utama, "card4");
    }// </editor-fold>//GEN-END:initComponents

    private void Field_Kode_Barang_TransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Field_Kode_Barang_TransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Field_Kode_Barang_TransaksiActionPerformed

    private void Field__Transaksi_BarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Field__Transaksi_BarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Field__Transaksi_BarangActionPerformed

    private void txt_totalhargaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_totalhargaMouseReleased

    }//GEN-LAST:event_txt_totalhargaMouseReleased

    private void txt_totalhargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalhargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalhargaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        transaksi();

        // Kosongkan kolom input untuk nama pelanggan
        Field_Nama_Pelanggan.setText(null);

        // Kosongkan tabel keranjang setelah transaksi selesai

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Panel_Utama.removeAll();
        Panel_Utama.repaint();
        Panel_Utama.revalidate();

        Panel_Utama.add(Panel_Stok);
        Panel_Utama.repaint();
        Panel_Utama.revalidate();

        Field_Cari_Stok.setText(null);
        txt_uang.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txt_harga2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_harga2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_harga2ActionPerformed

    private void txt_uangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_uangActionPerformed

    }//GEN-LAST:event_txt_uangActionPerformed

    private void txt_uangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_uangKeyPressed

    }//GEN-LAST:event_txt_uangKeyPressed

    private void txt_uangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_uangKeyReleased
        // TODO add your handling code here:
        kembalian();
    }//GEN-LAST:event_txt_uangKeyReleased

    private void txt_uangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_uangKeyTyped

    }//GEN-LAST:event_txt_uangKeyTyped

    private void txt_kembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kembalianActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {

            String file = "/Report/nota.jasper";

            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            HashMap param = new HashMap();

            param.put("total_semua", Field_Total_Semua.getText());
            param.put("uang", txt_uang.getText());
            param.put("kembali", txt_kembalian.getText());
            param.put("id", Field__Transaksi_ID.getText());
            param.put("kasir", Lb_Nama_Kasir.getText());

            JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream(file), param, con);
            JasperViewer.viewReport(print, false);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | JRException e) {
            System.out.println(e);
        } catch (SQLException ex) {
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void tb_keranjangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_keranjangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_keranjangMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        keranjang();

    }//GEN-LAST:event_jButton5ActionPerformed

    private void Field_Total_SemuaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Field_Total_SemuaMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_Field_Total_SemuaMouseReleased

    private void Field_Total_SemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Field_Total_SemuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Field_Total_SemuaActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int i = tb_keranjang.getSelectedRow();
        String kode = table.getValueAt(i, 0).toString();

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);

            // Ambil data barang yang akan dihapus dari tabel "keranjang"
            String queryGetData = "SELECT id_barang, jumlah FROM keranjang WHERE id_transaksi = ?";
            PreparedStatement psGetData = con.prepareStatement(queryGetData);
            psGetData.setString(1, kode);
            ResultSet rsGetData = psGetData.executeQuery();

            if (rsGetData.next()) {
                String idBarang = rsGetData.getString("id_barang");
                int jumlahDihapus = rsGetData.getInt("jumlah");

                // Hapus data dari tabel "keranjang"
                String queryDelete = "DELETE FROM keranjang WHERE id_transaksi = ?";
                PreparedStatement psDelete = con.prepareStatement(queryDelete);
                psDelete.setString(1, kode);
                psDelete.executeUpdate();

                // Perbarui stok barang di tabel "barang"
                String queryUpdateStock = "UPDATE barang SET jumlah = jumlah + ? WHERE id_barang = ?";
                PreparedStatement psUpdateStock = con.prepareStatement(queryUpdateStock);
                psUpdateStock.setInt(1, jumlahDihapus);
                psUpdateStock.setString(2, idBarang);
                psUpdateStock.executeUpdate();

                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            } else {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan");
            }

            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menghapus data");
        } finally {
            tampilData();
            TotalHarga();
            clear();
        }
        totalnya();

        txt_uang.setText(null);
        txt_kembalian.setText(null);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);

            // Simpan stok barang sebelum mengosongkan tabel "keranjang"
            Map<String, Integer> stokSebelumnya = new HashMap<>();

            // Ambil data barang dari tabel "keranjang"
            String queryGetData = "SELECT id_barang, jumlah FROM keranjang";
            PreparedStatement psGetData = con.prepareStatement(queryGetData);
            ResultSet rsGetData = psGetData.executeQuery();
            while (rsGetData.next()) {
                String idBarang = rsGetData.getString("id_barang");
                int jumlah = rsGetData.getInt("jumlah");
                stokSebelumnya.put(idBarang, jumlah);
            }

            // Kosongkan tabel "keranjang"
            String queryTruncate = "TRUNCATE keranjang";
            PreparedStatement st = con.prepareStatement(queryTruncate);
            st.executeUpdate();

            // Pemulihan stok barang setelah mengosongkan tabel "keranjang"
            for (Map.Entry<String, Integer> entry : stokSebelumnya.entrySet()) {
                String idBarang = entry.getKey();
                int jumlahSebelumnya = entry.getValue();
                String queryUpdateStock = "UPDATE barang SET jumlah = jumlah + ? WHERE id_barang = ?";
                PreparedStatement psUpdateStock = con.prepareStatement(queryUpdateStock);
                psUpdateStock.setInt(1, jumlahSebelumnya);
                psUpdateStock.setString(2, idBarang);
                psUpdateStock.executeUpdate();
            }

            // Setelah menghapus data, hitung kembali total harga
            TotalHarga();

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            // Tampilkan data yang tersisa setelah menghapus
            tampilData();

            // Kosongkan teks uang dan kembalian
            txt_uang.setText(null);
            txt_kembalian.setText(null);
        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void Panel_TransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Panel_TransaksiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Panel_TransaksiMouseClicked

    private void table_barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_barangMouseClicked
        // TODO add your handling code here:

        Panel_Utama.removeAll();
        Panel_Utama.repaint();
        Panel_Utama.revalidate();

        Panel_Utama.add(Panel_Transaksi);
        Panel_Utama.repaint();
        Panel_Utama.revalidate();

        int selectedRow = table_barang.getSelectedRow();

        Field_Kode_Barang_Transaksi.setText(String.valueOf(table_barang.getValueAt(selectedRow, 0)));
        Field__Transaksi_Barang.setText(String.valueOf(table_barang.getValueAt(selectedRow, 1)));
        txt_harga2.setText("Rp. " + String.valueOf(table_barang.getValueAt(selectedRow, 4)));

        autoIn();
        autoInN();

    }//GEN-LAST:event_table_barangMouseClicked

    private void rSMaterialButtonRectangle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle3ActionPerformed
        // TODO add your handling code here:
        Panel_Utama.removeAll();
        Panel_Utama.repaint();
        Panel_Utama.revalidate();

        Panel_Utama.add(Panel_Transaksi);
        Panel_Utama.repaint();
        Panel_Utama.revalidate();
    }//GEN-LAST:event_rSMaterialButtonRectangle3ActionPerformed

    private void Field__Transaksi_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Field__Transaksi_IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Field__Transaksi_IDActionPerformed

    private void txt_jumlah3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jumlah3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jumlah3ActionPerformed

    private void txt_jumlah3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jumlah3KeyReleased
        // TODO add your handling code here:
        total();
    }//GEN-LAST:event_txt_jumlah3KeyReleased

    private void txt_jumlah3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jumlah3KeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_jumlah3KeyTyped

    private void Field_Keranjang_IDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Field_Keranjang_IDKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_Field_Keranjang_IDKeyTyped

    private void Field_Keranjang_IDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Field_Keranjang_IDKeyReleased
        // TODO add your handling code here:
        total();
    }//GEN-LAST:event_Field_Keranjang_IDKeyReleased

    private void Field_Keranjang_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Field_Keranjang_IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Field_Keranjang_IDActionPerformed

    private void txt_uangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_uangMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_uangMouseExited

    private void txt_uangMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_uangMouseReleased
        // TODO add your handling code here:
        txt_kembalian.setText("Rp. ");

    }//GEN-LAST:event_txt_uangMouseReleased

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void rSMaterialButtonRectangle4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonRectangle4ActionPerformed

    private void table_RiwayatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_RiwayatMouseClicked
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_table_RiwayatMouseClicked

    private void rSMaterialButtonRectangle5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle5ActionPerformed
        // TODO add your handling code here:
         int Row = table_Riwayat.getSelectedRow();
         String ID = (String) table_Riwayat.getValueAt(Row, 1);

            try {
                // Load laporan Jasper
                Class.forName(driver);
                con = DriverManager.getConnection(url, user, pwd);
                String file = "/Report/nota_r_1.jasper";

                // Buat parameter untuk laporan Jasper
                
                HashMap<String, Object> param = new HashMap<>();
                
                param.put("id_transaksi", new String(String.valueOf(table_Riwayat.getValueAt(Row, 1)))); // Set parameter id_transaksi dengan nilai yang dipilih dari tabel
                param.put("kasir", Lb_Nama_Kasir.getText());
                // Isi laporan Jasper dengan parameter
                JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream(file), param, con);
                
                // Tampilkan laporan dalam JasperViewer
                JasperViewer.viewReport(print, false);
            } catch (JRException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            } catch (ClassNotFoundException ex) {
                 Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
             } catch (SQLException ex) {
                 Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
             }
        
    
    }//GEN-LAST:event_rSMaterialButtonRectangle5ActionPerformed

    private void rSMaterialButtonRectangle6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle6ActionPerformed
        Panel_Utama.removeAll();
        Panel_Utama.repaint();
        Panel_Utama.revalidate();

        Panel_Utama.add(Panel_Transaksi);
        Panel_Utama.repaint();
        Panel_Utama.revalidate();
    }//GEN-LAST:event_rSMaterialButtonRectangle6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Panel_Utama.removeAll();
        Panel_Utama.repaint();
        Panel_Utama.revalidate();

        Panel_Utama.add(Panel_Detail_Riwayat);
        Panel_Utama.repaint();
        Panel_Utama.revalidate();
        getDataT();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void Field_Cari_StokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Field_Cari_StokActionPerformed
        Panel_Utama.removeAll();
        Panel_Utama.repaint();
        Panel_Utama.revalidate();

        Panel_Utama.add(Panel_Transaksi);
        Panel_Utama.repaint();
        Panel_Utama.revalidate();

        String id_barang = Field_Cari_Stok.getText();

        // Cari baris yang sesuai dalam table_barang
        for (int i = 0; i < table_barang.getRowCount(); i++) {
            if (id_barang.equals(String.valueOf(table_barang.getValueAt(i, 0)))) { // Menggunakan kolom pertama sebagai kolom ID barang, ganti dengan indeks kolom yang sesuai
                // Jika baris yang sesuai ditemukan, isi field-field dengan nilai-nilai yang ditemukan
                Field_Kode_Barang_Transaksi.setText(String.valueOf(table_barang.getValueAt(i, 0))); // Contoh: mengambil nilai dari kolom pertama
                Field__Transaksi_Barang.setText(String.valueOf(table_barang.getValueAt(i, 1))); // Contoh: mengambil nilai dari kolom kedua
                txt_harga2.setText("Rp. " + String.valueOf(table_barang.getValueAt(i, 4))); // Contoh: mengambil nilai dari kolom kelima
                return; // Keluar dari loop setelah menemukan baris yang sesuai
            }
        }


    }//GEN-LAST:event_Field_Cari_StokActionPerformed

    private void txt_uangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_uangMouseClicked
        // TODO add your handling code here:
        autoInN();

    }//GEN-LAST:event_txt_uangMouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        try {
            db.aksi("TRUNCATE TABLE keranjang");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Mengosongkan Keranjang: " + e.getMessage());
        }
        tampilData();
        txt_uang.setText(null);
        Field_Nama_Pelanggan.setText(null);
        txt_totalharga.setText(null);

    }//GEN-LAST:event_jButton9ActionPerformed

    private void Field_Total_SemuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Field_Total_SemuaKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_Field_Total_SemuaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Field_Cari_Stok;
    private javax.swing.JTextField Field_Cari_Transaksi;
    public javax.swing.JTextField Field_Keranjang_ID;
    public javax.swing.JTextField Field_Kode_Barang_Transaksi;
    private javax.swing.JComboBox<String> Field_Metode;
    private javax.swing.JTextField Field_Nama_Pelanggan;
    public static javax.swing.JTextField Field_Total_Semua;
    public javax.swing.JTextField Field__Transaksi_Barang;
    public javax.swing.JTextField Field__Transaksi_ID;
    private javax.swing.JLabel Lb_Nama_Kasir;
    private javax.swing.JPanel Panel_Detail_Riwayat;
    private javax.swing.JPanel Panel_Stok;
    private javax.swing.JPanel Panel_Transaksi;
    private javax.swing.JPanel Panel_Utama;
    private javax.swing.JTable Tbl_Detail_Transaksi;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle3;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle4;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle5;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle6;
    private javax.swing.JTable table_Riwayat;
    private javax.swing.JTable table_barang;
    private javax.swing.JTable tb_keranjang;
    private com.toedter.calendar.JDateChooser tgl_transaksi;
    public javax.swing.JTextField txt_harga2;
    public javax.swing.JTextField txt_jumlah3;
    public static javax.swing.JTextField txt_kembalian;
    public javax.swing.JTextField txt_totalharga;
    public static javax.swing.JTextField txt_uang;
    // End of variables declaration//GEN-END:variables

    private void pencarianData() {

    }

}
