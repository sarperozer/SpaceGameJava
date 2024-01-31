import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Alien extends Entity{
	
	private GamePanel gamePanel;
	private int dir = 1;
	private int health = 5;
	private long lastLaserShotTime = 0;
	private boolean canShootLaser = false;
	
	public Alien(int posX, int posY, int xSpeed, int ySpeed, int width, int height, GamePanel gamePanel) {
		super(posX, posY, xSpeed, ySpeed, width, height);
		this.gamePanel = gamePanel;
		this.getImage();
	}


	public void update() {
		if(this.posX + this.width == gamePanel.getPanelSize() || this.posX < 0)
			dir = dir * -1;
		this.posY += this.ySpeed;
		this.posX += this.xSpeed * dir;
	}
	
	
	public void draw(Graphics g) {
		g.drawImage(img, posX, posY, width, height, null, null);
	}
	
	public void getImage() {
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/Alien1.png"));
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

	public long getLastLaserShotTime() {
		return lastLaserShotTime;
	}

	public void setLastLaserShotTime(long lastLaserShotTime) {
		this.lastLaserShotTime = lastLaserShotTime;
	}

	public boolean getCanShootLaser() {
		return canShootLaser;
	}

	public void setCanShootLaser(boolean canShootLaser) {
		this.canShootLaser = canShootLaser;
	}
}
