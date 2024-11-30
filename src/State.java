import java.util.*;
import java.lang.*;
public class State {
    private StateManager statemanager;

    public State(StateManager sg) {
        statemanager = sg;
    }
    public void update() {
    }

    public void render() {
    }

    public StateManager getStatemanager() {
        return statemanager;
    }

    public void delay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (Exception e) {
            System.out.println("Error with delay");
        }
    }

}
