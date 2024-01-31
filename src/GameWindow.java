import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameWindow extends JFrame implements ActionListener {
	
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu helpMenu;
	private JMenuItem registerItem;
	private JMenuItem playGameItem;
	private JMenuItem highScoreItem;
	private JMenuItem quitItem;
	private JMenuItem aboutItem;
    private	static boolean canPlay = false;
	

	public GameWindow(){
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		helpMenu = new JMenu("Help");
		registerItem = new JMenuItem("Register");
		playGameItem = new JMenuItem("Play Game");
		highScoreItem = new JMenuItem("High Score");
		quitItem = new JMenuItem("Quit");
		aboutItem = new JMenuItem("About");
		
		registerItem.addActionListener(this);
		playGameItem.addActionListener(this);
		highScoreItem.addActionListener(this);
		quitItem.addActionListener(this);
		aboutItem.addActionListener(this);
		
		fileMenu.add(registerItem);
		fileMenu.add(playGameItem);
		fileMenu.add(highScoreItem);
		fileMenu.add(quitItem);
		
		helpMenu.add(aboutItem);
		
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		
		this.setJMenuBar(menuBar);
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == aboutItem) {
			JOptionPane.showMessageDialog(null, "Name: Sarper\nSurname: Özer\nSchool Number: 20220702142\nEmail: sarper.ozer@std.yeditepe.edu.tr");
		}
		if(e.getSource() == quitItem) System.exit(0);
		if(e.getSource() == registerItem) {
			String userName = JOptionPane.showInputDialog("Enter a username: ");
			String password = JOptionPane.showInputDialog("Enter a password: ");
			
			User.register(userName, password);
			
			JOptionPane.showMessageDialog(null, "Succesfully Registered!");
		}
		if(e.getSource() == playGameItem) {
			String userName = JOptionPane.showInputDialog("Enter your username: ");
			String password = JOptionPane.showInputDialog("Enter your password: ");
			
			if(User.login(userName, password)) {
				setCanPlay(true);
				User.setUserName(userName);
				User.setPassword(password);
			}
		}
		if(e.getSource() == highScoreItem) {
			JFrame frame = new JFrame();
			frame.setLocationRelativeTo(null);
			frame.setLocation(600, 100);
			frame.setResizable(false);
			
			ScoreBoard scoreBoard = new ScoreBoard();
			scoreBoard.getPlayerScores().setText("");
			
			try {
				BufferedReader file = new BufferedReader(new FileReader(User.getUserdata()));
				String line;
				ArrayList<PlayerForSort> scoreRecords = new ArrayList<>();
				
				String currentLine = file.readLine();

				while (currentLine != null)
				{
				       String[] p = currentLine.split(" ");
				       String userName = p[0];
				       String password = p[1];
				       int score = Integer.parseInt(p[2]);
				       scoreRecords.add(new PlayerForSort(userName, password, score));
				       currentLine = file.readLine();
				}
				if(scoreRecords.size() != 0) {
					Collections.sort(scoreRecords, new ScoreComparator());
					
					for(int i = 0; i < scoreRecords.size(); i++) {
						scoreBoard.getPlayerScores().append(String.format("\t%s\t%s\n", scoreRecords.get(i).userName, scoreRecords.get(i).highScore));
					}
				}
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			frame.add(scoreBoard);
			frame.pack();
			frame.setVisible(true);
			
		}
	}
	

	public static boolean getCanPlay() {
		return canPlay;
	}


	public static void setCanPlay(boolean c) {
		canPlay = c;
	}

}
