import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class User {
	private final static File userData = new File("USER_DATA.txt");
	private static Scanner reader;
	private static FileWriter writer;
	private static String userName;
	private static String password;
	

	public static void register(String userName, String password) {
		try {
			writer = new FileWriter(userData, true);
			writer.write(userName + " " + password + " 0\n");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static boolean login(String userName, String password) {
		try {
			reader = new Scanner(userData);
			
			while(reader.hasNextLine()) {
				String data = reader.nextLine();
				String[] p = data.split(" ");
				if(p[0].equals(userName) && p[1].equals(password)) {
					JOptionPane.showMessageDialog(null, "Login Successful");
					return true;
				}
			}
			reader.close();
			JOptionPane.showMessageDialog(null, "Error!\nIncorrect Username or Password!");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false; 
	}
	

	public static void updateScore(SpaceShip spaceShip) {
		 try {
		        BufferedReader file = new BufferedReader(new FileReader(userData));
		        StringBuffer inputBuffer = new StringBuffer();
		        String line;

		        while ((line = file.readLine()) != null) {
		        	String[] p = line.split(" ");
		        	if(p[0].equals(userName) && p[1].equals(password) && spaceShip.getScore() > Integer.parseInt(p[2])) {
		        		p[2] = Integer.toString(spaceShip.getScore());
		        	}
	        		line = String.format("%s %s %s", p[0], p[1], p[2]);
		            inputBuffer.append(line);
		            inputBuffer.append('\n');
		        }
		        file.close();

		        // write the new string with the replaced line OVER the same file
		        FileOutputStream fileOut = new FileOutputStream(userData);
		        fileOut.write(inputBuffer.toString().getBytes());
		        fileOut.close();

		    } catch (Exception e) {
		        System.out.println("Problem reading file.");
		    }
	}
	

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		User.userName = userName;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		User.password = password;
	}

	public static File getUserdata() {
		return userData;
	}
}
