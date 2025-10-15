package GUI;

import OOP.SystemIT;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import OOP.SystemIT;
import javax.swing.JOptionPane;

public class LogIn extends javax.swing.JFrame{

    int xMouse, yMouse;
    
    public LogIn() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DesignPanel = new javax.swing.JPanel();
        LogInPanel3 = new javax.swing.JPanel();
        welcomeBack3 = new javax.swing.JLabel();
        line3 = new javax.swing.JPanel();
        LogIn = new javax.swing.JLabel();
        credentials = new javax.swing.JLabel();
        credentials1 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        viewPassword = new javax.swing.JCheckBox();
        LogInButton = new javax.swing.JButton();
        welcomeBack4 = new javax.swing.JLabel();
        credentials2 = new javax.swing.JLabel();
        FrameDrag = new javax.swing.JPanel();
        Dragging = new javax.swing.JLabel();
        exitButton = new javax.swing.JLabel();
        minimizeButton = new javax.swing.JLabel();
        P = new javax.swing.JLabel();
        M = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setForeground(java.awt.Color.white);
        setUndecorated(true);

        DesignPanel.setBackground(new java.awt.Color(0, 0, 0));

        LogInPanel3.setBackground(new java.awt.Color(51, 51, 51));

        welcomeBack3.setBackground(new java.awt.Color(255, 255, 255));
        welcomeBack3.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        welcomeBack3.setForeground(new java.awt.Color(255, 255, 255));
        welcomeBack3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeBack3.setText("Welcome Back!");

        line3.setBackground(new java.awt.Color(255, 255, 255));
        line3.setPreferredSize(new java.awt.Dimension(248, 3));

        javax.swing.GroupLayout line3Layout = new javax.swing.GroupLayout(line3);
        line3.setLayout(line3Layout);
        line3Layout.setHorizontalGroup(
            line3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 248, Short.MAX_VALUE)
        );
        line3Layout.setVerticalGroup(
            line3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        LogIn.setBackground(new java.awt.Color(255, 255, 255));
        LogIn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        LogIn.setForeground(new java.awt.Color(255, 255, 255));
        LogIn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LogIn.setText("Log In");

        credentials.setBackground(new java.awt.Color(255, 255, 255));
        credentials.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        credentials.setForeground(new java.awt.Color(255, 255, 255));
        credentials.setText("Employee Address:");

        credentials1.setBackground(new java.awt.Color(255, 255, 255));
        credentials1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        credentials1.setForeground(new java.awt.Color(255, 255, 255));
        credentials1.setText("Password:");

        emailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailFieldActionPerformed(evt);
            }
        });

        viewPassword.setFont(new java.awt.Font("Calibri", 0, 10)); // NOI18N
        viewPassword.setForeground(new java.awt.Color(255, 255, 255));
        viewPassword.setText("View Password");
        viewPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewPasswordActionPerformed(evt);
            }
        });

        LogInButton.setText("Log In");
        LogInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogInButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LogInPanel3Layout = new javax.swing.GroupLayout(LogInPanel3);
        LogInPanel3.setLayout(LogInPanel3Layout);
        LogInPanel3Layout.setHorizontalGroup(
            LogInPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LogInPanel3Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(welcomeBack3, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(LogInPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(line3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LogInPanel3Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(LogInPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LogInPanel3Layout.createSequentialGroup()
                        .addGroup(LogInPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(LogInPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(LogInPanel3Layout.createSequentialGroup()
                                    .addComponent(credentials, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(128, 128, 128))
                                .addComponent(emailField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LogInPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(LogInPanel3Layout.createSequentialGroup()
                                        .addComponent(credentials1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(128, 128, 128))
                                    .addComponent(passwordField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(viewPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LogInPanel3Layout.createSequentialGroup()
                        .addComponent(LogInButton)
                        .addGap(110, 110, 110))))
        );
        LogInPanel3Layout.setVerticalGroup(
            LogInPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LogInPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(welcomeBack3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(line3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LogIn)
                .addGap(35, 35, 35)
                .addComponent(credentials)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(credentials1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(viewPassword)
                .addGap(29, 29, 29)
                .addComponent(LogInButton)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        welcomeBack4.setBackground(new java.awt.Color(255, 255, 255));
        welcomeBack4.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        welcomeBack4.setForeground(new java.awt.Color(255, 255, 255));
        welcomeBack4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeBack4.setText("MotorPH");

        credentials2.setBackground(new java.awt.Color(255, 255, 255));
        credentials2.setFont(new java.awt.Font("Segoe UI Light", 2, 12)); // NOI18N
        credentials2.setForeground(new java.awt.Color(255, 255, 255));
        credentials2.setText("\"Stay organized, stay stress-free\"");

        javax.swing.GroupLayout DesignPanelLayout = new javax.swing.GroupLayout(DesignPanel);
        DesignPanel.setLayout(DesignPanelLayout);
        DesignPanelLayout.setHorizontalGroup(
            DesignPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DesignPanelLayout.createSequentialGroup()
                .addComponent(LogInPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(DesignPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DesignPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(credentials2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(43, Short.MAX_VALUE))
                    .addGroup(DesignPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(welcomeBack4, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        DesignPanelLayout.setVerticalGroup(
            DesignPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LogInPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DesignPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(credentials2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(welcomeBack4)
                .addGap(12, 12, 12))
        );

        FrameDrag.setBackground(new java.awt.Color(42, 40, 40));

        Dragging.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                DraggingMouseDragged(evt);
            }
        });
        Dragging.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                DraggingMousePressed(evt);
            }
        });

        exitButton.setBackground(new java.awt.Color(255, 255, 255));
        exitButton.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        exitButton.setForeground(new java.awt.Color(255, 255, 255));
        exitButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exitButton.setText("X");
        exitButton.setToolTipText("");
        exitButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitButtonMouseClicked(evt);
            }
        });

        minimizeButton.setBackground(new java.awt.Color(255, 255, 255));
        minimizeButton.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        minimizeButton.setForeground(new java.awt.Color(255, 255, 255));
        minimizeButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        minimizeButton.setText("-");
        minimizeButton.setToolTipText("");
        minimizeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minimizeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeButtonMouseClicked(evt);
            }
        });

        P.setBackground(new java.awt.Color(255, 255, 255));
        P.setFont(new java.awt.Font("Algerian", 0, 18)); // NOI18N
        P.setForeground(new java.awt.Color(255, 255, 255));
        P.setText("P");

        M.setBackground(new java.awt.Color(255, 255, 255));
        M.setFont(new java.awt.Font("Algerian", 0, 18)); // NOI18N
        M.setForeground(new java.awt.Color(255, 255, 255));
        M.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        M.setText("M");

        javax.swing.GroupLayout FrameDragLayout = new javax.swing.GroupLayout(FrameDrag);
        FrameDrag.setLayout(FrameDragLayout);
        FrameDragLayout.setHorizontalGroup(
            FrameDragLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FrameDragLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(M, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(P, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Dragging, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(minimizeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        FrameDragLayout.setVerticalGroup(
            FrameDragLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FrameDragLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FrameDragLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exitButton)
                    .addComponent(minimizeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(Dragging, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FrameDragLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(M)
                .addComponent(P, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(FrameDrag, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DesignPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(FrameDrag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(DesignPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void DraggingMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DraggingMouseDragged
       
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_DraggingMouseDragged

    private void DraggingMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DraggingMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
        
        
        
    }//GEN-LAST:event_DraggingMousePressed

    private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseClicked
               System.exit(0);
    }//GEN-LAST:event_exitButtonMouseClicked

    private void minimizeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeButtonMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_minimizeButtonMouseClicked

    private void viewPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewPasswordActionPerformed

        if (viewPassword.isSelected()) {
           
            passwordField.setEchoChar((char) 0); 
        } else {
            
            passwordField.setEchoChar('*');
        }
    }//GEN-LAST:event_viewPasswordActionPerformed

    private void LogInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogInButtonActionPerformed
          
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both email and password.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SystemIT.validateLogin(email, password, this);
    
    }//GEN-LAST:event_LogInButtonActionPerformed

    private void emailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailFieldActionPerformed

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
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogIn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DesignPanel;
    private javax.swing.JLabel Dragging;
    private javax.swing.JPanel FrameDrag;
    private javax.swing.JLabel LogIn;
    private javax.swing.JButton LogInButton;
    private javax.swing.JPanel LogInPanel3;
    private javax.swing.JLabel M;
    private javax.swing.JLabel P;
    private javax.swing.JLabel credentials;
    private javax.swing.JLabel credentials1;
    private javax.swing.JLabel credentials2;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel exitButton;
    private javax.swing.JPanel line3;
    private javax.swing.JLabel minimizeButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JCheckBox viewPassword;
    private javax.swing.JLabel welcomeBack3;
    private javax.swing.JLabel welcomeBack4;
    // End of variables declaration//GEN-END:variables
}