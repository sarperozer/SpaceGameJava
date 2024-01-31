import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GreenAlien extends Entity{
	
	private GamePanel gamePanel;
	private int health = 8;
	private int dir = 1;
	private long lastLaserShotTime = 0;
	private boolean canShootLaser = false;
	
	public GreenAlien(int posX, int posY, int xSpeed, int ySpeed, int width, int height, GamePanel gamePanel) {
		super(posX, posY, xSpeed, ySpeed, width, height);
		this.getImage();
		this.gamePanel = gamePanel;
	}


	public void update() {
		if(this.posX < 0)
			dir = dir * -1;
		this.posY += this.ySpeed;
		this.posX += this.xSpeed * dir;
	}
	
	
	public void draw(Graphics g) {
		g.drawImage(img, posX, posY, width, height, null, null);
	}
	
	public void getImage() {
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/greenAlien.png"));
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
