package admissionform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;

public class AddmissionForm extends JFrame {

    private JTextField nameField, fatherNameField, motherNameField, contactField, emailField, ageField, hscYearField, sscYearField, hscGpaField, sscGpaField;
    private JRadioButton maleRadio, femaleRadio, otherRadio;
    private JComboBox<String> deptBox, bloodGroupBox, semesterBox;
    private JTextArea presentAddressArea, permanentAddressArea;
    private JCheckBox sameAddressCheckBox;
    private JButton submitButton, showButton;
    private JSpinner dobSpinner;
    private JTable table;
    private DefaultTableModel tableModel;
    
    private StringBuilder savedData;

    public AddmissionForm() {
        setTitle("Admission Form");
        setSize(600, 800);  // Increased height for additional components
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        savedData = new StringBuilder();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add Title Label at the Top
        JLabel titleLabel = new JLabel("Metropolitan University", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24)); // Adjust font style and size as needed
        titleLabel.setForeground(Color.BLUE); // Optional: Set the color of the title text

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns to center the title
        add(titleLabel, gbc);

        // Reset gridwidth for other components
        gbc.gridwidth = 1;

        // Name Label and Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        add(nameField, gbc);

        // Father's Name Label and Field
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Father's Name:"), gbc);
        gbc.gridx = 1;
        fatherNameField = new JTextField(20);
        add(fatherNameField, gbc);

        // Mother's Name Label and Field
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Mother's Name:"), gbc);
        gbc.gridx = 1;
        motherNameField = new JTextField(20);
        add(motherNameField, gbc);

        // Contact Number Label and Field
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Contact Number:"), gbc);
        gbc.gridx = 1;
        contactField = new JTextField(15);
        add(contactField, gbc);

        // Email Label and Field
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(20);
        add(emailField, gbc);

        // Date of Birth Label and Spinner
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("Date of Birth:"), gbc);
        gbc.gridx = 1;
        dobSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dobSpinner, "dd-MM-yyyy");
        dobSpinner.setEditor(dateEditor);
        add(dobSpinner, gbc);

        // Age Label and Field (Read-Only)
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(new JLabel("Age:"), gbc);
        gbc.gridx = 1;
        ageField = new JTextField(20);
        ageField.setEditable(false);
        add(ageField, gbc);

        // Blood Group Label and ComboBox
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(new JLabel("Blood Group:"), gbc);
        gbc.gridx = 1;
        bloodGroupBox = new JComboBox<>(new String[] {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"});
        add(bloodGroupBox, gbc);

        // Gender Label and RadioButtons
        gbc.gridx = 0;
        gbc.gridy = 9;
        add(new JLabel("Gender:"), gbc);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        otherRadio = new JRadioButton("Other");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(otherRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        genderPanel.add(otherRadio);
        gbc.gridx = 1;
        add(genderPanel, gbc);

        // Department Label and ComboBox
        gbc.gridx = 0;
        gbc.gridy = 10;
        add(new JLabel("Department:"), gbc);
        gbc.gridx = 1;
        deptBox = new JComboBox<>(new String[] {
                "Computer Science & Engineering", "Software Engineering",
                "Electrical & Electronic Engineering", "Business Administration",
                "Economics", "English", "Law & Justice"});
        add(deptBox, gbc);

        // Semester Label and ComboBox
        gbc.gridx = 0;
        gbc.gridy = 11;
        add(new JLabel("Semester:"), gbc);
        gbc.gridx = 1;
        semesterBox = new JComboBox<>(new String[] {"Spring", "Summer"});
        add(semesterBox, gbc);

        // HSC Year Label and Field
        gbc.gridx = 0;
        gbc.gridy = 12;
        add(new JLabel("HSC Year:"), gbc);
        gbc.gridx = 1;
        hscYearField = new JTextField(4);
        add(hscYearField, gbc);

        // HSC GPA Label and Field
        gbc.gridx = 0;
        gbc.gridy = 13;
        add(new JLabel("HSC GPA:"), gbc);
        gbc.gridx = 1;
        hscGpaField = new JTextField(5);
        add(hscGpaField, gbc);

        // SSC Year Label and Field
        gbc.gridx = 0;
        gbc.gridy = 14;
        add(new JLabel("SSC Year:"), gbc);
        gbc.gridx = 1;
        sscYearField = new JTextField(4);
        add(sscYearField, gbc);

        // SSC GPA Label and Field
        gbc.gridx = 0;
        gbc.gridy = 15;
        add(new JLabel("SSC GPA:"), gbc);
        gbc.gridx = 1;
        sscGpaField = new JTextField(5);
        add(sscGpaField, gbc);

        // Present Address Label and TextArea
        gbc.gridx = 0;
        gbc.gridy = 16;
        add(new JLabel("Present Address:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        presentAddressArea = new JTextArea(3, 20);
        add(new JScrollPane(presentAddressArea), gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Permanent Address Label, TextArea, and Same Address Checkbox
        gbc.gridx = 0;
        gbc.gridy = 17;
        add(new JLabel("Permanent Address:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        permanentAddressArea = new JTextArea(3, 20);
        add(new JScrollPane(permanentAddressArea), gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        sameAddressCheckBox = new JCheckBox("Same as Present Address");
        gbc.gridy = 18;
        gbc.gridx = 1;
        add(sameAddressCheckBox, gbc);

        sameAddressCheckBox.addActionListener(e -> {
            if (sameAddressCheckBox.isSelected()) {
                permanentAddressArea.setText(presentAddressArea.getText());
                permanentAddressArea.setEditable(false);
            } else {
                permanentAddressArea.setEditable(true);
            }
        });

        // Submit Button
        gbc.gridx = 1;
        gbc.gridy = 19;
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitAction());
        add(submitButton, gbc);

        // Show Saved Information Button
        gbc.gridx = 1;
        gbc.gridy = 20;
        showButton = new JButton("Show Saved Information");
        showButton.addActionListener(new ShowAction());
        add(showButton, gbc);

        // Initialize JTable
        String[] columns = {"Name", "Father's Name", "Mother's Name", "Contact", "Email", "DOB", "Age", "Blood Group", "Gender", "Department", "Semester", "HSC Year", "HSC GPA", "SSC Year", "SSC GPA", "Present Address", "Permanent Address"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(1000, 180));
        gbc.gridx = 0;
        gbc.gridy = 21;
        gbc.gridwidth = 2;
        add(tableScrollPane, gbc);
    }

    private class SubmitAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (nameField.getText().isEmpty() || contactField.getText().isEmpty() || !isValidEmail(emailField.getText())) {
                JOptionPane.showMessageDialog(AddmissionForm.this, "Please fill all fields and provide a valid email.");
                return;
            }

            String[] row = {
                    nameField.getText(),
                    fatherNameField.getText(),
                    motherNameField.getText(),
                    contactField.getText(),
                    emailField.getText(),
                    new SimpleDateFormat("dd-MM-yyyy").format((Date) dobSpinner.getValue()),
                    ageField.getText(),
                    (String) bloodGroupBox.getSelectedItem(),
                    maleRadio.isSelected() ? "Male" : femaleRadio.isSelected() ? "Female" : "Other",
                    (String) deptBox.getSelectedItem(),
                    (String) semesterBox.getSelectedItem(),
                    hscYearField.getText(),
                    hscGpaField.getText(),
                    sscYearField.getText(),
                    sscGpaField.getText(),
                    presentAddressArea.getText(),
                    permanentAddressArea.getText()
            };

            tableModel.addRow(row);
            JOptionPane.showMessageDialog(AddmissionForm.this, "Information Submitted!");
            clearFormFields();
        }
    }

    private class ShowAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // The table automatically updates when the "Submit" button is clicked, no need for extra logic here
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    private void clearFormFields() {
        nameField.setText("");
        fatherNameField.setText("");
        motherNameField.setText("");
        contactField.setText("");
        emailField.setText("");
        ageField.setText("");
        hscYearField.setText("");
        hscGpaField.setText("");
        sscYearField.setText("");
        sscGpaField.setText("");
        presentAddressArea.setText("");
        permanentAddressArea.setText("");
        dobSpinner.setValue(new Date());
        sameAddressCheckBox.setSelected(false);
        maleRadio.setSelected(false);
        femaleRadio.setSelected(false);
        otherRadio.setSelected(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddmissionForm().setVisible(true));
    }
}
