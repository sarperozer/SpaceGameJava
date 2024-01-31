import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class ScoreBoard extends JPanel{
	
	private JTextArea playerScores;

	ScoreBoard(){
		//Border border = BorderFactory.createLineBorder(Color.green, 2);
		this.setPreferredSize(new Dimension(600,800));
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		JLabel label = new JLabel();
		label.setText("SCOREBOARD");
		label.setForeground(Color.white);
		label.setFont(new Font("MV Boli", Font.PLAIN, 30));
		label.setBounds(200, 0, 200, 50);
		//label.setBorder(border);
		
		playerScores = new JTextArea();
		playerScores.setEditable(false);
		playerScores.setBackground(Color.black);
		playerScores.setBounds(0, 100, 600, 600);
		playerScores.setForeground(Color.white);
		//playerScores.setBorder(border);

		playerScores.setFont(new Font("MV Boli", Font.PLAIN, 25));
		
		this.add(label);
		this.add(playerScores);
	}

	public JTextArea getPlayerScores() {
		return playerScores;
	}

	public void setPlayerScores(JTextArea playerScores) {
		this.playerScores = playerScores;
	}
	
}
