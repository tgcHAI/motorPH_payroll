package GUI;

import OOP.Employee;
import OOP.SessionTimeoutManager;
import javax.swing.JFrame;
import javax.swing.table.TableModel;

public class ViewPortal extends javax.swing.JFrame {

    int xMouse, yMouse;
    
    public ViewPortal() {
        initComponents();
        
                        //Call timeout
        SessionTimeoutManager.start(this, () -> {
        // This runs on Swing EDT — safe to show dialogs and dispose
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Session timed out due to inactivity.",
                "Session Expired",
                javax.swing.JOptionPane.WARNING_MESSAGE
            );
            new GUI.LogIn().setVisible(true); // Assuming Login is in GUI package
            this.dispose();
        });
    }
        @Override
    public void dispose() {
        SessionTimeoutManager.stop(); // Explicit cleanup
        super.dispose();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel1 = new javax.swing.JPanel();
        welcomeBack = new javax.swing.JLabel();
        line = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        viewEmployeeTable = new javax.swing.JTable();
        generateDetails = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        searchDescription = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        searchField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        LogOutButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        Dragging = new javax.swing.JLabel();
        minimizeButton = new javax.swing.JLabel();
        exitButton = new javax.swing.JLabel();
        M = new javax.swing.JLabel();
        P = new javax.swing.JLabel();

        jScrollPane2.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        welcomeBack.setBackground(new java.awt.Color(255, 255, 255));
        welcomeBack.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        welcomeBack.setForeground(new java.awt.Color(255, 255, 255));
        welcomeBack.setText("Employee Details");

        line.setBackground(new java.awt.Color(255, 255, 255));
        line.setPreferredSize(new java.awt.Dimension(248, 3));

        javax.swing.GroupLayout lineLayout = new javax.swing.GroupLayout(line);
        line.setLayout(lineLayout);
        lineLayout.setHorizontalGroup(
            lineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 248, Short.MAX_VALUE)
        );
        lineLayout.setVerticalGroup(
            lineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        viewEmployeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee #", "Last Name", "First Name", "SSS #", "PhilHealth #", "TIN #", "Pag IBIG #", "Birthday", "Address", "Phone Number", "Status", "Position", "Immediate Supervisor", "Basic Salary", "Rice Subsidy", "Phone Allowance", "Clothing Allowance", "Gross Semi-Monthly Rate", "Hourly Rate", "Email", "Password"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        viewEmployeeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewEmployeeTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(viewEmployeeTable);
        if (viewEmployeeTable.getColumnModel().getColumnCount() > 0) {
            viewEmployeeTable.getColumnModel().getColumn(3).setMinWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(3).setPreferredWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(3).setMaxWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(4).setMinWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(4).setPreferredWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(4).setMaxWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(5).setMinWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(5).setPreferredWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(5).setMaxWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(6).setMinWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(6).setPreferredWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(6).setMaxWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(7).setMinWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(7).setPreferredWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(7).setMaxWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(8).setMinWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(8).setPreferredWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(8).setMaxWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(9).setMinWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(9).setPreferredWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(9).setMaxWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(12).setMinWidth(150);
            viewEmployeeTable.getColumnModel().getColumn(12).setPreferredWidth(150);
            viewEmployeeTable.getColumnModel().getColumn(12).setMaxWidth(150);
            viewEmployeeTable.getColumnModel().getColumn(13).setMinWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(13).setPreferredWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(13).setMaxWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(14).setMinWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(14).setPreferredWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(14).setMaxWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(15).setMinWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(15).setPreferredWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(15).setMaxWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(16).setMinWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(16).setPreferredWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(16).setMaxWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(17).setMinWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(17).setPreferredWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(17).setMaxWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(18).setMinWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(18).setPreferredWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(18).setMaxWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(19).setMinWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(19).setPreferredWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(19).setMaxWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(20).setMinWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(20).setPreferredWidth(0);
            viewEmployeeTable.getColumnModel().getColumn(20).setMaxWidth(0);
        }

        generateDetails.setBackground(new java.awt.Color(102, 102, 102));
        generateDetails.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        generateDetails.setForeground(new java.awt.Color(255, 255, 255));
        generateDetails.setText("Generate Details");
        generateDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateDetailsActionPerformed(evt);
            }
        });

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        searchDescription.setFont(new java.awt.Font("Segoe UI", 3, 10)); // NOI18N
        searchDescription.setForeground(new java.awt.Color(255, 255, 255));
        searchDescription.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        searchDescription.setText("Enter Employee Number/Last name/First name");

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(line, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(welcomeBack, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(generateDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(searchField))
                                    .addComponent(searchDescription))))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(welcomeBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(line, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(generateDetails))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchButton)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refreshButton))
                .addGap(21, 21, 21))
        );

        jPanel2.setBackground(new java.awt.Color(42, 40, 40));

        LogOutButton.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        LogOutButton.setText("Log Out");
        LogOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LogOutButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LogOutButton)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(42, 40, 40));
        jPanel3.setPreferredSize(new java.awt.Dimension(0, 34));

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

        M.setBackground(new java.awt.Color(255, 255, 255));
        M.setFont(new java.awt.Font("Algerian", 0, 18)); // NOI18N
        M.setForeground(new java.awt.Color(255, 255, 255));
        M.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        M.setText("M");

        P.setBackground(new java.awt.Color(255, 255, 255));
        P.setFont(new java.awt.Font("Algerian", 0, 18)); // NOI18N
        P.setForeground(new java.awt.Color(255, 255, 255));
        P.setText("P");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(M, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(P, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(minimizeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(Dragging, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 64, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(M)
                        .addComponent(P, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(exitButton)
                        .addComponent(minimizeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Dragging, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(2, 2, 2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void minimizeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeButtonMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_minimizeButtonMouseClicked

    private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseClicked
        System.exit(0);
    }//GEN-LAST:event_exitButtonMouseClicked

    private void LogOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOutButtonActionPerformed

        LogIn frame = new LogIn();
        frame.setVisible(true);
        dispose();

    }//GEN-LAST:event_LogOutButtonActionPerformed

    private void viewEmployeeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewEmployeeTableMouseClicked
                                
            int row = viewEmployeeTable.getSelectedRow();
            TableModel model = viewEmployeeTable.getModel();

            // Create an Employee object using the selected row
            Employee employee = new Employee(model, row);

            ViewInformation updvw = new ViewInformation();
            updvw.setVisible(true);
           
            
            // Populate update view (updvw) with retrieved values
            updvw.setVisible(true);
            updvw.pack();
            updvw.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            updvw.employeeNumber.setText(employee.getEmployeeNumber());
            updvw.lastName.setText(employee.getLastName());
            updvw.firstName.setText(employee.getFirstName());

            updvw.sss.setText(employee.getSssNumber());
            updvw.philHealth.setText(employee.getPhilHealthNumber());
            updvw.tin.setText(employee.getTinNumber());
            updvw.pagIBIG.setText(employee.getPagIbigNumber());

            updvw.birthday.setText(employee.getBirthday());
            updvw.phoneNumber.setText(employee.getContactNumber());
            updvw.address.setText(employee.getAddress());

            updvw.status.setText(employee.getStatus());
            updvw.position.setText(getValue(model, row, 11)); // Position
            updvw.immediateSupervisor.setText(getValue(model, row, 12)); // Supervisor

            updvw.basicSalary.setText(getValue(model, row, 13));
            updvw.riceSub.setText(getValue(model, row, 14));
            updvw.phoneAllowance.setText(getValue(model, row, 15));
            updvw.clothingAllowance.setText(getValue(model, row, 16));
            
            updvw.monthlyRate.setText(getValue(model, row, 17)); // Gross Rate
            updvw.hourlyRate.setText(String.valueOf((int) employee.getHourlyRate()));
            
            
    }//GEN-LAST:event_viewEmployeeTableMouseClicked

        private String getValue(TableModel model, int row, int column) {
            // Retrieve value from the specified row and column in the table model
            Object value = model.getValueAt(row, column);
            return value != null ? value.toString() : ""; // Return value as string, handling null case
        }
    
    private void generateDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateDetailsActionPerformed
 
    }//GEN-LAST:event_generateDetailsActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed

    }//GEN-LAST:event_searchButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ViewPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPortal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewPortal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Dragging;
    private javax.swing.JButton LogOutButton;
    private javax.swing.JLabel M;
    private javax.swing.JLabel P;
    private javax.swing.JLabel exitButton;
    private javax.swing.JButton generateDetails;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JPanel line;
    private javax.swing.JLabel minimizeButton;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel searchDescription;
    private javax.swing.JTextField searchField;
    public javax.swing.JTable viewEmployeeTable;
    private javax.swing.JLabel welcomeBack;
    // End of variables declaration//GEN-END:variables
}
