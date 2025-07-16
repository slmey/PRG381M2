package view;

import controller.CounselorController;
import model.Counselor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CounselorPanel extends JPanel {
    private JTextField txtCounselorId, txtFirstName, txtLastName, txtSpecialization, txtEmail, txtAvailability;

    public CounselorPanel() {
        setLayout(new GridLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtCounselorId = new JTextField();
        txtFirstName = new JTextField();
        txtLastName = new JTextField();
        txtSpecialization = new JTextField();
        txtEmail = new JTextField();
        txtAvailability = new JTextField("Available/Unavailable");

        String[] labels = {
                "Counselor ID",
                "First Name",
                "Last Name",
                "Specialization",
                "Email",
                "Availability"
        };
        JTextField[] fields = {
                txtCounselorId,
                txtFirstName,
                txtLastName,
                txtSpecialization,
                txtEmail,
                txtAvailability
        };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            add(new JLabel(labels[i]), gbc);

            gbc.gridx = 1;
            add(fields[i], gbc);
        }

        JButton btnAdd = new JButton("Add Counselor");
        btnAdd.addActionListener(this::addCounselor);
        add(btnAdd);
    }

    private void addCounselor(ActionEvent e){
        try{
            int counselorId = Integer.parseInt(txtCounselorId.getText());
            String firstName = txtFirstName.getText();
            String lastName = txtLastName.getText();
            String specialization = txtSpecialization.getText();
            String email = txtEmail.getText();
            String availability = txtAvailability.getText();

            if (counselorId < 1 || firstName.isEmpty() || lastName.isEmpty() || specialization.isEmpty() || email.isEmpty() || availability.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all the fields");
                return;
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
