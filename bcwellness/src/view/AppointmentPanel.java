package view;

import controller.AppointmentController;
import model.Appointment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AppointmentPanel extends JPanel {
    private JTextField txtId, txtStudentName, txtCounselorId, txtDate, txtTime, txtStatus;

    public AppointmentPanel() {
        setLayout(new GridLayout(7, 2, 10, 10));

        txtId = new JTextField();
        txtStudentName = new JTextField();
        txtCounselorId = new JTextField();
        txtDate = new JTextField("yyyy-mm-dd");
        txtTime = new JTextField("hh:mm:ss");
        txtStatus = new JTextField("Scheduled");

        add(new JLabel("ID: "));
        add(txtId);
        add(new JLabel("Student Name: "));
        add(txtStudentName);
        add(new JLabel("Counselor ID: "));
        add(txtCounselorId);
        add(new JLabel("Date: "));
        add(txtDate);
        add(new JLabel("Time: "));
        add(txtTime);
        add(new JLabel("Status: "));
        add(txtStatus);

        JButton btnSave = new JButton("Book Appointment");
        btnSave.addActionListener(this::saveAppointment);
        add(btnSave);
    }

    private void saveAppointment(ActionEvent e){
        try {
            int id = Integer.parseInt(txtId.getText());
            String studentName = txtStudentName.getText();
            int counselorId = Integer.parseInt(txtCounselorId.getText());
            String date = txtDate.getText();
            String time = txtTime.getText();
            String status = txtStatus.getText();

            if (id.isEmpty() || studentName.isEmpty() || counselorId.isEmpty() || date.isEmpty() || time.isEmpty() || status.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please fill all the fields.");
                return;
            }

            Appointment appt = new Appointment(id, studentName, counselorId, date, time, status);
            AppointmentController.addAppointment(appt);
            JOptionPane.showMessageDialog(this, "Appointment has been booked.");
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
