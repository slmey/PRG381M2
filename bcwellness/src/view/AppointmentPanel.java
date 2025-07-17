/*package view;

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
}*/

package view;

import controller.AppointmentController;
import model.Appointment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class AppointmentPanel extends JPanel {
    private final JTextField[] fields = new JTextField[6];
    private final String[] labels = {"ID:", "Student Name:", "Counselor ID:", "Date (yyyy-mm-dd):", "Time (hh:mm:ss):", "Status:"};
    private JTable table;
    private DefaultTableModel tableModel;

    public AppointmentPanel() {
        setLayout(new BorderLayout());
        initializeInputPanel();
        initializeTable();
    }

    private void initializeInputPanel() {
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;

        for (int i = 0; i < labels.length; i++) {
            fields[i] = new JTextField(15);
            if (labels[i].contains("Date")) fields[i].setText("yyyy-mm-dd");
            if (labels[i].contains("Time")) fields[i].setText("hh:mm:ss");
            if (labels[i].contains("Status")) fields[i].setText("Scheduled");

            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.gridwidth = 1;
            inputPanel.add(new JLabel(labels[i]), gbc);

            gbc.gridx = 1;
            inputPanel.add(fields[i], gbc);
        }

        JButton btnSave = new JButton("Book Appointment");
        btnSave.addActionListener(this::saveAppointment);
        JButton btnUpdate = new JButton("Update Appointment");
        btnUpdate.addActionListener(this::updateAppointment);
        JButton btnDelete = new JButton("Cancel Appointment");
        btnDelete.addActionListener(this::deleteAppointment);
        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(this::refreshTable);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnSave);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnRefresh);

        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        gbc.weighty = 0.2;
        inputPanel.add(buttonPanel, gbc);

        add(inputPanel, BorderLayout.NORTH);
    }

    private void initializeTable() {
        String[] columnNames = {"ID", "Student Name", "Counselor ID", "Date", "Time", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        refreshTable(null); // Initial load
    }

    private void saveAppointment(ActionEvent e) {
        try {
            int id = Integer.parseInt(fields[0].getText());
            String studentName = fields[1].getText();
            int counselorId = Integer.parseInt(fields[2].getText());
            Date date = Date.valueOf(fields[3].getText());
            Time time = Time.valueOf(fields[4].getText());
            String status = fields[5].getText();

            if (id < 1 || studentName.isEmpty() || counselorId < 1 || date == null || time == null || status.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields correctly.");
                return;
            }

            Appointment appt = new Appointment(id, studentName, counselorId, date, time, status);
            AppointmentController.addAppointment(appt);
            JOptionPane.showMessageDialog(this, "Appointment booked.");
            clearFields();
            refreshTable(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void updateAppointment(ActionEvent e) {
        try {
            int id = Integer.parseInt(fields[0].getText());
            String studentName = fields[1].getText();
            int counselorId = Integer.parseInt(fields[2].getText());
            Date date = Date.valueOf(fields[3].getText());
            Time time = Time.valueOf(fields[4].getText());
            String status = fields[5].getText();

            if (id < 1 || studentName.isEmpty() || counselorId < 1 || date == null || time == null || status.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields correctly.");
                return;
            }

            Appointment appt = new Appointment(id, studentName, counselorId, date, time, status);
            AppointmentController.updateAppointment(appt);
            JOptionPane.showMessageDialog(this, "Appointment updated.");
            clearFields();
            refreshTable(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void deleteAppointment(ActionEvent e) {
        try {
            int id = Integer.parseInt(fields[0].getText());
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                AppointmentController.deleteAppointment(id);
                JOptionPane.showMessageDialog(this, "Appointment cancelled.");
                clearFields();
                refreshTable(null);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void refreshTable(ActionEvent e) {
        tableModel.setRowCount(0); // Clear table
        ArrayList<Appointment> appointments = AppointmentController.getAllAppointments();
        for (Appointment appt : appointments) {
            tableModel.addRow(new Object[]{
                    appt.getId(), appt.getStudentName(), appt.getCounselorId(),
                    appt.getDate(), appt.getTime(), appt.getStatus()
            });
        }
    }

    private void clearFields() {
        for (JTextField field : fields) field.setText("");
        fields[3].setText("yyyy-mm-dd");
        fields[4].setText("hh:mm:ss");
        fields[5].setText("Scheduled");
    }
}