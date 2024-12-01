import java.util.*;
import javax.swing.*;
import java.util.Timer;
import javax.swing.JFrame;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
/**
 * 
 * Date: NAN
 * @author kevinlam, celica
 *
 */

public class MainGame 
{
	/**
	 * StateManager for this class
	 */
	private StateManager manager;
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
	 * Variable used to listen to keyboard actions
	 */
	KeyAdapter listenBro;
	/**
	 * Message that shows up whenever an action is taken
	 */
	JPanel mess;
	JLabel content;
	Timer delay;
	/**
	 * For statistics of the pet
	 */
	JPanel stats;
	JLabel healthBar;
	JLabel sleepBar;
	JLabel happinessBar;
	JLabel fullnessBar;
	JLabel scoreBar;
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
	 * Panel for displaying pet items
	 */
	JPanel itemMenu;
	
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
	public MainGame(StateManager manage, Pet pet)
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
		render();
		createTimer();




	}
	
	/**
	 * Timer method to make the timer that decreases pet stats every 5 seconds to simulate time
	 */
	private void createTimer()
	{
		//every 5 seconds, decrease all stats except health. Do a action check
		TimerTask nature = new TimerTask() {
			
			@Override
			public void run()
			{
				pet.updateStats(0, -1, -1, -1);
				//check if we need to deduct health, actionCheck has an update call
				actionCheck();
				update();
			}
		};
		
		timer.scheduleAtFixedRate(nature, 0, interval);
		
	}
	
	
	/**
	 * Checks if the pet has died
	 */
	private void deathCheck()
	{
		if(!pet.isAlive())
		{
			timer.cancel();
			frame.dispose();
			SaveGame Dam = new SaveGame(manager, pet, true);
			//TODO: Go to death screen
			
		}
		
	}
	/**
	 * helper method
	 */
	private void actionCheck()
	{
		//Decrease health, update it to the player, checks if we are in the death screen
		if(pet.getHappiness()==0 || pet.getHunger() ==0)
		{
			displayMessage("you're losing health!");
			pet.updateStats(-10, 0, 0, 0);
			update();
			deathCheck();
		}
	}
	
	/**
	 * Whenever the method is called, show the message in the parameter to the player, after 5 seconds, the message will be closed.
	 * @param message
	 */
	private void displayMessage(String message)
	{
		//whenever the method is called, create a timer
		delay.cancel();
		delay = new Timer();
		TimerTask tempTask = new TimerTask() {
			
			@Override
			public void run()
			{
				mess.setVisible(false);
				
			}
		};
		//show message
		content.setText(message);
		mess.setVisible(true);
		//close messsage after 5 seconds
		delay.schedule(tempTask, 3000);
		
	}
	
	
	/**
	 * Makes the pet sleep until its sleep bar is full
	 */
	public void deepSleep()
	{
		//disables button when sleeping
		//WARNING: This method has a very horrible hack to disable keyboard input
	       sleep.setEnabled(false);
	       exerise.setEnabled(false);
	       feed.setEnabled(false);
	       play.setEnabled(false);
	       vet.setEnabled(false);
	       gifts.setEnabled(false);
	       shop.setEnabled(false);
	       save.setEnabled(false);
	       frame.removeKeyListener(listenBro);
	       
		//create timer that periodically updates pet stats, and checks if sleep is full
	       //tempted to recycle delay, not sure how it works
		Timer tempTimer = new Timer();
		TimerTask tempTask = new TimerTask() {
			@Override
			public void run()
			{
				
				pet.updateStats(0, 0, sleepiness, 0);
				update();
				if(pet.getSleep()>=100)
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
				       frame.addKeyListener(listenBro);
				    //cancel timer   
					tempTimer.cancel();
				}

			}
		};
		//until sleep = 100, increase sleep by 10 per 2 seconds
		tempTimer.scheduleAtFixedRate(tempTask, 0, 2000);
	}
	
	/**
	 * Initiates the process of sleeping
	 */
	private void sleep()
	{
		//pet goes to sleep
		displayMessage("Sleep is now sleeping...");
		deepSleep();
		pet.setMoney(30);
		update();

	}
	
	/**
	 * The pet will do exercise, making them more happy. Give 15 coins to player.
	 * WARNING: Spamming this can insta-kill you
	 */
	private void exerise()
	{
		displayMessage("Pet has finished exerising!");
		pet.updateStats(0, happy, 0-sleepiness, 0-hunger*2);
		actionCheck();
		pet.setMoney(15);
		update();

	}

	/**
	 * Pet will be fed, more shall be discussed
	 */
	private void feed()
	{
		
		//TODO: build a panel to display that allows user to use food items
		itemMenu = new JPanel();
		itemMenu.setBackground(Color.red);
		
		//Mandate option: I don't want to give gifts
				JButton goAway = new JButton("exit");
				goAway.addActionListener(new ActionListener() {
					//makes menu go away
					public void actionPerformed(ActionEvent e)
					{
						frame.remove(itemMenu);
						frame.repaint();
						//makes itemMenu nothing again
						itemMenu = null;
					}
					
				});
				itemMenu.add(goAway);
				
		//Optional option: Choose a item to feed to pet
		HashMap<Items, Integer> map = pet.getInventory();
		for(Items item: map.keySet())
		{
			Integer number = map.get(item);
			if(item.getFoodBoost()>0 && number >0)
			{
				JButton anItem = new JButton(item.getName());
				anItem.addActionListener( new ActionListener () {
					@Override
					 public void actionPerformed(ActionEvent e)
					 {
						item.use(pet);
						frame.remove(itemMenu);
						itemMenu = null;
					 }
				}

						);
				itemMenu.add(anItem);
			}
		}
		
		//display
		itemMenu.setVisible(true);
		frame.add(itemMenu, BorderLayout.WEST);
		//pet.removeItem();
		update();
		displayMessage("Pet successfully fed!");
		
		
	}
	/**
	 * Pet will be given gifts of choice
	 */
	//PROBLEM: Spamming this will disable this from working
	private void giveGifts()
	{
		itemMenu = new JPanel();
		itemMenu.setBackground(Color.red);
		HashMap<Items, Integer> map = pet.getInventory();
		
		//Mandate option: I don't want to give gifts
		JButton goAway = new JButton("Leave");
		goAway.addActionListener(new ActionListener() {
			//makes menu go away
			public void actionPerformed(ActionEvent e)
			{
				frame.remove(itemMenu);
				frame.repaint();
				//makes itemMenu nothing again
				itemMenu = null;
			}
			
		});
		itemMenu.add(goAway);
		
		//Optional option: choose a gift
		for(Items item: map.keySet())
		{
			Integer number = map.get(item);
			if(item.getHappyBoost()>0 && number >0)
			{
				JButton anItem = new JButton(item.getName());
				anItem.addActionListener( new ActionListener () {
					@Override
					 public void actionPerformed(ActionEvent e)
					 {
						//use item, remove the menu, 
						item.use(pet);
						displayMessage("Your pet received your gift!!");
						frame.remove(itemMenu);
						frame.repaint();
						//makes itemMenu nothing again
						itemMenu = null;
					 }
				}

						);
				itemMenu.add(anItem);
			}
		}
		//set menu visible
		itemMenu.setVisible(true);
		frame.add(itemMenu, BorderLayout.WEST);
		update();
	}
	/**
	 * Play with pet, increase money by 15
	 */
	private void play()
	{
		displayMessage("Played with your pet!");
		pet.updateStats(0, happy*2, -sleepiness*2, -hunger);
		actionCheck();
		pet.setMoney(15);
		update();
	}
	/**
	 * Bring pet to vet, restores everything
	 * WARNING: No delay, spamming this can take all your money
	 */
	private void vet()
	{
		displayMessage("Pet has returned from the vet");
		pet.updateStats(100, 100, 100, 100);
		//NOTE: Not sure what to do here
		pet.setMoney(-50);
		update();
	}
	/**
	 * Enter shop 
	 */
	private void enterShop()
	{
		Shop shop= new Shop(manager, pet);
		shop.render();
	}
	/**
	 * save file
	 */
	private void save()
	{
		//save data on file, to be discussed
		//TODO:Display stuff/ save
		frame.dispose();
		SaveGame aww = new SaveGame(manager, pet, false);
		
		
	}
	
	
	/**
	 * method to update the displayed statistics for the player
	 */
	private void update()
	{
		healthBar.setText(String.valueOf(pet.getHealth()));
		sleepBar.setText(String.valueOf(pet.getSleep()));
		happinessBar.setText(String.valueOf(pet.getHappiness()));
		fullnessBar.setText(String.valueOf(pet.getHunger()));
		scoreBar.setText(String.valueOf(pet.getMoney()));
		stats.repaint();
	}
	/**
	 * Sets up the Action Listen 
	 */
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
        
        //Panel for statistic of pets
        stats = new JPanel();
        stats.setBackground(Color.decode("#9BB641"));
		stats.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		stats.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Add labels for each stat - Ceclia
		JLabel healthLabel = new JLabel("Health: ");
		JLabel sleepLabel = new JLabel("Sleep: ");
		JLabel happinessLabel = new JLabel("Happiness: ");
		JLabel fullnessLabel = new JLabel("Hunger: ");
		JLabel scoreLabel = new JLabel("Score: ");

		//Values of each bar
        healthBar = new JLabel(String.valueOf(pet.getHealth()));
        sleepBar = new JLabel(String.valueOf(pet.getSleep()));
        happinessBar = new JLabel(String.valueOf(pet.getHappiness()));
        fullnessBar = new JLabel(String.valueOf(pet.getHunger()));
        scoreBar = new JLabel(String.valueOf(pet.getMoney()));
        
        
		// Add labels and values side by side - Ceclia
		stats.add(createStatRow(healthLabel, healthBar));
		stats.add(createStatRow(sleepLabel, sleepBar));
		stats.add(createStatRow(happinessLabel, happinessBar));
		stats.add(createStatRow(fullnessLabel, fullnessBar));
		stats.add(createStatRow(scoreLabel, scoreBar));
				
       
        

      //Creates the ActionListener needed to react to keyboard inputs
        //This section rules over how the game reacts to keyboard presses
        //TODO: Unga Bunga the keyrelease method to existence
        listenBro = new KeyAdapter() {
        	
        	@Override
        	public void keyPressed(KeyEvent e)
        	{
        		switch(e.getKeyChar())
        		{
        		case 's': enterShop(); 
        		break;
        		case 'e': exerise(); 
        		break;
        		case 'v': vet(); 
        		break;
        		case 'p': play();
        		break;
        		case 'f': feed();
        		break;
        		case 'z': sleep();
        		break;
        		//esc
        		case 27: save(); save.setBackground(Color.white);
        		break;
        		}
        	}

        	
        };
		frame.addKeyListener(listenBro);
        frame.setFocusable(true);
        
        
        //Setup to display messages
        delay = new Timer();
		mess= new JPanel();
		mess.setSize(new Dimension(50, 100));
		mess.setBackground(Color.BLACK);
		content = new JLabel("default");
		content.setFont(new Font("Serif",Font.PLAIN,22));
		content.setForeground(Color.white);
		mess.add(content);
		mess.setVisible(false);


		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0)); // Center align the stats panel
		topPanel.add(stats);
		topPanel.add(Box.createVerticalStrut(100));
		topPanel.add(mess);
		// Add stats panel to the center of the frame
		frame.add(topPanel, BorderLayout.CENTER);
		// frame.add(mess, BorderLayout.NORTH);
			
        
        //Set panel for menu
        JPanel menu = new JPanel();
        menu.setBorder(BorderFactory.createEmptyBorder(10,5,15,5));
        menu.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        menu.setBackground(Color.decode("#414643"));
		
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
        exerise = new JButton("Exercise");
        exerise.addActionListener(ea -> exerise());
        feed = new JButton("Feed Pet");
        feed.addActionListener(ea -> feed());
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
        
		JPanel savePanel = new JPanel();
		savePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0)); // Align to the left
		savePanel.add(save);

		// Add the "Save Game/Exit" button panel to the top-left corner
		frame.add(savePanel, BorderLayout.NORTH);

		menu.add(exerise);
		menu.add(sleep);
        menu.add(feed);
        menu.add(play);
        menu.add(vet);
        menu.add(gifts);
        menu.add(shop);


        frame.add(menu, BorderLayout.SOUTH);
          
        //display frame
        
        frame.setVisible(true);
		
	}

	// Helper method to create a horizontal row
	private JPanel createStatRow(JLabel label, JLabel value) {
		JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Align rows to the left
		row.setBackground(Color.decode("#9BB641")); // Match panel background
		row.add(label);
		row.add(value);
		return row;
	}

	
	public static void main(String[] args)
	{
		StateManager dummy = new StateManager();
		Pet pet = new Pet("sup", 100, 100, 100, 100, 100, "Wuliaonie");
		MainGame test = new MainGame(dummy, pet);
		
	}


//For some reason JPanel is really messed up when trying to set it up purely by code
	//If anyone is using netbeans or other IDE that has the ability to edit JSwing visually please help me out
		//resolved

}
