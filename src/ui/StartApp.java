package ui;
import Utilities.SQLUtil;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.SQLException;

// Entry point for the Swing GUI application!!!
public class StartApp {
    public static void main(String[] args) {
        // Initial Database Connectivity Check
        try (Connection conn = SQLUtil.getConnection()) {
            if (conn != null) {
                System.out.println("Database connection successful. Launching application...");
            } else { // This shouldn't happen under normal circumstances
                throw new SQLException("Failed to establish database connection.");
            }
        } catch (SQLException e) {
            System.err.println("FATAL ERROR: Could not connect to database. Please check DBUtil.java configuration and running service.");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Application Startup Failed: Database connection error.\n" +
                            "Details: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return; // Exits the application if DB fails
        }

        // Launches GUI on event dispatch thread
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}

// Author: Andrew Witten