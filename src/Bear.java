import javax.swing.*;
import java.awt.*;

/**
 * This class represents the pet bear using the parent Pet class.
 * It renders and updates the different sprites of the bear, as well as flips the sprite.
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
        updateSprite(panel, false);
     
    }

    /** 
     * Updates the sprite so that the correct sprite correlates to the pet attribute
     * @param panel
     */ 
    @Override 
    public void updateSprite(JPanel panel, boolean flipped){
        String spritePath = "/images/bearnormal.png"; // Bear default sprite

        if(!flipped){
            if(!spriteIsAlive()){
                spritePath = "/images/beardead.png"; // Bear dead state
            } else if (spriteIsTired()){
                spritePath = "/images/bearasleep.png"; // Bear asleep state
            } else if (!spriteIsHappy()){
                spritePath = "/images/bearangry.png"; // Bear angry state
            } else if(spriteIsHungry()){
                spritePath = "/images/bearhungry.png"; // Bear hungry state
            }else{
                spritePath = "/images/bearnormal.png"; // Bear normal state if all stats are back to normal
            }
        }
        else{
            if(!spriteIsAlive()){
                spritePath = "/images/beardead_flip.png"; // Bear dead state
            } else if (spriteIsTired()){
                spritePath = "/images/bearasleep_flip.png"; // Bear asleep state
            } else if (!spriteIsHappy()){
                spritePath = "/images/bearangry_flip.png"; // Bear angry state
            } else if(spriteIsHungry()){
                spritePath = "/images/bearhungry_flip.png"; // Bear hungry state
            }else{
                spritePath = "/images/bearnormal_flip.png"; // Bear normal state if all stats are back to normal
            }
        }
        
        // Update sprite for the bear
        ImageIcon bearSprite = new ImageIcon(getClass().getResource(spritePath)); // Replace with bear image path
        JLabel spriteLabel = new JLabel(bearSprite);

        panel.removeAll();
        panel.add(spriteLabel, BorderLayout.CENTER);
        
        panel.revalidate();  // Refresh the layout
        panel.repaint();     // Repaint the panel to reflect the changes
    }
}