/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Utama;

/**
 *
 * @author acer
 */
public class Retur extends javax.swing.JPanel {

    /**
     * Creates new form Beranda
     */
    public Retur() {
        initComponents();
        
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
        Lb_Daftar_Retur_Kategori = new javax.swing.JLabel();
        Field_Daftar_Retur_Kategori = new javax.swing.JComboBox<>();
        Lb_Daftar_Retur_Dari_Tanggal = new javax.swing.JLabel();
        Field_Daftar_Retur_Dari_Tanggal = new javax.swing.JTextField();
        Lb_Daftar_Retur_Sampai_Tanggal = new javax.swing.JLabel();
        Field_Daftar_Retur_Sampai_Tanggal = new javax.swing.JTextField();
        Lb_Daftar_Retur_Distributor = new javax.swing.JLabel();
        Field_Daftar_Retur_Distributor = new javax.swing.JComboBox<>();
        Btn_Daftar_Retur_Cari = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        pn_Tambah_Retur = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tbl_Tambah_Retur = new javax.swing.JTable();
        Lb_Retur_Tambah = new javax.swing.JLabel();
        Lb_Retur_Tambah_No_Transaksi = new javax.swing.JLabel();
        Lb_Retur_Tambah_Tanggal = new javax.swing.JLabel();
        Lb_Retur_Tambah_Distributor = new javax.swing.JLabel();
        Field_Retur_Tambah_No_Transaksi = new javax.swing.JTextField();
        Field_Retur_Tambah_Tanggal = new javax.swing.JTextField();
        Field_Retur_Tambah_Distributor = new javax.swing.JComboBox<>();
        Lb_Retur_Tambah_Nama_Toko = new javax.swing.JLabel();
        Field_Retur_Tambah_Nama_Toko = new javax.swing.JTextField();
        Field_Cari_Tambah_Retur = new javax.swing.JTextField();
        Btn_Cari_Tambah_Retur = new javax.swing.JButton();
        Lb_Retur_Tambah_Total = new javax.swing.JLabel();
        Field_Retur_Tambah_Total = new javax.swing.JTextField();
        Lb_Retur_Tambah_Potongan = new javax.swing.JLabel();
        Field_Retur_Tambah_Potongan = new javax.swing.JTextField();
        Lb_Retur_Tambah_Biaya_Lain = new javax.swing.JLabel();
        Field_Retur_Tambah_Biaya_Lain = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        Field_Retur_Tambah_Keterangan = new javax.swing.JTextArea();
        Lb_Retur_Tambah_Keterangan = new javax.swing.JLabel();
        Btn_Tambah_Retur_Tambah_Baru = new javax.swing.JButton();
        Btn_Tambah_Retur_Simpan = new javax.swing.JButton();
        Btn_Tambah_Retur_Cetak = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1182, 686));

        pn_Retur.setBackground(new java.awt.Color(255, 255, 255));
        pn_Retur.setPreferredSize(new java.awt.Dimension(1182, 686));

        Lb_Retur.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        Lb_Retur.setText("Retur");

        Btn_Daftar_Retur.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Btn_Daftar_Retur.setText("Daftar Retur");
        Btn_Daftar_Retur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Btn_Daftar_ReturMouseClicked(evt);
            }
        });

        Btn_Retur_Tambah.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Btn_Retur_Tambah.setText("Tambah");
        Btn_Retur_Tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Btn_Retur_TambahMouseClicked(evt);
            }
        });

        pn_Konten_Retur.setLayout(new java.awt.CardLayout());

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
        jScrollPane1.setViewportView(Tbl_Daftar_Retur);

        Lb_Daftar_Retur_Kata_Kunci.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Daftar_Retur_Kata_Kunci.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Daftar_Retur_Kata_Kunci.setText("Kata Kunci :");

        Field_Daftar_Retur_Kata_Kunci.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        Lb_Daftar_Retur_Kategori.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Daftar_Retur_Kategori.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Daftar_Retur_Kategori.setText("Kategori :");

        Field_Daftar_Retur_Kategori.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        Lb_Daftar_Retur_Dari_Tanggal.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Daftar_Retur_Dari_Tanggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Daftar_Retur_Dari_Tanggal.setText("Dari Tanggal :");

        Field_Daftar_Retur_Dari_Tanggal.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        Lb_Daftar_Retur_Sampai_Tanggal.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Daftar_Retur_Sampai_Tanggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Daftar_Retur_Sampai_Tanggal.setText("s/d");

        Field_Daftar_Retur_Sampai_Tanggal.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Field_Daftar_Retur_Sampai_Tanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Field_Daftar_Retur_Sampai_TanggalActionPerformed(evt);
            }
        });

        Lb_Daftar_Retur_Distributor.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Daftar_Retur_Distributor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Daftar_Retur_Distributor.setText("Distributor :");

        Field_Daftar_Retur_Distributor.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        Btn_Daftar_Retur_Cari.setBackground(new java.awt.Color(3, 0, 126));
        Btn_Daftar_Retur_Cari.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Daftar_Retur_Cari.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Daftar_Retur_Cari.setText("Cari");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Daftar Retur");

        javax.swing.GroupLayout pn_Daftar_ReturLayout = new javax.swing.GroupLayout(pn_Daftar_Retur);
        pn_Daftar_Retur.setLayout(pn_Daftar_ReturLayout);
        pn_Daftar_ReturLayout.setHorizontalGroup(
            pn_Daftar_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Daftar_ReturLayout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addGroup(pn_Daftar_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Lb_Daftar_Retur_Kata_Kunci)
                    .addComponent(Lb_Daftar_Retur_Distributor)
                    .addComponent(Lb_Daftar_Retur_Kategori)
                    .addComponent(Lb_Daftar_Retur_Dari_Tanggal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_Daftar_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pn_Daftar_ReturLayout.createSequentialGroup()
                        .addGroup(pn_Daftar_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Field_Daftar_Retur_Dari_Tanggal, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Field_Daftar_Retur_Kategori, javax.swing.GroupLayout.Alignment.LEADING, 0, 200, Short.MAX_VALUE)
                            .addComponent(Field_Daftar_Retur_Distributor, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Lb_Daftar_Retur_Sampai_Tanggal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Field_Daftar_Retur_Sampai_Tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Field_Daftar_Retur_Kata_Kunci, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Btn_Daftar_Retur_Cari)
                .addContainerGap(477, Short.MAX_VALUE))
            .addGroup(pn_Daftar_ReturLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Daftar_ReturLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(567, 567, 567))
        );
        pn_Daftar_ReturLayout.setVerticalGroup(
            pn_Daftar_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Daftar_ReturLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(pn_Daftar_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lb_Daftar_Retur_Kata_Kunci)
                    .addComponent(Field_Daftar_Retur_Kata_Kunci, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Daftar_Retur_Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(pn_Daftar_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Field_Daftar_Retur_Kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lb_Daftar_Retur_Kategori))
                .addGap(24, 24, 24)
                .addGroup(pn_Daftar_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Field_Daftar_Retur_Dari_Tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lb_Daftar_Retur_Dari_Tanggal)
                    .addComponent(Field_Daftar_Retur_Sampai_Tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lb_Daftar_Retur_Sampai_Tanggal))
                .addGap(28, 28, 28)
                .addGroup(pn_Daftar_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Field_Daftar_Retur_Distributor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lb_Daftar_Retur_Distributor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149))
        );

        pn_Konten_Retur.add(pn_Daftar_Retur, "card2");

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
        jScrollPane2.setViewportView(Tbl_Tambah_Retur);

        Lb_Retur_Tambah.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        Lb_Retur_Tambah.setText("Tambah");

        Lb_Retur_Tambah_No_Transaksi.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Retur_Tambah_No_Transaksi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Retur_Tambah_No_Transaksi.setText("No Transaksi :");

        Lb_Retur_Tambah_Tanggal.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Retur_Tambah_Tanggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Retur_Tambah_Tanggal.setText("Tanggal :");

        Lb_Retur_Tambah_Distributor.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Retur_Tambah_Distributor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Retur_Tambah_Distributor.setText("Distributor :");

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

        Lb_Retur_Tambah_Total.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Retur_Tambah_Total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Retur_Tambah_Total.setText("Total Akhir :");

        Lb_Retur_Tambah_Potongan.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Retur_Tambah_Potongan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Retur_Tambah_Potongan.setText("Potongan :");

        Lb_Retur_Tambah_Biaya_Lain.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Retur_Tambah_Biaya_Lain.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Retur_Tambah_Biaya_Lain.setText("Biaya Lain :");

        Field_Retur_Tambah_Keterangan.setColumns(20);
        Field_Retur_Tambah_Keterangan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Field_Retur_Tambah_Keterangan.setRows(5);
        jScrollPane3.setViewportView(Field_Retur_Tambah_Keterangan);

        Lb_Retur_Tambah_Keterangan.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Retur_Tambah_Keterangan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Retur_Tambah_Keterangan.setText("Keterangan :");

        Btn_Tambah_Retur_Tambah_Baru.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Btn_Tambah_Retur_Tambah_Baru.setText("Tambah");

        Btn_Tambah_Retur_Simpan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Btn_Tambah_Retur_Simpan.setText("Simpan");

        Btn_Tambah_Retur_Cetak.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Btn_Tambah_Retur_Cetak.setText("Cetak");
        Btn_Tambah_Retur_Cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Tambah_Retur_CetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_Tambah_ReturLayout = new javax.swing.GroupLayout(pn_Tambah_Retur);
        pn_Tambah_Retur.setLayout(pn_Tambah_ReturLayout);
        pn_Tambah_ReturLayout.setHorizontalGroup(
            pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Lb_Retur_Tambah_No_Transaksi)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Tambah_ReturLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Lb_Retur_Tambah_Tanggal, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Lb_Retur_Tambah_Distributor, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Field_Retur_Tambah_No_Transaksi)
                    .addComponent(Field_Retur_Tambah_Tanggal)
                    .addComponent(Field_Retur_Tambah_Distributor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(167, 167, 167)
                .addComponent(Lb_Retur_Tambah_Nama_Toko)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Field_Retur_Tambah_Nama_Toko, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Tambah_ReturLayout.createSequentialGroup()
                        .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1260, Short.MAX_VALUE)
                            .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(Field_Cari_Tambah_Retur, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Btn_Cari_Tambah_Retur, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                                .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                                        .addComponent(Btn_Tambah_Retur_Tambah_Baru, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(Btn_Tambah_Retur_Simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(Btn_Tambah_Retur_Cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(Lb_Retur_Tambah_Keterangan)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Tambah_ReturLayout.createSequentialGroup()
                                        .addGap(123, 123, 123)
                                        .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(Lb_Retur_Tambah_Potongan)
                                            .addComponent(Lb_Retur_Tambah_Biaya_Lain)))
                                    .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                                        .addGap(118, 118, 118)
                                        .addComponent(Lb_Retur_Tambah_Total)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Field_Retur_Tambah_Biaya_Lain, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(Field_Retur_Tambah_Potongan)
                                    .addComponent(Field_Retur_Tambah_Total, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(43, 43, 43))
                    .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                        .addComponent(Lb_Retur_Tambah)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pn_Tambah_ReturLayout.setVerticalGroup(
            pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Lb_Retur_Tambah)
                .addGap(8, 8, 8)
                .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lb_Retur_Tambah_Nama_Toko)
                    .addComponent(Field_Retur_Tambah_Nama_Toko, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Field_Retur_Tambah_No_Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lb_Retur_Tambah_No_Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Field_Retur_Tambah_Tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lb_Retur_Tambah_Tanggal))
                .addGap(31, 31, 31)
                .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Field_Retur_Tambah_Distributor, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lb_Retur_Tambah_Distributor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Field_Cari_Tambah_Retur, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Cari_Tambah_Retur, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                        .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Lb_Retur_Tambah_Biaya_Lain, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Field_Retur_Tambah_Biaya_Lain, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Lb_Retur_Tambah_Potongan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Field_Retur_Tambah_Potongan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Lb_Retur_Tambah_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Field_Retur_Tambah_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(pn_Tambah_ReturLayout.createSequentialGroup()
                            .addComponent(Lb_Retur_Tambah_Keterangan)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pn_Tambah_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Btn_Tambah_Retur_Tambah_Baru, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Btn_Tambah_Retur_Cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Btn_Tambah_Retur_Simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(82, 82, 82))
        );

        pn_Konten_Retur.add(pn_Tambah_Retur, "card2");

        javax.swing.GroupLayout pn_ReturLayout = new javax.swing.GroupLayout(pn_Retur);
        pn_Retur.setLayout(pn_ReturLayout);
        pn_ReturLayout.setHorizontalGroup(
            pn_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_ReturLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(pn_ReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pn_Konten_Retur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addComponent(pn_Retur, javax.swing.GroupLayout.DEFAULT_SIZE, 1376, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pn_Retur, javax.swing.GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE)
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

    private void Field_Daftar_Retur_Sampai_TanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Field_Daftar_Retur_Sampai_TanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Field_Daftar_Retur_Sampai_TanggalActionPerformed

    private void Btn_Tambah_Retur_CetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Tambah_Retur_CetakActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_Tambah_Retur_CetakActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Cari_Tambah_Retur;
    private javax.swing.JButton Btn_Daftar_Retur;
    private javax.swing.JButton Btn_Daftar_Retur_Cari;
    private javax.swing.JButton Btn_Retur_Tambah;
    private javax.swing.JButton Btn_Tambah_Retur_Cetak;
    private javax.swing.JButton Btn_Tambah_Retur_Simpan;
    private javax.swing.JButton Btn_Tambah_Retur_Tambah_Baru;
    private javax.swing.JTextField Field_Cari_Tambah_Retur;
    private javax.swing.JTextField Field_Daftar_Retur_Dari_Tanggal;
    private javax.swing.JComboBox<String> Field_Daftar_Retur_Distributor;
    private javax.swing.JTextField Field_Daftar_Retur_Kata_Kunci;
    private javax.swing.JComboBox<String> Field_Daftar_Retur_Kategori;
    private javax.swing.JTextField Field_Daftar_Retur_Sampai_Tanggal;
    private javax.swing.JTextField Field_Retur_Tambah_Biaya_Lain;
    private javax.swing.JComboBox<String> Field_Retur_Tambah_Distributor;
    private javax.swing.JTextArea Field_Retur_Tambah_Keterangan;
    private javax.swing.JTextField Field_Retur_Tambah_Nama_Toko;
    private javax.swing.JTextField Field_Retur_Tambah_No_Transaksi;
    private javax.swing.JTextField Field_Retur_Tambah_Potongan;
    private javax.swing.JTextField Field_Retur_Tambah_Tanggal;
    private javax.swing.JTextField Field_Retur_Tambah_Total;
    private javax.swing.JLabel Lb_Daftar_Retur_Dari_Tanggal;
    private javax.swing.JLabel Lb_Daftar_Retur_Distributor;
    private javax.swing.JLabel Lb_Daftar_Retur_Kata_Kunci;
    private javax.swing.JLabel Lb_Daftar_Retur_Kategori;
    private javax.swing.JLabel Lb_Daftar_Retur_Sampai_Tanggal;
    private javax.swing.JLabel Lb_Retur;
    private javax.swing.JLabel Lb_Retur_Tambah;
    private javax.swing.JLabel Lb_Retur_Tambah_Biaya_Lain;
    private javax.swing.JLabel Lb_Retur_Tambah_Distributor;
    private javax.swing.JLabel Lb_Retur_Tambah_Keterangan;
    private javax.swing.JLabel Lb_Retur_Tambah_Nama_Toko;
    private javax.swing.JLabel Lb_Retur_Tambah_No_Transaksi;
    private javax.swing.JLabel Lb_Retur_Tambah_Potongan;
    private javax.swing.JLabel Lb_Retur_Tambah_Tanggal;
    private javax.swing.JLabel Lb_Retur_Tambah_Total;
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
}
