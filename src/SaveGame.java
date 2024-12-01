import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class SaveGame {
    /**
     * The button to save to the first save
     */
    private JButton save1;
    /**
     * The button to save to the second save
     */
    private JButton save2;
    /**
     * The button to save to the third save
     */
    private JButton save3;
    /**
     * The button to return to the main game
     */
    private JButton returnToGame;
    /**
     * The Jframe displayed to the user
     */
    private JFrame frame;
    /**
     * The pet that will be saved
     */
    private Pet pet;
    /**
     * The statemanager to control and manage the time
     */
    private StateManager statemanager;
    /**
     * Storing if the pet died or not
     */
    private boolean died;

    /**
     * Creating the Save Game class which can save the pet or return to the main game
     * 
     * <p>
     * This constructor will set the statemanager, pet and died variables and then call the render method to start the UI allowing the user to choose
     * where to save their pet.
     * </p>
     * 
     * @param sg The statemanager to control the time
     * @param pet The pet whose stats we have to save
     * @param died A boolean saying if the pet was dead
     */
    public SaveGame(StateManager sg, Pet pet, boolean died) {
        statemanager = sg;
        this.pet = pet;
        this.died = died;
        render();
    }

    /**
     * Creating the UI frame and set up the buttons 
     * 
     * <p>
     * This will call the prepareUI class to set up the JFrame and then the individual buttons are created here with the pet ones saving the 
     * pet and then returning to the title screen, but in the case of return to game, the MainGame class is just called with the same pet you
     * entered the class with.
     * </p>
     */
    public void render() {
        prepareUI();

        save1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Saving the pet to Save1
                savePet("src/Save1.txt");
                //Disposing the frame
                frame.dispose();
                //Going back to the title screen
                Title title = new Title(statemanager);
                title.render();
            }
        });

        save2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Saving the pet to Save2
                savePet("src/Save2.txt");
                //Disposing the frame
                frame.dispose();
                //Going back to the title screen
                Title title = new Title(statemanager);
                title.render();
            }
        });

        save3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Saving the pet to Save3
                savePet("src/Save3.txt");
                //Disposing the frame
                frame.dispose();
                //Going back to the title screen
                Title title = new Title(statemanager);
                title.render();
            }
        });

        returnToGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //If the user did not die
                if (died == false) {
                    //Get rid of the frame
                    frame.dispose();
                    //And go back to the main game with the same pet
                    MainGame mg = new MainGame(statemanager, pet);
                    System.out.println("Returning to Main Game");
                } 
                //If the user did die, you cannot return to the main game and print a message saying that
                System.out.println("Pet dead, cannot play the Main Game");  
            }
        });

    }

    /**
     * Save the pet given a specific file to save it to
     * 
     * <p>
     * Given a specific file this class will access that class and then enter the stats of the pet one by one in the same way
     * those stats are read in the LoadSaves class.
     * </p>
     * 
     * @param file The file to save the pet to
     */
    private void savePet(String file) {
        try {
            //Writing all of the pets stats to the specified file
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(pet.getType());
            writer.newLine();
            writer.write(pet.getName());
            writer.newLine();
            writer.write(String.valueOf(pet.getHealth()));
            writer.newLine();
            writer.write(String.valueOf(pet.getHappiness()));
            writer.newLine();
            writer.write(String.valueOf(pet.getSleep()));
            writer.newLine();
            writer.write(String.valueOf(pet.getHunger()));
            writer.newLine();
            writer.write(String.valueOf(pet.getMoney()));
            writer.close();
        } catch (IOException e) {
            System.out.println("IOException SaveGame" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception SaveGame" + e.getMessage());
        }
    }

    /**
     * Creates the JFrame that the user sees and interacts with
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

        JLabel text = new JLabel();
        //Adding a title
        if (died == true) {
            text = new JLabel("YOU DIED! Save your game here");
        } else {
            text = new JLabel("Choose one of the following saves");
        }
        
        text.setFont(new Font("SansSerif", Font.BOLD, 40));
        text.setBounds(height / 3, 0, width, 100);
        frame.add(text);

        //Button to start a new game
        save1 = new JButton("Save 1");
        save1.setBounds(frame.getWidth() / 2-250, 200, 500, 100);
        frame.add(save1);

        //Adding a load save button
        save2 = new JButton("Save 2");
        save2.setBounds(frame.getWidth() / 2-250, 400, 500, 100);
        frame.add(save2);

        //Adding a parental controls button
        save3 = new JButton("Save 3");
        save3.setBounds(frame.getWidth() / 2-250, 600, 500, 100);
        frame.add(save3);

        //Adding a parental controls button
        returnToGame = new JButton("Return to Game");
        returnToGame.setBounds(frame.getWidth() / 2-250, 800, 500, 100);
        frame.add(returnToGame);
    }

}
