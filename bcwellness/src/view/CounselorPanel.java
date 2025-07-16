package view;

import controller.CounselorController;
import model.Counselor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CounselorPanel extends JPanel {
    private JTextField txtCounselorId, txtFirstName, txtLastName, txtSpecialization, txtEmail, txtAvailability;

    public CounselorPanel() {
        setLayout(new GridLayout(5,2, 10, 10));

        txtCounselorId = new JTextField();
        txtFirstName = new JTextField();
        txtLastName = new JTextField();
        txtSpecialization = new JTextField();
        txtEmail = new JTextField();
        txtAvailability = new JTextField("Available/Unavailable");

        add(new JLabel("Counselor ID: "));
        add(txtCounselorId);
        add(new JLabel("First Name: "));
        add(txtFirstName);
        add(new JLabel("Last Name: "));
        add(txtLastName);
        add(new JLabel("Specialization: "));
        add(txtSpecialization);
        add(new JLabel("Email: "));
        add(txtEmail);
        add(new JLabel("Availability: "));
        add(txtAvailability);

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

            if (counselorId.isEmpty || firstName.isEmpty() || lastName.isEmpty() || specialization.isEmpty() || email.isEmpty() || availability.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all the fields");
                return;
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
