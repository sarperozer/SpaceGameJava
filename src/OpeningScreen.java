import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class OpeningScreen extends JPanel{
	 OpeningScreen(){
		 this.setPreferredSize(new Dimension(900, 900));
		 this.setBackground(Color.BLACK);
		 this.setLayout(new GridBagLayout());
		 JLabel label = new JLabel();
		 label.setText("Login to Start!");
		 label.setForeground(Color.white);
		 label.setFont(new Font("MV Boli", Font.PLAIN, 20));
		 this.add(label);
	 }
}
