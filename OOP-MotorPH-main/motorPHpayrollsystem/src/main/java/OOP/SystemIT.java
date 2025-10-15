package OOP;

import GUI.*;
import javax.swing.*;
import java.io.*;

public class SystemIT extends User {
    public static final String EMPLOYEE_CSV = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "CSV" + File.separator + "EmpData.csv";
    private static final String ADMIN_CSV = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "CSV" + File.separator + "AdminLogin.csv";
    public static final String HR_CSV = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "CSV" + File.separator + "HRLogin.csv";
    private static final String FINANCE_CSV = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "CSV" + File.separator + "FinanceLogin.csv";
    public static final String ATTENDANCE_CSV = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "CSV" + File.separator + "AttendanceRecords";
    public static final String LEAVES_CSV = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "CSV" + File.separator + "Leaves.csv";
    
    public SystemIT(String email, String password, String role) {
        super(email, password, role);
    }

    public static void validateLogin(String email, String password, JFrame loginFrame) {
        if (checkCredentials(ADMIN_CSV, email, password)) {
            redirectToPortal(new Admin(email, password), new AdminPortal(), "Admin", loginFrame);
        } else if (checkCredentials(HR_CSV, email, password)) {
            redirectToPortal(new HR(email, password), new HRPortal(), "HR", loginFrame);
        } else if (checkCredentials(FINANCE_CSV, email, password)) {
            redirectToPortal(new Finance(email, password), new FinancePortal(), "Finance", loginFrame);
        } else {
            Employee employee = getEmployeeDetails(email, password);
            if (employee != null) {
                showEmployeePortal(employee, loginFrame);
            } else {
                JOptionPane.showMessageDialog(null, "Login Failed! Incorrect email or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //BCrypt hashing
    private static boolean checkCredentials(String filePath, String email, String password) {
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        boolean isFirstLine = true;
        while ((line = br.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }
            String[] values = line.split(",");
            if (values.length > 1 && values[0].trim().equals(email)) {
                String storedPassword = values[1].trim();
                // Use HashUtil to verify the password
                if (OOP.HashUtil.checkPassword(password, storedPassword)) {
                    return true;
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return false;
}


    private static Employee getEmployeeDetails(String email, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(EMPLOYEE_CSV))) {
            String line;
            boolean isFirstLine = true;
            int lineNumber = 0;  // Track line numbers for debugging

            while ((line = br.readLine()) != null) {
                lineNumber++;

                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                // Trim quotes and whitespace
                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].replaceAll("^\"|\"$", "").trim();
                }

                // Ensure we have at least 21 columns before accessing indices
                if (values.length >= 21 && values[19].equals(email)) {
                    String storedPassword = values[20].trim();

                    // Check hashed OR plaintext password (for backward compatibility)
                    if (HashUtil.checkPassword(password, storedPassword) || password.equals(storedPassword)) {
                        try {
                            return new Employee(
                                values[19], values[20],  // Email & Password
                                values[0], values[1], values[2],  // Employee Number, Last Name, First Name
                                values[7], values[10],   // Birthday, Status
                                values[9], values[8],    // Phone Number, Address
                                parseDoubleSafe(values[13]),  // Basic Salary
                                parseDoubleSafe(values[18]),  // Hourly Rate
                                parseDoubleSafe(values[14]),  // Rice Subsidy
                                parseDoubleSafe(values[15]),  // Phone Allowance
                                parseDoubleSafe(values[16]),  // Clothing Allowance
                                values[3], values[6], values[4], values[5] // SSS, Pag-IBIG, PhilHealth, TIN Number
                            );
                        } catch (NumberFormatException e) {
                            System.err.println("Error parsing numeric values at line " + lineNumber + ": " + e.getMessage());
                        }
                    }
                }

            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return null;
    }

        private static double parseDoubleSafe(String value) {
        if (value == null || value.isEmpty()) return 0.0;  // Default to 0 if empty
        return Double.parseDouble(value.replace(",", "")); // Remove commas and parse
    }

    private static void redirectToPortal(User user, JFrame portal, String role, JFrame loginFrame) {
        JOptionPane.showMessageDialog(null, "Successfully Logged In as " + role + "!");
        portal.setVisible(true);
        loginFrame.dispose();
    }

    private static void showEmployeePortal(Employee employee, JFrame loginFrame) {
        EmployeePortal frame = new EmployeePortal();
        frame.fillEmployeeDetails(employee);
        redirectToPortal(employee, frame, "Employee", loginFrame);
    }
}

