import javax.swing.*;
import java.awt.*;

/**
 * This class represents the pet dog using the parent Pet class.
 * It also renders the different sprites of the dog
 *
 * Date: Nov 28, 2024
 * @author Celia Chan
 */

public class Dog extends Pet{ 

    /**
     * Constructor to create a dog object. Has set parameters
     * @param name
     */
    public Dog(String name){
        super(name, 100, 0, 100, 80, 0, "Dog"); 
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
        updateSprite(panel);
     
    }

    /** 
     * Updates the sprite so that the correct sprite correlates to the pet attribute
     * @param panel
     */ 
    @Override 
    public void updateSprite(JPanel panel, boolean flipped){
        String spritePath = "/images/dognormal.png"; // Default Sprite
        if(!flipped){
            if(!spriteIsAlive()){
                spritePath = "/images/dogdead.png"; // Dog dead state
            } else if (spriteIsTired()){
                spritePath = "/images/dogasleep.png"; // Dog tired state
            } else if (!spriteIsHappy()){
                spritePath = "/images/dogangry.png"; // Dog angry state
            } else if(spriteIsHungry()){
                spritePath = "/images/doghungry.png"; // Dog hungry state
            }else{
                spritePath = "/images/dognormal.png"; // Dog normal state, if all the stats are back to normal
            }
        }
        else{
            if(!spriteIsAlive()){
                spritePath = "/images/dogdead_flip.png"; // Dog dead state
            } else if (spriteIsTired()){
                spritePath = "/images/dogasleep_flip.png"; // Dog tired state
            } else if (!spriteIsHappy()){
                spritePath = "/images/dogangry_flip.png"; // Dog angry state
            } else if(spriteIsHungry()){
                spritePath = "/images/doghungry_flip.png"; // Dog hungry state
            }else{
                spritePath = "/images/dognormal_flip.png"; // Dog normal state, if all the stats are back to normal
            }

        }
        
        // Update sprite for the dog
        ImageIcon dogSprite = new ImageIcon(getClass().getResource(spritePath)); // Replace with dog image path
        JLabel spriteLabel = new JLabel(dogSprite);

        panel.removeAll();
        panel.add(spriteLabel, BorderLayout.CENTER);
        
        panel.revalidate();  // Refresh the layout
        panel.repaint();     // Repaint the panel to reflect the changes
    }
}