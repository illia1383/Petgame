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
	 * Frame for display
	 */
	JFrame frame;
	/**
	 * Message that shows up whenever an action is taken
	 */
	JPanel mess;
	JLabel content;
	/**
	 * Buttons for the page
	 */
	JButton sleep;
	JButton exerise;
	JButton feed;
	JButton play;
	JButton vet;
	JButton gifts;
	JButton shop;
	JButton save;
	JButton [] buttons;
	
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
	private int sleepiness;
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
		sleepiness=5;
		hunger=5;
		health=5;
		//5 seconds per action in timer (perSecond scales on milliseconds)
		interval = 5000;
		this.timer = new Timer();
		createTimer();
		render();

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
		save();
		
	}
	
	/**
	 * Makes the pet sleep until its sleep bar is full
	 */
	public void deepSleep()
	{
		//buttons setEnable
	       sleep.setEnabled(false);
	       exerise.setEnabled(false);
	       feed.setEnabled(false);
	       play.setEnabled(false);
	       vet.setEnabled(false);
	       gifts.setEnabled(false);
	       shop.setEnabled(false);
	       save.setEnabled(false);
		
		Timer tempTimer = new Timer();
		TimerTask tempTask = new TimerTask() {
			@Override
			public void run()
			{
				
				pet.updateStats(0, 0, sleepiness, 0);
				System.out.println(pet.getSleep());
				if(pet.getSleep()==100)
				{
					//buttons enable
				       sleep.setEnabled(true);
				       exerise.setEnabled(true);
				       feed.setEnabled(true);
				       play.setEnabled(true);
				       vet.setEnabled(true);
				       gifts.setEnabled(true);
				       shop.setEnabled(true);
				       save.setEnabled(true);
				       
					tempTimer.cancel();
				}

			}
		};
		//TODO: disable timer
		//until sleep = 100, increase sleep by 10 per 2 seconds
		tempTimer.scheduleAtFixedRate(tempTask, 0, 2000);
	}
	/**
	 * The pet will do exercise, making them more happy. Give 15 coins to player
	 */
	private void exerise()
	{
		pet.updateStats(0, happy, -sleepiness, -hunger*2);
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
		//TODO: call pet for inventory
		//pet.removeItem();
		
	}
	/**
	 * Pet will be given gifts, more will be discussed due to consumables
	 */
	private void giveGifts()
	{
		pet.updateStats(health, happy, sleepiness, hunger);
	}
	/**
	 * Play with pet, increase money by 15
	 */
	private void play()
	{
		pet.updateStats(0, happy*2, -sleepiness*2, -hunger);
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
		//TODO:Display stuff/ save
		
	}
	/**
	 * render method
	 */
	public void render(){
		//frame
		frame = new JFrame();
		frame.setTitle("MainPage");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2000, 1000);
        frame.setBackground(Color.gray);
        
        //TODO: Display healthbar
        
        /*
         sleep
 		 excerise
         feed
         play
         vet
         gifts
         shop
         save
         */
        //Button setup
        sleep = new JButton("Sleep");
        sleep.addActionListener(ea -> sleep());
        exerise = new JButton("Excerise");
        exerise.addActionListener(ea -> exerise());
        feed = new JButton("Feed Pet");
        feed.addActionListener(ea -> exerise());
        play = new JButton("Play");
        play.addActionListener(ea -> play());
        vet = new JButton("Go to vet (-50 Coins)");
        vet.addActionListener( ea -> vet());
        gifts = new JButton("Gift Pet");
        gifts.addActionListener(ea -> giveGifts());
        shop = new JButton("Enter Shop");
        shop.addActionListener(ea -> enterShop());
        save = new JButton("Save Game/Exit");
        save.addActionListener(ea -> save());
        
        //messages
		mess= new JPanel();
		mess.setSize(new Dimension(50, 100));
		mess.setBackground(Color.BLACK);
		content = new JLabel("default");
		content.setFont(new Font("Serif",Font.PLAIN,22));
		mess.add(content);
		mess.setVisible(true);
		
        
        
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
        frame.add(mess, BorderLayout.NORTH);
        
        //setup actions
        
        frame.show();
        

		
	}
	public static void main(String[] args)
	{
		stateManager dummy = new stateManager();
		Pet pet = new Pet();
		MainGame hi = new MainGame(dummy, pet);
	}
}
