/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Utama;

/**
 *
 * @author acer
 */
public class Kasir extends javax.swing.JPanel {

    /**
     * Creates new form Barang
     */
    public Kasir() {
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

        pn_Konten_Kasir = new javax.swing.JPanel();
        Lb_Kasir = new javax.swing.JLabel();
        pn_Kasir_Detail = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tbl_Kasir_Daftar_Barang = new javax.swing.JTable();
        Field_Kasir_Total_Harga = new javax.swing.JTextField();
        Field_Kasir_Cari_Barang = new javax.swing.JTextField();
        Lb_Kasir_Cari_Barang = new javax.swing.JLabel();
        Btn_Kasir_Cari_Barang = new javax.swing.JButton();
        Field_Kasir_Jumlah_Barang = new javax.swing.JTextField();
        Lb_Kasir_Jumlah_Barang = new javax.swing.JLabel();
        Field_Kasir_Jumlah_Bayar = new javax.swing.JTextField();
        Lb_Kasir_Jumlah_Bayar = new javax.swing.JLabel();
        Lb_Kasir_Jumlah_Kembalian = new javax.swing.JLabel();
        Field_Kasir_Jumlah_Kembalian = new javax.swing.JTextField();
        Btn_Kasir_Bayar = new rojerusan.RSMaterialButtonRectangle();
        Btn_Kasir_Tambah_Baru = new rojerusan.RSMaterialButtonRectangle();
        Btn_Kasir_Simpan = new rojerusan.RSMaterialButtonRectangle();
        Btn_Kasir_Cetak_Nota = new rojerusan.RSMaterialButtonRectangle();
        Lb_Kasir_Kode_Transaksi = new javax.swing.JLabel();
        Field_Kasir_Kode_Transaksi = new javax.swing.JTextField();
        Field_Kasir_Tanggal_Transaksi = new javax.swing.JTextField();
        Lb_Kasir_Tanggal_Transaksi = new javax.swing.JLabel();
        Lb_Kasir_Nama_Pelanggan = new javax.swing.JLabel();
        Field_Kasir_Nama_Pelanggan = new javax.swing.JTextField();
        Lb_Kasir_Nama_Kasir = new javax.swing.JLabel();
        Field_Kasir_Nama_Kasir = new javax.swing.JTextField();
        Lb_Kasir_Catatan = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Field_Kasir_Catatan = new javax.swing.JTextArea();
        Btn_Kasir_Hutang = new rojerusan.RSMaterialButtonRectangle();
        Btn_Kasir_Batal = new rojerusan.RSMaterialButtonRectangle();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1150, 683));
        setLayout(new java.awt.CardLayout());

        pn_Konten_Kasir.setBackground(new java.awt.Color(255, 255, 255));
        pn_Konten_Kasir.setPreferredSize(new java.awt.Dimension(1150, 683));

        Lb_Kasir.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        Lb_Kasir.setText("Kasir");

        Tbl_Kasir_Daftar_Barang.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        Tbl_Kasir_Daftar_Barang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No", "Kode Barang", "Nama Barang", "Jumlah", "Satuan", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tbl_Kasir_Daftar_Barang.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(Tbl_Kasir_Daftar_Barang);

        Field_Kasir_Total_Harga.setBackground(new java.awt.Color(26, 131, 255));
        Field_Kasir_Total_Harga.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Field_Kasir_Total_Harga.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Field_Kasir_Total_Harga.setText("TOTAL");

        Lb_Kasir_Cari_Barang.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        Lb_Kasir_Cari_Barang.setText("Cari Barang");

        Btn_Kasir_Cari_Barang.setBackground(new java.awt.Color(3, 0, 126));
        Btn_Kasir_Cari_Barang.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        Btn_Kasir_Cari_Barang.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Kasir_Cari_Barang.setText("Cari");

        Field_Kasir_Jumlah_Barang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Field_Kasir_Jumlah_Barang.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Field_Kasir_Jumlah_Barang.setText("0");

        Lb_Kasir_Jumlah_Barang.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        Lb_Kasir_Jumlah_Barang.setText("Jumlah : ");

        Field_Kasir_Jumlah_Bayar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        Lb_Kasir_Jumlah_Bayar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        Lb_Kasir_Jumlah_Bayar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Kasir_Jumlah_Bayar.setText("Bayar :");

        Lb_Kasir_Jumlah_Kembalian.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        Lb_Kasir_Jumlah_Kembalian.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Kasir_Jumlah_Kembalian.setText("Kembali :");

        Field_Kasir_Jumlah_Kembalian.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        Btn_Kasir_Bayar.setBackground(new java.awt.Color(0, 219, 22));
        Btn_Kasir_Bayar.setForeground(new java.awt.Color(0, 0, 0));
        Btn_Kasir_Bayar.setText("Bayar");
        Btn_Kasir_Bayar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Kasir_Bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Kasir_BayarActionPerformed(evt);
            }
        });

        Btn_Kasir_Tambah_Baru.setBackground(new java.awt.Color(0, 65, 188));
        Btn_Kasir_Tambah_Baru.setText("Baru");
        Btn_Kasir_Tambah_Baru.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Kasir_Tambah_Baru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Kasir_Tambah_BaruActionPerformed(evt);
            }
        });

        Btn_Kasir_Simpan.setBackground(new java.awt.Color(0, 232, 0));
        Btn_Kasir_Simpan.setForeground(new java.awt.Color(0, 0, 0));
        Btn_Kasir_Simpan.setText("Simpan");
        Btn_Kasir_Simpan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        Btn_Kasir_Cetak_Nota.setBackground(new java.awt.Color(255, 229, 0));
        Btn_Kasir_Cetak_Nota.setForeground(new java.awt.Color(0, 0, 0));
        Btn_Kasir_Cetak_Nota.setText("Cetak Nota");
        Btn_Kasir_Cetak_Nota.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        Lb_Kasir_Kode_Transaksi.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        Lb_Kasir_Kode_Transaksi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Kasir_Kode_Transaksi.setText("No Transaksi :");

        Lb_Kasir_Tanggal_Transaksi.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        Lb_Kasir_Tanggal_Transaksi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Kasir_Tanggal_Transaksi.setText("Tanggal :");

        Lb_Kasir_Nama_Pelanggan.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        Lb_Kasir_Nama_Pelanggan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Kasir_Nama_Pelanggan.setText("Pelanggan :");

        Lb_Kasir_Nama_Kasir.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        Lb_Kasir_Nama_Kasir.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Kasir_Nama_Kasir.setText("Kasir :");

        Lb_Kasir_Catatan.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        Lb_Kasir_Catatan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Kasir_Catatan.setText("Catatan :");

        Field_Kasir_Catatan.setColumns(20);
        Field_Kasir_Catatan.setRows(5);
        jScrollPane2.setViewportView(Field_Kasir_Catatan);

        Btn_Kasir_Hutang.setBackground(new java.awt.Color(186, 0, 0));
        Btn_Kasir_Hutang.setText("Debt");
        Btn_Kasir_Hutang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Kasir_Hutang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Kasir_HutangActionPerformed(evt);
            }
        });

        Btn_Kasir_Batal.setBackground(new java.awt.Color(255, 153, 51));
        Btn_Kasir_Batal.setForeground(new java.awt.Color(0, 0, 0));
        Btn_Kasir_Batal.setText("Batal");
        Btn_Kasir_Batal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout pn_Kasir_DetailLayout = new javax.swing.GroupLayout(pn_Kasir_Detail);
        pn_Kasir_Detail.setLayout(pn_Kasir_DetailLayout);
        pn_Kasir_DetailLayout.setHorizontalGroup(
            pn_Kasir_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Kasir_DetailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_Kasir_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Kasir_DetailLayout.createSequentialGroup()
                        .addComponent(Lb_Kasir_Kode_Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Field_Kasir_Kode_Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_Kasir_DetailLayout.createSequentialGroup()
                        .addComponent(Lb_Kasir_Tanggal_Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Field_Kasir_Tanggal_Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_Kasir_DetailLayout.createSequentialGroup()
                        .addComponent(Lb_Kasir_Nama_Pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Field_Kasir_Nama_Pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_Kasir_DetailLayout.createSequentialGroup()
                        .addComponent(Lb_Kasir_Nama_Kasir, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Field_Kasir_Nama_Kasir, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_Kasir_DetailLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(pn_Kasir_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Lb_Kasir_Catatan)
                            .addGroup(pn_Kasir_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(pn_Kasir_DetailLayout.createSequentialGroup()
                                    .addComponent(Btn_Kasir_Batal, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Btn_Kasir_Simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pn_Kasir_DetailLayout.createSequentialGroup()
                                    .addComponent(Btn_Kasir_Hutang, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(164, 164, 164))))))
                .addGroup(pn_Kasir_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Kasir_DetailLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(pn_Kasir_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Kasir_DetailLayout.createSequentialGroup()
                                .addGroup(pn_Kasir_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pn_Kasir_DetailLayout.createSequentialGroup()
                                        .addComponent(Field_Kasir_Cari_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Btn_Kasir_Cari_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(Lb_Kasir_Cari_Barang))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Lb_Kasir_Jumlah_Barang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Field_Kasir_Jumlah_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Field_Kasir_Total_Harga, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Kasir_DetailLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pn_Kasir_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Kasir_DetailLayout.createSequentialGroup()
                                .addComponent(Lb_Kasir_Jumlah_Bayar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Field_Kasir_Jumlah_Bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Kasir_DetailLayout.createSequentialGroup()
                                .addComponent(Lb_Kasir_Jumlah_Kembalian)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Field_Kasir_Jumlah_Kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Kasir_DetailLayout.createSequentialGroup()
                                .addComponent(Btn_Kasir_Tambah_Baru, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Btn_Kasir_Cetak_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(Btn_Kasir_Bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(24, 24, 24))
        );
        pn_Kasir_DetailLayout.setVerticalGroup(
            pn_Kasir_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Kasir_DetailLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pn_Kasir_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Kasir_DetailLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(pn_Kasir_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Lb_Kasir_Jumlah_Barang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Field_Kasir_Jumlah_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pn_Kasir_DetailLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Lb_Kasir_Cari_Barang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn_Kasir_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_Kasir_DetailLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(Btn_Kasir_Cari_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Field_Kasir_Cari_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(21, 21, 21)
                .addComponent(Field_Kasir_Total_Harga, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(pn_Kasir_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Lb_Kasir_Jumlah_Bayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Field_Kasir_Jumlah_Bayar, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_Kasir_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Field_Kasir_Jumlah_Kembalian)
                    .addComponent(Lb_Kasir_Jumlah_Kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pn_Kasir_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_Kasir_Cetak_Nota, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Kasir_Tambah_Baru, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Kasir_Bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(115, 115, 115))
            .addGroup(pn_Kasir_DetailLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(pn_Kasir_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lb_Kasir_Kode_Transaksi)
                    .addComponent(Field_Kasir_Kode_Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pn_Kasir_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lb_Kasir_Tanggal_Transaksi)
                    .addComponent(Field_Kasir_Tanggal_Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pn_Kasir_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lb_Kasir_Nama_Pelanggan)
                    .addComponent(Field_Kasir_Nama_Pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pn_Kasir_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lb_Kasir_Nama_Kasir)
                    .addComponent(Field_Kasir_Nama_Kasir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(Lb_Kasir_Catatan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addGap(18, 18, 18)
                .addGroup(pn_Kasir_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_Kasir_Simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Kasir_Batal, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addComponent(Btn_Kasir_Hutang, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout pn_Konten_KasirLayout = new javax.swing.GroupLayout(pn_Konten_Kasir);
        pn_Konten_Kasir.setLayout(pn_Konten_KasirLayout);
        pn_Konten_KasirLayout.setHorizontalGroup(
            pn_Konten_KasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Konten_KasirLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(pn_Konten_KasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Konten_KasirLayout.createSequentialGroup()
                        .addComponent(pn_Kasir_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pn_Konten_KasirLayout.createSequentialGroup()
                        .addComponent(Lb_Kasir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1052, 1052, 1052))))
        );
        pn_Konten_KasirLayout.setVerticalGroup(
            pn_Konten_KasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Konten_KasirLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(Lb_Kasir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(31, 31, 31)
                .addComponent(pn_Kasir_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );

        add(pn_Konten_Kasir, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_Kasir_HutangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Kasir_HutangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_Kasir_HutangActionPerformed

    private void Btn_Kasir_BayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Kasir_BayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_Kasir_BayarActionPerformed

    private void Btn_Kasir_Tambah_BaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Kasir_Tambah_BaruActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_Kasir_Tambah_BaruActionPerformed

    
    
    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle Btn_Kasir_Batal;
    private rojerusan.RSMaterialButtonRectangle Btn_Kasir_Bayar;
    private javax.swing.JButton Btn_Kasir_Cari_Barang;
    private rojerusan.RSMaterialButtonRectangle Btn_Kasir_Cetak_Nota;
    private rojerusan.RSMaterialButtonRectangle Btn_Kasir_Hutang;
    private rojerusan.RSMaterialButtonRectangle Btn_Kasir_Simpan;
    private rojerusan.RSMaterialButtonRectangle Btn_Kasir_Tambah_Baru;
    private javax.swing.JTextField Field_Kasir_Cari_Barang;
    private javax.swing.JTextArea Field_Kasir_Catatan;
    private javax.swing.JTextField Field_Kasir_Jumlah_Barang;
    private javax.swing.JTextField Field_Kasir_Jumlah_Bayar;
    private javax.swing.JTextField Field_Kasir_Jumlah_Kembalian;
    private javax.swing.JTextField Field_Kasir_Kode_Transaksi;
    private javax.swing.JTextField Field_Kasir_Nama_Kasir;
    private javax.swing.JTextField Field_Kasir_Nama_Pelanggan;
    private javax.swing.JTextField Field_Kasir_Tanggal_Transaksi;
    private javax.swing.JTextField Field_Kasir_Total_Harga;
    private javax.swing.JLabel Lb_Kasir;
    private javax.swing.JLabel Lb_Kasir_Cari_Barang;
    private javax.swing.JLabel Lb_Kasir_Catatan;
    private javax.swing.JLabel Lb_Kasir_Jumlah_Barang;
    private javax.swing.JLabel Lb_Kasir_Jumlah_Bayar;
    private javax.swing.JLabel Lb_Kasir_Jumlah_Kembalian;
    private javax.swing.JLabel Lb_Kasir_Kode_Transaksi;
    private javax.swing.JLabel Lb_Kasir_Nama_Kasir;
    private javax.swing.JLabel Lb_Kasir_Nama_Pelanggan;
    private javax.swing.JLabel Lb_Kasir_Tanggal_Transaksi;
    private javax.swing.JTable Tbl_Kasir_Daftar_Barang;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pn_Kasir_Detail;
    private javax.swing.JPanel pn_Konten_Kasir;
    // End of variables declaration//GEN-END:variables
}
