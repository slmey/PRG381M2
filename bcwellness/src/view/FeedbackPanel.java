package view;

import controller.FeedBackController;
import model.Feedback;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/*
 * A panel for managing feedback, providing a GUI for CRUD operations:
 * submitting, viewing, editing, and deleting feedback.
 */
public class FeedbackPanel extends JPanel {
    private final JTextField[] fields = new JTextField[5];
    private final String[] labels = {"ID:", "Student Name:", "Student Email:", "Feedback:", "Rating (1–5):"};
    private JTable table;
    private DefaultTableModel tableModel;

    /*
     * Constructs the FeedbackPanel with input fields, buttons, and table.
     */
    public FeedbackPanel() {
        setLayout(new BorderLayout());
        initializeInputPanel();
        initializeTable();
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
            gbc.gridx = 1;
            inputPanel.add(fields[i], gbc);
        }

        JButton btnSubmit = new JButton("Submit Feedback");
        btnSubmit.addActionListener(this::submitFeedback);
        JButton btnEdit = new JButton("Edit Feedback");
        btnEdit.addActionListener(this::editFeedback);
        JButton btnDelete = new JButton("Delete Feedback");
        btnDelete.addActionListener(this::deleteFeedback);
        JButton btnView = new JButton("View Feedback");
        btnView.addActionListener(this::viewFeedback);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnSubmit);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnView);

        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        gbc.weighty = 0.2;
        inputPanel.add(buttonPanel, gbc);

        add(inputPanel, BorderLayout.NORTH);
    }

    /*
     * Initializes the table to display feedback data.
     */
    private void initializeTable() {
        String[] columnNames = {"ID", "Student Name", "Student Email", "Feedback", "Rating"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        viewFeedback(null); // Initial load of data

        // Add listener for row selection
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int selectedRow = table.getSelectedRow();
                fields[0].setText(table.getValueAt(selectedRow, 0).toString()); // ID
                fields[1].setText(table.getValueAt(selectedRow, 1).toString()); // Student Name
                fields[2].setText(table.getValueAt(selectedRow, 2).toString()); // Student Email
                fields[3].setText(table.getValueAt(selectedRow, 3).toString()); // Feedback
                fields[4].setText(table.getValueAt(selectedRow, 4).toString()); // Rating
            }
        });
    }

    /*
     * Handles submitting new feedback to the system.
     * e - The action event triggered by the button
     */
    private void submitFeedback(ActionEvent e) {
        try {
            int id = Integer.parseInt(fields[0].getText());
            String studentName = fields[1].getText();
            String studentEmail = fields[2].getText();
            String feedback = fields[3].getText();
            int rating = Integer.parseInt(fields[4].getText());

            if (id < 1 || studentName.isEmpty() || studentEmail.isEmpty()
                    || feedback.isEmpty() || rating < 1 || rating > 5) {
                JOptionPane.showMessageDialog(this, "Please fill all the fields correctly");
                return;
            }

            Feedback f = new Feedback(id, studentName, studentEmail, feedback, rating);
            FeedBackController.addFeedback(f);
            JOptionPane.showMessageDialog(this, "Feedback submitted");
            clearFields();
            viewFeedback(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    /*
     * Handles viewing all feedback in the table.
     * e - The action event triggered by the button (can be null)
     */
    private void viewFeedback(ActionEvent e) {
        tableModel.setRowCount(0); // Clear table
        ArrayList<Feedback> feedbacks = FeedBackController.getAllFeedback();
        for (Feedback f : feedbacks) {
            tableModel.addRow(new Object[]{
                    f.getId(), f.getStudentName(), f.getStudentEmail(), f.getFeedback(), f.getRating()
            });
        }
    }

    /*
     * Handles editing existing feedback.
     * e - The action event triggered by the button
     */
    private void editFeedback(ActionEvent e) {
        try {
            int id = Integer.parseInt(fields[0].getText());
            String studentName = fields[1].getText();
            String studentEmail = fields[2].getText();
            String feedback = fields[3].getText();
            int rating = Integer.parseInt(fields[4].getText());

            if (id < 1 || studentName.isEmpty() || studentEmail.isEmpty()
                    || feedback.isEmpty() || rating < 1 || rating > 5) {
                JOptionPane.showMessageDialog(this, "Please fill all the fields correctly");
                return;
            }

            Feedback f = new Feedback(id, studentName, studentEmail, feedback, rating);
            FeedBackController.updateFeedback(f);
            JOptionPane.showMessageDialog(this, "Feedback updated");
            clearFields();
            viewFeedback(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    /*
     * Handles deleting feedback from the system.
     * e - The action event triggered by the button
     */
    private void deleteFeedback(ActionEvent e) {
        try {
            int id = Integer.parseInt(fields[0].getText());
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                FeedBackController.deleteFeedback(id);
                JOptionPane.showMessageDialog(this, "Feedback deleted");
                clearFields();
                viewFeedback(null);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    /*
     * Clears all input fields after a successful operation.
     */
    private void clearFields() {
        for (JTextField field : fields) field.setText("");
    }
}