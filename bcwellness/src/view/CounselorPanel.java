package view;

import controller.CounselorController;
import model.Counselor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CounselorPanel extends JPanel {
    private final JTextField[] fields = new JTextField[6];
    private final String[] labels = {
            "Counselor ID:", "First Name:", "Last Name:",
            "Specialization:", "Email:", "Availability:"
    };

    public CounselorPanel() {
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
            if (labels[i].equals("Availability:")) {
                fields[i].setText("Available/Unavailable");
            }
            gbc.gridx = 1;
            add(fields[i], gbc);
        }

        JButton btnAdd = new JButton("Add Counselor");
        btnAdd.addActionListener(this::addCounselor);

        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        gbc.weighty = 0.2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnAdd, gbc);
    }

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
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}