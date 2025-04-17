/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package myproject.budget_app;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import myproject.Connect_DB.Operations;
/**
 *
 * @author jomsa
 */
public class Search extends javax.swing.JDialog {

    /**
     * Creates new form Search
     */
    public Search(java.awt.Frame parent, boolean modal, int uid) {
        super(parent, modal);
        setResizable(false);
        initComponents();
        Operations op = new Operations();
        this.uid = uid;
        op.loadAllData(res_tbl, uid);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        search_txt = new javax.swing.JTextField();
        search_bttn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        res_tbl = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        search_txt.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        getContentPane().add(search_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 260, 30));

        search_bttn.setIcon(new javax.swing.ImageIcon("D:\\[BSCS]\\Projects\\Info_Mgmt\\Budget_App\\budget_app\\image_assets\\search_bttn.png")); // NOI18N
        search_bttn.setBorderPainted(false);
        search_bttn.setOpaque(false);
        search_bttn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                search_bttnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                search_bttnMouseExited(evt);
            }
        });
        search_bttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_bttnActionPerformed(evt);
            }
        });
        getContentPane().add(search_bttn, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 30, 30));

        jScrollPane1.setOpaque(false);

        res_tbl.setFont(new java.awt.Font("Poppins", 0, 8)); // NOI18N
        res_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Transact_ID", "Type", "Description", "Amount", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        res_tbl.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        res_tbl.setAutoscrolls(false);
        res_tbl.setEnabled(false);
        jScrollPane1.setViewportView(res_tbl);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 73, 404, 322));

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel1.setText("Results");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 50, 16));

        bg.setIcon(new javax.swing.ImageIcon("D:\\[BSCS]\\Projects\\Info_Mgmt\\Budget_App\\budget_app\\image_assets\\bg9.jpg")); // NOI18N
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void search_bttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_bttnActionPerformed
        // TODO add your handling code here:
        Operations op = new Operations();
        String keyword = getKeyword();
        op.searchTrans(res_tbl, uid, keyword, "");
    }//GEN-LAST:event_search_bttnActionPerformed

    private void search_bttnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_bttnMouseEntered
        // TODO add your handling code here:
        search_bttn.setIcon(new ImageIcon("D:\\[BSCS]\\Projects\\Info_Mgmt\\Budget_App\\budget_app\\image_assets\\search_bttn_clicked.png"));
    }//GEN-LAST:event_search_bttnMouseEntered

    private void search_bttnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_bttnMouseExited
        // TODO add your handling code here:
        search_bttn.setIcon(new ImageIcon("D:\\[BSCS]\\Projects\\Info_Mgmt\\Budget_App\\budget_app\\image_assets\\search_bttn.png"));
    }//GEN-LAST:event_search_bttnMouseExited

    private String getKeyword(){
        String keyword = search_txt.getText().trim();
        if (keyword.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please enter a keyword first.");
            return null;
        }
        return keyword;
    }
    /**
     * @param args the command line arguments
     */
    int uid;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable res_tbl;
    private javax.swing.JButton search_bttn;
    private javax.swing.JTextField search_txt;
    // End of variables declaration//GEN-END:variables
}
