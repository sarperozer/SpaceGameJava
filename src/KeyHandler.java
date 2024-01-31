import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	private boolean UP, DOWN, RIGHT, LEFT, LASER;
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_W) {
			UP = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_S) {
			DOWN = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			RIGHT = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_A) {
			LEFT = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			LASER = true;
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_W) {
			UP = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_S) {
			DOWN = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			RIGHT = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_A) {
			LEFT = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			LASER = false;
		}
	}
	

	public boolean isUP() {
		return UP;
	}

	public boolean isDOWN() {
		return DOWN;
	}


	public boolean isRIGHT() {
		return RIGHT;
	}


	public boolean isLEFT() {
		return LEFT;
	}
	
	public boolean isLASER() {
		return LASER;
	}

}
