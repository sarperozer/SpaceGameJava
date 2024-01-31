import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AlienLaser extends Entity{
		
	AlienLaser(Alien alien){
		super(alien.getPosX() + 15,alien.getPosY() + 40, 0, 8, 60, 60);

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
			img = ImageIO.read(getClass().getResourceAsStream("/AlienLaser.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
