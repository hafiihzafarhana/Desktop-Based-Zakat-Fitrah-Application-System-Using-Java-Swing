package zakats;

//Import dari JAR atau beberapa library
import com.lowagie.text.pdf.codec.Base64;
import java.awt.Desktop;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Muzaki extends javax.swing.JFrame {

    // Constructor (akan dijalankan terlebuh dahulu   
    public Muzaki() {
//        Setiap halaman tabel hanya berisi x data
        batas = 2;
        initComponents();
//        Set data Admin
        dataAdmin();
//        Menampilkan nama muzaki sesuai query
        showProducts(MuzakiList, 0);
//        paginasi sesuai dengan query
        tampilPagi(MuzakiList);
    }

//  Beberapa variabel
    ResultSet Rs = null, Rs1 = null, Rs2 = null, Rs3 = null;
    Connection Con = null, Con1 = null;
    Statement St = null, St1 = null, St2 = null, St3 = null;
    int PrNum, beras, uang, PrNumLogMuzaki, batas, Key = 0;
    String sqlshow = "", pagisqlshow = "", role;
    int xMouse,yMouse;
    
    @SuppressWarnings("unchecked")

//    Melaukan clear terhadap field input
    private void clear() {
        namaMuz.setText(null);
        jumlahJiwa.setText(null);
        zakatBeras.setText(null);
        zakatUang.setText(null);
        shodaqoh.setText(null);
        nik.setText(null);
        subTotal.setText(null);
        dibayarkan.setText(null);
        kembalian.setText(null);
    }

//    Cetak kwitansi saat menambah atau update data
    private void cetakKwi(int id) {
        
        HashMap a = new HashMap();
        a.put("id_muz", id);
        try {
            try {
                Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
            } catch (SQLException ex) {
                Logger.getLogger(Muzaki.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            InputStream file = getClass().getResourceAsStream("report1.jrxml");
            JasperDesign jd = JRXmlLoader.load("C:\\Users\\FARHAN\\Documents\\Tugas dan materi Kuliah UPN\\Semester 4\\PBO\\Zakats\\src\\zakats\\report1.jrxml");
            JasperReport jreports = JasperCompileManager.compileReport(jd);
            
            JasperPrint jprints = JasperFillManager.fillReport(jreports, a, Con);
            JasperViewer.viewReport(jprints,false);
 
        } catch (JRException e) {
            Logger.getLogger(Muzaki.class.getName()).log(Level.SEVERE, null, e);
        }
    }

//    Paginasi
    public void tampilPagi(JTable jt) {
        try {
            String pagisqlshow = "";
            if (cariNamaMuz.getText().length() != 0) {
                pagisqlshow = "SELECT * FROM muzaki WHERE nama_muzaki LIKE '%" + cariNamaMuz.getText() + "%'";
            } else if (tanggalan.getDate() != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(tanggalan.getCalendar().getTimeInMillis());
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(Calendar.MONTH) + 1;
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);
                String yesZeroMoon = (mMonth < 10) ? "0" : "";
                String yesZeroDay = (mDay < 10) ? "0" : "";
                  
                String result = mYear + "/" + yesZeroMoon + mMonth + "/" + yesZeroDay + mDay;
                
                pagisqlshow = "SELECT * FROM muzaki WHERE tgl_tambah LIKE '%" + result + "%'";
            } else {
                pagisqlshow = "SELECT * FROM muzaki";
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

//    Membuka folder desktop apabila ingin export data ke Excel
    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e + "bagian openFIle");
        }
    }

//    Algoritma kirim data menjadi excel
    public void exportExcel(JTable jk) {
        try {
            JFileChooser JFileChooser = new JFileChooser();
            JFileChooser.showSaveDialog(jk);
            File saveFile = JFileChooser.getSelectedFile();
            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("Muzaki");

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

//    Menampung data admin yang login
    private void dataAdmin() {
        try {
            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
            St = Con.createStatement();
            Rs = St.executeQuery("SELECT * FROM admin WHERE id_admin = '" + IdAdminMuzaki.getText() + "'");
            if (Rs.next()) {
                NamaAdmin.setText("Nama Admin : " + Rs.getString(2));
                roleAdmin.setText(Rs.getString(5));
            } else {
                JOptionPane.showMessageDialog(this, "Anda belum login");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    
    private void CountProd() {
        try {
            St1 = Con.createStatement();
            Rs1 = St1.executeQuery("SELECT MAX(id_muzaki) FROM muzaki");
            Rs1.next();
            PrNum = Rs1.getInt(1) + 1;

        } catch (Exception e) {
        }
    }

//    Aktivitas admin apabila ingin melakukan CRUD di halaman muzaki
    private void dataLogAdminMuzaki(String info, String id_admin, String waktu, int id_muzaki) {
        try {
            St3 = Con.createStatement();
            Rs3 = St3.executeQuery("SELECT MAX(id_log_muzaki) FROM log_muzaki");
            Rs3.next();
            PrNumLogMuzaki = Rs3.getInt(1) + 1;

            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");

            PreparedStatement PstLogMuzaki = Con.prepareStatement("insert into log_muzaki values(?,?,?,?,?)");
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

//    mendapat jumlah beras yang harus dibayar
    private void ambilBeras() {
        try {
            St2 = Con.createStatement();
            Rs2 = St2.executeQuery("SELECT Keterangan FROM items WHERE Item='Beras' LIMIT 1");
            Rs2.next();
            beras = Rs2.getInt(1);
        } catch (Exception e) {
        }
    }

//    mendapat jumlah uang yang harus dibayar
    private void ambilUang() {
        try {
            St3 = Con.createStatement();
            Rs3 = St3.executeQuery("SELECT Keterangan FROM items WHERE Item='Rupiah' LIMIT 1");
            Rs3.next();
            uang = Rs3.getInt(1);
        } catch (Exception e) {
        }
    }

//    Menampilkan data muzaki
    private void showProducts(JTable jt, int posisi) {
        DefaultTableModel model = new DefaultTableModel(new String[]{
            "ID Muzaki", "Nama Muzaki", "Jumlah Jiwa", "Jumlah Zakat Beras", "Jumlah Zakat Uang", "Shodaqoh", "NIK",
            "Sub Total", "Dibayarkan", "Kembalian", "TGL Tambah", "TGL Update"
        }, 0);
        if (cariNamaMuz.getText().length() != 0) {
            sqlshow = "SELECT * FROM muzaki WHERE nama_muzaki LIKE '%" + cariNamaMuz.getText() + "%' LIMIT " + posisi + "," + batas;
        } else if (tanggalan.getDate() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(tanggalan.getCalendar().getTimeInMillis());
            int mYear = calendar.get(Calendar.YEAR);
            int mMonth = calendar.get(Calendar.MONTH) + 1;
            int mDay = calendar.get(Calendar.DAY_OF_MONTH);
            String yesZeroMoon = (mMonth < 10) ? "0" : "";
            String yesZeroDay = (mDay < 10) ? "0" : "";

            String result = mYear + "/" + yesZeroMoon + mMonth + "/" + yesZeroDay + mDay;
            sqlshow = "SELECT * FROM muzaki WHERE tgl_tambah LIKE '%" + result + "%' LIMIT " + posisi + "," + batas;

        }else {
            sqlshow = "SELECT * FROM muzaki LIMIT " + posisi + "," + batas;
        }
        try {

            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
            St = Con.createStatement();
            Rs = St.executeQuery(sqlshow);
            while (Rs.next()) {
                int id_muzaki = Rs.getInt("id_muzaki");
                String nama_muzaki = Rs.getString("nama_muzaki");
                int jumlah_jiwa = Rs.getInt("jumlah_jiwa");
                int jumlah_zakat_beras = Rs.getInt("jumlah_zakat_beras");
                int jumlah_zakat_uang = Rs.getInt("jumlah_zakat_uang");
                int shodaqoh = Rs.getInt("shodaqoh");
                int nik = Rs.getInt("nik");
                String tgl_tambah = Rs.getString("tgl_tambah");
                String tgl_update = Rs.getString("tgl_update");
                int subTot = Rs.getInt("subTotal");
                int pembayaran = Rs.getInt("pembayaran");
                int kembalian = Rs.getInt("kembalian");
                model.addRow(new Object[]{
                    id_muzaki, nama_muzaki, jumlah_jiwa, jumlah_zakat_beras, jumlah_zakat_uang, shodaqoh, nik, subTot,
                    pembayaran, kembalian, tgl_tambah, tgl_update
                });
            }

            jt.setModel(model);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        atur = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        SuperAdmin = new javax.swing.JLabel();
        penggunaPage = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        namaMuz = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jumlahJiwa = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        zakatBeras = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        zakatUang = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        shodaqoh = new javax.swing.JTextField();
        nik = new javax.swing.JTextField();
        hapusMuza = new javax.swing.JButton();
        tambah_muzaki = new javax.swing.JButton();
        printData = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        MuzakiList = new javax.swing.JTable();
        editMuza = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        cariNamaMuz = new javax.swing.JTextField();
        cariNama = new javax.swing.JButton();
        clearCari = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        tanggalan = new com.toedter.calendar.JDateChooser();
        ditanggalin = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        roleAdmin = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        printDataExcel = new javax.swing.JButton();
        paginasiBox = new javax.swing.JComboBox<>();
        btnKalkulasi = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        subTotal = new javax.swing.JTextField();
        dibayarkan = new javax.swing.JTextField();
        kembalian = new javax.swing.JTextField();

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

        atur.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        atur.setForeground(new java.awt.Color(255, 255, 255));
        atur.setIcon(new javax.swing.ImageIcon("C:\\Users\\FARHAN\\Documents\\Tugas dan materi Kuliah UPN\\Semester 4\\PBO\\Gambar Asset\\new admin\\setting-lines.png")); // NOI18N
        atur.setText("Pengaturan");
        atur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aturMouseClicked(evt);
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

        logout.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        logout.setForeground(new java.awt.Color(255, 255, 255));
        logout.setIcon(new javax.swing.ImageIcon("C:\\Users\\FARHAN\\Documents\\Tugas dan materi Kuliah UPN\\Semester 4\\PBO\\Gambar Asset\\user\\logout.png")); // NOI18N
        logout.setText("Logout");
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("ADMIN");

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
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SuperAdmin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(atur, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(90, 90, 90))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(penggunaPage, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(atur)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(SuperAdmin)
                .addGap(18, 18, 18)
                .addComponent(penggunaPage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel5.setBackground(new java.awt.Color(116, 202, 138));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(116, 202, 138));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Muzaki");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(116, 202, 138));
        jLabel8.setText("Nama Perwakilan");

        namaMuz.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        namaMuz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaMuzActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(116, 202, 138));
        jLabel9.setText("Jumlah Jiwa");

        jumlahJiwa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jumlahJiwa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahJiwaActionPerformed(evt);
            }
        });
        jumlahJiwa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jumlahJiwaKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(116, 202, 138));
        jLabel10.setText("Jumlah Zakat Beras");

        zakatBeras.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        zakatBeras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zakatBerasActionPerformed(evt);
            }
        });
        zakatBeras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                zakatBerasKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(116, 202, 138));
        jLabel11.setText("Jumlah Zakat Uang");

        zakatUang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        zakatUang.setText("0");
        zakatUang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zakatUangActionPerformed(evt);
            }
        });
        zakatUang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                zakatUangKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(116, 202, 138));
        jLabel12.setText("Shodaqoh");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(116, 202, 138));
        jLabel13.setText("NIK");

        shodaqoh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        shodaqoh.setText("0");
        shodaqoh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shodaqohActionPerformed(evt);
            }
        });
        shodaqoh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                shodaqohKeyReleased(evt);
            }
        });

        nik.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nikActionPerformed(evt);
            }
        });

        hapusMuza.setBackground(new java.awt.Color(212, 12, 16));
        hapusMuza.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        hapusMuza.setForeground(new java.awt.Color(255, 255, 255));
        hapusMuza.setText("Hapus");
        hapusMuza.setBorderPainted(false);
        hapusMuza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusMuzaActionPerformed(evt);
            }
        });

        tambah_muzaki.setBackground(new java.awt.Color(116, 202, 138));
        tambah_muzaki.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tambah_muzaki.setForeground(new java.awt.Color(255, 255, 255));
        tambah_muzaki.setText("Tambah");
        tambah_muzaki.setBorderPainted(false);
        tambah_muzaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah_muzakiActionPerformed(evt);
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

        MuzakiList.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        MuzakiList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Nama", "Jiwa", "Beras (kg)", "Uang (Rp)", "Shodaqoh", "RT", "Tanggal Tambah"
            }
        ));
        MuzakiList.setShowVerticalLines(false);
        MuzakiList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MuzakiListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(MuzakiList);
        if (MuzakiList.getColumnModel().getColumnCount() > 0) {
            MuzakiList.getColumnModel().getColumn(4).setMaxWidth(20);
        }

        editMuza.setBackground(new java.awt.Color(255, 153, 0));
        editMuza.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        editMuza.setForeground(new java.awt.Color(255, 255, 255));
        editMuza.setText("Update");
        editMuza.setBorderPainted(false);
        editMuza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMuzaActionPerformed(evt);
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

        cariNamaMuz.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cariNamaMuz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariNamaMuzActionPerformed(evt);
            }
        });

        cariNama.setBackground(new java.awt.Color(51, 255, 51));
        cariNama.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cariNama.setForeground(new java.awt.Color(255, 255, 255));
        cariNama.setText("Cari Nama");
        cariNama.setBorderPainted(false);
        cariNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariNamaActionPerformed(evt);
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

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(116, 202, 138));
        jLabel14.setText("Cari (Nama)");

        tanggalan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        ditanggalin.setBackground(new java.awt.Color(51, 153, 255));
        ditanggalin.setForeground(new java.awt.Color(255, 255, 255));
        ditanggalin.setText("Cari Tanggal");
        ditanggalin.setBorderPainted(false);
        ditanggalin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ditanggalinActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(116, 202, 138));
        jLabel15.setText("Cari (Tanggal)");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        roleMuzaki.setForeground(new java.awt.Color(255, 255, 255));

        NamaAdmin.setEditable(false);
        NamaAdmin.setBackground(new java.awt.Color(255, 255, 255));
        NamaAdmin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        NamaAdmin.setForeground(new java.awt.Color(116, 202, 138));
        NamaAdmin.setBorder(null);
        NamaAdmin.setDisabledTextColor(new java.awt.Color(255, 255, 255));

        roleAdmin.setEditable(false);
        roleAdmin.setBackground(new java.awt.Color(255, 255, 255));
        roleAdmin.setForeground(new java.awt.Color(255, 255, 255));
        roleAdmin.setText("jTextField1");
        roleAdmin.setBorder(null);
        roleAdmin.setDisabledTextColor(new java.awt.Color(255, 255, 255));

        IdAdminMuzaki.setEditable(false);
        IdAdminMuzaki.setBackground(new java.awt.Color(255, 255, 255));
        IdAdminMuzaki.setForeground(new java.awt.Color(255, 255, 255));
        IdAdminMuzaki.setBorder(null);
        IdAdminMuzaki.setCaretColor(new java.awt.Color(240, 240, 240));
        IdAdminMuzaki.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        IdAdminMuzaki.setSelectionColor(new java.awt.Color(255, 255, 255));
        IdAdminMuzaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdAdminMuzakiActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(153, 153, 0));
        jButton1.setText("Kwitansi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\FARHAN\\Documents\\Tugas dan materi Kuliah UPN\\Semester 4\\PBO\\Gambar Asset\\flaticon\\minimize.png")); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(NamaAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roleMuzaki)
                .addGap(18, 18, 18)
                .addComponent(roleAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(156, 156, 156)
                .addComponent(IdAdminMuzaki, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roleMuzaki, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NamaAdmin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(roleAdmin, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jButton1))
                            .addComponent(IdAdminMuzaki, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

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

        paginasiBox.setBackground(new java.awt.Color(0, 102, 204));
        paginasiBox.setForeground(new java.awt.Color(255, 255, 255));
        paginasiBox.setAutoscrolls(true);
        paginasiBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                paginasiBoxItemStateChanged(evt);
            }
        });

        btnKalkulasi.setBackground(new java.awt.Color(153, 204, 0));
        btnKalkulasi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKalkulasi.setForeground(new java.awt.Color(255, 255, 255));
        btnKalkulasi.setText("Kalkulasi");
        btnKalkulasi.setBorderPainted(false);
        btnKalkulasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKalkulasiActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(116, 202, 138));
        jLabel16.setText("Sub Total (Uang)");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(116, 202, 138));
        jLabel17.setText("Jumlah Uang");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(116, 202, 138));
        jLabel18.setText("Kembalian");

        subTotal.setEditable(false);
        subTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        subTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                subTotalKeyReleased(evt);
            }
        });

        dibayarkan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dibayarkan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dibayarkanKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dibayarkanKeyReleased(evt);
            }
        });

        kembalian.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(shodaqoh)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nik, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(namaMuz, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jumlahJiwa, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel13))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(zakatBeras, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel18))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(zakatUang, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel16)
                                            .addComponent(subTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(dibayarkan, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(clear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(editMuza, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tambah_muzaki, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(hapusMuza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnKalkulasi, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                            .addComponent(jLabel14)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cariNamaMuz, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cariNama, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tanggalan, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ditanggalin, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel15)
                            .addComponent(clearCari, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(paginasiBox, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(printDataExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(printData, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(695, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(12, 12, 12))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namaMuz, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jumlahJiwa, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zakatBeras, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zakatUang, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(shodaqoh, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nik, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dibayarkan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tambah_muzaki, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hapusMuza, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editMuza, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKalkulasi, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cariNamaMuz, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariNama, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tanggalan, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ditanggalin, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(clearCari, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paginasiBox, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(printDataExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(printData, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void namaMuzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaMuzActionPerformed
    }//GEN-LAST:event_namaMuzActionPerformed

    private void jumlahJiwaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahJiwaActionPerformed
    }//GEN-LAST:event_jumlahJiwaActionPerformed

    private void zakatBerasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zakatBerasActionPerformed
    }//GEN-LAST:event_zakatBerasActionPerformed

    private void zakatUangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zakatUangActionPerformed
    }//GEN-LAST:event_zakatUangActionPerformed

    private void shodaqohActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shodaqohActionPerformed
    }//GEN-LAST:event_shodaqohActionPerformed

    private void nikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nikActionPerformed
    }//GEN-LAST:event_nikActionPerformed

//    Tambah data muzaki
    private void tambah_muzakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah_muzakiActionPerformed
        if (namaMuz.getText().isEmpty() || dibayarkan.getText().isEmpty() || jumlahJiwa.getText().isEmpty() || nik.getText().isEmpty() || zakatBeras.getText().isEmpty() || zakatUang.getText().isEmpty() || shodaqoh.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Form Harus Terisi");
        } else {
            try {
                CountProd();
                ambilBeras();
                ambilUang();
               
                Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
                int bagiBeras = Integer.parseInt(zakatBeras.getText()) / beras;
                //                          6/3 = 2
                if (Integer.parseInt(zakatUang.getText()) == ((Integer.parseInt(jumlahJiwa.getText()) - bagiBeras) * uang) && Integer.parseInt(dibayarkan.getText()) >= Integer.parseInt(subTotal.getText())) {
//                    40000 == (3-2)*40000
//                    40000 == (1)*40000
                    PreparedStatement Pst = Con.prepareStatement("insert into muzaki values(?,?,?,?,?,?,?,?,?,?,?,?)");
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    Pst.setInt(1, PrNum);
                    Pst.setString(2, namaMuz.getText());
                    Pst.setString(3, jumlahJiwa.getText());
                    Pst.setString(4, zakatBeras.getText());
                    Pst.setString(5, zakatUang.getText());
                    Pst.setString(6, shodaqoh.getText());
                    Pst.setString(7, nik.getText());
                    Pst.setString(8, dtf.format(now));
                    Pst.setString(9, dtf.format(now));

                    Pst.setInt(10, Integer.parseInt(subTotal.getText()));
                    Pst.setInt(11, Integer.parseInt(dibayarkan.getText()));
                    Pst.setInt(12, Integer.parseInt(kembalian.getText()));
                    dataLogAdminMuzaki("Tambah", IdAdminMuzaki.getText(), dtf.format(now), PrNum);
                    int row = Pst.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Data Muzaki (" + namaMuz.getText() + ") Ditambahkan");
   
                    cetakKwi(PrNum);
                    Con.close();
                    clear();
                } else {
                    JOptionPane.showMessageDialog(this, "Jumlah Zakat Beras atau Uang Tidak Sesuai atau uang yang dibayarkan kurang");
                }

                showProducts(MuzakiList, 0);
                tampilPagi(MuzakiList);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
        }
    }//GEN-LAST:event_tambah_muzakiActionPerformed

//    Print PDF data muzaki
    private void printDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printDataActionPerformed
        try {
            MuzakiList.print();
        } catch (PrinterException ex) {
            Logger.getLogger(Muzaki.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_printDataActionPerformed

//    Mendapatkan data muzaki apabila click data muzaki yang ada di dalam tabel,
//    lalu data akan dimasukan ke dalam field input
    private void MuzakiListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MuzakiListMouseClicked
        DefaultTableModel model = (DefaultTableModel) MuzakiList.getModel();
        int MyIndex = MuzakiList.getSelectedRow();
        Key = Integer.valueOf(model.getValueAt(MyIndex, 0).toString());
        namaMuz.setText(model.getValueAt(MyIndex, 1).toString());
        jumlahJiwa.setText(model.getValueAt(MyIndex, 2).toString());
        zakatBeras.setText(model.getValueAt(MyIndex, 3).toString());
        zakatUang.setText(model.getValueAt(MyIndex, 4).toString());
        shodaqoh.setText(model.getValueAt(MyIndex, 5).toString());
        nik.setText(model.getValueAt(MyIndex, 6).toString());
        subTotal.setText(model.getValueAt(MyIndex, 7).toString());
        dibayarkan.setText(model.getValueAt(MyIndex, 8).toString());
        kembalian.setText(model.getValueAt(MyIndex, 9).toString());
    }//GEN-LAST:event_MuzakiListMouseClicked

//    Edit Data Muzaki
    private void editMuzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMuzaActionPerformed
        if (namaMuz.getText().isEmpty() || dibayarkan.getText().isEmpty() || jumlahJiwa.getText().isEmpty() || nik.getText().isEmpty() || zakatBeras.getText().isEmpty() || zakatUang.getText().isEmpty() || shodaqoh.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Form Harus Terisi");
        } else {
            try {

                int bagiBeras = Integer.parseInt(zakatBeras.getText()) / 3;
                if (Integer.parseInt(zakatUang.getText()) == ((Integer.parseInt(jumlahJiwa.getText()) - bagiBeras) * 40000) && Integer.parseInt(dibayarkan.getText()) >= Integer.parseInt(subTotal.getText())) {
                    Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
                    PreparedStatement Pst = Con.prepareStatement("UPDATE muzaki SET nama_muzaki=?,jumlah_jiwa=?,jumlah_zakat_beras=?,jumlah_zakat_uang=?,shodaqoh=?,nik=?, tgl_update=?,subTotal=?, pembayaran=?, kembalian=? WHERE id_muzaki=?");
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    Pst.setInt(11, Key);
                    Pst.setString(1, namaMuz.getText());
                    Pst.setString(2, jumlahJiwa.getText());
                    Pst.setString(3, zakatBeras.getText());
                    Pst.setString(4, zakatUang.getText());
                    Pst.setString(5, shodaqoh.getText());
                    Pst.setString(6, nik.getText());
                    Pst.setString(7, dtf.format(now));
                    Pst.setString(8, subTotal.getText());
                    Pst.setString(9, dibayarkan.getText());
                    Pst.setString(10, kembalian.getText());
                    dataLogAdminMuzaki("Update", IdAdminMuzaki.getText(), dtf.format(now), Key);
                    int row = Pst.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Data Muzaki (" + namaMuz.getText() + ") Diupdate");
                    cetakKwi(Key);
                    Con.close();
                    clear();
                    showProducts(MuzakiList, 0);
                    tampilPagi(MuzakiList);
                } else {
                    JOptionPane.showMessageDialog(this, "Jumlah Zakat Beras atau Uang Tidak Sesuai");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex);
            }
        }
    }//GEN-LAST:event_editMuzaActionPerformed

//    hapus data muzaki
    private void hapusMuzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusMuzaActionPerformed
        try {
            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
            PreparedStatement Pst = Con.prepareStatement("DELETE FROM muzaki WHERE id_muzaki=?");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            Pst.setInt(1, Key);
            dataLogAdminMuzaki("Hapus", IdAdminMuzaki.getText(), dtf.format(now), Key);
            int row = Pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Muzaki (" + namaMuz.getText() + ") Dihapus");
            Con.close();
            clear();
            showProducts(MuzakiList, 0);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }//GEN-LAST:event_hapusMuzaActionPerformed

//    button clear
    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        clear();
    }//GEN-LAST:event_clearActionPerformed

//    button filter cari nama
    private void cariNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariNamaActionPerformed
        int page = Integer.parseInt(paginasiBox.getSelectedItem().toString());
        int pss = (page - 1) * batas;
        showProducts(MuzakiList, pss);
        tampilPagi(MuzakiList);
    }//GEN-LAST:event_cariNamaActionPerformed

//    logout
    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutMouseClicked

//    menuju halaman atur
    private void aturMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aturMouseClicked
        JFrame jfAtur = new Atur();
        jfAtur.show();
        dispose();
    }//GEN-LAST:event_aturMouseClicked

//    Menuju halaman mustahik
    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        JFrame jfMustahik = new Mustahik();
        jfMustahik.show();
        dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

//    menuju halaman index atau dashboard
    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        JFrame jfIndex = new Index();
        jfIndex.show();
        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

//    melakukan clear dield input filter
    private void clearCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearCariActionPerformed
        // TODO add your handling code here:
        cariNamaMuz.setText(null);
        tanggalan.setDate(null);
        showProducts(MuzakiList, 0);
        tampilPagi(MuzakiList);

    }//GEN-LAST:event_clearCariActionPerformed

//    button cari berdasarkan tanggal
    private void ditanggalinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ditanggalinActionPerformed
        int page = Integer.parseInt(paginasiBox.getSelectedItem().toString());
        int pss = (page - 1) * batas;
        showProducts(MuzakiList, pss);
        tampilPagi(MuzakiList);
    }//GEN-LAST:event_ditanggalinActionPerformed

    private void IdAdminMuzakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdAdminMuzakiActionPerformed
    }//GEN-LAST:event_IdAdminMuzakiActionPerformed

//    button print excel
    private void printDataExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printDataExcelActionPerformed
        exportExcel(MuzakiList);
    }//GEN-LAST:event_printDataExcelActionPerformed

//   button melakukan kalkulasi untuk pembayaran zakat uang dan shodaqoh
    private void btnKalkulasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKalkulasiActionPerformed
        try {
            int total = Integer.parseInt(shodaqoh.getText()) + Integer.parseInt(zakatUang.getText());
            subTotal.setText(Integer.toString(total));
            dibayarkan.setText(Integer.toString(total));
            kembalian.setText("0");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnKalkulasiActionPerformed

    private void dibayarkanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dibayarkanKeyPressed
    }//GEN-LAST:event_dibayarkanKeyPressed
//  Field pembayaran
    private void dibayarkanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dibayarkanKeyReleased
        int kembali = Integer.parseInt(dibayarkan.getText()) - Integer.parseInt(subTotal.getText());
        kembalian.setText(Integer.toString(kembali));
    }//GEN-LAST:event_dibayarkanKeyReleased

    private void zakatBerasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zakatBerasKeyReleased
        ambilBeras();
        ambilUang();
//        30
        int zakatBerass = Integer.parseInt(zakatBeras.getText());
//        10
//                    10                              * 3
        int tots = Integer.parseInt(jumlahJiwa.getText()) * beras;
//                                ((30 - 30)/beras) * uang
        int banyakJumlahUang = ((tots - zakatBerass)/beras) * uang;
        zakatUang.setText(Integer.toString(banyakJumlahUang));
    }//GEN-LAST:event_zakatBerasKeyReleased

    private void zakatUangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zakatUangKeyReleased
        if(zakatUang.getText().isEmpty()){
            zakatUang.setText("0");
        }
    }//GEN-LAST:event_zakatUangKeyReleased

    private void cariNamaMuzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariNamaMuzActionPerformed
    }//GEN-LAST:event_cariNamaMuzActionPerformed

//    atur paginasi
    private void paginasiBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_paginasiBoxItemStateChanged
        try {
            int page = Integer.parseInt(paginasiBox.getSelectedItem().toString());
            int pss = (page - 1) * batas;
            showProducts(MuzakiList, pss);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_paginasiBoxItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String value = JOptionPane.showInputDialog(this, "Masukan Nama Perwakilan");
        cetakKwi(Integer.parseInt(value));
    }//GEN-LAST:event_jButton1ActionPerformed

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

    private void jumlahJiwaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahJiwaKeyReleased
        // TODO add your handling code here:
        ambilBeras();
        ambilUang();
        int dapat;
        if(jumlahJiwa.getText().isEmpty() || Integer.parseInt(jumlahJiwa.getText()) < 1){
            jumlahJiwa.setText("1");
            dapat = 0;
        }
        else{
            dapat = Integer.parseInt(jumlahJiwa.getText());
        }
        
        // beras
//        int bagiBeras = Integer.parseInt(zakatBeras.getText()) / beras;
        int tot = dapat * beras;
        if(tot == 0){
            tot = 3;
        }
        zakatBeras.setText(Integer.toString(tot));
    }//GEN-LAST:event_jumlahJiwaKeyReleased

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

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        this.setExtendedState(Muzaki.ICONIFIED);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void shodaqohKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_shodaqohKeyReleased
        // TODO add your handling code here:
        if(shodaqoh.getText().isEmpty()){
            shodaqoh.setText("0");
        }
    }//GEN-LAST:event_shodaqohKeyReleased

    private void subTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_subTotalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_subTotalKeyReleased

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
            java.util.logging.Logger.getLogger(Muzaki.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Muzaki.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Muzaki.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Muzaki.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        Apabila tidak ada data admin, maka menuju ke halaman lg
        if (IdAdminMuzaki.getText().isEmpty()) {
            JFrame login = new Login();
            login.show();
        } else {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new Muzaki().setVisible(true);
                }
            });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final javax.swing.JTextField IdAdminMuzaki = new javax.swing.JTextField();
    private javax.swing.JTable MuzakiList;
    public static final javax.swing.JTextField NamaAdmin = new javax.swing.JTextField();
    private javax.swing.JLabel SuperAdmin;
    private javax.swing.JLabel atur;
    private javax.swing.JButton btnKalkulasi;
    private javax.swing.JButton cariNama;
    private javax.swing.JTextField cariNamaMuz;
    private javax.swing.JButton clear;
    private javax.swing.JButton clearCari;
    private javax.swing.JTextField dibayarkan;
    private javax.swing.JButton ditanggalin;
    private javax.swing.JButton editMuza;
    private javax.swing.JButton hapusMuza;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jumlahJiwa;
    private javax.swing.JTextField kembalian;
    private javax.swing.JLabel logout;
    private javax.swing.JTextField namaMuz;
    private javax.swing.JTextField nik;
    private javax.swing.JComboBox<String> paginasiBox;
    private javax.swing.JLabel penggunaPage;
    private javax.swing.JButton printData;
    private javax.swing.JButton printDataExcel;
    private javax.swing.JTextField roleAdmin;
    public static final javax.swing.JLabel roleMuzaki = new javax.swing.JLabel();
    private javax.swing.JTextField shodaqoh;
    private javax.swing.JTextField subTotal;
    private javax.swing.JButton tambah_muzaki;
    private com.toedter.calendar.JDateChooser tanggalan;
    private javax.swing.JTextField zakatBeras;
    private javax.swing.JTextField zakatUang;
    // End of variables declaration//GEN-END:variables
}
