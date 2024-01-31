import java.awt.Dimension;

public class Main{
	public static void main(String[] args) {
		GameWindow openingWindow = new GameWindow();
		openingWindow.add(new OpeningScreen());
		openingWindow.setSize(new Dimension(900,900));
		openingWindow.setResizable(false);
		openingWindow.setLocationRelativeTo(null);
		
		while(true) {
			if(openingWindow.getCanPlay() == true) {
				break;
			}
			else {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		openingWindow.setVisible(false);
		
		GamePanel gamePanel = new GamePanel();
		GameWindow gameWindow = new GameWindow();
		gameWindow.add(gamePanel);
		//OpeningScreen openingScreen = new OpeningScreen();
		//gameWindow.add(openingScreen);
		gameWindow.pack();
		gameWindow.setResizable(false);
		gameWindow.setLocationRelativeTo(null);
		gamePanel.startGame();
		
	}
}
