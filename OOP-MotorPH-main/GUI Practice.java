import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {
   
    // Declaration of GUI components
    private JLabel milestone1Label, milestone2Label, terminalLabel, resultLabel;
    private JTextField milestone1Field, milestone2Field, terminalField;
    private JButton calculateButton;

    // Constructor to initialize GUI
    public GUI() {
        setTitle("Milestone Calculator");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(180,211,178));

      // Initialize and position labels
        milestone1Label = new JLabel("Milestone 1 (0-25):");
        milestone1Label.setBounds(10, 10, 150, 25);
        
        milestone2Label = new JLabel("Milestone 2 (0-40):");
        milestone2Label.setBounds(10, 40, 150, 25);
        
        terminalLabel = new JLabel("Terminal Assessment (0-35):");
        terminalLabel.setBounds(10, 70, 200, 25);
        
        resultLabel = new JLabel("Result:");
        resultLabel.setBounds(10, 100,1108, 25);

       // Initialize and position text fields
        milestone1Field = new JTextField();
        milestone1Field.setBounds(200, 10, 120, 25);
        
        milestone2Field = new JTextField();
        milestone2Field.setBounds(200, 40, 120, 25);
        
        terminalField = new JTextField();
        terminalField.setBounds(200, 70, 120, 25);

        // Initialize and position the calculate button
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(140, 130, 100, 25);
        calculateButton.addActionListener(this); // Add action listener

        // Add components to the frame
        add(milestone1Label);
        add(milestone1Field);
        add(milestone2Label);
        add(milestone2Field);
        add(terminalLabel);
        add(terminalField);
        add(resultLabel);
        add(new JLabel()); // This adds an empty label for formatting purposes
        add(calculateButton);

        setVisible(true); // Make the GUI visible
    }

   // ActionListener interface method
    public void actionPerformed(ActionEvent e) {
        calculateGrades(); // Call the method to calculate grades when the button is clicked
    }

   // Method to calculate grades
    private void calculateGrades() {
        try {
            // Parse input values from text fields
            float milestone1 = Float.parseFloat(milestone1Field.getText());
            float milestone2 = Float.parseFloat(milestone2Field.getText());
            float terminal = Float.parseFloat(terminalField.getText());

            // Check if input values are within valid ranges
            if (milestone1 < 0 || milestone1 > 25 || milestone2 < 0 || milestone2 > 40 || terminal < 0 || terminal > 35) {
                JOptionPane.showMessageDialog(this, "Invalid input! Values must be between 0 and the maximum points for each milestone.");
                return;  // Exit method if input is invalid
            }

            // Calculate grades based on input values
            float milestone1Grade = milestone1 / 25 * 100;
            float milestone2Grade = milestone2 / 40 * 100;
            float terminalGrade = terminal / 35 * 100;
            float totalGrade = (milestone1Grade * 0.25f) + (milestone2Grade * 0.4f) + (terminalGrade * 0.35f);

            // Display the result
            resultLabel.setText("Result: " + totalGrade + "%");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter valid numeric values.");
        }
    }

   // Main method to start the GUI
    public static void main(String[] args) {
         // Run GUI on the event dispatch thread to ensure thread safety
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI();   // Create and display the GUI
            }
        });
    }
}
