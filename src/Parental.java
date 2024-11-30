import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class Parental {
    private StateManager statemanager;
    private LocalTime restrictedStartTime = null;
    private LocalTime restrictedEndTime = null;

    public Parental(StateManager sg) {
        statemanager = sg;
    }

    public void render() {
        // Create the main JFrame for the parental page
        JFrame frame = new JFrame("Parental Controls");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(4, 1, 10, 10)); // Arrange buttons in a vertical grid
        frame.setLocationRelativeTo(null);

        // Login prompt before showing the frame
        if (!showLoginDialog(frame)) {
            // Close the application if login fails
            JOptionPane.showMessageDialog(null, "Access Denied. Closing application.");
            System.exit(0);
        }

        // Button to revive dead pets
        JButton reviveButton = new JButton("Revive Dead Pets");
        reviveButton.addActionListener(e -> {
            if (isPlayRestricted()) {
                JOptionPane.showMessageDialog(frame, "Play is restricted during this time!");
            } else {
                JOptionPane.showMessageDialog(frame, "Revive Dead Pets clicked!");
            }
        });

        // Button to view player stats
        JButton viewStatsButton = new JButton("View Player Stats");
        viewStatsButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "View Player Stats clicked!"));

        // Button to configure time control
        JButton timeControlButton = new JButton("Configure Time Control");
        timeControlButton.addActionListener(e -> showTimeConfigDialog(frame));

        // Button to exit and return to the main title page
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            frame.dispose(); // Close the parental page
            returnToTitlePage(); // Navigate back to the main title page
        });

        // Add buttons to the frame
        frame.add(reviveButton);
        frame.add(viewStatsButton);
        frame.add(timeControlButton);
        frame.add(exitButton);

        // Set frame visible
        frame.setVisible(true);
    }

    private boolean showLoginDialog(JFrame parentFrame) {
        // Create a modal dialog for login
        JDialog loginDialog = new JDialog(parentFrame, "Login", true);
        loginDialog.setSize(300, 150);
        loginDialog.setLayout(new GridLayout(3, 2, 10, 10));
        loginDialog.setLocationRelativeTo(parentFrame);

        // Add username and password fields
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        // Add buttons for login and cancel
        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");

        // Add components to the dialog
        loginDialog.add(usernameLabel);
        loginDialog.add(usernameField);
        loginDialog.add(passwordLabel);
        loginDialog.add(passwordField);
        loginDialog.add(loginButton);
        loginDialog.add(cancelButton);

        // Create an array to store the result
        final boolean[] isAuthenticated = {false};

        // Add action listeners
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if ("Parent".equals(username) && "cs2212".equals(password)) {
                isAuthenticated[0] = true;
                loginDialog.dispose(); // Close the dialog
            } else {
                JOptionPane.showMessageDialog(loginDialog, "Invalid credentials. Try again.");
            }
        });

        cancelButton.addActionListener(e -> {
            isAuthenticated[0] = false;
            loginDialog.dispose(); // Close the dialog
        });

        // Show the dialog
        loginDialog.setVisible(true);
        return isAuthenticated[0];
    }

    private void showTimeConfigDialog(JFrame parentFrame) {
        // Create a modal dialog for time configuration
        JDialog timeDialog = new JDialog(parentFrame, "Set Restricted Times", true);
        timeDialog.setSize(400, 200);
        timeDialog.setLayout(new GridLayout(3, 2, 10, 10));
        timeDialog.setLocationRelativeTo(parentFrame);

        // Add fields to set start and end times
        JLabel startTimeLabel = new JLabel("Start Time (HH:mm):");
        JTextField startTimeField = new JTextField();
        JLabel endTimeLabel = new JLabel("End Time (HH:mm):");
        JTextField endTimeField = new JTextField();

        // Add buttons to save and cancel
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        timeDialog.add(startTimeLabel);
        timeDialog.add(startTimeField);
        timeDialog.add(endTimeLabel);
        timeDialog.add(endTimeField);
        timeDialog.add(saveButton);
        timeDialog.add(cancelButton);

        saveButton.addActionListener(e -> {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                restrictedStartTime = LocalTime.parse(startTimeField.getText(), formatter);
                restrictedEndTime = LocalTime.parse(endTimeField.getText(), formatter);
                JOptionPane.showMessageDialog(timeDialog, "Restricted times set successfully.");
                timeDialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(timeDialog, "Invalid time format. Please use HH:mm.");
            }
        });

        cancelButton.addActionListener(e -> timeDialog.dispose());

        timeDialog.setVisible(true);
    }

    private boolean isPlayRestricted() {
        if (restrictedStartTime == null || restrictedEndTime == null) {
            return false; // No restriction set
        }
        LocalTime now = LocalTime.now();
        return now.isAfter(restrictedStartTime) && now.isBefore(restrictedEndTime);
    }

    private void returnToTitlePage() {
        // Logic to go back to the main title page
        System.out.println("Returning to the main title page...");
        Title title = new Title(statemanager);
        title.render();
    }

    // Main method for testing
    public static void main(String[] args) {
        // Dummy StateManager for testing
        StateManager dummyStateManager = new StateManager();

        // Create Parental instance and render it
        Parental parental = new Parental(dummyStateManager);
        parental.render();
    }
}

<<<<<<< Updated upstream
=======
// Dummy StateManager class for testing
class StateManager {
    // Add any necessary methods or properties here
}

// Dummy Title class for testing
class Title {
    private StateManager statemanager;

    public Title(StateManager sm) {
        statemanager = sm;
    }

    public void render() {
        System.out.println("Rendering the Title page...");
    }
}
>>>>>>> Stashed changes
