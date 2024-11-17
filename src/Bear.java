package src;
import javax.swing.*;
import java.awt.*;


public class Bear extends Pet{ 
    public Bear(String name){
        super(name, 70, 60, 100, 100, 0);
    }

    @Override
    // Not finalized, just testing out how to use Jframes and panels.
    public void render(){
        JFrame frame = new JFrame("Simple Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300); // Set the window size
        
        // Create the panel
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new BorderLayout()); // Set layout for the panel
        
        // Replace with a Bear Image
        // ImageIcon sprite = new ImageIcon("images/tile043.png"); // Replace with your image path
        // JLabel spriteLabel = new JLabel(sprite);
        // panel.add(spriteLabel, BorderLayout.CENTER);
        
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(0, 1)); // Single column for stats

        statsPanel.add(new JLabel("Name: " + getName()));
        statsPanel.add(new JLabel("Health: " + getHealth()));
        statsPanel.add(new JLabel("Happiness: " + getHappiness()));
        statsPanel.add(new JLabel("Sleep: " + getSleep()));
        statsPanel.add(new JLabel("Hunger: " + getHunger()));
        statsPanel.add(new JLabel("Birthday: " + getBirthDate()));
        panel.add(statsPanel, BorderLayout.SOUTH);

        
        frame.add(panel);
        frame.setVisible(true); // Make the frame visible

    }

}
