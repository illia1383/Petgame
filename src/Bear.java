import javax.swing.*;
import java.awt.*;


public class Bear extends Pet{ 
    public Bear(String name){
        super(name, 100, 100, 40, 80, 0); //Temporary Stats
    }

    @Override
    public void render(JFrame frame, JPanel panel) {
        // Call the parent (Pet) render to reuse the same frame and panel
        super.render(frame, panel);

        // Update sprite for the dog 
        ImageIcon bearSprite = new ImageIcon("images/catbear.png"); // Replace with dog image path
        JLabel spriteLabel = new JLabel(bearSprite);
        panel.add(spriteLabel, BorderLayout.CENTER);

    }
}