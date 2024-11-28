import java.util.*;
public class StateManager {
    private Stack<State> stateStack;
    private boolean playing;
    private boolean running;

    public StateManager() {
        playing = false;
        running = true;
        stateStack = new Stack<State>();
<<<<<<< HEAD
        loadStates();
=======
        startMenu();
        //loadStates();
>>>>>>> petClass
    }

    public void loadStates() {
        Title titleState = new Title(this);
<<<<<<< HEAD
        stateStack.push(titleState);
=======
        //stateStack.push(titleState);
    }

    public void startMenu() {
        Title titleState = new Title(this);
        titleState.render();
>>>>>>> petClass
    }

    public void gameLoop() {
        //Renders then updates then loop until game over
        while (stateStack.isEmpty() == false) {
<<<<<<< HEAD
=======
            //Get input
>>>>>>> petClass
            stateStack.peek().render();
            stateStack.peek().update();
        }
    }

    public Stack<State> getStateStack() {
        return stateStack;
    }

<<<<<<< HEAD
=======
    public boolean getPlaying() { return playing; }

    public boolean getRunning() { return running; }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void printStack() {
        for (int i = stateStack.size() - 1; i >= 0; i--) {
            System.out.println(stateStack.get(i)); //States are still objects so doesnt print properly
        }
    }

    public static void main(String[] args) {
        StateManager sg = new StateManager();
    }

>>>>>>> petClass
}
