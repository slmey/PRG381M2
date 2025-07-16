package view;

import controller.AppointmentController;
import model.Appointment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AppointmentPanel extends JPanel {
    private final JTextField[] fields = new JTextField[6];
    private final String[] labels = {
            "ID:", "Student Name:", "Counselor ID:",
            "Date (yyyy-mm-dd):", "Time (hh:mm:ss):", "Status:"
    };

    public AppointmentPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;

        for (int i = 0; i < labels.length; i++) {
            fields[i] = new JTextField();
            if (labels[i].contains("Date")) fields[i].setText("yyyy-mm-dd");
            if (labels[i].contains("Time")) fields[i].setText("hh:mm:ss");
            if (labels[i].contains("Status")) fields[i].setText("Scheduled");

            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.gridwidth = 1;
            add(new JLabel(labels[i]), gbc);

            gbc.gridx = 1;
            add(fields[i], gbc);
        }

        JButton btnSave = new JButton("Book Appointment");
        btnSave.addActionListener(this::saveAppointment);

        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        gbc.weighty = 0.2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnSave, gbc);
    }

    private void saveAppointment(ActionEvent e) {
        try {
            int id = Integer.parseInt(fields[0].getText());
            String studentName = fields[1].getText();
            int counselorId = Integer.parseInt(fields[2].getText());
            String date = fields[3].getText();
            String time = fields[4].getText();
            String status = fields[5].getText();

            if (id < 1 || studentName.isEmpty() || counselorId < 1 ||
                    date.isEmpty() || time.isEmpty() || status.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all the fields.");
                return;
            }

            Appointment appt = new Appointment(id, studentName, counselorId, date, time, status);
            AppointmentController.addAppointment(appt);
            JOptionPane.showMessageDialog(this, "Appointment has been booked.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}