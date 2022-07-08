package zakats;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import static zakats.Muzaki.IdAdminMuzaki;
import static zakats.Muzaki.NamaAdmin;

public class AturAdmin extends javax.swing.JFrame {

    /** Creates new form AturAdmin */
    public AturAdmin() {
        initComponents();
        showAdmin();
        dataAdmin();
    }

    ResultSet Rs = null, Rs1 = null, Rs2 = null, Rs3 = null;
    Connection Con = null;
    Statement St = null, St1 = null, St2 = null, St3 = null;
    int xMouse,yMouse;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        logout2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        namaAdminIndex = new javax.swing.JTextField();
        roleAdmin = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        nameAdmin = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        usernameAdmin = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        passAdmin = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        roleAdmins = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        tambahAdmin = new javax.swing.JButton();
        hapusAdmin = new javax.swing.JButton();
        updateAdmin = new javax.swing.JButton();
        clsData = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        cariNamaAdmin = new javax.swing.JTextField();
        btnCariAdmin = new javax.swing.JButton();
        clsFilterAdmin = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAdmin = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(116, 202, 138));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\FARHAN\\Documents\\Tugas dan materi Kuliah UPN\\Semester 4\\PBO\\Gambar Asset\\new admin\\cctv-camera.png")); // NOI18N
        jLabel5.setText("Aktivitas");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\FARHAN\\Documents\\Tugas dan materi Kuliah UPN\\Semester 4\\PBO\\Gambar Asset\\new admin\\add-user.png")); // NOI18N
        jLabel6.setText("Admin");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        logout2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        logout2.setForeground(new java.awt.Color(255, 255, 255));
        logout2.setIcon(new javax.swing.ImageIcon("C:\\Users\\FARHAN\\Documents\\Tugas dan materi Kuliah UPN\\Semester 4\\PBO\\Gambar Asset\\user\\logout.png")); // NOI18N
        logout2.setText("Logout");
        logout2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logout2MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Menu");

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\Users\\FARHAN\\Documents\\Tugas dan materi Kuliah UPN\\Semester 4\\PBO\\Gambar Asset\\new admin\\return.png")); // NOI18N
        jLabel7.setText("Kembali");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(logout2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(41, 41, 41)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logout2)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        namaAdminIndex.setEditable(false);
        namaAdminIndex.setBackground(new java.awt.Color(255, 255, 255));
        namaAdminIndex.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        namaAdminIndex.setForeground(new java.awt.Color(116, 202, 138));
        namaAdminIndex.setBorder(null);

        roleAdmin.setEditable(false);
        roleAdmin.setBackground(new java.awt.Color(255, 255, 255));
        roleAdmin.setForeground(new java.awt.Color(255, 255, 255));
        roleAdmin.setBorder(null);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\FARHAN\\Documents\\Tugas dan materi Kuliah UPN\\Semester 4\\PBO\\Gambar Asset\\flaticon\\minimize.png")); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(namaAdminIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(roleAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 550, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roleAdmin)
                    .addComponent(namaAdminIndex, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(116, 202, 138));
        jLabel10.setText("Nama Admin");

        nameAdmin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        nameAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameAdminActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(116, 202, 138));
        jLabel11.setText("Username");

        usernameAdmin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        usernameAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameAdminActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(116, 202, 138));
        jLabel12.setText("Password");

        passAdmin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        passAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passAdminActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(116, 202, 138));
        jLabel13.setText("Role");

        roleAdmins.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        roleAdmins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roleAdminsActionPerformed(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(116, 202, 138));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(116, 202, 138));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Atur Admin");

        tambahAdmin.setBackground(new java.awt.Color(116, 202, 138));
        tambahAdmin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tambahAdmin.setForeground(new java.awt.Color(255, 255, 255));
        tambahAdmin.setText("Tambah");
        tambahAdmin.setBorderPainted(false);
        tambahAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahAdminActionPerformed(evt);
            }
        });

        hapusAdmin.setBackground(new java.awt.Color(212, 12, 16));
        hapusAdmin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        hapusAdmin.setForeground(new java.awt.Color(255, 255, 255));
        hapusAdmin.setText("Hapus");
        hapusAdmin.setBorderPainted(false);
        hapusAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusAdminActionPerformed(evt);
            }
        });

        updateAdmin.setBackground(new java.awt.Color(255, 153, 0));
        updateAdmin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        updateAdmin.setForeground(new java.awt.Color(255, 255, 255));
        updateAdmin.setText("Update");
        updateAdmin.setBorderPainted(false);
        updateAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateAdminActionPerformed(evt);
            }
        });

        clsData.setBackground(new java.awt.Color(102, 255, 153));
        clsData.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clsData.setForeground(new java.awt.Color(255, 255, 255));
        clsData.setText("Clear");
        clsData.setBorderPainted(false);
        clsData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clsDataActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(116, 202, 138));
        jLabel15.setText("Cari (Nama)");

        cariNamaAdmin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cariNamaAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariNamaAdminActionPerformed(evt);
            }
        });

        btnCariAdmin.setBackground(new java.awt.Color(51, 255, 51));
        btnCariAdmin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCariAdmin.setForeground(new java.awt.Color(255, 255, 255));
        btnCariAdmin.setText("Cari Nama");
        btnCariAdmin.setBorderPainted(false);
        btnCariAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariAdminActionPerformed(evt);
            }
        });

        clsFilterAdmin.setBackground(new java.awt.Color(0, 102, 255));
        clsFilterAdmin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clsFilterAdmin.setForeground(new java.awt.Color(255, 255, 255));
        clsFilterAdmin.setText("Clear");
        clsFilterAdmin.setBorderPainted(false);
        clsFilterAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clsFilterAdminActionPerformed(evt);
            }
        });

        tblAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAdminMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblAdmin);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(cariNamaAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCariAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clsFilterAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel15))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(tambahAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hapusAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updateAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clsData, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(nameAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(usernameAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(passAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(roleAdmins, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(67, 67, 67))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 1231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roleAdmins, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tambahAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hapusAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clsData, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cariNamaAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clsFilterAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 278, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1246, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    int PrNum;
    
    private  void dataAdmin(){
        try {
            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
            St = Con.createStatement();
            Rs = St.executeQuery("SELECT * FROM admin WHERE id_admin = '"+IdAdminMuzaki.getText()+"'");
            if(Rs.next()){
                namaAdminIndex.setText("Nama Admin : "+Rs.getString(2));
                roleAdmin.setText(Rs.getString(5));
            }
            else{
                JOptionPane.showMessageDialog(this, "Kamu belum login");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    
    private void showAdmin(){
        try {
             Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
             St = Con.createStatement();
             Rs = St.executeQuery("SELECT * FROM admin");
             tblAdmin.setModel(DbUtils.resultSetToTableModel(Rs));
        } catch (Exception e) {
        }
    }
    
    private  void cariData(){
        try {
            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
            St = Con.createStatement();
            Rs = St.executeQuery("SELECT * FROM admin WHERE nama_admin LIKE '%"+cariNamaAdmin.getText()+"%'");
            tblAdmin.setModel(DbUtils.resultSetToTableModel(Rs));
            cariNamaAdmin.setText(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    
    private void CountProd(){
        try {
            St1 = Con.createStatement();
            Rs1 = St1.executeQuery("SELECT MAX(id) FROM admin");
            Rs1.next();
            PrNum = Rs1.getInt(1)+1;
           
        } catch (Exception e) {
        }
    }
    
    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        JFrame jfLog = new SuperAdmin();
        jfLog.show();
        dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseClicked

    private void logout2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logout2MouseClicked
        // TODO add your handling code here:
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logout2MouseClicked

    private void nameAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameAdminActionPerformed

    private void usernameAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameAdminActionPerformed

    private void passAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passAdminActionPerformed

    private void roleAdminsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roleAdminsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roleAdminsActionPerformed

    private void tambahAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahAdminActionPerformed
        // TODO add your handling code here:
        if(nameAdmin.getText().isEmpty() || usernameAdmin.getText().isEmpty() || passAdmin.getText().isEmpty() || roleAdmins.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Form Harus Terisi");
        }
        else{
            //            JOptionPane.showMessageDialog(this, jComboBox1.getSelectedIndex());
            try {
                CountProd();
                Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
                PreparedStatement Pst = Con.prepareStatement("insert into admin values(?,?,?,?,?)");
 
                Pst.setInt(1, PrNum);
                Pst.setString(2, nameAdmin.getText());
                Pst.setString(3, usernameAdmin.getText());
                Pst.setString(4, passAdmin.getText());
                Pst.setString(5, roleAdmins.getText());
                int row = Pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data Admin ("+ nameAdmin.getText() +") Ditambahkan");
                Con.close();
                nameAdmin.setText(null);
                usernameAdmin.setText(null);
                passAdmin.setText(null);
                roleAdmins.setText(null);
                showAdmin();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex);
            }
        }
    }//GEN-LAST:event_tambahAdminActionPerformed

    private void hapusAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusAdminActionPerformed
        // TODO add your handling code here:
        //            JOptionPane.showMessageDialog(this, jComboBox1.getSelectedIndex());
        try {
            CountProd();
            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
            PreparedStatement Pst = Con.prepareStatement("DELETE FROM admin WHERE id_admin=?");
            Pst.setInt(1, Key);
            int row = Pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Admin ("+ nameAdmin.getText() +") Dihapus");
            Con.close();
            nameAdmin.setText(null);
            usernameAdmin.setText(null);
            passAdmin.setText(null);
            roleAdmins.setText(null);
            showAdmin();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }//GEN-LAST:event_hapusAdminActionPerformed

    private void updateAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateAdminActionPerformed
        // TODO add your handling code here:
        if(nameAdmin.getText().isEmpty() || usernameAdmin.getText().isEmpty() || passAdmin.getText().isEmpty() || roleAdmins.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Form Harus Terisi");
        }
        else{
            //            JOptionPane.showMessageDialog(this, jComboBox1.getSelectedIndex());
            try {
                CountProd();
                Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
                PreparedStatement Pst = Con.prepareStatement("UPDATE admin SET nama_admin=?,username=?, password=?, role=? WHERE id_admin=?");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                Pst.setInt(5, Key);
                Pst.setString(1, nameAdmin.getText());
                Pst.setString(2, usernameAdmin.getText());
                Pst.setString(3, passAdmin.getText());
                Pst.setString(4, roleAdmins.getText());
                
                int row = Pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data Admin ("+ nameAdmin.getText() +") Diupdate");
                Con.close();
                nameAdmin.setText(null);
                usernameAdmin.setText(null);
                passAdmin.setText(null);
                roleAdmins.setText(null);
                showAdmin();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex);
            }
        }
    }//GEN-LAST:event_updateAdminActionPerformed

    private void clsDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clsDataActionPerformed
        // TODO add your handling code here:
        nameAdmin.setText(null);
        usernameAdmin.setText(null);
        passAdmin.setText(null);
        roleAdmins.setText(null);
    }//GEN-LAST:event_clsDataActionPerformed

    private void cariNamaAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariNamaAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariNamaAdminActionPerformed

    private void btnCariAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariAdminActionPerformed
        // TODO add your handling code here:
        cariData();
    }//GEN-LAST:event_btnCariAdminActionPerformed

    private void clsFilterAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clsFilterAdminActionPerformed
        // TODO add your handling code here:
        showAdmin();
    }//GEN-LAST:event_clsFilterAdminActionPerformed

    int Key = 0;
    private void tblAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAdminMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblAdmin.getModel();
        int MyIndex = tblAdmin.getSelectedRow();
        Key = Integer.valueOf(model.getValueAt(MyIndex, 0).toString());
        nameAdmin.setText(model.getValueAt(MyIndex, 1).toString());
        usernameAdmin.setText(model.getValueAt(MyIndex, 2).toString());
        passAdmin.setText(model.getValueAt(MyIndex, 3).toString());
        roleAdmins.setText(model.getValueAt(MyIndex, 4).toString());
    }//GEN-LAST:event_tblAdminMouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        JFrame jfIndex = new Index();
        jfIndex.show();
        dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
        int x= evt.getXOnScreen();
        int y=evt.getYOnScreen();
        setLocation(x-xMouse,y-yMouse);
    }//GEN-LAST:event_formMouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        xMouse=evt.getX();
        yMouse=evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        this.setExtendedState(AturAdmin.ICONIFIED);
    }//GEN-LAST:event_jLabel1MouseClicked

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
            java.util.logging.Logger.getLogger(AturAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AturAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AturAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AturAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        if(Muzaki.IdAdminMuzaki.getText().isEmpty()){
            JFrame login = new Login();
            login.show();
        }
        else{
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AturAdmin().setVisible(true);
            }
        });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCariAdmin;
    private javax.swing.JTextField cariNamaAdmin;
    private javax.swing.JButton clsData;
    private javax.swing.JButton clsFilterAdmin;
    private javax.swing.JButton hapusAdmin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel logout2;
    private javax.swing.JTextField namaAdminIndex;
    private javax.swing.JTextField nameAdmin;
    private javax.swing.JTextField passAdmin;
    private javax.swing.JTextField roleAdmin;
    private javax.swing.JTextField roleAdmins;
    private javax.swing.JButton tambahAdmin;
    private javax.swing.JTable tblAdmin;
    private javax.swing.JButton updateAdmin;
    private javax.swing.JTextField usernameAdmin;
    // End of variables declaration//GEN-END:variables

}
