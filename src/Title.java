import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.time.*;
public class Title {
    private JButton startGame;
    private JButton loadSave;
    private JButton parentalControls;
    private JButton exit;
    private JFrame frame;
    private StateManager statemanager;

    public Title(StateManager sg) {
        statemanager = sg;
    }

    public void render() {
        prepareUI();
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkRestrictions()) {
                    System.out.println("Cannot play right now!");
                } else {
                    frame.dispose();
                    System.out.println("Choose Pet");
                    ChoosePet choosepet = new ChoosePet(statemanager);
                    choosepet.render();
                }
            }
        });

        loadSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkRestrictions()) {
                    System.out.println("Cannot play right now!");
                } else {
                    LoadSaves ls = new LoadSaves(statemanager);
                    ls.render();
                    frame.dispose();
                }
            }
        });

        parentalControls.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Parental parental = new Parental(statemanager);
                parental.render();

            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalTime end = LocalTime.now(); //Finding the time the user clicks exit game
                statemanager.setEndGame(end);
                statemanager.addTime();
                frame.dispose();
                System.exit(0);
            }
        });

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
