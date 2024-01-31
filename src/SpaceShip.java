import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.imageio.ImageIO;

public class SpaceShip extends Entity {
	
	private GamePanel gamePanel;
	private KeyHandler keyHandler;
	private int health = 3;
	private int Score = 0;
	
	SpaceShip(GamePanel gamePanel, KeyHandler keyHandler){
		super(450,800,6,6,90,90);
		this.gamePanel = gamePanel;
		this.keyHandler = keyHandler;
		
		this.getImage();
	}
	
	public void update() {
		
		if(keyHandler.isUP() && posY > 0) {
			posY -= ySpeed;
		}
		else if(keyHandler.isDOWN() && posY + height < gamePanel.getPanelSize()) {
			posY += ySpeed;
		}
		else if(keyHandler.isLEFT() && posX > 0) {
			posX -= xSpeed;
		}
		else if(keyHandler.isRIGHT() && posX + width < gamePanel.getPanelSize()) {
			posX += xSpeed;
		}
		
	}
	
	public void draw(Graphics g) {
		
		g.drawImage(img, posX, posY, width, height, null, null);
		
	}
	
	public void getImage() {
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/Game1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public void setHealth(int h) {
		this.health = h;
	}
	
	public int getScore() {
		return Score;
	}
	public void setScore(int s) {
		this.Score = s;
	}
}
