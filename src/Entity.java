import java.awt.image.BufferedImage;

public class Entity {
	protected int posX, posY;
	protected int xSpeed, ySpeed;
	protected int width, height;
	
	protected BufferedImage img;
	
	public Entity(int posX, int posY, int xSpeed, int ySpeed, int width, int height) {
		this.posX = posX;
		this.posY = posY;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.width = width;
		this.height = height;
	}
	
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public int getxSpeed() {
		return xSpeed;
	}
	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}
	public int getySpeed() {
		return ySpeed;
	}
	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
	
	
}
