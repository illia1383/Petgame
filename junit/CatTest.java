import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.awt.*;

/**
 * Tests the cat class's sprites and initialization
 * Date：Nov 30, 2024
 * @author Celia Chan
 */

public class CatTest {
    
    @Test
    void testCatInitialization() {
        // Create a Cat object
        Cat cat = new Cat("Melon");

        // Verify the initial values of the Cat
        assertEquals("Melon", cat.getName());
        assertEquals(100, cat.getHealth());
        assertEquals(80, cat.getHappiness());
        assertEquals(40, cat.getSleep());
        assertEquals(100, cat.getHunger());
        assertEquals(0, cat.getMoney());
        assertEquals("Cat", cat.getType());
    }

    @Test
    void testRenderNormalState() {
        // Create a Cat object
        Cat cat = new Cat("Melon");

        // Set up the JFrame and JPanel
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        // Call the render method
        cat.render(frame, panel);

        // Verify that the correct sprite is displayed (normal sprite)
        Component[] components = panel.getComponents();
        assertEquals(1, components.length);
        assertTrue(components[0] instanceof JLabel);

        JLabel spriteLabel = (JLabel) components[0];
        ImageIcon icon = (ImageIcon) spriteLabel.getIcon();
        assertEquals("images/catnormal.png", icon.getDescription()); // Description must match
    }

    @Test
    void testUpdateSpriteForAngryState() {
        // Create a Cat object
        Cat cat = new Cat("Melon");

        // Set hunger to simulate the hungry state
        cat.updateStats(0, -80, 0, 0);

        // Set up the JPanel for rendering
        JPanel panel = new JPanel();

        // Call the updateSprite method
        cat.updateSprite(panel, false);

        // Verify the sprite path for the hungry state
        Component[] components = panel.getComponents();
        assertEquals(1, components.length);
        assertTrue(components[0] instanceof JLabel);

        JLabel spriteLabel = (JLabel) components[0];
        ImageIcon icon = (ImageIcon) spriteLabel.getIcon();
        assertEquals("images/catangry.png", icon.getDescription());
    }

    @Test
    void testUpdateSpriteForHungryState() {
        // Create a Cat object
        Cat cat = new Cat("Melon");

        // Set hunger to simulate the hungry state
        cat.updateStats(0, 0, 0, -100);

        // Set up the JPanel for rendering
        JPanel panel = new JPanel();

        // Call the updateSprite method
        cat.updateSprite(panel, false);

        // Verify the sprite path for the hungry state
        Component[] components = panel.getComponents();
        assertEquals(1, components.length);
        assertTrue(components[0] instanceof JLabel);

        JLabel spriteLabel = (JLabel) components[0];
        ImageIcon icon = (ImageIcon) spriteLabel.getIcon();
        assertEquals("images/cathungry.png", icon.getDescription());
    }

    @Test
    void testUpdateSpriteForTiredState() {
        // Create a Cat object
        Cat cat = new Cat("Melon");

        // Set sleep to simulate the tired state
        cat.updateStats(0, 0, -40, 0);

        // Set up the JPanel for rendering
        JPanel panel = new JPanel();

        // Call the updateSprite method
        cat.updateSprite(panel, false);

        // Verify the sprite path for the tired state
        Component[] components = panel.getComponents();
        assertEquals(1, components.length);
        assertTrue(components[0] instanceof JLabel);

        JLabel spriteLabel = (JLabel) components[0];
        ImageIcon icon = (ImageIcon) spriteLabel.getIcon();
        assertEquals("images/catasleep.png", icon.getDescription());
    }

    @Test
    void testUpdateSpriteForDeadState() {
        // Create a Cat object
        Cat cat = new Cat("Melon");

        // Set health to simulate the dead state
        cat.updateStats(-100, 0, 0, 0);

        // Set up the JPanel for rendering
        JPanel panel = new JPanel();

        // Call the updateSprite method
        cat.updateSprite(panel, false);

        // Verify the sprite path for the dead state
        Component[] components = panel.getComponents();
        assertEquals(1, components.length);
        assertTrue(components[0] instanceof JLabel);

        JLabel spriteLabel = (JLabel) components[0];
        ImageIcon icon = (ImageIcon) spriteLabel.getIcon();
        assertEquals("images/catdead.png", icon.getDescription());
    }
}
