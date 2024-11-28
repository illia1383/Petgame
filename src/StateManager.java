import java.util.*;
public class StateManager {
    private Stack<State> stateStack;
    private boolean playing;
    private boolean running;

    public StateManager() {
        playing = false;
        running = true;
        stateStack = new Stack<State>();
        startMenu();
        //loadStates();
    }

    public void loadStates() {
        Title titleState = new Title(this);
        //stateStack.push(titleState);
    }

    public void startMenu() {
        Title titleState = new Title(this);
        titleState.render();
    }

    public void gameLoop() {
        //Renders then updates then loop until game over
        while (stateStack.isEmpty() == false) {
            //Get input
            stateStack.peek().render();
            stateStack.peek().update();
        }
    }

    public Stack<State> getStateStack() {
        return stateStack;
    }

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

}
