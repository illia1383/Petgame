package src;
import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.DateFormat;


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
        private String birthDate;
        private int money;


    /**
     * The constructor of the pet, given the following parameters, the pet is made.
     * @param name
     * @param health
     * @param happiness
     * @param sleep
     * @param hunger
     * @param money
     */
    public Pet(String name, int health, int happiness, int sleep, int hunger, int money){
        this.name = name;
        this.health = health;
        this.happiness = happiness;
        this.sleep = sleep;
        this.hunger = hunger;
        this.birthDate = setBirthDate();
        this.money = 0;
        this.alive = true;
        this.hungry = false;
        this.tired = false;
        this.unhappy = false;
     
    }

    /**
     * Gets the birthday of the pet
     * @return The birthday
     */
    private String setBirthDate(){
        Date birthday = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
        String dateString = dateFormat.format(birthday);
        return dateString;
    } 

    public String getBirthDate(){
        return this.birthDate;
    }

    public String getName(){
        return this.name;
    }

    /**
     * Updates the stats of the pet by the given number and puts a restraint on it so it can't go past 100 or 0
     * @param healthChange
     * @param happinessChange
     * @param sleepChange
     * @param hungerChange
     */
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

    public int getHealth(){
        return this.health;
    }
    
    public int getHappiness(){
        return this.happiness;
    }

    public int getSleep(){
        return this.sleep;
    }

    public int getHunger(){
        return this.hunger;
    }

    /**
     * Checks if the pet is alive
     * @param health
     * @return True if the pet is alive, false otherwise
     */
    public boolean isAlive(){
        if (this.health == 0){
            this.alive = true;
        }
        else this.alive = false;
        return alive;
    }

    /**
     * Checks if the pet is hungry
     * @param hunger
     * @return True if the pet is hungry, false otherwise
     */
    public boolean isHungry(){
        if (this.hunger < 100){
            this.hungry = true;
            }
        else this.hungry = false;
        return hungry;
    }

    /**
     * Checks if the pet is tired
     * @param sleep
     * @return True if the pet is tired, false otherwise
     */
    public boolean isTired(){
        if (this.sleep < 100){
            this.tired = true;
            }
        else this.tired = false;
        return tired;
    }
    
    /**
     * Checks if the pet is happy
     * @param happiness
     * @return True if the pet is unhappy, false otherwise
     */
    public boolean isHappy(){
        if (this.happiness < 100){
            this.unhappy = true;
            }
        else this.unhappy = false;
        return unhappy;
    }

    public int getMoney(){
        return this.money;
    }

    public void setMoney(int changeAmount){
        this.money += changeAmount;
    }

    
    // Not finalized, just testing out how to use Jframes and panels.
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
           Pet myPet = new Pet("Fluffy", 100, 80, 70, 50, 0);
            myPet.render(); // Render the UI
           
        });
    }
}