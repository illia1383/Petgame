import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Pet{
        private int health;
        private int happiness;
        private int sleep;
        private int hunger;
        private boolean alive;
        private boolean hungry;
        private boolean tired;
        private boolean unhappy;
        private String name;
        private int birthDate;
        private int money;


    public Pet(String name, int health, int happiness, int sleep, int hunger){
        this.name = name;
        this.health = health;
        this.happiness = happiness;
        this.sleep = sleep;
        this.hunger = hunger;
     
    }
    
    public void updateStats(int healthChange, int happinessChange, int sleepChange, int hungerChange){
        this.health += healthChange;
        this.happiness += happinessChange;
        this.sleep += sleepChange;
        this.hunger += hungerChange;

        this.health = Math.max(0, Math.min(this.health, 100));
        this.happiness = Math.max(0, Math.min(this.happiness, 100));
        this.sleep = Math.max(0, Math.min(this.sleep, 100));
        this.hunger = Math.max(0, Math.min(this.hunger, 100));
    }
    
    public void render(){
        JFrame frame = new JFrame("Simple Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300); // Set the window size
        
        // Create the panel
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new BorderLayout()); // Set layout for the panel
        
        ImageIcon sprite = new ImageIcon("images/tile043.png"); // Replace with your image path
        JLabel spriteLabel = new JLabel(sprite);
        panel.add(spriteLabel, BorderLayout.CENTER);
        
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(0, 1)); // Single column for stats

        statsPanel.add(new JLabel("Name: " + name));
        statsPanel.add(new JLabel("Health: " + health));
        statsPanel.add(new JLabel("Happiness: " + happiness));
        statsPanel.add(new JLabel("Sleep: " + sleep));
        statsPanel.add(new JLabel("Hunger: " + hunger));
        panel.add(statsPanel, BorderLayout.SOUTH);

        
        frame.add(panel);
        frame.setVisible(true); // Make the frame visible

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
           Pet myPet = new Pet("Fluffy", 100, 80, 70, 50);
            myPet.render(); // Render the UI
           
        });
    }
}