package admissionform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.Period;
import java.util.Date;
import com.toedter.calendar.JDateChooser;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class AdmissionForm extends JFrame {

    private JTextField nameField, fatherNameField, motherNameField, contactField, emailField, ageField, hscGpaField,
            sscGpaField;
    private JRadioButton maleRadio, femaleRadio, otherRadio;
    private ButtonGroup bloodGroupButtonGroup;
    private JRadioButton aPositive, aNegative, bPositive, bNegative, abPositive, abNegative, oPositive, oNegative;

    private JComboBox<Department> deptBox;
    private JComboBox<String> bloodGroupBox, semesterBox, hscYearBox, sscYearBox;
    private JTextArea presentAddressArea, permanentAddressArea;
    private JCheckBox sameAddressCheckBox;
    private JTable table;
    private DefaultTableModel tableModel;
    private JDateChooser dateChooser; // Removed dayComboBox, monthComboBox, yearComboBox

    enum Department {
        CSE("Computer Science & Engineering"),
        SWE("Software Engineering"),
        EEE("Electrical & Electronic Engineering"),
        BBA("Business Administration"),
        ECO("Economics"),
        ENG("English"),
        LAW("Law & Justice");

        private final String displayName;

        Department(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

    public AdmissionForm() {
        setTitle("Admission Form");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(238, 230, 230));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("Metropolitan University", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLUE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);
        gbc.gridwidth = 1;

        // Name
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        add(nameField, gbc);

        // Father's Name
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Father's Name:"), gbc);
        gbc.gridx = 1;
        fatherNameField = new JTextField(20);
        add(fatherNameField, gbc);

        // Mother's Name
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Mother's Name:"), gbc);
        gbc.gridx = 1;
        motherNameField = new JTextField(20);
        add(motherNameField, gbc);

        // Contact
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Contact Number:"), gbc);
        gbc.gridx = 1;
        contactField = new JTextField(15);
        add(contactField, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(20);
        add(emailField, gbc);

        // Date of Birth using JDateChooser
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("Date of Birth:"), gbc);

        gbc.gridx = 1;
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd MMM yyyy");  // Example format: 27 Nov 2024
        add(dateChooser, gbc);

        // Calculate age when the date changes
        dateChooser.addPropertyChangeListener("date", evt -> calculateAge());

        // Age
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(new JLabel("Age:"), gbc);
        gbc.gridx = 1;
        ageField = new JTextField(20);
        ageField.setEditable(false);
        add(ageField, gbc);

        // Blood Group (replace existing JComboBox code)
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(new JLabel("Blood Group:"), gbc);

        JPanel bloodGroupPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        aPositive = new JRadioButton("A+");
        aNegative = new JRadioButton("A-");
        bPositive = new JRadioButton("B+");
        bNegative = new JRadioButton("B-");
        abPositive = new JRadioButton("AB+");
        abNegative = new JRadioButton("AB-");
        oPositive = new JRadioButton("O+");
        oNegative = new JRadioButton("O-");

        bloodGroupButtonGroup = new ButtonGroup();
        bloodGroupButtonGroup.add(aPositive);
        bloodGroupButtonGroup.add(aNegative);
        bloodGroupButtonGroup.add(bPositive);
        bloodGroupButtonGroup.add(bNegative);
        bloodGroupButtonGroup.add(abPositive);
        bloodGroupButtonGroup.add(abNegative);
        bloodGroupButtonGroup.add(oPositive);
        bloodGroupButtonGroup.add(oNegative);

        bloodGroupPanel.add(aPositive);
        bloodGroupPanel.add(aNegative);
        bloodGroupPanel.add(bPositive);
        bloodGroupPanel.add(bNegative);
        bloodGroupPanel.add(abPositive);
        bloodGroupPanel.add(abNegative);
        bloodGroupPanel.add(oPositive);
        bloodGroupPanel.add(oNegative);

        gbc.gridx = 1;
        add(bloodGroupPanel, gbc);



        // Gender
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

        // Department
        gbc.gridx = 0;
        gbc.gridy = 10;
        add(new JLabel("Department:"), gbc);
        gbc.gridx = 1;
        deptBox = new JComboBox<>(Department.values());
        add(deptBox, gbc);

        // Semester
        gbc.gridx = 0;
        gbc.gridy = 11;
        add(new JLabel("Semester:"), gbc);
        gbc.gridx = 1;
        semesterBox = new JComboBox<>(new String[] { "Spring", "Summer" });
        add(semesterBox, gbc);

        // HSC Year and GPA
        gbc.gridx = 0;
        gbc.gridy = 12;
        add(new JLabel("HSC Year:"), gbc);
        gbc.gridx = 1;
        hscYearBox = new JComboBox<>();
        for (int i = 2020; i <= 2024; i++) hscYearBox.addItem(String.valueOf(i));
        add(hscYearBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 13;
        add(new JLabel("HSC GPA:"), gbc);
        gbc.gridx = 1;
        hscGpaField = new JTextField(5);
        add(hscGpaField, gbc);

        // SSC Year and GPA
        gbc.gridx = 0;
        gbc.gridy = 14;
        add(new JLabel("SSC Year:"), gbc);
        gbc.gridx = 1;
        sscYearBox = new JComboBox<>();
        for (int i = 2018; i <= 2022; i++) sscYearBox.addItem(String.valueOf(i));
        add(sscYearBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 15;
        add(new JLabel("SSC GPA:"), gbc);
        gbc.gridx = 1;
        sscGpaField = new JTextField(5);
        add(sscGpaField, gbc);

        // Addresses
        gbc.gridx = 0;
        gbc.gridy = 16;
        add(new JLabel("Present Address:"), gbc);
        gbc.gridx = 1;
        presentAddressArea = new JTextArea(3, 20);
        add(new JScrollPane(presentAddressArea), gbc);

        gbc.gridx = 0;
        gbc.gridy = 17;
        add(new JLabel("Permanent Address:"), gbc);
        gbc.gridx = 1;
        permanentAddressArea = new JTextArea(3, 20);
        add(new JScrollPane(permanentAddressArea), gbc);

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

        // Buttons
        gbc.gridx = 1;
        gbc.gridy = 19;
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitAction());
        add(submitButton, gbc);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> clearFormFields());
        gbc.gridx = 0;
        add(resetButton, gbc);

        // JTable
        String[] columns = { "Name", "Father's Name", "Mother's Name", "Contact", "Email", "DOB", "Age", "Blood Group",
                "Gender", "Department", "Semester", "HSC Year", "HSC GPA", "SSC Year", "SSC GPA", "Present Address",
                "Permanent Address" };
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1200, 200));

        gbc.gridx = 0;
        gbc.gridy = 20;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

        pack();
        
        // Buttons for Save and Load
        JButton saveButton = new JButton("Save Data");
        saveButton.addActionListener(e -> saveData());
        gbc.gridx = 0;
        gbc.gridy = 21;
        add(saveButton, gbc);

        JButton loadButton = new JButton("Load Data");
        loadButton.addActionListener(e -> loadData());
        gbc.gridx = 0;
        gbc.gridy = 22;
        add(loadButton, gbc);

        
       

    }

    private void clearFormFields() {
        nameField.setText("");
        fatherNameField.setText("");
        motherNameField.setText("");
        contactField.setText("");
        emailField.setText("");
        ageField.setText("");
        hscGpaField.setText("");
        sscGpaField.setText("");
        presentAddressArea.setText("");
        permanentAddressArea.setText("");
        sameAddressCheckBox.setSelected(false);
        maleRadio.setSelected(false);
        femaleRadio.setSelected(false);
        otherRadio.setSelected(false);
        deptBox.setSelectedIndex(0);
        semesterBox.setSelectedIndex(0);
        hscYearBox.setSelectedIndex(0);
        sscYearBox.setSelectedIndex(0);
        bloodGroupButtonGroup.clearSelection();

    }
    private boolean validateForm() {
    if (nameField.getText().trim().isEmpty() || fatherNameField.getText().trim().isEmpty() ||
        motherNameField.getText().trim().isEmpty() || contactField.getText().trim().isEmpty() ||
        emailField.getText().trim().isEmpty() || hscYearBox.getSelectedItem().toString().trim().isEmpty() ||
        sscYearBox.getSelectedItem().toString().trim().isEmpty() || hscGpaField.getText().trim().isEmpty() ||
        sscGpaField.getText().trim().isEmpty() || presentAddressArea.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    if (!Pattern.matches("\\d{11}", contactField.getText().trim())) {
        JOptionPane.showMessageDialog(this, "Invalid contact number!", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    if (!Pattern.matches("^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", emailField.getText().trim())) {
        JOptionPane.showMessageDialog(this, "Invalid email address!", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    try {
        double hscGpa = Double.parseDouble(hscGpaField.getText().trim());
        double sscGpa = Double.parseDouble(sscGpaField.getText().trim());
        if (hscGpa < 0 || hscGpa > 5 || sscGpa < 0 || sscGpa > 5) {
            JOptionPane.showMessageDialog(this, "GPA must be between 0 and 5!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "GPA must be a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    int sscYear = Integer.parseInt(sscYearBox.getSelectedItem().toString());
    int hscYear = Integer.parseInt(hscYearBox.getSelectedItem().toString());

    if (hscYear - sscYear < 2) {
        JOptionPane.showMessageDialog(this, "The gap between SSC and HSC must be at least 2 years!", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    // Check if a blood group is selected
if (bloodGroupButtonGroup.getSelection() == null) {
    JOptionPane.showMessageDialog(this, "Please select a blood group!", "Error", JOptionPane.ERROR_MESSAGE);
    return false;
}


    return true;
}

    private void calculateAge() {
    Date dob = dateChooser.getDate();
    if (dob == null) {
        JOptionPane.showMessageDialog(this, "Please select a valid date of birth!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    LocalDate birthDate = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate currentDate = LocalDate.now();
    if (birthDate.isAfter(currentDate)) {
        JOptionPane.showMessageDialog(this, "Date of birth cannot be in the future!", "Error", JOptionPane.ERROR_MESSAGE);
        ageField.setText("");
        return;
    }

    Period age = Period.between(birthDate, currentDate);
    ageField.setText(String.valueOf(age.getYears()));
}

   private class SubmitAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (validateForm()) {
            String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
            String gender = maleRadio.isSelected() ? "Male" : femaleRadio.isSelected() ? "Female" : "Other";

            // Determine the selected blood group
            String bloodGroup = getSelectedBloodGroup();

            // Confirm Submission
            int confirm = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to submit this form?", 
                "Confirm Submission", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Add data to JTable
                tableModel.addRow(new Object[] {
                    nameField.getText(), fatherNameField.getText(), motherNameField.getText(),
                    contactField.getText(), emailField.getText(), dob, ageField.getText(),
                    bloodGroup, gender, deptBox.getSelectedItem(), semesterBox.getSelectedItem(),
                    hscYearBox.getSelectedItem(), hscGpaField.getText(), sscYearBox.getSelectedItem(),
                    sscGpaField.getText(), presentAddressArea.getText(), permanentAddressArea.getText()
                });

                JOptionPane.showMessageDialog(null, "Form submitted successfully!");
                clearFormFields();
            }
        }
    }

    private String getSelectedBloodGroup() {
        if (aPositive.isSelected()) return "A+";
        else if (aNegative.isSelected()) return "A-";
        else if (bPositive.isSelected()) return "B+";
        else if (bNegative.isSelected()) return "B-";
        else if (abPositive.isSelected()) return "AB+";
        else if (abNegative.isSelected()) return "AB-";
        else if (oPositive.isSelected()) return "O+";
        else if (oNegative.isSelected()) return "O-";
        return "";
    }
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdmissionForm().setVisible(true));
    }
    

private void saveData() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("admission_data.ser"))) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Student student = new Student(
    tableModel.getValueAt(i, 0).toString(), // Name
    tableModel.getValueAt(i, 1).toString(), // Father's Name
    tableModel.getValueAt(i, 2).toString(), // Mother's Name
    tableModel.getValueAt(i, 3).toString(), // Contact
    tableModel.getValueAt(i, 4).toString(), // Email
    tableModel.getValueAt(i, 5).toString(), // DOB
    tableModel.getValueAt(i, 6).toString(), // Age
    tableModel.getValueAt(i, 7).toString(), // Blood Group
    tableModel.getValueAt(i, 8).toString(), // Gender
    tableModel.getValueAt(i, 9).toString(), // Department
    tableModel.getValueAt(i, 10).toString(), // Semester
    tableModel.getValueAt(i, 11).toString(), // HSC Year
    tableModel.getValueAt(i, 12).toString(), // HSC GPA
    tableModel.getValueAt(i, 13).toString(), // SSC Year
    tableModel.getValueAt(i, 14).toString(), // SSC GPA
    tableModel.getValueAt(i, 15).toString(), // Present Address
    tableModel.getValueAt(i, 16).toString()  // Permanent Address
);
;
            oos.writeObject(student);
        }
        JOptionPane.showMessageDialog(this, "Data saved successfully!");
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error saving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void loadData() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("admission_data.ser"))) {
        tableModel.setRowCount(0); // Clear existing data
        while (true) {
            Student student = (Student) ois.readObject();
            tableModel.addRow(student.toTableRow());
        }
    } catch (EOFException ex) {
        JOptionPane.showMessageDialog(this, "Data loaded successfully!");
    } catch (IOException | ClassNotFoundException ex) {
        JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}


}
