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
}
