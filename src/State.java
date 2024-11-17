import java.util.*;
public class State {
    private StateManager statemanager;


    public State(StateManager sg) {
        statemanager = sg;
    }
    public void update() {
    }

    public void render() {
    }

    public void enterState() {
        statemanager.getStateStack().push(this);
    }

    public void exitState() {
        statemanager.getStateStack().pop();
        if (statemanager.getStateStack().size() == 1) {
            System.exit(0);
        }
    }

    public StateManager getStatemanager() {
        return statemanager;
    }

}
