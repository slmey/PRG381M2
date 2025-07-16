package view;

import model.Feedback;

import javax.imageio.plugins.tiff.ExifTIFFTagSet;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainDashboard {
    public MainDashboard() {
        setTitle("Wellness Management System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //create tabbed panel
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Appointments", new AppointmentPanel());
        tabbedPane.addTab("Counselors", new CounselorPanel());
        tabbedPane.addTab("Feedback", new FeedbackPanel());

        add(tabbedPane, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(MainDashboard::new);
    }
}
