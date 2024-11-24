package admissionform;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;

public class AdmissionForm extends JFrame {

    private JTextField nameField, fatherNameField, motherNameField, contactField, emailField, ageField, hscGpaField,
            sscGpaField;
    private JRadioButton maleRadio, femaleRadio, otherRadio;
    private JComboBox<String> deptBox, bloodGroupBox, semesterBox, hscYearBox, sscYearBox;
    private JTextArea presentAddressArea, permanentAddressArea;
    private JCheckBox sameAddressCheckBox;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> dayComboBox, monthComboBox, yearComboBox;

    public AdmissionForm() {
        setTitle("Admission Form");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
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

        // Date of Birth
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("Date of Birth:"), gbc);
        gbc.gridx = 1;
        JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dayComboBox = new JComboBox<>();
        for (int i = 1; i <= 31; i++)
            dayComboBox.addItem(String.valueOf(i));
        dobPanel.add(dayComboBox);

        monthComboBox = new JComboBox<>(new String[] { "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December" });
        dobPanel.add(monthComboBox);

        yearComboBox = new JComboBox<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear - 30; i <= currentYear; i++)
            yearComboBox.addItem(String.valueOf(i));
        dobPanel.add(yearComboBox);

        add(dobPanel, gbc);

        // Age
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(new JLabel("Age:"), gbc);
        gbc.gridx = 1;
        ageField = new JTextField(20);
        ageField.setEditable(false);

        yearComboBox.addActionListener(e -> calculateAge());
        add(ageField, gbc);

        // Blood Group
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(new JLabel("Blood Group:"), gbc);
        gbc.gridx = 1;
        bloodGroupBox = new JComboBox<>(new String[] { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" });
        add(bloodGroupBox, gbc);

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
        deptBox = new JComboBox<>(new String[] { "Computer Science & Engineering", "Software Engineering",
                "Electrical & Electronic Engineering", "Business Administration", "Economics", "English",
                "Law & Justice" });
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
        for (int i = 2020; i <= 2024; i++)
            hscYearBox.addItem(String.valueOf(i));
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
        for (int i = 2018; i <= 2022; i++)
            sscYearBox.addItem(String.valueOf(i));
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
    }

    private class SubmitAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (validateForm()) {
                String dob = dayComboBox.getSelectedItem() + " " +
                        monthComboBox.getSelectedItem() + " " +
                        yearComboBox.getSelectedItem();

                String gender = maleRadio.isSelected() ? "Male" : femaleRadio.isSelected() ? "Female" : "Other";

                // Add data to the JTable
                tableModel.addRow(new Object[] {
                        nameField.getText(), fatherNameField.getText(), motherNameField.getText(),
                        contactField.getText(), emailField.getText(), dob, ageField.getText(),
                        bloodGroupBox.getSelectedItem(), gender, deptBox.getSelectedItem(),
                        semesterBox.getSelectedItem(), hscYearBox.getSelectedItem(), hscGpaField.getText(),
                        sscYearBox.getSelectedItem(), sscGpaField.getText(), presentAddressArea.getText(),
                        permanentAddressArea.getText()
                });

                // Save data to a text file
                saveDataToFile();

                JOptionPane.showMessageDialog(null, "Form submitted successfully!");
                clearFormFields();
            }
        }
    }

    private void saveDataToFile() {
        try (FileWriter writer = new FileWriter("admission_data.txt", true)) { // Append mode
            writer.write("Name: " + nameField.getText() + "\n");
            writer.write("Father's Name: " + fatherNameField.getText() + "\n");
            writer.write("Mother's Name: " + motherNameField.getText() + "\n");
            writer.write("Contact: " + contactField.getText() + "\n");
            writer.write("Email: " + emailField.getText() + "\n");
            writer.write("Date of Birth: " + dayComboBox.getSelectedItem() + " " +
                    monthComboBox.getSelectedItem() + " " + yearComboBox.getSelectedItem() + "\n");
            writer.write("Age: " + ageField.getText() + "\n");
            writer.write("Blood Group: " + bloodGroupBox.getSelectedItem() + "\n");
            writer.write("Gender: " + (maleRadio.isSelected() ? "Male" : femaleRadio.isSelected() ? "Female" : "Other")
                    + "\n");
            writer.write("Department: " + deptBox.getSelectedItem() + "\n");
            writer.write("Semester: " + semesterBox.getSelectedItem() + "\n");
            writer.write("HSC Year: " + hscYearBox.getSelectedItem() + "\n");
            writer.write("HSC GPA: " + hscGpaField.getText() + "\n");
            writer.write("SSC Year: " + sscYearBox.getSelectedItem() + "\n");
            writer.write("SSC GPA: " + sscGpaField.getText() + "\n");
            writer.write("Present Address: " + presentAddressArea.getText() + "\n");
            writer.write("Permanent Address: " + permanentAddressArea.getText() + "\n");
            writer.write("------------------------------------------------------------\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving data to file!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void calculateAge() {
        int selectedYear = Integer.parseInt((String) yearComboBox.getSelectedItem());
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        ageField.setText(String.valueOf(currentYear - selectedYear));
    }

    private void clearFormFields() {
        nameField.setText("");
        fatherNameField.setText("");
        motherNameField.setText("");
        contactField.setText("");
        emailField.setText("");
        ageField.setText("");
       // hscYearField.setText("");
        //sscYearField.setText("");
        hscGpaField.setText("");
        sscGpaField.setText("");
        presentAddressArea.setText("");
        permanentAddressArea.setText("");
        dayComboBox.setSelectedIndex(0);
        monthComboBox.setSelectedIndex(0);
        yearComboBox.setSelectedIndex(0);
        bloodGroupBox.setSelectedIndex(0);
        deptBox.setSelectedIndex(0);
        semesterBox.setSelectedIndex(0);
        maleRadio.setSelected(false);
        femaleRadio.setSelected(false);
        otherRadio.setSelected(false);
        sameAddressCheckBox.setSelected(false);
        permanentAddressArea.setEditable(true);
        hscYearBox.setSelectedIndex(0);
        sscYearBox.setSelectedIndex(0);
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
        if (!Pattern.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", emailField.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Invalid email address!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdmissionForm().setVisible(true);
        });
    }
}
