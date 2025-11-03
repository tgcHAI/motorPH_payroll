package OOP;

import GUI.*;
import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Arrays;
import java.util.UUID;
import FUNCTIONS.GoogleAuth.QRCodeGenerator;
import FUNCTIONS.GoogleAuth.SecretKeyGenerator;
import FUNCTIONS.GoogleAuth.TOTPValidator;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SystemIT extends User {
    public static final String EMPLOYEE_CSV = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "CSV" + File.separator + "EmpData.csv";
    private static final String ADMIN_CSV = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "CSV" + File.separator + "AdminLogin.csv";
    public static final String HR_CSV = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "CSV" + File.separator + "HRLogin.csv";
    private static final String FINANCE_CSV = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "CSV" + File.separator + "FinanceLogin.csv";
    public static final String ATTENDANCE_CSV = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "CSV" + File.separator + "AttendanceRecords";
    public static final String LEAVES_CSV = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "CSV" + File.separator + "Leaves.csv";
    
    
    private static final int CSV_EMAIL_COL_INDEX = 19;
    private static final int CSV_PASSWORD_COL_INDEX = 20;
    private static final int CSV_TOTP_SECRET_COL_INDEX = 21; 
    
    public SystemIT(String email, String password, String role) {
        super(email, password, role);
    }
    
    public static void main(String args[]){
        // Test enrollment
        JFrame dummyFrame = new JFrame();
        String secret = enrollMFA("user2@motorph.com", EMPLOYEE_CSV, dummyFrame);
        System.out.println("Enrolled with secret: " + secret);
    }

    // === NEW: SAFE TOTP SECRET STORAGE ===
    public static void saveTotpSecret(String email, String secret) {
        try {
            Path csvPath = Paths.get(EMPLOYEE_CSV);
            List<String> lines = Files.readAllLines(csvPath, StandardCharsets.UTF_8);
            boolean headerSkipped = false;
            boolean found = false;

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                String[] parts = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                for (int j = 0; j < parts.length; j++) {
                    parts[j] = parts[j].replaceAll("^\"|\"$", "").trim();
                }
                
                if (parts.length <= CSV_TOTP_SECRET_COL_INDEX) {
                    parts = Arrays.copyOf(parts, CSV_TOTP_SECRET_COL_INDEX + 1);
                    for (int k = 0; k < parts.length; k++) {
                        if (parts[k] == null) parts[k] = "";
                    }
                }

                if (parts.length > CSV_EMAIL_COL_INDEX) {
                    String csvEmail = parts[CSV_EMAIL_COL_INDEX];
                    if (email.equalsIgnoreCase(csvEmail)) {
                        parts[CSV_TOTP_SECRET_COL_INDEX] = "\"" + secret + "\"";
                        lines.set(i, String.join(",", parts));
                        found = true;
                        break;
                    }
                }
            }

            if (found) {
                Files.write(csvPath, lines, StandardCharsets.UTF_8);
                System.out.println("TOTP secret saved for: " + email);
            } else {
                System.err.println("User not found: " + email);
            }
        } catch (IOException e) {
            System.err.println("CSV update failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // === NEW: MFA ENROLLMENT FLOW ===
    private static String enrollMFA(String email, String csvPath, JFrame loginFrame) {
        String secret = SecretKeyGenerator.generateSecretKey();
        saveTotpSecret(email, secret);
        
        String issuer = "MotorPH";
        String otpUrl = String.format(
            "otpauth://totp/%s:%s?secret=%s&issuer=%s",
            issuer, email, secret, issuer
        );
        
        try {
            String qrPath = System.getProperty("user.dir") + File.separator + "mfa_setup.png";
            QRCodeGenerator.generateQRCode(otpUrl, qrPath, 300, 300);
            showMFAEnrollmentDialog(email, qrPath, secret, loginFrame);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to generate QR code: " + e.getMessage(), 
                                        "MFA Setup Error", JOptionPane.ERROR_MESSAGE);
        }
        return secret;
    }

    private static void showMFAEnrollmentDialog(String email, String qrPath, String secret, JFrame loginFrame) {
        JFrame frame = new JFrame("Set Up Authenticator App");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        try {
            BufferedImage img = ImageIO.read(new File(qrPath));
            JLabel qrLabel = new JLabel(new ImageIcon(img));
            frame.add(qrLabel, BorderLayout.CENTER);
        } catch (IOException e) {
            frame.add(new JLabel("Failed to load QR code"), BorderLayout.CENTER);
        }
        
        JTextArea instructions = new JTextArea(
            "1. Install Google Authenticator (or similar app)\n" +
            "2. Tap '+' → 'Scan QR code'\n" +
            "3. Enter the 6-digit code below to verify setup\n\n" +
            "Can't scan? Enter this key manually:\n" + secret
        );
        instructions.setEditable(false);
        instructions.setWrapStyleWord(true);
        instructions.setLineWrap(true);
        frame.add(instructions, BorderLayout.SOUTH);
        
        JTextField otpField = new JTextField(10);
        otpField.setFont(new Font("Monospaced", Font.BOLD, 16));
        frame.add(otpField, BorderLayout.NORTH);
        
        JButton verifyBtn = new JButton("Verify & Complete Setup");
        verifyBtn.addActionListener(e -> {
            try {
                if (TOTPValidator.validateOTP(secret, otpField.getText().trim())) {
                    JOptionPane.showMessageDialog(frame, "MFA setup successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    completeLogin(email, loginFrame);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid code. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (GeneralSecurityException ex) {
                Logger.getLogger(SystemIT.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        frame.add(verifyBtn, BorderLayout.EAST);
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // === NEW: MFA CHALLENGE FLOW ===
    private static void showMFAChallengeDialog(String email, String secret, JFrame loginFrame) {
        JDialog dialog = new JDialog((Frame) null, "Two-Factor Authentication", true);
        dialog.setLayout(new FlowLayout());
        
        dialog.add(new JLabel("Enter 6-digit code from your authenticator app:"));
        JTextField otpField = new JTextField(10);
        otpField.setFont(new Font("Monospaced", Font.BOLD, 16));
        dialog.add(otpField);
        
        JButton verifyBtn = new JButton("Verify");
        verifyBtn.addActionListener(e -> {
            try {
                if (TOTPValidator.validateOTP(secret, otpField.getText().trim())) {
                    dialog.dispose();
                    completeLogin(email, loginFrame);
                } else {
                    JOptionPane.showMessageDialog(dialog, "Invalid OTP. Please try again.",
                            "Authentication Failed", JOptionPane.ERROR_MESSAGE);
                }
            } catch (GeneralSecurityException ex) {
                Logger.getLogger(SystemIT.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        dialog.add(verifyBtn);
        
        dialog.getRootPane().setDefaultButton(verifyBtn);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private static String getUserRole(String email) {
        // Check Admin
        try (BufferedReader br = new BufferedReader(new FileReader(ADMIN_CSV))) {
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (first) { first = false; continue; }
                String[] parts = line.split(",", -1);
                if (parts.length > 0 && email.equalsIgnoreCase(parts[0].trim())) 
                    return "Admin";
            }
        } catch (IOException e) { /* ignore */ }

        // Check HR
        try (BufferedReader br = new BufferedReader(new FileReader(HR_CSV))) {
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (first) { first = false; continue; }
                String[] parts = line.split(",", -1);
                if (parts.length > 0 && email.equalsIgnoreCase(parts[0].trim())) 
                    return "HR";
            }
        } catch (IOException e) { /* ignore */ }

        // Check Finance
        try (BufferedReader br = new BufferedReader(new FileReader(FINANCE_CSV))) {
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (first) { first = false; continue; }
                String[] parts = line.split(",", -1);
                if (parts.length > 0 && email.equalsIgnoreCase(parts[0].trim())) 
                    return "Finance";
            }
        } catch (IOException e) { /* ignore */ }

        // Check Employee (in EmpData.csv)
        try (BufferedReader br = new BufferedReader(new FileReader(EMPLOYEE_CSV))) {
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (first) { first = false; continue; }
                String[] parts = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (parts.length > CSV_EMAIL_COL_INDEX && 
                    email.equalsIgnoreCase(parts[CSV_EMAIL_COL_INDEX].replaceAll("^\"|\"$", "").trim())) 
                    return "Employee";
            }
        } catch (IOException e) { /* ignore */ }

        return null; // Unknown role
    }
    
    private static void completeLogin(String email, JFrame loginFrame) {
        String role = getUserRole(email);
        if (role == null) {
            JOptionPane.showMessageDialog(null, "User role not found. Contact admin.", "Error", JOptionPane.ERROR_MESSAGE);
            loginFrame.setVisible(true); // Return to login
            return;
        }

        switch (role) {
            case "Admin":
                new AdminPortal().setVisible(true);
                break;
            case "HR":
                new HRPortal().setVisible(true);
                break;
            case "Finance":
                new FinancePortal().setVisible(true);
                break;
            case "Employee":
                Employee emp = getEmployeeByEmail(email);
                if (emp != null) {
                    EmployeePortal portal = new EmployeePortal();
                    portal.fillEmployeeDetails(emp);
                    portal.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Employee data not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    loginFrame.setVisible(true);
                    return;
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Unknown role: " + role, "Error", JOptionPane.ERROR_MESSAGE);
                loginFrame.setVisible(true);
                return;
        }
        
        loginFrame.dispose(); // Close login window
    }

    private static Employee getEmployeeByEmail(String email) {
        try (BufferedReader br = new BufferedReader(new FileReader(EMPLOYEE_CSV))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].replaceAll("^\"|\"$", "").trim();
                }
                if (values.length > CSV_EMAIL_COL_INDEX && values[CSV_EMAIL_COL_INDEX].equals(email)) {
                    return new Employee(
                        values[CSV_EMAIL_COL_INDEX], values[CSV_PASSWORD_COL_INDEX],
                        values[0], values[1], values[2], values[7], values[10],
                        values[9], values[8], parseDoubleSafe(values[13]),
                        parseDoubleSafe(values[18]), parseDoubleSafe(values[14]),
                        parseDoubleSafe(values[15]), parseDoubleSafe(values[16]),
                        values[3], values[6], values[4], values[5]
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // === UPDATED: VALIDATE LOGIN WITH MFA ===
    public static void validateLogin(String email, String password, JFrame loginFrame) {
        System.out.println("Login attempt: " + email);
        
        String role = getRoleByEmailAndPassword(email, password);
        if (role == null) {
            JOptionPane.showMessageDialog(loginFrame, "Invalid email or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }
        System.out.println("Role detected: " + role);

        String secret = getTotpSecret(email);
        System.out.println("TOTP secret: " + (secret.isEmpty() ? "NOT ENROLLED" : "ENROLLED"));
        
        if (secret.isEmpty()) {
            enrollMFA(email, EMPLOYEE_CSV, loginFrame); // ✅ Pass loginFrame here
        } else {
            showMFAChallengeDialog(email, secret, loginFrame);
        }
    }

    private static String getRoleByEmailAndPassword(String email, String password) {
        if (checkCredentials(ADMIN_CSV, email, password)) return "Admin";
        if (checkCredentials(HR_CSV, email, password)) return "HR";
        if (checkCredentials(FINANCE_CSV, email, password)) return "Finance";
        if (getEmployeeDetails(email, password) != null) return "Employee";
        return null;
    }

    private static String getTotpSecret(String email) {
        try (BufferedReader br = new BufferedReader(new FileReader(EMPLOYEE_CSV))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] parts = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].replaceAll("^\"|\"$", "").trim();
                }
                if (parts.length > CSV_EMAIL_COL_INDEX && 
                    email.equalsIgnoreCase(parts[CSV_EMAIL_COL_INDEX])) {
                    return (parts.length > CSV_TOTP_SECRET_COL_INDEX) ? 
                           parts[CSV_TOTP_SECRET_COL_INDEX] : "";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    // === PRESERVED EXISTING METHODS (MINOR TWEAKS) ===
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
                if (values.length > 1 && values[0].trim().equals(email) && values[1].trim().equals(password)) {
                    return true;
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
            int lineNumber = 0;  

            while ((line = br.readLine()) != null) {
                lineNumber++;

                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].replaceAll("^\"|\"$", "").trim();
                }

                if (values.length >= 21 && values[19].equals(email) && values[20].equals(password)) {
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