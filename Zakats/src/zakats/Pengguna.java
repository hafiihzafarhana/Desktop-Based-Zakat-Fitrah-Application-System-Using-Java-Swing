package zakats;

import java.awt.Desktop;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import static zakats.Mustahik.idMustahikAdmin;
import static zakats.Muzaki.IdAdminMuzaki;

public class Pengguna extends javax.swing.JFrame {
    
    int batas;
    public Pengguna() {
        batas = 2;
        initComponents();
        dataAdmin();
        showPengguna(tblPengguna,0);
        tampilPagi(tblPengguna);
     
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        SuperAdmin = new javax.swing.JLabel();
        penggunaPage = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        namaAdminIndex = new javax.swing.JTextField();
        roleAdmin = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        namePengguna = new javax.swing.JTextField();
        usernamePengguna = new javax.swing.JTextField();
        passPengguna = new javax.swing.JTextField();
        nikPengguna = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        hapusPengguna = new javax.swing.JButton();
        updatePengguna = new javax.swing.JButton();
        clsData = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPengguna = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        cariNamaPengguna = new javax.swing.JTextField();
        cariNama = new javax.swing.JButton();
        paginasiBox = new javax.swing.JComboBox<>();
        printDataExcel = new javax.swing.JButton();
        printData = new javax.swing.JButton();
        clearCari = new javax.swing.JButton();

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

        jPanel1.setBackground(new java.awt.Color(116, 202, 138));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\FARHAN\\Documents\\Tugas dan materi Kuliah UPN\\Semester 4\\PBO\\Gambar Asset\\user\\dashboard.png")); // NOI18N
        jLabel1.setText("Dashboard");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\FARHAN\\Documents\\Tugas dan materi Kuliah UPN\\Semester 4\\PBO\\Gambar Asset\\new admin\\setting-lines.png")); // NOI18N
        jLabel2.setText("Pengaturan");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\FARHAN\\Documents\\Tugas dan materi Kuliah UPN\\Semester 4\\PBO\\Gambar Asset\\new admin\\give.png")); // NOI18N
        jLabel3.setText("Mustahik");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\FARHAN\\Documents\\Tugas dan materi Kuliah UPN\\Semester 4\\PBO\\Gambar Asset\\new admin\\give-love.png")); // NOI18N
        jLabel4.setText("Muzaki");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\FARHAN\\Documents\\Tugas dan materi Kuliah UPN\\Semester 4\\PBO\\Gambar Asset\\user\\logout.png")); // NOI18N
        jLabel6.setText("Logout");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Menu");

        SuperAdmin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        SuperAdmin.setForeground(new java.awt.Color(255, 255, 255));
        SuperAdmin.setIcon(new javax.swing.ImageIcon("C:\\Users\\FARHAN\\Documents\\Tugas dan materi Kuliah UPN\\Semester 4\\PBO\\Gambar Asset\\new admin\\log-format.png")); // NOI18N
        SuperAdmin.setText("Log Admin");
        SuperAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SuperAdminMouseClicked(evt);
            }
        });

        penggunaPage.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        penggunaPage.setForeground(new java.awt.Color(255, 255, 255));
        penggunaPage.setIcon(new javax.swing.ImageIcon("C:\\Users\\FARHAN\\Documents\\Tugas dan materi Kuliah UPN\\Semester 4\\PBO\\Gambar Asset\\new admin\\user (1).png")); // NOI18N
        penggunaPage.setText("Pengguna");
        penggunaPage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                penggunaPageMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(penggunaPage, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SuperAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(SuperAdmin)
                .addGap(18, 18, 18)
                .addComponent(penggunaPage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 498, Short.MAX_VALUE)
                .addComponent(jLabel6)
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

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\FARHAN\\Documents\\Tugas dan materi Kuliah UPN\\Semester 4\\PBO\\Gambar Asset\\flaticon\\minimize.png")); // NOI18N
        jLabel5.setText("jLabel5");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(roleAdmin)
                        .addComponent(namaAdminIndex, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel14.setBackground(new java.awt.Color(116, 202, 138));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(116, 202, 138));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Atur Pengguna");

        namePengguna.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        namePengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namePenggunaActionPerformed(evt);
            }
        });

        usernamePengguna.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        usernamePengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernamePenggunaActionPerformed(evt);
            }
        });

        passPengguna.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        passPengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passPenggunaActionPerformed(evt);
            }
        });

        nikPengguna.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        nikPengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nikPenggunaActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(116, 202, 138));
        jLabel13.setText("NIK Pengguna");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(116, 202, 138));
        jLabel12.setText("Password Pengguna");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(116, 202, 138));
        jLabel11.setText("Username Pengguna");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(116, 202, 138));
        jLabel10.setText("Nama Pengguna");

        hapusPengguna.setBackground(new java.awt.Color(212, 12, 16));
        hapusPengguna.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        hapusPengguna.setForeground(new java.awt.Color(255, 255, 255));
        hapusPengguna.setText("Hapus");
        hapusPengguna.setBorderPainted(false);
        hapusPengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusPenggunaActionPerformed(evt);
            }
        });

        updatePengguna.setBackground(new java.awt.Color(255, 153, 0));
        updatePengguna.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        updatePengguna.setForeground(new java.awt.Color(255, 255, 255));
        updatePengguna.setText("Update");
        updatePengguna.setBorderPainted(false);
        updatePengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePenggunaActionPerformed(evt);
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

        tblPengguna.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Pengguna", "Nama", "Username", "Password", "NIK", "Tanggal Tambah"
            }
        ));
        tblPengguna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPenggunaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPengguna);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(116, 202, 138));
        jLabel15.setText("Cari (Nama & NIK)");

        cariNamaPengguna.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cariNamaPengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariNamaPenggunaActionPerformed(evt);
            }
        });

        cariNama.setBackground(new java.awt.Color(51, 255, 51));
        cariNama.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cariNama.setForeground(new java.awt.Color(255, 255, 255));
        cariNama.setText("Cari");
        cariNama.setBorderPainted(false);
        cariNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariNamaActionPerformed(evt);
            }
        });

        paginasiBox.setBackground(new java.awt.Color(0, 102, 204));
        paginasiBox.setForeground(new java.awt.Color(255, 255, 255));
        paginasiBox.setAutoscrolls(true);
        paginasiBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                paginasiBoxItemStateChanged(evt);
            }
        });
        paginasiBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paginasiBoxActionPerformed(evt);
            }
        });

        printDataExcel.setBackground(new java.awt.Color(51, 102, 255));
        printDataExcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        printDataExcel.setForeground(new java.awt.Color(255, 255, 255));
        printDataExcel.setText("Print Excel");
        printDataExcel.setBorderPainted(false);
        printDataExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printDataExcelActionPerformed(evt);
            }
        });

        printData.setBackground(new java.awt.Color(0, 204, 51));
        printData.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        printData.setForeground(new java.awt.Color(255, 255, 255));
        printData.setText("Print PDF");
        printData.setBorderPainted(false);
        printData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printDataActionPerformed(evt);
            }
        });

        clearCari.setBackground(new java.awt.Color(0, 102, 255));
        clearCari.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clearCari.setForeground(new java.awt.Color(255, 255, 255));
        clearCari.setText("Clear");
        clearCari.setBorderPainted(false);
        clearCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearCariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 1253, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(namePengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(usernamePengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(passPengguna))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(nikPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(paginasiBox, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(printDataExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(printData, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(hapusPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(updatePengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(clsData, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(cariNamaPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cariNama, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(clearCari, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namePengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernamePengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nikPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hapusPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatePengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clsData, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cariNamaPengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariNama, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearCari, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paginasiBox, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(printDataExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(printData, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    ResultSet Rs = null, Rs1 = null, Rs2 = null, Rs3 = null;
    Connection Con = null;
    Statement St = null, St1 = null, St2 = null, St3 = null;
    int Key;
    String sqlshow = "";
    String pagisqlshow = "";
    int xMouse,yMouse;
    
    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e + "bagian openFIle");
        }
    }

    public void exportExcel(JTable jk) {
        try {
            JFileChooser JFileChooser = new JFileChooser();
            JFileChooser.showSaveDialog(jk);
            File saveFile = JFileChooser.getSelectedFile();
            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("Pengguna");

                Row rowcol = sheet.createRow(0);
                for (int i = 0; i < jk.getColumnCount(); i++) {
                    Cell cell = rowcol.createCell(i);
                    cell.setCellValue(jk.getColumnName(i));
                }

                for (int j = 0; j < jk.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < jk.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (jk.getValueAt(j, k) != null) {
                            cell.setCellValue(jk.getValueAt(j, k).toString());
                        }
                    }
                }

                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            } else {
                JOptionPane.showMessageDialog(this, "Error Dalam Export Excell");
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException io) {
            System.out.println(io);
        }
    }
    
    private void tampilPagi(JTable jt){
        try {
            String pagisqlshow = "";
            if (cariNamaPengguna.getText().length() != 0) {
                pagisqlshow = "SELECT * FROM pengguna WHERE nama_pengguna LIKE '%" + cariNamaPengguna.getText() + "%' OR nik LIKE '%"+cariNamaPengguna.getText()+"%'";
            } else {
                pagisqlshow = "SELECT * FROM pengguna";
            }

            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
            St = Con.createStatement();
            Rs = St.executeQuery(pagisqlshow);
            Rs.last();
            int jumlah = (int) Math.ceil((Rs.getRow() + 1) / batas);
            int no = 1;
            paginasiBox.removeAllItems();
            while (no <= jumlah) {
                paginasiBox.addItem(Integer.toString(no));
                no++;
            }

        } catch (Exception e) {
        }
    }
    
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
    
    private void showPengguna(JTable jt, int posisi){
        String sqlshow = "";
        DefaultTableModel model = new DefaultTableModel(new String[]{
            "ID Pengguna", "Nama", "Username", "Password", "NIK", "TGL Tambah"
        }, 0);
        
        if (cariNamaPengguna.getText().length() != 0) {
                sqlshow = "SELECT * FROM pengguna WHERE nama_pengguna LIKE '%" + cariNamaPengguna.getText() + "%' OR nik LIKE '%"+cariNamaPengguna.getText()+"%' LIMIT "+posisi+","+batas+"";
            } else {
                sqlshow = "SELECT * FROM pengguna LIMIT "+posisi+","+batas+"";
            }
        
        
        
        try {
            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
            St = Con.createStatement();
            Rs = St.executeQuery(sqlshow);
            while (Rs.next()) {
                int id_pengguna = Rs.getInt("id_pengguna");
                String nama_pengguna = Rs.getString("nama_pengguna");
                String username_pengguna = Rs.getString("username_pengguna");
                String password_pengguna = Rs.getString("password_pengguna");
                String nik_pengguna = Rs.getString("nik");
                String tgl_tambah = Rs.getString("waktu_daftar");
                model.addRow(new Object[]{
                    id_pengguna, nama_pengguna, username_pengguna, password_pengguna, nik_pengguna, tgl_tambah
                });
            }
            jt.setModel(model);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        JFrame jfIndex = new Index();
        jfIndex.show();

        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        JFrame jfMustahik = new Mustahik();
        jfMustahik.show();

        dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        JFrame jfMuzaki = new Muzaki();
        jfMuzaki.show();

        dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void SuperAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SuperAdminMouseClicked
        // TODO add your handling code here:
        if(Integer.parseInt(roleAdmin.getText()) != 2){
            JOptionPane.showMessageDialog(this,"Kamu bukan super Admin");
        }
        else{
        JFrame JFSuperA = new SuperAdmin();
        JFSuperA.show();
        dispose();
        }
    }//GEN-LAST:event_SuperAdminMouseClicked

    private void penggunaPageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_penggunaPageMouseClicked
        // TODO add your handling code here:
        JFrame JFPengguna = new Pengguna();
        JFPengguna.show();
        dispose();
    }//GEN-LAST:event_penggunaPageMouseClicked

    private void namePenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namePenggunaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namePenggunaActionPerformed

    private void usernamePenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernamePenggunaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernamePenggunaActionPerformed

    private void passPenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passPenggunaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passPenggunaActionPerformed

    private void nikPenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nikPenggunaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nikPenggunaActionPerformed

    private void hapusPenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusPenggunaActionPerformed
        try {
            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
            PreparedStatement Pst = Con.prepareStatement("DELETE FROM pengguna WHERE id_pengguna=?");
            Pst.setInt(1, Key);
            int row = Pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Pengguna ("+ namePengguna.getText() +") Dihapus");
            Con.close();
            showPengguna(tblPengguna, 0);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }//GEN-LAST:event_hapusPenggunaActionPerformed

    private void updatePenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePenggunaActionPerformed
        // TODO add your handling code here:
        if(namePengguna.getText().isEmpty() || usernamePengguna.getText().isEmpty() || passPengguna.getText().isEmpty() || nikPengguna.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Form Harus Terisi");
        }
        else{
            try {
                Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
                PreparedStatement Pst = Con.prepareStatement("UPDATE pengguna SET nama_pengguna=?,username_pengguna=?, password_pengguna=?, nik=? WHERE id_pengguna=?");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                Pst.setInt(5, Key);
                Pst.setString(1, namePengguna.getText());
                Pst.setString(2, usernamePengguna.getText());
                Pst.setString(3, passPengguna.getText());
                Pst.setString(4, nikPengguna.getText());

                int row = Pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data Pengguna ("+ namePengguna.getText() +") Diupdate");
                Con.close();
                namePengguna.setText(null);
                usernamePengguna.setText(null);
                passPengguna.setText(null);
                nikPengguna.setText(null);
                showPengguna(tblPengguna, 0);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex);
            }
        }
    }//GEN-LAST:event_updatePenggunaActionPerformed

    private void clsDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clsDataActionPerformed
        // TODO add your handling code here:
        namePengguna.setText(null);
        usernamePengguna.setText(null);
        nikPengguna.setText(null);
        passPengguna.setText(null);
    }//GEN-LAST:event_clsDataActionPerformed

    private void tblPenggunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPenggunaMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblPengguna.getModel();
        int MyIndex = tblPengguna.getSelectedRow();
        Key = Integer.valueOf(model.getValueAt(MyIndex, 0).toString());
        namePengguna.setText(model.getValueAt(MyIndex, 1).toString());
        usernamePengguna.setText(model.getValueAt(MyIndex, 2).toString());
        passPengguna.setText(model.getValueAt(MyIndex, 3).toString());
        nikPengguna.setText(model.getValueAt(MyIndex, 4).toString());
    }//GEN-LAST:event_tblPenggunaMouseClicked

    private void cariNamaPenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariNamaPenggunaActionPerformed

    }//GEN-LAST:event_cariNamaPenggunaActionPerformed

    private void cariNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariNamaActionPerformed
        int page = Integer.parseInt(paginasiBox.getSelectedItem().toString());
        int pss = (page - 1) * batas;
        showPengguna(tblPengguna, pss);
        tampilPagi(tblPengguna);
    }//GEN-LAST:event_cariNamaActionPerformed

    private void paginasiBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_paginasiBoxItemStateChanged
        try {
            int page = Integer.parseInt(paginasiBox.getSelectedItem().toString());
            int pss = (page - 1) * batas;
            showPengguna(tblPengguna, pss);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_paginasiBoxItemStateChanged

    private void printDataExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printDataExcelActionPerformed
        exportExcel(tblPengguna);
    }//GEN-LAST:event_printDataExcelActionPerformed

    private void printDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printDataActionPerformed
        try {
            tblPengguna.print();
        } catch (PrinterException ex) {
            Logger.getLogger(Muzaki.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_printDataActionPerformed

    private void paginasiBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paginasiBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paginasiBoxActionPerformed

    private void clearCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearCariActionPerformed
        // TODO add your handling code here:
        cariNamaPengguna.setText(null);
        showPengguna(tblPengguna, 0);
        tampilPagi(tblPengguna);
    }//GEN-LAST:event_clearCariActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        JFrame jfAtur = new Atur();
        jfAtur.show();

        dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

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

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        this.setExtendedState(Pengguna.ICONIFIED);
    }//GEN-LAST:event_jLabel5MouseClicked

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
            java.util.logging.Logger.getLogger(Pengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        if(idMustahikAdmin.getText().isEmpty()){
            JFrame login = new Login();
            login.show();
        }else{
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pengguna().setVisible(true);
            }
        });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel SuperAdmin;
    private javax.swing.JButton cariNama;
    private javax.swing.JTextField cariNamaPengguna;
    private javax.swing.JButton clearCari;
    private javax.swing.JButton clsData;
    private javax.swing.JButton hapusPengguna;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField namaAdminIndex;
    private javax.swing.JTextField namePengguna;
    private javax.swing.JTextField nikPengguna;
    private javax.swing.JComboBox<String> paginasiBox;
    private javax.swing.JTextField passPengguna;
    private javax.swing.JLabel penggunaPage;
    private javax.swing.JButton printData;
    private javax.swing.JButton printDataExcel;
    private javax.swing.JTextField roleAdmin;
    private javax.swing.JTable tblPengguna;
    private javax.swing.JButton updatePengguna;
    private javax.swing.JTextField usernamePengguna;
    // End of variables declaration//GEN-END:variables
}
