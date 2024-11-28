import java.util.*;
<<<<<<< HEAD
public class State {
    private StateManager statemanager;

=======
import java.lang.*;
public class State {
    private StateManager statemanager;
    private boolean isStatic = false;
>>>>>>> petClass

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
<<<<<<< HEAD
        if (statemanager.getStateStack().size() == 1) {
=======
        if (statemanager.getStateStack().size() == 0) { //Exit at 0 as this is when the user exits from the main menu
>>>>>>> petClass
            System.exit(0);
        }
    }

    public StateManager getStatemanager() {
        return statemanager;
    }

<<<<<<< HEAD
=======
    public boolean getIsStatic() {
        return isStatic;
    }

    public void setIsStatic(boolean s) {
        isStatic = s;
    }

    public void delay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (Exception e) {
            System.out.println("Error with delay");
        }
    }

>>>>>>> petClass
}
