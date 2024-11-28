import java.util.HashMap;

import org.junit.*;

public class TestPet {
    private Dog testDog;
    private Cat testCat;
    private Bear testBear;

    @Before
    public void setUp(){
        testDog = new Dog("Dumpling");
        testCat = new Cat("Fish");
        testBear = new Bear("Berry");
    }

    @Test
    public void testConstructor(){
        Assert.assertEquals("Dumpling", testDog.getName());
        Assert.assertEquals("Fish", testCat.getName());
        Assert.assertEquals("Berry", testBear.getName());
        Assert.assertEquals(100, testDog.getHealth());
        Assert.assertEquals(80, testDog.getHappiness());
        Assert.assertEquals(100, testDog.getSleep());
        Assert.assertEquals(40, testDog.getHunger());
        Assert.assertEquals(0, testDog.getMoney());
        Assert.assertNotNull(testDog.getBirthDate());
        Assert.assertTrue(testDog.isAlive());
    }

    @Test
    public void testUpdateStats(){
        testDog.updateStats(-50, -30, -20, -10);
        Assert.assertEquals(50, testDog.getHealth());
        Assert.assertEquals(50, testDog.getHappiness());
        Assert.assertEquals(80, testDog.getSleep());
        Assert.assertEquals(30, testDog.getHunger());
    }
    @Test
    public void testUpdateStatsBounds() {
        testDog.updateStats(-200, -200, -200, -200);
        Assert.assertEquals(0, testDog.getHealth());
        Assert.assertEquals(0, testDog.getHappiness());
        Assert.assertEquals(0, testDog.getSleep());
        Assert.assertEquals(0, testDog.getHunger());

        testDog.updateStats(200, 200, 200, 200);
        Assert.assertEquals(100, testDog.getHealth());
        Assert.assertEquals(100, testDog.getHappiness());
        Assert.assertEquals(100, testDog.getSleep());
        Assert.assertEquals(100, testDog.getHunger());
    }

    @Test
    public void testIsAlive() {
        Assert.assertTrue(testDog.isAlive());
        testDog.updateStats(-100, 0, 0, 0);
        Assert.assertFalse(testDog.isAlive());
    }

    @Test
    public void testIsHungry() {
        Assert.assertFalse(testDog.isHungry());
        testDog.updateStats(0, 0, 0, -100);
        Assert.assertTrue(testDog.isHungry());
    }

    @Test
    public void testIsTired() {
        Assert.assertFalse(testDog.isTired());
        testDog.updateStats(0, 0, -100, 0);
        Assert.assertTrue(testDog.isTired());
    }

    @Test
    public void testIsHappy() {
        Assert.assertFalse(testDog.isHappy());
        testDog.updateStats(0, -100, 0, 0);
        Assert.assertTrue(testDog.isHappy());
    }

    @Test
    public void testMoneyOperations() {
        testDog.setMoney(50);
        Assert.assertEquals(50, testDog.getMoney());
        testDog.setMoney(-30);
        Assert.assertEquals(20, testDog.getMoney());
        testDog.setMoney(-50);
        Assert.assertEquals(0, testDog.getMoney());
    }

    @Test
    public void testInventoryOperations() {
        testDog.addItem("Apple", 5);
        testDog.addItem("Bone", 3);
        HashMap<String, Integer> inventory = testDog.getInventory();
        Assert.assertEquals(5, (int) inventory.get("Apple"));
        Assert.assertEquals(3, (int) inventory.get("Bone"));

        testDog.addItem("Apple", 2);
        Assert.assertEquals(7, (int) inventory.get("Apple"));

        Assert.assertTrue(testDog.removeItem("Apple", 3));
        Assert.assertEquals(4, (int) inventory.get("Apple"));

        Assert.assertFalse(testDog.removeItem("Bone", 5));
        Assert.assertTrue(testDog.removeItem("Bone", 3));
        Assert.assertFalse(inventory.containsKey("Bone"));

        Assert.assertFalse(testDog.removeItem("NonExistent", 1));
    }

    @Test
    public void testBirthDate() {
        String birthDate = testDog.getBirthDate();
        Assert.assertNotNull(birthDate);
        Assert.assertFalse(birthDate.isEmpty());
    }
}

