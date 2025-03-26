package OOP;

import GUI.EmployeePortal;
import GUI.LogIn;
import javax.swing.table.TableModel;

public class Employee extends User {
    private String employeeNumber, lastName, firstName, birthday, status, contactNumber, address;
    private double basicSalary, hourlyRate, riceSubsidy, phoneSubsidy, clothingAllowance;
    private String sssNumber, pagIbigNumber, philHealthNumber, tinNumber;

    // Constructor
    public Employee(String email, String password, String employeeNumber, String lastName, String firstName,
                    String birthday, String status, String contactNumber, String address, 
                    double basicSalary, double hourlyRate, double riceSubsidy, 
                    double phoneSubsidy, double clothingAllowance, 
                    String sssNumber, String pagIbigNumber, 
                    String philHealthNumber, String tinNumber) {
        
        super(email, password, "Employee");
        this.employeeNumber = employeeNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.status = status;
        this.contactNumber = contactNumber;
        this.address = address;
        this.basicSalary = basicSalary;
        this.hourlyRate = hourlyRate;
        this.riceSubsidy = riceSubsidy;
        this.phoneSubsidy = phoneSubsidy;
        this.clothingAllowance = clothingAllowance;
        this.sssNumber = sssNumber;
        this.pagIbigNumber = pagIbigNumber;
        this.philHealthNumber = philHealthNumber;
        this.tinNumber = tinNumber;
    }

    // Constructor to load data from JTable
    public Employee(TableModel model, int row) {
        super("", "", "Employee");  // Default email/password (not stored in JTable)
        this.employeeNumber = getValue(model, row, 0);
        this.lastName = getValue(model, row, 1);
        this.firstName = getValue(model, row, 2);
        this.birthday = getValue(model, row, 7);
        this.status = getValue(model, row, 10);
        this.contactNumber = getValue(model, row, 9);
        this.address = getValue(model, row, 8);
        this.basicSalary = parseDouble(model, row, 13);
        this.hourlyRate = parseDouble(model, row, 18);
        this.riceSubsidy = parseDouble(model, row, 14);
        this.phoneSubsidy = parseDouble(model, row, 15);
        this.clothingAllowance = parseDouble(model, row, 16);
        this.sssNumber = getValue(model, row, 3);
        this.pagIbigNumber = getValue(model, row, 6);
        this.philHealthNumber = getValue(model, row, 4);
        this.tinNumber = getValue(model, row, 5);
        
    }

    private String getValue(TableModel model, int row, int col) {
        Object value = model.getValueAt(row, col);
        return value != null ? value.toString() : "";
    }

    private double parseDouble(TableModel model, int row, int col) {
        try {
            return Double.parseDouble(getValue(model, row, col));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    // Getter methods for individual attributes
    public String getEmployeeNumber() { return employeeNumber; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getBirthday() { return birthday; }
    public String getStatus() { return status; }
    public String getContactNumber() { return contactNumber; }
    public String getAddress() { return address; }
    public double getBasicSalary() { return basicSalary; }
    public double getHourlyRate() { return hourlyRate; }
    public double getRiceSubsidy() { return riceSubsidy; }
    public double getPhoneSubsidy() { return phoneSubsidy; }
    public double getClothingAllowance() { return clothingAllowance; }
    public String getSssNumber() { return sssNumber; }
    public String getPagIbigNumber() { return pagIbigNumber; }
    public String getPhilHealthNumber() { return philHealthNumber; }
    public String getTinNumber() { return tinNumber; }

    // Method to return employee details in a String array
    public String[] getDetails() {
        return new String[]{
            lastName, firstName, birthday, status, contactNumber, address,
            String.valueOf(basicSalary), String.valueOf(hourlyRate),
            String.valueOf(riceSubsidy), String.valueOf(phoneSubsidy), String.valueOf(clothingAllowance),
            sssNumber, pagIbigNumber, philHealthNumber, tinNumber, employeeNumber
        };
    }
}

