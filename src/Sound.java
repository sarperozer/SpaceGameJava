import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	Clip clip;
	URL[] soundURL = new URL[10];
	
	public Sound() {
		soundURL[0] = getClass().getResource("/Game Music_lower.wav");
		soundURL[1] = getClass().getResource("/Hit Sound Effect_lower.wav");
		soundURL[2] = getClass().getResource("/Damage_lower.wav");
		soundURL[3] = getClass().getResource("/Game Over Sound_lower.wav");
	}
	
	public void getSound(int i) {
		try {
			AudioInputStream is = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(is);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
}
