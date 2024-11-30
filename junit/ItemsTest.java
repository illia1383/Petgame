import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PetTest {

    @Test
    void testPetStatsInitialization() {
        // Create a Pet instance
        Pet pet = new Pet("Fluffy", 100, 80, 70, 50, 0, "Cat");

        // Assert that the pet's stats are initialized correctly
        assertEquals(100, pet.getHealth());
        assertEquals(80, pet.getHappiness());
        assertEquals(70, pet.getSleep());
        assertEquals(50, pet.getHunger());
        assertEquals(0, pet.getMoney());
        assertTrue(pet.isAlive());
    }

    @Test
    void testUpdateStatsWithValidValues() {
        // Create a Pet instance
        Pet pet = new Pet("Fluffy", 100, 80, 70, 50, 0, "Cat");

        // Update stats
        pet.updateStats(-10, 5, -20, 10);

        // Assert the stats have been updated and constrained within 0-100 range
        assertEquals(90, pet.getHealth());
        assertEquals(85, pet.getHappiness());
        assertEquals(50, pet.getSleep());
        assertEquals(60, pet.getHunger());
    }

    @Test
    void testUpdateStatsWithMaxValues() {
        // Create a Pet instance
        Pet pet = new Pet("Fluffy", 100, 100, 100, 100, 0, "Cat");

        // Update stats with values that exceed 100
        pet.updateStats(20, 10, 5, 5);

        // Assert that the values stay within 0-100 range
        assertEquals(100, pet.getHealth());
        assertEquals(100, pet.getHappiness());
        assertEquals(100, pet.getSleep());
        assertEquals(100, pet.getHunger());
    }

    @Test
    void testUpdateStatsWithMinValues() {
        // Create a Pet instance
        Pet pet = new Pet("Fluffy", 10, 20, 30, 40, 0, "Cat");

        // Update stats with negative values that would reduce stats below 0
        pet.updateStats(-20, -30, -40, -50);

        // Assert that the values stay within 0-100 range
        assertEquals(0, pet.getHealth());
        assertEquals(0, pet.getHappiness());
        assertEquals(0, pet.getSleep());
        assertEquals(0, pet.getHunger());
    }

    @Test
    void testPetIsAliveWhenHealthIsZero() {
        // Create a Pet instance
        Pet pet = new Pet("Fluffy", 0, 50, 50, 50, 0, "Cat");

        // Assert that the pet is not alive when health is zero
        assertFalse(pet.isAlive());
    }

    @Test
    void testPetIsAliveWhenHealthIsAboveZero() {
        // Create a Pet instance
        Pet pet = new Pet("Fluffy", 50, 50, 50, 50, 0, "Cat");

        // Assert that the pet is alive when health is above zero
        assertTrue(pet.isAlive());
    }

    @Test
    void testPetIsHungryWhenHungerIsZero() {
        // Create a Pet instance
        Pet pet = new Pet("Fluffy", 50, 50, 50, 0, 0, "Cat");

        // Assert that the pet is hungry when hunger is zero
        assertTrue(pet.isHungry());
    }

    @Test
    void testPetIsNotHungryWhenHungerIsAboveZero() {
        // Create a Pet instance
        Pet pet = new Pet("Fluffy", 50, 50, 50, 30, 0, "Cat");

        // Assert that the pet is not hungry when hunger is above zero
        assertFalse(pet.isHungry());
    }

    @Test
    void testAddItemToInventory() {
        // Create a Pet instance
        Pet pet = new Pet("Fluffy", 100, 80, 70, 50, 0, "Cat");

        // Add items to the inventory
        pet.addItem("Apple", 5);

        // Assert that the item has been added to the inventory
        assertTrue(pet.getInventory().containsKey(Items.getItemByName("Apple")));
        assertEquals(5, pet.getInventory().get(Items.getItemByName("Apple")));
    }

    @Test
    void testRemoveItemFromInventory() {
        // Create a Pet instance
        Pet pet = new Pet("Fluffy", 100, 80, 70, 50, 0, "Cat");

        // Add an item and remove it
        pet.addItem("Apple", 5);
        boolean result = pet.removeItem(Items.getItemByName("Apple"), 3);

        // Assert that the item was removed correctly
        assertTrue(result);
        assertEquals(2, pet.getInventory().get(Items.getItemByName("Apple")));
    }

    @Test
    void testRemoveMoreItemsThanAvailable() {
        // Create a Pet instance
        Pet pet = new Pet("Fluffy", 100, 80, 70, 50, 0, "Cat");

        // Add an item and attempt to remove more than available
        pet.addItem("Apple", 5);
        boolean result = pet.removeItem(Items.getItemByName("Apple"), 6);

        // Assert that the removal failed
        assertFalse(result);
    }

    @Test
    void testRemoveItemNotInInventory() {
        // Create a Pet instance
        Pet pet = new Pet("Fluffy", 100, 80, 70, 50, 0, "Cat");

        // Attempt to remove an item that isn't in the inventory
        boolean result = pet.removeItem(Items.getItemByName("Banana"), 1);

        // Assert that the removal failed
        assertFalse(result);
    }

    @Test
    void testSetMoney() {
        // Create a Pet instance
        Pet pet = new Pet("Fluffy", 100, 80, 70, 50, 0, "Cat");

        // Set money and verify it
        pet.setMoney(10);
        assertEquals(10, pet.getMoney());

        // Subtract money and verify it doesn't go below zero
        pet.setMoney(-15);
        assertEquals(0, pet.getMoney());
    }
}
