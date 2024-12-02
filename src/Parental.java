import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import java.util.List;

/**
 * The {@code Parental} class implements a parental control system
 * with functionalities like reviving pets, viewing stats, configuring restricted play times,
 * and managing other parental controls using a graphical user interface.
 */
public class Parental {
    private StateManager statemanager;
    private LocalTime restrictedStartTime = null;
    private LocalTime restrictedEndTime = null;

    /**
     * Constructs a {@code Parental} object with a specified state manager.
     *
     * @param sg the {@code StateManager} instance to manage state transitions
     */
    public Parental(StateManager sg) {
        this.statemanager = sg;
    }

    /**
     * Renders the parental control interface with buttons for various features.
     * A login dialog is displayed before granting access.
     */
    public void render() {
        JFrame frame = new JFrame("Parental Controls");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(4, 1, 10, 10));
        frame.setLocationRelativeTo(null);

        if (!showLoginDialog(frame)) {
            JOptionPane.showMessageDialog(null, "Access Denied. Closing application.");
            System.exit(0);
        }

        JButton reviveButton = new JButton("Revive Dead Pets");
        reviveButton.addActionListener(e -> handleRevivePets(frame));

        JButton viewStatsButton = new JButton("View Player Stats");
        viewStatsButton.addActionListener(e -> handleViewPlayerStats(frame));

        JButton timeControlButton = new JButton("Configure Time Control");
        timeControlButton.addActionListener(e -> showTimeConfigDialog(frame));

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            frame.dispose();
            returnToTitlePage();
        });

        frame.add(reviveButton);
        frame.add(viewStatsButton);
        frame.add(timeControlButton);
        frame.add(exitButton);

        frame.setVisible(true);
    }

    /**
     * Displays a login dialog for user authentication.
     *
     * @param parentFrame the parent frame for the login dialog
     * @return {@code true} if login is successful, {@code false} otherwise
     */
    private boolean showLoginDialog(JFrame parentFrame) {
        JDialog loginDialog = new JDialog(parentFrame, "Login", true);
        loginDialog.setSize(300, 150);
        loginDialog.setLayout(new GridLayout(3, 2, 10, 10));
        loginDialog.setLocationRelativeTo(parentFrame);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");

        loginDialog.add(usernameLabel);
        loginDialog.add(usernameField);
        loginDialog.add(passwordLabel);
        loginDialog.add(passwordField);
        loginDialog.add(loginButton);
        loginDialog.add(cancelButton);

        final boolean[] isAuthenticated = {false};

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if ("Parent".equals(username) && "cs2212".equals(password)) {
                isAuthenticated[0] = true;
                loginDialog.dispose();
            } else {
                JOptionPane.showMessageDialog(loginDialog, "Invalid credentials. Try again.");
            }
        });

        cancelButton.addActionListener(e -> {
            isAuthenticated[0] = false;
            loginDialog.dispose();
        });

        loginDialog.setVisible(true);
        return isAuthenticated[0];
    }

    /**
     * Handles reviving pets by updating their health in the save files.
     *
     * @param frame the parent frame to display dialogs
     */
    private void handleRevivePets(JFrame frame) {
        String[] filePaths = {"src/Save1.txt", "src/Save2.txt", "src/Save3.txt"};
        boolean revivedAnyPet = false;

        for (String filePath : filePaths) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(filePath));
                if (lines.size() >= 3) {
                    int petHealth = Integer.parseInt(lines.get(2).trim());
                    if (petHealth == 0) {
                        lines.set(2, "100");
                        revivedAnyPet = true;
                    }
                }
                Files.write(Paths.get(filePath), lines);
            } catch (IOException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Error processing file " + filePath + ": " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (revivedAnyPet) {
            JOptionPane.showMessageDialog(frame, "All dead pets have been revived!");
        } else {
            JOptionPane.showMessageDialog(frame, "No dead pets found.");
        }
    }

    /**
     * Handles viewing player statistics from a file.
     *
     * @param frame the parent frame to display dialogs
     */
    private void handleViewPlayerStats(JFrame frame) {
        String filePath = "src/timeStats.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String avgTime = reader.readLine();
            String totalTime = reader.readLine();

            if (avgTime != null && totalTime != null) {
                String message = "Average Time: " + avgTime + "\nTotal Time: " + totalTime;
                JOptionPane.showMessageDialog(frame, message, "Player Stats", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "File is empty or incomplete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error reading file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Displays a dialog for configuring restricted play times.
     *
     * @param parentFrame the parent frame for the dialog
     */
    private void showTimeConfigDialog(JFrame parentFrame) {
        JDialog timeDialog = new JDialog(parentFrame, "Set Restricted Times", true);
        timeDialog.setSize(400, 200);
        timeDialog.setLayout(new GridLayout(3, 2, 10, 10));
        timeDialog.setLocationRelativeTo(parentFrame);

        JLabel startTimeLabel = new JLabel("Start Time (HH:mm):");
        JTextField startTimeField = new JTextField();
        JLabel endTimeLabel = new JLabel("End Time (HH:mm):");
        JTextField endTimeField = new JTextField();

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

    /**
     * Checks if the current time falls within the restricted play period.
     *
     * @return {@code true} if play is restricted, {@code false} otherwise
     */
    private boolean isPlayRestricted() {
        if (restrictedStartTime == null || restrictedEndTime == null) {
            return false;
        }
        LocalTime now = LocalTime.now();
        return now.isAfter(restrictedStartTime) && now.isBefore(restrictedEndTime);
    }

    /**
     * Navigates back to the main title page and closes the parental control interface.
     */
    private void returnToTitlePage() {
        System.out.println("Returning to the main title page...");
        Title title = new Title(statemanager);
        title.render();
    }
}
