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
    @Override
    public void render(JFrame frame, JPanel panel) {
        // Call the parent (Pet) render to reuse the same frame and panel
        super.render(frame, panel);

        String spritePath = "images/catnormal.png"; // Regular state of pet
        if(!isAlive()){
            spritePath = "images/catdead.png";
        } else if (isTired()){
            spritePath = "images/cattired.png";
        } else if (!isHappy()){
            spritePath = "images/catangry.png";
        } else if(isHungry()){
            spritePath = "images/cathungry.png";
        }else{
            spritePath = "images/catnormal.png";
        }
        
        // Update Cat sprite
        ImageIcon catSprite = new ImageIcon(spritePath); // Replace with cat image path
        JLabel spriteLabel = new JLabel(catSprite);
        panel.add(spriteLabel, BorderLayout.CENTER);

       

    }
}