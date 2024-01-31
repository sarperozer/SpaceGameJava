import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Laser extends Entity{
	
	Laser(SpaceShip spaceShip){
		super(spaceShip.getPosX() + 30,spaceShip.getPosY() - 40, 0, 8, 30, 60);

		this.getImage();
	}
	
	
	public void update() {
		this.posY -= this.ySpeed;
	}
	
	
	public void draw(Graphics g) {
		g.drawImage(img, posX, posY, width, height, null, null);
	}
	
	public void getImage() {
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/Laser.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
