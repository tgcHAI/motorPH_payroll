package OOP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Finance extends User {
    
    private static final String EMPLOYEE_DATA_FILE = "CSV/EmpData.csv";

    public static String getEMPLOYEE_DATA_FILE() {
        return EMPLOYEE_DATA_FILE;
    }
    //comment, I made the mistake of commiting this wrong on netbeans instead of github
    //data fields
    private double basicSalary;
    private double hourlyRate;
    private double riceSubsidy;
    private double phoneAllowance;
    private double clothingAllowance;
    private double sss;
    private double philHealth;
    private double tin;
    private double pagIbig;
    
    public Finance(String email, String password) {
        super(email, password, "Finance");
    }
   public void loadEmployeeData(String employeeID) {
        try (BufferedReader br = new BufferedReader(new FileReader(EMPLOYEE_DATA_FILE))) {
            String line;
            // Skip header
            br.readLine();
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 19) continue;
                //NOTE: columns that are salary driven are columns 4-7 and columns 14-19
                if (data[0].trim().equals(employeeID)) {
                    // Retrieve Finance-related fields:
                    this.sss = parseDouble(data[4]);  // SSS #
                    this.philHealth = parseDouble(data[5]);  // PhilHealth #
                    this.tin = parseDouble(data[6]);  // TIN #
                    this.pagIbig = parseDouble(data[7]);  // Pag IBIG #
                    this.basicSalary = parseDouble(data[14]);  // Basic Salary
                    this.riceSubsidy = parseDouble(data[15]);  // Rice Subsidy
                    this.phoneAllowance = parseDouble(data[16]);  // Phone Allowance
                    this.clothingAllowance = parseDouble(data[17]);  // Clothing Allowance
                    this.hourlyRate = parseDouble(data[18]);  // Hourly Rate
                    found = true;
                    break;
                }
            }
            if (!found) {}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Helper method to safely parse a double from a String.
    private double parseDouble(String value) {
        try {
            return Double.parseDouble(value.replace("\"", "").replace(",", ""));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
    
    //getter zone

    public double getBasicSalary() {
        return basicSalary;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getRiceSubsidy() {
        return riceSubsidy;
    }

    public double getPhoneAllowance() {
        return phoneAllowance;
    }

    public double getClothingAllowance() {
        return clothingAllowance;
    }

    public double getSss() {
        return sss;
    }

    public double getPhilHealth() {
        return philHealth;
    }

    public double getTin() {
        return tin;
    }

    public double getPagIbig() {
        return pagIbig;
    }
    
    //setter zone

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setRiceSubsidy(double riceSubsidy) {
        this.riceSubsidy = riceSubsidy;
    }

    public void setPhoneAllowance(double phoneAllowance) {
        this.phoneAllowance = phoneAllowance;
    }

    public void setClothingAllowance(double clothingAllowance) {
        this.clothingAllowance = clothingAllowance;
    }

    public void setSss(double sss) {
        this.sss = sss;
    }

    public void setPhilHealth(double philHealth) {
        this.philHealth = philHealth;
    }

    public void setTin(double tin) {
        this.tin = tin;
    }

    public void setPagIbig(double pagIbig) {
        this.pagIbig = pagIbig;
    }
    
}