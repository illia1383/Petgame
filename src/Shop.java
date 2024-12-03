/**
 * This class represents the shop where the user can purchase items for their pet.
 *
 * Date: Nov 19, 2024
 * @author James Wong
 */

import javax.swing.*;
import java.awt.*;

public class Shop extends State {
    private Pet pet; // The pet associated with the shop
    private Items[] items; // Array to store available items
    private int shopSize; // Size of the shop
    private StateManager stateManager;
    private JFrame shopWindow;

    /**
     * Shop
     * @param stateManager manages the states
     * @param pet is the pet we are currently working with
     */
    public Shop(StateManager stateManager, Pet pet) {
        super(stateManager);
        this.pet = pet;
        this.stateManager = stateManager;
        setShop(); // Initialize shop items
    }

    /**
     * Render the shop window
     */
    public void render() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = (gd.getDisplayMode().getWidth()) / 2; // Display on only half the screen
        int height = gd.getDisplayMode().getHeight();

        shopWindow = new JFrame("Shop");
        shopWindow.setSize(width, height);
        shopWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        shopWindow.setLayout(new GridLayout(shopSize + 1, 1)); // Add rows for items and the title

        // Title Label
        JLabel shopLabel = new JLabel("Welcome to the Shop! Select an item to purchase:");
        shopLabel.setHorizontalAlignment(SwingConstants.CENTER);
        shopWindow.add(shopLabel);

        // Add each item as a button
        for (int i = 0; i < shopSize; i++) {
            Items item = items[i];

            // Create a button for the item
            JButton itemButton = new JButton(
                    item.getName() + " - Price: " + item.getPrice() +
                            " | Food Boost: " + item.getFoodBoost() +
                            " | Happiness Boost: " + item.getHappyBoost()
            );

            // Set action listener for buying items
            final int index = i;
            itemButton.addActionListener(e -> buy(index));
            shopWindow.add(itemButton);
        }

        shopWindow.setVisible(true);
    }

    /**
     * Update the shop after an item is purchased
     */
    public void update() {
        if (shopWindow != null) {
            shopWindow.dispose(); // Close current shop window
            render(); // Re-render shop
        }
    }

    /**
     * Handle item purchase
     *
     * @param itemIndex The index of the item to buy
     */
    public void buy(int itemIndex) {
        Items selectedItem = items[itemIndex];
        if (pet.getMoney() >= selectedItem.getPrice()) { // Check if pet has enough money
            pet.setMoney(-selectedItem.getPrice()); // Deduct item price
            selectedItem.use(pet); // Apply item's effects to the pet

            // Update the quantity of the purchased item in the pet's inventory
            pet.addItem(selectedItem.getName(), 1); // Increment quantity by 1
            JOptionPane.showMessageDialog(null, "You bought " + selectedItem.getName() + "!");
        } else {
            JOptionPane.showMessageDialog(null, "You don't have enough money to buy " + selectedItem.getName() + ".");
        }
        update(); // Update the shop to reflect changes
    }

    /**
     * Set up the shop items
     */
    private void setShop() {
        shopSize = 5; // Number of items in the shop
        items = new Items[shopSize]; // Initialize items array

        // Add items to the shop
        items[0] = new Items("Teddy Bear", 50, 1, 0, 20);
        items[1] = new Items("Soccerball", 50, 1, 0, 40);
        items[2] = new Items("Chocolate", 20, 1, 10, 5);
        items[3] = new Items("Carrot", 50, 1, 30, 10);
        items[4] = new Items("Apple", 50, 1, 40, 10);
    }

    /**
     * Gets the JFrame representing the shop window.
     *
     * @return the shop window JFrame, or null if the window hasn't been created yet.
     */
    public JFrame getShopWindow() {
        return shopWindow;
    }
}
