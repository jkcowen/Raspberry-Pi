package FileUploadTCP;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Playmusic implements Runnable {
    // public static void main(String[] args){
        // Thread t = new Thread(new Playmusic());
        // t.start();
    // }   
	private String filename;
	
	public Playmusic(String filename){
		this.filename = filename;
	}
	
    @Override
    public void run() {
        AudioInputStream audioIn;
        try {
        	audioIn = AudioSystem.getAudioInputStream(new File("C:/users/jkc11/onedrive/documents/cpre186/server/"+filename));	
            Clip clip;
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
           // clip.stop();
            Thread.sleep(clip.getMicrosecondLength()/1000);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException  e1) {
            System.out.print("Could not find file // File was moved\n");
            e1.printStackTrace();
        }
    }
}