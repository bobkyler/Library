/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Kyle
 */
public class jReturnBooks extends javax.swing.JFrame {

    /**
     * Creates new form jReturnBooks
     */
    
    private Account acc;
    private Connect conn;
    private ArrayList<Book> books;
    private int index;
    private String condition;
    private String date;
    
    
    public jReturnBooks(Account user) {
        initComponents(); 
        
        jBtnCancel.setOpaque(false);
        jBtnCancel.setContentAreaFilled(false); 
        jBtnCancel.setBorderPainted(false);
        
        jBtnReturnBook.setOpaque(false);
        jBtnReturnBook.setContentAreaFilled(false); 
        jBtnReturnBook.setBorderPainted(false);
        
         this.acc= user;
        conn = new Connect();
        books = conn.getBorrowedBooks(user);  
        for(Book bk: books){      //getting all the books
            jCmbBoxTitle.addItem(bk.getTitle());
        }
        index = jCmbBoxTitle.getSelectedIndex();
        String bookID = books.get(index).getBookID();
        String author = books.get(index).getAuthor();
        jLabelBookID.setText(bookID);
        jLabelAuthor.setText(author);
        
        //Date of Today
        date = java.time.LocalDate.now().toString();
        jLabelDate.setText(date);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCmbBoxTitle = new javax.swing.JComboBox<>();
        jLabelAuthor = new javax.swing.JLabel();
        jComboBoxCondition = new javax.swing.JComboBox<>();
        jLabelDate = new javax.swing.JTextField();
        jBtnReturnBook = new javax.swing.JButton();
        jBtnCancel = new javax.swing.JButton();
        jLabelBookID = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCmbBoxTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCmbBoxTitle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCmbBoxTitleMouseClicked(evt);
            }
        });
        getContentPane().add(jCmbBoxTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 270, 30));

        jLabelAuthor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(jLabelAuthor, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 283, 250, 40));

        jComboBoxCondition.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBoxCondition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Good", "Bad" }));
        jComboBoxCondition.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxConditionMouseClicked(evt);
            }
        });
        getContentPane().add(jComboBoxCondition, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 110, 30));

        jLabelDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelDate.setBorder(null);
        getContentPane().add(jLabelDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 383, 180, 37));

        jBtnReturnBook.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jBtnReturnBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnReturnBookActionPerformed(evt);
            }
        });
        getContentPane().add(jBtnReturnBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 450, 170, 50));

        jBtnCancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jBtnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(jBtnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 448, 170, 50));

        jLabelBookID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(jLabelBookID, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 193, 240, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Design/4.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jCmbBoxTitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCmbBoxTitleMouseClicked
        // TODO add your handling code here:
        index = jCmbBoxTitle.getSelectedIndex();
        String bookID = books.get(index).getBookID();
        String author = books.get(index).getAuthor();
        jLabelBookID.setText(bookID);
        jLabelAuthor.setText(author);
    }//GEN-LAST:event_jCmbBoxTitleMouseClicked

    private void jComboBoxConditionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxConditionMouseClicked
        // TODO add your handling code here:
        int condIndex = jComboBoxCondition.getSelectedIndex();
        if(condIndex ==0){
            condition = "Good";
        }else{
            condition = "Bad";
        }
    }//GEN-LAST:event_jComboBoxConditionMouseClicked

    private void jBtnReturnBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnReturnBookActionPerformed
        // TODO add your handling code here:
        Book currentBk = books.get(index);
        if(conn.returnBook(acc, currentBk, condition, date) == true){
            JOptionPane.showMessageDialog(this, "You returned "+currentBk.getTitle());
        }else{
            JOptionPane.showMessageDialog(this, "You failed to return "+currentBk.getTitle());
        }
    }//GEN-LAST:event_jBtnReturnBookActionPerformed

    private void jBtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelActionPerformed
        // TODO add your handling code here:
        jMenu menu = new jMenu();
        menu.user(acc);
        menu.show();
        this.dispose();
    }//GEN-LAST:event_jBtnCancelActionPerformed

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
            java.util.logging.Logger.getLogger(jReturnBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jReturnBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jReturnBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jReturnBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new jReturnBooks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnCancel;
    private javax.swing.JButton jBtnReturnBook;
    private javax.swing.JComboBox<String> jCmbBoxTitle;
    private javax.swing.JComboBox<String> jComboBoxCondition;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelAuthor;
    private javax.swing.JLabel jLabelBookID;
    private javax.swing.JTextField jLabelDate;
    // End of variables declaration//GEN-END:variables
}
