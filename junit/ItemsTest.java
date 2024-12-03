/**
 * This class represents the test for items that can be bought and used on the pet
 *
 * Date: Nov 30 2024
 * @author James Wong
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemsTest {

    private Items foodItem;
    private Items happinessItem;
    private Pet testPet;

    @BeforeEach
    void setUp() {
        // Create a pet with initial stats
        testPet = new Pet("Test Pet", 50, 50, 50, 50, 100, "Dog");

        // Create test items
        foodItem = new Items("Food", 10, 5, 10, 0); // Food item with foodBoost of 10
        happinessItem = new Items("Toy", 15, 3, 0, 10); // Toy item with happinessBoost of 10
    }

    @Test
    void getName() {
        assertEquals("Food", foodItem.getName(), "Item name should be 'Food'");
        assertEquals("Toy", happinessItem.getName(), "Item name should be 'Toy'");
    }

    @Test
    void getPrice() {
        assertEquals(10, foodItem.getPrice(), "Food item price should be 10");
        assertEquals(15, happinessItem.getPrice(), "Toy item price should be 15");
    }

    @Test
    void getQuantity() {
        assertEquals(5, foodItem.getQuantity(), "Food item quantity should be 5");
        assertEquals(3, happinessItem.getQuantity(), "Toy item quantity should be 3");
    }

    @Test
    void getFoodBoost() {
        assertEquals(10, foodItem.getFoodBoost(), "Food item should have a food boost of 10");
        assertEquals(0, happinessItem.getFoodBoost(), "Toy item should have no food boost");
    }

    @Test
    void getHappyBoost() {
        assertEquals(0, foodItem.getHappyBoost(), "Food item should have no happiness boost");
        assertEquals(10, happinessItem.getHappyBoost(), "Toy item should have a happiness boost of 10");
    }

    @Test
    void use() {
        testPet.updateStats(0, 0, 0, 0); // Reset stats to base values
        testPet.addItem("Food", 1); // Add food item to inventory

        // Use food item
        foodItem.use(testPet);

        // After using food item, hunger should decrease by foodBoost value
        assertEquals(50, testPet.getHunger(), "Hunger should be reduced by 10 after using the food item");
    }

    @Test
    void getItemByName() {
        Items foundItem = Items.getItemByName("Food");
        assertNotNull(foundItem, "Item 'Food' should be found");
        assertEquals("Food", foundItem.getName(), "The found item should be 'Food'");

        Items notFoundItem = Items.getItemByName("NonExistent");
        assertNull(notFoundItem, "Item 'NonExistent' should not be found");
    }
}