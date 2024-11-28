import java.util.*;
import javax.swing.*;
import java.util.Timer;
import javax.swing.JFrame;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private int interval;
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
		//set pet and manager
		this.pet = pet;
		this.manager = manage;
		//scaling for happy and stuff
		happy=5;
		sleep=5;
		hunger=5;
		health=5;
		//5 seconds per action in timer (perSecond scales on milliseconds)
		interval = 5000;
		this.timer = new Timer();
		createTimer();

	}
	
	/**
	 * Timer method to make the timer that decreases pet stats every 5 seconds to simulate time
	 */
	private void createTimer()
	{
		TimerTask nature = new TimerTask() {
			
			@Override
			public void run()
			{
				pet.updateStats(0, -1, -1, -1);
				System.out.println("hi");
				//if the pet is detected to be dead, cancel timer and do death animation
				//switch(pet.sprite())
				//case 1 cancel
				
			}
		};
		//every 5 seconds, decrease these things
		timer.scheduleAtFixedRate(nature, 0, interval);
		
	}
	//private method for death
	private void youDied()
	{
		//not sure what to do yet;
		timer.cancel();
		//TODO: Call death screen class
		
	}
	
	/**
	 * Makes the pet sleep until its sleep bar is full
	 */
	public void deepSleep()
	{
		Timer tempTimer = new Timer();
		TimerTask tempTask = new TimerTask() {
			@Override
			public void run()
			{
				
				pet.updateStats(0, 0, sleep, 0);
				System.out.println(pet.getSleep());
				if(pet.getSleep()==100)
				{
					tempTimer.cancel();
					System.out.println("cancelled");
				}

			}
		};
		//TODO: Disable timer
		//until sleep = 100, increase sleep by 10 per 2 seconds
		tempTimer.scheduleAtFixedRate(tempTask, 0, 2000);
	}
	/**
	 * The pet will do exercise, making them more happy. Give 15 coins to player
	 */
	private void exerise()
	{
		pet.updateStats(0, happy, -sleep, -hunger*2);
		pet.setMoney(15);
	}
	/**
	 * Initiates the process of deep sleep
	 */
	private void sleep()
	{
		//pet goes to sleep
		deepSleep();
		pet.setMoney(30);
	}
	/**
	 * Pet will be fed, more shall be discussed
	 */
	private void feed()
	{
		//feed pet
		pet.updateStats(health, happy, sleep, hunger);
		//lose money
		pet.setMoney(+10);
	}
	/**
	 * Pet will be given gifts, more will be discussed due to consumables
	 */
	private void giveGifts()
	{
		pet.updateStats(health, happy, sleep, hunger);
	}
	/**
	 * Play with pet, increase money by 15
	 */
	private void play()
	{
		pet.updateStats(0, happy*2, -sleep*2, -hunger);
		pet.setMoney(15);
	}
	/**
	 * Bring pet to vet, recovers everything
	 */
	private void vet()
	{
		pet.updateStats(100, 100, 100, 100);
		//Action to decrease money unknown
		pet.setMoney(-50);
	}
	/**
	 * Enter shop 
	 */
	private void enterShop()
	{
		Shop shop= new Shop();
		shop.render();
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
		//frame
		JFrame frame = new JFrame();
		frame.setTitle("MainPage");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2000, 1000);
        frame.setBackground(Color.gray);
        
        //setup buttons
        JButton sleep = new JButton("Sleep");
        sleep.addActionListener(ea -> sleep());
        JButton exerise = new JButton("Excerise");
        exerise.addActionListener(ea -> exerise());
        JButton feed = new JButton("Feed Pet");
        feed.addActionListener(ea -> exerise());
        JButton play = new JButton("Play");
        play.addActionListener(ea -> play());
        JButton vet = new JButton("Go to vet (-50 Coins)");
        vet.addActionListener( ea -> vet());
        JButton gifts = new JButton("Gift Pet");
        gifts.addActionListener(ea -> giveGifts());
        JButton shop = new JButton("Enter Shop");
        shop.addActionListener(ea -> enterShop());
        JButton save = new JButton("Save Game");
        save.addActionListener(ea -> save());
        
        
        //Set panel for menu
        JPanel menu = new JPanel();
        menu.setBorder(BorderFactory.createEmptyBorder(10,5,15,5));
        menu.setLayout(new BoxLayout(menu, BoxLayout.X_AXIS));
        menu.setBackground(Color.black);
        menu.add(sleep);
        menu.add(exerise);
        menu.add(feed);
        menu.add(play);
        menu.add(vet);
        menu.add(gifts);
        menu.add(shop);
        menu.add(save);
        frame.add(menu, BorderLayout.SOUTH);
        
        //setup actions
        
        frame.show();
        

		
	}
	public static void main(String[] args)
	{
		stateManager dummy = new stateManager();
		Pet pet = new Pet();
		MainGame hi = new MainGame(dummy, pet);
		hi.render();
	}
}
