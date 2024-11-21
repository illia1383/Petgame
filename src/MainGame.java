import Pet
import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class MainGame extends state
{
	/**
	 * The Pet
	 */
	private Pet pet;
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
	 */
	public MainGame(stateManager manage, Pet pet)
	{
		this.pet = pet;
		happy=10;
		sleep=10;
		hunger=10;
		health=10;
	}
	/**
	 * The pet will do exercise
	 */
	private exerise()
	{
		//pet actions
		pet.updateStats(+health, +happy, -sleep, -hunger);
		pet.getMoney(10);
	}
	/**
	 * Pet will sleep
	 */
	private sleep()
	{
		//pet sleep
		pet.updateStats(health, happy, sleep, hunger);
		pet.getMoney(10);
	}
	/**
	 * Pet will be fed
	 */
	private feed()
	{
		//feed
		pet.updateStats(health, happy, sleep, hunger);
		pet.getMoney(10);
	}
	/**
	 * Pet will be given gifts
	 */
	private giveGifts()
	{
		pet.updateStats(health, happy, sleep, hunger);
		pet.getMoney(10);
	}
	/**
	 * Play with pet
	 */
	private play()
	{
		pet.updateStats(health, happy, sleep, hunger);
		pet.getMoney(10);
	}
	/**
	 * Bring pet to vet
	 */
	private vet()
	{
		pet.updateStats(health*2, happy, sleep, hunger*2);
		//Action to decrease money unknown
		pet.getMoney(10);
	}
	/**
	 * Enter shop 
	 */
	private enterShop();
	{

	}
	/**
	 * save file
	 */
	private save()
	{
		
	}
	/**
	 * render method
	 */
	void render() {
		
	}
}
