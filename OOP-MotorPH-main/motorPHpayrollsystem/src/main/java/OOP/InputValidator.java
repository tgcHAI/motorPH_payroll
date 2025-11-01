package OOP;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import java.awt.Component;
import java.util.Map;

/**
 * Validates user input across JTextField and JTextPane components.
 */
public class InputValidator {

    /**
     * Validates a map of text components against their validation rules.
     * Supports both JTextField and JTextPane.
     * 
     * @param fieldRules Map of Component (JTextField/JTextPane) → ValidationRule
     * @return null if all valid, or an error message string if invalid
     */
    public static String validate(Map<? extends Component, ValidationRule> fieldRules) {
    for (Map.Entry<? extends Component, ValidationRule> entry : fieldRules.entrySet()) {
        Component comp = entry.getKey();
        ValidationRule rule = entry.getValue();
        
        String value = "";
        if (comp instanceof JTextField) {
            value = ((JTextField) comp).getText();
        } else if (comp instanceof JTextPane) {
            value = ((JTextPane) comp).getText();
        }

        if (rule == ValidationRule.REQUIRED) {
            if (value == null || value.trim().isEmpty()) {
                return rule.getErrorMessage();
            }
        }

        if (value != null && !value.trim().isEmpty()) {
            if (!rule.isValid(value)) {
                return rule.getErrorMessage();
            }
        }
    }
    return null;
}
}