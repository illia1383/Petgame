import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

/**
 * This class represents the pet. 
 * It creates the inital object, updates the attributes number of the pet, and handles the inventory
 * There are also getters and setters for each variable.
 *
 * Date: Nov 28, 2024
 * @author Celia Chan
 */

public class Pet{
        /** Health attribute of the pet */
        private int health;
        /** Happiness attribute of the pet */
        private int happiness;
        /** Sleep attribute of the pet */
        private int sleep;
        /** Hunger attribute of the pet */
        private int hunger;
        /** Boolean for if the pet is alive */
        private boolean alive;
        /** Boolean for if the pet is hungry */
        private boolean hungry;
        /** Boolean for if the pet is tired */
        private boolean tired;
        /** Boolean for if the pet is happy */
        private boolean happy;
        /** The name of the pet */
        private String name;
        /** The birthdate of the pet */
        private String birthDate;
        /** How much money the pet currently has */
        private int money;
        /** Denotes if pet is a dog, cat or bear */
        private String type;
        /** The inventory that the pet holds */
        private HashMap<Items, Integer> inventory;
        // Dictionary for the inventory
        // Key = item and then the value would be the number of inventory would be the 1

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
     * Checks if the pet is alive
     * @param health
     * @return True if the pet is alive, false otherwise
     */
    public boolean spriteIsAlive(){
        if (this.health < 30){
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
    public boolean spriteIsHungry(){
        if (this.hunger < 30){
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
    public boolean spriteIsTired(){
        if (this.sleep < 10){
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
    public boolean spriteIsHappy(){
        if (this.happiness < 40){
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

    /**
     * 
     * @return the type of animal the pet is
     */
    public String getType(){
        return type;
    }

    /**
     * Sets the type of animal the pet is
     * @param type
     */
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
        inventory.put(item, inventory.getOrDefault(item, 0) + quantity);
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
     * Default implementation for render, used in the child classes
     * @param frame
     * @param panel
     */
    public void render(JFrame frame, JPanel panel){
        this.frame = frame;
        this.panel = panel;
    
    }

    /**
     * Default implementation for updateSprite, used in the child classes
     * @param panel
     */
    public void updateSprite(JPanel panel, boolean flipped){
        //Default Implementation; Won't do anything by itself.
    }
}