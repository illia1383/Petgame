import javax.swing.*;
import java.awt.*;


public class Dog extends Pet{ 
    public Dog(String name){
        super(name, 100, 80, 100, 40, 0, "Dog"); // Temporary Stats
    }

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
        JLabel spriteLabel = new JLabel(dogSprite);
        panel.add(spriteLabel, BorderLayout.CENTER);

    }
}