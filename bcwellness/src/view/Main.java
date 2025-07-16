package view;

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
            tabbedPane.addTab("Appointments", new JLabel("Appointments UI Placeholder"));
            tabbedPane.addTab("Counselors", new JLabel("Counselors UI Placeholder"));
            tabbedPane.addTab("Feedback", new FeedbackPanel());

            frame.add(tabbedPane, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}
