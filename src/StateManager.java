import java.time.*;
import java.time.temporal.*;
import java.util.*;
import java.io.*;

public class StateManager {
    private Stack<State> stateStack;
    private boolean playing;
    private boolean running;
    private LocalTime startRestriction = LocalTime.of(3,0);
    private LocalTime endRestriction = LocalTime.of(3, 0);
    private LocalTime startGame;
    private LocalTime endGame;

    public StateManager() {
        playing = false;
        running = true;
        stateStack = new Stack<State>();
        
        //Getting the current local time
        startGame = LocalTime.now();
        startMenu(); //Starting the main game

        //Getting the time the user ends the game
        endGame = LocalTime.now();
        
        //Calculating the difference in time
        long duration = startGame.until(endGame, ChronoUnit.MINUTES);

        Random random = new Random();
        int length = random.nextInt(16 - 2 + 1) + 2;
        
        //Adding the time to the file
        addTime((double)length, "timeStats.txt");
    }

    /**
     * Adding the updated time stats
     * 
     * @param duration How long the user played this session
     */
    private void addTime(double duration, String file) {
        try {
            File fileobj = new File(file);
            //Reading the information in the file will have 3 variables
            BufferedReader reader = new BufferedReader(new FileReader(fileobj));
            double totalTime = Double.parseDouble(reader.readLine());
            double avgTime = Double.parseDouble(reader.readLine());
            int numTimes = Integer.parseInt(reader.readLine());
            reader.close();

            //Doing the time calculations required for each time the user plays
            numTimes++; //Add one to numTimes
            totalTime += duration;
            avgTime = Math.round(totalTime / (double)numTimes * 100.0) / 100.0;
            
            //Writing back to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileobj));
            writer.write(Double.toString(totalTime));
            writer.newLine();
            writer.write(Double.toString(avgTime));
            writer.newLine();
            writer.write(Integer.toString(numTimes));
            writer.close();
        } catch (IOException e) {
            System.out.println("IO Exception - Statemanager time");
        } catch (Exception e) {
            System.out.println("Non IOException - Statemanager time" + e.getMessage());
        }
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

    public LocalTime getStartRestriction() {
        return startRestriction;
    }

    public LocalTime getEndRestriction() {
        return endRestriction;
    }


    private void setStartRestriction(int hour, int min) {
        startRestriction = LocalTime.of(hour, min);
    }
    private void setEndRestriction(int hour, int min) {
        endRestriction = LocalTime.of(hour, min);
    }

    public boolean getPlaying() { return playing; }

    public boolean getRunning() { return running; }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public static void main(String[] args) {
        StateManager sg = new StateManager();
    }

}
