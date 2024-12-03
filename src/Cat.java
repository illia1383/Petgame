import javax.swing.*;
import java.awt.*;

/**
 * This class represents the pet cat using the parent Pet class.
 * It also renders the different sprites of the cat
 *
 * Date: Nov 28, 2024
 * @author Celia Chan
 */


public class Cat extends Pet{ 
    /**
     * Constructor to create the cat object. Has set parameters
     * @param name
     */
    public Cat(String name){
        super(name, 100, 80, 40, 100, 0, "Cat"); 
    }

     /**
     * Renders the pet sprites and changes them depending on the emotional stats of the pet
     * @param frame
     * @param panel
     */
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
        String spritePath = "/images/catnormal.png"; // Cat default sprite

        if(!flipped){
            if(!spriteIsAlive()){
                spritePath = "/images/catdead.png"; // Cat dead state
            } else if (spriteIsTired()){
                spritePath = "/images/catasleep.png"; // Cat tired state
            } else if (!spriteIsHappy()){
                spritePath = "/images/catangry.png"; // Cat angry state
            } else if (spriteIsHungry()){
                spritePath = "/images/cathungry.png"; // Cat hungrystate
            }else{
                spritePath = "/images/catnormal.png"; // Cat normal state (If all stats are back to normal)
            }
        }
        else{
            if(!spriteIsAlive()){
                spritePath = "/images/catdead_flip.png"; // Cat dead state
            } else if (spriteIsTired()){
                spritePath = "/images/catasleep_flip.png"; // Cat tired state
            } else if (!spriteIsHappy()){
                spritePath = "/images/catangry_flip.png"; // Cat angry state
            } else if(spriteIsHungry()){
                spritePath = "/images/cathungry_flip.png"; // Cat hungrystate
            }else{
                spritePath = "/images/catnormal_flip.png"; // Cat normal state (If all stats are back to normal)
            }
        }
        
        // Update sprite for the cat
        ImageIcon dogSprite = new ImageIcon(getClass().getResource(spritePath)); // Replace with cat image path
        JLabel spriteLabel = new JLabel(dogSprite);

        panel.removeAll();
        panel.add(spriteLabel, BorderLayout.CENTER);
        
        panel.revalidate();  // Refresh the layout
        panel.repaint();     // Repaint the panel to reflect the changes
    }
}