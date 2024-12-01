public class State {
    /**
     * The statemanager controls the time settings 
     */
    private StateManager statemanager;

    /**
     * Creating the State
     * 
     * @param sg The statemanager
     */
    public State(StateManager sg) {
        statemanager = sg;
    }
    /**
     * Updating based on what the user inputs
     */
    public void update() {
    }
    /**
     * Rendering the screen
     */
    public void render() {
    }

    /**
     * Getting the state manager
     * @return StateManager
     */
    public StateManager getStatemanager() {
        return statemanager;
    }

    /**
     * Delaying the game
     * @param delay How long to delay it
     */
    public void delay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (Exception e) {
            System.out.println("Error with delay");
        }
    }

}
