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
import static zakats.Muzaki.IdAdminMuzaki;
import static zakats.Muzaki.NamaAdmin;

public class Mustahik extends javax.swing.JFrame {

    int batas;

    public Mustahik() {
        batas = 2;
        initComponents();
        showProducts(tblMustahik, 0);
        tampilPagi(tblMustahik);
        dataAdmin();
    }

    ResultSet Rs = null, Rs1 = null, Rs2 = null, Rs3 = null;
    Connection Con = null, Con1 = null;
    Statement St = null, St1 = null, St2 = null, St3 = null;
    int xMouse,yMouse;

    @SuppressWarnings("unchecked")

    int PrNum;
    String sqlshow = "";
    String pagisqlshow = "";
//    String role;

    private void clear() {
        namaPerwakilan.setText(null);
        alamat.setText(null);
        
    }

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
                Sheet sheet = wb.createSheet("Mustahik");

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

    private void dataAdmin() {
        try {
            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
            St = Con.createStatement();
            Rs = St.executeQuery("SELECT * FROM admin WHERE id_admin = '" + idMustahikAdmin.getText() + "'");
            if (Rs.next()) {
                namaAdminMustahik.setText("Nama Admin : " + Rs.getString(2));
                roleAdmin.setText(Rs.getString(5));
            } else {
                JOptionPane.showMessageDialog(this, "Kamu belum login");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void CountProd() {
        try {
            St1 = Con.createStatement();
            Rs1 = St1.executeQuery("SELECT MAX(id) FROM mustahik");
            Rs1.next();
            PrNum = Rs1.getInt(1) + 1;
        } catch (Exception e) {
        }
    }

    int PrNumLogMuzaki;

    private void dataLogAdminMustahik(String info, String id_admin, String waktu, int id_muzaki) {
        try {
            St3 = Con.createStatement();
            Rs3 = St3.executeQuery("SELECT MAX(id_log_mustahik) FROM log_mustahik");
            Rs3.next();
            PrNumLogMuzaki = Rs3.getInt(1) + 1;

            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");

            PreparedStatement PstLogMuzaki = Con.prepareStatement("insert into log_mustahik values(?,?,?,?,?)");
            PstLogMuzaki.setInt(1, PrNumLogMuzaki);
            PstLogMuzaki.setString(2, id_admin);
            PstLogMuzaki.setInt(3, id_muzaki);
            PstLogMuzaki.setString(4, info);
            PstLogMuzaki.setString(5, waktu);
            int row = PstLogMuzaki.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void tampilPagi(JTable jt) {
        try {
            if (namaMustahik.getText().length() != 0) {
                pagisqlshow = "SELECT * FROM mustahik WHERE nama_mustahik LIKE '%" + namaMustahik.getText() + "%'";
            } else if (tanggalan.getDate() != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(tanggalan.getCalendar().getTimeInMillis());
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(Calendar.MONTH) + 1;
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);
                String yesZeroMoon = (mMonth < 10) ? "0" : "";
                String yesZeroDay = (mDay < 10) ? "0" : "";

                String result = mYear + "/" + yesZeroMoon + mMonth + "/" + yesZeroDay + mDay;

                pagisqlshow = "SELECT * FROM mustahik WHERE tgl_tambah LIKE '%" + result + "%'";
            } else if (comboStatus.getSelectedIndex() != 0 || comboStatus.getSelectedItem() != "Pilih") {
                pagisqlshow = "SELECT * FROM mustahik WHERE status = '" + comboStatus.getSelectedItem().toString() + "'";
            } else {
                pagisqlshow = "SELECT * FROM mustahik";
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

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MuzakiList = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        SuperAdmin = new javax.swing.JLabel();
        penggunaPage = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        namaPerwakilan = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        alamat = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMustahik = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        comboStatus = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        namaMustahik = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cariNamaBut = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        tanggalan = new com.toedter.calendar.JDateChooser();
        btnTanggal = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        namaAdminMustahik = new javax.swing.JTextField();
        roleAdmin = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        paginasiBox = new javax.swing.JComboBox<>();
        printDataExcel = new javax.swing.JButton();
        printData1 = new javax.swing.JButton();

        jLabel5.setBackground(new java.awt.Color(116, 202, 138));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(116, 202, 138));
        jLabel5.setText("Muzaki");

        jTextField1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(116, 202, 138));
        jLabel8.setText("Nama Perwakilan");

        MuzakiList.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        MuzakiList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Nama", "Jiwa", "Beras (kg)", "Uang (Rp)", "Shodaqoh", "RT"
            }
        ));
        MuzakiList.setShowVerticalLines(false);
        jScrollPane1.setViewportView(MuzakiList);

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

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\FARHAN\\Documents\\Tugas dan materi Kuliah UPN\\Semester 4\\PBO\\Gambar Asset\\new admin\\give-love.png")); // NOI18N
        jLabel4.setText("Muzaki");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Menu");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\FARHAN\\Documents\\Tugas dan materi Kuliah UPN\\Semester 4\\PBO\\Gambar Asset\\user\\logout.png")); // NOI18N
        jLabel6.setText("Logout");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(penggunaPage, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(SuperAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addGap(36, 36, 36))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(SuperAdmin)
                .addGap(18, 18, 18)
                .addComponent(penggunaPage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        jLabel9.setBackground(new java.awt.Color(116, 202, 138));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(116, 202, 138));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Mustahik");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(116, 202, 138));
        jLabel10.setText("Nama Perwakilan");

        namaPerwakilan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        namaPerwakilan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaPerwakilanActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(116, 202, 138));
        jLabel11.setText("Alamat");

        alamat.setColumns(20);
        alamat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        alamat.setRows(5);
        jScrollPane2.setViewportView(alamat);

        jButton3.setBackground(new java.awt.Color(116, 202, 138));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Tambah");
        jButton3.setBorderPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(212, 12, 16));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Hapus");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 153, 0));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Update");
        jButton5.setBorderPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        tblMustahik.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblMustahik.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMustahikMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblMustahik);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(116, 202, 138));
        jLabel12.setText("Status");

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tidak Menerima", "Menerima" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(116, 202, 138));
        jLabel13.setText("Filter (Status)");

        comboStatus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        comboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Tidak Menerima", "Menerima" }));
        comboStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboStatusItemStateChanged(evt);
            }
        });
        comboStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboStatusActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 102, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Clear");
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        namaMustahik.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        namaMustahik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaMustahikActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(116, 202, 138));
        jLabel14.setText("Cari (Nama)");

        cariNamaBut.setBackground(new java.awt.Color(51, 255, 51));
        cariNamaBut.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cariNamaBut.setForeground(new java.awt.Color(255, 255, 255));
        cariNamaBut.setText("Cari Nama");
        cariNamaBut.setBorderPainted(false);
        cariNamaBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariNamaButActionPerformed(evt);
            }
        });

        clear.setBackground(new java.awt.Color(102, 255, 153));
        clear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clear.setForeground(new java.awt.Color(255, 255, 255));
        clear.setText("Clear");
        clear.setBorderPainted(false);
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(116, 202, 138));
        jLabel16.setText("Cari (Tanggal)");

        tanggalan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btnTanggal.setBackground(new java.awt.Color(51, 153, 255));
        btnTanggal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTanggal.setForeground(new java.awt.Color(255, 255, 255));
        btnTanggal.setText("Cari Tanggal");
        btnTanggal.setBorderPainted(false);
        btnTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTanggalActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        namaAdminMustahik.setEditable(false);
        namaAdminMustahik.setBackground(new java.awt.Color(255, 255, 255));
        namaAdminMustahik.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        namaAdminMustahik.setForeground(new java.awt.Color(116, 202, 138));
        namaAdminMustahik.setBorder(null);

        roleAdmin.setEditable(false);
        roleAdmin.setBackground(new java.awt.Color(255, 255, 255));
        roleAdmin.setForeground(new java.awt.Color(255, 255, 255));
        roleAdmin.setBorder(null);

        idMustahikAdmin.setEditable(false);
        idMustahikAdmin.setBackground(new java.awt.Color(255, 255, 255));
        idMustahikAdmin.setForeground(new java.awt.Color(255, 255, 255));
        idMustahikAdmin.setBorder(null);

        jLabel15.setIcon(new javax.swing.ImageIcon("C:\\Users\\FARHAN\\Documents\\Tugas dan materi Kuliah UPN\\Semester 4\\PBO\\Gambar Asset\\flaticon\\minimize.png")); // NOI18N
        jLabel15.setText("jLabel15");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(namaAdminMustahik, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roleAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(idMustahikAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(namaAdminMustahik, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(roleAdmin)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(idMustahikAdmin)
                        .addComponent(jLabel15)))
                .addContainerGap())
        );

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

        printData1.setBackground(new java.awt.Color(0, 204, 51));
        printData1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        printData1.setForeground(new java.awt.Color(255, 255, 255));
        printData1.setText("Print PDF");
        printData1.setBorderPainted(false);
        printData1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printData1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(namaPerwakilan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(paginasiBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(printDataExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(printData1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel14)
                            .addComponent(jLabel16)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tanggalan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(namaMustahik, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cariNamaBut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(608, 608, 608))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namaPerwakilan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69)
                .addComponent(jLabel13)
                .addGap(3, 3, 3)
                .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namaMustahik, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariNamaBut, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tanggalan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paginasiBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(printDataExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(printData1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
   private void showProducts(JTable jt, int posisi) {
        DefaultTableModel model = new DefaultTableModel(new String[]{
            "ID Mustahik", "Nama Mustahik", "Status", "Alamat", "Tanggal Tambah", "Tanggal Update"
        }, 0);
//        jComboBox2
        if (namaMustahik.getText().length() != 0) {
            sqlshow = "SELECT * FROM mustahik WHERE nama_mustahik LIKE '%" + namaMustahik.getText() + "%' LIMIT " + posisi + "," + batas;
        } else if (tanggalan.getDate() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(tanggalan.getCalendar().getTimeInMillis());
            int mYear = calendar.get(Calendar.YEAR);
            int mMonth = calendar.get(Calendar.MONTH) + 1;
            int mDay = calendar.get(Calendar.DAY_OF_MONTH);
            String yesZeroMoon = (mMonth < 10) ? "0" : "";
            String yesZeroDay = (mDay < 10) ? "0" : "";

            String result = mYear + "/" + yesZeroMoon + mMonth + "/" + yesZeroDay + mDay;
            sqlshow = "SELECT * FROM mustahik WHERE tgl_tambah LIKE '%" + result + "%' LIMIT " + posisi + "," + batas;

        } else if (comboStatus.getSelectedIndex() != 0) {
            sqlshow = "SELECT * FROM mustahik WHERE status = '" + comboStatus.getSelectedItem().toString() + "' LIMIT " + posisi + "," + batas;
        } else {
            sqlshow = "SELECT * FROM mustahik LIMIT " + posisi + "," + batas;
        }

        try {
            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
            St = Con.createStatement();
            Rs = St.executeQuery(sqlshow);
            while (Rs.next()) {
                int id_mustahik = Rs.getInt("id");
                String nama_mustahik = Rs.getString("nama_mustahik");
                String status = Rs.getString("status");
                String alamat = Rs.getString("alamat");
                String tgl_tambah = Rs.getString("tgl_tambah");
                String tgl_update = Rs.getString("tgl_update");
                model.addRow(new Object[]{
                    id_mustahik, nama_mustahik, status, alamat, tgl_tambah, tgl_update
                });
            }
            jt.setModel(model);

        } catch (Exception e) {
        }
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void namaPerwakilanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaPerwakilanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaPerwakilanActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (namaPerwakilan.getText().isEmpty() || alamat.getText().isEmpty() || jComboBox1.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Form Harus Terisi");
        } else {
//            JOptionPane.showMessageDialog(this, jComboBox1.getSelectedIndex());
            try {
                CountProd();
                Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
                PreparedStatement Pst = Con.prepareStatement("insert into mustahik values(?,?,?,?,?,?)");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                Pst.setInt(1, PrNum);
                Pst.setString(2, namaPerwakilan.getText());
                Pst.setString(3, alamat.getText());
                Pst.setString(4, jComboBox1.getSelectedItem().toString());
                Pst.setString(5, dtf.format(now));
                Pst.setString(6, dtf.format(now));
                dataLogAdminMustahik("Tambah", idMustahikAdmin.getText(), dtf.format(now), PrNum);
                int row = Pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data Mustahik (" + namaPerwakilan.getText() + ") Ditambahkan");
                Con.close();
                namaPerwakilan.setText(null);
                alamat.setText(null);
                showProducts(tblMustahik, 0);
                tampilPagi(tblMustahik);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void comboStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboStatusActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_comboStatusActionPerformed

    int Key = 0;

    private void tblMustahikMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMustahikMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblMustahik.getModel();
        int MyIndex = tblMustahik.getSelectedRow();
        Key = Integer.valueOf(model.getValueAt(MyIndex, 0).toString());
        namaPerwakilan.setText(model.getValueAt(MyIndex, 1).toString());
        alamat.setText(model.getValueAt(MyIndex, 2).toString());
        jComboBox1.setSelectedItem(model.getValueAt(MyIndex, 3));
    }//GEN-LAST:event_tblMustahikMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (namaPerwakilan.getText().isEmpty() || alamat.getText().isEmpty() || jComboBox1.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Form Harus Terisi");
        } else {
//            JOptionPane.showMessageDialog(this, jComboBox1.getSelectedIndex());
            try {
                CountProd();
                Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
                PreparedStatement Pst = Con.prepareStatement("UPDATE mustahik SET nama_mustahik=?,alamat=?,status=?, tgl_update=? WHERE id=?");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                Pst.setInt(5, Key);
                Pst.setString(1, namaPerwakilan.getText());
                Pst.setString(2, alamat.getText());
                Pst.setString(3, jComboBox1.getSelectedItem().toString());
                Pst.setString(4, dtf.format(now));
                dataLogAdminMustahik("Update", idMustahikAdmin.getText(), dtf.format(now), Key);
                int row = Pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data Mustahik (" + namaPerwakilan.getText() + ") Diupdate");
                Con.close();
                namaPerwakilan.setText(null);
                alamat.setText(null);
                showProducts(tblMustahik, 0);
                tampilPagi(tblMustahik);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex);
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
//            JOptionPane.showMessageDialog(this, jComboBox1.getSelectedIndex());
        try {
            CountProd();
            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
            PreparedStatement Pst = Con.prepareStatement("DELETE FROM mustahik WHERE id=?");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            Pst.setInt(1, Key);
            dataLogAdminMustahik("Hapus", idMustahikAdmin.getText(), dtf.format(now), Key);
            int row = Pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Mustahik (" + namaPerwakilan.getText() + ") Dihapus");
            Con.close();
            namaPerwakilan.setText(null);
            alamat.setText(null);
            showProducts(tblMustahik, 0);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void comboStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboStatusItemStateChanged
        // TODO add your handling code here:
        int page = Integer.parseInt(paginasiBox.getSelectedItem().toString());
        int pss = (page - 1) * batas;
        showProducts(tblMustahik, pss);
        tampilPagi(tblMustahik);
    }//GEN-LAST:event_comboStatusItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        // Refresh
        namaMustahik.setText(null);
        tanggalan.setDate(null);
        comboStatus.setSelectedIndex(0);
        showProducts(tblMustahik, 0);
        tampilPagi(tblMustahik);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void namaMustahikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaMustahikActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaMustahikActionPerformed

    private void cariNamaButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariNamaButActionPerformed
        // TODO add your handling code here:
//        cariData();
        int page = Integer.parseInt(paginasiBox.getSelectedItem().toString());
        int pss = (page - 1) * batas;
        showProducts(tblMustahik, pss);
        tampilPagi(tblMustahik);
    }//GEN-LAST:event_cariNamaButActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
        showProducts(tblMustahik, 0);
        tampilPagi(tblMustahik);
        namaPerwakilan.setText(null);
        alamat.setText(null);
        comboStatus.setSelectedIndex(0);
    }//GEN-LAST:event_clearActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        JFrame jfAtur = new Atur();
        jfAtur.show();

        dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        JFrame jfMuzaki = new Muzaki();
        jfMuzaki.show();

        dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        JFrame jfIndex = new Index();
        jfIndex.show();

        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void btnTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTanggalActionPerformed
        // TODO add your handling code here:
//        filterTanggal();
        int page = Integer.parseInt(paginasiBox.getSelectedItem().toString());
        int pss = (page - 1) * batas;
        showProducts(tblMustahik, pss);
        tampilPagi(tblMustahik);
    }//GEN-LAST:event_btnTanggalActionPerformed

    private void paginasiBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_paginasiBoxItemStateChanged
        // TODO add your handling code here:
        try {
            int page = Integer.parseInt(paginasiBox.getSelectedItem().toString());
            int pss = (page - 1) * batas;
            showProducts(tblMustahik, pss);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_paginasiBoxItemStateChanged

    private void printDataExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printDataExcelActionPerformed
        // TODO add your handling code here:
//        exportExcel(MuzakiList);
        exportExcel(tblMustahik);
    }//GEN-LAST:event_printDataExcelActionPerformed

    private void printData1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printData1ActionPerformed
        try {
            // TODO add your handling code here:
            tblMustahik.print();
        } catch (PrinterException ex) {
            Logger.getLogger(Muzaki.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_printData1ActionPerformed

    private void paginasiBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paginasiBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paginasiBoxActionPerformed

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

    private void SuperAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SuperAdminMouseClicked
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

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        // TODO add your handling code here:
        this.setExtendedState(Mustahik.ICONIFIED);
    }//GEN-LAST:event_jLabel15MouseClicked

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
            java.util.logging.Logger.getLogger(Mustahik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mustahik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mustahik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mustahik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        if (idMustahikAdmin.getText().isEmpty()) {
            JFrame login = new Login();
            login.show();
        } else {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new Mustahik().setVisible(true);
                }
            });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable MuzakiList;
    private javax.swing.JLabel SuperAdmin;
    private javax.swing.JTextArea alamat;
    private javax.swing.JButton btnTanggal;
    private javax.swing.JButton cariNamaBut;
    private javax.swing.JButton clear;
    private javax.swing.JComboBox<String> comboStatus;
    public static final javax.swing.JTextField idMustahikAdmin = new javax.swing.JTextField();
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField namaAdminMustahik;
    private javax.swing.JTextField namaMustahik;
    private javax.swing.JTextField namaPerwakilan;
    private javax.swing.JComboBox<String> paginasiBox;
    private javax.swing.JLabel penggunaPage;
    private javax.swing.JButton printData1;
    private javax.swing.JButton printDataExcel;
    private javax.swing.JTextField roleAdmin;
    private com.toedter.calendar.JDateChooser tanggalan;
    private javax.swing.JTable tblMustahik;
    // End of variables declaration//GEN-END:variables
}
