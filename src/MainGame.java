import java.util.*;
import javax.swing.*;
import java.util.Timer;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;


public class MainGame extends state
{
	/**
	 * StateManager for this class
	 */
	private stateManager manager;
	/**
	 * The Pet
	 */
	private Pet pet;
	/**
	 * Timer
	 */
	private Timer timer;
	/**
	 * Timer On/off
	 */
	private boolean timerOn;
	/**
	 * How many seconds before an action is taken
	 */
	private int perSecond;
	/**
	 * health value segments
	 */
	private int health;
	/**
	 * Happiness segment
	 */
	private int happy;
	/**
	 * Sleep segment
	 */
	private int sleep;
	/**
	 * Hunger segment
	 */
	private int hunger;

	
	/**
	 * constructor for the class MainGame
	 * @param manage
	 * @param pet
	 * @calls 
	 */
	public MainGame(stateManager manage, Pet pet)
	{
		//set pet and manager
		this.pet = pet;
		this.manager = manage;
		//scaling for happy and stuff
		happy=10;
		sleep=10;
		hunger=10;
		health=10;
		//5 seconds per action in timer (perSecond scales on milliseconds)
		perSecond = 5000;
		createTimer();

	}
	
	/**
	 * Timer method to make
	 */
	private void createTimer()
	{
		TimerTask = new TimerTask();
	}
	
	/**
	 * Method to force the pet to sleep, decreases less hunger than normal sleeping
	 */
	private void deepSleep()
	{
		//until sleep =
		while(pet.getSleep() != 100)
		{
			pet.updateStats(0, 0, sleep, 0);
		}
	}
	/**
	 * The pet will do exercise
	 */
	private void exerise()
	{
		//pet actions
		pet.updateStats(2, happy, -sleep, -hunger);
		if(pet.isTired())
		{
			deepSleep();
		}
		pet.setMoney(pet.getMoney()+10);
	}
	/**
	 * Pet will sleep
	 */
	private void sleep()
	{
		//pet sleep
		pet.updateStats(0, happy, sleep, -hunger);
		pet.setMoney(pet.getMoney()+10);
	}
	/**
	 * Pet will be fed
	 */
	private void feed()
	{
		//feed
		pet.updateStats(health, happy, sleep, hunger);
		pet.setMoney(pet.getMoney()+10);
	}
	/**
	 * Pet will be given gifts
	 */
	private void giveGifts()
	{
		pet.updateStats(health, happy, sleep, hunger);
	}
	/**
	 * Play with pet
	 */
	private void play()
	{
		pet.updateStats(health, happy, sleep, hunger);
		pet.setMoney(pet.getMoney()+10);
	}
	/**
	 * Bring pet to vet
	 */
	private void vet()
	{
		pet.updateStats(health*2, happy, sleep, hunger*2);
		//Action to decrease money unknown
		pet.setMoney(pet.getMoney()-20);
	}
	/**
	 * Enter shop 
	 */
	private void enterShop();
	{
		int a = 0;
	}
	/**
	 * save file
	 */
	private void save()
	{
		//save data on file, to be discussed
	}
	/**
	 * render method
	 */
	public void render(){
		JFrame frame = new JFrame("Main Page");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(4, 1, 10, 10)); // Arrange buttons in a vertical grid
        frame.setLocationRelativeTo(null);


		
	}
	public static void main(String[] args)
	{
		stateManager dummy = new stateManager();
		Pet pet = new Pet();
		MainGame hi = new MainGame(dummy, pet);
	}
}
