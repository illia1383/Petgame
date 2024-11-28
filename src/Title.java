<<<<<<< HEAD
import java.util.*;
public class Title extends State{
    public Title(StateManager sg) {
        super(sg);
    }

    public void render() {
        System.out.println("\n\n\nTitle/Menu State");
        System.out.println("1) Start Game");
        System.out.println("2) Load Save");
        System.out.println("3) Parental Control Settings");
    }

    public void update() {
        Scanner userinput = new Scanner(System.in);
        int choice = userinput.nextInt();
        switch (choice) {
            case 1:
                //State game
                break;
            case 2:
                //load games
                break;
            case 3:
                //Parental controls
                break;
        }
    }
=======
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
    private LocalTime startRestriction = LocalTime.of(12,0);
    private LocalTime endRestriction = LocalTime.of(12, 0);

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
        titleofGame.setBounds(frame.getWidth() / 2-250, 100, 1000, 200);
        frame.add(titleofGame);

        //Button to start a new game
        startGame = new JButton("Start New Game");
        startGame.setBounds(frame.getWidth() / 2-250, 300, 500, 50);
        frame.add(startGame);

        //Adding a load save button
        loadSave = new JButton("Load Save");
        loadSave.setBounds(frame.getWidth() / 2-250, 400, 500, 50);
        frame.add(loadSave);

        //Adding a parental controls button
        parentalControls = new JButton("Parental Settings");
        parentalControls.setBounds(frame.getWidth() / 2-250, 500, 500, 50);
        frame.add(parentalControls);

        //Adding an exit game button
        exit = new JButton("Exit");
        exit.setBounds(frame.getWidth() / 2-250, 600, 500, 50);
        frame.add(exit);
    }

    private boolean checkRestrictions() {
        LocalTime now = LocalTime.now();
        if (now.isAfter(startRestriction) && now.isBefore(endRestriction)) {
            return true;
        }
        return false;
    }

    private void setStartRestriction(int hour, int min) {
        startRestriction = LocalTime.of(hour, min);
    }
    private void setEndRestriction(int hour, int min) {
        endRestriction = LocalTime.of(hour, min);
    }


>>>>>>> petClass
}
