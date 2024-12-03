import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShopTest {

    private Pet pet;
    private Items item;

    @BeforeEach
    public void setUp() {
        // Create a pet with initial money and stats
        pet = new Pet("Fluffy", 100, 100, 100, 100, 50, "Cat");

        // Create an item (e.g., food item with price 10, food boost 20)
        item = new Items("Food", 10, 5, 20, 0);
    }

    @Test
    public void testBuyItem() {
        // Initially, the pet has 50 money
        assertEquals(50, pet.getMoney());

        // Buy the item (assuming pet can afford it)
        int itemPrice = item.getPrice();
        if (pet.getMoney() >= itemPrice) {
            pet.setMoney(-itemPrice); // Deduct money for the item
            pet.addItem(item.getName(), 1); // Add the item to the inventory
        }

        // Check if money is deducted
        assertEquals(40, pet.getMoney()); // Pet's money should be reduced by 10

        // Check if the item was added to the inventory
        assertTrue(pet.getInventory().containsKey(item), "Item should be in the inventory");

        // Check if the inventory quantity is correct
        assertEquals(1, pet.getInventory().get(item), "The quantity of the item should be 1");
    }
}
