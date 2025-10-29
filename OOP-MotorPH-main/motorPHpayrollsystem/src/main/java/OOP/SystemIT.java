package OOP;

import GUI.*;
import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Arrays;
import java.util.UUID;
import FUNCTIONS.GoogleAuth.SecretKeyGenerator;
import static FUNCTIONS.GoogleAuth.SecretKeyGenerator.generateSecretKey;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
        checkSecretKey("user2@motorPH.com", "password2");
    }

    public static void helperMFAFunction(String email, JFrame loginFrame) {
        
        //Error handling
        if (email == null || loginFrame == null) {
            throw new IllegalArgumentException("email and loginFrame must not be null");
        }

        // Ensure we run UI work on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Hide the login window (do not dispose here so MFA can restore it on error)
            loginFrame.setVisible(false);
        });
    }
    
    public static String checkSecretKey(String email, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(EMPLOYEE_CSV))) {
            String line;
            boolean isFirstLine = true; // Flag to skip the header row
            int lineCount = 1;
            
            while ((line = br.readLine()) != null) {
                System.out.println(lineCount);
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip the header line
                }

                String[] parts = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
             
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].replaceAll("^\"|\"$", "").trim();
                }
                System.out.println(Arrays.toString(parts));
            
                   
                if (parts.length >= CSV_TOTP_SECRET_COL_INDEX) {
                    String csvEmail = parts[CSV_EMAIL_COL_INDEX].trim();
                    System.out.println(csvEmail);
                    String csvPassword = parts[CSV_PASSWORD_COL_INDEX].trim();
                    System.out.println(csvPassword);

                    // Check if provided email and password match a record in the CSV
                    if (email.equalsIgnoreCase(csvEmail) && password.equals(csvPassword)) {
                // TOTP secret may or may not exist
                String totpSecret = (parts.length > CSV_TOTP_SECRET_COL_INDEX)
                        ? parts[CSV_TOTP_SECRET_COL_INDEX].trim()
                        : ""; // treat missing column as empty -> enroll

    if (totpSecret == null || totpSecret.isEmpty() || totpSecret.equalsIgnoreCase("null")) {
        String[] path = parts;
        System.out.println(Arrays.toString(path));
        updateTotpSecret(EMPLOYEE_CSV, path, email, lineCount, br);
        return "";
    } else {
        System.out.println("User '" + email + "' authenticated with existing TOTP secret.");
        return totpSecret;
    }
        }
             } else {
                    // Log or handle malformed lines if necessary
                    System.err.println("Skipping malformed line in CSV (not enough columns): " + line);
                }
                
                lineCount += 1;
                
            }
            System.out.println("Authentication failed for user: " + email + " (user not found or invalid password).");
            return null; 
        } catch (IOException e) {
            System.err.println("Error reading employee data from file: " + e.getMessage());
            e.printStackTrace();
        }
        return "";
    }
    
    public static String updateTotpSecret(String csvPath, String[] updateRow, String email, int lineCount, BufferedReader br) throws IOException {
        Path csvFile = Paths.get(csvPath);
        Path tempFile = Files.createTempFile(csvFile.getParent(), "csv-update-", ".tmp");
        String updatedSecret = null;
        boolean found = false;

        if (br == null) {
            try (BufferedReader reader = Files.newBufferedReader(csvFile, StandardCharsets.UTF_8);
                 BufferedWriter writer = Files.newBufferedWriter(tempFile, StandardCharsets.UTF_8)) {
                processReaderAndWrite(reader, writer, updateRow, email);
            }
        } else {
            BufferedWriter writer = Files.newBufferedWriter(tempFile, StandardCharsets.UTF_8);
            try {
                updatedSecret = processReaderAndWriteReturnSecret(br, writer, updateRow, email);
                if (updatedSecret != null) found = true;
            } finally {
                writer.close();
            }
        }

    try {
        Files.move(tempFile, csvFile, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
    } catch (AtomicMoveNotSupportedException ex) {
        Files.move(tempFile, csvFile, StandardCopyOption.REPLACE_EXISTING);
    }

    return updatedSecret;
}

private static String processReaderAndWriteReturnSecret(BufferedReader reader, BufferedWriter writer, String[] updateRow, String email) throws IOException {
    String header = reader.readLine();
    if (header == null) {
        throw new IOException("CSV file is empty: " + " (path unknown in this helper)");
    }
    writer.write(header);
    writer.newLine();

    String line;
    String updatedSecret = null;

    String splitRegex = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

    while ((line = reader.readLine()) != null) {
        String[] parts = line.split(splitRegex, -1);

        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].replaceAll("^\"|\"$", "").trim();
        }

        // Check for email match
        if (CSV_EMAIL_COL_INDEX < parts.length && parts[CSV_EMAIL_COL_INDEX].equalsIgnoreCase(email)) {
            String newSecret = null;
            if (updateRow != null && updateRow.length > CSV_TOTP_SECRET_COL_INDEX && updateRow[CSV_TOTP_SECRET_COL_INDEX] != null) {
                newSecret = updateRow[CSV_TOTP_SECRET_COL_INDEX];
            } else {
                newSecret = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
            }

            // ensure array is large enough
            if (parts.length <= CSV_TOTP_SECRET_COL_INDEX) {
                parts = Arrays.copyOf(parts, CSV_TOTP_SECRET_COL_INDEX + 1);
                for (int k = 0; k < parts.length; k++) {
                    if (parts[k] == null) parts[k] = "";
                }
            }
            parts[CSV_TOTP_SECRET_COL_INDEX] = newSecret;
            updatedSecret = newSecret;
        }

        writer.write(joinCsv(parts));
        writer.newLine();
    }

    writer.flush();
    return updatedSecret;
}

private static String processReaderAndWrite(BufferedReader reader, BufferedWriter writer, String[] updateRow, String email) throws IOException {
    return processReaderAndWriteReturnSecret(reader, writer, updateRow, email);
}

private static String joinCsv(String[] parts) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < parts.length; i++) {
        if (i > 0) sb.append(',');
        sb.append(escapeCsv(parts[i]));
    }
    return sb.toString();
}

private static String escapeCsv(String field) {
    if (field == null) return "";
    boolean needQuotes = field.contains(",") || field.contains("\"") || field.contains("\n") || field.contains("\r");
    String escaped = field.replace("\"", "\"\"");
    return needQuotes ? ("\"" + escaped + "\"") : escaped;
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
    
    private static void redirectToMFA(String email){
        
    }

    private static void showEmployeePortal(Employee employee, JFrame loginFrame) {
        EmployeePortal frame = new EmployeePortal();
        frame.fillEmployeeDetails(employee);
        redirectToPortal(employee, frame, "Employee", loginFrame);
    }
}