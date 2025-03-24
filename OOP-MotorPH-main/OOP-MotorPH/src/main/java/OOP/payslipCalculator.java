import java.io.*;
import java.util.*;

public class payslipCalculator {

    public static void main(String[] args) {
        // Usage: java PayslipCalculator <EmployeeID> <HoursWorked>
        if (args.length < 2) {
            System.out.println("Usage: java PayslipCalculator <EmployeeID> <HoursWorked>");
            System.exit(0);
        }
        String empID = args[0];
        double hoursWorked = Double.parseDouble(args[1]);

        // Read employee data from CSV
        EmployeePayrollData empData = readEmployeePayrollData(empID);
        if (empData == null) {
            System.out.println("Employee with ID " + empID + " not found.");
            System.exit(0);
        }

        // Instantiate the payroll calculator using the employee data.
        PayrollCalculator calculator = new PayrollCalculator(empData);

        // Compute values
        double grossPay = calculator.calculateGrossPay(hoursWorked);
        double totalDeductions = calculator.calculateTotalDeductions();
        double tax = calculator.calculateTax();
        double netPay = calculator.calculateNetPay(hoursWorked);

        // Display the payslip results
        System.out.println("Payslip for Employee ID: " + empID);
        System.out.printf("Gross Pay: %.2f%n", grossPay);
        System.out.printf("Total Deductions: %.2f%n", totalDeductions);
        System.out.printf("Tax: %.2f%n", tax);
        System.out.printf("Net Pay: %.2f%n", netPay);
    }

    /**
     * Reads EmpData.csv to find the payroll data for a given employee ID.
     * Expected CSV format (1-indexed columns):
     * 1: Employee ID
     * 5: SSS #
     * 6: PhilHealth #
     * 7: TIN #
     * 8: Pag IBIG #
     * 14: Basic Salary
     * 15: Rice Subsidy
     * 16: Phone Allowance
     * 17: Clothing Allowance
     * 18: Gross Semi-Monthly Rate
     * 19: Hourly Rate
     *
     * In the code, these are accessed as follows (0-indexed):
     * empID -> data[0]
     * SSS # -> data[4]
     * PhilHealth # -> data[5]
     * TIN # -> data[6]
     * Pag IBIG # -> data[7]
     * Basic Salary -> data[13]
     * Rice Subsidy -> data[14]
     * Phone Allowance -> data[15]
     * Clothing Allowance -> data[16]
     * Gross Semi-Monthly Rate -> data[17]
     * Hourly Rate -> data[18]
     */
    public static EmployeePayrollData readEmployeePayrollData(String empID) {
        String csvFile = "CSV/EmpData.csv";
        String line;
        String splitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Read header row first
            String headerLine = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);
                // Ensure the row has at least 19 columns (0-indexed: index 18)
                if (data.length < 19) continue;
                if (data[0].equals(empID)) {
                    // Read deduction IDs (if needed for display)
                    String sssNumber = data[4].replace("\"", "").trim();
                    String philHealthNumber = data[5].replace("\"", "").trim();
                    String tinNumber = data[6].replace("\"", "").trim();
                    String pagIbigNumber = data[7].replace("\"", "").trim();

                    // Read payroll-related values and remove any extraneous quotes or commas
                    double basicSalary = Double.parseDouble(data[13].replace("\"", "").replace(",", ""));
                    double riceSubsidy = Double.parseDouble(data[14].replace("\"", "").replace(",", ""));
                    double phoneAllowance = Double.parseDouble(data[15].replace("\"", "").replace(",", ""));
                    double clothingAllowance = Double.parseDouble(data[16].replace("\"", "").replace(",", ""));
                    double grossSemiMonthlyRate = Double.parseDouble(data[17].replace("\"", "").replace(",", ""));
                    double hourlyRate = Double.parseDouble(data[18].replace("\"", "").replace(",", ""));

                    return new EmployeePayrollData(empID, sssNumber, philHealthNumber, tinNumber, pagIbigNumber,
                            basicSalary, riceSubsidy, phoneAllowance, clothingAllowance, grossSemiMonthlyRate, hourlyRate);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // --- EmployeePayrollData class (Encapsulation) ---
    static class EmployeePayrollData {
        private String empID;
        private String sssNumber;
        private String philHealthNumber;
        private String tinNumber;
        private String pagIbigNumber;
        private double basicSalary;
        private double riceSubsidy;
        private double phoneAllowance;
        private double clothingAllowance;
        private double grossSemiMonthlyRate;
        private double hourlyRate;

        public EmployeePayrollData(String empID, String sssNumber, String philHealthNumber, String tinNumber,
                                   String pagIbigNumber, double basicSalary, double riceSubsidy, double phoneAllowance,
                                   double clothingAllowance, double grossSemiMonthlyRate, double hourlyRate) {
            this.empID = empID;
            this.sssNumber = sssNumber;
            this.philHealthNumber = philHealthNumber;
            this.tinNumber = tinNumber;
            this.pagIbigNumber = pagIbigNumber;
            this.basicSalary = basicSalary;
            this.riceSubsidy = riceSubsidy;
            this.phoneAllowance = phoneAllowance;
            this.clothingAllowance = clothingAllowance;
            this.grossSemiMonthlyRate = grossSemiMonthlyRate;
            this.hourlyRate = hourlyRate;
        }

        public String getEmpID() { return empID; }
        public String getSssNumber() { return sssNumber; }
        public String getPhilHealthNumber() { return philHealthNumber; }
        public String getTinNumber() { return tinNumber; }
        public String getPagIbigNumber() { return pagIbigNumber; }
        public double getBasicSalary() { return basicSalary; }
        public double getRiceSubsidy() { return riceSubsidy; }
        public double getPhoneAllowance() { return phoneAllowance; }
        public double getClothingAllowance() { return clothingAllowance; }
        public double getGrossSemiMonthlyRate() { return grossSemiMonthlyRate; }
        public double getHourlyRate() { return hourlyRate; }
    }

    // --- Deduction abstract class (Polymorphism) ---
    static abstract class Deduction {
        // Computes the deduction based on the given salary.
        public abstract double calculate(double salary);
    }

    // --- SSS Deduction ---
    static class SSSDeduction extends Deduction {
        @Override
        public double calculate(double salary) {
            double[][] sssTable = {
                {3250, 135}, {3750, 157.5}, {4250, 180}, {4750, 202.5}, {5250, 225},
                {5750, 247.5}, {6250, 270}, {6750, 292.5}, {7250, 315}, {7750, 337.5},
                {8250, 360}, {8750, 382.5}, {9250, 405}, {9750, 427.5}, {10250, 450},
                {10750, 472.5}, {11250, 495}, {11750, 517.5}, {12250, 540}, {12750, 562.5},
                {13250, 585}, {13750, 607.5}, {14250, 630}, {14750, 652.5}, {15250, 675},
                {15750, 697.5}, {16250, 720}, {16750, 742.5}, {17250, 765}, {17750, 787.5},
                {18250, 810}, {18750, 832.5}, {19250, 855}, {19750, 877.5}, {20250, 900},
                {20750, 922.5}, {21250, 945}, {21750, 967.5}, {22250, 990}, {22750, 1012.5},
                {23250, 1035}, {23750, 1057.5}, {24250, 1080}, {24750, 1102.5}, {Double.MAX_VALUE, 1125}
            };
            for (double[] bracket : sssTable) {
                if (salary < bracket[0]) {
                    return bracket[1];
                }
            }
            return 1125; // Fallback
        }
    }

    // --- Pag-IBIG Deduction ---
    static class PagIbigDeduction extends Deduction {
        @Override
        public double calculate(double salary) {
            double employeeContribution = salary * 0.02;
            return Math.min(employeeContribution, 100);
        }
    }

    // --- PhilHealth Deduction ---
    static class PhilHealthDeduction extends Deduction {
        @Override
        public double calculate(double salary) {
            return (salary * 0.03) / 2; // Employee's share
        }
    }

    // --- PayrollCalculator class ---
    static class PayrollCalculator {
        private EmployeePayrollData empData;
        private List<Deduction> deductions;

        public PayrollCalculator(EmployeePayrollData empData) {
            this.empData = empData;
            this.deductions = new ArrayList<>();
            // Use polymorphism to add deduction types
            deductions.add(new SSSDeduction());
            deductions.add(new PagIbigDeduction());
            deductions.add(new PhilHealthDeduction());
        }

        // Calculate gross pay given hours worked
        public double calculateGrossPay(double hoursWorked) {
            return empData.getHourlyRate() * hoursWorked;
        }

        // Calculate total deductions based on the basic salary
        public double calculateTotalDeductions() {
            double total = 0;
            for (Deduction d : deductions) {
                total += d.calculate(empData.getBasicSalary());
            }
            return total;
        }

        // Calculate taxable income
        public double calculateTaxableIncome() {
            return empData.getBasicSalary() - calculateTotalDeductions();
        }

        // Calculate tax (simplified brackets; expand as needed)
        public double calculateTax() {
            double taxableIncome = calculateTaxableIncome();
            if (taxableIncome <= 20832) {
                return 0;
            } else if (taxableIncome <= 33333) {
                return (taxableIncome - 20833) * 0.2;
            } else {
                return 2500 + (taxableIncome - 33333) * 0.25;
            }
        }

        // Calculate net pay: gross pay minus total deductions and tax
        public double calculateNetPay(double hoursWorked) {
            double grossPay = calculateGrossPay(hoursWorked);
            double totalDeductions = calculateTotalDeductions();
            double tax = calculateTax();
            return grossPay - totalDeductions - tax;
        }
    }
}
