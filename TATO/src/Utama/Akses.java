/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Utama;

/**
 *
 * @author acer
 */
public class Akses extends javax.swing.JPanel {
    
    /**
     * Creates new form Beranda
     */
    public Akses() {
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

        pn_Akses = new javax.swing.JPanel();
        Lb_Akses = new javax.swing.JLabel();
        pn_Konten_Akses = new javax.swing.JPanel();
        pn_Update_Akses = new javax.swing.JPanel();
        Lb_Tambah_User = new javax.swing.JLabel();
        Lb_Akses_User_ID = new javax.swing.JLabel();
        Lb_Akses_Password = new javax.swing.JLabel();
        Lb_Akses_Nama = new javax.swing.JLabel();
        Lb_Akses_Hak_Akses = new javax.swing.JLabel();
        Field_Akses_User_ID = new javax.swing.JTextField();
        Field_Akses_Password = new javax.swing.JTextField();
        Field_Tambah_Nama_User = new javax.swing.JTextField();
        Field_Akses_Hak_Akses = new javax.swing.JComboBox<>();
        Lb_Akses_Nama_Lengkap = new javax.swing.JLabel();
        Field_Akses_Nama_Lengkap = new javax.swing.JTextField();
        Lb_Akses_Jenis_Kelamin = new javax.swing.JLabel();
        Field_Data_Karyawan_Jenis_Kelamin = new javax.swing.JTextField();
        Lb_Akses_No_Telepon = new javax.swing.JLabel();
        Field_Akses_No_Telepon = new javax.swing.JTextField();
        Lb_Akses_Tanggal_Lahir = new javax.swing.JLabel();
        Field_Akses_Tanggal_Lahir = new javax.swing.JTextField();
        Lb_Akses_Status_Karyawan = new javax.swing.JLabel();
        Lb_Akses_Gaji = new javax.swing.JLabel();
        Field_Akses_Gaji = new javax.swing.JTextField();
        Lb_Akses_No_Rek = new javax.swing.JLabel();
        Field_Akses_No_Rek = new javax.swing.JTextField();
        Field_Akses_Status_Karyawan = new javax.swing.JComboBox<>();
        Field_Akses_No_Identitas = new javax.swing.JTextField();
        Lb_Akses_No_Identitas = new javax.swing.JLabel();
        Lb_Akses_Alamat = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Field_Akses_Alamat = new javax.swing.JTextArea();
        Field_Akses_Tanggal_Mulai = new javax.swing.JTextField();
        Lb_Akses_Tanggal_Mulai = new javax.swing.JLabel();
        Btn_Akses_Update = new javax.swing.JButton();
        Btn_Akses_Simpan = new javax.swing.JButton();
        Btn_Akses_Cari_User_ID = new javax.swing.JButton();
        Btn_Akses_Cari_Nama_User = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1182, 686));

        pn_Akses.setBackground(new java.awt.Color(255, 255, 255));
        pn_Akses.setPreferredSize(new java.awt.Dimension(1182, 686));

        Lb_Akses.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        Lb_Akses.setText("Akses");

        pn_Konten_Akses.setLayout(new java.awt.CardLayout());

        Lb_Tambah_User.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        Lb_Tambah_User.setText("Hak Akses");

        Lb_Akses_User_ID.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Akses_User_ID.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Akses_User_ID.setText("User ID :");

        Lb_Akses_Password.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Akses_Password.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Akses_Password.setText("Password :");

        Lb_Akses_Nama.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Akses_Nama.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Akses_Nama.setText("Nama User :");

        Lb_Akses_Hak_Akses.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Akses_Hak_Akses.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Akses_Hak_Akses.setText("Akses :");

        Field_Akses_User_ID.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        Field_Akses_Password.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        Field_Tambah_Nama_User.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        Field_Akses_Hak_Akses.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Field_Akses_Hak_Akses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Karyawan", " " }));
        Field_Akses_Hak_Akses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Field_Akses_Hak_AksesActionPerformed(evt);
            }
        });

        Lb_Akses_Nama_Lengkap.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Akses_Nama_Lengkap.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Akses_Nama_Lengkap.setText("Nama Lengkap :");

        Field_Akses_Nama_Lengkap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        Lb_Akses_Jenis_Kelamin.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Akses_Jenis_Kelamin.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Akses_Jenis_Kelamin.setText("Jenis Kelamin :");

        Field_Data_Karyawan_Jenis_Kelamin.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        Lb_Akses_No_Telepon.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Akses_No_Telepon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Akses_No_Telepon.setText("No Telepon :");

        Field_Akses_No_Telepon.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        Lb_Akses_Tanggal_Lahir.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Akses_Tanggal_Lahir.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Akses_Tanggal_Lahir.setText("Tanggal Lahir :");

        Field_Akses_Tanggal_Lahir.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        Lb_Akses_Status_Karyawan.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Akses_Status_Karyawan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Akses_Status_Karyawan.setText("Status Karyawan :");

        Lb_Akses_Gaji.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Akses_Gaji.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Akses_Gaji.setText("Gaji :");

        Field_Akses_Gaji.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        Lb_Akses_No_Rek.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Akses_No_Rek.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Akses_No_Rek.setText("No Rek :");

        Field_Akses_No_Rek.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        Field_Akses_Status_Karyawan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Field_Akses_Status_Karyawan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kontrak", "Paruh Waktu", "Penuh Waktu", " " }));

        Field_Akses_No_Identitas.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        Lb_Akses_No_Identitas.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Akses_No_Identitas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Akses_No_Identitas.setText("No Identitas :");

        Lb_Akses_Alamat.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Akses_Alamat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Akses_Alamat.setText("Alamat :");

        Field_Akses_Alamat.setColumns(20);
        Field_Akses_Alamat.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Field_Akses_Alamat.setRows(5);
        jScrollPane2.setViewportView(Field_Akses_Alamat);

        Field_Akses_Tanggal_Mulai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        Lb_Akses_Tanggal_Mulai.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Akses_Tanggal_Mulai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Lb_Akses_Tanggal_Mulai.setText("Tanggal Mulai :");

        Btn_Akses_Update.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Btn_Akses_Update.setText("Update");
        Btn_Akses_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Akses_UpdateActionPerformed(evt);
            }
        });

        Btn_Akses_Simpan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Btn_Akses_Simpan.setText("Simpan");

        Btn_Akses_Cari_User_ID.setBackground(new java.awt.Color(3, 0, 126));
        Btn_Akses_Cari_User_ID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Akses_Cari_User_ID.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Akses_Cari_User_ID.setText("Cari");
        Btn_Akses_Cari_User_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Akses_Cari_User_IDActionPerformed(evt);
            }
        });

        Btn_Akses_Cari_Nama_User.setBackground(new java.awt.Color(3, 0, 126));
        Btn_Akses_Cari_Nama_User.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Btn_Akses_Cari_Nama_User.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Akses_Cari_Nama_User.setText("Cari");

        javax.swing.GroupLayout pn_Update_AksesLayout = new javax.swing.GroupLayout(pn_Update_Akses);
        pn_Update_Akses.setLayout(pn_Update_AksesLayout);
        pn_Update_AksesLayout.setHorizontalGroup(
            pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Update_AksesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Lb_Tambah_User)
                    .addGroup(pn_Update_AksesLayout.createSequentialGroup()
                        .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_Update_AksesLayout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(Lb_Akses_User_ID)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Field_Akses_User_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pn_Update_AksesLayout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(Lb_Akses_Hak_Akses)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Field_Akses_Hak_Akses, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pn_Update_AksesLayout.createSequentialGroup()
                                .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pn_Update_AksesLayout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(pn_Update_AksesLayout.createSequentialGroup()
                                                .addComponent(Lb_Akses_Password)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Field_Akses_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(pn_Update_AksesLayout.createSequentialGroup()
                                                .addComponent(Lb_Akses_Nama)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Field_Tambah_Nama_User, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(pn_Update_AksesLayout.createSequentialGroup()
                                        .addGap(65, 65, 65)
                                        .addComponent(Btn_Akses_Simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(Btn_Akses_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Btn_Akses_Cari_User_ID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Btn_Akses_Cari_Nama_User, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Update_AksesLayout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(Lb_Akses_Alamat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_Update_AksesLayout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Lb_Akses_Nama_Lengkap)
                            .addComponent(Lb_Akses_No_Identitas)
                            .addComponent(Lb_Akses_Jenis_Kelamin)
                            .addComponent(Lb_Akses_No_Telepon)
                            .addComponent(Lb_Akses_Tanggal_Lahir))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Field_Akses_Tanggal_Lahir, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Field_Akses_No_Telepon, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Field_Data_Karyawan_Jenis_Kelamin, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Field_Akses_No_Identitas, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Field_Akses_Nama_Lengkap, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(125, 125, 125)
                        .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Lb_Akses_Tanggal_Mulai)
                            .addComponent(Lb_Akses_Gaji)
                            .addComponent(Lb_Akses_No_Rek)
                            .addComponent(Lb_Akses_Status_Karyawan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Field_Akses_Status_Karyawan, 0, 200, Short.MAX_VALUE)
                            .addComponent(Field_Akses_No_Rek)
                            .addComponent(Field_Akses_Gaji)
                            .addComponent(Field_Akses_Tanggal_Mulai))))
                .addGap(337, 337, 337))
        );
        pn_Update_AksesLayout.setVerticalGroup(
            pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Update_AksesLayout.createSequentialGroup()
                .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_Update_AksesLayout.createSequentialGroup()
                        .addGap(500, 500, 500)
                        .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Lb_Akses_Alamat)
                            .addComponent(Btn_Akses_Simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Btn_Akses_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pn_Update_AksesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Lb_Tambah_User)
                        .addGap(100, 100, 100)
                        .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_Update_AksesLayout.createSequentialGroup()
                                .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Lb_Akses_User_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Btn_Akses_Cari_User_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Field_Akses_User_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Lb_Akses_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Field_Akses_Password, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Update_AksesLayout.createSequentialGroup()
                                        .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(Field_Tambah_Nama_User, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Lb_Akses_Nama)
                                            .addComponent(Btn_Akses_Cari_Nama_User, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(67, 67, 67))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Field_Akses_Hak_Akses, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Lb_Akses_Hak_Akses))))
                            .addGroup(pn_Update_AksesLayout.createSequentialGroup()
                                .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Lb_Akses_Nama_Lengkap)
                                    .addComponent(Field_Akses_Nama_Lengkap, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Lb_Akses_Tanggal_Mulai)
                                    .addComponent(Field_Akses_Tanggal_Mulai, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pn_Update_AksesLayout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(Lb_Akses_No_Identitas)
                                            .addComponent(Field_Akses_No_Identitas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(41, 41, 41)
                                        .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(Field_Data_Karyawan_Jenis_Kelamin, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Lb_Akses_Jenis_Kelamin))
                                        .addGap(38, 38, 38)
                                        .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(Field_Akses_No_Telepon, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Lb_Akses_No_Telepon)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pn_Update_AksesLayout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(Field_Akses_Gaji, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Lb_Akses_Gaji))
                                        .addGap(33, 33, 33)
                                        .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(Field_Akses_No_Rek, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Lb_Akses_No_Rek))
                                        .addGap(33, 33, 33)
                                        .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(Field_Akses_Status_Karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Lb_Akses_Status_Karyawan))))
                                .addGap(33, 33, 33)
                                .addGroup(pn_Update_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Field_Akses_Tanggal_Lahir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Lb_Akses_Tanggal_Lahir)))))
                    .addGroup(pn_Update_AksesLayout.createSequentialGroup()
                        .addGap(500, 500, 500)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pn_Konten_Akses.add(pn_Update_Akses, "card3");

        javax.swing.GroupLayout pn_AksesLayout = new javax.swing.GroupLayout(pn_Akses);
        pn_Akses.setLayout(pn_AksesLayout);
        pn_AksesLayout.setHorizontalGroup(
            pn_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_AksesLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(pn_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Lb_Akses)
                    .addComponent(pn_Konten_Akses, javax.swing.GroupLayout.PREFERRED_SIZE, 1637, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pn_AksesLayout.setVerticalGroup(
            pn_AksesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_AksesLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(Lb_Akses)
                .addGap(71, 71, 71)
                .addComponent(pn_Konten_Akses, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pn_Akses, javax.swing.GroupLayout.DEFAULT_SIZE, 1693, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pn_Akses, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Field_Akses_Hak_AksesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Field_Akses_Hak_AksesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Field_Akses_Hak_AksesActionPerformed

    private void Btn_Akses_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Akses_UpdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_Akses_UpdateActionPerformed

    private void Btn_Akses_Cari_User_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Akses_Cari_User_IDActionPerformed
        // TODO add your handling code here:
        new Stok().setVisible(true);
        
    }//GEN-LAST:event_Btn_Akses_Cari_User_IDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Akses_Cari_Nama_User;
    private javax.swing.JButton Btn_Akses_Cari_User_ID;
    private javax.swing.JButton Btn_Akses_Simpan;
    private javax.swing.JButton Btn_Akses_Update;
    private javax.swing.JTextArea Field_Akses_Alamat;
    private javax.swing.JTextField Field_Akses_Gaji;
    private javax.swing.JComboBox<String> Field_Akses_Hak_Akses;
    private javax.swing.JTextField Field_Akses_Nama_Lengkap;
    private javax.swing.JTextField Field_Akses_No_Identitas;
    private javax.swing.JTextField Field_Akses_No_Rek;
    private javax.swing.JTextField Field_Akses_No_Telepon;
    private javax.swing.JTextField Field_Akses_Password;
    private javax.swing.JComboBox<String> Field_Akses_Status_Karyawan;
    private javax.swing.JTextField Field_Akses_Tanggal_Lahir;
    private javax.swing.JTextField Field_Akses_Tanggal_Mulai;
    private javax.swing.JTextField Field_Akses_User_ID;
    private javax.swing.JTextField Field_Data_Karyawan_Jenis_Kelamin;
    private javax.swing.JTextField Field_Tambah_Nama_User;
    private javax.swing.JLabel Lb_Akses;
    private javax.swing.JLabel Lb_Akses_Alamat;
    private javax.swing.JLabel Lb_Akses_Gaji;
    private javax.swing.JLabel Lb_Akses_Hak_Akses;
    private javax.swing.JLabel Lb_Akses_Jenis_Kelamin;
    private javax.swing.JLabel Lb_Akses_Nama;
    private javax.swing.JLabel Lb_Akses_Nama_Lengkap;
    private javax.swing.JLabel Lb_Akses_No_Identitas;
    private javax.swing.JLabel Lb_Akses_No_Rek;
    private javax.swing.JLabel Lb_Akses_No_Telepon;
    private javax.swing.JLabel Lb_Akses_Password;
    private javax.swing.JLabel Lb_Akses_Status_Karyawan;
    private javax.swing.JLabel Lb_Akses_Tanggal_Lahir;
    private javax.swing.JLabel Lb_Akses_Tanggal_Mulai;
    private javax.swing.JLabel Lb_Akses_User_ID;
    private javax.swing.JLabel Lb_Tambah_User;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pn_Akses;
    private javax.swing.JPanel pn_Konten_Akses;
    private javax.swing.JPanel pn_Update_Akses;
    // End of variables declaration//GEN-END:variables
}
