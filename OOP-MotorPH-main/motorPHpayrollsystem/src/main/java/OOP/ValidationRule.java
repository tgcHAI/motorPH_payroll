/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OOP;

import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 *
 * @author Fen
 */
public enum ValidationRule {
    REQUIRED("This field is required.", s -> s != null && !s.trim().isEmpty()),
    NAME("Name must contain only letters, spaces, hyphens, or apostrophes.", s -> {
        if (s == null || s.trim().isEmpty()) return true;
        return Pattern.matches("^[A-Za-z\\s\\-'.]{2,50}$", s.trim());
    }),
    EMPLOYEE_ID("Employee ID must be 3–20 alphanumeric characters (e.g., EMP123).", s -> {
        if (s == null || s.trim().isEmpty()) return true;
        return Pattern.matches("^[A-Za-z0-9]{3,20}$", s.trim());
    }),
    SSS("SSS number must be exactly 10 digits.", s -> {
        if (s == null || s.trim().isEmpty()) return true;
        return Pattern.matches("^\\d{10}$", s.trim());
    }),
    PHILHEALTH("PhilHealth number must be exactly 12 digits.", s -> {
        if (s == null || s.trim().isEmpty()) return true;
        return Pattern.matches("^\\d{12}$", s.trim());
    }),
    TIN("TIN must be 9, 12, or 13 digits (e.g., 123456789).", s -> {
        if (s == null || s.trim().isEmpty()) return true;
        String clean = s.trim().replaceAll("[\\-\\s]", ""); // Remove hyphens/spaces
        return Pattern.matches("^\\d{9}$|^\\d{12}$|^\\d{13}$", clean);
    }),
    
    PAGIBIG("Pag-IBIG MID must be exactly 12 digits.", s -> {
        if (s == null || s.trim().isEmpty()) return true;
        return Pattern.matches("^\\d{12}$", s.trim());
    }),
    PHONE("Phone number must be 7–15 digits, optionally with +, -, or spaces.", s -> {
        if (s == null || s.trim().isEmpty()) return true;
        return Pattern.matches("^[+]?[0-9\\s\\-()]{7,15}$", s.trim());
    }),
    DECIMAL_NON_NEGATIVE("Must be a non-negative number (e.g., 123.45).", s -> {
        if (s == null || s.trim().isEmpty()) return true;
        try {
            double d = Double.parseDouble(s.trim());
            return d >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }),
    
    DATE_YYYYMMDD("Date must be in YYYY-MM-DD format.", s -> {
        if (s == null || s.trim().isEmpty()) return true;
        return Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", s.trim());
    }),
    GENERIC_TEXT("Text too long or contains invalid characters.", s -> {
        if (s == null) return true;
        return s.length() <= 200; // Max 200 chars
    });
    private final String errorMessage;
    private final Predicate<String> validator;

    ValidationRule(String errorMessage, Predicate<String> validator) {
        this.errorMessage = errorMessage;
        this.validator = validator;
    }
    public boolean isValid(String value) {
        return validator.test(value);
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}