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
import static zakats.Mustahik.idMustahikAdmin;

public class Atur extends javax.swing.JFrame {

    public Atur() {
        initComponents();
        showItems();
        dataAdmin();
    }
   
    ResultSet Rs = null, Rs1 = null, Rs2 = null, Rs3 = null;
    Connection Con = null, Con1 = null;
    Statement St = null, St1 = null, St2 = null, St3 = null;
    int xMouse,yMouse;
    
    @SuppressWarnings("unchecked")
    
    private  void dataAdmin(){
        try {
            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
            St = Con.createStatement();
            Rs = St.executeQuery("SELECT * FROM admin WHERE id_admin = '"+idMustahikAdmin.getText()+"'");
            if(Rs.next()){
                namaAdminAtur.setText("Nama Admin : "+Rs.getString(2));
                roleAdmin.setText(Rs.getString(5));
            }
            else{
                JOptionPane.showMessageDialog(this, "Kamu belum login");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    int PrNumLogMuzaki;
    private void dataLogAdminMuzaki(String id_admin,String nama_item, String waktu, String jumlah){
        try {
            St3 = Con.createStatement();
            Rs3 = St3.executeQuery("SELECT MAX(id_log_item) FROM log_item");
            Rs3.next();
            PrNumLogMuzaki = Rs3.getInt(1)+1;
            
            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
            
            PreparedStatement PstLogMuzaki = Con.prepareStatement("insert into log_item values(?,?,?,?,?)");
            PstLogMuzaki.setInt(1, PrNumLogMuzaki);
            PstLogMuzaki.setString(2, id_admin);
            PstLogMuzaki.setString(3, nama_item);
            PstLogMuzaki.setString(4, waktu);
            PstLogMuzaki.setString(5, jumlah);
            int row = PstLogMuzaki.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    
    private void showItems(){
        try {
             Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
             St = Con.createStatement();
             Rs = St.executeQuery("SELECT * FROM items");
             TabelRupiah.setModel(DbUtils.resultSetToTableModel(Rs));
        } catch (Exception e) {
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        SuperAdmin = new javax.swing.JLabel();
        penggunaPage = new javax.swing.JLabel();
        logAct = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tipe = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jumlah = new javax.swing.JTextField();
        btnEdit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelRupiah = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        namaAdminAtur = new javax.swing.JTextField();
        roleAdmin = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        jLabel13.setText("jLabel12");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

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
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(SuperAdmin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                .addComponent(penggunaPage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        jLabel9.setBackground(new java.awt.Color(116, 202, 138));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(116, 202, 138));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Atur");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(116, 202, 138));
        jLabel10.setText("Tipe");

        tipe.setEditable(false);
        tipe.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipeActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(116, 202, 138));
        jLabel11.setText("Jumlah");

        jumlah.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahActionPerformed(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(255, 153, 0));
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Update");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        TabelRupiah.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tipe", "Rupiah"
            }
        ));
        TabelRupiah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelRupiahMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelRupiah);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        namaAdminAtur.setEditable(false);
        namaAdminAtur.setBackground(new java.awt.Color(255, 255, 255));
        namaAdminAtur.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        namaAdminAtur.setForeground(new java.awt.Color(116, 202, 138));
        namaAdminAtur.setBorder(null);
        namaAdminAtur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaAdminAturActionPerformed(evt);
            }
        });

        roleAdmin.setEditable(false);
        roleAdmin.setBackground(new java.awt.Color(255, 255, 255));
        roleAdmin.setForeground(new java.awt.Color(255, 255, 255));
        roleAdmin.setBorder(null);

        jLabel12.setIcon(new javax.swing.ImageIcon("C:\\Users\\FARHAN\\Documents\\Tugas dan materi Kuliah UPN\\Semester 4\\PBO\\Gambar Asset\\flaticon\\minimize.png")); // NOI18N
        jLabel12.setText("jLabel12");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(namaAdminAtur, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roleAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namaAdminAtur, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(roleAdmin)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout logActLayout = new javax.swing.GroupLayout(logAct);
        logAct.setLayout(logActLayout);
        logActLayout.setHorizontalGroup(
            logActLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logActLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(logActLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(logActLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel10)
                        .addComponent(jLabel11)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tipe, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                        .addComponent(jumlah))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 952, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(284, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        logActLayout.setVerticalGroup(
            logActLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logActLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tipe, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(460, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(logAct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(logAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipeActionPerformed

    }//GEN-LAST:event_tipeActionPerformed

    private void jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        if(tipe.getText().isEmpty() || jumlah.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Form Harus Terisi");
        }
        else{
//            JOptionPane.showMessageDialog(this, jComboBox1.getSelectedIndex());
            try {
                
                Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
                PreparedStatement Pst = Con.prepareStatement("UPDATE items SET Item=?,Keterangan=? WHERE ID=?");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                LocalDateTime now = LocalDateTime.now();
                Pst.setInt(3, Key);
                Pst.setString(1, tipe.getText());
                Pst.setString(2, jumlah.getText());
                dataLogAdminMuzaki(idMustahikAdmin.getText(), tipe.getText(), dtf.format(now), jumlah.getText());
                int row = Pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data ("+ tipe.getText() +") Diupdate");
                Con.close();
                tipe.setText(null);
                jumlah.setText(null);
                showItems();
            } catch (SQLException ex) {
            }
        }
    }//GEN-LAST:event_btnEditActionPerformed
    
    int Key = 0;
    private void TabelRupiahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelRupiahMouseClicked
        DefaultTableModel model = (DefaultTableModel) TabelRupiah.getModel();
        int MyIndex = TabelRupiah.getSelectedRow();
        Key = Integer.valueOf(model.getValueAt(MyIndex, 0).toString());
        jumlah.setText(model.getValueAt(MyIndex, 2).toString());
        tipe.setText(model.getValueAt(MyIndex, 1).toString());
    }//GEN-LAST:event_TabelRupiahMouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        new Login().setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

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

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        JFrame jfIndex = new Index();
        jfIndex.show();
        
        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void namaAdminAturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaAdminAturActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaAdminAturActionPerformed

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

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
        this.setExtendedState(Atur.ICONIFIED);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13MouseClicked
    
    public static void main(String args[]) {
        if(idMustahikAdmin.getText().isEmpty()){
            JFrame JFlogin = new Login();
            JFlogin.show();
        }
        else{
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Atur().setVisible(true);
            }
        });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel SuperAdmin;
    private javax.swing.JTable TabelRupiah;
    private javax.swing.JButton btnEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jumlah;
    private javax.swing.JPanel logAct;
    private javax.swing.JTextField namaAdminAtur;
    private javax.swing.JLabel penggunaPage;
    private javax.swing.JTextField roleAdmin;
    private javax.swing.JTextField tipe;
    // End of variables declaration//GEN-END:variables
}
