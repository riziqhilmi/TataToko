/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Utama;

import static Utama.Transaksi.txt_kembalian;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;

/**
 *
 * @author acer
 */
public class Laporan_Transaksi extends javax.swing.JPanel {

    /**
     * Creates new form Beranda
     */
    koneksi db = new koneksi();
    java.sql.Connection con;
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/tatatoko";
    private final String user = "root";
    private final String pwd = "";

    public Laporan_Transaksi() {
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

        pn_Laporan_Transaksi = new javax.swing.JPanel();
        Lb_Laporan_Transasksi = new javax.swing.JLabel();
        pn_Konten_Laporan_Transaksi = new javax.swing.JPanel();
        pn_Laporan_Penjualan = new javax.swing.JPanel();
        pn_Ctrl_Laporan_Penjualan = new javax.swing.JPanel();
        Lb_Laporan_Penjualan_Dari_Tanggal = new javax.swing.JLabel();
        Btn_Tambah_Laporan_Penjualan = new javax.swing.JButton();
        Field_Transaksi_Tanggal_Penjualan_Awal = new com.toedter.calendar.JDateChooser();
        Field_Transaksi_Tanggal_Penjualan_Akhir = new com.toedter.calendar.JDateChooser();
        Lb_Laporan_Penjualan_Dari_Tanggal1 = new javax.swing.JLabel();
        Lb_Laporan_Penjualan = new javax.swing.JLabel();
        pn_Konten_Penjualan = new javax.swing.JPanel();
        pn_Laporan_Pendapatan = new javax.swing.JPanel();
        pn_Ctrl_Laporan_Pendapatan = new javax.swing.JPanel();
        Lb_Laporan_Pendapatan_Dari_Tanggal = new javax.swing.JLabel();
        Btn_Tambah_Laporan_Pendapatan = new javax.swing.JButton();
        Field_Transaksi_Tanggal_Pendapatan_Awal = new com.toedter.calendar.JDateChooser();
        Field_Transaksi_Tanggal_Pendapatan_Akhir = new com.toedter.calendar.JDateChooser();
        Lb_Laporan_Pendapatan_Sampai_Tanggal = new javax.swing.JLabel();
        Lb_Laporan_Pendapatan = new javax.swing.JLabel();
        pn_Konten_Pendapatan = new javax.swing.JPanel();
        Btn_Laporan_Penjualan = new javax.swing.JButton();
        Btn_Laporan_Pendapatan = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1182, 686));

        pn_Laporan_Transaksi.setBackground(new java.awt.Color(255, 255, 255));
        pn_Laporan_Transaksi.setPreferredSize(new java.awt.Dimension(1182, 686));

        Lb_Laporan_Transasksi.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        Lb_Laporan_Transasksi.setText("Laporan Transaksi");

        pn_Konten_Laporan_Transaksi.setLayout(new java.awt.CardLayout());

        pn_Ctrl_Laporan_Penjualan.setBackground(new java.awt.Color(255, 255, 255));

        Lb_Laporan_Penjualan_Dari_Tanggal.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        Lb_Laporan_Penjualan_Dari_Tanggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Laporan_Penjualan_Dari_Tanggal.setText("Tanggal :");

        Btn_Tambah_Laporan_Penjualan.setBackground(new java.awt.Color(0, 0, 204));
        Btn_Tambah_Laporan_Penjualan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Btn_Tambah_Laporan_Penjualan.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Tambah_Laporan_Penjualan.setText("Tambah");
        Btn_Tambah_Laporan_Penjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Tambah_Laporan_PenjualanActionPerformed(evt);
            }
        });

        Field_Transaksi_Tanggal_Penjualan_Awal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        Field_Transaksi_Tanggal_Penjualan_Akhir.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        Lb_Laporan_Penjualan_Dari_Tanggal1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Laporan_Penjualan_Dari_Tanggal1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Laporan_Penjualan_Dari_Tanggal1.setText("Sampai");

        javax.swing.GroupLayout pn_Ctrl_Laporan_PenjualanLayout = new javax.swing.GroupLayout(pn_Ctrl_Laporan_Penjualan);
        pn_Ctrl_Laporan_Penjualan.setLayout(pn_Ctrl_Laporan_PenjualanLayout);
        pn_Ctrl_Laporan_PenjualanLayout.setHorizontalGroup(
            pn_Ctrl_Laporan_PenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Ctrl_Laporan_PenjualanLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(Btn_Tambah_Laporan_Penjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Ctrl_Laporan_PenjualanLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pn_Ctrl_Laporan_PenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Lb_Laporan_Penjualan_Dari_Tanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Lb_Laporan_Penjualan_Dari_Tanggal1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_Ctrl_Laporan_PenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Field_Transaksi_Tanggal_Penjualan_Akhir, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addComponent(Field_Transaksi_Tanggal_Penjualan_Awal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(170, 170, 170))
        );
        pn_Ctrl_Laporan_PenjualanLayout.setVerticalGroup(
            pn_Ctrl_Laporan_PenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Ctrl_Laporan_PenjualanLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(pn_Ctrl_Laporan_PenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Lb_Laporan_Penjualan_Dari_Tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Field_Transaksi_Tanggal_Penjualan_Awal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(pn_Ctrl_Laporan_PenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Lb_Laporan_Penjualan_Dari_Tanggal1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Field_Transaksi_Tanggal_Penjualan_Akhir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(127, 127, 127)
                .addComponent(Btn_Tambah_Laporan_Penjualan, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addGap(32, 32, 32))
        );

        Lb_Laporan_Penjualan.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        Lb_Laporan_Penjualan.setText("Penjualan");

        pn_Konten_Penjualan.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pn_Konten_PenjualanLayout = new javax.swing.GroupLayout(pn_Konten_Penjualan);
        pn_Konten_Penjualan.setLayout(pn_Konten_PenjualanLayout);
        pn_Konten_PenjualanLayout.setHorizontalGroup(
            pn_Konten_PenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        pn_Konten_PenjualanLayout.setVerticalGroup(
            pn_Konten_PenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 665, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pn_Laporan_PenjualanLayout = new javax.swing.GroupLayout(pn_Laporan_Penjualan);
        pn_Laporan_Penjualan.setLayout(pn_Laporan_PenjualanLayout);
        pn_Laporan_PenjualanLayout.setHorizontalGroup(
            pn_Laporan_PenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Laporan_PenjualanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_Laporan_PenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Lb_Laporan_Penjualan)
                    .addGroup(pn_Laporan_PenjualanLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(pn_Ctrl_Laporan_Penjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(pn_Konten_Penjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        pn_Laporan_PenjualanLayout.setVerticalGroup(
            pn_Laporan_PenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Laporan_PenjualanLayout.createSequentialGroup()
                .addGroup(pn_Laporan_PenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Laporan_PenjualanLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(Lb_Laporan_Penjualan)
                        .addGap(18, 18, 18)
                        .addComponent(pn_Ctrl_Laporan_Penjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_Laporan_PenjualanLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(pn_Konten_Penjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pn_Konten_Laporan_Transaksi.add(pn_Laporan_Penjualan, "card2");

        pn_Ctrl_Laporan_Pendapatan.setBackground(new java.awt.Color(255, 255, 255));

        Lb_Laporan_Pendapatan_Dari_Tanggal.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        Lb_Laporan_Pendapatan_Dari_Tanggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Laporan_Pendapatan_Dari_Tanggal.setText("Tanggal :");

        Btn_Tambah_Laporan_Pendapatan.setBackground(new java.awt.Color(0, 0, 204));
        Btn_Tambah_Laporan_Pendapatan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Btn_Tambah_Laporan_Pendapatan.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Tambah_Laporan_Pendapatan.setText("Tambah");
        Btn_Tambah_Laporan_Pendapatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Tambah_Laporan_PendapatanActionPerformed(evt);
            }
        });

        Field_Transaksi_Tanggal_Pendapatan_Awal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        Field_Transaksi_Tanggal_Pendapatan_Akhir.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        Lb_Laporan_Pendapatan_Sampai_Tanggal.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Laporan_Pendapatan_Sampai_Tanggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Laporan_Pendapatan_Sampai_Tanggal.setText("Sampai");

        javax.swing.GroupLayout pn_Ctrl_Laporan_PendapatanLayout = new javax.swing.GroupLayout(pn_Ctrl_Laporan_Pendapatan);
        pn_Ctrl_Laporan_Pendapatan.setLayout(pn_Ctrl_Laporan_PendapatanLayout);
        pn_Ctrl_Laporan_PendapatanLayout.setHorizontalGroup(
            pn_Ctrl_Laporan_PendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Ctrl_Laporan_PendapatanLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(Btn_Tambah_Laporan_Pendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Ctrl_Laporan_PendapatanLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pn_Ctrl_Laporan_PendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Lb_Laporan_Pendapatan_Dari_Tanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Lb_Laporan_Pendapatan_Sampai_Tanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_Ctrl_Laporan_PendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Field_Transaksi_Tanggal_Pendapatan_Akhir, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addComponent(Field_Transaksi_Tanggal_Pendapatan_Awal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(170, 170, 170))
        );
        pn_Ctrl_Laporan_PendapatanLayout.setVerticalGroup(
            pn_Ctrl_Laporan_PendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Ctrl_Laporan_PendapatanLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(pn_Ctrl_Laporan_PendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Lb_Laporan_Pendapatan_Dari_Tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Field_Transaksi_Tanggal_Pendapatan_Awal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(pn_Ctrl_Laporan_PendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Lb_Laporan_Pendapatan_Sampai_Tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Field_Transaksi_Tanggal_Pendapatan_Akhir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(127, 127, 127)
                .addComponent(Btn_Tambah_Laporan_Pendapatan, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addGap(32, 32, 32))
        );

        Lb_Laporan_Pendapatan.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        Lb_Laporan_Pendapatan.setText("Pendapatan");

        pn_Konten_Pendapatan.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pn_Konten_PendapatanLayout = new javax.swing.GroupLayout(pn_Konten_Pendapatan);
        pn_Konten_Pendapatan.setLayout(pn_Konten_PendapatanLayout);
        pn_Konten_PendapatanLayout.setHorizontalGroup(
            pn_Konten_PendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        pn_Konten_PendapatanLayout.setVerticalGroup(
            pn_Konten_PendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 665, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pn_Laporan_PendapatanLayout = new javax.swing.GroupLayout(pn_Laporan_Pendapatan);
        pn_Laporan_Pendapatan.setLayout(pn_Laporan_PendapatanLayout);
        pn_Laporan_PendapatanLayout.setHorizontalGroup(
            pn_Laporan_PendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Laporan_PendapatanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_Laporan_PendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Lb_Laporan_Pendapatan)
                    .addGroup(pn_Laporan_PendapatanLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(pn_Ctrl_Laporan_Pendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(pn_Konten_Pendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        pn_Laporan_PendapatanLayout.setVerticalGroup(
            pn_Laporan_PendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Laporan_PendapatanLayout.createSequentialGroup()
                .addGroup(pn_Laporan_PendapatanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Laporan_PendapatanLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(Lb_Laporan_Pendapatan)
                        .addGap(18, 18, 18)
                        .addComponent(pn_Ctrl_Laporan_Pendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_Laporan_PendapatanLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(pn_Konten_Pendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pn_Konten_Laporan_Transaksi.add(pn_Laporan_Pendapatan, "card2");

        Btn_Laporan_Penjualan.setBackground(new java.awt.Color(3, 0, 126));
        Btn_Laporan_Penjualan.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        Btn_Laporan_Penjualan.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Laporan_Penjualan.setText("Penjualan");
        Btn_Laporan_Penjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Btn_Laporan_PenjualanMouseClicked(evt);
            }
        });

        Btn_Laporan_Pendapatan.setBackground(new java.awt.Color(3, 0, 126));
        Btn_Laporan_Pendapatan.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        Btn_Laporan_Pendapatan.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Laporan_Pendapatan.setText("Pendapatan");
        Btn_Laporan_Pendapatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Btn_Laporan_PendapatanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pn_Laporan_TransaksiLayout = new javax.swing.GroupLayout(pn_Laporan_Transaksi);
        pn_Laporan_Transaksi.setLayout(pn_Laporan_TransaksiLayout);
        pn_Laporan_TransaksiLayout.setHorizontalGroup(
            pn_Laporan_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Laporan_TransaksiLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(pn_Laporan_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Laporan_TransaksiLayout.createSequentialGroup()
                        .addComponent(Btn_Laporan_Penjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(Btn_Laporan_Pendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Lb_Laporan_Transasksi)
                    .addComponent(pn_Konten_Laporan_Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 1630, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pn_Laporan_TransaksiLayout.setVerticalGroup(
            pn_Laporan_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Laporan_TransaksiLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(Lb_Laporan_Transasksi)
                .addGap(45, 45, 45)
                .addGroup(pn_Laporan_TransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pn_Laporan_TransaksiLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(Btn_Laporan_Penjualan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(Btn_Laporan_Pendapatan, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_Konten_Laporan_Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_Laporan_Transaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 1686, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pn_Laporan_Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 1008, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_Laporan_PenjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Btn_Laporan_PenjualanMouseClicked
        pn_Konten_Laporan_Transaksi.removeAll();
        pn_Konten_Laporan_Transaksi.repaint();
        pn_Konten_Laporan_Transaksi.revalidate();

        pn_Konten_Laporan_Transaksi.add(pn_Laporan_Penjualan);
        pn_Konten_Laporan_Transaksi.repaint();
        pn_Konten_Laporan_Transaksi.revalidate();
    }//GEN-LAST:event_Btn_Laporan_PenjualanMouseClicked

    private void Btn_Laporan_PendapatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Btn_Laporan_PendapatanMouseClicked
        pn_Konten_Laporan_Transaksi.removeAll();
        pn_Konten_Laporan_Transaksi.repaint();
        pn_Konten_Laporan_Transaksi.revalidate();

        pn_Konten_Laporan_Transaksi.add(pn_Laporan_Pendapatan);
        pn_Konten_Laporan_Transaksi.repaint();
        pn_Konten_Laporan_Transaksi.revalidate();
    }//GEN-LAST:event_Btn_Laporan_PendapatanMouseClicked

    private void Btn_Tambah_Laporan_PenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Tambah_Laporan_PenjualanActionPerformed
        try {
            String file = "/Report/Transaksi_Penjualan.jasper";

            // Memuat kelas driver JDBC
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);

            // Membuat objek parameter
            HashMap<String, Object> param = new HashMap<>();

            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String tanggalakhir = String.valueOf(date.format(Field_Transaksi_Tanggal_Penjualan_Akhir.getDate()));
        String tanggalawal = String.valueOf(date.format(Field_Transaksi_Tanggal_Penjualan_Awal.getDate()));    
        
            // Mengambil tanggal awal dan akhir dari JDateChooser
            param.put("daritgl", tanggalawal);
            param.put("sampaitgl", tanggalakhir);

            // Mengisi laporan dengan data dari database
            JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream(file), param, con);

            // Menghapus konten panel sebelumnya
            pn_Konten_Penjualan.removeAll();
            pn_Konten_Penjualan.setLayout(new java.awt.BorderLayout());

            // Membuat JRViewer untuk menampilkan laporan
            JRViewer viewer = new JRViewer(print);

            // Menambahkan JRViewer ke panel
            pn_Konten_Penjualan.add(viewer, java.awt.BorderLayout.CENTER);

            // Menetapkan ukuran preferensi agar JRViewer sesuai dengan panel
            viewer.setPreferredSize(pn_Konten_Penjualan.getSize());

            viewer.setZoomRatio(0.75f);

            // Refresh panel
            pn_Konten_Penjualan.revalidate();
            pn_Konten_Penjualan.repaint();

        } catch (ClassNotFoundException | JRException | SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_Btn_Tambah_Laporan_PenjualanActionPerformed

    private void Btn_Tambah_Laporan_PendapatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Tambah_Laporan_PendapatanActionPerformed
    try {
            String file = "/Report/Laporan_Pendapatan.jasper";

            // Memuat kelas driver JDBC
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);

            // Membuat objek parameter
             HashMap<String, Object> param = new HashMap<>();

        // Mengambil tanggal awal dan akhir dari JDateChooser
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String tanggalawal = date.format(Field_Transaksi_Tanggal_Pendapatan_Awal.getDate());
        String tanggalakhir = date.format(Field_Transaksi_Tanggal_Pendapatan_Akhir.getDate());

        // Memasukkan parameter tanggal ke dalam hashmap
        param.put("daritanggal", tanggalawal);
        param.put("sampaitanggal", tanggalakhir);

        // Mengisi laporan dengan data dari database
        JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream(file), param, con);

        // Menghapus konten panel sebelumnya
        pn_Konten_Pendapatan.removeAll();
        pn_Konten_Pendapatan.setLayout(new java.awt.BorderLayout());

        // Membuat JRViewer untuk menampilkan laporan
        JRViewer viewer = new JRViewer(print);

        // Menambahkan JRViewer ke panel
        pn_Konten_Pendapatan.add(viewer, java.awt.BorderLayout.CENTER);

        // Menetapkan ukuran preferensi agar JRViewer sesuai dengan panel
        viewer.setPreferredSize(pn_Konten_Pendapatan.getSize());

        // Mengatur zoom level JRViewer
        viewer.setZoomRatio(0.75f);

        // Refresh panel
        pn_Konten_Pendapatan.revalidate();
        pn_Konten_Pendapatan.repaint();

    } catch (ClassNotFoundException | JRException | SQLException e) {
        System.out.println(e);
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_Btn_Tambah_Laporan_PendapatanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Laporan_Pendapatan;
    private javax.swing.JButton Btn_Laporan_Penjualan;
    private javax.swing.JButton Btn_Tambah_Laporan_Pendapatan;
    private javax.swing.JButton Btn_Tambah_Laporan_Penjualan;
    private com.toedter.calendar.JDateChooser Field_Transaksi_Tanggal_Pendapatan_Akhir;
    private com.toedter.calendar.JDateChooser Field_Transaksi_Tanggal_Pendapatan_Awal;
    private com.toedter.calendar.JDateChooser Field_Transaksi_Tanggal_Penjualan_Akhir;
    private com.toedter.calendar.JDateChooser Field_Transaksi_Tanggal_Penjualan_Awal;
    private javax.swing.JLabel Lb_Laporan_Pendapatan;
    private javax.swing.JLabel Lb_Laporan_Pendapatan_Dari_Tanggal;
    private javax.swing.JLabel Lb_Laporan_Pendapatan_Sampai_Tanggal;
    private javax.swing.JLabel Lb_Laporan_Penjualan;
    private javax.swing.JLabel Lb_Laporan_Penjualan_Dari_Tanggal;
    private javax.swing.JLabel Lb_Laporan_Penjualan_Dari_Tanggal1;
    private javax.swing.JLabel Lb_Laporan_Transasksi;
    private javax.swing.JPanel pn_Ctrl_Laporan_Pendapatan;
    private javax.swing.JPanel pn_Ctrl_Laporan_Penjualan;
    private javax.swing.JPanel pn_Konten_Laporan_Transaksi;
    private javax.swing.JPanel pn_Konten_Pendapatan;
    private javax.swing.JPanel pn_Konten_Penjualan;
    private javax.swing.JPanel pn_Laporan_Pendapatan;
    private javax.swing.JPanel pn_Laporan_Penjualan;
    private javax.swing.JPanel pn_Laporan_Transaksi;
    // End of variables declaration//GEN-END:variables
}
