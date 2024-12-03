import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.time.*;

/**
 * The Title class represents the title screen of the game. It provides the user
 * with several options including starting a new game, loading a saved game, 
 * managing parental controls, or exiting the game.
 */
public class Title {
    /**
     * Button to start game.
     */
    private JButton startGame;
    /**
     * Button to load saves.
     */
    private JButton loadSave;
    /**
     * Button to access parental controls.
     */
    private JButton parentalControls;
    /**
     * Button to exit game.
     */
    private JButton exit;
    /**
     * The frame that is rendered 
     */
    private JFrame frame;
    /**
     * The statemanager to control the time
     */
    private StateManager statemanager;

    /**
     * Constructs a Title object with the specified StateManager.
     * 
     * @param sg The StateManager to be used by this Title screen.
     */
    public Title(StateManager sg) {
        statemanager = sg;
    }

    /**
     * Renders the title screen UI and sets up the event listeners for the buttons.
     * When a button is clicked, it triggers the appropriate action based on the
     * current restrictions and choice.
     */
    public void render() {
        prepareUI();
        
        //If the user presses the start game button do the following actions
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Checking if the user can play
                if (checkRestrictions()) {
                    System.out.println("Cannot play right now!");
                } else {
                    //If they can opening the choose pet screen
                    frame.dispose();
                    System.out.println("Choose Pet");
                    ChoosePet choosepet = new ChoosePet(statemanager);
                    choosepet.render();
                }
            }
        });

        //If the user presses the load save button do the following actions
        loadSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Checking if the user can play
                if (checkRestrictions()) {
                    System.out.println("Cannot play right now!");
                } else {
                    //If they can load the saves
                    LoadSaves ls = new LoadSaves(statemanager);
                    ls.render();
                    frame.dispose();
                }
            }
        });

        //If the user presses the parental controls button do the following actions
        parentalControls.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Opening up the parental class and disposing the frame
                frame.dispose();
                Parental parental = new Parental(statemanager);
                parental.render();

            }
        });

        //If the user presses the exit button do the following actions
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalTime end = LocalTime.now(); //Finding the time the user clicks exit game
                //Using the time the user ends the game to update the stats
                statemanager.setEndGame(end);
                statemanager.addTime();
                frame.dispose();
                System.exit(0); //Exitting the game 
            }
        });

    }

    /**
     * Prepares the UI components of the title screen including buttons and labels.
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
        JLabel titleofGame = new JLabel("PET GAME");
        titleofGame.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 100));
        titleofGame.setBounds(frame.getWidth() / 3, 100, 1000, 200);
        frame.add(titleofGame);

        //Button to start a new game
        startGame = new JButton("Start New Game");
        startGame.setBounds(frame.getWidth() / 3, 300, 500, 50);
        frame.add(startGame);

        //Adding a load save button
        loadSave = new JButton("Load Save");
        loadSave.setBounds(frame.getWidth() / 3, 400, 500, 50);
        frame.add(loadSave);

        //Adding a parental controls button
        parentalControls = new JButton("Parental Settings");
        parentalControls.setBounds(frame.getWidth() / 3, 500, 500, 50);
        frame.add(parentalControls);

        //Adding an exit game button
        exit = new JButton("Exit");
        exit.setBounds(frame.getWidth() / 3, 600, 500, 50);
        frame.add(exit);
    }

     /**
     * Checks if the current time is within the restricted playing time.
     * If it is within the restriction, it returns true; otherwise, false.
     * 
     * @return boolean True if the current time is within the restricted playing time, otherwise false.
     */
    private boolean checkRestrictions() {
        LocalTime now = LocalTime.now();
        if (statemanager.getStartRestriction() != null && statemanager.getEndRestriction() != null) {
            if (now.isAfter(statemanager.getStartRestriction()) && now.isBefore(statemanager.getEndRestriction())) {
                return true;
            }
        }
        return false;
    }

    


}
