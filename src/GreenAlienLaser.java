
import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GreenAlienLaser extends Entity{
		
	GreenAlienLaser(GreenAlien alien, int i){
		super(alien.getPosX() + 15,alien.getPosY() + 40, 0, 4, 60, 60);

		if(i == 0) {
			super.setPosX(alien.getPosX());
			super.setPosY(alien.getPosY() + alien.getHeight());
			super.setxSpeed(-2);
		}
		else if(i == 1) {
			super.setPosX(alien.getPosX() + (alien.getWidth()/2));
			super.setPosY(alien.getPosY() + alien.getHeight());
			super.setxSpeed(0);
		}
		else if(i == 2) {
			super.setPosX(alien.getPosX() + alien.getWidth());
			super.setPosY(alien.getPosY() + alien.getHeight());
			super.setxSpeed(2);
		}
		
		
		this.getImage();
	}
	
	public void update() {
		this.posY += this.ySpeed;
		this.posX += this.xSpeed;
	}
	
	
	public void draw(Graphics g) {
		g.drawImage(img, posX, posY, width, height, null, null);
	}
	
	public void getImage() {
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/greenAlienLaser.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

