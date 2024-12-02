import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

public class TestPet {
    @Test
    public void testPetInitialization(){
        Pet pet = new Pet("Fish", 80, 90, 70, 60, 100, "Cat");

        assertEquals("Fish", pet.getName());
        assertEquals(80, pet.getHealth());
        assertEquals(90, pet.getHappiness());
        assertEquals(70, pet.getSleep());
        assertEquals(60, pet.getHunger());
        assertEquals(0, pet.getMoney()); // Default money is 0
        assertEquals("Cat", pet.getType());
        assertNotNull(pet.getBirthDate()); // Ensure birth date is set
    }

    @Test
    public void testUpdateStats() {
        Pet pet = new Pet("Oreo", 50, 50, 50, 50, 0, "Dog");
        pet.updateStats(30, -20, 10, -60);

        assertEquals(80, pet.getHealth()); // 50 + 30
        assertEquals(30, pet.getHappiness()); // 50 - 20
        assertEquals(60, pet.getSleep()); // 50 + 10
        assertEquals(0, pet.getHunger()); // 50 - 60 but clamped to 0
    }

    @Test
    public void testUpdateStatsLimits() {
        Pet pet = new Pet("Oreo", 50, 50, 50, 50, 0, "Dog");
        
        // Over 100
        pet.updateStats(100, 100, 100, 100);

        assertEquals(100, pet.getHealth()); 
        assertEquals(100, pet.getHappiness()); 
        assertEquals(100, pet.getSleep()); 
        assertEquals(100, pet.getHunger()); 

        // Under 100
        pet.updateStats(-100, -100, -100, -100);
        assertEquals(0, pet.getHealth()); 
        assertEquals(0, pet.getHappiness()); 
        assertEquals(0, pet.getSleep()); 
        assertEquals(0, pet.getHunger()); 

    }

    @Test
    public void testStateChecks() {
        Pet pet = new Pet("Fish", 0, 10, 10, 10, 0, "Cat");

        assertFalse(pet.isAlive()); // Health is 0, should not be alive
        assertFalse(!pet.isHappy()); // Happiness is not 0, should be happy
        assertFalse(pet.isTired()); // Sleep is not 0, should not be tired
        assertFalse(pet.isHungry()); // Hunger is not 0, should not be hungry
    }

    @Test
    public void testMoneyManagement() {
        Pet pet = new Pet("Ducky", 100, 100, 100, 100, 0, "Bear");
        pet.setMoney(50);

        assertEquals(50, pet.getMoney());

        pet.setMoney(-20);
        assertEquals(30, pet.getMoney()); // 50 - 20 = 30

        pet.setMoney(-40);
        assertEquals(0, pet.getMoney()); // Cannot go below 0
    }

    @Test
    public void testInventoryManagement() {
        Pet pet = new Pet("Oreo", 100, 100, 100, 100, 0, "Dog");
        Items item = Items.getItemByName("Bone");

        pet.addItem("Bone", 2);

        HashMap<Items, Integer> inventory = pet.getInventory();
        assertTrue(inventory.containsKey(item));
        assertEquals(2, inventory.get(item));

        pet.addItem("Bone", 3);
        assertEquals(5, inventory.get(item)); // 2 + 3 = 5

        boolean removed = pet.removeItem(item, 3);
        assertTrue(removed);
        assertEquals(2, inventory.get(item));

        removed = pet.removeItem(item, 3);
        assertFalse(removed); // Not enough items to remove

        pet.removeItem(item, 2);
        assertFalse(inventory.containsKey(item)); // Entire item removed
    }

    @Test
    public void testBirthDate() {
        Pet pet = new Pet("Reggie", 100, 100, 100, 100, 0, "Cat");

        assertNotNull(pet.getBirthDate()); // Check birth date is set
        assertTrue(pet.getBirthDate().matches("\\d{2}/\\d{2}/\\d{4}")); // Check date format MM/dd/YYYY
    }
}


