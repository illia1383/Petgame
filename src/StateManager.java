import java.time.*;
import java.time.temporal.*;
import java.util.*;
import java.io.*;

public class StateManager {
    private Stack<State> stateStack; /**The stack holding the states that are run */
    private LocalTime startRestriction = null; /**The start of the time restriction when the user cannot play */
    private LocalTime endRestriction = null; /**The end of the time restriction when the user cannot play */
    private LocalTime startGame; /**The time the user starts playing the game */
    private LocalTime endGame; /**The time the user closes the game */

    /**
     * Constructor that initializes the statestack, sets the time and starts the game
     */
    public StateManager() {
        stateStack = new Stack<State>();
        
        //Getting the current local time
        startGame = LocalTime.now();
        startMenu(); //Starting the main game
    }

    /**
     * Starting the game by running the Title State
     */
    public void startMenu() {
        Title titleState = new Title(this);
        titleState.render(); 
    }


    /**
     * Adding the updated time stats
     * 
     * @param duration How long the user played this session
     */
    public void addTime() {
        try {
            File fileobj = new File("src/timeStats.txt");
            //Reading the information in the file will have 3 variables
            BufferedReader reader = new BufferedReader(new FileReader(fileobj));
            double totalTime = Double.parseDouble(reader.readLine());
            double avgTime = Double.parseDouble(reader.readLine());
            int numTimes = Integer.parseInt(reader.readLine());
            reader.close();

            //Finding the duration of the the time betwen starting and ending the game
            long duration = startGame.until(endGame, ChronoUnit.MINUTES);

            //Doing the time calculations required for each time the user plays
            numTimes++; //Add one to numTimes
            totalTime += (double)duration;
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
    
    /**
     * Set the time the game ends
     * 
     * @param end
     */
    public void setEndGame(LocalTime end) {
        endGame = end;
    }

    /**
     * Get the start of the time the user cannot play the game
     * 
     * @return LocalTime The start of the restriction
     */
    public LocalTime getStartRestriction() {
        return startRestriction;
    }

    /**
     * Get the end of the time the user cannot play the game
     * 
     * @return LocalTime The end of the restriction
     */
    public LocalTime getEndRestriction() {
        return endRestriction;
    }

    /**
     * Set the start of the time the user cannot play the game
     * 
     * @param hour The hour the user cannot play
     * @param min The minute the user cannot play
     */
    public void setStartRestriction(int hour, int min) {
        startRestriction = LocalTime.of(hour, min);
    }
    /**
     * Set the end of the time the user cannot play the game
     * 
     * @param hour The hour the user cannot play
     * @param min The minute the user cannot play
     */
    public void setEndRestriction(int hour, int min) {
        endRestriction = LocalTime.of(hour, min);
    }

    public static void main(String[] args) {
        StateManager sg = new StateManager();
    }

}
