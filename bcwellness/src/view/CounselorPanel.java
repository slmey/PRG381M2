package view;

import controller.CounselorController;
import model.Counselor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/*
 * A panel for managing counselors, providing a GUI for CRUD operations:
 * adding, viewing, updating, and removing counselors.
 */
public class CounselorPanel extends JPanel {
    private final JTextField[] fields = new JTextField[6]; // Array to hold input fields
    private final String[] labels = {
            "Counselor ID:", "First Name:", "Last Name:",
            "Specialization:", "Email:", "Availability:"
    };
    private JTable table;         // Table to display counselor data
    private DefaultTableModel tableModel; // Model for the JTable

    /*
     * Constructs the CounselorPanel with input fields, buttons, and table.
     */
    public CounselorPanel() {
        setLayout(new BorderLayout());
        initializeInputPanel();
        initializeTable();
        System.out.println("CounselorPanel initialized"); // Debug print
    }

    /*
     * Initializes the input panel with labels, text fields, and buttons.
     */
    private void initializeInputPanel() {
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.gridwidth = 1;
            inputPanel.add(new JLabel(labels[i]), gbc);

            fields[i] = new JTextField();
            if (labels[i].equals("Availability:")) {
                fields[i].setText("Available/Unavailable");
            }
            gbc.gridx = 1;
            inputPanel.add(fields[i], gbc);
        }

        JButton btnAdd = new JButton("Add Counselor");
        btnAdd.addActionListener(this::addCounselor);
        JButton btnUpdate = new JButton("Update Counselor");
        btnUpdate.addActionListener(this::updateCounselor);
        JButton btnDelete = new JButton("Remove Counselor");
        btnDelete.addActionListener(this::deleteCounselor);
        JButton btnRefresh = new JButton("View/Refresh");
        btnRefresh.addActionListener(this::refreshTable);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnRefresh);
        System.out.println("Buttons added: " + buttonPanel.getComponentCount()); // Debug print

        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        gbc.weighty = 0.2;
        inputPanel.add(buttonPanel, gbc);

        add(inputPanel, BorderLayout.NORTH);
    }

    /*
     * Initializes the table to display counselor data.
     */
    private void initializeTable() {
        String[] columnNames = {"Counselor ID", "First Name", "Last Name", "Specialization", "Email", "Availability"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        System.out.println("Table initialized"); // Debug print
        add(scrollPane, BorderLayout.CENTER);
        refreshTable(null); // Initial load of data

        // Add listener for row selection
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int selectedRow = table.getSelectedRow();
                fields[0].setText(table.getValueAt(selectedRow, 0).toString()); // Counselor ID
                fields[1].setText(table.getValueAt(selectedRow, 1).toString()); // First Name
                fields[2].setText(table.getValueAt(selectedRow, 2).toString()); // Last Name
                fields[3].setText(table.getValueAt(selectedRow, 3).toString()); // Specialization
                fields[4].setText(table.getValueAt(selectedRow, 4).toString()); // Email
                fields[5].setText(table.getValueAt(selectedRow, 5).toString()); // Availability
            }
        });
    }

    /*
     * Handles adding a new counselor to the system.
     * e - The action event triggered by the button
     */
    private void addCounselor(ActionEvent e) {
        try {
            int counselorId = Integer.parseInt(fields[0].getText());
            String firstName = fields[1].getText();
            String lastName = fields[2].getText();
            String specialization = fields[3].getText();
            String email = fields[4].getText();
            String availability = fields[5].getText();

            if (counselorId < 1 || firstName.isEmpty() || lastName.isEmpty()
                    || specialization.isEmpty() || email.isEmpty() || availability.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all the fields");
                return;
            }

            Counselor c = new Counselor(counselorId, firstName, lastName, specialization, email, availability);
            CounselorController.addCounselor(c);
            JOptionPane.showMessageDialog(this, "Counselor added!");
            clearFields();
            refreshTable(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    /*
     * Handles updating an existing counselor's details.
     * e - The action event triggered by the button
     */
    private void updateCounselor(ActionEvent e) {
        try {
            int counselorId = Integer.parseInt(fields[0].getText());
            String firstName = fields[1].getText();
            String lastName = fields[2].getText();
            String specialization = fields[3].getText();
            String email = fields[4].getText();
            String availability = fields[5].getText();

            if (counselorId < 1 || firstName.isEmpty() || lastName.isEmpty()
                    || specialization.isEmpty() || email.isEmpty() || availability.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all the fields");
                return;
            }

            Counselor c = new Counselor(counselorId, firstName, lastName, specialization, email, availability);
            CounselorController.updateCounselor(c);
            JOptionPane.showMessageDialog(this, "Counselor updated!");
            clearFields();
            refreshTable(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    /*
     * Handles removing a counselor from the system.
     * e - The action event triggered by the button
     */
    private void deleteCounselor(ActionEvent e) {
        try {
            int counselorId = Integer.parseInt(fields[0].getText());
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm Removal", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                CounselorController.deleteCounselor(counselorId);
                JOptionPane.showMessageDialog(this, "Counselor removed!");
                clearFields();
                refreshTable(null);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    /*
     * Refreshes the table with the latest counselor data.
     * e - The action event triggered by the button (can be null)
     */
    private void refreshTable(ActionEvent e) {
        tableModel.setRowCount(0); // Clear table
        ArrayList<Counselor> counselors = CounselorController.getAllCounselors();
        for (Counselor c : counselors) {
            tableModel.addRow(new Object[]{
                    c.getCounselorId(), c.getFirstName(), c.getLastName(),
                    c.getSpecialization(), c.getEmail(), c.getAvailability()
            });
        }
    }

    /*
     * Clears all input fields after a successful operation.
     */
    private void clearFields() {
        for (JTextField field : fields) field.setText("");
        fields[5].setText("Available/Unavailable"); // Reset availability
    }
}