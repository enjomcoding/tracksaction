/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package myproject.budget_app;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import myproject.Connect_DB.Menu_Assets;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;
import  myproject.Connect_DB.Operations;

/**
 *
 * @author jomsa
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main(String email) {
        setResizable(false);
        initComponents();
        this.email = email;
        Menu_Assets assets = new Menu_Assets();
        Operations op = new Operations();
        int uid = assets.getUID(email);
        int currBal = op.getCurrBal(uid);

        // Initialize user information
        initializeUserInfo(uid, assets, currBal);

        // Load recent transactions
        loadRecentTransactions(uid, op);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title_r1 = new javax.swing.JLabel();
        amnt_r1 = new javax.swing.JLabel();
        date_r1 = new javax.swing.JLabel();
        date_r2 = new javax.swing.JLabel();
        amnt_r2 = new javax.swing.JLabel();
        title_r2 = new javax.swing.JLabel();
        bal_amnt = new javax.swing.JLabel();
        recent_lbl = new javax.swing.JLabel();
        profile_buttn = new javax.swing.JButton();
        search_bttn = new javax.swing.JButton();
        bal_tab = new javax.swing.JLabel();
        name_lbl = new javax.swing.JLabel();
        rec1 = new javax.swing.JLabel();
        rec2 = new javax.swing.JLabel();
        hello1 = new javax.swing.JLabel();
        view_bttn = new javax.swing.JButton();
        arrow = new javax.swing.JLabel();
        add_bttn = new javax.swing.JButton();
        bg = new javax.swing.JLabel();
        name_lbl2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title_r1.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        title_r1.setToolTipText("");
        getContentPane().add(title_r1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 370, 150, -1));

        amnt_r1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        amnt_r1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(amnt_r1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 370, 70, -1));

        date_r1.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        getContentPane().add(date_r1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, -1, -1));

        date_r2.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        getContentPane().add(date_r2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, -1, -1));

        amnt_r2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        amnt_r2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(amnt_r2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 460, 70, -1));

        title_r2.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        title_r2.setToolTipText("");
        getContentPane().add(title_r2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 460, 150, -1));

        bal_amnt.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        bal_amnt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        bal_amnt.setText("0.00");
        getContentPane().add(bal_amnt, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 270, 50));

        recent_lbl.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        recent_lbl.setText("Recent Transactions");
        getContentPane().add(recent_lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 160, 40));

        profile_buttn.setIcon(new javax.swing.ImageIcon("D:\\[BSCS]\\Projects\\Info_Mgmt\\Budget_App\\budget_app\\image_assets\\profile_icon.png")); // NOI18N
        profile_buttn.setBorderPainted(false);
        profile_buttn.setOpaque(false);
        profile_buttn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                profile_buttnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                profile_buttnMouseExited(evt);
            }
        });
        profile_buttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profile_buttnActionPerformed(evt);
            }
        });
        getContentPane().add(profile_buttn, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 50, 50));

        search_bttn.setIcon(new javax.swing.ImageIcon("D:\\[BSCS]\\Projects\\Info_Mgmt\\Budget_App\\budget_app\\image_assets\\search_menu.png")); // NOI18N
        search_bttn.setBorderPainted(false);
        search_bttn.setFocusPainted(false);
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
        getContentPane().add(search_bttn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 370, 40));

        bal_tab.setIcon(new javax.swing.ImageIcon("D:\\[BSCS]\\Projects\\Info_Mgmt\\Budget_App\\budget_app\\image_assets\\bal_tab.png")); // NOI18N
        bal_tab.setText("jLabel1");
        getContentPane().add(bal_tab, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 340, -1));

        name_lbl.setFont(new java.awt.Font("Poppins Black", 1, 18)); // NOI18N
        getContentPane().add(name_lbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 120, 40));

        rec1.setIcon(new javax.swing.ImageIcon("D:\\[BSCS]\\Projects\\Info_Mgmt\\Budget_App\\budget_app\\image_assets\\recent_tab.png")); // NOI18N
        rec1.setText("jLabel1");
        getContentPane().add(rec1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 360, -1));

        rec2.setIcon(new javax.swing.ImageIcon("D:\\[BSCS]\\Projects\\Info_Mgmt\\Budget_App\\budget_app\\image_assets\\recent_tab.png")); // NOI18N
        rec2.setText("jLabel1");
        getContentPane().add(rec2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 360, -1));

        hello1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        hello1.setText("Hello,");
        getContentPane().add(hello1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 30));

        view_bttn.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        view_bttn.setText("See All");
        view_bttn.setBorderPainted(false);
        view_bttn.setOpaque(false);
        view_bttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_bttnActionPerformed(evt);
            }
        });
        getContentPane().add(view_bttn, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 80, 20));

        arrow.setIcon(new javax.swing.ImageIcon("D:\\[BSCS]\\Projects\\Info_Mgmt\\Budget_App\\budget_app\\image_assets\\see_all_arrow.png")); // NOI18N
        arrow.setToolTipText("");
        getContentPane().add(arrow, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 310, -1, -1));

        add_bttn.setIcon(new javax.swing.ImageIcon("D:\\[BSCS]\\Projects\\Info_Mgmt\\Budget_App\\budget_app\\image_assets\\add.png")); // NOI18N
        add_bttn.setBorderPainted(false);
        add_bttn.setOpaque(false);
        add_bttn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                add_bttnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                add_bttnMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                add_bttnMouseReleased(evt);
            }
        });
        add_bttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_bttnActionPerformed(evt);
            }
        });
        getContentPane().add(add_bttn, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 40, 40));

        bg.setIcon(new javax.swing.ImageIcon("D:\\[BSCS]\\Projects\\Info_Mgmt\\Budget_App\\budget_app\\image_assets\\bg9.jpg")); // NOI18N
        bg.setToolTipText("");
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(-140, -140, 530, 650));

        name_lbl2.setFont(new java.awt.Font("Poppins Black", 1, 18)); // NOI18N
        name_lbl2.setText("jLabel2");
        getContentPane().add(name_lbl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 120, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void search_bttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_bttnActionPerformed
        // TODO add your handling code here:
        Menu_Assets assets = new Menu_Assets();
        int uid = assets.getUID(email);
        Search search = new Search(this, true, uid);
        search.setVisible(true);
    }//GEN-LAST:event_search_bttnActionPerformed

    private void view_bttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_bttnActionPerformed
        // TODO add your handling code here:
        Menu_Assets assets = new Menu_Assets();
        int uid = assets.getUID(email);
        View see = new View(this, true, uid);
        see.setVisible(true);
    }//GEN-LAST:event_view_bttnActionPerformed

    private void add_bttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_bttnActionPerformed
        // TODO add your handling code here:
        Menu_Assets assets = new Menu_Assets();
        int uid = assets.getUID(email);
        Add add = new Add(this, true, uid);
        add.setVisible(true);
    }//GEN-LAST:event_add_bttnActionPerformed

    private void profile_buttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profile_buttnActionPerformed
        // TODO add your handling code here:
        Menu_Assets assets = new Menu_Assets();
        int uid = assets.getUID(email);
        Profile prof = new Profile(this, true, uid, email);
        this.dispose();
        prof.setVisible(true);
    }//GEN-LAST:event_profile_buttnActionPerformed

    private void search_bttnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_bttnMouseEntered
        // TODO add your handling code here:
        search_bttn.setIcon(new ImageIcon("D:\\[BSCS]\\Projects\\Info_Mgmt\\Budget_App\\budget_app\\image_assets\\search_menu_clicked.png"));
    }//GEN-LAST:event_search_bttnMouseEntered

    private void search_bttnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_bttnMouseExited
        // TODO add your handling code here:
        search_bttn.setIcon(new ImageIcon("D:\\[BSCS]\\Projects\\Info_Mgmt\\Budget_App\\budget_app\\image_assets\\search_menu.png"));
    }//GEN-LAST:event_search_bttnMouseExited

    private void add_bttnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_bttnMouseEntered
        // TODO add your handling code here:
        add_bttn.setIcon(new ImageIcon("D:\\[BSCS]\\Projects\\Info_Mgmt\\Budget_App\\budget_app\\image_assets\\add_clicked.png"));
    }//GEN-LAST:event_add_bttnMouseEntered

    private void add_bttnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_bttnMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_add_bttnMouseReleased

    private void add_bttnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_bttnMouseExited
        // TODO add your handling code here:
        add_bttn.setIcon(new ImageIcon("D:\\[BSCS]\\Projects\\Info_Mgmt\\Budget_App\\budget_app\\image_assets\\add.png"));
    }//GEN-LAST:event_add_bttnMouseExited

    private void profile_buttnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profile_buttnMouseEntered
        // TODO add your handling code here:
        profile_buttn.setIcon(new ImageIcon("D:\\[BSCS]\\Projects\\Info_Mgmt\\Budget_App\\budget_app\\image_assets\\profile_clicked.png"));
    }//GEN-LAST:event_profile_buttnMouseEntered

    private void profile_buttnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profile_buttnMouseExited
        // TODO add your handling code here:
        profile_buttn.setIcon(new ImageIcon("D:\\[BSCS]\\Projects\\Info_Mgmt\\Budget_App\\budget_app\\image_assets\\profile_icon.png"));
    }//GEN-LAST:event_profile_buttnMouseExited
    
    private void initializeUserInfo(int uid, Menu_Assets assets, int currBal) {
        if (uid != -1) {
            String name = assets.getName(uid);
            if (name != null && !name.isEmpty()) {
                name_lbl.setText(name);
                bal_amnt.setText(String.valueOf(currBal));
                // Start the timer to update balance dynamically
                startBalanceUpdater(uid);
            } else {
                name_lbl.setText("Name not found");
            }
        } else {
            name_lbl.setText("User  not found");
        }
    }

    private void loadRecentTransactions(int uid, Operations op) {
        Object[] recData = op.getRecentTrans(uid);
        
        // Load the most recent transaction
        if (recData.length > 0) {
            loadTransactionData((Object[]) recData[0], date_r1, amnt_r1, title_r1);
        }

        // Load the second most recent transaction
        if (recData.length > 1) {
            loadTransactionData((Object[]) recData[1], date_r2, amnt_r2, title_r2);
        }
    }

    private void loadTransactionData(Object[] transactionData, JLabel dateLabel, JLabel amountLabel, JLabel titleLabel) {
        // Format the date
        String rawDate = (String) transactionData[0];
        String formattedDate = formatDate(rawDate);
        dateLabel.setText(formattedDate);

        // Format the amount
        BigDecimal amount = (BigDecimal) transactionData[1];
        String formattedAmount = formatCurrency(amount);
        amountLabel.setText(formattedAmount);

        // Set the title
        titleLabel.setText((String) transactionData[2]);
    }

    private String formatDate(String rawDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM/dd");
        try {
            Date date = inputFormat.parse(rawDate);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "--"; // Set a default or error message
        }
    }

    private String formatCurrency(BigDecimal amount) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "PH"));
        currencyFormat.setMinimumFractionDigits(2);
        currencyFormat.setMaximumFractionDigits(2);
        return currencyFormat.format(amount);
    }

    
    private void startBalanceUpdater(int uid) {
        int delay = 1200; // Delay in milliseconds (5 seconds)
        Timer timer = new Timer(delay, e -> {
            Operations op = new Operations();
            int currBal = op.getCurrBal(uid);
            bal_amnt.setText(String.valueOf(currBal));
        });

        timer.start(); // Start the timer
    }
    
    
    /**
     * @param args the command line arguments
     */
    private BigDecimal currBal;
    String email;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_bttn;
    private javax.swing.JLabel amnt_r1;
    private javax.swing.JLabel amnt_r2;
    private javax.swing.JLabel arrow;
    private javax.swing.JLabel bal_amnt;
    private javax.swing.JLabel bal_tab;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel date_r1;
    private javax.swing.JLabel date_r2;
    private javax.swing.JLabel hello1;
    private javax.swing.JLabel name_lbl;
    private javax.swing.JLabel name_lbl2;
    private javax.swing.JButton profile_buttn;
    private javax.swing.JLabel rec1;
    private javax.swing.JLabel rec2;
    private javax.swing.JLabel recent_lbl;
    private javax.swing.JButton search_bttn;
    private javax.swing.JLabel title_r1;
    private javax.swing.JLabel title_r2;
    private javax.swing.JButton view_bttn;
    // End of variables declaration//GEN-END:variables
}
