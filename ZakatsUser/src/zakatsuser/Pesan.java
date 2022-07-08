package zakatsuser;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komponen.DialogAlert;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import pesangagal.PesanGagal;
import static zakatsuser.Dashboard.idAll;
import static zakatsuser.Pembayaran.jumlahJiwa;
import static zakatsuser.Pembayaran.shodaqoh;
import static zakatsuser.Pembayaran.zakatuang;

public class Pesan extends DialogAlert {

    public Pesan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        subTotal.setBackground(new Color(0,0,0,0));
        dibayarkan.setBackground(new Color(0,0,0,0));
        kembalians.setBackground(new Color(0,0,0,0));
    }
    
    ResultSet Rs = null, Rs1 = null;
    Connection Con = null;
    Statement St = null, St1 = null;
    
    int PrNum;
    
    
    private void CountProd() {
        try {
            St1 = Con.createStatement();
            Rs1 = St1.executeQuery("SELECT MAX(id_muzaki) FROM muzaki");
            Rs1.next();
            PrNum = Rs1.getInt(1) + 1;

        } catch (Exception e) {
        }
    }
    
    private void cetakKwi(int id) {
        
        HashMap a = new HashMap();
        a.put("id_muz", id);
        try {
            try {
                Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
            } catch (SQLException ex) {
                Logger.getLogger(Pesan.class.getName()).log(Level.SEVERE, null, ex);
            }
            
//            InputStream file = getClass().getResourceAsStream("report1.jrxml");
            JasperDesign jd = JRXmlLoader.load("C:\\Users\\FARHAN\\Documents\\Tugas dan materi Kuliah UPN\\Semester 4\\PBO\\ZakatsUser\\src\\laporan\\report1.jrxml");
            JasperReport jreports = JasperCompileManager.compileReport(jd);
            
            JasperPrint jprints = JasperFillManager.fillReport(jreports, a, Con);
            JasperViewer.viewReport(jprints,false);
 
        } catch (JRException e) {
            Logger.getLogger(Pesan.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private void clear(){
        subTotal.setText(null);
        dibayarkan.setText(null);
        kembalians.setText(null);
        jumlahJiwa.setText(null);
        zakatuang.setText(null);
        shodaqoh.setText(null);
    }
    
    private void iniPesan(String pesanq){
        PesanGagal ff = new PesanGagal();
        ff.PesanGG(pesanq);
        ff.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tunaikanBTN = new com.k33ptoo.components.KButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        tunaikanBTN.setText("Tunaikan");
        tunaikanBTN.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        tunaikanBTN.setkBorderRadius(40);
        tunaikanBTN.setkHoverColor(new java.awt.Color(0, 0, 0));
        tunaikanBTN.setkHoverEndColor(new java.awt.Color(153, 153, 153));
        tunaikanBTN.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        tunaikanBTN.setkHoverStartColor(new java.awt.Color(204, 255, 255));
        tunaikanBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tunaikanBTNActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Sub Total");

        subTotal.setEditable(false);
        subTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        subTotal.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        subTotal.setOpaque(false);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Dibayarkan");

        dibayarkan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dibayarkan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        dibayarkan.setOpaque(false);
        dibayarkan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dibayarkanKeyReleased(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("Kembalian");

        kembalians.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        kembalians.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        kembalians.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("X");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\FARHAN\\Documents\\Tugas dan materi Kuliah UPN\\Semester 4\\PBO\\Gambar Asset\\user\\payna.png")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(tunaikanBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(204, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(subTotal, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(kembalians, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dibayarkan)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)))
                .addContainerGap())
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(13, 13, 13)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dibayarkan, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kembalians, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tunaikanBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tunaikanBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tunaikanBTNActionPerformed
        if(Integer.parseInt(dibayarkan.getText()) < Integer.parseInt(subTotal.getText())){
            int kekurangan = -(Integer.parseInt(dibayarkan.getText()) - Integer.parseInt(subTotal.getText()) );
            iniPesan("Jumlah Yang Harus Dibayarkan Ternyata Kurang " + kekurangan+ " Rupiah");
        }
        else{
            try {
                Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbzakat", "root", "");
                St = Con.createStatement();
                Rs = St.executeQuery("SELECT nama_pengguna, nik FROM pengguna WHERE id_pengguna='"+idAll.getText()+"'");
                if(Rs.next()){
                    CountProd();
                    PreparedStatement Pst = Con.prepareStatement("insert into muzaki values(?,?,?,?,?,?,?,?,?,?,?,?)");
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    Pst.setInt(1, PrNum);
                    Pst.setString(2, Rs.getString(1));
                    Pst.setString(3, jumlahJiwa.getText());
                    Pst.setString(4, "0");
                    Pst.setString(5, zakatuang.getText());
                    Pst.setString(6, shodaqoh.getText());
                    Pst.setString(7, Rs.getString(2));
                    Pst.setString(8, dtf.format(now));
                    Pst.setString(9, dtf.format(now));
                    Pst.setInt(10, Integer.parseInt(subTotal.getText()));
                    Pst.setInt(11, Integer.parseInt(dibayarkan.getText()));
                    Pst.setInt(12, Integer.parseInt(kembalians.getText()));
                    int row = Pst.executeUpdate();
                    if(row == 1){
                        cetakKwi(PrNum);
                        Con.close();
                        clear();
                        closeAlert();
                    }
                    else{
                        iniPesan("Data Gagal Ditambahkan Karena Sistem Sedang Pemeliharaan");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Pesan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tunaikanBTNActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        closeAlert();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void dibayarkanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dibayarkanKeyReleased
        // TODO add your handling code here:
        int kembalian = Integer.parseInt(dibayarkan.getText()) - Integer.parseInt(subTotal.getText());
        kembalians.setText(Integer.toString(kembalian));
    }//GEN-LAST:event_dibayarkanKeyReleased

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
            java.util.logging.Logger.getLogger(Pesan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pesan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pesan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pesan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Pesan dialog = new Pesan(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
            dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final javax.swing.JTextField dibayarkan = new javax.swing.JTextField();
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel4;
    public static final javax.swing.JTextField kembalians = new javax.swing.JTextField();
    public static final javax.swing.JTextField subTotal = new javax.swing.JTextField();
    private com.k33ptoo.components.KButton tunaikanBTN;
    // End of variables declaration//GEN-END:variables
}
