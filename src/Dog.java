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
        super(name, 100, 80, 100, 40, 0, "Dog"); 
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

        String spritePath = "images/dognormal.png";
        if(!isAlive()){
            spritePath = "images/dogdead.png";
        } else if (isTired()){
            spritePath = "images/dogtired.png";
        } else if (!isHappy()){
            spritePath = "images/dogangry.png";
        } else if(isHungry()){
            spritePath = "images/doghungry.png";
        }else{
            spritePath = "images/dognormal.png";
        }
        
        // Update sprite for the dog 
        ImageIcon dogSprite = new ImageIcon(spritePath); // Replace with dog image path
        // Image originalImage = dogSprite.getImage();
        // Image resizedTest = originalImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        // ImageIcon resizeImage = new ImageIcon(resizedTest);
        JLabel spriteLabel = new JLabel(dogSprite);
        panel.add(spriteLabel, BorderLayout.CENTER);

    }
}