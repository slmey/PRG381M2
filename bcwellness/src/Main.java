import view.AppointmentPanel;
import view.CounselorPanel;
import view.FeedbackPanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Wellness Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            // Tabs for each section
            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Appointments", new AppointmentPanel());
            tabbedPane.addTab("Counselors", new CounselorPanel());
            tabbedPane.addTab("Feedback", new FeedbackPanel());

            frame.add(tabbedPane, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}