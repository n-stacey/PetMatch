package ui;
import bo.User;
import dao.UserDAO;
import dao.UserDAOImpl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/*
 This is the UI entry point for the Swing GUI application.
 Handles user login using UserDAO.
 */
public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private final UserDAO userDAO;

    public LoginFrame() {
        super("Pet Matcher Login");
        // Initialize DAO
        this.userDAO = new UserDAOImpl();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null); // Center on screen

        initComponents();
        layoutComponents();
        addListeners();
    }

    private void initComponents() {
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Log In");
    }

    private void layoutComponents() {
        JPanel formPanel = new JPanel(new SpringLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);

        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);

        // Simple alignment hack for SpringLayout
        SpringUtilities.makeCompactGrid(formPanel, 2, 2, 6, 6, 6, 6);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);

        this.setLayout(new BorderLayout());
        this.add(formPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addListeners() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                User user = userDAO.login(email, password);

                if (user != null) {
                    if(user.getIsAdmin()) {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Login Successful! Role: Admin");
                        // Open MainFrame, passing the user object and their admin status
                        try {
                            new MainFrame(user).setVisible(true);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        dispose();
                    }
                    else if(!user.getIsAdmin()) {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Login Successful! Role: User");
                        // Open MainFrame, passing the user object and their admin status
                        try {
                            new MainFrame(user).setVisible(true);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid credentials.", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}

// A simple SpringLayout utility for demonstration
class SpringUtilities {
    public static void makeCompactGrid(Container parent, int rows, int cols, int initialX, int initialY, int xPad, int yPad) {
        SpringLayout layout = (SpringLayout) parent.getLayout();

        Spring x = Spring.constant(initialX);
        for (int c = 0; c < cols; c++) {
            Spring width = Spring.constant(0);
            for (int r = 0; r < rows; r++) {
                width = Spring.max(width, layout.getConstraints(parent.getComponent(r * cols + c)).getWidth());
            }
            for (int r = 0; r < rows; r++) {
                SpringLayout.Constraints constraints = layout.getConstraints(parent.getComponent(r * cols + c));
                constraints.setX(x);
                constraints.setWidth(width);
            }
            x = Spring.sum(x, Spring.sum(width, Spring.constant(xPad)));
        }

        Spring y = Spring.constant(initialY);
        for (int r = 0; r < rows; r++) {
            Spring height = Spring.constant(0);
            for (int c = 0; c < cols; c++) {
                height = Spring.max(height, layout.getConstraints(parent.getComponent(r * cols + c)).getHeight());
            }
            for (int c = 0; c < cols; c++) {
                SpringLayout.Constraints constraints = layout.getConstraints(parent.getComponent(r * cols + c));
                constraints.setY(y);
                constraints.setHeight(height);
            }
            y = Spring.sum(y, Spring.sum(height, Spring.constant(yPad)));
        }

        SpringLayout.Constraints constraints = layout.getConstraints(parent);
        constraints.setConstraint(SpringLayout.EAST, x);
        constraints.setConstraint(SpringLayout.SOUTH, y);
    }
}
// Author: Andrew Witten