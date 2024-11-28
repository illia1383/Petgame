import javax.swing.*;
import java.awt.*;


public class Bear extends Pet{ 
    public Bear(String name){
        super(name, 100, 100, 40, 80, 0, "Bear"); //Temporary Stats
    }

    @Override
    public void render(JFrame frame, JPanel panel) {
        // Call the parent (Pet) render to reuse the same frame and panel
        super.render(frame, panel);

        String spritePath = "images/bearnormal.png";
        if(!isAlive()){
            spritePath = "images/beardead.png";
        } else if (isTired()){
            spritePath = "images/beartired.png";
        } else if (!isHappy()){
            spritePath = "images/bearangry.png";
        } else if(isHungry()){
            spritePath = "images/bearhungry.png";
        }else{
            spritePath = "images/bearnormal.png";
        }

        // Update sprite for the dog 
        ImageIcon bearSprite = new ImageIcon(spritePath); // Replace with bear image path
        JLabel spriteLabel = new JLabel(bearSprite);
        panel.add(spriteLabel, BorderLayout.CENTER);

    }
}