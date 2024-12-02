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
        updateSprite(panel, false);
     
    }

    /** 
     * Updates the sprite so that the correct sprite correlates to the pet attribute
     * @param panel
     */ 
    @Override 
    public void updateSprite(JPanel panel, boolean flipped){
        String spritePath = "images/bearnormal.png"; // Bear default sprite

        if(!flipped){
            if(!isAlive()){
                spritePath = "images/beardead.png"; // Bear dead state
            } else if (isTired()){
                spritePath = "images/bearasleep.png"; // Bear asleep state
            } else if (!isHappy()){
                spritePath = "images/bearangry.png"; // Bear angry state
            } else if(isHungry()){
                spritePath = "images/bearhungry.png"; // Bear hungry state
            }else{
                spritePath = "images/bearnormal.png"; // Bear normal state if all stats are back to normal
            }
        }
        else{
            if(!isAlive()){
                spritePath = "images/beardead_flip.png"; // Bear dead state
            } else if (isTired()){
                spritePath = "images/bearasleep_flip.png"; // Bear asleep state
            } else if (!isHappy()){
                spritePath = "images/bearangry_flip.png"; // Bear angry state
            } else if(isHungry()){
                spritePath = "images/bearhungry_flip.png"; // Bear hungry state
            }else{
                spritePath = "images/bearnormal_flip.png"; // Bear normal state if all stats are back to normal
            }
        }
        
        // Update sprite for the bear
        ImageIcon dogSprite = new ImageIcon(spritePath); // Replace with bear image path
        JLabel spriteLabel = new JLabel(dogSprite);

        panel.removeAll();
        panel.add(spriteLabel, BorderLayout.CENTER);
        
        panel.revalidate();  // Refresh the layout
        panel.repaint();     // Repaint the panel to reflect the changes
    }
}