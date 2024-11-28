<<<<<<< HEAD
public class Tutorial extends State{
    private PracticePet practicePet;
    public Tutorial(StateManager sg, PracticePet p) {
        super(sg);
        practicePet = p;
    }

=======
import java.awt.*;
import javax.swing.*;

public class Tutorial  {

    private StateManager statemanager;

    public Tutorial(StateManager sg) {
        statemanager = sg;
    }

    public void render() {
        // Create the main JFrame
        JFrame frame = new JFrame("Tutorial");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        // Show the tutorial pop-up
        showTutorialDialog(frame);

        // Set frame visible
        frame.setVisible(true);
    }

    private void showTutorialDialog(JFrame frame) {
        // Tutorial messages
        String[] tutorialMessages = {
            "Welcome to the game!",
            "The goal is to keep your pet alive and maintain their other needs.",
            "Interact with objects by pressing (Add the button they have to press) ",
            "You can buy items for your pet through the store, These items will help feed your pet and make them happy.",
            "Good luck and have fun!"
        };

        // Create JDialog for the tutorial
        JDialog tutorialDialog = new JDialog(frame, "Tutorial", true);
        tutorialDialog.setSize(300, 200);
        tutorialDialog.setLayout(new BorderLayout());
        tutorialDialog.setLocationRelativeTo(frame);

        // Add tutorial message using JTextArea for multi-line support
        JTextArea tutorialMessage = new JTextArea(tutorialMessages[0]);
        tutorialMessage.setFont(new Font("Arial", Font.PLAIN, 14));
        tutorialMessage.setWrapStyleWord(true);
        tutorialMessage.setLineWrap(true);
        tutorialMessage.setEditable(false);
        tutorialMessage.setOpaque(false);
        tutorialDialog.add(tutorialMessage, BorderLayout.CENTER);

        // Buttons for navigation
        JButton nextButton = new JButton("Next");
        JButton backButton = new JButton("Back");
        backButton.setEnabled(false); // Disable back button initially

        // Track current tutorial step
        final int[] currentStep = {0};

        // Button Actions
        nextButton.addActionListener(e -> {
            if (currentStep[0] < tutorialMessages.length - 1) {
                currentStep[0]++;
                tutorialMessage.setText(tutorialMessages[currentStep[0]]);
                backButton.setEnabled(true);
                if (currentStep[0] == tutorialMessages.length - 1) {
                    //After the finish button is pressed it will move the user to the game screen will have to add more functionality to this button 
                    nextButton.setText("Finish");
                }
            } else {
                tutorialDialog.dispose(); // Close dialog when finished
            }
        });

        backButton.addActionListener(e -> {
            if (currentStep[0] > 0) {
                currentStep[0]--;
                tutorialMessage.setText(tutorialMessages[currentStep[0]]);
                nextButton.setText("Next");
                if (currentStep[0] == 0) {
                    backButton.setEnabled(false);
                }
            }
        });

        // Add buttons to panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);
        tutorialDialog.add(buttonPanel, BorderLayout.SOUTH);

        // Make dialog visible
        tutorialDialog.setVisible(true);
    }

    // Main method for testing
    public static void main(String[] args) {
        // Create a dummy StateManager for testing
        StateManager dummyStateManager = new StateManager();

        // Create Tutorial instance and render it
        Tutorial tutorial = new Tutorial(dummyStateManager);
        tutorial.render();
    }
>>>>>>> petClass
}
