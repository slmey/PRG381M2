package view;

import controller.FeedBackController;
import controller.FeedbackController;
import model.Feedback;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FeedbackPanel extends JPanel {
    private JTextField txtId, txtStudentName, txtStudentEmail, txtFeedback, txtRating;
    private JTextArea txtComments;

    public FeedbackPanel() {
        setLayout(new GridLayout(5, 2, 10, 10));

        txtId = new JTextField();
        txtStudentName = new JTextField();
        txtStudentEmail = new JTextField();
        txtFeedback = new JTextField();
        txtRating = new JTextField();
        txtComments = new JTextArea();

        add(new JLabel("ID: "));
        add(txtId);
        add(new JLabel("Student Name: "));
        add(txtStudentName);
        add(new JLabel("Student Email: "));
        add(txtStudentEmail);
        add(new JLabel("Feedback: "));
        add(txtFeedback);
        add(new JLabel("Rating: "));
        add(txtRating);
        add(new JScrollPane(txtComments));

        JButton btnSubmit = new JButton("Submit Feedback");
        btnSubmit.addActionListener(this::submitFeedback);
        add(btnSubmit);
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
