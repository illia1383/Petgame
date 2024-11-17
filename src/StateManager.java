import java.util.*;
public class StateManager {
    private Stack<State> stateStack;
    private boolean playing;
    private boolean running;

    public StateManager() {
        playing = false;
        running = true;
        stateStack = new Stack<State>();
        loadStates();
    }

    public void loadStates() {
        Title titleState = new Title(this);
        stateStack.push(titleState);
    }

    public void gameLoop() {
        //Renders then updates then loop until game over
        while (stateStack.isEmpty() == false) {
            stateStack.peek().render();
            stateStack.peek().update();
        }
    }

    public Stack<State> getStateStack() {
        return stateStack;
    }

}
