/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Utama;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author acer
 */
public class Beranda extends javax.swing.JPanel {
    Connection con;
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/tatatoko";
    private final String user = "root";
    private final String pwd = "";
    koneksi db = new koneksi();
    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel model2 = new DefaultTableModel();
    DefaultTableModel model3 = new DefaultTableModel();
    DefaultTableModel model4 = new DefaultTableModel();
    /**
     * Creates new form Beranda
     */
    public Beranda() {
        initComponents();
        getColumn();
        getData();
        
        getColumnD();
        getDataD();
        
        
        model3.addColumn("Tanggal");
        model3.addColumn("ID Transaksi");
        model3.addColumn("ID Barang");
        model3.addColumn("Nama Barang");
        model3.addColumn("Harga");
        model3.addColumn("Jumlah");
        model3.addColumn("Total");
        model3.addColumn("Metode Pembayaran");
        Tbl_Beranda_Transaksi.setModel(model3);
        getDataT();
        
        model4.addColumn("ID");
        model4.addColumn("Nama User");
        model4.addColumn("Password ");
        model4.addColumn("Akses");
        model4.addColumn("Nama Lengkap");
        model4.addColumn("No Identitas");
        model4.addColumn("Jenis Kelamin");
        model4.addColumn("No Telepon");
        model4.addColumn("Tanggal Lahir");
        model4.addColumn("Email");
        model4.addColumn("Status");
        model4.addColumn("Tanggal Mulai");
        model4.addColumn("Gaji");
        model4.addColumn("No Rek");
        model4.addColumn("Alamat");
        getDataK();
    }
    
    public void getColumn(){
        model.addColumn("ID");
        model.addColumn("Nama Barang");
        model.addColumn("Jenis Barang");
        model.addColumn("Jumlah Barang");
        model.addColumn("Harga Beli");
        model.addColumn("Harga Jual");
        model.addColumn("Tanggal");
        model.addColumn("Expired");
        model.addColumn("Satuan");
        model.addColumn("Status");
        model.addColumn("Catatan");
        Tbl_Beranda_Barang.setModel(model);
        
        
    }
    public void getData(){
        
        ResultSet hasil = db.ambilData("SELECT * FROM barang");
        try {
            while(hasil.next()) {
                model.addRow(new Object[]{hasil.getString("id_barang"),
                    hasil.getString("nama_barang"),hasil.getString("jenis"),
                    hasil.getString("jumlah"),hasil.getString("harga_beli"),
                    hasil.getString("harga_jual"),hasil.getString("tanggal"),
                    hasil.getString("expired"),hasil.getString("satuan"),
                    hasil.getString("status"),hasil.getString("catatan"),
                    hasil.getString("barcode"),hasil.getString("merk")});
            }
             Tbl_Beranda_Barang.setModel(model);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void getColumnD() {
        model2.addColumn("ID");
        model2.addColumn("Nama ");
        model2.addColumn("Kota");
        model2.addColumn("Telepon");
        model2.addColumn("Email");
        model2.addColumn("Fax");
        model2.addColumn("Alamat");
        model2.addColumn("No Rek");
        model2.addColumn("Bank");
        model2.addColumn("Ket");
        model2.addColumn("Status");
        Tbl_Beranda_Distributor.setModel(model2);
        

    }

    public void getDataD() {

        ResultSet hasil = db.ambilData("SELECT * FROM distributor");
        try {
            while (hasil.next()) {
                model2.addRow(new Object[]{hasil.getString("id_distributor"),
                    hasil.getString("nama"), hasil.getString("kota"),
                    hasil.getString("telepon"), hasil.getString("email"),
                    hasil.getString("fax"), hasil.getString("alamat"),
                    hasil.getString("no_rek"), hasil.getString("bank"),
                    hasil.getString("ket"), hasil.getString("status"),});
            }
            Tbl_Beranda_Distributor.setModel(model2);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    
    public void getDataT(){
        
        ResultSet hasil = db.ambilData("SELECT * FROM transaksi");
        try {
            while (hasil.next()) {
                model3.addRow(new Object[]{hasil.getString("tgl_transaksi"),
                    hasil.getString("id_transaksi"), hasil.getString("id_barang"),
                    hasil.getString("nama_barang"),
                    hasil.getString("harga"), hasil.getString("jumlah_barang"),
                    hasil.getString("total_harga"), hasil.getString("metode")});
            }
            Tbl_Beranda_Transaksi.setModel(model3);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       
    }
    
    
    public void getDataK() {

        ResultSet hasil = db.ambilData("SELECT * FROM pengguna");
        try {
            while (hasil.next()) {
                model4.addRow(new Object[]{hasil.getString("id_username"),
                    hasil.getString("username"),hasil.getString("password"), 
                    hasil.getString("Akses"),hasil.getString("Nama_Lengkap"),
                    hasil.getString("No_Identitas"), hasil.getString("Jenis_Kelamin"),
                    hasil.getString("No_Telepon"), hasil.getString("Tanggal_Lahir"),
                    hasil.getString("email"), hasil.getString("Status_Karyawan"),
                    hasil.getString("Tanggal_Mulai"), hasil.getString("Gaji"),
                    hasil.getString("No_Rekening"),hasil.getString("Alamat")});
            }
            Tbl_Beranda_Karyawan.setModel(model4);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public void search() {
    String cari = Field_Cari_Beranda_Barang.getText();
    ResultSet hasil = db.ambilData("SELECT * FROM barang WHERE nama_barang LIKE '%" + cari + "%' LIMIT 1");
    try {
        model.setRowCount(0); // Menghapus semua baris yang ada di model sebelum menambahkan baris baru
        if (hasil.next()) {
            model.addRow(new Object[]{hasil.getString("id_barang"),
                hasil.getString("nama_barang"),hasil.getString("jenis"),
                hasil.getString("jumlah"),hasil.getString("harga_beli"),
                hasil.getString("harga_jual"),hasil.getString("tanggal"),
                hasil.getString("expired"),hasil.getString("satuan"),
                hasil.getString("status"),hasil.getString("catatan")});
            Tbl_Beranda_Barang.setModel(model);
        } else {
            JOptionPane.showMessageDialog(null, "Tidak ada data yang valid");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_Beranda = new javax.swing.JPanel();
        Lb_Beranda = new javax.swing.JLabel();
        pn_Konten_Beranda = new javax.swing.JPanel();
        pn_Card_Barang = new javax.swing.JPanel();
        pn_Lg_Barang = new javax.swing.JPanel();
        Lb_Logo_Barang = new javax.swing.JLabel();
        Lb_Barang = new javax.swing.JLabel();
        Lb_Jumlah_Barang = new javax.swing.JLabel();
        pn_Card_Distributor = new javax.swing.JPanel();
        pn_Lg_Distributor = new javax.swing.JPanel();
        Lb_Logo_Distributor = new javax.swing.JLabel();
        Lb_Distributor = new javax.swing.JLabel();
        Lb_Jumlah_Distributor = new javax.swing.JLabel();
        pn_Card_Transaksi = new javax.swing.JPanel();
        pn_Lg_Transaksi = new javax.swing.JPanel();
        Lb_Logo_Distributor2 = new javax.swing.JLabel();
        Lb_Transaksi = new javax.swing.JLabel();
        Lb_Jumlah_Transaksi = new javax.swing.JLabel();
        pn_Card_Karyawan = new javax.swing.JPanel();
        pn_Lg_Karyawan = new javax.swing.JPanel();
        Lb_Logo_Barang1 = new javax.swing.JLabel();
        Lb_Karyawan = new javax.swing.JLabel();
        Lb_Jumlah_Karyawan = new javax.swing.JLabel();
        pn_Konten_Tbl_Beranda = new javax.swing.JPanel();
        pn_Tbl_Beranda_Barang = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        Tbl_Beranda_Barang = new javax.swing.JTable();
        Lb_Tbl_Data_Barang = new javax.swing.JLabel();
        Lb_Beranda_Barang = new javax.swing.JLabel();
        Btn_Cari_Beranda_Barang = new javax.swing.JButton();
        Field_Cari_Beranda_Barang = new javax.swing.JTextField();
        pn_Tbl_Beranda_Distributor = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        Tbl_Beranda_Distributor = new javax.swing.JTable();
        Lb_Tbl_Data_Distributor = new javax.swing.JLabel();
        Lb_Beranda_Distributor = new javax.swing.JLabel();
        Btn_Cari_Beranda_Distributor = new javax.swing.JButton();
        Field_Cari_Beranda_Distributor = new javax.swing.JTextField();
        pn_Tbl_Beranda_Transaksi = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        Tbl_Beranda_Transaksi = new javax.swing.JTable();
        Lb_Tbl_Data_Transaksi = new javax.swing.JLabel();
        Lb_Beranda_Transaksi = new javax.swing.JLabel();
        Btn_Cari_Beranda_Transaksi = new javax.swing.JButton();
        Field_Cari_Beranda_Transaksi = new javax.swing.JTextField();
        pn_Tbl_Beranda_Karyawan = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        Tbl_Beranda_Karyawan = new javax.swing.JTable();
        Lb_Tbl_Data_Karyawan = new javax.swing.JLabel();
        Lb_Beranda_Karyawan = new javax.swing.JLabel();
        Btn_Cari_Beranda_Karyawan = new javax.swing.JButton();
        Field_Cari_Beranda_Karyawan = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1182, 686));

        pn_Beranda.setBackground(new java.awt.Color(255, 255, 255));
        pn_Beranda.setPreferredSize(new java.awt.Dimension(1182, 686));

        Lb_Beranda.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        Lb_Beranda.setText("Beranda");

        pn_Card_Barang.setBackground(new java.awt.Color(255, 255, 255));
        pn_Card_Barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_Card_BarangMouseClicked(evt);
            }
        });

        pn_Lg_Barang.setBackground(new java.awt.Color(3, 0, 126));

        Lb_Logo_Barang.setForeground(new java.awt.Color(255, 255, 255));
        Lb_Logo_Barang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Lb_Logo_Barang.setText("Logo");

        javax.swing.GroupLayout pn_Lg_BarangLayout = new javax.swing.GroupLayout(pn_Lg_Barang);
        pn_Lg_Barang.setLayout(pn_Lg_BarangLayout);
        pn_Lg_BarangLayout.setHorizontalGroup(
            pn_Lg_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Lg_BarangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Lb_Logo_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        pn_Lg_BarangLayout.setVerticalGroup(
            pn_Lg_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Lg_BarangLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(Lb_Logo_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Lb_Barang.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        Lb_Barang.setText("Barang");

        Lb_Jumlah_Barang.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        Lb_Jumlah_Barang.setText("0");

        javax.swing.GroupLayout pn_Card_BarangLayout = new javax.swing.GroupLayout(pn_Card_Barang);
        pn_Card_Barang.setLayout(pn_Card_BarangLayout);
        pn_Card_BarangLayout.setHorizontalGroup(
            pn_Card_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Card_BarangLayout.createSequentialGroup()
                .addComponent(pn_Lg_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_Card_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Lb_Jumlah_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lb_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        pn_Card_BarangLayout.setVerticalGroup(
            pn_Card_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_Lg_Barang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pn_Card_BarangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Lb_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Lb_Jumlah_Barang)
                .addContainerGap())
        );

        pn_Card_Distributor.setBackground(new java.awt.Color(255, 255, 255));
        pn_Card_Distributor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_Card_DistributorMouseClicked(evt);
            }
        });

        pn_Lg_Distributor.setBackground(new java.awt.Color(3, 0, 126));

        Lb_Logo_Distributor.setForeground(new java.awt.Color(255, 255, 255));
        Lb_Logo_Distributor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Lb_Logo_Distributor.setText("Logo");

        javax.swing.GroupLayout pn_Lg_DistributorLayout = new javax.swing.GroupLayout(pn_Lg_Distributor);
        pn_Lg_Distributor.setLayout(pn_Lg_DistributorLayout);
        pn_Lg_DistributorLayout.setHorizontalGroup(
            pn_Lg_DistributorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Lg_DistributorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Lb_Logo_Distributor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        pn_Lg_DistributorLayout.setVerticalGroup(
            pn_Lg_DistributorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Lg_DistributorLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(Lb_Logo_Distributor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Lb_Distributor.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        Lb_Distributor.setText("Distributor");

        Lb_Jumlah_Distributor.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        Lb_Jumlah_Distributor.setText("0");

        javax.swing.GroupLayout pn_Card_DistributorLayout = new javax.swing.GroupLayout(pn_Card_Distributor);
        pn_Card_Distributor.setLayout(pn_Card_DistributorLayout);
        pn_Card_DistributorLayout.setHorizontalGroup(
            pn_Card_DistributorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Card_DistributorLayout.createSequentialGroup()
                .addComponent(pn_Lg_Distributor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_Card_DistributorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Card_DistributorLayout.createSequentialGroup()
                        .addComponent(Lb_Distributor, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(55, Short.MAX_VALUE))
                    .addGroup(pn_Card_DistributorLayout.createSequentialGroup()
                        .addComponent(Lb_Jumlah_Distributor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(85, 85, 85))))
        );
        pn_Card_DistributorLayout.setVerticalGroup(
            pn_Card_DistributorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_Lg_Distributor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pn_Card_DistributorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Lb_Distributor)
                .addGap(18, 18, 18)
                .addComponent(Lb_Jumlah_Distributor))
        );

        pn_Card_Transaksi.setBackground(new java.awt.Color(255, 255, 255));
        pn_Card_Transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_Card_TransaksiMouseClicked(evt);
            }
        });

        pn_Lg_Transaksi.setBackground(new java.awt.Color(3, 0, 126));

        Lb_Logo_Distributor2.setForeground(new java.awt.Color(255, 255, 255));
        Lb_Logo_Distributor2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Lb_Logo_Distributor2.setText("Logo");

        javax.swing.GroupLayout pn_Lg_TransaksiLayout = new javax.swing.GroupLayout(pn_Lg_Transaksi);
        pn_Lg_Transaksi.setLayout(pn_Lg_TransaksiLayout);
        pn_Lg_TransaksiLayout.setHorizontalGroup(
            pn_Lg_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Lg_TransaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Lb_Logo_Distributor2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        pn_Lg_TransaksiLayout.setVerticalGroup(
            pn_Lg_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Lg_TransaksiLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(Lb_Logo_Distributor2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Lb_Transaksi.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        Lb_Transaksi.setText("Transaksi");

        Lb_Jumlah_Transaksi.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        Lb_Jumlah_Transaksi.setText("0");

        javax.swing.GroupLayout pn_Card_TransaksiLayout = new javax.swing.GroupLayout(pn_Card_Transaksi);
        pn_Card_Transaksi.setLayout(pn_Card_TransaksiLayout);
        pn_Card_TransaksiLayout.setHorizontalGroup(
            pn_Card_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Card_TransaksiLayout.createSequentialGroup()
                .addComponent(pn_Lg_Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_Card_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Card_TransaksiLayout.createSequentialGroup()
                        .addComponent(Lb_Jumlah_Transaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(63, 63, 63))
                    .addGroup(pn_Card_TransaksiLayout.createSequentialGroup()
                        .addComponent(Lb_Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(62, Short.MAX_VALUE))))
        );
        pn_Card_TransaksiLayout.setVerticalGroup(
            pn_Card_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_Lg_Transaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pn_Card_TransaksiLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(Lb_Transaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Lb_Jumlah_Transaksi)
                .addContainerGap())
        );

        pn_Card_Karyawan.setBackground(new java.awt.Color(255, 255, 255));
        pn_Card_Karyawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pn_Card_KaryawanMouseClicked(evt);
            }
        });

        pn_Lg_Karyawan.setBackground(new java.awt.Color(3, 0, 126));

        Lb_Logo_Barang1.setForeground(new java.awt.Color(255, 255, 255));
        Lb_Logo_Barang1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Lb_Logo_Barang1.setText("Logo");

        javax.swing.GroupLayout pn_Lg_KaryawanLayout = new javax.swing.GroupLayout(pn_Lg_Karyawan);
        pn_Lg_Karyawan.setLayout(pn_Lg_KaryawanLayout);
        pn_Lg_KaryawanLayout.setHorizontalGroup(
            pn_Lg_KaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Lg_KaryawanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Lb_Logo_Barang1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        pn_Lg_KaryawanLayout.setVerticalGroup(
            pn_Lg_KaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Lg_KaryawanLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(Lb_Logo_Barang1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Lb_Karyawan.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        Lb_Karyawan.setText("Karyawan");

        Lb_Jumlah_Karyawan.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        Lb_Jumlah_Karyawan.setText("0");

        javax.swing.GroupLayout pn_Card_KaryawanLayout = new javax.swing.GroupLayout(pn_Card_Karyawan);
        pn_Card_Karyawan.setLayout(pn_Card_KaryawanLayout);
        pn_Card_KaryawanLayout.setHorizontalGroup(
            pn_Card_KaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Card_KaryawanLayout.createSequentialGroup()
                .addComponent(pn_Lg_Karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_Card_KaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Card_KaryawanLayout.createSequentialGroup()
                        .addComponent(Lb_Karyawan, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                        .addGap(44, 44, 44))
                    .addGroup(pn_Card_KaryawanLayout.createSequentialGroup()
                        .addComponent(Lb_Jumlah_Karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pn_Card_KaryawanLayout.setVerticalGroup(
            pn_Card_KaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_Lg_Karyawan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pn_Card_KaryawanLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(Lb_Karyawan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Lb_Jumlah_Karyawan)
                .addContainerGap())
        );

        pn_Konten_Tbl_Beranda.setBackground(new java.awt.Color(255, 255, 255));
        pn_Konten_Tbl_Beranda.setLayout(new java.awt.CardLayout());

        pn_Tbl_Beranda_Barang.setBackground(new java.awt.Color(255, 255, 255));

        Tbl_Beranda_Barang.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        Tbl_Beranda_Barang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Kode Barang", "Nama Barang", "Kategori", "Satuan", "Harga Jual", "Jumlah", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tbl_Beranda_Barang.setRowHeight(40);
        Tbl_Beranda_Barang.setSelectionBackground(new java.awt.Color(0, 0, 204));
        jScrollPane7.setViewportView(Tbl_Beranda_Barang);

        Lb_Tbl_Data_Barang.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Tbl_Data_Barang.setText("Data Barang");

        Lb_Beranda_Barang.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        Lb_Beranda_Barang.setText("Barang");

        Btn_Cari_Beranda_Barang.setBackground(new java.awt.Color(0, 0, 153));
        Btn_Cari_Beranda_Barang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Cari_Beranda_Barang.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Cari_Beranda_Barang.setText("Cari");
        Btn_Cari_Beranda_Barang.setMaximumSize(new java.awt.Dimension(82, 21));
        Btn_Cari_Beranda_Barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Cari_Beranda_BarangActionPerformed(evt);
            }
        });

        Field_Cari_Beranda_Barang.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N

        javax.swing.GroupLayout pn_Tbl_Beranda_BarangLayout = new javax.swing.GroupLayout(pn_Tbl_Beranda_Barang);
        pn_Tbl_Beranda_Barang.setLayout(pn_Tbl_Beranda_BarangLayout);
        pn_Tbl_Beranda_BarangLayout.setHorizontalGroup(
            pn_Tbl_Beranda_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Tbl_Beranda_BarangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_Tbl_Beranda_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Tbl_Beranda_BarangLayout.createSequentialGroup()
                        .addGroup(pn_Tbl_Beranda_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane7)
                            .addGroup(pn_Tbl_Beranda_BarangLayout.createSequentialGroup()
                                .addGap(0, 596, Short.MAX_VALUE)
                                .addComponent(Lb_Tbl_Data_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(395, 395, 395)
                                .addComponent(Field_Cari_Beranda_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Btn_Cari_Beranda_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(99, 99, 99))
                    .addGroup(pn_Tbl_Beranda_BarangLayout.createSequentialGroup()
                        .addComponent(Lb_Beranda_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))))
        );
        pn_Tbl_Beranda_BarangLayout.setVerticalGroup(
            pn_Tbl_Beranda_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Tbl_Beranda_BarangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Lb_Beranda_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(pn_Tbl_Beranda_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lb_Tbl_Data_Barang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Btn_Cari_Beranda_Barang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Field_Cari_Beranda_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                .addGap(39, 39, 39))
        );

        pn_Konten_Tbl_Beranda.add(pn_Tbl_Beranda_Barang, "card2");

        pn_Tbl_Beranda_Distributor.setBackground(new java.awt.Color(255, 255, 255));

        Tbl_Beranda_Distributor.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        Tbl_Beranda_Distributor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No", "Kode Distributor", "Nama", "Kota", "Provinsi", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tbl_Beranda_Distributor.setRowHeight(40);
        Tbl_Beranda_Distributor.setSelectionBackground(new java.awt.Color(0, 0, 204));
        jScrollPane8.setViewportView(Tbl_Beranda_Distributor);

        Lb_Tbl_Data_Distributor.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Tbl_Data_Distributor.setText("Data Distributor");

        Lb_Beranda_Distributor.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        Lb_Beranda_Distributor.setText("Distributor");

        Btn_Cari_Beranda_Distributor.setBackground(new java.awt.Color(0, 0, 153));
        Btn_Cari_Beranda_Distributor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Cari_Beranda_Distributor.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Cari_Beranda_Distributor.setText("Cari");
        Btn_Cari_Beranda_Distributor.setMaximumSize(new java.awt.Dimension(82, 21));
        Btn_Cari_Beranda_Distributor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Cari_Beranda_DistributorActionPerformed(evt);
            }
        });

        Field_Cari_Beranda_Distributor.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N

        javax.swing.GroupLayout pn_Tbl_Beranda_DistributorLayout = new javax.swing.GroupLayout(pn_Tbl_Beranda_Distributor);
        pn_Tbl_Beranda_Distributor.setLayout(pn_Tbl_Beranda_DistributorLayout);
        pn_Tbl_Beranda_DistributorLayout.setHorizontalGroup(
            pn_Tbl_Beranda_DistributorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Tbl_Beranda_DistributorLayout.createSequentialGroup()
                .addGroup(pn_Tbl_Beranda_DistributorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pn_Tbl_Beranda_DistributorLayout.createSequentialGroup()
                        .addGap(446, 446, 446)
                        .addComponent(Lb_Tbl_Data_Distributor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(564, 564, 564)
                        .addComponent(Field_Cari_Beranda_Distributor, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Btn_Cari_Beranda_Distributor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pn_Tbl_Beranda_DistributorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Lb_Beranda_Distributor))
                    .addGroup(pn_Tbl_Beranda_DistributorLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane8)))
                .addGap(45, 45, 45))
        );
        pn_Tbl_Beranda_DistributorLayout.setVerticalGroup(
            pn_Tbl_Beranda_DistributorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Tbl_Beranda_DistributorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_Tbl_Beranda_DistributorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Tbl_Beranda_DistributorLayout.createSequentialGroup()
                        .addComponent(Lb_Beranda_Distributor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(Lb_Tbl_Data_Distributor, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Tbl_Beranda_DistributorLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pn_Tbl_Beranda_DistributorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Btn_Cari_Beranda_Distributor, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Field_Cari_Beranda_Distributor, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addGap(28, 28, 28))
        );

        pn_Konten_Tbl_Beranda.add(pn_Tbl_Beranda_Distributor, "card2");

        pn_Tbl_Beranda_Transaksi.setBackground(new java.awt.Color(255, 255, 255));

        Tbl_Beranda_Transaksi.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        Tbl_Beranda_Transaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        Tbl_Beranda_Transaksi.setRowHeight(40);
        jScrollPane9.setViewportView(Tbl_Beranda_Transaksi);

        Lb_Tbl_Data_Transaksi.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Tbl_Data_Transaksi.setText("Data Transaksi");

        Lb_Beranda_Transaksi.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        Lb_Beranda_Transaksi.setText("Transaksi");

        Btn_Cari_Beranda_Transaksi.setBackground(new java.awt.Color(0, 0, 153));
        Btn_Cari_Beranda_Transaksi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Cari_Beranda_Transaksi.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Cari_Beranda_Transaksi.setText("Cari");
        Btn_Cari_Beranda_Transaksi.setMaximumSize(new java.awt.Dimension(82, 21));
        Btn_Cari_Beranda_Transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Cari_Beranda_TransaksiActionPerformed(evt);
            }
        });

        Field_Cari_Beranda_Transaksi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        javax.swing.GroupLayout pn_Tbl_Beranda_TransaksiLayout = new javax.swing.GroupLayout(pn_Tbl_Beranda_Transaksi);
        pn_Tbl_Beranda_Transaksi.setLayout(pn_Tbl_Beranda_TransaksiLayout);
        pn_Tbl_Beranda_TransaksiLayout.setHorizontalGroup(
            pn_Tbl_Beranda_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Tbl_Beranda_TransaksiLayout.createSequentialGroup()
                .addGroup(pn_Tbl_Beranda_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pn_Tbl_Beranda_TransaksiLayout.createSequentialGroup()
                        .addGap(787, 787, 787)
                        .addComponent(Lb_Tbl_Data_Transaksi)
                        .addGap(310, 310, 310)
                        .addComponent(Field_Cari_Beranda_Transaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_Cari_Beranda_Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_Tbl_Beranda_TransaksiLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane9))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pn_Tbl_Beranda_TransaksiLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Lb_Beranda_Transaksi)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(45, 45, 45))
        );
        pn_Tbl_Beranda_TransaksiLayout.setVerticalGroup(
            pn_Tbl_Beranda_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Tbl_Beranda_TransaksiLayout.createSequentialGroup()
                .addGroup(pn_Tbl_Beranda_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Tbl_Beranda_TransaksiLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Lb_Beranda_Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(Lb_Tbl_Data_Transaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Tbl_Beranda_TransaksiLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(Field_Cari_Beranda_Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Tbl_Beranda_TransaksiLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Btn_Cari_Beranda_Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                .addGap(28, 28, 28))
        );

        pn_Konten_Tbl_Beranda.add(pn_Tbl_Beranda_Transaksi, "card2");

        pn_Tbl_Beranda_Karyawan.setBackground(new java.awt.Color(255, 255, 255));

        Tbl_Beranda_Karyawan.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        Tbl_Beranda_Karyawan.setModel(new javax.swing.table.DefaultTableModel(
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
        Tbl_Beranda_Karyawan.setRowHeight(40);
        jScrollPane10.setViewportView(Tbl_Beranda_Karyawan);

        Lb_Tbl_Data_Karyawan.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Tbl_Data_Karyawan.setText("Data Karyawan");

        Lb_Beranda_Karyawan.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        Lb_Beranda_Karyawan.setText("Karyawan");

        Btn_Cari_Beranda_Karyawan.setBackground(new java.awt.Color(0, 0, 153));
        Btn_Cari_Beranda_Karyawan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Cari_Beranda_Karyawan.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Cari_Beranda_Karyawan.setText("Cari");
        Btn_Cari_Beranda_Karyawan.setMaximumSize(new java.awt.Dimension(82, 21));
        Btn_Cari_Beranda_Karyawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Cari_Beranda_KaryawanActionPerformed(evt);
            }
        });

        Field_Cari_Beranda_Karyawan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        javax.swing.GroupLayout pn_Tbl_Beranda_KaryawanLayout = new javax.swing.GroupLayout(pn_Tbl_Beranda_Karyawan);
        pn_Tbl_Beranda_Karyawan.setLayout(pn_Tbl_Beranda_KaryawanLayout);
        pn_Tbl_Beranda_KaryawanLayout.setHorizontalGroup(
            pn_Tbl_Beranda_KaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Tbl_Beranda_KaryawanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Lb_Beranda_Karyawan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pn_Tbl_Beranda_KaryawanLayout.createSequentialGroup()
                .addGroup(pn_Tbl_Beranda_KaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pn_Tbl_Beranda_KaryawanLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 1408, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_Tbl_Beranda_KaryawanLayout.createSequentialGroup()
                        .addGap(446, 446, 446)
                        .addComponent(Lb_Tbl_Data_Karyawan, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                        .addGap(493, 493, 493)
                        .addComponent(Field_Cari_Beranda_Karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_Cari_Beranda_Karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        pn_Tbl_Beranda_KaryawanLayout.setVerticalGroup(
            pn_Tbl_Beranda_KaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Tbl_Beranda_KaryawanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_Tbl_Beranda_KaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Tbl_Beranda_KaryawanLayout.createSequentialGroup()
                        .addComponent(Lb_Beranda_Karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(Lb_Tbl_Data_Karyawan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Tbl_Beranda_KaryawanLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pn_Tbl_Beranda_KaryawanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Btn_Cari_Beranda_Karyawan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Field_Cari_Beranda_Karyawan, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addGap(28, 28, 28))
        );

        pn_Konten_Tbl_Beranda.add(pn_Tbl_Beranda_Karyawan, "card2");

        javax.swing.GroupLayout pn_Konten_BerandaLayout = new javax.swing.GroupLayout(pn_Konten_Beranda);
        pn_Konten_Beranda.setLayout(pn_Konten_BerandaLayout);
        pn_Konten_BerandaLayout.setHorizontalGroup(
            pn_Konten_BerandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Konten_BerandaLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(pn_Konten_BerandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Konten_BerandaLayout.createSequentialGroup()
                        .addComponent(pn_Konten_Tbl_Beranda, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                        .addGap(17, 17, 17))
                    .addGroup(pn_Konten_BerandaLayout.createSequentialGroup()
                        .addComponent(pn_Card_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(pn_Card_Distributor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(pn_Card_Transaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(90, 90, 90)
                        .addComponent(pn_Card_Karyawan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(396, 396, 396))))
        );
        pn_Konten_BerandaLayout.setVerticalGroup(
            pn_Konten_BerandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Konten_BerandaLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pn_Konten_BerandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pn_Card_Karyawan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pn_Card_Transaksi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pn_Card_Barang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pn_Card_Distributor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(126, 126, 126)
                .addComponent(pn_Konten_Tbl_Beranda, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pn_BerandaLayout = new javax.swing.GroupLayout(pn_Beranda);
        pn_Beranda.setLayout(pn_BerandaLayout);
        pn_BerandaLayout.setHorizontalGroup(
            pn_BerandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_BerandaLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(pn_BerandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Lb_Beranda, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pn_Konten_Beranda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        pn_BerandaLayout.setVerticalGroup(
            pn_BerandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_BerandaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(Lb_Beranda, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(pn_Konten_Beranda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(93, 93, 93))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pn_Beranda, javax.swing.GroupLayout.PREFERRED_SIZE, 1738, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pn_Beranda, javax.swing.GroupLayout.DEFAULT_SIZE, 1027, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pn_Card_BarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_Card_BarangMouseClicked
        pn_Konten_Tbl_Beranda.removeAll();
        pn_Konten_Tbl_Beranda.repaint();
        pn_Konten_Tbl_Beranda.revalidate();
        
        pn_Konten_Tbl_Beranda.add(pn_Tbl_Beranda_Barang);
        pn_Konten_Tbl_Beranda.repaint();
        pn_Konten_Tbl_Beranda.revalidate();
    }//GEN-LAST:event_pn_Card_BarangMouseClicked

    private void Btn_Cari_Beranda_BarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Cari_Beranda_BarangActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_Btn_Cari_Beranda_BarangActionPerformed

    private void Btn_Cari_Beranda_DistributorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Cari_Beranda_DistributorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_Cari_Beranda_DistributorActionPerformed

    private void Btn_Cari_Beranda_TransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Cari_Beranda_TransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_Cari_Beranda_TransaksiActionPerformed

    private void Btn_Cari_Beranda_KaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Cari_Beranda_KaryawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_Cari_Beranda_KaryawanActionPerformed

    private void pn_Card_DistributorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_Card_DistributorMouseClicked
        pn_Konten_Tbl_Beranda.removeAll();
        pn_Konten_Tbl_Beranda.repaint();
        pn_Konten_Tbl_Beranda.revalidate();
        
        pn_Konten_Tbl_Beranda.add(pn_Tbl_Beranda_Distributor);
        pn_Konten_Tbl_Beranda.repaint();
        pn_Konten_Tbl_Beranda.revalidate();
    }//GEN-LAST:event_pn_Card_DistributorMouseClicked

    private void pn_Card_TransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_Card_TransaksiMouseClicked
         pn_Konten_Tbl_Beranda.removeAll();
        pn_Konten_Tbl_Beranda.repaint();
        pn_Konten_Tbl_Beranda.revalidate();
        
        pn_Konten_Tbl_Beranda.add(pn_Tbl_Beranda_Transaksi);
        pn_Konten_Tbl_Beranda.repaint();
        pn_Konten_Tbl_Beranda.revalidate();
    }//GEN-LAST:event_pn_Card_TransaksiMouseClicked

    private void pn_Card_KaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pn_Card_KaryawanMouseClicked
         pn_Konten_Tbl_Beranda.removeAll();
        pn_Konten_Tbl_Beranda.repaint();
        pn_Konten_Tbl_Beranda.revalidate();
        
        pn_Konten_Tbl_Beranda.add(pn_Tbl_Beranda_Karyawan);
        pn_Konten_Tbl_Beranda.repaint();
        pn_Konten_Tbl_Beranda.revalidate();
    }//GEN-LAST:event_pn_Card_KaryawanMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Cari_Beranda_Barang;
    private javax.swing.JButton Btn_Cari_Beranda_Distributor;
    private javax.swing.JButton Btn_Cari_Beranda_Karyawan;
    private javax.swing.JButton Btn_Cari_Beranda_Transaksi;
    private javax.swing.JTextField Field_Cari_Beranda_Barang;
    private javax.swing.JTextField Field_Cari_Beranda_Distributor;
    private javax.swing.JTextField Field_Cari_Beranda_Karyawan;
    private javax.swing.JTextField Field_Cari_Beranda_Transaksi;
    private javax.swing.JLabel Lb_Barang;
    private javax.swing.JLabel Lb_Beranda;
    private javax.swing.JLabel Lb_Beranda_Barang;
    private javax.swing.JLabel Lb_Beranda_Distributor;
    private javax.swing.JLabel Lb_Beranda_Karyawan;
    private javax.swing.JLabel Lb_Beranda_Transaksi;
    private javax.swing.JLabel Lb_Distributor;
    private javax.swing.JLabel Lb_Jumlah_Barang;
    private javax.swing.JLabel Lb_Jumlah_Distributor;
    private javax.swing.JLabel Lb_Jumlah_Karyawan;
    private javax.swing.JLabel Lb_Jumlah_Transaksi;
    private javax.swing.JLabel Lb_Karyawan;
    private javax.swing.JLabel Lb_Logo_Barang;
    private javax.swing.JLabel Lb_Logo_Barang1;
    private javax.swing.JLabel Lb_Logo_Distributor;
    private javax.swing.JLabel Lb_Logo_Distributor2;
    private javax.swing.JLabel Lb_Tbl_Data_Barang;
    private javax.swing.JLabel Lb_Tbl_Data_Distributor;
    private javax.swing.JLabel Lb_Tbl_Data_Karyawan;
    private javax.swing.JLabel Lb_Tbl_Data_Transaksi;
    private javax.swing.JLabel Lb_Transaksi;
    private javax.swing.JTable Tbl_Beranda_Barang;
    private javax.swing.JTable Tbl_Beranda_Distributor;
    private javax.swing.JTable Tbl_Beranda_Karyawan;
    private javax.swing.JTable Tbl_Beranda_Transaksi;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPanel pn_Beranda;
    private javax.swing.JPanel pn_Card_Barang;
    private javax.swing.JPanel pn_Card_Distributor;
    private javax.swing.JPanel pn_Card_Karyawan;
    private javax.swing.JPanel pn_Card_Transaksi;
    private javax.swing.JPanel pn_Konten_Beranda;
    private javax.swing.JPanel pn_Konten_Tbl_Beranda;
    private javax.swing.JPanel pn_Lg_Barang;
    private javax.swing.JPanel pn_Lg_Distributor;
    private javax.swing.JPanel pn_Lg_Karyawan;
    private javax.swing.JPanel pn_Lg_Transaksi;
    private javax.swing.JPanel pn_Tbl_Beranda_Barang;
    private javax.swing.JPanel pn_Tbl_Beranda_Distributor;
    private javax.swing.JPanel pn_Tbl_Beranda_Karyawan;
    private javax.swing.JPanel pn_Tbl_Beranda_Transaksi;
    // End of variables declaration//GEN-END:variables
}
