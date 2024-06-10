/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Utama;

import com.barcodelib.barcode.Linear;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Barang extends javax.swing.JPanel {

    Connection con;
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/tatatoko";
    private final String user = "root";
    private final String pwd = "";

    koneksi db = new koneksi();
    DefaultTableModel model = new DefaultTableModel();

    /**
     * Creates new form Barang
     */
    public Barang() {
        initComponents();
        getColumn();
        getData();
        autoIn();
        tanggal();
        distributor();
        search();
        ViewBarcode();
        kategori();
        satuan();
        status();

        //tampilkan();
        Field_Tambah_Kategori.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Pastikan hanya menangani event saat item dipilih (bukan saat dibatalkan)
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    autoIn();
                }
            }
        });

    }

    private void ensureDirectoryExists(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs(); // Buat direktori beserta subdirektori jika belum ada
        }
    }

    public void ViewBarcode() {
        String barcodeImg = Field_Tambah_Kode_Barang.getText(); // ambil nilai dari kode barang
        String barcodeFilePath = generateBarcode(barcodeImg, "D:/barcodes/");

        if (barcodeFilePath != null) {
            try {
                BufferedImage barcode = ImageIO.read(new File(barcodeFilePath));
                ImageIcon icon = new ImageIcon(barcode);
                Field_Tambah_Barcode.setIcon(icon);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Gagal membuat atau memuat barcode");
        }
    }

    public void getBarcodeImage(int width, int height, JLabel label, String path) {
        try {
            File file = new File("D:/" + path + ".png");
            BufferedImage bi = ImageIO.read(file);
            Image i = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon ii = new ImageIcon(i);
            label.setIcon(ii);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String generateBarcode(String barcodeIMG, String savePath) {
        int lebar = 290;
        int tinggi = 100;
        String filePath = barcodeIMG;

        // Pastikan direktori ada
        ensureDirectoryExists(savePath);

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(
                    barcodeIMG,
                    BarcodeFormat.CODE_128,
                    lebar,
                    tinggi
            );

            BufferedImage barcode = new BufferedImage(lebar, tinggi, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < lebar; x++) {
                for (int y = 0; y < tinggi; y++) {
                    barcode.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            // Simpan gambar barcode ke file
            File outputfile = new File(filePath);
            ImageIO.write(barcode, "png", outputfile);

            return filePath;
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void showBarcode(java.awt.event.ActionEvent evt) {
        showBarcode();
    }

    public void autoIn() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);

            // Ambil kategori yang dipilih dari combobox
            String selectedCategory = (String) Field_Tambah_Kategori.getSelectedItem();
            String prefix = ""; // Inisialisasi awalan kode barang

            if (selectedCategory != null) {
                String categoryQuery = "SELECT kode_unik FROM kategori WHERE nama = ?";
                PreparedStatement categoryStmt = con.prepareStatement(categoryQuery);
                categoryStmt.setString(1, selectedCategory);
                ResultSet categoryRs = categoryStmt.executeQuery();
                if (categoryRs.next()) {
                    prefix = categoryRs.getString("kode_unik");
                    if (prefix.equals("")) {
                        prefix = "";
                    }
                } else {
                    prefix = "D";
                }
                categoryRs.close();
                categoryStmt.close();
            }

            if (!prefix.isEmpty()) {
                // Query untuk mendapatkan nomor urut terbesar dari id_barang di tabel barang
                String sqlquery = "SELECT MAX(RIGHT(id_barang, 4)) AS no_auto FROM barang WHERE id_barang LIKE ?";
                PreparedStatement st = con.prepareStatement(sqlquery);
                st.setString(1, prefix + "%");
                ResultSet rs = st.executeQuery();
                if (rs.next() && rs.getString("no_auto") != null) {
                    String no_a, no_p;
                    no_a = Integer.toString(rs.getInt(1) + 1);
                    int p = no_a.length();
                    no_p = "";
                    for (int i = 1; i <= 4 - p; i++) {
                        no_p = no_p + "0";
                    }
                    Field_Tambah_Kode_Barang.setText(prefix + no_p + no_a);
                } else {
                    Field_Tambah_Kode_Barang.setText(prefix + "0001"); // Jika tidak ada id_barang dengan prefix tersebut, mulai dari 0001
                }
                rs.close();
                st.close();
            } else {
                Field_Tambah_Kode_Barang.setText("");
            }
        } catch (Exception e) {
            Field_Tambah_Kode_Barang.setText("ST0001"); // Jika terjadi kesalahan, isi dengan kode default
            e.printStackTrace();
        }
    }

    public void getColumn() {
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
        Tbl_Barang_Tambah.setModel(model);
        Tbl_Barang_Detail_Barang.setModel(model);
        jTable3.setModel(model);

    }

    public void getData() {

        ResultSet hasil = db.ambilData("SELECT * FROM barang");
        try {
            while (hasil.next()) {
                model.addRow(new Object[]{hasil.getString("id_barang"),
                    hasil.getString("nama_barang"), hasil.getString("jenis"),
                    hasil.getString("jumlah"), hasil.getString("harga_beli"),
                    hasil.getString("harga_jual"), hasil.getString("tanggal"),
                    hasil.getString("expired"), hasil.getString("satuan"),
                    hasil.getString("status"), hasil.getString("catatan"),
                    hasil.getString("barcode"), hasil.getString("merk")});
            }
            Tbl_Barang_Tambah.setModel(model);
            Tbl_Barang_Detail_Barang.setModel(model);
            jTable3.setModel(model);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void kategori() {
        ResultSet hasil = db.ambilData("SELECT * FROM kategori");
        try {
            while (hasil.next()) {
                // Ambil nilai dari kolom "nama" dan tambahkan ke dalam JComboBox
                Field_Tambah_Kategori.addItem(hasil.getString("nama"));
                Field_Update_Kategori.addItem(hasil.getString("nama"));

            }
        } catch (Exception e) {
            System.out.println("Tidak Dapat Mengambil Data");
        }

    }

    public void satuan() {
        ResultSet hasil = db.ambilData("SELECT * FROM satuan");
        try {
            while (hasil.next()) {
                // Ambil nilai dari kolom "nama" dan tambahkan ke dalam JComboBox
                Field_Tambah_Satuan.addItem(hasil.getString("nama"));
                Field_Update_Satuan.addItem(hasil.getString("nama"));

            }
        } catch (Exception e) {
            System.out.println("Tidak Dapat Mengambil Data");
        }

    }

    public void status() {
        ResultSet hasil = db.ambilData("SELECT * FROM status WHERE kategori = 'Barang'");
        try {
            while (hasil.next()) {
                // Ambil nilai dari kolom "nama" dan tambahkan ke dalam JComboBox
                Field_Tambah_Status.addItem(hasil.getString("nama"));
                Field_Update_Status.addItem(hasil.getString("nama"));
            }
        } catch (Exception e) {
            System.out.println("Tidak Dapat Mengambil Data");
        }

    }

    public void tanggal() {
        Date now = new Date();
        Field_Tambah_Tanggal_Masuk.setDate(now);
        Field_Tambah_Kadaluwarsa.setDateFormatString(driver);
        Field_Update_Tanggal_Masuk.setDate(now);
        Field_Update_Kadaluwarsa.setDateFormatString(driver);

    }

    public void search() {
        Cari_BarangDetail.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterData(Cari_BarangDetail.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterData(Cari_BarangDetail.getText()); // Call filterData on removeUpdate
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterData(Cari_BarangDetail.getText()); // Call filterData on changedUpdate
            }

            private void filterData(String keyword) {
                TableRowSorter<TableModel> sorter = new TableRowSorter<>(Tbl_Barang_Detail_Barang.getModel());
                Tbl_Barang_Detail_Barang.setRowSorter(sorter);

                if (keyword.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword)); // Filter data sesuai dengan kata kunci (ignore case)
                }
            }
        });

        Field_Barang_Update_Cari.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterData(Field_Barang_Update_Cari.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterData(Field_Barang_Update_Cari.getText()); // Call filterData on removeUpdate
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterData(Field_Barang_Update_Cari.getText()); // Call filterData on changedUpdate
            }

            private void filterData(String keyword) {
                TableRowSorter<TableModel> sorter = new TableRowSorter<>(jTable3.getModel());
                jTable3.setRowSorter(sorter);

                if (keyword.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword)); // Filter data sesuai dengan kata kunci (ignore case)
                }
            }
        });
    }

    public void distributor() {
        ResultSet hasil = db.ambilData("SELECT * FROM distributor");
        try {
            while (hasil.next()) {
                // Ambil nilai dari kolom "nama" dan tambahkan ke dalam JComboBox
                Field_Tambah_Kode_Distributor.addItem(hasil.getString("nama"));
                Field_Update_Kode_Distributor.addItem(hasil.getString("nama"));

            }
        } catch (Exception e) {
            System.out.println("Tidak Dapat Mengambil Data");
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

        pn_Konten_Barang = new javax.swing.JPanel();
        pn_Tbl_Barang = new javax.swing.JPanel();
        Lb_Barang = new javax.swing.JLabel();
        pn_ctrl_Detail = new javax.swing.JPanel();
        Btn_Barang_Detail = new rojerusan.RSMaterialButtonRectangle();
        Btn_Barang_Tambah = new rojerusan.RSMaterialButtonRectangle();
        Btn_Barang_Update = new rojerusan.RSMaterialButtonRectangle();
        Cari_Barang_Btn = new javax.swing.JButton();
        Cari_BarangDetail = new javax.swing.JTextField();
        Tbl_Detail = new javax.swing.JScrollPane();
        Tbl_Barang_Detail_Barang = new javax.swing.JTable();
        Lb_Tbl_Detail = new javax.swing.JLabel();
        pn_Tbl_Barang_Tambah = new javax.swing.JPanel();
        Lb_Barang_Tambah1 = new javax.swing.JLabel();
        Lb_Barang_Tambah2 = new javax.swing.JLabel();
        Lb_Barang_Tambah3 = new javax.swing.JLabel();
        Scrl_Tambah_Barang = new javax.swing.JScrollPane();
        Tbl_Barang_Tambah = new javax.swing.JTable();
        pn_Tambah_Barang = new javax.swing.JPanel();
        Field_Tambah_Kode_Barang = new javax.swing.JTextField();
        Lb_Tambah_Kode_Barang = new javax.swing.JLabel();
        Lb_Tambah_Tanggal_Masuk = new javax.swing.JLabel();
        Field_Tambah_Harga_Beli = new javax.swing.JTextField();
        Lb_Tambah_Harga_Beli = new javax.swing.JLabel();
        Field_Tambah_Harga_Jual = new javax.swing.JTextField();
        Lb_Tambah_Harga_Jual = new javax.swing.JLabel();
        Lb_Tambah_Nama_Barang = new javax.swing.JLabel();
        Field_Tambah_Nama_Barang = new javax.swing.JTextField();
        Lb_Tambah_Kadaluwarsa = new javax.swing.JLabel();
        Lb_Tambah_Merk = new javax.swing.JLabel();
        Field_Tambah_Merk = new javax.swing.JTextField();
        Lb_Tambah_Jumlah = new javax.swing.JLabel();
        Field_Tambah_Jumlah = new javax.swing.JTextField();
        Lb_Tambah_Kategori = new javax.swing.JLabel();
        Field_Tambah_Kategori = new javax.swing.JComboBox<>();
        Lb_Tambah_Satuan = new javax.swing.JLabel();
        Field_Tambah_Satuan = new javax.swing.JComboBox<>();
        Lb_Tambah_Status = new javax.swing.JLabel();
        Field_Tambah_Status = new javax.swing.JComboBox<>();
        Lb_Tambah_Kode_Distributor = new javax.swing.JLabel();
        Field_Tambah_Kode_Distributor = new javax.swing.JComboBox<>();
        Lb_Tambah_Barcode1 = new javax.swing.JLabel();
        jScrollPane_Tambah_Catatan = new javax.swing.JScrollPane();
        Field_Tambah_Catatan = new javax.swing.JTextArea();
        Btn_Tambah_Simpan = new rojerusan.RSMaterialButtonRectangle();
        Btn_Tambah_Kembali = new rojerusan.RSMaterialButtonRectangle();
        Field_Tambah_Tanggal_Masuk = new com.toedter.calendar.JDateChooser();
        Field_Tambah_Kadaluwarsa = new com.toedter.calendar.JDateChooser();
        Field_Tambah_Barcode = new javax.swing.JLabel();
        Btn_Tambah_Barcode = new rojerusan.RSMaterialButtonRectangle();
        Lb_Tambah_Barcode2 = new javax.swing.JLabel();
        Lb_Data_Barang_Tambah = new javax.swing.JLabel();
        pn_Tbl_Barang_Detail = new javax.swing.JPanel();
        Lb_Barang_Detail4 = new javax.swing.JLabel();
        Lb_Barang_Detail5 = new javax.swing.JLabel();
        Lb_Barang_Detail6 = new javax.swing.JLabel();
        pn_Konten_Barang_Detail = new javax.swing.JPanel();
        pn_Informasi_Barang_Detail = new javax.swing.JPanel();
        Lb_Informasi_Nama_Barang = new javax.swing.JLabel();
        Lb_Informasi_Kode_Barang = new javax.swing.JLabel();
        Lb_Informasi_Terjual = new javax.swing.JLabel();
        Lb_Informasi_Stok = new javax.swing.JLabel();
        Lb_Informasi_Kadaluwarsa = new javax.swing.JLabel();
        Lb_Informasi_Terjual_Jumlah = new javax.swing.JLabel();
        Lb_Informasi_Stok_Jumlah = new javax.swing.JLabel();
        Lb_Informasi_exp = new javax.swing.JLabel();
        Btn_Barang_Detail_Update = new rojerusan.RSMaterialButtonRectangle();
        Lb_Informasi_Tanggal_Masuk_Detail = new javax.swing.JLabel();
        FIeld_Informasi_Tanggal_Masuk_Detail = new javax.swing.JTextField();
        Lb_Informasi_Kode_Distributor_Detail = new javax.swing.JLabel();
        FIeld_Informasi_Kode_Distributor_Detail = new javax.swing.JTextField();
        Lb_Informasi_Brand_Merk_Detail = new javax.swing.JLabel();
        FIeld_Informasi_Brand_Merk_Detail = new javax.swing.JTextField();
        Lb_Informasi_Kategori_Detail = new javax.swing.JLabel();
        FIeld_Informasi_Kategori_Detail = new javax.swing.JTextField();
        Lb_Informasi_Satuan_Detail = new javax.swing.JLabel();
        FIeld_Informasi_Satuan_Detail = new javax.swing.JTextField();
        Lb_Informasi_Harga_Beli_Detail = new javax.swing.JLabel();
        FIeld_Informasi_Harga_Beli_Detail = new javax.swing.JTextField();
        Lb_Informasi_Harga_Jual_Detail = new javax.swing.JLabel();
        FIeld_Informasi_Harga_Jual_Detail = new javax.swing.JTextField();
        Lb_Informasi_Status_Detail = new javax.swing.JLabel();
        FIeld_Informasi_Status_Detail = new javax.swing.JTextField();
        Lb_Informasi_Catatan_Detail = new javax.swing.JLabel();
        Lb_Informasi_Barcode_Detail = new javax.swing.JLabel();
        FIeld_Informasi_Barcode_Detail = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        FIeld_Informasi_Catatan_Detail = new javax.swing.JTextArea();
        Btn_Barang_Detail_Kembali = new rojerusan.RSMaterialButtonRectangle();
        pn_Tbl_Barang_Update = new javax.swing.JPanel();
        Lb_Barang_Update1 = new javax.swing.JLabel();
        Lb_Barang_Update2 = new javax.swing.JLabel();
        Lb_Barang_Update3 = new javax.swing.JLabel();
        pn_Konten_Barang_Detail1 = new javax.swing.JPanel();
        Btn_Update_Simpan = new rojerusan.RSMaterialButtonRectangle();
        Btn_Update_Kembali = new rojerusan.RSMaterialButtonRectangle();
        Btn_Update_Hapus = new rojerusan.RSMaterialButtonRectangle();
        Field_Update_Kode_Barang = new javax.swing.JTextField();
        Lb_Update_Kode_Barang = new javax.swing.JLabel();
        Lb_Update_Tanggal_Masuk = new javax.swing.JLabel();
        Field_Update_Harga_Beli = new javax.swing.JTextField();
        Lb_Update_Harga_Beli = new javax.swing.JLabel();
        Lb_Update_Harga_Jual = new javax.swing.JLabel();
        Lb_Update_Kadaluwarsa = new javax.swing.JLabel();
        Lb_Update_Jumlah = new javax.swing.JLabel();
        Field_Update_Jumlah = new javax.swing.JTextField();
        Lb_Update_Kategori1 = new javax.swing.JLabel();
        Field_Update_Kategori = new javax.swing.JComboBox<>();
        Lb_Update_Satuan1 = new javax.swing.JLabel();
        Field_Update_Satuan = new javax.swing.JComboBox<>();
        Field_Update_Status = new javax.swing.JComboBox<>();
        Lb_Update_Kode_Distributor = new javax.swing.JLabel();
        Field_Update_Kode_Distributor = new javax.swing.JComboBox<>();
        Lb_Update_Barcode = new javax.swing.JLabel();
        jScrollPane_Update_Catatan = new javax.swing.JScrollPane();
        Field_Update_Catatan = new javax.swing.JTextArea();
        Field_Update_Harga_Jual = new javax.swing.JTextField();
        Field_Update_Nama_Barang = new javax.swing.JTextField();
        Lb_Update_Nama_Barang = new javax.swing.JLabel();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        Field_Update_Kadaluwarsa = new com.toedter.calendar.JDateChooser();
        Field_Update_Tanggal_Masuk = new com.toedter.calendar.JDateChooser();
        Lb_Update_Status = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        Lb_Data_Barang_Update = new javax.swing.JLabel();
        Field_Barang_Update_Cari = new javax.swing.JTextField();
        Btn_Barang_Update_Cari = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1150, 683));
        setLayout(new java.awt.CardLayout());

        pn_Konten_Barang.setPreferredSize(new java.awt.Dimension(1150, 683));
        pn_Konten_Barang.setLayout(new java.awt.CardLayout());

        pn_Tbl_Barang.setBackground(new java.awt.Color(255, 255, 255));
        pn_Tbl_Barang.setPreferredSize(new java.awt.Dimension(1150, 683));

        Lb_Barang.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        Lb_Barang.setText("Barang");

        Btn_Barang_Detail.setBackground(new java.awt.Color(3, 0, 169));
        Btn_Barang_Detail.setText("Detail");
        Btn_Barang_Detail.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        Btn_Barang_Detail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Barang_DetailActionPerformed(evt);
            }
        });

        Btn_Barang_Tambah.setBackground(new java.awt.Color(3, 0, 169));
        Btn_Barang_Tambah.setText("Tambah");
        Btn_Barang_Tambah.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        Btn_Barang_Tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Barang_TambahActionPerformed(evt);
            }
        });

        Btn_Barang_Update.setBackground(new java.awt.Color(243, 78, 36));
        Btn_Barang_Update.setText("Update");
        Btn_Barang_Update.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        Btn_Barang_Update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Btn_Barang_UpdateMouseClicked(evt);
            }
        });
        Btn_Barang_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Barang_UpdateActionPerformed(evt);
            }
        });

        Cari_Barang_Btn.setBackground(new java.awt.Color(0, 0, 153));
        Cari_Barang_Btn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Cari_Barang_Btn.setForeground(new java.awt.Color(255, 255, 255));
        Cari_Barang_Btn.setText("Cari");
        Cari_Barang_Btn.setMaximumSize(new java.awt.Dimension(82, 21));
        Cari_Barang_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cari_Barang_BtnActionPerformed(evt);
            }
        });

        Cari_BarangDetail.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N

        javax.swing.GroupLayout pn_ctrl_DetailLayout = new javax.swing.GroupLayout(pn_ctrl_Detail);
        pn_ctrl_Detail.setLayout(pn_ctrl_DetailLayout);
        pn_ctrl_DetailLayout.setHorizontalGroup(
            pn_ctrl_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_ctrl_DetailLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(Btn_Barang_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(Btn_Barang_Tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(Btn_Barang_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Cari_BarangDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Cari_Barang_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );
        pn_ctrl_DetailLayout.setVerticalGroup(
            pn_ctrl_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_ctrl_DetailLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pn_ctrl_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_Barang_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Barang_Update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Btn_Barang_Tambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17))
            .addGroup(pn_ctrl_DetailLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pn_ctrl_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cari_Barang_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cari_BarangDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Tbl_Barang_Detail_Barang.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        Tbl_Barang_Detail_Barang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Kode Barang", "Nama Barang", "Kategori", "Satuan", "Harga Jual", "Jumlah", "Kadaluwarsa", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tbl_Barang_Detail_Barang.setRowHeight(30);
        Tbl_Detail.setViewportView(Tbl_Barang_Detail_Barang);

        Lb_Tbl_Detail.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Tbl_Detail.setText("Detail Barang");

        javax.swing.GroupLayout pn_Tbl_BarangLayout = new javax.swing.GroupLayout(pn_Tbl_Barang);
        pn_Tbl_Barang.setLayout(pn_Tbl_BarangLayout);
        pn_Tbl_BarangLayout.setHorizontalGroup(
            pn_Tbl_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Tbl_BarangLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(pn_Tbl_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Tbl_Detail)
                    .addComponent(pn_ctrl_Detail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pn_Tbl_BarangLayout.createSequentialGroup()
                        .addComponent(Lb_Barang)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(50, 50, 50))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Tbl_BarangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Lb_Tbl_Detail)
                .addGap(543, 543, 543))
        );
        pn_Tbl_BarangLayout.setVerticalGroup(
            pn_Tbl_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Tbl_BarangLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(Lb_Barang)
                .addGap(55, 55, 55)
                .addComponent(pn_ctrl_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(Lb_Tbl_Detail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Tbl_Detail, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addGap(208, 208, 208))
        );

        pn_Konten_Barang.add(pn_Tbl_Barang, "card2");

        pn_Tbl_Barang_Tambah.setBackground(new java.awt.Color(255, 255, 255));
        pn_Tbl_Barang_Tambah.setPreferredSize(new java.awt.Dimension(1150, 683));

        Lb_Barang_Tambah1.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        Lb_Barang_Tambah1.setText("Barang");

        Lb_Barang_Tambah2.setFont(new java.awt.Font("SansSerif", 1, 32)); // NOI18N
        Lb_Barang_Tambah2.setText(">");

        Lb_Barang_Tambah3.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        Lb_Barang_Tambah3.setText("Tambah");

        Tbl_Barang_Tambah.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        Tbl_Barang_Tambah.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Kode Barang", "Nama Barang", "Kategori", "Satuan", "Harga Jual", "Jumlah", "Kadaluwarsa", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tbl_Barang_Tambah.setRowHeight(30);
        Scrl_Tambah_Barang.setViewportView(Tbl_Barang_Tambah);

        pn_Tambah_Barang.setPreferredSize(new java.awt.Dimension(1150, 683));

        Field_Tambah_Kode_Barang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Field_Tambah_Kode_Barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Field_Tambah_Kode_BarangActionPerformed(evt);
            }
        });

        Lb_Tambah_Kode_Barang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Tambah_Kode_Barang.setText("Kode Barang");

        Lb_Tambah_Tanggal_Masuk.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Tambah_Tanggal_Masuk.setText("Tanggal Masuk");

        Field_Tambah_Harga_Beli.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Field_Tambah_Harga_Beli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Field_Tambah_Harga_BeliActionPerformed(evt);
            }
        });

        Lb_Tambah_Harga_Beli.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Tambah_Harga_Beli.setText("Harga Beli");

        Field_Tambah_Harga_Jual.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        Lb_Tambah_Harga_Jual.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Tambah_Harga_Jual.setText("Harga Jual");

        Lb_Tambah_Nama_Barang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Tambah_Nama_Barang.setText("Nama Barang");

        Field_Tambah_Nama_Barang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        Lb_Tambah_Kadaluwarsa.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Tambah_Kadaluwarsa.setText("Kadaluwarsa");

        Lb_Tambah_Merk.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Tambah_Merk.setText("Merk");

        Field_Tambah_Merk.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Field_Tambah_Merk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Field_Tambah_MerkActionPerformed(evt);
            }
        });

        Lb_Tambah_Jumlah.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Tambah_Jumlah.setText("Jumlah");

        Field_Tambah_Jumlah.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        Lb_Tambah_Kategori.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Tambah_Kategori.setText("Kategori");

        Field_Tambah_Kategori.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Field_Tambah_Kategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--default--" }));
        Field_Tambah_Kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Field_Tambah_KategoriActionPerformed(evt);
            }
        });

        Lb_Tambah_Satuan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Tambah_Satuan.setText("Satuan");

        Field_Tambah_Satuan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Field_Tambah_Satuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--default--" }));

        Lb_Tambah_Status.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Tambah_Status.setText("Status");

        Field_Tambah_Status.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Field_Tambah_Status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--default--" }));

        Lb_Tambah_Kode_Distributor.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Tambah_Kode_Distributor.setText("Kode Distributor");

        Field_Tambah_Kode_Distributor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Field_Tambah_Kode_Distributor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--default--" }));

        Lb_Tambah_Barcode1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Tambah_Barcode1.setText("Catatan");

        Field_Tambah_Catatan.setColumns(20);
        Field_Tambah_Catatan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Field_Tambah_Catatan.setRows(5);
        jScrollPane_Tambah_Catatan.setViewportView(Field_Tambah_Catatan);

        Btn_Tambah_Simpan.setBackground(new java.awt.Color(3, 0, 126));
        Btn_Tambah_Simpan.setText("Simpan");
        Btn_Tambah_Simpan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        Btn_Tambah_Simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Tambah_SimpanActionPerformed(evt);
            }
        });

        Btn_Tambah_Kembali.setBackground(new java.awt.Color(255, 202, 0));
        Btn_Tambah_Kembali.setForeground(new java.awt.Color(0, 0, 0));
        Btn_Tambah_Kembali.setText("Kembali");
        Btn_Tambah_Kembali.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        Btn_Tambah_Kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Tambah_KembaliActionPerformed(evt);
            }
        });

        Field_Tambah_Tanggal_Masuk.setEnabled(false);
        Field_Tambah_Tanggal_Masuk.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        Field_Tambah_Kadaluwarsa.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        Btn_Tambah_Barcode.setBackground(new java.awt.Color(51, 153, 255));
        Btn_Tambah_Barcode.setForeground(new java.awt.Color(0, 0, 0));
        Btn_Tambah_Barcode.setText("Cetak Barcode");
        Btn_Tambah_Barcode.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        Btn_Tambah_Barcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Tambah_BarcodeActionPerformed(evt);
            }
        });

        Lb_Tambah_Barcode2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Tambah_Barcode2.setText("Barcode");

        javax.swing.GroupLayout pn_Tambah_BarangLayout = new javax.swing.GroupLayout(pn_Tambah_Barang);
        pn_Tambah_Barang.setLayout(pn_Tambah_BarangLayout);
        pn_Tambah_BarangLayout.setHorizontalGroup(
            pn_Tambah_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Tambah_BarangLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(pn_Tambah_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pn_Tambah_BarangLayout.createSequentialGroup()
                        .addGroup(pn_Tambah_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Lb_Tambah_Harga_Jual, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Lb_Tambah_Kode_Barang)
                            .addComponent(Lb_Tambah_Harga_Beli)
                            .addGroup(pn_Tambah_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(Field_Tambah_Harga_Jual, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Field_Tambah_Harga_Beli, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Field_Tambah_Tanggal_Masuk, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                .addComponent(Lb_Tambah_Tanggal_Masuk, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Field_Tambah_Kode_Barang, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(82, 82, 82)
                        .addGroup(pn_Tambah_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Lb_Tambah_Nama_Barang)
                            .addComponent(Lb_Tambah_Kadaluwarsa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pn_Tambah_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Field_Tambah_Nama_Barang)
                                .addComponent(Field_Tambah_Kadaluwarsa, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                .addComponent(Lb_Tambah_Merk)
                                .addComponent(Field_Tambah_Merk)
                                .addComponent(Lb_Tambah_Jumlah)
                                .addComponent(Field_Tambah_Jumlah, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGap(103, 103, 103)
                        .addGroup(pn_Tambah_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Lb_Tambah_Kode_Distributor)
                            .addComponent(Field_Tambah_Kode_Distributor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Lb_Tambah_Kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Field_Tambah_Kategori, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Lb_Tambah_Satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Field_Tambah_Satuan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Lb_Tambah_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Field_Tambah_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(164, 164, 164)
                        .addGroup(pn_Tambah_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pn_Tambah_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(pn_Tambah_BarangLayout.createSequentialGroup()
                                    .addComponent(Lb_Tambah_Barcode1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(243, 243, 243))
                                .addComponent(jScrollPane_Tambah_Catatan)
                                .addComponent(Field_Tambah_Barcode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(Btn_Tambah_Barcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Lb_Tambah_Barcode2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pn_Tambah_BarangLayout.createSequentialGroup()
                        .addComponent(Btn_Tambah_Simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_Tambah_Kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        pn_Tambah_BarangLayout.setVerticalGroup(
            pn_Tambah_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Tambah_BarangLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(pn_Tambah_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pn_Tambah_BarangLayout.createSequentialGroup()
                        .addGroup(pn_Tambah_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Tambah_BarangLayout.createSequentialGroup()
                                .addGroup(pn_Tambah_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Lb_Tambah_Nama_Barang)
                                    .addComponent(Lb_Tambah_Kode_Barang))
                                .addGap(6, 6, 6)
                                .addComponent(Field_Tambah_Kode_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(143, 143, 143))
                            .addGroup(pn_Tambah_BarangLayout.createSequentialGroup()
                                .addGroup(pn_Tambah_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pn_Tambah_BarangLayout.createSequentialGroup()
                                        .addGroup(pn_Tambah_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(Field_Tambah_Nama_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(pn_Tambah_BarangLayout.createSequentialGroup()
                                                .addComponent(Lb_Tambah_Kode_Distributor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Field_Tambah_Kode_Distributor, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(31, 31, 31)
                                        .addGroup(pn_Tambah_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pn_Tambah_BarangLayout.createSequentialGroup()
                                                .addComponent(Lb_Tambah_Kategori)
                                                .addGap(6, 6, 6)
                                                .addComponent(Field_Tambah_Kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(pn_Tambah_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(pn_Tambah_BarangLayout.createSequentialGroup()
                                                    .addComponent(Lb_Tambah_Tanggal_Masuk)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(Field_Tambah_Tanggal_Masuk, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(pn_Tambah_BarangLayout.createSequentialGroup()
                                                    .addComponent(Lb_Tambah_Kadaluwarsa, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(5, 5, 5)
                                                    .addComponent(Field_Tambah_Kadaluwarsa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(1, 1, 1)))))
                                    .addGroup(pn_Tambah_BarangLayout.createSequentialGroup()
                                        .addComponent(Lb_Tambah_Barcode2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(Field_Tambah_Barcode, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(41, 41, 41)))
                        .addGroup(pn_Tambah_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Lb_Tambah_Harga_Beli)
                            .addComponent(Lb_Tambah_Merk, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Lb_Tambah_Satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn_Tambah_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pn_Tambah_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Field_Tambah_Merk, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Field_Tambah_Satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Field_Tambah_Harga_Beli, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(pn_Tambah_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Lb_Tambah_Harga_Jual, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Lb_Tambah_Jumlah)
                            .addComponent(Lb_Tambah_Status)))
                    .addGroup(pn_Tambah_BarangLayout.createSequentialGroup()
                        .addComponent(Lb_Tambah_Barcode1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane_Tambah_Catatan, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pn_Tambah_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Tambah_BarangLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(pn_Tambah_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Field_Tambah_Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Field_Tambah_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Field_Tambah_Harga_Jual, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pn_Tambah_BarangLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(Btn_Tambah_Barcode, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pn_Tambah_BarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Btn_Tambah_Kembali, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                            .addComponent(Btn_Tambah_Simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        Lb_Data_Barang_Tambah.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Data_Barang_Tambah.setText("Data Barang");

        javax.swing.GroupLayout pn_Tbl_Barang_TambahLayout = new javax.swing.GroupLayout(pn_Tbl_Barang_Tambah);
        pn_Tbl_Barang_Tambah.setLayout(pn_Tbl_Barang_TambahLayout);
        pn_Tbl_Barang_TambahLayout.setHorizontalGroup(
            pn_Tbl_Barang_TambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Tbl_Barang_TambahLayout.createSequentialGroup()
                .addGroup(pn_Tbl_Barang_TambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Tbl_Barang_TambahLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(pn_Tbl_Barang_TambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pn_Tambah_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, 1620, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pn_Tbl_Barang_TambahLayout.createSequentialGroup()
                                .addComponent(Lb_Barang_Tambah1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Lb_Barang_Tambah2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Lb_Barang_Tambah3))
                            .addComponent(Scrl_Tambah_Barang)))
                    .addGroup(pn_Tbl_Barang_TambahLayout.createSequentialGroup()
                        .addGap(781, 781, 781)
                        .addComponent(Lb_Data_Barang_Tambah)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pn_Tbl_Barang_TambahLayout.setVerticalGroup(
            pn_Tbl_Barang_TambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Tbl_Barang_TambahLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pn_Tbl_Barang_TambahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lb_Barang_Tambah1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lb_Barang_Tambah2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lb_Barang_Tambah3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(pn_Tambah_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Lb_Data_Barang_Tambah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Scrl_Tambah_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pn_Konten_Barang.add(pn_Tbl_Barang_Tambah, "card2");

        pn_Tbl_Barang_Detail.setBackground(new java.awt.Color(255, 255, 255));
        pn_Tbl_Barang_Detail.setPreferredSize(new java.awt.Dimension(1150, 683));

        Lb_Barang_Detail4.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        Lb_Barang_Detail4.setText("Barang");

        Lb_Barang_Detail5.setFont(new java.awt.Font("SansSerif", 1, 32)); // NOI18N
        Lb_Barang_Detail5.setText(">");

        Lb_Barang_Detail6.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        Lb_Barang_Detail6.setText("Detail");

        pn_Konten_Barang_Detail.setBackground(new java.awt.Color(243, 243, 243));

        pn_Informasi_Barang_Detail.setBackground(new java.awt.Color(255, 255, 255));
        pn_Informasi_Barang_Detail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Lb_Informasi_Nama_Barang.setFont(new java.awt.Font("SansSerif", 0, 28)); // NOI18N
        Lb_Informasi_Nama_Barang.setText("Nama Barang");
        Lb_Informasi_Nama_Barang.setIconTextGap(5);

        Lb_Informasi_Kode_Barang.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        Lb_Informasi_Kode_Barang.setText("Kode Barang");

        Lb_Informasi_Terjual.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Lb_Informasi_Terjual.setText("Terjual");

        Lb_Informasi_Stok.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Lb_Informasi_Stok.setText("Stok");

        Lb_Informasi_Kadaluwarsa.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Lb_Informasi_Kadaluwarsa.setText("Expired");

        Lb_Informasi_Terjual_Jumlah.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Lb_Informasi_Terjual_Jumlah.setForeground(new java.awt.Color(51, 153, 255));
        Lb_Informasi_Terjual_Jumlah.setText("#");

        Lb_Informasi_Stok_Jumlah.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Lb_Informasi_Stok_Jumlah.setForeground(new java.awt.Color(51, 153, 255));
        Lb_Informasi_Stok_Jumlah.setText("#");

        Lb_Informasi_exp.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Lb_Informasi_exp.setForeground(new java.awt.Color(51, 153, 255));
        Lb_Informasi_exp.setText("#");

        javax.swing.GroupLayout pn_Informasi_Barang_DetailLayout = new javax.swing.GroupLayout(pn_Informasi_Barang_Detail);
        pn_Informasi_Barang_Detail.setLayout(pn_Informasi_Barang_DetailLayout);
        pn_Informasi_Barang_DetailLayout.setHorizontalGroup(
            pn_Informasi_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Informasi_Barang_DetailLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(pn_Informasi_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Informasi_Barang_DetailLayout.createSequentialGroup()
                        .addComponent(Lb_Informasi_Terjual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(67, 67, 67))
                    .addGroup(pn_Informasi_Barang_DetailLayout.createSequentialGroup()
                        .addComponent(Lb_Informasi_Stok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(90, 90, 90))
                    .addComponent(Lb_Informasi_Kadaluwarsa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(72, 72, 72)
                .addGroup(pn_Informasi_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Lb_Informasi_Terjual_Jumlah)
                    .addComponent(Lb_Informasi_Stok_Jumlah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Lb_Informasi_exp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pn_Informasi_Barang_DetailLayout.createSequentialGroup()
                .addGroup(pn_Informasi_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Informasi_Barang_DetailLayout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(Lb_Informasi_Kode_Barang))
                    .addGroup(pn_Informasi_Barang_DetailLayout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(Lb_Informasi_Nama_Barang)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pn_Informasi_Barang_DetailLayout.setVerticalGroup(
            pn_Informasi_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Informasi_Barang_DetailLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(Lb_Informasi_Kode_Barang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Lb_Informasi_Nama_Barang)
                .addGap(63, 63, 63)
                .addGroup(pn_Informasi_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lb_Informasi_Terjual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Lb_Informasi_Terjual_Jumlah))
                .addGap(46, 46, 46)
                .addGroup(pn_Informasi_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lb_Informasi_Stok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Lb_Informasi_Stok_Jumlah))
                .addGap(44, 44, 44)
                .addGroup(pn_Informasi_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lb_Informasi_Kadaluwarsa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Lb_Informasi_exp))
                .addGap(57, 57, 57))
        );

        Btn_Barang_Detail_Update.setBackground(new java.awt.Color(243, 78, 36));
        Btn_Barang_Detail_Update.setText("UPDATE");
        Btn_Barang_Detail_Update.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Barang_Detail_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Barang_Detail_UpdateActionPerformed(evt);
            }
        });

        Lb_Informasi_Tanggal_Masuk_Detail.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Informasi_Tanggal_Masuk_Detail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Informasi_Tanggal_Masuk_Detail.setText("Tanggal Masuk :");

        FIeld_Informasi_Tanggal_Masuk_Detail.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        FIeld_Informasi_Tanggal_Masuk_Detail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FIeld_Informasi_Tanggal_Masuk_DetailActionPerformed(evt);
            }
        });

        Lb_Informasi_Kode_Distributor_Detail.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Informasi_Kode_Distributor_Detail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Informasi_Kode_Distributor_Detail.setText("Kode Distributor :");

        FIeld_Informasi_Kode_Distributor_Detail.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N

        Lb_Informasi_Brand_Merk_Detail.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Informasi_Brand_Merk_Detail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Informasi_Brand_Merk_Detail.setText("Brand/Merk :");

        FIeld_Informasi_Brand_Merk_Detail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        Lb_Informasi_Kategori_Detail.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Informasi_Kategori_Detail.setText("Kategori :");

        FIeld_Informasi_Kategori_Detail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        Lb_Informasi_Satuan_Detail.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Informasi_Satuan_Detail.setText("Satuan :");

        FIeld_Informasi_Satuan_Detail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        Lb_Informasi_Harga_Beli_Detail.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Informasi_Harga_Beli_Detail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Informasi_Harga_Beli_Detail.setText("Harga Beli :");

        FIeld_Informasi_Harga_Beli_Detail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        Lb_Informasi_Harga_Jual_Detail.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Informasi_Harga_Jual_Detail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Informasi_Harga_Jual_Detail.setText("Harga Jual :");

        FIeld_Informasi_Harga_Jual_Detail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        Lb_Informasi_Status_Detail.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Informasi_Status_Detail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Informasi_Status_Detail.setText("Status :");

        FIeld_Informasi_Status_Detail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        FIeld_Informasi_Status_Detail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FIeld_Informasi_Status_DetailActionPerformed(evt);
            }
        });

        Lb_Informasi_Catatan_Detail.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Informasi_Catatan_Detail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Informasi_Catatan_Detail.setText("Catatan :");

        Lb_Informasi_Barcode_Detail.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Informasi_Barcode_Detail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Informasi_Barcode_Detail.setText("Barcode :");

        FIeld_Informasi_Barcode_Detail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        FIeld_Informasi_Catatan_Detail.setColumns(20);
        FIeld_Informasi_Catatan_Detail.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        FIeld_Informasi_Catatan_Detail.setRows(5);
        jScrollPane1.setViewportView(FIeld_Informasi_Catatan_Detail);

        Btn_Barang_Detail_Kembali.setBackground(new java.awt.Color(255, 202, 0));
        Btn_Barang_Detail_Kembali.setForeground(new java.awt.Color(0, 0, 0));
        Btn_Barang_Detail_Kembali.setText("Kembali");
        Btn_Barang_Detail_Kembali.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Barang_Detail_Kembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Btn_Barang_Detail_KembaliMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pn_Konten_Barang_DetailLayout = new javax.swing.GroupLayout(pn_Konten_Barang_Detail);
        pn_Konten_Barang_Detail.setLayout(pn_Konten_Barang_DetailLayout);
        pn_Konten_Barang_DetailLayout.setHorizontalGroup(
            pn_Konten_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Konten_Barang_DetailLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pn_Konten_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Btn_Barang_Detail_Kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pn_Konten_Barang_DetailLayout.createSequentialGroup()
                        .addGroup(pn_Konten_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pn_Informasi_Barang_Detail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Btn_Barang_Detail_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pn_Konten_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_Konten_Barang_DetailLayout.createSequentialGroup()
                                .addGap(187, 187, 187)
                                .addComponent(Lb_Informasi_Status_Detail)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FIeld_Informasi_Status_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Konten_Barang_DetailLayout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addGroup(pn_Konten_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Konten_Barang_DetailLayout.createSequentialGroup()
                                        .addComponent(Lb_Informasi_Tanggal_Masuk_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(FIeld_Informasi_Tanggal_Masuk_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Konten_Barang_DetailLayout.createSequentialGroup()
                                        .addComponent(Lb_Informasi_Kode_Distributor_Detail)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(FIeld_Informasi_Kode_Distributor_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Konten_Barang_DetailLayout.createSequentialGroup()
                                        .addComponent(Lb_Informasi_Barcode_Detail)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(FIeld_Informasi_Barcode_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Konten_Barang_DetailLayout.createSequentialGroup()
                                        .addComponent(Lb_Informasi_Harga_Beli_Detail)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(FIeld_Informasi_Harga_Beli_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(71, 71, 71)
                        .addGroup(pn_Konten_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Lb_Informasi_Catatan_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pn_Konten_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(Lb_Informasi_Kategori_Detail)
                                .addComponent(Lb_Informasi_Brand_Merk_Detail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Lb_Informasi_Satuan_Detail)
                                .addComponent(Lb_Informasi_Harga_Jual_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn_Konten_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(FIeld_Informasi_Harga_Jual_Detail)
                            .addComponent(FIeld_Informasi_Brand_Merk_Detail)
                            .addComponent(FIeld_Informasi_Kategori_Detail)
                            .addComponent(FIeld_Informasi_Satuan_Detail))))
                .addGap(124, 124, 124))
        );
        pn_Konten_Barang_DetailLayout.setVerticalGroup(
            pn_Konten_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Konten_Barang_DetailLayout.createSequentialGroup()
                .addGroup(pn_Konten_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Konten_Barang_DetailLayout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addGroup(pn_Konten_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_Konten_Barang_DetailLayout.createSequentialGroup()
                                .addGroup(pn_Konten_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pn_Konten_Barang_DetailLayout.createSequentialGroup()
                                        .addGap(76, 76, 76)
                                        .addGroup(pn_Konten_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(Lb_Informasi_Kategori_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(FIeld_Informasi_Kategori_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(FIeld_Informasi_Kode_Distributor_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Lb_Informasi_Kode_Distributor_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(pn_Konten_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(pn_Konten_Barang_DetailLayout.createSequentialGroup()
                                                .addComponent(Lb_Informasi_Catatan_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(33, 33, 33))
                                            .addGroup(pn_Konten_Barang_DetailLayout.createSequentialGroup()
                                                .addGroup(pn_Konten_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(Lb_Informasi_Satuan_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(FIeld_Informasi_Satuan_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(40, 40, 40)
                                                .addGroup(pn_Konten_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(FIeld_Informasi_Harga_Jual_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(Lb_Informasi_Harga_Jual_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(46, 46, 46)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(pn_Konten_Barang_DetailLayout.createSequentialGroup()
                                        .addGroup(pn_Konten_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(pn_Konten_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(Lb_Informasi_Tanggal_Masuk_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(FIeld_Informasi_Tanggal_Masuk_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(pn_Konten_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(Lb_Informasi_Brand_Merk_Detail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(FIeld_Informasi_Brand_Merk_Detail, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(38, 38, 38))
                            .addGroup(pn_Konten_Barang_DetailLayout.createSequentialGroup()
                                .addGap(166, 166, 166)
                                .addGroup(pn_Konten_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Lb_Informasi_Barcode_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(FIeld_Informasi_Barcode_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addGroup(pn_Konten_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Lb_Informasi_Status_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(FIeld_Informasi_Status_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(46, 46, 46)
                                .addGroup(pn_Konten_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Lb_Informasi_Harga_Beli_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(FIeld_Informasi_Harga_Beli_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(pn_Konten_Barang_DetailLayout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(pn_Informasi_Barang_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(Btn_Barang_Detail_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(Btn_Barang_Detail_Kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(274, 274, 274))
        );

        javax.swing.GroupLayout pn_Tbl_Barang_DetailLayout = new javax.swing.GroupLayout(pn_Tbl_Barang_Detail);
        pn_Tbl_Barang_Detail.setLayout(pn_Tbl_Barang_DetailLayout);
        pn_Tbl_Barang_DetailLayout.setHorizontalGroup(
            pn_Tbl_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Tbl_Barang_DetailLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(pn_Tbl_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Tbl_Barang_DetailLayout.createSequentialGroup()
                        .addComponent(pn_Konten_Barang_Detail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(50, 50, 50))
                    .addGroup(pn_Tbl_Barang_DetailLayout.createSequentialGroup()
                        .addComponent(Lb_Barang_Detail4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Lb_Barang_Detail5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Lb_Barang_Detail6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pn_Tbl_Barang_DetailLayout.setVerticalGroup(
            pn_Tbl_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Tbl_Barang_DetailLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pn_Tbl_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Lb_Barang_Detail5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pn_Tbl_Barang_DetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Lb_Barang_Detail4)
                        .addComponent(Lb_Barang_Detail6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(65, 65, 65)
                .addComponent(pn_Konten_Barang_Detail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pn_Konten_Barang.add(pn_Tbl_Barang_Detail, "card2");

        pn_Tbl_Barang_Update.setBackground(new java.awt.Color(255, 255, 255));
        pn_Tbl_Barang_Update.setPreferredSize(new java.awt.Dimension(1150, 683));

        Lb_Barang_Update1.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        Lb_Barang_Update1.setText("Barang");

        Lb_Barang_Update2.setFont(new java.awt.Font("SansSerif", 1, 32)); // NOI18N
        Lb_Barang_Update2.setText(">");

        Lb_Barang_Update3.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        Lb_Barang_Update3.setText("Update");

        pn_Konten_Barang_Detail1.setBackground(new java.awt.Color(243, 243, 243));

        Btn_Update_Simpan.setBackground(new java.awt.Color(3, 0, 169));
        Btn_Update_Simpan.setText("Simpan");
        Btn_Update_Simpan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Update_Simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Update_SimpanActionPerformed(evt);
            }
        });

        Btn_Update_Kembali.setBackground(new java.awt.Color(3, 0, 169));
        Btn_Update_Kembali.setText("Kembali");
        Btn_Update_Kembali.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Update_Kembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Btn_Update_KembaliMouseClicked(evt);
            }
        });

        Btn_Update_Hapus.setBackground(new java.awt.Color(255, 0, 0));
        Btn_Update_Hapus.setText("Hapus");
        Btn_Update_Hapus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Update_Hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Update_HapusActionPerformed(evt);
            }
        });

        Field_Update_Kode_Barang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Field_Update_Kode_Barang.setEnabled(false);

        Lb_Update_Kode_Barang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Update_Kode_Barang.setText("Kode Barang");

        Lb_Update_Tanggal_Masuk.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Update_Tanggal_Masuk.setText("Tanggal Masuk");

        Field_Update_Harga_Beli.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        Lb_Update_Harga_Beli.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Update_Harga_Beli.setText("Harga Beli");

        Lb_Update_Harga_Jual.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Update_Harga_Jual.setText("Harga Jual");

        Lb_Update_Kadaluwarsa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Lb_Update_Kadaluwarsa.setText("Kadaluwarsa");

        Lb_Update_Jumlah.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Lb_Update_Jumlah.setText("Jumlah");

        Field_Update_Jumlah.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Field_Update_Jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Field_Update_JumlahActionPerformed(evt);
            }
        });

        Lb_Update_Kategori1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Lb_Update_Kategori1.setText("Kategori");

        Field_Update_Kategori.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Field_Update_Kategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--default--" }));
        Field_Update_Kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Field_Update_KategoriActionPerformed(evt);
            }
        });

        Lb_Update_Satuan1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Lb_Update_Satuan1.setText("Satuan");

        Field_Update_Satuan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Field_Update_Satuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--default--" }));

        Field_Update_Status.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Field_Update_Status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--default--" }));
        Field_Update_Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Field_Update_StatusActionPerformed(evt);
            }
        });

        Lb_Update_Kode_Distributor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Lb_Update_Kode_Distributor.setText("Kode Distributor");

        Field_Update_Kode_Distributor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Field_Update_Kode_Distributor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--default--" }));

        Lb_Update_Barcode.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Update_Barcode.setText("Catatan");

        Field_Update_Catatan.setColumns(20);
        Field_Update_Catatan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Field_Update_Catatan.setRows(5);
        jScrollPane_Update_Catatan.setViewportView(Field_Update_Catatan);

        Field_Update_Harga_Jual.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        Field_Update_Nama_Barang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        Lb_Update_Nama_Barang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Lb_Update_Nama_Barang.setText("Nama Barang");

        rSMaterialButtonRectangle1.setText("PILIH");
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });

        Field_Update_Kadaluwarsa.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        Field_Update_Tanggal_Masuk.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        Lb_Update_Status.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lb_Update_Status.setText("Status");

        javax.swing.GroupLayout pn_Konten_Barang_Detail1Layout = new javax.swing.GroupLayout(pn_Konten_Barang_Detail1);
        pn_Konten_Barang_Detail1.setLayout(pn_Konten_Barang_Detail1Layout);
        pn_Konten_Barang_Detail1Layout.setHorizontalGroup(
            pn_Konten_Barang_Detail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Konten_Barang_Detail1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(pn_Konten_Barang_Detail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Field_Update_Harga_Jual)
                    .addComponent(Field_Update_Harga_Beli)
                    .addComponent(Field_Update_Tanggal_Masuk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                    .addComponent(Lb_Update_Kode_Barang)
                    .addComponent(Lb_Update_Tanggal_Masuk)
                    .addComponent(Lb_Update_Harga_Jual)
                    .addComponent(Lb_Update_Harga_Beli, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Field_Update_Kode_Barang, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pn_Konten_Barang_Detail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Lb_Update_Jumlah)
                    .addGroup(pn_Konten_Barang_Detail1Layout.createSequentialGroup()
                        .addGroup(pn_Konten_Barang_Detail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Field_Update_Nama_Barang)
                            .addComponent(Field_Update_Kadaluwarsa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Field_Update_Jumlah)
                            .addGroup(pn_Konten_Barang_Detail1Layout.createSequentialGroup()
                                .addGroup(pn_Konten_Barang_Detail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Lb_Update_Nama_Barang)
                                    .addComponent(Lb_Update_Kadaluwarsa))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(54, 54, 54)))
                .addGap(70, 70, 70)
                .addGroup(pn_Konten_Barang_Detail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Konten_Barang_Detail1Layout.createSequentialGroup()
                        .addComponent(Lb_Update_Kategori1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(298, 298, 298))
                    .addGroup(pn_Konten_Barang_Detail1Layout.createSequentialGroup()
                        .addGroup(pn_Konten_Barang_Detail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Lb_Update_Kode_Distributor)
                            .addGroup(pn_Konten_Barang_Detail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(Field_Update_Kategori, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Field_Update_Kode_Distributor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Lb_Update_Satuan1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Field_Update_Satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Lb_Update_Status)
                            .addComponent(Field_Update_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(pn_Konten_Barang_Detail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pn_Konten_Barang_Detail1Layout.createSequentialGroup()
                        .addGroup(pn_Konten_Barang_Detail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Btn_Update_Simpan, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(Btn_Update_Kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addGroup(pn_Konten_Barang_Detail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_Konten_Barang_Detail1Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(Btn_Update_Hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Konten_Barang_Detail1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pn_Konten_Barang_Detail1Layout.createSequentialGroup()
                        .addComponent(Lb_Update_Barcode)
                        .addGap(236, 236, 236))
                    .addComponent(jScrollPane_Update_Catatan))
                .addGap(129, 129, 129))
        );
        pn_Konten_Barang_Detail1Layout.setVerticalGroup(
            pn_Konten_Barang_Detail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Konten_Barang_Detail1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pn_Konten_Barang_Detail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Konten_Barang_Detail1Layout.createSequentialGroup()
                        .addGroup(pn_Konten_Barang_Detail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_Konten_Barang_Detail1Layout.createSequentialGroup()
                                .addComponent(Lb_Update_Kode_Barang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Field_Update_Kode_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pn_Konten_Barang_Detail1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(pn_Konten_Barang_Detail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pn_Konten_Barang_Detail1Layout.createSequentialGroup()
                                        .addComponent(Lb_Update_Nama_Barang)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Field_Update_Nama_Barang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addComponent(Lb_Update_Kadaluwarsa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Field_Update_Kadaluwarsa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(192, 192, 192))
                                    .addGroup(pn_Konten_Barang_Detail1Layout.createSequentialGroup()
                                        .addGap(283, 283, 283)
                                        .addComponent(Lb_Update_Harga_Jual)
                                        .addGap(8, 8, 8)
                                        .addComponent(Field_Update_Harga_Jual, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pn_Konten_Barang_Detail1Layout.createSequentialGroup()
                                        .addGroup(pn_Konten_Barang_Detail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pn_Konten_Barang_Detail1Layout.createSequentialGroup()
                                                .addComponent(Lb_Update_Kode_Distributor)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Field_Update_Kode_Distributor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29)
                                                .addComponent(Lb_Update_Kategori1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Field_Update_Kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(pn_Konten_Barang_Detail1Layout.createSequentialGroup()
                                                .addComponent(Lb_Update_Barcode)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane_Update_Catatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(pn_Konten_Barang_Detail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pn_Konten_Barang_Detail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(Btn_Update_Simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(pn_Konten_Barang_Detail1Layout.createSequentialGroup()
                                                .addComponent(Lb_Update_Satuan1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Field_Update_Satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(109, 109, 109)))))
                        .addGap(21, 21, 21))
                    .addGroup(pn_Konten_Barang_Detail1Layout.createSequentialGroup()
                        .addGroup(pn_Konten_Barang_Detail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pn_Konten_Barang_Detail1Layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(Lb_Update_Tanggal_Masuk)
                                .addGap(4, 4, 4)
                                .addComponent(Field_Update_Tanggal_Masuk, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addGroup(pn_Konten_Barang_Detail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Lb_Update_Harga_Beli)
                                    .addComponent(Lb_Update_Jumlah))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pn_Konten_Barang_Detail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Field_Update_Harga_Beli, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Field_Update_Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pn_Konten_Barang_Detail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Btn_Update_Hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Btn_Update_Kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pn_Konten_Barang_Detail1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(Lb_Update_Status)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Field_Update_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTable3.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Kode Barang", "Nama Barang", "Kategori", "Satuan", "Jumlah", "Harga Jual", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setRowHeight(30);
        jScrollPane3.setViewportView(jTable3);

        Lb_Data_Barang_Update.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Data_Barang_Update.setText("Data Barang");

        Btn_Barang_Update_Cari.setBackground(new java.awt.Color(0, 0, 153));
        Btn_Barang_Update_Cari.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Barang_Update_Cari.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Barang_Update_Cari.setText("Cari");

        javax.swing.GroupLayout pn_Tbl_Barang_UpdateLayout = new javax.swing.GroupLayout(pn_Tbl_Barang_Update);
        pn_Tbl_Barang_Update.setLayout(pn_Tbl_Barang_UpdateLayout);
        pn_Tbl_Barang_UpdateLayout.setHorizontalGroup(
            pn_Tbl_Barang_UpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Tbl_Barang_UpdateLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(pn_Tbl_Barang_UpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Tbl_Barang_UpdateLayout.createSequentialGroup()
                        .addComponent(Field_Barang_Update_Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_Barang_Update_Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(317, 317, 317)
                        .addComponent(Lb_Data_Barang_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_Tbl_Barang_UpdateLayout.createSequentialGroup()
                        .addComponent(Lb_Barang_Update1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Lb_Barang_Update2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Lb_Barang_Update3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1325, 1325, 1325))
                    .addComponent(jScrollPane3)
                    .addComponent(pn_Konten_Barang_Detail1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pn_Tbl_Barang_UpdateLayout.setVerticalGroup(
            pn_Tbl_Barang_UpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Tbl_Barang_UpdateLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pn_Tbl_Barang_UpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lb_Barang_Update2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Lb_Barang_Update3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Lb_Barang_Update1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50)
                .addComponent(pn_Konten_Barang_Detail1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(pn_Tbl_Barang_UpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Field_Barang_Update_Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Barang_Update_Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lb_Data_Barang_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pn_Konten_Barang.add(pn_Tbl_Barang_Update, "card2");

        add(pn_Konten_Barang, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_Barang_DetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Barang_DetailActionPerformed
        pn_Konten_Barang.removeAll();
        pn_Konten_Barang.repaint();
        pn_Konten_Barang.revalidate();

        pn_Konten_Barang.add(pn_Tbl_Barang_Detail);
        pn_Konten_Barang.repaint();
        pn_Konten_Barang.revalidate();

        int selectedRow = Tbl_Barang_Detail_Barang.getSelectedRow();

        if (selectedRow != -1) {
            try {
                // Mendapatkan ID barang dari baris terpilih
                String idBarang = String.valueOf(Tbl_Barang_Detail_Barang.getValueAt(selectedRow, 0)); // Misalnya, indeks kolom ID adalah 0

                // Mengambil data merk berdasarkan ID barang
                ResultSet rs = db.ambilData("SELECT * FROM barang WHERE id_barang = '" + idBarang + "'");
                if (rs.next()) {
                    // Menampilkan nilai merk di JTextField "FIeld_Informasi_Brand_Merk_Detail"
                    String merk = rs.getString("merk");
                    FIeld_Informasi_Brand_Merk_Detail.setText(merk);
                    String id = rs.getString("id_barang");
                    Lb_Informasi_Kode_Barang.setText(id);
                    String stok = rs.getString("jumlah");
                    Lb_Informasi_Stok_Jumlah.setText(stok);
                    String nama = rs.getString("nama_barang");
                    Lb_Informasi_Nama_Barang.setText(nama);
                    String exp = rs.getString("expired");
                    Lb_Informasi_exp.setText(exp);
                    String ds = rs.getString("id_distributor");
                    FIeld_Informasi_Kode_Distributor_Detail.setText(ds);

                    // Mengambil jumlah barang terjual berdasarkan nama barang
                    ResultSet rsTerjual = db.ambilData("SELECT SUM(jumlah) AS totalTerjual FROM detail_transaksi WHERE nama_barang = '" + nama + "'");
                    if (rsTerjual.next()) {
                        int totalTerjual = rsTerjual.getInt("totalTerjual");
                        Lb_Informasi_Terjual_Jumlah.setText(String.valueOf(totalTerjual));
                    } else {
                        Lb_Informasi_Terjual_Jumlah.setText("0");
                    }
                } else {
                    FIeld_Informasi_Brand_Merk_Detail.setText("Merk tidak tersedia");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        //FIeld_Informasi_Kode_Barang_Detail.setText(String.valueOf(Tbl_Barang_Detail_Barang.getValueAt(selectedRow, 0)));
        //FIeld_Informasi_Nama_Barang_Detail.setText(String.valueOf(Tbl_Barang_Detail_Barang.getValueAt(selectedRow, 1)));
        FIeld_Informasi_Kategori_Detail.setText(String.valueOf(Tbl_Barang_Detail_Barang.getValueAt(selectedRow, 2)));
        //FIeld_Informasi_Jumlah_Detail.setText(String.valueOf(Tbl_Barang_Detail_Barang.getValueAt(selectedRow, 3)));
        FIeld_Informasi_Harga_Beli_Detail.setText(String.valueOf(Tbl_Barang_Detail_Barang.getValueAt(selectedRow, 4)));
        FIeld_Informasi_Harga_Jual_Detail.setText(String.valueOf(Tbl_Barang_Detail_Barang.getValueAt(selectedRow, 5)));
        FIeld_Informasi_Tanggal_Masuk_Detail.setText(String.valueOf(Tbl_Barang_Detail_Barang.getValueAt(selectedRow, 6)));
        FIeld_Informasi_Barcode_Detail.setText(String.valueOf(Tbl_Barang_Detail_Barang.getValueAt(selectedRow, 0)));
        FIeld_Informasi_Satuan_Detail.setText(String.valueOf(Tbl_Barang_Detail_Barang.getValueAt(selectedRow, 8)));
        FIeld_Informasi_Status_Detail.setText(String.valueOf(Tbl_Barang_Detail_Barang.getValueAt(selectedRow, 9)));
        FIeld_Informasi_Catatan_Detail.setText(String.valueOf(Tbl_Barang_Detail_Barang.getValueAt(selectedRow, 10)));


    }//GEN-LAST:event_Btn_Barang_DetailActionPerformed

    private void Btn_Barang_TambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Barang_TambahActionPerformed
        pn_Konten_Barang.removeAll();
        pn_Konten_Barang.repaint();
        pn_Konten_Barang.revalidate();

        pn_Konten_Barang.add(pn_Tbl_Barang_Tambah);
        pn_Konten_Barang.repaint();
        pn_Konten_Barang.revalidate();

    }//GEN-LAST:event_Btn_Barang_TambahActionPerformed

    private void Btn_Barang_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Barang_UpdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_Barang_UpdateActionPerformed

    private void Cari_Barang_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cari_Barang_BtnActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_Cari_Barang_BtnActionPerformed

    private void Btn_Barang_Detail_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Barang_Detail_UpdateActionPerformed

        pn_Konten_Barang.removeAll();
        pn_Konten_Barang.repaint();
        pn_Konten_Barang.revalidate();

        pn_Konten_Barang.add(pn_Tbl_Barang_Update);
        pn_Konten_Barang.repaint();
        pn_Konten_Barang.revalidate();

        int selectedRow = Tbl_Barang_Detail_Barang.getSelectedRow();

        Field_Update_Kode_Barang.setText(String.valueOf(Tbl_Barang_Detail_Barang.getValueAt(selectedRow, 0)));
        Field_Update_Nama_Barang.setText(String.valueOf(Tbl_Barang_Detail_Barang.getValueAt(selectedRow, 1)));
        Field_Update_Kategori.setSelectedItem(String.valueOf(Tbl_Barang_Detail_Barang.getValueAt(selectedRow, 2)));
        Field_Update_Jumlah.setText(String.valueOf(Tbl_Barang_Detail_Barang.getValueAt(selectedRow, 3)));
        Field_Update_Harga_Beli.setText(String.valueOf(Tbl_Barang_Detail_Barang.getValueAt(selectedRow, 4)));
        Field_Update_Harga_Jual.setText(String.valueOf(Tbl_Barang_Detail_Barang.getValueAt(selectedRow, 5)));
        Field_Update_Tanggal_Masuk.setDateFormatString(String.valueOf(Tbl_Barang_Detail_Barang.getValueAt(selectedRow, 6)));
        Field_Update_Kadaluwarsa.setDateFormatString(String.valueOf(Tbl_Barang_Detail_Barang.getValueAt(selectedRow, 7)));
        Field_Update_Satuan.setSelectedItem(String.valueOf(Tbl_Barang_Detail_Barang.getValueAt(selectedRow, 8)));
        Field_Update_Status.setSelectedItem(String.valueOf(Tbl_Barang_Detail_Barang.getValueAt(selectedRow, 9)));
        Field_Update_Catatan.setText(String.valueOf(Tbl_Barang_Detail_Barang.getValueAt(selectedRow, 10)));

        getData();
    }//GEN-LAST:event_Btn_Barang_Detail_UpdateActionPerformed

    private void Btn_Barang_Detail_KembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Btn_Barang_Detail_KembaliMouseClicked
        pn_Konten_Barang.removeAll();
        pn_Konten_Barang.repaint();
        pn_Konten_Barang.revalidate();

        pn_Konten_Barang.add(pn_Tbl_Barang);
        pn_Konten_Barang.repaint();
        pn_Konten_Barang.revalidate();
    }//GEN-LAST:event_Btn_Barang_Detail_KembaliMouseClicked

    private void Btn_Update_KembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Btn_Update_KembaliMouseClicked
        pn_Konten_Barang.removeAll();
        pn_Konten_Barang.repaint();
        pn_Konten_Barang.revalidate();

        pn_Konten_Barang.add(pn_Tbl_Barang);
        pn_Konten_Barang.repaint();
        pn_Konten_Barang.revalidate();
        resetFormUpdate();
    }//GEN-LAST:event_Btn_Update_KembaliMouseClicked

    private void Btn_Barang_UpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Btn_Barang_UpdateMouseClicked
        pn_Konten_Barang.removeAll();
        pn_Konten_Barang.repaint();
        pn_Konten_Barang.revalidate();

        pn_Konten_Barang.add(pn_Tbl_Barang_Update);
        pn_Konten_Barang.repaint();
        pn_Konten_Barang.revalidate();

    }//GEN-LAST:event_Btn_Barang_UpdateMouseClicked

    private void Btn_Tambah_KembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Tambah_KembaliActionPerformed
        pn_Konten_Barang.removeAll();
        pn_Konten_Barang.repaint();
        pn_Konten_Barang.revalidate();

        pn_Konten_Barang.add(pn_Tbl_Barang);
        pn_Konten_Barang.repaint();
        pn_Konten_Barang.revalidate();
        resetForm();
    }//GEN-LAST:event_Btn_Tambah_KembaliActionPerformed

    private void Field_Tambah_MerkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Field_Tambah_MerkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Field_Tambah_MerkActionPerformed

    private void Btn_Tambah_SimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Tambah_SimpanActionPerformed
        String id = Field_Tambah_Kode_Barang.getText();
        String nama = Field_Tambah_Nama_Barang.getText();
        String merk = Field_Tambah_Merk.getText();
        String jumlah = Field_Tambah_Jumlah.getText();
        String harga_beli = Field_Tambah_Harga_Beli.getText();
        String harga_jual = Field_Tambah_Harga_Jual.getText();

        String satuan = String.valueOf(Field_Tambah_Satuan.getSelectedItem());
        String status = String.valueOf(Field_Tambah_Status.getSelectedItem());
        String catatan = Field_Tambah_Catatan.getText();
        String kategori = String.valueOf(Field_Tambah_Kategori.getSelectedItem());
        String distributor = String.valueOf(Field_Tambah_Kode_Distributor.getSelectedItem());

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = date.format(Field_Tambah_Tanggal_Masuk.getDate());
        String kadaluwarsa = date.format(Field_Tambah_Kadaluwarsa.getDate());

        // Buat barcode dan dapatkan path file-nya
        String barcodeImg = Field_Tambah_Kode_Barang.getText(); // Asumsikan barcode berdasarkan ID barang
        String barcodeFilePath = generateBarcode(barcodeImg, "D:/barcodes/");

        if (barcodeFilePath != null) {
            // Sisipkan data ke dalam database termasuk path file barcode
            db.aksi("INSERT INTO barang VALUES ('" + id + "','" + nama + "','" + kategori + "','" + jumlah + "','" + harga_beli + "','" + harga_jual + "','" + tanggal + "','" + kadaluwarsa + "','" + satuan + "','" + status + "','" + catatan + "','" + barcodeFilePath + "','" + merk + "','" + distributor + "')");
            model.setRowCount(0);
            Tbl_Barang_Tambah.setModel(model);
        } else {
            // Tangani kasus kesalahan di mana pembuatan barcode gagal
            System.out.println("Gagal membuat barcode");
        }
        resetForm();
        getData();
        autoIn();
    }//GEN-LAST:event_Btn_Tambah_SimpanActionPerformed

    private void Field_Tambah_KategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Field_Tambah_KategoriActionPerformed
        Field_Tambah_Kategori.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autoIn(); // Panggil metode autoIn saat combobox dipilih
            }
        });
    }//GEN-LAST:event_Field_Tambah_KategoriActionPerformed

    private void Btn_Update_HapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Update_HapusActionPerformed
        int Row = jTable3.getSelectedRow();
        String ID = (String) jTable3.getValueAt(Row, 0);
        db.aksi("DELETE FROM barang WHERE id_barang='" + ID + "'");
        model.setRowCount(0);
        jTable3.setModel(model);
        getData();
        resetFormUpdate();
    }//GEN-LAST:event_Btn_Update_HapusActionPerformed

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
        int selectedRow = jTable3.getSelectedRow();

        Field_Update_Kode_Barang.setText(String.valueOf(jTable3.getValueAt(selectedRow, 0)));
        Field_Update_Nama_Barang.setText(String.valueOf(jTable3.getValueAt(selectedRow, 1)));
        Field_Update_Kategori.setSelectedItem(String.valueOf(jTable3.getValueAt(selectedRow, 2)));
        Field_Update_Jumlah.setText(String.valueOf(jTable3.getValueAt(selectedRow, 3)));
        Field_Update_Harga_Beli.setText(String.valueOf(jTable3.getValueAt(selectedRow, 4)));
        Field_Update_Harga_Jual.setText(String.valueOf(jTable3.getValueAt(selectedRow, 5)));
        Field_Update_Tanggal_Masuk.setDateFormatString(String.valueOf(jTable3.getValueAt(selectedRow, 6)));
        Field_Update_Kadaluwarsa.setDateFormatString(String.valueOf(jTable3.getValueAt(selectedRow, 7)));
        Field_Update_Satuan.setSelectedItem(String.valueOf(jTable3.getValueAt(selectedRow, 8)));
        Field_Update_Status.setSelectedItem(String.valueOf(jTable3.getValueAt(selectedRow, 9)));
        Field_Update_Catatan.setText(String.valueOf(jTable3.getValueAt(selectedRow, 10)));
        Field_Barang_Update_Cari.setText(null);
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void Btn_Update_SimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Update_SimpanActionPerformed

        try {
            db.aksi("UPDATE barang SET nama_barang = '" + Field_Update_Nama_Barang.getText()
                    + "',jenis = '" + Field_Update_Kategori.getSelectedItem() + "',jumlah = '" + Field_Update_Jumlah.getText()
                    + "',harga_beli = '" + Field_Update_Harga_Beli.getText() + "',harga_jual = '" + Field_Update_Harga_Jual.getText() + "',tanggal = '" + Field_Update_Tanggal_Masuk.getDateFormatString()
                    + "',expired = '" + Field_Update_Kadaluwarsa.getDateFormatString() + "',satuan = '" + Field_Update_Satuan.getSelectedItem() + "',status = '" + Field_Update_Status.getSelectedItem()
                    + "',catatan = '" + Field_Update_Catatan.getText()
                    + "',id_distributor = '" + Field_Update_Kode_Distributor.getSelectedItem()
                    + "' WHERE id_barang = '" + Field_Update_Kode_Barang.getText() + "'");
            model.setRowCount(0);
            jTable3.setModel(model);
            JOptionPane.showMessageDialog(null, "Data Berhasil Ter-Update!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Terupdate!");
        }

        getData();
        resetFormUpdate();
        tanggal();
    }//GEN-LAST:event_Btn_Update_SimpanActionPerformed

    private void FIeld_Informasi_Status_DetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FIeld_Informasi_Status_DetailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FIeld_Informasi_Status_DetailActionPerformed

    private void FIeld_Informasi_Tanggal_Masuk_DetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FIeld_Informasi_Tanggal_Masuk_DetailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FIeld_Informasi_Tanggal_Masuk_DetailActionPerformed

    private void Field_Tambah_Kode_BarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Field_Tambah_Kode_BarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Field_Tambah_Kode_BarangActionPerformed

    private void Field_Tambah_Harga_BeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Field_Tambah_Harga_BeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Field_Tambah_Harga_BeliActionPerformed

    private void Field_Update_JumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Field_Update_JumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Field_Update_JumlahActionPerformed

    private void Field_Update_KategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Field_Update_KategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Field_Update_KategoriActionPerformed

    private void Field_Update_StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Field_Update_StatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Field_Update_StatusActionPerformed

    private void Btn_Tambah_BarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Tambah_BarcodeActionPerformed
        try {
            Linear barcode = new Linear();
            barcode.setType(Linear.CODE128B);
            barcode.setData(Field_Tambah_Kode_Barang.getText());
            barcode.setI(11.0f);
            String path = Field_Tambah_Barcode.getText();
            barcode.renderBarcode("D:\\" + path + ".png");
            //int width = 290;
            //int height = 10;
            getBarcodeImage(290, 100, Field_Tambah_Barcode, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_Btn_Tambah_BarcodeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle Btn_Barang_Detail;
    private rojerusan.RSMaterialButtonRectangle Btn_Barang_Detail_Kembali;
    private rojerusan.RSMaterialButtonRectangle Btn_Barang_Detail_Update;
    private rojerusan.RSMaterialButtonRectangle Btn_Barang_Tambah;
    private rojerusan.RSMaterialButtonRectangle Btn_Barang_Update;
    private javax.swing.JButton Btn_Barang_Update_Cari;
    private rojerusan.RSMaterialButtonRectangle Btn_Tambah_Barcode;
    private rojerusan.RSMaterialButtonRectangle Btn_Tambah_Kembali;
    private rojerusan.RSMaterialButtonRectangle Btn_Tambah_Simpan;
    private rojerusan.RSMaterialButtonRectangle Btn_Update_Hapus;
    private rojerusan.RSMaterialButtonRectangle Btn_Update_Kembali;
    private rojerusan.RSMaterialButtonRectangle Btn_Update_Simpan;
    private javax.swing.JTextField Cari_BarangDetail;
    private javax.swing.JButton Cari_Barang_Btn;
    private javax.swing.JTextField FIeld_Informasi_Barcode_Detail;
    private javax.swing.JTextField FIeld_Informasi_Brand_Merk_Detail;
    private javax.swing.JTextArea FIeld_Informasi_Catatan_Detail;
    private javax.swing.JTextField FIeld_Informasi_Harga_Beli_Detail;
    private javax.swing.JTextField FIeld_Informasi_Harga_Jual_Detail;
    private javax.swing.JTextField FIeld_Informasi_Kategori_Detail;
    private javax.swing.JTextField FIeld_Informasi_Kode_Distributor_Detail;
    private javax.swing.JTextField FIeld_Informasi_Satuan_Detail;
    private javax.swing.JTextField FIeld_Informasi_Status_Detail;
    private javax.swing.JTextField FIeld_Informasi_Tanggal_Masuk_Detail;
    private javax.swing.JTextField Field_Barang_Update_Cari;
    private javax.swing.JLabel Field_Tambah_Barcode;
    private javax.swing.JTextArea Field_Tambah_Catatan;
    private javax.swing.JTextField Field_Tambah_Harga_Beli;
    private javax.swing.JTextField Field_Tambah_Harga_Jual;
    private javax.swing.JTextField Field_Tambah_Jumlah;
    private com.toedter.calendar.JDateChooser Field_Tambah_Kadaluwarsa;
    private javax.swing.JComboBox<String> Field_Tambah_Kategori;
    private javax.swing.JTextField Field_Tambah_Kode_Barang;
    private javax.swing.JComboBox<String> Field_Tambah_Kode_Distributor;
    private javax.swing.JTextField Field_Tambah_Merk;
    private javax.swing.JTextField Field_Tambah_Nama_Barang;
    private javax.swing.JComboBox<String> Field_Tambah_Satuan;
    private javax.swing.JComboBox<String> Field_Tambah_Status;
    private com.toedter.calendar.JDateChooser Field_Tambah_Tanggal_Masuk;
    private javax.swing.JTextArea Field_Update_Catatan;
    private javax.swing.JTextField Field_Update_Harga_Beli;
    private javax.swing.JTextField Field_Update_Harga_Jual;
    private javax.swing.JTextField Field_Update_Jumlah;
    private com.toedter.calendar.JDateChooser Field_Update_Kadaluwarsa;
    private javax.swing.JComboBox<String> Field_Update_Kategori;
    private javax.swing.JTextField Field_Update_Kode_Barang;
    private javax.swing.JComboBox<String> Field_Update_Kode_Distributor;
    private javax.swing.JTextField Field_Update_Nama_Barang;
    private javax.swing.JComboBox<String> Field_Update_Satuan;
    private javax.swing.JComboBox<String> Field_Update_Status;
    private com.toedter.calendar.JDateChooser Field_Update_Tanggal_Masuk;
    private javax.swing.JLabel Lb_Barang;
    private javax.swing.JLabel Lb_Barang_Detail4;
    private javax.swing.JLabel Lb_Barang_Detail5;
    private javax.swing.JLabel Lb_Barang_Detail6;
    private javax.swing.JLabel Lb_Barang_Tambah1;
    private javax.swing.JLabel Lb_Barang_Tambah2;
    private javax.swing.JLabel Lb_Barang_Tambah3;
    private javax.swing.JLabel Lb_Barang_Update1;
    private javax.swing.JLabel Lb_Barang_Update2;
    private javax.swing.JLabel Lb_Barang_Update3;
    private javax.swing.JLabel Lb_Data_Barang_Tambah;
    private javax.swing.JLabel Lb_Data_Barang_Update;
    private javax.swing.JLabel Lb_Informasi_Barcode_Detail;
    private javax.swing.JLabel Lb_Informasi_Brand_Merk_Detail;
    private javax.swing.JLabel Lb_Informasi_Catatan_Detail;
    private javax.swing.JLabel Lb_Informasi_Harga_Beli_Detail;
    private javax.swing.JLabel Lb_Informasi_Harga_Jual_Detail;
    private javax.swing.JLabel Lb_Informasi_Kadaluwarsa;
    private javax.swing.JLabel Lb_Informasi_Kategori_Detail;
    private javax.swing.JLabel Lb_Informasi_Kode_Barang;
    private javax.swing.JLabel Lb_Informasi_Kode_Distributor_Detail;
    private javax.swing.JLabel Lb_Informasi_Nama_Barang;
    private javax.swing.JLabel Lb_Informasi_Satuan_Detail;
    private javax.swing.JLabel Lb_Informasi_Status_Detail;
    private javax.swing.JLabel Lb_Informasi_Stok;
    private javax.swing.JLabel Lb_Informasi_Stok_Jumlah;
    private javax.swing.JLabel Lb_Informasi_Tanggal_Masuk_Detail;
    private javax.swing.JLabel Lb_Informasi_Terjual;
    private javax.swing.JLabel Lb_Informasi_Terjual_Jumlah;
    private javax.swing.JLabel Lb_Informasi_exp;
    private javax.swing.JLabel Lb_Tambah_Barcode1;
    private javax.swing.JLabel Lb_Tambah_Barcode2;
    private javax.swing.JLabel Lb_Tambah_Harga_Beli;
    private javax.swing.JLabel Lb_Tambah_Harga_Jual;
    private javax.swing.JLabel Lb_Tambah_Jumlah;
    private javax.swing.JLabel Lb_Tambah_Kadaluwarsa;
    private javax.swing.JLabel Lb_Tambah_Kategori;
    private javax.swing.JLabel Lb_Tambah_Kode_Barang;
    private javax.swing.JLabel Lb_Tambah_Kode_Distributor;
    private javax.swing.JLabel Lb_Tambah_Merk;
    private javax.swing.JLabel Lb_Tambah_Nama_Barang;
    private javax.swing.JLabel Lb_Tambah_Satuan;
    private javax.swing.JLabel Lb_Tambah_Status;
    private javax.swing.JLabel Lb_Tambah_Tanggal_Masuk;
    private javax.swing.JLabel Lb_Tbl_Detail;
    private javax.swing.JLabel Lb_Update_Barcode;
    private javax.swing.JLabel Lb_Update_Harga_Beli;
    private javax.swing.JLabel Lb_Update_Harga_Jual;
    private javax.swing.JLabel Lb_Update_Jumlah;
    private javax.swing.JLabel Lb_Update_Kadaluwarsa;
    private javax.swing.JLabel Lb_Update_Kategori1;
    private javax.swing.JLabel Lb_Update_Kode_Barang;
    private javax.swing.JLabel Lb_Update_Kode_Distributor;
    private javax.swing.JLabel Lb_Update_Nama_Barang;
    private javax.swing.JLabel Lb_Update_Satuan1;
    private javax.swing.JLabel Lb_Update_Status;
    private javax.swing.JLabel Lb_Update_Tanggal_Masuk;
    private javax.swing.JScrollPane Scrl_Tambah_Barang;
    private javax.swing.JTable Tbl_Barang_Detail_Barang;
    private javax.swing.JTable Tbl_Barang_Tambah;
    private javax.swing.JScrollPane Tbl_Detail;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane_Tambah_Catatan;
    private javax.swing.JScrollPane jScrollPane_Update_Catatan;
    private javax.swing.JTable jTable3;
    private javax.swing.JPanel pn_Informasi_Barang_Detail;
    private javax.swing.JPanel pn_Konten_Barang;
    private javax.swing.JPanel pn_Konten_Barang_Detail;
    private javax.swing.JPanel pn_Konten_Barang_Detail1;
    private javax.swing.JPanel pn_Tambah_Barang;
    private javax.swing.JPanel pn_Tbl_Barang;
    private javax.swing.JPanel pn_Tbl_Barang_Detail;
    private javax.swing.JPanel pn_Tbl_Barang_Tambah;
    private javax.swing.JPanel pn_Tbl_Barang_Update;
    private javax.swing.JPanel pn_ctrl_Detail;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    // End of variables declaration//GEN-END:variables

    private void resetForm() {
        Field_Tambah_Kode_Barang.setText("");
        Field_Tambah_Nama_Barang.setText("");
        Field_Tambah_Merk.setText("");
        Field_Tambah_Jumlah.setText("");
        Field_Tambah_Harga_Beli.setText("");
        Field_Tambah_Harga_Jual.setText("");
        //Field_Tambah_Tanggal_Masuk.setDateFormatString(null);
        Field_Tambah_Kadaluwarsa.setDateFormatString(null);
        Field_Tambah_Satuan.equals("");
        Field_Tambah_Status.getSelectedItem();
        Field_Tambah_Catatan.setText("");
        Field_Tambah_Kategori.getSelectedItem();
        Field_Tambah_Barcode.setText("");
    }

    private void resetFormUpdate() {
        Field_Update_Kode_Barang.setText("");
        Field_Update_Nama_Barang.setText("");
        Field_Update_Tanggal_Masuk.setDateFormatString(null);
        Field_Update_Jumlah.setText("");
        Field_Update_Harga_Beli.setText("");
        Field_Update_Harga_Jual.setText("");
        Field_Update_Kategori.setName("--default--");
        Field_Update_Kadaluwarsa.setDateFormatString(null);
        Field_Update_Satuan.setName("--default--");
        Field_Update_Status.setName("--default--");
        Field_Update_Catatan.setText("");
    }

    private void showBarcode() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
