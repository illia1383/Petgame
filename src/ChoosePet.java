//import java.util.*;
//import java.awt.*;
//import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;
public class ChoosePet {
    private PracticePet pp; //Creating the pet that we will add to later
    private JButton dog;
    private JButton bear;
    private JButton cat;
    private JFrame frame;
    private StateManager statemanager;

    public ChoosePet(StateManager sg) {
        statemanager = sg;
        pp = null;
    }

    public void render() {
        prepareUI();
        cat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Picked a Cat!!");
                pp = new PracticePet("Timothy", "Cat", true);
                runTutorial();
            }
        });

        bear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Picked the bear!!!");
                pp = new PracticePet("Bernithy", "Bear", true);
                runTutorial();
            }
        });

        dog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Picked the dog!!!");
                pp = new PracticePet("Snoopy", "Dog", true);
                runTutorial();
            }
        });

    }

    public void prepareUI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        frame.setSize(width, height);
        frame.setLayout(null);
        frame.setVisible(true);

        JLabel text = new JLabel("CHOOSE ONE OF THE FOLLOWING PETS!!");
        text.setFont(new Font("SansSerif", Font.BOLD, 40));
        text.setBounds(height / 2, 0, width, 400);
        frame.add(text);

        //Button to start a new game
        dog = new JButton("Dog");
        dog.setBounds(width/5, height/3, height/4, height/4);
        frame.add(dog);

        //Adding a load save button
        bear = new JButton("Bear");
        bear.setBounds(2*width/5, height/3, height/4, height/4);
        frame.add(bear);

        //Adding a parental controls button
        cat = new JButton("Cat");
        cat.setBounds(3*width/5, height/3, height/4, height/4);
        frame.add(cat);
    }

    public void runTutorial() {
        Tutorial tutorial = new Tutorial(statemanager);
        frame.dispose();
        tutorial.render();
    }


}
