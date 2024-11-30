import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

/**
 * This class represents the virtual pet. This class handles pet attributes： health, happiness, sleep, hunger, inventory, type and birthdate.
 * It also renders the pet.
 * 
 * Date: Nov 28, 2024
 * @author Celia Chan
 */

public class Pet{
        /*
         * 
         */
        private int health;
        private int happiness;
        private int sleep;
        private int hunger;
        private boolean alive;
        private boolean hungry;
        private boolean tired;
        private boolean happy;
        private String name;
        private String birthDate;
        private int money;
        private String type;
        private HashMap<Items, Integer> inventory;
        // Dictionary for the inventory
        // Key = item and then the value would be the number of inventory would be the 1

        // GUI Labels for pet statistics
        private JLabel healthLabel;
        private JLabel happinessLabel;
        private JLabel sleepLabel;
        private JLabel hungerLabel;
        private JLabel moneyLabel;

        // GUI Main Screen Frame
        private JFrame frame;
        private JPanel panel;
    /**
     * The constructor of the pet, given the following parameters, the pet is made.
     * @param name
     * @param health
     * @param happiness
     * @param sleep
     * @param hunger
     * @param money
     */
    public Pet(String name, int health, int happiness, int sleep, int hunger, int money, String type){
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
        this.happy = true;
        this.type = type;
        this.inventory = new HashMap<>();

        // Intialize Frame and Panel: Once main class is finished i might need to set the frame to equal to the given frame
        frame = new JFrame("Simple Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300); // Set the window size
        
        // Create the panel
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new BorderLayout()); // Set layout for the panel

        healthLabel = new JLabel(String.valueOf(getHealth()));
        happinessLabel = new JLabel(String.valueOf(getHappiness()));
        sleepLabel = new JLabel(String.valueOf(getSleep()));
        hungerLabel = new JLabel(String.valueOf(getHunger()));
        moneyLabel = new JLabel(String.valueOf(getMoney()));
    }

    /**
     * Updates the stats of the pet by the given number and puts a restraint on it so it can't go past 100 or 0 (This is basically a setter)
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

        refreshStats(); // Refresh the stats
        
        render(frame,panel); // Re-render frame(when stats are updated)
    }

    /**
     * Gets the name of the pet
     * @return the name of the pet
     */
    public String getName(){
        return this.name;
    }

      /**
     * Checks if the pet is alive
     * @param health
     * @return True if the pet is alive, false otherwise
     */
    public boolean isAlive(){
        if (this.health == 0){
            this.alive = false;
        }
        else this.alive = true;
        return alive;
    }

    /**
     * Checks if the pet is hungry
     * @param hunger
     * @return True if the pet is hungry, false otherwise
     */
    public boolean isHungry(){
        if (this.hunger == 0){
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
        if (this.sleep == 0){
            this.tired = true;
            }
        else this.tired = false;
        return tired;
    }
    
    /**
     * Checks if the pet is happy
     * @param happiness
     * @return False the pet is unhappy, True otherwise
     */
    public boolean isHappy(){
        if (this.happiness == 0){
            this.happy = false;
            }
        else this.happy = true;
        return happy;
    }

    /**
     * 
     * @return the value of health of the pet
     */
    public int getHealth(){
        return this.health;
    }
    
    /**
     * 
     * @return the value of happiness of the pet
     */
    public int getHappiness(){
        return this.happiness;
    }

    /**
     * 
     * @return the value of sleep of the pet
     */
    public int getSleep(){
        return this.sleep;
    }

    /**
     * 
     * @return the value of hunger of the pet
     */
    public int getHunger(){
        return this.hunger;
    }

    /**
     * 
     * @return the current money for the pet
     */
    public int getMoney(){
        return this.money;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }
    /**
     * Sets the amount of money for the pet (Can be used in the shop to remove money from the pet just make it negative)
     * @param changeAmount
     */
    public void setMoney(int changeAmount){
        this.money += changeAmount;
        this.money = Math.max(0,this.money);

        refreshStats();
    }

    /**
     * 
     * @return the birthdate of the pet (The date the pet was created)
     */
    public String getBirthDate(){
        return this.birthDate;
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
    /**
     * Gets the current inventory of the pet
     * @return the inventory and the quantity of the item
     */
    public HashMap<Items, Integer> getInventory(){
        return inventory;
    }

    /**
     * Adds inventory to the current inventory of the pet
     * @param itemName
     * @param quantity
     */
    public void addItem(String itemName, int quantity) {
        Items item = Items.getItemByName(itemName); // Find the item by its name
        if (item != null) {
            inventory.put(item, inventory.getOrDefault(item, 0) + quantity);
        } else {
            System.out.println("Error: Item " + itemName + " not found.");
        }
        // Puts the inventory into the HashMap
        // getOrDefault basically gets the current quantity of the item or if it doesn't already exist in the inventory it defaults the value as 0
        inventory.put(item, inventory.getOrDefault(itemName, 0) + quantity);
    }

    /**
     * Removes the item from the current inventory of the pet
     * @param itemName
     * @param quantity
     * @return True if the item was successfully removed or false if the quantity exceeded the amount of item it could delete or if there is no item with that name in the dictionary 
     */
    public boolean removeItem(Items itemName, int quantity){
        // If the item does not exist within the hashmap or if the quantity is more than there is inventory
        if(!inventory.containsKey(itemName) || inventory.get(itemName) < quantity){
            return false; // Cannot remove the item
        }
        inventory.put(itemName, inventory.get(itemName) - quantity); // Subtract the amount that needs to be removed from the inventory
        if(inventory.get(itemName) == 0){ // If there are no more inventory, then the entire item should be removed from the inventory
            inventory.remove(itemName);
        }
        return true; // Return true that the item has been successfully removed.
    }

    /**
     * Gets the frame from the main game (May be just for testing)
     * @return the frame
     */
    public JFrame getFrame(){
        return frame;
    }
    /**
     * Gets the panel from the main game (May be just for testing)
     * @return the panel
     */
    public JPanel getPanel(){
        return panel;
    }
   
    // Not finalized, just testing out how to use Jframes and panels. 
    public void render(JFrame frame, JPanel panel){
   
        
        // ImageIcon sprite = new ImageIcon("images/bearcatdog.png"); // Replace with your image path
        // JLabel spriteLabel = new JLabel(sprite);
        // panel.add(spriteLabel, BorderLayout.CENTER);
        
        // For my update stats (Sprite change) method
        this.frame = frame;
        this.panel = panel; 

        // Invisible stats to keep track of it.
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(0, 1)); // Single column for stats
        
        // I don't know how much I need these renders either
        statsPanel.add(new JLabel(getName()));
        statsPanel.add(healthLabel);
        statsPanel.add(happinessLabel);
        statsPanel.add(sleepLabel);
        statsPanel.add(hungerLabel);
        statsPanel.add(moneyLabel);
        statsPanel.add(new JLabel("Birthday: " + getBirthDate()));
        panel.add(statsPanel, BorderLayout.SOUTH);
        

        
        frame.add(panel);
        frame.setVisible(true); // Make the frame visible
        statsPanel.setVisible(false); // Make the stats invisible
    }

    /**
     * This method refreshes the stats of the pet when it is changed
     */
    private void refreshStats() {
        // Update the text of the labels
        healthLabel.setText(String.valueOf(getHealth()));
        happinessLabel.setText(String.valueOf(getHappiness()));
        sleepLabel.setText(String.valueOf(getSleep()));
        hungerLabel.setText(String.valueOf(getHunger()));
        moneyLabel.setText(String.valueOf(getMoney()));
    }

    // // Testing I don't think this is needed
    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(() -> {
    //         //  Pet myPet = new Pet("Fluffy", 100, 80, 70, 50, 0);
    //         //myPet.render(myPet.frame); // Render the UI
            
            
    //         Dog myDog = new Dog("Dumpling");
    //         myDog.render(myDog.getFrame(),myDog.getPanel());

    //         Cat myCat = new Cat("Fish");
    //         myCat.render(myCat.getFrame(),myCat.getPanel());
    //         Bear myBear = new Bear("Berry");
    //         myBear.render(myBear.getFrame(),myBear.getPanel());
    //         }
    //     );
    // }
}