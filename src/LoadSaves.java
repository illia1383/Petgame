import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
public class LoadSaves {
    private JButton save1;
    private JButton save2;
    private JButton save3;
    private JButton exit;
    private JFrame frame;
    private PracticePet pp;
    private StateManager statemanager;

    public LoadSaves(StateManager sg) {
        statemanager = sg;
    }

    public void render() {
        prepareUI();

        save1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (makePet("src/Save1.txt")) {
                    System.out.println("Can play");
                    frame.dispose();
                }

            }
        });

        save2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (makePet("src/Save2.txt")) {
                    System.out.println("Can play");
                    frame.dispose();
                }
            }
        });

        save3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (makePet("src/Save3.txt")) {
                    System.out.println("Can play");
                    frame.dispose();
                }
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Title title = new Title(statemanager);
                title.render();
            }
        });
    }

    private boolean makePet(String file) {
        try {
            //Reading the file
            BufferedReader reader = new BufferedReader(new FileReader(file));
            //Need to not read if there is an empty file do later
            //If there is a file to read
            String name = reader.readLine();
            String type = reader.readLine();
            String alive = reader.readLine();
            pp = new PracticePet(name, type, Boolean.parseBoolean(alive));

            if (pp.getAlive()) { //If there is pet to play with, start hte game
                return true;
            }

        } catch (IOException er) {
            System.out.println("IO Error");
            System.exit(0);
        } catch (Exception er) {
            System.out.println("Other error");
            System.exit(0);
        }
        return false;
    }

    private void prepareUI() {
        frame = new JFrame(); //Instance of JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        //Setting the size of the frame
        frame.setSize(width, height);
        frame.setLayout(null);
        frame.setVisible(true);

        //Adding a title
        JLabel text = new JLabel("Choose one of the following saves");
        text.setFont(new Font("SansSerif", Font.BOLD, 40));
        text.setBounds(height / 2+125, 0, width, 400);
        frame.add(text);

        //Button to start a new game
        save1 = new JButton("Save 1");
        save1.setBounds(frame.getWidth() / 2-250, 300, 500, 50);
        frame.add(save1);

        //Adding a load save button
        save2 = new JButton("Save 2");
        save2.setBounds(frame.getWidth() / 2-250, 400, 500, 50);
        frame.add(save2);

        //Adding a parental controls button
        save3 = new JButton("Save 3");
        save3.setBounds(frame.getWidth() / 2-250, 500, 500, 50);
        frame.add(save3);

        //Adding an exit game button
        exit = new JButton("Exit");
        exit.setBounds(frame.getWidth() / 2-250, 600, 500, 50);
        frame.add(exit);
    }

}
