<<<<<<< HEAD
public class Parental extends State {
    public Parental(StateManager sg) {
        super(sg);
    }

    public void render() {
        System.out.println("\n\n\n\n----Parental Control Settings----");
        System.out.println("1) View Stats");
        System.out.println("2) Time Controls");
        System.out.println("3) Revive Pets");
        System.out.println("4) Exit");
    }

    public void update() {

    }


}
=======
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Parental {
    private StateManager statemanager;

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

        // Button to revive dead pets
        JButton reviveButton = new JButton("Revive Dead Pets");
        reviveButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Revive Dead Pets clicked!"));

        // Button to view player stats
        JButton viewStatsButton = new JButton("View Player Stats");
        viewStatsButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "View Player Stats clicked!"));

        // Button to configure time control
        JButton timeControlButton = new JButton("Configure Time Control");
        timeControlButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Configure Time Control clicked!"));

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

    private void returnToTitlePage() {
        // Logic to go back to the main title page
        System.out.println("Returning to the main title page...");
        Title title = new Title(statemanager);
        title.render();
        // Example: you might call `stateManager.setState(new TitlePage(stateManager));`
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

>>>>>>> petClass
