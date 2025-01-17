/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Utama;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author acer
 */
public class Retur extends javax.swing.JPanel {

    Connection con;
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/tatatoko";
    private final String user = "root";
    private final String pwd = "";

    koneksi db = new koneksi();
    DefaultTableModel model = new DefaultTableModel();

    /**
     * Creates new form Beranda
     */
    public Retur() {
        initComponents();
        distributor();
        barang();
        tanggal();
        model.addColumn("No Return");
        model.addColumn("Tanggal");
        model.addColumn("Nama Barang");
        model.addColumn("Jumlah");
        model.addColumn("Distributor");
        model.addColumn("Keterangan");

        Tbl_Daftar_Retur.setModel(model);
        Tbl_Tambah_Retur.setModel(model);
        getData();
        autoIn();
        search();
    }

    public void tanggal() {
        Date now = new Date();
        Field_Retur_Tambah_Tanggal.setDate(now);
        Field_Dari_Tanggal.setDateFormatString(driver);
        Field_Sampai_Tanggal.setDateFormatString(driver);

    }

    private void getData() {
        ResultSet hasil = db.ambilData("SELECT * FROM retur");
        try {
            while (hasil.next()) {
                model.addRow(new Object[]{hasil.getString("id_return"),
                    hasil.getString("tanggal"),
                    hasil.getString("nama_barang"),
                    hasil.getString("jumlah"),
                    hasil.getString("distributor"),
                    hasil.getString("keterangan")});
            }
        } catch (Exception e) {
            System.out.println("Tidak Dapat Mengambil Data");
        }

    }

    private void getTanggal() {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String dari = date.format(Field_Dari_Tanggal.getDate());
        String sampai = date.format(Field_Sampai_Tanggal.getDate());

        // Menghapus semua baris yang ada di tabel sebelum menambahkan data baru
        model.setRowCount(0);

        // Mengambil data dari database berdasarkan tanggal
        ResultSet hasil = db.ambilData("SELECT * FROM retur WHERE tanggal BETWEEN '" + dari + "' AND '" + sampai + "'");
        try {
            while (hasil.next()) {
                model.addRow(new Object[]{
                    hasil.getString("id_return"),
                    hasil.getString("tanggal"),
                    hasil.getString("nama_barang"),
                    hasil.getString("jumlah"),
                    hasil.getString("distributor"),
                    hasil.getString("keterangan")
                });
            }
        } catch (Exception e) {
            System.out.println("Tidak Dapat Mengambil Data");
            e.printStackTrace();
        }
    }

    public void distributor() {
        ResultSet hasil = db.ambilData("SELECT * FROM distributor");
        try {
            while (hasil.next()) {
                // Ambil nilai dari kolom "nama" dan tambahkan ke dalam JComboBox
                Field_Retur_Tambah_Distributor.addItem(hasil.getString("nama"));
                Field_Daftar_Retur_Distributor.addItem(hasil.getString("nama"));

            }
        } catch (Exception e) {
            System.out.println("Tidak Dapat Mengambil Data");
        }

    }

    public void barang() {
        ResultSet hasil = db.ambilData("SELECT * FROM barang");
        try {
            while (hasil.next()) {
                Field_Retur_Tambah_Nama.addItem(hasil.getString("nama_barang"));
            }
        } catch (Exception e) {
        }
    }
    // Method to reduce the stock of an item

    private boolean kurangiStokBarang(String id_b, int jumlah) {
        try {
            // Get the current stock of the item from the database
            ResultSet rs = db.ambilData("SELECT jumlah FROM barang WHERE id_barang = '" + id_b + "'");
            int stokSaatIni = 0;
            if (rs.next()) {
                stokSaatIni = rs.getInt("jumlah");
            }

            // Calculate new stock after reducing the purchased amount
            int stokBaru = stokSaatIni - jumlah;

            // Check if the stock is sufficient
            if (stokBaru < 0) {
                JOptionPane.showMessageDialog(null, "Stok tidak cukup. Stok saat ini: " + stokSaatIni);
                return false;
            } else {
                // Update the stock of the item in the database
                db.aksi("UPDATE barang SET jumlah = " + stokBaru + " WHERE id_barang = '" + id_b + "'");
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mengurangi stok barang: " + e.getMessage());
            return false;
        }
    }

// Method to add data and reduce stock accordingly
    private void tambahData() {
        String kode = Field_Retur_Tambah_No_Transaksi.getText();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = String.valueOf(date.format(Field_Retur_Tambah_Tanggal.getDate()));
        String distributor = String.valueOf(Field_Retur_Tambah_Distributor.getSelectedItem());
        String nama = String.valueOf(Field_Retur_Tambah_Nama.getSelectedItem());
        String ket = Field_Retur_Tambah_Keterangan.getText();
        String jumlahStr = Field_Retur_Tambah_Jumlah.getText();

        try {
            // Assume id_barang is the ID of the selected item, you need to adjust it according to your application's logic
            String id_barang = getIdBarangByName(nama); // Implement this method to get the ID of the item based on the name

            int jumlah = Integer.parseInt(jumlahStr);

            // Insert retur data into the database
            db.aksi("INSERT INTO retur VALUES ('" + kode + "','" + tanggal + "','" + nama + "','" + jumlah + "','" + distributor + "','" + ket + "')");

            // Reduce item stock
            boolean success = kurangiStokBarang(id_barang, jumlah);
            if (success) {
                // If stock reduction is successful, refresh the table and clear the form
                model.setRowCount(0);
                Tbl_Daftar_Retur.setModel(model);
                Tbl_Tambah_Retur.setModel(model);
                getData();
                clear();
                JOptionPane.showMessageDialog(null, "Data Berhasil Di Retur !!!");
            } else {
                // If stock reduction fails, delete the recently inserted retur data
                db.aksi("DELETE FROM retur WHERE kode_transaksi = '" + kode + "'");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menambah data retur: " + e.getMessage());
        }
    }

// Method to get the ID of an item based on its name
    private String getIdBarangByName(String namaBarang) {
        try {
            ResultSet rs = db.ambilData("SELECT id_barang FROM barang WHERE nama_barang = '" + namaBarang + "'");
            if (rs.next()) {
                return rs.getString("id_barang");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mendapatkan ID barang: " + e.getMessage());
        }
        return null;
    }

    public void autoIn() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);

            String sqlquery = "SELECT MAX(RIGHT(id_return,4)) AS no_auto FROM retur";
            PreparedStatement st = con.prepareStatement(sqlquery);
            ResultSet rs = st.executeQuery();

            String no_a, no_p = "";
            int p;
            if (rs.next()) {
                no_a = Integer.toString(rs.getInt(1) + 1);
                p = no_a.length();

                for (int i = 1; i <= 4 - p; i++) {
                    no_p = no_p + "0";
                }
                Field_Retur_Tambah_No_Transaksi.setText(no_p + no_a);
            }

        } catch (Exception e) {
            Field_Retur_Tambah_No_Transaksi.setText("");
            e.printStackTrace();
        }

    }

    public void search() {
        Field_Cari_Tambah_Retur.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterData(Field_Cari_Tambah_Retur.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterData(Field_Cari_Tambah_Retur.getText()); // Call filterData on removeUpdate
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterData(Field_Cari_Tambah_Retur.getText()); // Call filterData on changedUpdate
            }

            private void filterData(String keyword) {
                TableRowSorter<TableModel> sorter = new TableRowSorter<>(Tbl_Tambah_Retur.getModel());
                Tbl_Tambah_Retur.setRowSorter(sorter);

                if (keyword.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword)); // Filter data sesuai dengan kata kunci (ignore case)
                }
            }
        });
        
        Field_Daftar_Retur_Kata_Kunci.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterData(Field_Daftar_Retur_Kata_Kunci.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterData(Field_Daftar_Retur_Kata_Kunci.getText()); // Call filterData on removeUpdate
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterData(Field_Daftar_Retur_Kata_Kunci.getText()); // Call filterData on changedUpdate
            }

            private void filterData(String keyword) {
                TableRowSorter<TableModel> sorter = new TableRowSorter<>(Tbl_Daftar_Retur.getModel());
                Tbl_Daftar_Retur.setRowSorter(sorter);

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

        pn_Retur = new javax.swing.JPanel();
        Lb_Retur = new javax.swing.JLabel();
        Btn_Daftar_Retur = new javax.swing.JButton();
        Btn_Retur_Tambah = new javax.swing.JButton();
        pn_Konten_Retur = new javax.swing.JPanel();
        pn_Daftar_Retur = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tbl_Daftar_Retur = new javax.swing.JTable();
        Lb_Daftar_Retur_Kata_Kunci = new javax.swing.JLabel();
        Field_Daftar_Retur_Kata_Kunci = new javax.swing.JTextField();
        Lb_Daftar_Retur_Dari_Tanggal = new javax.swing.JLabel();
        Lb_Daftar_Retur_Sampai_Tanggal = new javax.swing.JLabel();
        Lb_Daftar_Retur_Distributor = new javax.swing.JLabel();
        Field_Daftar_Retur_Distributor = new javax.swing.JComboBox<>();
        Btn_Daftar_Retur_Cari = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        Field_Dari_Tanggal = new com.toedter.calendar.JDateChooser();
        Field_Sampai_Tanggal = new com.toedter.calendar.JDateChooser();
        pn_Tambah_Retur = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tbl_Tambah_Retur = new javax.swing.JTable();
        Lb_Retur_Tambah = new javax.swing.JLabel();
        Lb_Retur_Tambah_No_Transaksi = new javax.swing.JLabel();
        Lb_Retur_Tambah_Tanggal = new javax.swing.JLabel();
        Lb_Retur_Tambah_Distributor = new javax.swing.JLabel();
        Field_Retur_Tambah_No_Transaksi = new javax.swing.JTextField();
        Field_Retur_Tambah_Distributor = new javax.swing.JComboBox<>();
        Lb_Retur_Tambah_Nama_Toko = new javax.swing.JLabel();
        Field_Retur_Tambah_Nama_Toko = new javax.swing.JTextField();
        Field_Cari_Tambah_Retur = new javax.swing.JTextField();
        Btn_Cari_Tambah_Retur = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        Field_Retur_Tambah_Keterangan = new javax.swing.JTextArea();
        Lb_Retur_Tambah_Keterangan = new javax.swing.JLabel();
        Btn_Tambah_Retur_Tambah_Baru = new javax.swing.JButton();
        Lb_Retur_Tambah_No_Transaksi1 = new javax.swing.JLabel();
        Field_Retur_Tambah_Nama = new javax.swing.JComboBox<>();
        Lb_Retur_Tambah_No_Transaksi2 = new javax.swing.JLabel();
        Field_Retur_Tambah_Jumlah = new javax.swing.JTextField();
        Field_Retur_Tambah_Tanggal = new com.toedter.calendar.JDateChooser();

        setPreferredSize(new java.awt.Dimension(1182, 686));

        pn_Retur.setBackground(new java.awt.Color(255, 255, 255));
        pn_Retur.setPreferredSize(new java.awt.Dimension(1182, 686));

        Lb_Retur.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        Lb_Retur.setText("Retur");

        Btn_Daftar_Retur.setBackground(new java.awt.Color(3, 0, 169));
        Btn_Daftar_Retur.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Btn_Daftar_Retur.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Daftar_Retur.setText("Daftar Retur");
        Btn_Daftar_Retur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Btn_Daftar_ReturMouseClicked(evt);
            }
        });

        Btn_Retur_Tambah.setBackground(new java.awt.Color(3, 0, 169));
        Btn_Retur_Tambah.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Btn_Retur_Tambah.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Retur_Tambah.setText("Tambah");
        Btn_Retur_Tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Btn_Retur_TambahMouseClicked(evt);
            }
        });

        pn_Konten_Retur.setLayout(new java.awt.CardLayout());

        pn_Daftar_Retur.setBackground(new java.awt.Color(255, 255, 255));

        Tbl_Daftar_Retur.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        Tbl_Daftar_Retur.setModel(new javax.swing.table.DefaultTableModel(
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
        Tbl_Daftar_Retur.setRowHeight(40);
        Tbl_Daftar_Retur.setSelectionBackground(new java.awt.Color(0, 0, 204));
        jScrollPane1.setViewportView(Tbl_Daftar_Retur);

        Lb_Daftar_Retur_Kata_Kunci.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Daftar_Retur_Kata_Kunci.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Daftar_Retur_Kata_Kunci.setText("Kata Kunci :");

        Field_Daftar_Retur_Kata_Kunci.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        Lb_Daftar_Retur_Dari_Tanggal.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Daftar_Retur_Dari_Tanggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Daftar_Retur_Dari_Tanggal.setText("Dari Tanggal :");

        Lb_Daftar_Retur_Sampai_Tanggal.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Daftar_Retur_Sampai_Tanggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Daftar_Retur_Sampai_Tanggal.setText("s/d");

        Lb_Daftar_Retur_Distributor.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Daftar_Retur_Distributor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Daftar_Retur_Distributor.setText("Distributor :");

        Field_Daftar_Retur_Distributor.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        Btn_Daftar_Retur_Cari.setBackground(new java.awt.Color(3, 0, 126));
        Btn_Daftar_Retur_Cari.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Daftar_Retur_Cari.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Daftar_Retur_Cari.setText("Cari");
        Btn_Daftar_Retur_Cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Daftar_Retur_CariActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Daftar Retur");

        Field_Dari_Tanggal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        Field_Sampai_Tanggal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        javax.swing.GroupLayout pn_Daftar_ReturLayout = new javax.swing.GroupLayout(pn_Daftar_Retur);
        pn_Daftar_Retur.setLayout(pn_Daftar_ReturLayout);
        pn_Daftar_ReturLayout.setHorizontalGroup(
            pn_Daftar_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Daftar_ReturLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(567, 567, 567))
            .addGroup(pn_Daftar_ReturLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pn_Daftar_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Daftar_ReturLayout.createSequentialGroup()
                        .addGroup(pn_Daftar_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Lb_Daftar_Retur_Dari_Tanggal)
                            .addComponent(Lb_Daftar_Retur_Kata_Kunci)
                            .addComponent(Lb_Daftar_Retur_Distributor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn_Daftar_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_Daftar_ReturLayout.createSequentialGroup()
                                .addComponent(Field_Daftar_Retur_Kata_Kunci, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Btn_Daftar_Retur_Cari))
                            .addGroup(pn_Daftar_ReturLayout.createSequentialGroup()
                                .addGroup(pn_Daftar_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Field_Daftar_Retur_Distributor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Field_Dari_Tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(Lb_Daftar_Retur_Sampai_Tanggal)
                                .addGap(26, 26, 26)
                                .addComponent(Field_Sampai_Tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(335, 335, 335))
                    .addGroup(pn_Daftar_ReturLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        pn_Daftar_ReturLayout.setVerticalGroup(
            pn_Daftar_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Daftar_ReturLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(pn_Daftar_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lb_Daftar_Retur_Kata_Kunci)
                    .addComponent(Field_Daftar_Retur_Kata_Kunci, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Daftar_Retur_Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pn_Daftar_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Daftar_ReturLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(pn_Daftar_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Lb_Daftar_Retur_Sampai_Tanggal, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Lb_Daftar_Retur_Dari_Tanggal))
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Daftar_ReturLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(pn_Daftar_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Field_Sampai_Tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Field_Dari_Tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)))
                .addGroup(pn_Daftar_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Field_Daftar_Retur_Distributor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lb_Daftar_Retur_Distributor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(71, 71, 71)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149))
        );

        pn_Konten_Retur.add(pn_Daftar_Retur, "card2");

        pn_Tambah_Retur.setBackground(new java.awt.Color(255, 255, 255));

        Tbl_Tambah_Retur.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Tbl_Tambah_Retur.setModel(new javax.swing.table.DefaultTableModel(
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
        Tbl_Tambah_Retur.setRowHeight(35);
        Tbl_Tambah_Retur.setSelectionBackground(new java.awt.Color(0, 0, 204));
        jScrollPane2.setViewportView(Tbl_Tambah_Retur);

        Lb_Retur_Tambah.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        Lb_Retur_Tambah.setText("Tambah");

        Lb_Retur_Tambah_No_Transaksi.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Retur_Tambah_No_Transaksi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Retur_Tambah_No_Transaksi.setText("No Retur         :");

        Lb_Retur_Tambah_Tanggal.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Retur_Tambah_Tanggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Retur_Tambah_Tanggal.setText("Tanggal :");

        Lb_Retur_Tambah_Distributor.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Retur_Tambah_Distributor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Retur_Tambah_Distributor.setText("Distributor      :");

        Field_Retur_Tambah_No_Transaksi.setEnabled(false);

        Field_Retur_Tambah_Distributor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Field_Retur_Tambah_Distributor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--default--" }));
        Field_Retur_Tambah_Distributor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Field_Retur_Tambah_DistributorActionPerformed(evt);
            }
        });

        Lb_Retur_Tambah_Nama_Toko.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Retur_Tambah_Nama_Toko.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Retur_Tambah_Nama_Toko.setText("Dari :");

        Field_Retur_Tambah_Nama_Toko.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Field_Retur_Tambah_Nama_Toko.setText("KANTOR SAYUR");

        Btn_Cari_Tambah_Retur.setBackground(new java.awt.Color(0, 0, 153));
        Btn_Cari_Tambah_Retur.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Cari_Tambah_Retur.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Cari_Tambah_Retur.setText("Cari");
        Btn_Cari_Tambah_Retur.setMaximumSize(new java.awt.Dimension(82, 21));
        Btn_Cari_Tambah_Retur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Cari_Tambah_ReturActionPerformed(evt);
            }
        });

        Field_Retur_Tambah_Keterangan.setColumns(20);
        Field_Retur_Tambah_Keterangan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Field_Retur_Tambah_Keterangan.setRows(5);
        jScrollPane3.setViewportView(Field_Retur_Tambah_Keterangan);

        Lb_Retur_Tambah_Keterangan.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Retur_Tambah_Keterangan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Retur_Tambah_Keterangan.setText("Keterangan :");

        Btn_Tambah_Retur_Tambah_Baru.setBackground(new java.awt.Color(3, 0, 169));
        Btn_Tambah_Retur_Tambah_Baru.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Btn_Tambah_Retur_Tambah_Baru.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Tambah_Retur_Tambah_Baru.setText("Tambah");
        Btn_Tambah_Retur_Tambah_Baru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Tambah_Retur_Tambah_BaruActionPerformed(evt);
            }
        });

        Lb_Retur_Tambah_No_Transaksi1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Retur_Tambah_No_Transaksi1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Retur_Tambah_No_Transaksi1.setText("Nama Barang  :");

        Field_Retur_Tambah_Nama.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Field_Retur_Tambah_Nama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--default--" }));

        Lb_Retur_Tambah_No_Transaksi2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Retur_Tambah_No_Transaksi2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Retur_Tambah_No_Transaksi2.setText("Jumlah :");

        Field_Retur_Tambah_Jumlah.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        Field_Retur_Tambah_Tanggal.setDateFormatString("dd-MM-yyyy");
        Field_Retur_Tambah_Tanggal.setEnabled(false);
        Field_Retur_Tambah_Tanggal.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        javax.swing.GroupLayout pn_Tambah_ReturLayout = new javax.swing.GroupLayout(pn_Tambah_Retur);
        pn_Tambah_Retur.setLayout(pn_Tambah_ReturLayout);
        pn_Tambah_ReturLayout.setHorizontalGroup(
            pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                                .addComponent(Lb_Retur_Tambah_No_Transaksi)
                                .addGap(15, 15, 15)
                                .addComponent(Field_Retur_Tambah_No_Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                                .addComponent(Lb_Retur_Tambah_No_Transaksi1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Field_Retur_Tambah_Nama, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                                .addComponent(Lb_Retur_Tambah_Distributor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Field_Retur_Tambah_Distributor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)))
                        .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                                .addGap(415, 415, 415)
                                .addComponent(Lb_Retur_Tambah_Nama_Toko)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Field_Retur_Tambah_Nama_Toko, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Lb_Retur_Tambah_Tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Field_Retur_Tambah_Tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59))
                            .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addComponent(Lb_Retur_Tambah_No_Transaksi2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Field_Retur_Tambah_Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                        .addComponent(Lb_Retur_Tambah)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Tambah_ReturLayout.createSequentialGroup()
                        .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(Field_Cari_Tambah_Retur, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Btn_Cari_Tambah_Retur, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2)
                            .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                                .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                                        .addGap(90, 90, 90)
                                        .addComponent(Btn_Tambah_Retur_Tambah_Baru, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(Lb_Retur_Tambah_Keterangan)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(433, 433, 433)))
                        .addGap(43, 43, 43))))
        );
        pn_Tambah_ReturLayout.setVerticalGroup(
            pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Lb_Retur_Tambah)
                .addGap(30, 30, 30)
                .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Lb_Retur_Tambah_Nama_Toko)
                        .addComponent(Field_Retur_Tambah_Nama_Toko, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(Lb_Retur_Tambah_Tanggal)
                        .addComponent(Field_Retur_Tambah_Tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                        .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Field_Retur_Tambah_No_Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Lb_Retur_Tambah_No_Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Lb_Retur_Tambah_Distributor)
                            .addComponent(Field_Retur_Tambah_Distributor, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Field_Retur_Tambah_Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Lb_Retur_Tambah_No_Transaksi2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Lb_Retur_Tambah_No_Transaksi1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Field_Retur_Tambah_Nama, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Field_Cari_Tambah_Retur, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Cari_Tambah_Retur, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                        .addComponent(Lb_Retur_Tambah_Keterangan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Btn_Tambah_Retur_Tambah_Baru, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(88, 88, 88))
        );

        pn_Konten_Retur.add(pn_Tambah_Retur, "card2");

        javax.swing.GroupLayout pn_ReturLayout = new javax.swing.GroupLayout(pn_Retur);
        pn_Retur.setLayout(pn_ReturLayout);
        pn_ReturLayout.setHorizontalGroup(
            pn_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_ReturLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(pn_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pn_Konten_Retur, javax.swing.GroupLayout.PREFERRED_SIZE, 1144, Short.MAX_VALUE)
                    .addGroup(pn_ReturLayout.createSequentialGroup()
                        .addGroup(pn_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Lb_Retur)
                            .addGroup(pn_ReturLayout.createSequentialGroup()
                                .addComponent(Btn_Daftar_Retur, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Btn_Retur_Tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pn_ReturLayout.setVerticalGroup(
            pn_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_ReturLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(Lb_Retur)
                .addGap(55, 55, 55)
                .addGroup(pn_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_Daftar_Retur, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Retur_Tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pn_Konten_Retur, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_Retur, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pn_Retur, javax.swing.GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_Daftar_ReturMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Btn_Daftar_ReturMouseClicked
        pn_Konten_Retur.removeAll();
        pn_Konten_Retur.repaint();
        pn_Konten_Retur.revalidate();

        pn_Konten_Retur.add(pn_Daftar_Retur);
        pn_Konten_Retur.repaint();
        pn_Konten_Retur.revalidate();
    }//GEN-LAST:event_Btn_Daftar_ReturMouseClicked

    private void Btn_Cari_Tambah_ReturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Cari_Tambah_ReturActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_Cari_Tambah_ReturActionPerformed

    private void Btn_Retur_TambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Btn_Retur_TambahMouseClicked
        pn_Konten_Retur.removeAll();
        pn_Konten_Retur.repaint();
        pn_Konten_Retur.revalidate();

        pn_Konten_Retur.add(pn_Tambah_Retur);
        pn_Konten_Retur.repaint();
        pn_Konten_Retur.revalidate();
    }//GEN-LAST:event_Btn_Retur_TambahMouseClicked

    private void Btn_Tambah_Retur_Tambah_BaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Tambah_Retur_Tambah_BaruActionPerformed
        // TODO add your handling code here:
        tambahData();

    }//GEN-LAST:event_Btn_Tambah_Retur_Tambah_BaruActionPerformed

    private void Field_Retur_Tambah_DistributorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Field_Retur_Tambah_DistributorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Field_Retur_Tambah_DistributorActionPerformed

    private void Btn_Daftar_Retur_CariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Daftar_Retur_CariActionPerformed
        // TODO add your handling code here:
        getTanggal();

    }//GEN-LAST:event_Btn_Daftar_Retur_CariActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Cari_Tambah_Retur;
    private javax.swing.JButton Btn_Daftar_Retur;
    private javax.swing.JButton Btn_Daftar_Retur_Cari;
    private javax.swing.JButton Btn_Retur_Tambah;
    private javax.swing.JButton Btn_Tambah_Retur_Tambah_Baru;
    private javax.swing.JTextField Field_Cari_Tambah_Retur;
    private javax.swing.JComboBox<String> Field_Daftar_Retur_Distributor;
    private javax.swing.JTextField Field_Daftar_Retur_Kata_Kunci;
    private com.toedter.calendar.JDateChooser Field_Dari_Tanggal;
    private javax.swing.JComboBox<String> Field_Retur_Tambah_Distributor;
    private javax.swing.JTextField Field_Retur_Tambah_Jumlah;
    private javax.swing.JTextArea Field_Retur_Tambah_Keterangan;
    private javax.swing.JComboBox<String> Field_Retur_Tambah_Nama;
    private javax.swing.JTextField Field_Retur_Tambah_Nama_Toko;
    private javax.swing.JTextField Field_Retur_Tambah_No_Transaksi;
    private com.toedter.calendar.JDateChooser Field_Retur_Tambah_Tanggal;
    private com.toedter.calendar.JDateChooser Field_Sampai_Tanggal;
    private javax.swing.JLabel Lb_Daftar_Retur_Dari_Tanggal;
    private javax.swing.JLabel Lb_Daftar_Retur_Distributor;
    private javax.swing.JLabel Lb_Daftar_Retur_Kata_Kunci;
    private javax.swing.JLabel Lb_Daftar_Retur_Sampai_Tanggal;
    private javax.swing.JLabel Lb_Retur;
    private javax.swing.JLabel Lb_Retur_Tambah;
    private javax.swing.JLabel Lb_Retur_Tambah_Distributor;
    private javax.swing.JLabel Lb_Retur_Tambah_Keterangan;
    private javax.swing.JLabel Lb_Retur_Tambah_Nama_Toko;
    private javax.swing.JLabel Lb_Retur_Tambah_No_Transaksi;
    private javax.swing.JLabel Lb_Retur_Tambah_No_Transaksi1;
    private javax.swing.JLabel Lb_Retur_Tambah_No_Transaksi2;
    private javax.swing.JLabel Lb_Retur_Tambah_Tanggal;
    private javax.swing.JTable Tbl_Daftar_Retur;
    private javax.swing.JTable Tbl_Tambah_Retur;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pn_Daftar_Retur;
    private javax.swing.JPanel pn_Konten_Retur;
    private javax.swing.JPanel pn_Retur;
    private javax.swing.JPanel pn_Tambah_Retur;
    // End of variables declaration//GEN-END:variables

    private void clear() {
        Field_Retur_Tambah_No_Transaksi.setText(null);

        Field_Retur_Tambah_Keterangan.setText(null);
        Field_Retur_Tambah_Keterangan.setText(null);
    }
}
