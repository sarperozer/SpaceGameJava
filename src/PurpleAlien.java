import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PurpleAlien extends Entity {

	private int health = 5;
	
	public PurpleAlien(int posX, int posY, int ySpeed, int width, int height) {
		super(posX, posY, 0, ySpeed, width, height);
		this.getImage();
	}


	public void update() {
		this.posY += this.ySpeed;
	}
	
	
	public void draw(Graphics g) {
		g.drawImage(img, posX, posY, width, height, null, null);
	}
	
	public void getImage() {
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/purpleAlien.png"));
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
}
