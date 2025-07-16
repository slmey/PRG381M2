package view;

import controller.FeedBackController;
import model.Feedback;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FeedbackPanel extends JPanel {
    private final JTextField[] fields = new JTextField[5];
    private final String[] labels = {
            "ID:", "Student Name:", "Student Email:",
            "Feedback:", "Rating (1–5):"
    };

    public FeedbackPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.gridwidth = 1;
            add(new JLabel(labels[i]), gbc);

            fields[i] = new JTextField();
            gbc.gridx = 1;
            add(fields[i], gbc);
        }

        JButton btnSubmit = new JButton("Submit Feedback");
        btnSubmit.addActionListener(this::submitFeedback);

        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        gbc.weighty = 0.2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnSubmit, gbc);
    }

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
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}