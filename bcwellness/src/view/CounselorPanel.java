package view;

import controller.CounselorController;
import model.Counselor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CounselorPanel extends JPanel {
    private JTextField txtCounselorId, txtFirstName, txtLastName, txtSpecialization, txtEmail, txtAvailability;

    public CounselorPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtCounselorId = new JTextField(20);
        txtFirstName = new JTextField(20);
        txtLastName = new JTextField(20);
        txtSpecialization = new JTextField(20);
        txtEmail = new JTextField(20);
        txtAvailability = new JTextField("Available/Unavailable", 20);

        addLabelAndField("Counselor ID:", txtCounselorId, gbc, 0);
        addLabelAndField("First Name:", txtFirstName, gbc, 1);
        addLabelAndField("Last Name:", txtLastName, gbc, 2);
        addLabelAndField("Specialization:", txtSpecialization, gbc, 3);
        addLabelAndField("Email:", txtEmail, gbc, 4);
        addLabelAndField("Availability:", txtAvailability, gbc, 5);

        JButton btnAdd = new JButton("Add Counselor");
        btnAdd.addActionListener(this::addCounselor);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnAdd, gbc);
    }

    private void addLabelAndField(String labelText, JTextField field, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        add(field, gbc);
    }

    private void addCounselor(ActionEvent e){
        try{
            int counselorId = Integer.parseInt(txtCounselorId.getText());
            String firstName = txtFirstName.getText();
            String lastName = txtLastName.getText();
            String specialization = txtSpecialization.getText();
            String email = txtEmail.getText();
            String availability = txtAvailability.getText();

            if (counselorId < 1 || firstName.isEmpty() || lastName.isEmpty()
                    || specialization.isEmpty() || email.isEmpty() || availability.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all the fields");
                return;
            }

            Counselor c = new Counselor(counselorId, firstName, lastName, specialization, email, availability);
            CounselorController.addCounselor(c);
            JOptionPane.showMessageDialog(this, "Counselor added!");
        } catch (Exception ex){
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}