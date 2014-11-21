package Audio;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class AudioEngine {
	private File file;

	private Media media;
	private MediaPlayer mediaPlayer;

	public AudioEngine() {
	}

	public void setFile(File file) {
		this.file = file;
	}

	public File getFile() {
		return this.file;
	}
	
	public void playFile(File file) {
		this.file = file;
		playSound();
	}
	
	public void pause(){
		try {
			mediaPlayer.pause();
		} catch (Exception e) {
			System.out.print(e);
		}
	}
	
	public void resume(){
		try {
			mediaPlayer.play();
		} catch (Exception e) {
			System.out.print(e);
		}
	}
	
	public Status getStatus(){
		return mediaPlayer.getStatus();
	}

	public void playSound() {
		try {
			
			if (mediaPlayer != null){
				if (getStatus().equals(MediaPlayer.Status.PLAYING))
					mediaPlayer.pause();
			}
			
			media = new Media(file.toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			
			mediaPlayer.play();
		} catch (Exception e) {
			System.out.print(e);
		}
	}
}