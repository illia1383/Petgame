import javax.swing.*;
import java.awt.*;

/**
 * This class represents the pet bear using the parent Pet class.
 * It also renders the different sprites of the bear
 *
 * Date: Nov 28, 2024
 * @author Celia Chan
 */


public class Bear extends Pet{ 

    /**
     * Constructor to create the bear object. Has set parameters
     * @param name
     */
    public Bear(String name){
        super(name, 100, 100, 40, 80, 0, "Bear"); 
    }

    /**
     * Renders the pet sprites and changes them depending on the emotional stats of the pet
     * @param frame
     * @param panel
     */
    @Override
    public void render(JFrame frame, JPanel panel) {
        // Call the parent (Pet) render to reuse the same frame and panel
        super.render(frame, panel);

        String spritePath = "images/bearnormal.png"; // Regular state of pet (Default)
        if(!isAlive()){
            spritePath = "images/beardead.png"; // Dead state
        } else if (isTired()){
            spritePath = "images/beartired.png"; // Tired state
        } else if (!isHappy()){
            spritePath = "images/bearangry.png"; // Angry state
        } else if(isHungry()){
            spritePath = "images/bearhungry.png"; // Hungry state
        }else{
            spritePath = "images/bearnormal.png"; // If its not it goes back to normal
        }

        // Update sprite for the dog 
        ImageIcon bearSprite = new ImageIcon(spritePath); // Replace with bear image path
        JLabel spriteLabel = new JLabel(bearSprite);
        panel.add(spriteLabel, BorderLayout.CENTER);

    }
}