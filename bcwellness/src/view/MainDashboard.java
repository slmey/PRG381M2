package view;

import javax.swing.*;
import java.awt.*;

public class MainDashboard extends JFrame {

    public MainDashboard() {
        setTitle("Wellness Management System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create tabbed panel
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
