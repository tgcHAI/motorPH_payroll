package OOP;

import GUI.ViewPortal;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.*;

public class Admin extends SystemIT {

    public Admin(String email, String password) {
        super(email, password, "Admin");
    }

    // Handles JTable click event and populates text fields (Removing Quotes for UI)
    public void populateFieldsFromTable(JTable jTable1, JTextField[] textFields, JLabel emailLabel, JLabel passwordLabel) {
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "No row selected!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        TableModel model = jTable1.getModel();

        // Populate JTextFields, removing quotes where necessary
        textFields[0].setText(getValue(model, row, 0));  // Employee Number
        textFields[1].setText(getValue(model, row, 1));  // Last Name
        textFields[2].setText(getValue(model, row, 2));  // First Name
        textFields[3].setText(getValue(model, row, 3));  // SSS Number
        textFields[4].setText(getValue(model, row, 4));  // PhilHealth Number
        textFields[5].setText(getValue(model, row, 5));  // TIN Number
        textFields[6].setText(getValue(model, row, 6));  // Pag-IBIG Number
        textFields[7].setText(getValue(model, row, 7));  // Birthday
        textFields[8].setText(removeQuotes(getValue(model, row, 8)));  // Address
        textFields[9].setText(getValue(model, row, 9));  // Phone Number
        textFields[10].setText(getValue(model, row, 10)); // Status
        textFields[11].setText(getValue(model, row, 11)); // Position
        textFields[12].setText(removeQuotes(getValue(model, row, 12))); // Immediate Supervisor (Removes Quotes  to reduce error :D)
        textFields[13].setText(removeQuotes(getValue(model, row, 13))); // Basic Salary
        textFields[14].setText(removeQuotes(getValue(model, row, 14))); // Rice Subsidy
        textFields[15].setText(removeQuotes(getValue(model, row, 15))); // Phone Allowance
        textFields[16].setText(removeQuotes(getValue(model, row, 16))); // Clothing Allowance
        textFields[17].setText(removeQuotes(getValue(model, row, 17))); // Monthly Rate
        textFields[18].setText(getValue(model, row, 18)); // Hourly Rate

        // Hidden fields (Email & Password)
        emailLabel.setText(getValue(model, row, 19));
        passwordLabel.setText(getValue(model, row, 20));
    }

    // Handles updating employee details (Restoring Quotes for CSV)
    public void updateEmployeeDetails(JTable jTable1, JTextField[] textFields, JLabel emailLabel, JLabel passwordLabel) {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Extract values from text fields and RESTORE QUOTES where necessary
        String[] newValues = new String[textFields.length];
        for (int i = 0; i < textFields.length; i++) {
            // Fields that need quotes when saving to CSV
            if (i == 8 || i == 12 || i == 13 || i == 14 || i == 15 || i == 16 || i == 17) {
                newValues[i] = addQuotes(textFields[i].getText().trim()); // Restore quotes for CSV
            } else {
                newValues[i] = textFields[i].getText().trim();
            }
        }

        String email = emailLabel.getText().trim();
        String password = passwordLabel.getText().trim();

        // Check for duplicate Employee Number
        if (isEmployeeNumberDuplicate(jTable1, newValues[0], selectedRow)) {
            JOptionPane.showMessageDialog(null, "Employee number already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //  Update JTable with new values
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        for (int i = 0; i < newValues.length; i++) {
            model.setValueAt(newValues[i], selectedRow, i);
        }
        model.setValueAt(email, selectedRow, 19);
        model.setValueAt(password, selectedRow, 20);

        // Save updated data to CSV
        int option = JOptionPane.showConfirmDialog(null, "Confirm update?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            try {
                saveDataToCSV(model, SystemIT.EMPLOYEE_CSV);
                JOptionPane.showMessageDialog(null, "Employee details updated successfully!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error saving to CSV: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //  Saves updated data to CSV
    private void saveDataToCSV(TableModel model, String filePath) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                bw.write(model.getColumnName(j));
                if (j < model.getColumnCount() - 1) {
                    bw.write(",");
                }
            }
            bw.newLine();

            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    bw.write(model.getValueAt(i, j).toString());
                    if (j < model.getColumnCount() - 1) {
                        bw.write(",");
                    }
                }
                bw.newLine();
            }
        }
    }

    //  Loads employee data into JTable (Preserving Format)
    public void viewEmployeeData(ViewPortal frame) {
        DefaultTableModel model = (DefaultTableModel) frame.viewEmployeeTable.getModel();
        model.setRowCount(0); // Clear table before loading new data

        try (BufferedReader br = new BufferedReader(new FileReader(SystemIT.EMPLOYEE_CSV))) {
            String line;
            boolean isFirstRow = true;
            while ((line = br.readLine()) != null) {
                if (isFirstRow) {
                    isFirstRow = false; // Skip header
                    continue;
                }
                String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                model.addRow(data);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading employee data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //  Removes quotes for display in JTextFields
    private String removeQuotes(String text) {
        return text.replaceAll("^\"|\"$", ""); // Remove only the surrounding quotes
    }

    //  Adds quotes when saving to CSV for Address, Immediate Supervisor, Basic Salary, etc.
    private String addQuotes(String text) {
        if (!text.startsWith("\"") || !text.endsWith("\"")) {
            return "\"" + text + "\""; // Add quotes only if not already present
        }
        return text;
    }

    //  Helper method to get value from JTable
    private String getValue(TableModel model, int row, int column) {
        Object value = model.getValueAt(row, column);
        return value != null ? value.toString().trim() : "";
    }

    //  Check for duplicate Employee Number
    private boolean isEmployeeNumberDuplicate(JTable table, String empNum, int selectedRow) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            if (i != selectedRow && model.getValueAt(i, 0).equals(empNum)) {
                return true;
            }
        }
        return false;
    }
}
