/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Utama;

import Utama.Beranda;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author acer
 */
public class Menu_Utama extends javax.swing.JFrame {
private Transaksi transaksiPanel;
private String nama;
    int xx, xy;
    
    public Menu_Utama(String nama, String Akses, String usr) {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.nama = nama;
        
        Lb_Nama.setText(nama);
        Lb_Akses.setText(Akses);
        
        execute(Akses);
        
        
        
        UpdateDate();
        
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateDate();
            }
        });
        
        timer.start();
        
        
    }
    
    
    private void UpdateDate() {
        Calendar calendar = Calendar.getInstance();
        Date now = new Date();
        SimpleDateFormat Hari = new SimpleDateFormat("EEEE", new Locale("in", "id"));
        SimpleDateFormat tanggalwaktu = new SimpleDateFormat("dd-MM-yyyy   HH-mm-ss");
        String HariIni = Hari.format(calendar.getTime());
        String tanggal  = tanggalwaktu.format(now);
        Lb_Tanggal.setText(HariIni+", "+ tanggal);
    }
    
    
    

    Menu_Utama() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Sidebar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Menus = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Lb_Nama = new javax.swing.JLabel();
        Lb_Akses = new javax.swing.JLabel();
        Konten = new javax.swing.JPanel();
        Dasar = new javax.swing.JPanel();
        Navbar = new javax.swing.JPanel();
        Btn_Logout = new javax.swing.JButton();
        Lb_Tanggal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        Sidebar.setBackground(new java.awt.Color(46, 46, 46));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setHorizontalScrollBar(null);
        jScrollPane1.setWheelScrollingEnabled(false);

        Menus.setBackground(new java.awt.Color(46, 46, 46));
        Menus.setLayout(new javax.swing.BoxLayout(Menus, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(Menus);

        jPanel1.setBackground(new java.awt.Color(46, 46, 46));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/elkon (1) (1).png"))); // NOI18N

        Lb_Nama.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Lb_Nama.setForeground(new java.awt.Color(255, 255, 255));
        Lb_Nama.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Lb_Nama.setText("Nama");

        Lb_Akses.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        Lb_Akses.setForeground(new java.awt.Color(255, 255, 255));
        Lb_Akses.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Lb_Akses.setText("Akses");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Lb_Nama, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(Lb_Akses, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Lb_Akses)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Lb_Nama)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout SidebarLayout = new javax.swing.GroupLayout(Sidebar);
        Sidebar.setLayout(SidebarLayout);
        SidebarLayout.setHorizontalGroup(
            SidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SidebarLayout.setVerticalGroup(
            SidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SidebarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE))
        );

        getContentPane().add(Sidebar, java.awt.BorderLayout.LINE_START);

        Konten.setLayout(new java.awt.BorderLayout());

        Dasar.setBackground(new java.awt.Color(242, 243, 244));
        Dasar.setLayout(new java.awt.BorderLayout());
        Konten.add(Dasar, java.awt.BorderLayout.CENTER);

        getContentPane().add(Konten, java.awt.BorderLayout.CENTER);

        Navbar.setBackground(new java.awt.Color(3, 0, 126));

        Btn_Logout.setBackground(new java.awt.Color(3, 0, 126));
        Btn_Logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/icon_Logout.png"))); // NOI18N
        Btn_Logout.setBorder(null);
        Btn_Logout.setBorderPainted(false);
        Btn_Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_LogoutActionPerformed(evt);
            }
        });

        Lb_Tanggal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Lb_Tanggal.setForeground(new java.awt.Color(255, 255, 255));
        Lb_Tanggal.setText("Tanggal dan Waktu");

        javax.swing.GroupLayout NavbarLayout = new javax.swing.GroupLayout(Navbar);
        Navbar.setLayout(NavbarLayout);
        NavbarLayout.setHorizontalGroup(
            NavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavbarLayout.createSequentialGroup()
                .addGap(1543, 1543, 1543)
                .addComponent(Lb_Tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Btn_Logout, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
        NavbarLayout.setVerticalGroup(
            NavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NavbarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NavbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Btn_Logout, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(Lb_Tanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(Navbar, java.awt.BorderLayout.PAGE_START);

        setSize(new java.awt.Dimension(1885, 967));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
   
    
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Dasar.add(new Beranda());
        Dasar.repaint();
        Dasar.revalidate();
    }//GEN-LAST:event_formWindowOpened

    private void Btn_LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_LogoutActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Login().setVisible(true);
    }//GEN-LAST:event_Btn_LogoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu_Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_Utama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_Utama("usr","nama", "Akses").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Logout;
    private javax.swing.JPanel Dasar;
    private javax.swing.JPanel Konten;
    private javax.swing.JLabel Lb_Akses;
    private javax.swing.JLabel Lb_Nama;
    private javax.swing.JLabel Lb_Tanggal;
    private javax.swing.JPanel Menus;
    private javax.swing.JPanel Navbar;
    private javax.swing.JPanel Sidebar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

private void execute(String Akses) {
    ImageIcon iconBeranda = new ImageIcon(getClass().getResource("/Gambar/icon_Beranda.png"));
    ImageIcon iconMaster = new ImageIcon(getClass().getResource("/Gambar/icon_Master.png"));
    ImageIcon iconSubMasterBarang = new ImageIcon(getClass().getResource("/Gambar/icon_Barang.png"));
    ImageIcon iconSubMasterJenis = new ImageIcon(getClass().getResource("/Gambar/icon_Jenis.png"));
    ImageIcon iconSubMasterDistributor = new ImageIcon(getClass().getResource("/Gambar/icon_Distributor.png"));
    ImageIcon iconKasir = new ImageIcon(getClass().getResource("/Gambar/icon_Kasir.png"));
    ImageIcon iconLayanan = new ImageIcon(getClass().getResource("/Gambar/icon_Layanan.png"));
    ImageIcon iconSubLayananRetur = new ImageIcon(getClass().getResource("/Gambar/icon_Retur.png"));
    ImageIcon iconSubLayananHutang = new ImageIcon(getClass().getResource("/Gambar/icon_Hutang.png"));
    ImageIcon iconKaryawan = new ImageIcon(getClass().getResource("/Gambar/logokaryawan.png"));
    ImageIcon iconSubKaryawanUser = new ImageIcon(getClass().getResource("/Gambar/icon_User.png"));
    ImageIcon iconSubKaryawanAkses = new ImageIcon(getClass().getResource("/Gambar/icon_Akses.png"));
    ImageIcon iconLaporan = new ImageIcon(getClass().getResource("/Gambar/icon_Laporan.png"));
    ImageIcon iconSubLaporanMaster = new ImageIcon(getClass().getResource("/Gambar/icon_LaporanMaster.png"));
    ImageIcon iconSubLaporanTransaksi = new ImageIcon(getClass().getResource("/Gambar/icon_LaporanTransaksi.png"));
    
    
    
    //sub menu
    
    //Menu Sub Master
    MenuItem masBarang = new MenuItem(null, true, iconSubMasterBarang, "Barang", new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Dasar.removeAll();
            Dasar.add(new Barang());
            Dasar.repaint();
            Dasar.revalidate();
        }
    });
    
    
    MenuItem masJenis = new MenuItem(null, true, iconSubMasterJenis, "Jenis", new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Dasar.removeAll();
            Dasar.add(new Jenis());
            Dasar.repaint();
            Dasar.revalidate();
        }
    });
    MenuItem masDistributor = new MenuItem(null, true, iconSubMasterDistributor, "Distributor", new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Dasar.removeAll();
            Dasar.add(new Distributor());
            Dasar.repaint();
            Dasar.revalidate();
        }
    });
    
    
    //Menu sub Layanan
    MenuItem LayRetur = new MenuItem(null, true, iconSubLayananRetur, "Retur", new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Dasar.removeAll();
            Dasar.add(new Retur());
            Dasar.repaint();
            Dasar.revalidate();
        }
    });
    MenuItem LayHutang = new MenuItem(null, true, iconSubLayananHutang, "Hutang", new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Dasar.removeAll();
            Dasar.add(new Hutang());
            Dasar.repaint();
            Dasar.revalidate();
        }
    });
    
    
    //Menu sub Karyawan
    MenuItem KarUser = new MenuItem(null, true, iconSubKaryawanUser, "User", new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Dasar.removeAll();
            Dasar.add(new User());
            Dasar.repaint();
            Dasar.revalidate();
            
        }
    });
    MenuItem KarAkses = new MenuItem(null, true, iconSubKaryawanAkses, "Hak Akses", new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Dasar.removeAll();
            Dasar.add(new Akses());
            Dasar.repaint();
            Dasar.revalidate();
        }
    });
    
    
    //Menu sub Laporan
    MenuItem LapMaster = new MenuItem(null, true, iconSubLaporanMaster, "Master", new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Dasar.removeAll();
            Dasar.add(new Laporan_Master());
            Dasar.repaint();
            Dasar.revalidate();
        }
    });
    MenuItem LapTransaksi = new MenuItem(null, true, iconSubLaporanTransaksi, "Transaksi", new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Dasar.removeAll();
            Dasar.add(new Laporan_Transaksi());
            Dasar.repaint();
            Dasar.revalidate();
        }
    });
    
    
   
    //Menu Utama
    
    //Menu Beranda
    MenuItem menuBeranda = new MenuItem(iconBeranda, false, null, "Beranda", new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Dasar.removeAll();
            Dasar.add(new Beranda());
            Dasar.repaint();
            Dasar.revalidate();
        }
    });
    
    //Menu Master
    MenuItem menuMaster = new MenuItem(iconMaster, false, null, "Master", null, masBarang, masJenis, masDistributor);
    
    
    //Menu Kasir
    MenuItem menuKasir = new MenuItem(iconKasir, false, null, "Kasir", new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Dasar.removeAll();
            Dasar.add(new Transaksi(nama));
            Dasar.repaint();
            Dasar.revalidate();
        }
    });
    MenuItem menuLayanan = new MenuItem(iconLayanan, false, null, "Layanan", null, LayRetur, LayHutang);
    MenuItem menuKaryawan = new MenuItem(iconKaryawan, false, null, "Karyawan", null, KarUser, KarAkses);
    MenuItem menuLaporan = new MenuItem(iconLaporan, false, null, "Laporan", null, LapMaster, LapTransaksi);
    
    
    
    
    if(Akses.equals("Karyawan")){
        addMenu(menuBeranda, menuKasir,  menuLayanan,  menuLaporan);
    }else{
        addMenu(menuBeranda, menuMaster, menuKasir, menuLayanan, menuKaryawan, menuLaporan);
    }
            
    
}

    private void addMenu(MenuItem... menu) {
    for (int i = 0; i < menu.length; i++) {
        Menus.add(menu[i]);
        ArrayList<MenuItem> subMenu = menu[i].getSubMenu();
        for (MenuItem m : subMenu) {
            addMenu(m);
        }
    }
    Menus.revalidate();

    }
    


    public Transaksi getTransaksiPanel() {
        if (transaksiPanel == null) {
            transaksiPanel = new Transaksi(nama); // Inisialisasi panel jika belum ada
        }
        return transaksiPanel;
    }

}




