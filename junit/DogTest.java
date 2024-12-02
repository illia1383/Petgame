import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.awt.*;

public class DogTest {
    
    @Test
    void testDogInitialization() {
        // Create a Dog object
        Dog dog = new Dog("Marshmellow");

        // Verify the initial values of the Dog
        assertEquals("Buddy", dog.getName());
        assertEquals(100, dog.getHealth());
        assertEquals(40, dog.getHappiness());
        assertEquals(100, dog.getSleep());
        assertEquals(80, dog.getHunger());
        assertEquals(0, dog.getMoney());
        assertEquals("Dog", dog.getType());
    }

    @Test
    void testRenderNormalState() {
        // Create a Dog object
        Dog dog = new Dog("Marshmellow");

        // Set up the JFrame and JPanel
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        // Call the render method
        dog.render(frame, panel);

        // Verify that the correct sprite is displayed (normal sprite)
        Component[] components = panel.getComponents();
        assertEquals(1, components.length);
        assertTrue(components[0] instanceof JLabel);

        JLabel spriteLabel = (JLabel) components[0];
        ImageIcon icon = (ImageIcon) spriteLabel.getIcon();
        assertEquals("images/dognormal.png", icon.getDescription()); // Description must match
    }

    
    @Test
    void testUpdateSpriteForAngryState() {
        // Create a Dog object
        Dog dog = new Dog("Marshmellow");

        // Set hunger to simulate the hungry state
        dog.updateStats(0, -40, 0, 0);

        // Set up the JPanel for rendering
        JPanel panel = new JPanel();

        // Call the updateSprite method
        dog.updateSprite(panel, false);

        // Verify the sprite path for the hungry state
        Component[] components = panel.getComponents();
        assertEquals(1, components.length);
        assertTrue(components[0] instanceof JLabel);

        JLabel spriteLabel = (JLabel) components[0];
        ImageIcon icon = (ImageIcon) spriteLabel.getIcon();
        assertEquals("images/dogangry.png", icon.getDescription());
    }

    @Test
    void testUpdateSpriteForHungryState() {
        // Create a Dog object
        Dog dog = new Dog("Marshmellow");

        // Set hunger to simulate the hungry state
        dog.updateStats(0, 0, 0, -80);

        // Set up the JPanel for rendering
        JPanel panel = new JPanel();

        // Call the updateSprite method
        dog.updateSprite(panel, false);

        // Verify the sprite path for the hungry state
        Component[] components = panel.getComponents();
        assertEquals(1, components.length);
        assertTrue(components[0] instanceof JLabel);

        JLabel spriteLabel = (JLabel) components[0];
        ImageIcon icon = (ImageIcon) spriteLabel.getIcon();
        assertEquals("images/doghungry.png", icon.getDescription());
    }

    @Test
    void testUpdateSpriteForTiredState() {
        // Create a Dog object
        Dog dog = new Dog("Marshmellow");

        // Set sleep to simulate the tired state
        dog.updateStats(0, 0, -100, 0);

        // Set up the JPanel for rendering
        JPanel panel = new JPanel();

        // Call the updateSprite method
        dog.updateSprite(panel, false);

        // Verify the sprite path for the tired state
        Component[] components = panel.getComponents();
        assertEquals(1, components.length);
        assertTrue(components[0] instanceof JLabel);

        JLabel spriteLabel = (JLabel) components[0];
        ImageIcon icon = (ImageIcon) spriteLabel.getIcon();
        assertEquals("images/dogasleep.png", icon.getDescription());
    }

    @Test
    void testUpdateSpriteForDeadState() {
        // Create a Dog object
        Dog dog = new Dog("Marshmellow");

        // Set health to simulate the dead state
        dog.updateStats(-100, 0, 0, 0);

        // Set up the JPanel for rendering
        JPanel panel = new JPanel();

        // Call the updateSprite method
        dog.updateSprite(panel, false);

        // Verify the sprite path for the dead state
        Component[] components = panel.getComponents();
        assertEquals(1, components.length);
        assertTrue(components[0] instanceof JLabel);

        JLabel spriteLabel = (JLabel) components[0];
        ImageIcon icon = (ImageIcon) spriteLabel.getIcon();
        assertEquals("images/dogdead.png", icon.getDescription());
    }
}
