import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class ShopTest {
    private Pet testPet;
    private Shop shop;
    private StateManager dummyStateManager;

    @BeforeEach
    public void setUp() {
        // Initialize the test pet
        testPet = new Pet("Fluffy", 100, 80, 70, 50, 100, "Dog");

        // Create a dummy StateManager
        dummyStateManager = new StateManager();

        // Initialize the Shop
        shop = new Shop(dummyStateManager, testPet);

        // Create items (populates Items.allItems)
        new Items("Teddy Bear", 100, 1, 0, 20);
        new Items("Soccerball", 100, 1, 0, 40);
        new Items("Chocolate", 200, 1, 10, 5);
        new Items("Carrot", 150, 1, 30, 10);
        new Items("Apple", 150, 1, 40, 10);
    }

    @Test
    public void testBuyItemSuccessfully() {
        // Simulate buying the first item (Teddy Bear)
        int initialMoney = testPet.getMoney();
        Items teddyBear = Items.getItemByName("Teddy Bear");

        // Ensure the item exists
        assertNotNull(teddyBear, "Teddy Bear should exist in the shop.");

        // Check the initial state of the pet's inventory
        assertFalse(testPet.getInventory().containsKey(teddyBear.getName()), "Pet's inventory should not initially contain the item.");

        // Simulate the purchase
        shop.buy(0); // Buy the first item

        // Verify money deduction
        assertEquals(initialMoney - teddyBear.getPrice(), testPet.getMoney(), "Money should decrease by the item's price.");

        // Verify inventory update
        assertTrue(testPet.getInventory().containsKey(teddyBear.getName()), "Inventory should contain the purchased item.");
        assertEquals(1, testPet.getInventory().get(teddyBear.getName()), "Inventory should reflect the correct quantity.");
    }

    @Test
    public void testBuyItemNotEnoughMoney() {
        // Set the pet's money below the price of the first item
        testPet.setMoney(10);
        Items teddyBear = Items.getItemByName("Teddy Bear");

        // Ensure the item exists
        assertNotNull(teddyBear, "Teddy Bear should exist in the shop.");

        // Attempt to buy the item
        shop.buy(0); // Buy the first item

        // Verify that the pet's money and inventory remain unchanged
        assertEquals(10, testPet.getMoney(), "Money should remain unchanged due to insufficient funds.");
        assertFalse(testPet.getInventory().containsKey(teddyBear.getName()), "Inventory should not contain the item.");
    }

    @Test
    public void testBuyInvalidItemIndex() {
        // Attempt to buy an item at an invalid index
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> shop.buy(999), "An invalid index should throw an exception.");
    }

    @Test
    public void testRenderShop() {
        // Render the shop window
        SwingUtilities.invokeLater(() -> shop.render());

        // Verify that the shop window is visible
        assertNotNull(shop.getShopWindow(), "Shop window should be created and not null.");
        assertTrue(shop.getShopWindow().isVisible(), "Shop window should be visible.");
    }
}
