import javax.swing.*;
import java.awt.*;


public class Cat extends Pet{ 
    public Cat(String name){
        super(name, 100, 80, 40, 100, 0, "Cat"); 
    }

    @Override
    public void render(JFrame frame, JPanel panel) {
        // Call the parent (Pet) render to reuse the same frame and panel
        super.render(frame, panel);

        String spritePath = "images/catnormal.png";
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