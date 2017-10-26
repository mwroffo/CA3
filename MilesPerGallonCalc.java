// Program: MilesPerGallonCalc.java
// Written by: Michael Roffo
// Description: A GUI that calculates miles per gallon.
// Challenges: Setting up exception handling for non-double inputs.
// Time Spent: 3 hours
// 
//                   Revision History
// Date:                   By:               Action:
// ---------------------------------------------------
// 10/26/17 (MR) created.
// 10/17/17 (MR) add proper layout management:

import java.awt.BorderLayout; // for Layout
import java.awt.GridLayout;
import java.awt.Container; 
import java.awt.event.ActionListener; // to handle events
import java.awt.event.ActionEvent; // to create event objects
import javax.swing.JPanel; //  
import javax.swing.JFrame; // for the entire frame library, etc.
import javax.swing.JLabel; // needs labels,
import javax.swing.JTextField; // textfields,
import javax.swing.JButton; // and buttons.
import javax.swing.JOptionPane; // JOptionPane for alerts.

public class MilesPerGallonCalc extends JFrame {
    // DECLARE INSTANCE VARS:
    private double miles;
    private double gallons;
    private final JLabel gallonsLabel;
    private final JLabel milesLabel;
    private final JLabel milesPerGallonLabel;
    private final JLabel resultLabel;
    private final JTextField gallonsTextField;
    private final JTextField milesTextField;
    private final JButton calcButton;
    private final JButton clearButton;
    private final JButton exitButton;
    // private final GridLayout labelsLayout;
    // private final GridLayout textFieldsLayout;
    // private final GridLayout buttonsLayout;
    private final JPanel labelJPanel;
    private final JPanel textFieldJPanel;
    private final JPanel buttonsJPanel;

    public MilesPerGallonCalc() {
        super("Miles per Gallon Calculator");

        // set layouts on JPanels because JPanels are containers:
        labelJPanel = new JPanel();
        labelJPanel.setLayout(new GridLayout(3,1));
        textFieldJPanel = new JPanel();
        textFieldJPanel.setLayout(new GridLayout(3,1));
        buttonsJPanel = new JPanel();
        buttonsJPanel.setLayout(new GridLayout(1,3));

        // **************
        // CREATE LABELS:
        // **************
        gallonsLabel = new JLabel("Gallons of gas:");
        milesLabel = new JLabel("Number of miles:");
        milesPerGallonLabel = new JLabel("Miles per Gallon (MPG):");
        resultLabel = new JLabel("<result here>");

        // ******************
        // CREATE TEXTFIELDS:
        // ******************
        gallonsTextField = new JTextField("enter gallons burned", 20);
        milesTextField = new JTextField("enter miles traveled", 20);

        // ***************
        // CREATE BUTTONS:
        // ***************
        calcButton = new JButton("Calculate MPG");
        clearButton = new JButton("Clear");
        exitButton = new JButton("Exit");

        // **********************
        // ADD OBJECTS TO PANELS:
        // **********************
        labelJPanel.add(gallonsLabel);
        textFieldJPanel.add(gallonsTextField);
        labelJPanel.add(milesLabel);
        textFieldJPanel.add(milesTextField);
        labelJPanel.add(milesPerGallonLabel);
        textFieldJPanel.add(resultLabel);
        buttonsJPanel.add(calcButton);
        buttonsJPanel.add(clearButton);
        buttonsJPanel.add(exitButton);

        // ADD PANELS TO FRAME:
        add(labelJPanel, BorderLayout.WEST);
        add(textFieldJPanel, BorderLayout.EAST);
        add(buttonsJPanel, BorderLayout.SOUTH);

        // ******************
        // REGISTER HANDLERS:
        // ******************
        MPGHandler mpgHandler = new MPGHandler();
        ClearHandler clearHandler = new ClearHandler();
        ExitHandler exitHandler = new ExitHandler();
        calcButton.addActionListener(mpgHandler);
        clearButton.addActionListener(clearHandler);
        exitButton.addActionListener(exitHandler);

    }

    public double calcMPG() {
        setMiles();
        setGallons();
        return miles / gallons ;
    }

    public void setMiles() throws NumberFormatException {
        try {
            miles = Double.parseDouble(milesTextField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error (1). Enter decimal for miles.");
        }
    }

    public void setGallons() throws NumberFormatException {
        try {
            gallons = Double.parseDouble(gallonsTextField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error (2). Enter decimal for gallons.");
        }
    }

    // Define calculate MPG handler:
    private class MPGHandler implements ActionListener {
        // handler always starts by overriding method actionPerformed():
        @Override
        public void actionPerformed(ActionEvent event) {
            resultLabel.setText(String.format("%.2f", calcMPG()));
        }
    }

    // define clear handler:
    private class ClearHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            gallonsTextField.setText("");
            milesTextField.setText("");
        }
    }

    // define exit handler:
    private class ExitHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }

    // TEST CLIENT:
    public static void main(String[] args) {
        // create calc object
        MilesPerGallonCalc calculator = new MilesPerGallonCalc();
        // set preferences:
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculator.setSize(450, 150);
        calculator.setVisible(true);
    }
}