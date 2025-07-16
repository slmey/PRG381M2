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

        JButton btnUpdate = new JButton("Update Counselor");
        btnUpdate.addActionListener(this::updateCounselor);

        JButton btnDelete = new JButton("Delete Counselor");
        btnDelete.addActionListener(this::deleteCounselor);

        JButton btnView = new JButton("View All Counselors");
        btnView.addActionListener(this::viewCounselors);

        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        gbc.weighty = 0.2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnView);
        add(buttonPanel, gbc);
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
            if (CounselorController.addCounselor(c)) {
                JOptionPane.showMessageDialog(this, "Counselor added!");
            } else {
                JOptionPane.showMessageDialog(this, "Error adding counselor.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void updateCounselor(ActionEvent e) {
        try {
            int counselorId = Integer.parseInt(fields[0].getText());
            if (!CounselorController.counselorExists(counselorId)) {
                JOptionPane.showMessageDialog(this, "Counselor ID does not exist.");
                return;
            }

            String firstName = fields[1].getText();
            String lastName = fields[2].getText();
            String specialization = fields[3].getText();
            String email = fields[4].getText();
            String availability = fields[5].getText();

            if (firstName.isEmpty() || lastName.isEmpty() || specialization.isEmpty() || email.isEmpty() || availability.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all the fields");
                return;
            }

            Counselor c = new Counselor(counselorId, firstName, lastName, specialization, email, availability);
            if (CounselorController.updateCounselor(c)) {
                JOptionPane.showMessageDialog(this, "Counselor updated!");
            } else {
                JOptionPane.showMessageDialog(this, "Error updating counselor.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void deleteCounselor(ActionEvent e) {
        try {
            int counselorId = Integer.parseInt(fields[0].getText());
            if (!CounselorController.counselorExists(counselorId)) {
                JOptionPane.showMessageDialog(this, "Counselor ID does not exist.");
                return;
            }

            int confirmed = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this counselor?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION && CounselorController.deleteCounselor(counselorId)) {
                JOptionPane.showMessageDialog(this, "Counselor deleted!");
            } else {
                JOptionPane.showMessageDialog(this, "Error deleting counselor.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void viewCounselors(ActionEvent e) {
        java.util.List<Counselor> counselors = CounselorController.getAllCounselors();
        if (counselors.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No counselors found.");
            return;
        }

        StringBuilder message = new StringBuilder("List of Counselors:\n");
        for (Counselor c : counselors) {
            message.append(c.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, message.toString());
    }
}
