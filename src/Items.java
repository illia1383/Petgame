/**
 * This class represents the items that can be bought and used on the pet
 *
 * Date: Nov 19 2024
 * @author James Wong
 */

import java.util.ArrayList;
import java.util.List;

public class Items {

    /** Attributes **/
    private String name;
    private int price;
    private int quantity;
    private int foodBoost;
    private int happyBoost;

    // Static list of all items
    private static List<Items> allItems = new ArrayList<>();

    /**
     * Items
     * @param name is the name of the item
     * @param price is the cost of the item
     * @param quantity is the amount of an item as a number
     * @param foodBoost is how much the item increases hunger
     * @param happyBoost is how happy the item makes the pet
     */
    public Items(String name, int price, int quantity, int foodBoost, int happyBoost) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.foodBoost = foodBoost;
        this.happyBoost = happyBoost;

        // Add item to the list
        allItems.add(this);
    }

    /**
     * getName
     *
     * @return name
     */
    public String getName() {return name;}

    /**
     * getPrice
     * @return price
     */
    public int getPrice() {return price;}

    /**
     * getQuantity
     * @return quantity
     */
    public int getQuantity() {return quantity;}

    /**
     * getFoodBoost
     * @return foodBoost
     */
    public int getFoodBoost() {return foodBoost;}

    /**
     * getHappyBoost
     * @return happyBoost
     */
    public int getHappyBoost() {return happyBoost;}

    /**
     * use
     * @param pet is the pet we are currently working with
     */
    public void use(Pet pet) {
        // Check if the item boosts hunger (food boost)
        if (foodBoost > 0) {
            pet.updateStats(0, 0, 0, foodBoost); // Apply food boost to reduce hunger
        } else if (foodBoost > 0) {
            System.out.println("Pet is not hungry, no food boost applied.");
        }

        // Check if the item boosts happiness (happiness boost)
        if (happyBoost > 0) {
            pet.updateStats(0, happyBoost, 0, 0); // Boost happiness if the pet is unhappy
        } else if (happyBoost > 0) {
            System.out.println("Pet is already happy, no happiness boost applied.");
        }
    }

    /**
     * Retrieves an item by name
     * @param name
     * @return the matching item, otherwise, null if no match is found
     */
    public static Items getItemByName(String name) {
        for (Items item : allItems) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item; // Return the matching item
            }
        }
        return null; // Return null if no match is found
    }
}
