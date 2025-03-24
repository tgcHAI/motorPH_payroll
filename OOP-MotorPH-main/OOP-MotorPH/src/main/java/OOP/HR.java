package OOP;

import javax.swing.JOptionPane;

public class HR extends User {
    public HR(String email, String password) {
        super(email, password, "HR");
    }

    public void approveLeave(String employeeID) {
        JOptionPane.showMessageDialog(null, "Approved Request!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void rejectLeave(String employeeID) {
        JOptionPane.showMessageDialog(null, "Rejected the Request", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}


