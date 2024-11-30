import javax.swing.*;
import java.awt.*;


public class Cat extends Pet{ 
    public Cat(String name){
        super(name, 100, 80, 40, 100, 0, "Cat"); // Temporary Stats
    }

    @Override
    public void render(JFrame frame, JPanel panel) {
        // Call the parent (Pet) render to reuse the same frame and panel
        super.render(frame, panel);

        // Update Cat sprite
        ImageIcon catSprite = new ImageIcon("images/cat.png"); // Replace with dog image path
        JLabel spriteLabel = new JLabel(catSprite);
        panel.add(spriteLabel, BorderLayout.CENTER);

    }
}