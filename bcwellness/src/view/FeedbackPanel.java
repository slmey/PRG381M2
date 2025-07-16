package view;

import controller.FeedBackController;
import model.Feedback;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FeedbackPanel extends JPanel {
    private JTextField txtId, txtStudentName, txtStudentEmail, txtFeedback, txtRating;

    public FeedbackPanel() {
        setLayout(new GridLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtId = new JTextField();
        txtStudentName = new JTextField();
        txtStudentEmail = new JTextField();
        txtFeedback = new JTextField();
        txtRating = new JTextField();

        String[] lables = {
                "ID",
                "Student Name",
                "Student Email",
                "Feedback",
                "Rating"
        };
        JTextField[] fields = {
                txtId,
                txtStudentName,
                txtStudentEmail,
                txtFeedback,
                txtRating
        };

        for (int i = 0; i < lables.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            add(new JLabel(lables[i]), gbc);

            gbc.gridx = 1;
            add(fields[i], gbc);
        }

        JButton btnSubmit = new JButton("Submit Feedback");
        btnSubmit.addActionListener(this::submitFeedback);
        add(btnSubmit);

        gbc.gridx = 0;
        gbc.gridy = lables.length;
        gbc.gridwidth = 2;
        add(btnSubmit, gbc);
    }

    private void submitFeedback(ActionEvent e) {
        try {
            int id = Integer.parseInt(txtId.getText());
            String studentName = txtStudentName.getText();
            String studentEmail = txtStudentEmail.getText();
            String feedback = txtFeedback.getText();
            int rating = Integer.parseInt(txtRating.getText());

            if (id < 1 || studentName.isEmpty() || studentEmail.isEmpty() || feedback.isEmpty() || rating < 1 || rating > 5) {
                JOptionPane.showMessageDialog(this, "Please fill all the fields");
                return;
            }

            Feedback f = new Feedback(id, studentName, studentEmail, feedback, rating);
            FeedBackController.addFeedback(f);
            JOptionPane.showMessageDialog(this, "Feedback submitted");
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Error" + ex.getMessage());
        }
    }
}
