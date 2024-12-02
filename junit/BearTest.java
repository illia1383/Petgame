import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.awt.*;

public class BearTest {
    
    @Test
    void testBearInitialization() {
        // Create a Bear object
        Bear bear = new Bear("Mint");

        // Verify the initial values of the Bear
        assertEquals("Mint", bear.getName());
        assertEquals(100, bear.getHealth());
        assertEquals(100, bear.getHappiness());
        assertEquals(40, bear.getSleep());
        assertEquals(80, bear.getHunger());
        assertEquals(0, bear.getMoney());
        assertEquals("Bear", bear.getType());
    }

    @Test
    void testRenderNormalState() {
        // Create a Bear object
        Bear bear = new Bear("Melon");

        // Set up the JFrame and JPanel
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        // Call the render method
        bear.render(frame, panel);

        // Verify that the correct sprite is displayed (normal sprite)
        Component[] components = panel.getComponents();
        assertEquals(1, components.length);
        assertTrue(components[0] instanceof JLabel);

        JLabel spriteLabel = (JLabel) components[0];
        ImageIcon icon = (ImageIcon) spriteLabel.getIcon();
        assertEquals("images/bearnormal.png", icon.getDescription()); // Description must match
    }

    @Test
    void testUpdateSpriteForAngryState() {
        // Create a Bear object
        Bear bear = new Bear("Mint");

        // Set hunger to simulate the hungry state
        bear.updateStats(0, -100, 0, 0);

        // Set up the JPanel for rendering
        JPanel panel = new JPanel();

        // Call the updateSprite method
        bear.updateSprite(panel, false);

        // Verify the sprite path for the hungry state
        Component[] components = panel.getComponents();
        assertEquals(1, components.length);
        assertTrue(components[0] instanceof JLabel);

        JLabel spriteLabel = (JLabel) components[0];
        ImageIcon icon = (ImageIcon) spriteLabel.getIcon();
        assertEquals("images/bearangry.png", icon.getDescription());
    }


    @Test
    void testUpdateSpriteForHungryState() {
        // Create a Bear object
        Bear bear = new Bear("Mint");

        // Set hunger to simulate the hungry state
        bear.updateStats(0, 0, 0, -80);

        // Set up the JPanel for rendering
        JPanel panel = new JPanel();

        // Call the updateSprite method
        bear.updateSprite(panel, false);

        // Verify the sprite path for the hungry state
        Component[] components = panel.getComponents();
        assertEquals(1, components.length);
        assertTrue(components[0] instanceof JLabel);

        JLabel spriteLabel = (JLabel) components[0];
        ImageIcon icon = (ImageIcon) spriteLabel.getIcon();
        assertEquals("images/bearhungry.png", icon.getDescription());
    }

    @Test
    void testUpdateSpriteForTiredState() {
        // Create a Bear object
        Bear bear = new Bear("Mint");

        // Set sleep to simulate the tired state
        bear.updateStats(0, 0, -40, 0);

        // Set up the JPanel for rendering
        JPanel panel = new JPanel();

        // Call the updateSprite method
        bear.updateSprite(panel, false);

        // Verify the sprite path for the tired state
        Component[] components = panel.getComponents();
        assertEquals(1, components.length);
        assertTrue(components[0] instanceof JLabel);

        JLabel spriteLabel = (JLabel) components[0];
        ImageIcon icon = (ImageIcon) spriteLabel.getIcon();
        assertEquals("images/bearasleep.png", icon.getDescription());
    }

    @Test
    void testUpdateSpriteForDeadState() {
        // Create a Bear object
        Bear bear = new Bear("Mint");

        // Set health to simulate the dead state
        bear.updateStats(-100, 0, 0, 0);

        // Set up the JPanel for rendering
        JPanel panel = new JPanel();

        // Call the updateSprite method
        bear.updateSprite(panel, false);

        // Verify the sprite path for the dead state
        Component[] components = panel.getComponents();
        assertEquals(1, components.length);
        assertTrue(components[0] instanceof JLabel);

        JLabel spriteLabel = (JLabel) components[0];
        ImageIcon icon = (ImageIcon) spriteLabel.getIcon();
        assertEquals("images/beardead.png", icon.getDescription());
    }
}

