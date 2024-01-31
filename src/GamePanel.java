import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GamePanel extends JPanel implements Runnable{
	
	private int posX = 0, posY = 0;
	private int width = 100, height = 100;
	private final int xSpeed = 5, ySpeed = 5;
	private int PANEL_SIZE = 900; //900x900 px
	private final int FPS = 60;
	
	Thread gameThread;	
	KeyHandler keyHandler = new KeyHandler();
	SpaceShip spaceShip = new SpaceShip(this, keyHandler);
	
	ArrayList<Laser> lasers = new ArrayList<>();
	ArrayList<Alien> aliens = new ArrayList<>();
	ArrayList<AlienLaser> alienLasers = new ArrayList<>();
	ArrayList<PurpleAlien> purpleAliens = new ArrayList<>();
	ArrayList<GreenAlien> greenAliens = new ArrayList<>();
	ArrayList<GreenAlienLaser> greenAlienLasers = new ArrayList<>();
	
	ArrayList<Laser> toRemoveLasers = new ArrayList<>();
	ArrayList<Alien> toRemoveAliens = new ArrayList<>();
	ArrayList<AlienLaser> toRemoveAlienLasers = new ArrayList<>();
	ArrayList<PurpleAlien> toRemovePurpleAliens = new ArrayList<>();
	ArrayList<GreenAlien> toRemoveGreenAliens = new ArrayList<>();
	ArrayList<GreenAlienLaser> toRemoveGreenAlienLasers = new ArrayList<>();
	
	Laser laser;
	Alien alien;
	JLabel score = new JLabel();
	JLabel fps = new JLabel();
	JLabel health = new JLabel();
	JLabel levelLabel = new JLabel();
	JLabel gameOver = new JLabel("GAME OVER");
	Image backgroundImage;
	
	boolean canCreateLaser = false;
	boolean canCreateAlien = false;
	boolean canCreatePurpleAlien = false;
	boolean canCreateGreenAlien = false;
	
	Sound sound = new Sound();
	Sound music = new Sound();
	
	int i= 0, alienCount = 0;
	private static int level = 1, MAX_ALIEN_SIZE = 3;
	
	GamePanel(){
		this.setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
		this.setLayout(null);
		
		score.setText("Score: " + spaceShip.getScore());
		score.setForeground(Color.white);
		score.setFont(new Font("MV Boli", Font.PLAIN, 20));
		score.setBounds(400, 0, 200, 50);
		
		fps.setForeground(Color.white);
		fps.setBounds(800,0,100,50);
		fps.setFont(new Font("MV Boli", Font.PLAIN, 20));
		
		health.setText("Health: " + spaceShip.getHealth());
		health.setForeground(Color.white);
		health.setFont(new Font("MV Boli", Font.PLAIN, 20));
		health.setBounds(0,0,150,50);
		
		levelLabel.setText("Level: " + level);
		levelLabel.setForeground(Color.white);
		levelLabel.setFont(new Font("MV Boli", Font.PLAIN, 15));
		levelLabel.setBounds(0,0,100,100);
		
		gameOver.setForeground(Color.white);
		gameOver.setBounds(350, 300,300,300);
		gameOver.setFont(new Font("MV Boli", Font.PLAIN, 30));
		
		this.add(score);
		this.add(fps);
		this.add(health);
		this.add(levelLabel);
		playMusic(0);
		try {
			backgroundImage = ImageIO.read(getClass().getResourceAsStream("/background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, this);
		
		spaceShip.draw(g);
		
		
		for(Laser l : lasers) {
			l.draw(g);
		}
		
		for(Alien a : aliens)
			a.draw(g);
		
		for(AlienLaser al : alienLasers)
			al.draw(g);
		

		for(PurpleAlien p : purpleAliens)
			p.draw(g);
		
		for(GreenAlien gr : greenAliens)
			gr.draw(g);
		
		for(GreenAlienLaser al : greenAlienLasers)
			al.draw(g);
		
		
	}
	
	public void startGame() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	
	
	public void update() {
		spaceShip.update();
		
		for(Laser l : lasers)
			l.update();
		
		
		for(Alien a : aliens)
			a.update();
		
		for(AlienLaser al : alienLasers)
			al.update();
		
		for(PurpleAlien p : purpleAliens)
			p.update();
		
		for(GreenAlien g : greenAliens)
			g.update();
		
		for(GreenAlienLaser al : greenAlienLasers)
			al.update();
		
	}
	
	public void init() {
		
		
		if(canCreateLaser && keyHandler.isLASER()) {
			laser = new Laser(spaceShip);
			lasers.add(laser);
			canCreateLaser = false;
		}
		
		if(canCreateAlien) {
			if(aliens.size() < MAX_ALIEN_SIZE && level == 1) {
			alien = new Alien(0, 0, 5, 1, 90, 90, this);
			aliens.add(alien);
			canCreateAlien = false;
			}
			else if(aliens.size() < MAX_ALIEN_SIZE && level == 2) {
				alien = new Alien(0, 0, 5, 2, 90, 90, this);
				aliens.add(alien);
				canCreateAlien = false;
			}
			else if(aliens.size() < MAX_ALIEN_SIZE && level == 3) {
				alien = new Alien(0, 0, 6, 2, 90, 90, this);
				aliens.add(alien);
				canCreateAlien = false;
			}
		}
		
		for(Alien a : aliens) {
			if(a.getCanShootLaser()) {
				AlienLaser alienLaser = new AlienLaser(a);
				alienLasers.add(alienLaser);
				a.setCanShootLaser(false);
			}
		}
		
		if(canCreateGreenAlien) {
			greenAliens.add(new GreenAlien(900, 0, -2, 1, 90, 90, this));
			canCreateGreenAlien = false;
		}
		
		for(GreenAlien a : greenAliens) {
			if(a.getCanShootLaser()) {
				greenAlienLasers.add(new GreenAlienLaser(a, 0));
				greenAlienLasers.add(new GreenAlienLaser(a, 1));
				greenAlienLasers.add(new GreenAlienLaser(a, 2));
				a.setCanShootLaser(false);
			}
		}
		
		
		if(level == 2 && canCreatePurpleAlien) {
			for(int i = 0; i < 900; i += 100) {
				purpleAliens.add(new PurpleAlien(i,0,1,100,90));
				canCreatePurpleAlien = false;
			}
			
		}
		
		if(level == 3 && canCreatePurpleAlien) {
			for(int i = 0; i < 900; i += 100) {
				purpleAliens.add(new PurpleAlien(i,0,2,100,90));
				canCreatePurpleAlien = false;
			}
			
		}
		
	}
	
	public void check() {
		for (Iterator<Laser> iterator = lasers.iterator(); iterator.hasNext();) {
		    Laser laser = iterator.next();
		    if(laser.getPosY() + laser.getHeight() < 0) {
		        iterator.remove();
		    }
		}
		
		for(Iterator<Alien> iterator = aliens.iterator(); iterator.hasNext();) {
			Alien alien = iterator.next();
			if(alien.getPosY() > this.getPanelSize()) {
		        iterator.remove();
		    }
		}
		
		for(Iterator<AlienLaser> iterator = alienLasers.iterator(); iterator.hasNext();) {
			AlienLaser alienLaser = iterator.next();
			if(alienLaser.getPosY() > this.getPanelSize()) {
		        iterator.remove();
		    }
		}
		
		for(Iterator<PurpleAlien> iterator = purpleAliens.iterator(); iterator.hasNext();) {
			PurpleAlien purpleAlien = iterator.next();
			if(purpleAlien.getPosY() > this.getPanelSize()) {
		        iterator.remove();
		    }
		}
		
		for(Iterator<GreenAlien> iterator = greenAliens.iterator(); iterator.hasNext();) {
			GreenAlien greenAlien = iterator.next();
			if(greenAlien.getPosY() > this.getPanelSize()) {
		        iterator.remove();
		    }
		}
		
		for(Iterator<GreenAlienLaser> iterator = greenAlienLasers.iterator(); iterator.hasNext();) {
			GreenAlienLaser greenAlienLaser = iterator.next();
			if(greenAlienLaser.getPosY() > this.getPanelSize()) {
		        iterator.remove();
		    }
		}
		
		for(Laser laser : lasers) {
			for(Alien alien : aliens) {
				if(laser.getPosX() < alien.getPosX() + alien.getWidth() && laser.getPosX() + laser.getWidth() > alien.getPosX() && 
						laser.getPosY() < alien.getPosY() + alien.height && laser.getPosY() > alien.getPosY()) {
					playSoundEffect(1);
					spaceShip.setScore(spaceShip.getScore() + 10);
					score.setText("Score: " + spaceShip.getScore());
					alien.setHealth(alien.getHealth() - 1);
					toRemoveLasers.add(laser);
					if(alien.getHealth() == 0)
						toRemoveAliens.add(alien);
				}
			}
		}
		
		for(Laser laser : lasers) {
			for(PurpleAlien purpleAlien : purpleAliens) {
				if(laser.getPosX() < purpleAlien.getPosX() + purpleAlien.getWidth() && laser.getPosX() + laser.getWidth() > purpleAlien.getPosX() && 
						laser.getPosY() < purpleAlien.getPosY() + purpleAlien.height && laser.getPosY() > purpleAlien.getPosY()) {
					playSoundEffect(1);
					spaceShip.setScore(spaceShip.getScore() + 20);
					score.setText("Score: " + spaceShip.getScore());
					purpleAlien.setHealth(purpleAlien.getHealth() - 1);
					toRemoveLasers.add(laser);
					if(purpleAlien.getHealth() == 0)
						toRemovePurpleAliens.add(purpleAlien);
				}
			}
		}
		
		for(Laser laser : lasers) {
			for(GreenAlien greenAlien : greenAliens) {
				if(laser.getPosX() < greenAlien.getPosX() + greenAlien.getWidth() && laser.getPosX() + laser.getWidth() > greenAlien.getPosX() && 
						laser.getPosY() < greenAlien.getPosY() + greenAlien.height && laser.getPosY() > greenAlien.getPosY()) {
					playSoundEffect(1);
					spaceShip.setScore(spaceShip.getScore() + 20);
					score.setText("Score: " + spaceShip.getScore());
					greenAlien.setHealth(greenAlien.getHealth() - 1);
					toRemoveLasers.add(laser);
					if(greenAlien.getHealth() == 0)
						toRemoveGreenAliens.add(greenAlien);
				}
			}
		}
		
		for(AlienLaser al : alienLasers) {
			if(al.getPosX() + al.getWidth() > spaceShip.getPosX() && al.getPosX() < spaceShip.getPosX() + spaceShip.getWidth() && al.getPosY() > spaceShip.getPosY() &&
					al.getPosY() + al.getHeight() < spaceShip.getPosY() + spaceShip.getHeight()) {
				spaceShip.setHealth(spaceShip.getHealth() - 1);
				toRemoveAlienLasers.add(al);
				health.setText("Health: " + spaceShip.getHealth());
				playSoundEffect(2);
			}
		}
		
		for(PurpleAlien al : purpleAliens) {
			if(al.getPosX() + al.getWidth() > spaceShip.getPosX() && al.getPosX() < spaceShip.getPosX() + spaceShip.getWidth() && al.getPosY() + al.getHeight() > spaceShip.getPosY() &&
					al.getPosY() + al.getHeight() < spaceShip.getPosY() + spaceShip.getHeight()) {
				spaceShip.setHealth(spaceShip.getHealth() - 1);
				health.setText("Health: " + spaceShip.getHealth());
				toRemovePurpleAliens.add(al);
				playSoundEffect(2);
			}
			else if(al.getPosX() + al.getWidth() > spaceShip.getPosX() && al.getPosX() < spaceShip.getPosX() + spaceShip.getWidth() &&
					al.getPosY() < spaceShip.getPosY() + spaceShip.getHeight() && spaceShip.getPosY() + spaceShip.getHeight() < al.getPosY() + al.getHeight()) {
				spaceShip.setHealth(spaceShip.getHealth() - 1);
				health.setText("Health: " + spaceShip.getHealth());
				toRemovePurpleAliens.add(al);
				playSoundEffect(2);
			}
		}
		
		
		for(GreenAlien a : greenAliens) {
			if(spaceShip.getPosX() > a.getPosX() && spaceShip.getPosX() < a.getPosX() + a.getWidth() &&    // Hitting aliens from bottom right
					spaceShip.getPosY() < a.getPosY() + a.getHeight() && spaceShip.getPosY() > a.getPosY()) {
				spaceShip.setHealth(spaceShip.getHealth() - 1);
				toRemoveGreenAliens.add(a);
				health.setText("Health: " + spaceShip.getHealth());
				playSoundEffect(2);
			}
			else if(spaceShip.getPosX() > a.getPosX() && spaceShip.getPosX() < a.getPosX() + a.getWidth() &&    // Hitting aliens from upper right
					spaceShip.getPosY() + spaceShip.getHeight() < a.getPosY() + a.getHeight() && spaceShip.getPosY() + spaceShip.getHeight() > a.getPosY()) {
				spaceShip.setHealth(spaceShip.getHealth() - 1);
				toRemoveGreenAliens.add(a);
				health.setText("Health: " + spaceShip.getHealth());
				playSoundEffect(2);
			}
			else if(spaceShip.getPosX() + spaceShip.getWidth() > a.getPosX() && spaceShip.getPosX() + spaceShip.getWidth() < a.getPosX() + a.getWidth() &&   // Hitting aliens from bottom left
					spaceShip.getPosY() < a.getPosY() + a.getHeight() && spaceShip.getPosY() > a.getPosY()) {
				spaceShip.setHealth(spaceShip.getHealth() - 1);
				toRemoveGreenAliens.add(a);
				health.setText("Health: " + spaceShip.getHealth());
				playSoundEffect(2);
			}
			else if(spaceShip.getPosX() + spaceShip.getWidth() > a.getPosX() && spaceShip.getPosX() + spaceShip.getWidth() < a.getPosX() + a.getWidth() &&   // Hitting aliens from bottom left
					spaceShip.getPosY() + spaceShip.getHeight() < a.getPosY() + a.getHeight() && spaceShip.getPosY() + spaceShip.getHeight() > a.getPosY()) {
				spaceShip.setHealth(spaceShip.getHealth() - 1);
				toRemoveGreenAliens.add(a);
				health.setText("Health: " + spaceShip.getHealth());
				playSoundEffect(2);
			}
		}
		
		
		
		for(Alien a : aliens) {
			if(spaceShip.getPosX() > a.getPosX() && spaceShip.getPosX() < a.getPosX() + a.getWidth() &&    // Hitting aliens from bottom right
					spaceShip.getPosY() < a.getPosY() + a.getHeight() && spaceShip.getPosY() > a.getPosY()) {
				spaceShip.setHealth(spaceShip.getHealth() - 1);
				toRemoveAliens.add(a);
				health.setText("Health: " + spaceShip.getHealth());
				playSoundEffect(2);
			}
			else if(spaceShip.getPosX() > a.getPosX() && spaceShip.getPosX() < a.getPosX() + a.getWidth() &&    // Hitting aliens from upper right
					spaceShip.getPosY() + spaceShip.getHeight() < a.getPosY() + a.getHeight() && spaceShip.getPosY() + spaceShip.getHeight() > a.getPosY()) {
				spaceShip.setHealth(spaceShip.getHealth() - 1);
				toRemoveAliens.add(a);
				health.setText("Health: " + spaceShip.getHealth());
				playSoundEffect(2);
			}
			else if(spaceShip.getPosX() + spaceShip.getWidth() > a.getPosX() && spaceShip.getPosX() + spaceShip.getWidth() < a.getPosX() + a.getWidth() &&   // Hitting aliens from bottom left
					spaceShip.getPosY() < a.getPosY() + a.getHeight() && spaceShip.getPosY() > a.getPosY()) {
				spaceShip.setHealth(spaceShip.getHealth() - 1);
				toRemoveAliens.add(a);
				health.setText("Health: " + spaceShip.getHealth());
				playSoundEffect(2);
			}
			else if(spaceShip.getPosX() + spaceShip.getWidth() > a.getPosX() && spaceShip.getPosX() + spaceShip.getWidth() < a.getPosX() + a.getWidth() &&   // Hitting aliens from bottom left
					spaceShip.getPosY() + spaceShip.getHeight() < a.getPosY() + a.getHeight() && spaceShip.getPosY() + spaceShip.getHeight() > a.getPosY()) {
				spaceShip.setHealth(spaceShip.getHealth() - 1);
				toRemoveAliens.add(a);
				health.setText("Health: " + spaceShip.getHealth());
				playSoundEffect(2);
			}
		}
		
		for(GreenAlienLaser a : greenAlienLasers) {
			if(spaceShip.getPosX() > a.getPosX() && spaceShip.getPosX() < a.getPosX() + a.getWidth() &&    
					spaceShip.getPosY() < a.getPosY() + a.getHeight() && spaceShip.getPosY() > a.getPosY()) {
				spaceShip.setHealth(spaceShip.getHealth() - 1);
				toRemoveGreenAlienLasers.add(a);
				health.setText("Health: " + spaceShip.getHealth());
				playSoundEffect(2);
			}
			else if(spaceShip.getPosX() > a.getPosX() && spaceShip.getPosX() < a.getPosX() + a.getWidth() &&    
					spaceShip.getPosY() + spaceShip.getHeight() < a.getPosY() + a.getHeight() && spaceShip.getPosY() + spaceShip.getHeight() > a.getPosY()) {
				spaceShip.setHealth(spaceShip.getHealth() - 1);
				toRemoveGreenAlienLasers.add(a);
				health.setText("Health: " + spaceShip.getHealth());
				playSoundEffect(2);
			}
			else if(spaceShip.getPosX() + spaceShip.getWidth() > a.getPosX() && spaceShip.getPosX() + spaceShip.getWidth() < a.getPosX() + a.getWidth() &&   
					spaceShip.getPosY() < a.getPosY() + a.getHeight() && spaceShip.getPosY() > a.getPosY()) {
				spaceShip.setHealth(spaceShip.getHealth() - 1);
				toRemoveGreenAlienLasers.add(a);
				health.setText("Health: " + spaceShip.getHealth());
				playSoundEffect(2);
			}
			else if(spaceShip.getPosX() + spaceShip.getWidth() > a.getPosX() && spaceShip.getPosX() + spaceShip.getWidth() < a.getPosX() + a.getWidth() &&  
					spaceShip.getPosY() + spaceShip.getHeight() < a.getPosY() + a.getHeight() && spaceShip.getPosY() + spaceShip.getHeight() > a.getPosY()) {
				spaceShip.setHealth(spaceShip.getHealth() - 1);
				toRemoveGreenAlienLasers.add(a);
				health.setText("Health: " + spaceShip.getHealth());
				playSoundEffect(2);
			}
		}
		
		lasers.removeAll(toRemoveLasers);
		aliens.removeAll(toRemoveAliens);
		alienLasers.removeAll(toRemoveAlienLasers);
		purpleAliens.removeAll(toRemovePurpleAliens);
		greenAliens.removeAll(toRemoveGreenAliens);
		greenAlienLasers.removeAll(toRemoveGreenAlienLasers);
	}

	@Override
	public void run() {
		int frames = 0;
		long lastFpsTime = System.currentTimeMillis();
		long currentFpsTime = System.currentTimeMillis();
		double timePerFrame = 1000000000/FPS;       // To get 60 frames per second we need a new frame at every 1/60 sec
		long last = System.nanoTime();
		long now = System.nanoTime();
		long lastLaserTime = System.currentTimeMillis();
		long lastAlienTime = 0;
		long gameStartTime = System.currentTimeMillis();
		long lastPurpleAlienTime = System.currentTimeMillis();
		long lastGreenAlienTime = System.currentTimeMillis();
		
		while(true) {
			now = System.nanoTime();
			currentFpsTime = System.currentTimeMillis();
			
			if(spaceShip.getHealth() == 0) {
				
				this.add(gameOver);
				User.updateScore(spaceShip);
				stopMusic();
				playSoundEffect(3);
				
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.exit(0);
			}
			
			
			if(now - last >= timePerFrame) {
				
				init(); // Initialize
				
				update(); // Updates the positions
				
				check();  // Check
				
				repaint();  // Calls paintComponent
				
				
				last = now;
				frames++;
			}
			
			if(currentFpsTime - lastFpsTime >= 1000) {
				lastFpsTime = currentFpsTime;
				fps.setText("FPS: " + frames);
				frames = 0;
			}
			
			if(currentFpsTime - lastLaserTime >= 350){
				canCreateLaser = true;
				lastLaserTime = System.currentTimeMillis();
			}
			
			if(currentFpsTime - lastAlienTime >= 1000){
				canCreateAlien = true;
				lastAlienTime = System.currentTimeMillis();
			}
			
			if(currentFpsTime - lastPurpleAlienTime >= 25000) {
				canCreatePurpleAlien = true;
				lastPurpleAlienTime = System.currentTimeMillis();
			}
			
			if(currentFpsTime - lastGreenAlienTime >= 12000) {
				canCreateGreenAlien = true;
				lastGreenAlienTime = System.currentTimeMillis();
			}
			
			if(currentFpsTime - gameStartTime >= 30000 && level != 3){
				level = 2;
				levelLabel.setText("Level: " + level);
			}
			
			if(currentFpsTime - gameStartTime >= 90000){
				level = 3;
				MAX_ALIEN_SIZE = 4;
				levelLabel.setText("Level: " + level);
			}
			
			
			for(Alien a : aliens) {
				if(currentFpsTime - a.getLastLaserShotTime() > 3000 && a.getPosY() < 600) {
					a.setCanShootLaser(true);
					a.setLastLaserShotTime(currentFpsTime);
				}
			}
			
			for(GreenAlien a : greenAliens) {
				if(currentFpsTime - a.getLastLaserShotTime() > 4000 && a.getPosX() < 700 && a.getPosX() > 100 && a.getPosY() > 300) {
					a.setCanShootLaser(true);
					a.setLastLaserShotTime(currentFpsTime);
				}
			}
			
				
		}
		
	}
	
	public int getPanelSize() {
		return PANEL_SIZE;
	}
	
	public void playMusic(int i) {
		music.getSound(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSoundEffect(int i) {
		sound.getSound(i);
		sound.play();
	}
}


