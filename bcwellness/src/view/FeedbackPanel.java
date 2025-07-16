package view;

import controller.FeedBackController;
import model.Feedback;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FeedbackPanel extends JPanel {
    private JTextField txtId, txtStudentName, txtStudentEmail, txtFeedback, txtRating;

    public FeedbackPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtId = new JTextField(20);
        txtStudentName = new JTextField(20);
        txtStudentEmail = new JTextField(20);
        txtFeedback = new JTextField(20);
        txtRating = new JTextField(20);

        addLabelAndField("ID:", txtId, gbc, 0);
        addLabelAndField("Student Name:", txtStudentName, gbc, 1);
        addLabelAndField("Student Email:", txtStudentEmail, gbc, 2);
        addLabelAndField("Feedback:", txtFeedback, gbc, 3);
        addLabelAndField("Rating (1–5):", txtRating, gbc, 4);

        JButton btnSubmit = new JButton("Submit Feedback");
        btnSubmit.addActionListener(this::submitFeedback);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnSubmit, gbc);
    }

    private void addLabelAndField(String labelText, JTextField field, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        add(field, gbc);
    }

    private void submitFeedback(ActionEvent e) {
        try {
            int id = Integer.parseInt(txtId.getText());
            String studentName = txtStudentName.getText();
            String studentEmail = txtStudentEmail.getText();
            String feedback = txtFeedback.getText();
            int rating = Integer.parseInt(txtRating.getText());

            if (id < 1 || studentName.isEmpty() || studentEmail.isEmpty() || feedback.isEmpty() || rating < 1 || rating > 5) {
                JOptionPane.showMessageDialog(this, "Please fill all the fields correctly");
                return;
            }

            Feedback f = new Feedback(id, studentName, studentEmail, feedback, rating);
            FeedBackController.addFeedback(f);
            JOptionPane.showMessageDialog(this, "Feedback submitted");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}