import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

/**
 * The {@code LoadSaves} class provides a UI to load saved game files
 * and initialize the game based on the selected save.
 * It interacts with {@code StateManager} to manage the game's state
 * and uses {@code Pet} objects to represent the loaded pets.
 */
public class LoadSaves {
    /**
     * Button to load Save 1.
     */
    private JButton save1;

    /**
     * Button to load Save 2.
     */
    private JButton save2;

    /**
     * Button to load Save 3.
     */
    private JButton save3;

    /**
     * Button to exit the save loading screen and return to the title screen.
     */
    private JButton exit;

    /**
     * The main frame for the UI.
     */
    private JFrame frame;

    /**
     * The {@code Pet} object loaded from a save file.
     */
    private Pet pet;

    /**
     * The {@code StateManager} instance used to manage game states.
     */
    private StateManager statemanager;

     /**
     * Constructs a {@code LoadSaves} object.
     *
     * @param sg the {@code StateManager} used to manage game states
     */
    public LoadSaves(StateManager sg) {
        statemanager = sg;
        pet = null;
    }


    /**
     * Renders the UI for the save loading screen and sets up
     * action listeners for the buttons.
     */
    public void render() {
        prepareUI();

        save1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (makePet("src/Save1.txt")) {
                    System.out.println("Can play");
                    frame.dispose();
                    MainGame mg = new MainGame(statemanager, pet);
                }

            }
        });

        save2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (makePet("src/Save2.txt")) {
                    System.out.println("Can play");
                    frame.dispose();
                    MainGame mg = new MainGame(statemanager, pet);
                    
                }
            }
        });

        save3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (makePet("src/Save3.txt")) {
                    System.out.println("Can play");
                    frame.dispose();
                    MainGame mg = new MainGame(statemanager, pet);
                    
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

    /**
     * Reads the save file and creates a {@code Pet} object
     * based on the file's data.
     *
     * @param file the name of the save file to load
     * @return {@code true} if a valid pet is loaded and ready for the game;
     *         {@code false} otherwise
     */
    private boolean makePet(String file) {
        try {
            //Reading the file
            File fileobj = new File(file);
            BufferedReader reader = new BufferedReader(new FileReader(fileobj));

            //If the file is empty return false
            if (fileobj.length() == 0) {
                return false;
            }

            //Need to not read if there is an empty file do later
            //If there is a file to read
            String type = reader.readLine();
            String name = reader.readLine();
            
            int[] petStats = new int[5];
            for (int i = 0; i < 5; i++) {
                petStats[i] = Integer.parseInt(reader.readLine());
            }

            //Creating the pet based off the type
            if (type.equals("cat")) {
                pet = new Cat(name);
            } else if (type.equals("dog")) {
                pet = new Dog(name);
            } else if (type.equals("bear")) {
                pet = new Bear(name);
            }

            //Adding the stats of the pet
            pet.updateStats(-100 + petStats[0], -100 + petStats[1], -100 + petStats[2], -100 + petStats[3]);
            pet.setMoney(petStats[4]);
            
            reader.close();
            if (pet.getHealth() > 0) { //If there is pet to play with, which is alive, start the game
                return true;
            }
        } catch (IOException er) {
            System.out.println("IO Error - Loading the Saves");
        } catch (Exception er) {
            System.out.println("Other error - Loading the Saves");
        }
        return false;
    }

    /**
     * Prepares the UI components for the save loading screen.
     */
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
