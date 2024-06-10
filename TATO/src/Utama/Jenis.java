/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Utama;

import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author acer
 */
public class Jenis extends javax.swing.JPanel {
    
    Connection con;
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/tatatoko";
    private final String user = "root";
    private final String pwd = "";
    koneksi db = new koneksi();
    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel model2 = new DefaultTableModel();
    DefaultTableModel model3 = new DefaultTableModel();

    
    
    public Jenis() {
        initComponents();
        
        //model.addColumn("No");
        model.addColumn("Nama Kategori");
        model.addColumn("Kode Unik");
        model.addColumn("Status");
        Tbl_Daftar_Kategori.setModel(model);
        getData();

        //model2.addColumn("No");
        model2.addColumn("Nama Satuan");
        model2.addColumn("Status");
        Tbl_Daftar_Satuan.setModel(model2);
        getDataS();
        
        //model3.addColumn("No");
        model3.addColumn("Nama Status");
        model3.addColumn("Kategori");
        Tbl_Daftar_Status.setModel(model3);
        getDataSt();
    }

    private void getData(){
        ResultSet hasil = db.ambilData("SELECT * FROM kategori");
        try {
            while(hasil.next()){
                model.addRow(new Object[]{
                hasil.getString("nama"),
                hasil.getString("kode_unik"),
                hasil.getString("status"),});
            }
        } catch (Exception e) {
            System.out.println("Tidak Dapat Mengambil Data");
        }
            
    }
    
    private void getDataS(){
        ResultSet hasil = db.ambilData("SELECT * FROM satuan");
        try {
            while(hasil.next()){
                model2.addRow(new Object[]{
                hasil.getString("nama"),
                hasil.getString("status"),});
            }
        } catch (Exception e) {
            System.out.println("Tidak Dapat Mengambil Data");
        }
            
    }
    
    private void getDataSt(){
        ResultSet hasil = db.ambilData("SELECT * FROM status");
        try {
            while(hasil.next()){
                model3.addRow(new Object[]{
                hasil.getString("nama"),
                hasil.getString("kategori"),});
            }
        } catch (Exception e) {
            System.out.println("Tidak Dapat Mengambil Data");
        }
            
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_Konten_Barang = new javax.swing.JPanel();
        pn__Jenis_Detail = new javax.swing.JPanel();
        Lb_Jenis = new javax.swing.JLabel();
        pn_Ctrl_Jenis = new javax.swing.JPanel();
        Btn_Menu_Kategori = new rojerusan.RSMaterialButtonRectangle();
        Btn_Menu_Satuan = new rojerusan.RSMaterialButtonRectangle();
        Btn_Menu_Status = new rojerusan.RSMaterialButtonRectangle();
        pn_Konten_Jenis = new javax.swing.JPanel();
        pn_Tbl_Jenis_Kategori = new javax.swing.JPanel();
        jScrollPane_Tbl_Kategori = new javax.swing.JScrollPane();
        Tbl_Daftar_Kategori = new javax.swing.JTable();
        Lb_Daftar_Kategori = new javax.swing.JLabel();
        Lb_Kategori = new javax.swing.JLabel();
        Field_Kategori_Cari = new javax.swing.JTextField();
        Btn_Kategori_Cari = new javax.swing.JButton();
        Lb_Kategori_Aksi_Kode_Unik1 = new javax.swing.JLabel();
        Field_Kategori_Kode_Unik = new javax.swing.JTextField();
        Lb_Aksi_Kategori_Nama_Kategori1 = new javax.swing.JLabel();
        Field_Kategori_Nama_Kategori = new javax.swing.JTextField();
        Lb_Aksi_Kategori_Status1 = new javax.swing.JLabel();
        Field_Kategori_Status = new javax.swing.JComboBox<>();
        Btn_Kategori_Simpan = new rojerusan.RSMaterialButtonRectangle();
        Btn_Kategori_Hapus = new rojerusan.RSMaterialButtonRectangle();
        pn_Tbl_Jenis_Satuan = new javax.swing.JPanel();
        jScrollPane_Tbl_Satuan = new javax.swing.JScrollPane();
        Tbl_Daftar_Satuan = new javax.swing.JTable();
        Lb_Daftar_Satuan = new javax.swing.JLabel();
        Lb_Satuan = new javax.swing.JLabel();
        Field_Satuan_Cari = new javax.swing.JTextField();
        Btn_Satuan_Cari = new javax.swing.JButton();
        Lb_Aksi_Satuan_Nama_Satuan1 = new javax.swing.JLabel();
        Field_Satuan_Nama_Satuan = new javax.swing.JTextField();
        Field_Satuan_Status = new javax.swing.JComboBox<>();
        Lb_Aksi_Satuan_Status1 = new javax.swing.JLabel();
        Btn_Satuan_Tambah = new rojerusan.RSMaterialButtonRectangle();
        Btn_Satuan_Hapus = new rojerusan.RSMaterialButtonRectangle();
        pn_Tbl_Jenis_Status = new javax.swing.JPanel();
        jScrollPane_Tbl_Status = new javax.swing.JScrollPane();
        Tbl_Daftar_Status = new javax.swing.JTable();
        Btn_Status_Tambah = new rojerusan.RSMaterialButtonRectangle();
        Lb_Kategori2 = new javax.swing.JLabel();
        Btn_Status_Hapus = new rojerusan.RSMaterialButtonRectangle();
        Lb_Status_Nama_Status = new javax.swing.JLabel();
        Field_Nama_status = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        Field_Status_Kategori = new javax.swing.JComboBox<>();
        Field_Status_Cari = new javax.swing.JTextField();
        Btn_Status_Cari = new javax.swing.JButton();
        Lb_Daftar_Status = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1150, 683));
        setLayout(new java.awt.CardLayout());

        pn_Konten_Barang.setPreferredSize(new java.awt.Dimension(1150, 683));
        pn_Konten_Barang.setLayout(new java.awt.CardLayout());

        pn__Jenis_Detail.setBackground(new java.awt.Color(255, 255, 255));
        pn__Jenis_Detail.setPreferredSize(new java.awt.Dimension(1150, 683));

        Lb_Jenis.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        Lb_Jenis.setText("Jenis");

        pn_Ctrl_Jenis.setBackground(new java.awt.Color(215, 215, 215));

        Btn_Menu_Kategori.setBackground(new java.awt.Color(3, 0, 126));
        Btn_Menu_Kategori.setText("Kategori");
        Btn_Menu_Kategori.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Menu_Kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Menu_KategoriActionPerformed(evt);
            }
        });

        Btn_Menu_Satuan.setBackground(new java.awt.Color(3, 0, 126));
        Btn_Menu_Satuan.setText("Satuan");
        Btn_Menu_Satuan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Menu_Satuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Menu_SatuanActionPerformed(evt);
            }
        });

        Btn_Menu_Status.setBackground(new java.awt.Color(3, 0, 126));
        Btn_Menu_Status.setText("Status");
        Btn_Menu_Status.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Menu_Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Menu_StatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_Ctrl_JenisLayout = new javax.swing.GroupLayout(pn_Ctrl_Jenis);
        pn_Ctrl_Jenis.setLayout(pn_Ctrl_JenisLayout);
        pn_Ctrl_JenisLayout.setHorizontalGroup(
            pn_Ctrl_JenisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Ctrl_JenisLayout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(Btn_Menu_Kategori, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(70, 70, 70)
                .addComponent(Btn_Menu_Satuan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(70, 70, 70)
                .addComponent(Btn_Menu_Status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(137, 137, 137))
        );
        pn_Ctrl_JenisLayout.setVerticalGroup(
            pn_Ctrl_JenisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Ctrl_JenisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_Ctrl_JenisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_Menu_Satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(Btn_Menu_Status, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(Btn_Menu_Kategori, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
                .addContainerGap())
        );

        pn_Konten_Jenis.setLayout(new java.awt.CardLayout());

        Tbl_Daftar_Kategori.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        Tbl_Daftar_Kategori.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No", "Nama Kategori", "Kode Unik", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tbl_Daftar_Kategori.setRowHeight(30);
        jScrollPane_Tbl_Kategori.setViewportView(Tbl_Daftar_Kategori);

        Lb_Daftar_Kategori.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Daftar_Kategori.setText("Daftar Kategori");

        Lb_Kategori.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        Lb_Kategori.setText("Kategori");

        Btn_Kategori_Cari.setBackground(new java.awt.Color(3, 0, 126));
        Btn_Kategori_Cari.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        Btn_Kategori_Cari.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Kategori_Cari.setText("Cari");

        Lb_Kategori_Aksi_Kode_Unik1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Kategori_Aksi_Kode_Unik1.setText("Kode Unik");

        Field_Kategori_Kode_Unik.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        Lb_Aksi_Kategori_Nama_Kategori1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Aksi_Kategori_Nama_Kategori1.setText("Nama Kategori");

        Field_Kategori_Nama_Kategori.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        Lb_Aksi_Kategori_Status1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Aksi_Kategori_Status1.setText("Status");

        Field_Kategori_Status.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Field_Kategori_Status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Barang" }));

        Btn_Kategori_Simpan.setBackground(new java.awt.Color(3, 0, 126));
        Btn_Kategori_Simpan.setText("Simpan");
        Btn_Kategori_Simpan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Kategori_Simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Kategori_SimpanActionPerformed(evt);
            }
        });

        Btn_Kategori_Hapus.setBackground(new java.awt.Color(255, 0, 0));
        Btn_Kategori_Hapus.setText("Hapus");
        Btn_Kategori_Hapus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Kategori_Hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Kategori_HapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_Tbl_Jenis_KategoriLayout = new javax.swing.GroupLayout(pn_Tbl_Jenis_Kategori);
        pn_Tbl_Jenis_Kategori.setLayout(pn_Tbl_Jenis_KategoriLayout);
        pn_Tbl_Jenis_KategoriLayout.setHorizontalGroup(
            pn_Tbl_Jenis_KategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Tbl_Jenis_KategoriLayout.createSequentialGroup()
                .addGroup(pn_Tbl_Jenis_KategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Tbl_Jenis_KategoriLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(Lb_Kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_Tbl_Jenis_KategoriLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(pn_Tbl_Jenis_KategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_Tbl_Jenis_KategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(pn_Tbl_Jenis_KategoriLayout.createSequentialGroup()
                                    .addComponent(Lb_Aksi_Kategori_Nama_Kategori1)
                                    .addGap(447, 447, 447))
                                .addGroup(pn_Tbl_Jenis_KategoriLayout.createSequentialGroup()
                                    .addComponent(Field_Kategori_Nama_Kategori)
                                    .addGap(262, 262, 262)))
                            .addGroup(pn_Tbl_Jenis_KategoriLayout.createSequentialGroup()
                                .addGroup(pn_Tbl_Jenis_KategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Lb_Aksi_Kategori_Status1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Lb_Kategori_Aksi_Kode_Unik1)
                                    .addComponent(Field_Kategori_Kode_Unik, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Field_Kategori_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Btn_Kategori_Simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Btn_Kategori_Hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(262, 262, 262)))
                        .addGroup(pn_Tbl_Jenis_KategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_Tbl_Jenis_KategoriLayout.createSequentialGroup()
                                .addComponent(Field_Kategori_Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Btn_Kategori_Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(279, 279, 279)
                                .addComponent(Lb_Daftar_Kategori))
                            .addComponent(jScrollPane_Tbl_Kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(121, Short.MAX_VALUE))
        );
        pn_Tbl_Jenis_KategoriLayout.setVerticalGroup(
            pn_Tbl_Jenis_KategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Tbl_Jenis_KategoriLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(Lb_Kategori)
                .addGroup(pn_Tbl_Jenis_KategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Tbl_Jenis_KategoriLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn_Tbl_Jenis_KategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Field_Kategori_Cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Btn_Kategori_Cari)
                            .addComponent(Lb_Daftar_Kategori))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane_Tbl_Kategori, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                        .addGap(79, 79, 79))
                    .addGroup(pn_Tbl_Jenis_KategoriLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(Lb_Kategori_Aksi_Kode_Unik1)
                        .addGap(8, 8, 8)
                        .addComponent(Field_Kategori_Kode_Unik, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(Lb_Aksi_Kategori_Nama_Kategori1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Field_Kategori_Nama_Kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Lb_Aksi_Kategori_Status1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Field_Kategori_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(Btn_Kategori_Simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(Btn_Kategori_Hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))))
        );

        pn_Konten_Jenis.add(pn_Tbl_Jenis_Kategori, "card2");

        Tbl_Daftar_Satuan.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        Tbl_Daftar_Satuan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "No", "Nama Satuan", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tbl_Daftar_Satuan.setRowHeight(30);
        jScrollPane_Tbl_Satuan.setViewportView(Tbl_Daftar_Satuan);

        Lb_Daftar_Satuan.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Daftar_Satuan.setText("Daftar Satuan");

        Lb_Satuan.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        Lb_Satuan.setText("Satuan");

        Btn_Satuan_Cari.setBackground(new java.awt.Color(3, 0, 126));
        Btn_Satuan_Cari.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Satuan_Cari.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Satuan_Cari.setText("Cari");

        Lb_Aksi_Satuan_Nama_Satuan1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Aksi_Satuan_Nama_Satuan1.setText("Nama Satuan");

        Field_Satuan_Nama_Satuan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        Field_Satuan_Status.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Field_Satuan_Status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Barang" }));

        Lb_Aksi_Satuan_Status1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Aksi_Satuan_Status1.setText("Status");

        Btn_Satuan_Tambah.setBackground(new java.awt.Color(3, 0, 126));
        Btn_Satuan_Tambah.setText("Tambahkan");
        Btn_Satuan_Tambah.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Satuan_Tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Satuan_TambahActionPerformed(evt);
            }
        });

        Btn_Satuan_Hapus.setBackground(new java.awt.Color(255, 0, 0));
        Btn_Satuan_Hapus.setText("Hapus");
        Btn_Satuan_Hapus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Satuan_Hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Satuan_HapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_Tbl_Jenis_SatuanLayout = new javax.swing.GroupLayout(pn_Tbl_Jenis_Satuan);
        pn_Tbl_Jenis_Satuan.setLayout(pn_Tbl_Jenis_SatuanLayout);
        pn_Tbl_Jenis_SatuanLayout.setHorizontalGroup(
            pn_Tbl_Jenis_SatuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Tbl_Jenis_SatuanLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pn_Tbl_Jenis_SatuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Tbl_Jenis_SatuanLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(pn_Tbl_Jenis_SatuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_Tbl_Jenis_SatuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Lb_Aksi_Satuan_Status1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Field_Satuan_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Field_Satuan_Nama_Satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Lb_Aksi_Satuan_Nama_Satuan1))
                            .addComponent(Btn_Satuan_Hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Btn_Satuan_Tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(259, 259, Short.MAX_VALUE)
                        .addGroup(pn_Tbl_Jenis_SatuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pn_Tbl_Jenis_SatuanLayout.createSequentialGroup()
                                .addComponent(Lb_Daftar_Satuan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Field_Satuan_Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Btn_Satuan_Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane_Tbl_Satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 859, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(557, 557, 557))
                    .addGroup(pn_Tbl_Jenis_SatuanLayout.createSequentialGroup()
                        .addComponent(Lb_Satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pn_Tbl_Jenis_SatuanLayout.setVerticalGroup(
            pn_Tbl_Jenis_SatuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Tbl_Jenis_SatuanLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(Lb_Satuan)
                .addGap(41, 41, 41)
                .addGroup(pn_Tbl_Jenis_SatuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Tbl_Jenis_SatuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Field_Satuan_Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Btn_Satuan_Cari))
                    .addComponent(Lb_Daftar_Satuan, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_Tbl_Jenis_SatuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Tbl_Jenis_SatuanLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(Lb_Aksi_Satuan_Nama_Satuan1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Field_Satuan_Nama_Satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(Lb_Aksi_Satuan_Status1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Field_Satuan_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111)
                        .addComponent(Btn_Satuan_Tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(Btn_Satuan_Hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane_Tbl_Satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(104, 104, 104))
        );

        pn_Konten_Jenis.add(pn_Tbl_Jenis_Satuan, "card2");

        Tbl_Daftar_Status.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        Tbl_Daftar_Status.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "No", "Nama Status", "Kategori"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tbl_Daftar_Status.setRowHeight(30);
        jScrollPane_Tbl_Status.setViewportView(Tbl_Daftar_Status);

        Btn_Status_Tambah.setBackground(new java.awt.Color(3, 0, 126));
        Btn_Status_Tambah.setText("Tambahkan");
        Btn_Status_Tambah.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Status_Tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Status_TambahActionPerformed(evt);
            }
        });

        Lb_Kategori2.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        Lb_Kategori2.setText("Status");

        Btn_Status_Hapus.setBackground(new java.awt.Color(255, 0, 0));
        Btn_Status_Hapus.setText("Hapus");
        Btn_Status_Hapus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Status_Hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Status_HapusActionPerformed(evt);
            }
        });

        Lb_Status_Nama_Status.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Status_Nama_Status.setText("Nama Status");

        Field_Nama_status.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Kategori");

        Field_Status_Kategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih--", "Barang", "Distributor" }));

        Btn_Status_Cari.setBackground(new java.awt.Color(3, 0, 126));
        Btn_Status_Cari.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Status_Cari.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Status_Cari.setText("Cari");
        Btn_Status_Cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Status_CariActionPerformed(evt);
            }
        });

        Lb_Daftar_Status.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Daftar_Status.setText("Daftar Status");

        javax.swing.GroupLayout pn_Tbl_Jenis_StatusLayout = new javax.swing.GroupLayout(pn_Tbl_Jenis_Status);
        pn_Tbl_Jenis_Status.setLayout(pn_Tbl_Jenis_StatusLayout);
        pn_Tbl_Jenis_StatusLayout.setHorizontalGroup(
            pn_Tbl_Jenis_StatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Tbl_Jenis_StatusLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pn_Tbl_Jenis_StatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Tbl_Jenis_StatusLayout.createSequentialGroup()
                        .addComponent(Lb_Kategori2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 1459, Short.MAX_VALUE))
                    .addGroup(pn_Tbl_Jenis_StatusLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(pn_Tbl_Jenis_StatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Field_Status_Kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Btn_Status_Hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Lb_Status_Nama_Status)
                            .addComponent(Field_Nama_status, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Btn_Status_Tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pn_Tbl_Jenis_StatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane_Tbl_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pn_Tbl_Jenis_StatusLayout.createSequentialGroup()
                                .addComponent(Lb_Daftar_Status)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Field_Status_Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Btn_Status_Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(134, 134, 134))))
        );
        pn_Tbl_Jenis_StatusLayout.setVerticalGroup(
            pn_Tbl_Jenis_StatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Tbl_Jenis_StatusLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(Lb_Kategori2)
                .addGroup(pn_Tbl_Jenis_StatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Tbl_Jenis_StatusLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(Lb_Status_Nama_Status)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Field_Nama_status, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Field_Status_Kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(Btn_Status_Tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(Btn_Status_Hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pn_Tbl_Jenis_StatusLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn_Tbl_Jenis_StatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Field_Status_Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Btn_Status_Cari)
                            .addComponent(Lb_Daftar_Status))
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane_Tbl_Status, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                        .addGap(49, 49, 49))))
        );

        pn_Konten_Jenis.add(pn_Tbl_Jenis_Status, "card2");

        javax.swing.GroupLayout pn__Jenis_DetailLayout = new javax.swing.GroupLayout(pn__Jenis_Detail);
        pn__Jenis_Detail.setLayout(pn__Jenis_DetailLayout);
        pn__Jenis_DetailLayout.setHorizontalGroup(
            pn__Jenis_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn__Jenis_DetailLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(Lb_Jenis)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pn__Jenis_DetailLayout.createSequentialGroup()
                .addGap(0, 108, Short.MAX_VALUE)
                .addGroup(pn__Jenis_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pn_Konten_Jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 1596, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pn_Ctrl_Jenis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        pn__Jenis_DetailLayout.setVerticalGroup(
            pn__Jenis_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn__Jenis_DetailLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(Lb_Jenis)
                .addGap(85, 85, 85)
                .addComponent(pn_Ctrl_Jenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pn_Konten_Jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        pn_Konten_Barang.add(pn__Jenis_Detail, "card2");

        add(pn_Konten_Barang, "card2");
    }// </editor-fold>//GEN-END:initComponents
// menu kategori
    private void Btn_Menu_KategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Menu_KategoriActionPerformed
        pn_Konten_Jenis.removeAll();
        pn_Konten_Jenis.repaint();
        pn_Konten_Jenis.revalidate();
        
        pn_Konten_Jenis.add(pn_Tbl_Jenis_Kategori);
        pn_Konten_Jenis.repaint();
        pn_Konten_Jenis.revalidate();
    }//GEN-LAST:event_Btn_Menu_KategoriActionPerformed

    
    // menu satuan
    private void Btn_Menu_SatuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Menu_SatuanActionPerformed
        pn_Konten_Jenis.removeAll();
        pn_Konten_Jenis.repaint();
        pn_Konten_Jenis.revalidate();
        
        pn_Konten_Jenis.add(pn_Tbl_Jenis_Satuan);
        pn_Konten_Jenis.repaint();
        pn_Konten_Jenis.revalidate();
    }//GEN-LAST:event_Btn_Menu_SatuanActionPerformed

    
    
    
    
    
    //Menu Status
    private void Btn_Menu_StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Menu_StatusActionPerformed
         pn_Konten_Jenis.removeAll();
        pn_Konten_Jenis.repaint();
        pn_Konten_Jenis.revalidate();
        
        pn_Konten_Jenis.add(pn_Tbl_Jenis_Status);
        pn_Konten_Jenis.repaint();
        pn_Konten_Jenis.revalidate();
    }//GEN-LAST:event_Btn_Menu_StatusActionPerformed

    private void Btn_Satuan_TambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Satuan_TambahActionPerformed
       
       String nama = Field_Satuan_Nama_Satuan.getText();
       String status = String.valueOf(Field_Satuan_Status.getSelectedItem());
       
        try {
            
            db.aksi("INSERT INTO satuan (no, `nama`, `status`)" + "VALUES (NULL, '" + nama + "','" + status + "')");
            model2.setRowCount(0);
            Tbl_Daftar_Satuan.setModel(model2);
            getDataS();
            clearS();
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_Btn_Satuan_TambahActionPerformed

    private void Btn_Satuan_HapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Satuan_HapusActionPerformed
        int Row = Tbl_Daftar_Satuan.getSelectedRow();
        String ID = (String) Tbl_Daftar_Satuan.getValueAt(Row, 0);
        db.aksi("DELETE FROM satuan WHERE nama ='"+ID+"'");
        model2.setRowCount(0);
        Tbl_Daftar_Satuan.setModel(model2);
        getDataS();
    }//GEN-LAST:event_Btn_Satuan_HapusActionPerformed

    private void Btn_Kategori_SimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Kategori_SimpanActionPerformed
       String kode = Field_Kategori_Kode_Unik.getText();
       String nama = Field_Kategori_Nama_Kategori.getText();
       String status = String.valueOf(Field_Kategori_Status.getSelectedItem());
       
        try {
            
            db.aksi("INSERT INTO kategori (no, `nama`, `kode_unik`, `status`)" + "VALUES (NULL, '" + nama + "','" + kode + "','" + status + "')");
            model.setRowCount(0);
            Tbl_Daftar_Kategori.setModel(model);
            getData();
            clear();
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_Btn_Kategori_SimpanActionPerformed

    private void Btn_Kategori_HapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Kategori_HapusActionPerformed
        int Row = Tbl_Daftar_Kategori.getSelectedRow();
        String ID = (String) Tbl_Daftar_Kategori.getValueAt(Row, 1);
        db.aksi("DELETE FROM kategori WHERE kode_unik='"+ID+"'");
        model.setRowCount(0);
        Tbl_Daftar_Kategori.setModel(model);
        getData();
    }//GEN-LAST:event_Btn_Kategori_HapusActionPerformed

    private void Btn_Status_TambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Status_TambahActionPerformed
       
       String nama = Field_Nama_status.getText();
       String kategori = String.valueOf(Field_Status_Kategori.getSelectedItem());
       
        try {
            
            db.aksi("INSERT INTO status (no, `nama`, `kategori`)" + "VALUES (NULL, '" + nama + "','" + kategori + "')");
            model3.setRowCount(0);
            Tbl_Daftar_Status.setModel(model3);
            getDataSt();
            clearSt();
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_Btn_Status_TambahActionPerformed

    private void Btn_Status_HapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Status_HapusActionPerformed
        int Row = Tbl_Daftar_Status.getSelectedRow();
        String ID = (String) Tbl_Daftar_Status.getValueAt(Row, 0);
        db.aksi("DELETE FROM status WHERE nama ='"+ID+"'");
        model3.setRowCount(0);
        Tbl_Daftar_Status.setModel(model3);
        getDataSt();
    }//GEN-LAST:event_Btn_Status_HapusActionPerformed

    private void Btn_Status_CariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Status_CariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_Status_CariActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Kategori_Cari;
    private rojerusan.RSMaterialButtonRectangle Btn_Kategori_Hapus;
    private rojerusan.RSMaterialButtonRectangle Btn_Kategori_Simpan;
    private rojerusan.RSMaterialButtonRectangle Btn_Menu_Kategori;
    private rojerusan.RSMaterialButtonRectangle Btn_Menu_Satuan;
    private rojerusan.RSMaterialButtonRectangle Btn_Menu_Status;
    private javax.swing.JButton Btn_Satuan_Cari;
    private rojerusan.RSMaterialButtonRectangle Btn_Satuan_Hapus;
    private rojerusan.RSMaterialButtonRectangle Btn_Satuan_Tambah;
    private javax.swing.JButton Btn_Status_Cari;
    private rojerusan.RSMaterialButtonRectangle Btn_Status_Hapus;
    private rojerusan.RSMaterialButtonRectangle Btn_Status_Tambah;
    private javax.swing.JTextField Field_Kategori_Cari;
    private javax.swing.JTextField Field_Kategori_Kode_Unik;
    private javax.swing.JTextField Field_Kategori_Nama_Kategori;
    private javax.swing.JComboBox<String> Field_Kategori_Status;
    private javax.swing.JTextField Field_Nama_status;
    private javax.swing.JTextField Field_Satuan_Cari;
    private javax.swing.JTextField Field_Satuan_Nama_Satuan;
    private javax.swing.JComboBox<String> Field_Satuan_Status;
    private javax.swing.JTextField Field_Status_Cari;
    private javax.swing.JComboBox<String> Field_Status_Kategori;
    private javax.swing.JLabel Lb_Aksi_Kategori_Nama_Kategori1;
    private javax.swing.JLabel Lb_Aksi_Kategori_Status1;
    private javax.swing.JLabel Lb_Aksi_Satuan_Nama_Satuan1;
    private javax.swing.JLabel Lb_Aksi_Satuan_Status1;
    private javax.swing.JLabel Lb_Daftar_Kategori;
    private javax.swing.JLabel Lb_Daftar_Satuan;
    private javax.swing.JLabel Lb_Daftar_Status;
    private javax.swing.JLabel Lb_Jenis;
    private javax.swing.JLabel Lb_Kategori;
    private javax.swing.JLabel Lb_Kategori2;
    private javax.swing.JLabel Lb_Kategori_Aksi_Kode_Unik1;
    private javax.swing.JLabel Lb_Satuan;
    private javax.swing.JLabel Lb_Status_Nama_Status;
    private javax.swing.JTable Tbl_Daftar_Kategori;
    private javax.swing.JTable Tbl_Daftar_Satuan;
    private javax.swing.JTable Tbl_Daftar_Status;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane_Tbl_Kategori;
    private javax.swing.JScrollPane jScrollPane_Tbl_Satuan;
    private javax.swing.JScrollPane jScrollPane_Tbl_Status;
    private javax.swing.JPanel pn_Ctrl_Jenis;
    private javax.swing.JPanel pn_Konten_Barang;
    private javax.swing.JPanel pn_Konten_Jenis;
    private javax.swing.JPanel pn_Tbl_Jenis_Kategori;
    private javax.swing.JPanel pn_Tbl_Jenis_Satuan;
    private javax.swing.JPanel pn_Tbl_Jenis_Status;
    private javax.swing.JPanel pn__Jenis_Detail;
    // End of variables declaration//GEN-END:variables

    private void clear() {
        Field_Kategori_Kode_Unik.setText(null);
        Field_Kategori_Nama_Kategori.setText(null);

      }

    private void clearS() {
        Field_Satuan_Nama_Satuan.setText(null);
    }

    private void clearSt() {
       Field_Nama_status.setText(null);}
}
