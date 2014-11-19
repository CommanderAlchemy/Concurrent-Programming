package controller;

import interfaces.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import Audio.AudioEngine;
import application.MediaMain;

public class MediaController implements Initializable, controller{
	
	/*
	 * Class variables
	 */
	@FXML private Button	btn_Player_Open;
	@FXML private Button 	btn_Player_Play;
	@FXML private Button	btn_Player_Stop;
	@FXML private Label 	lbl_NowPlaying;
	
	private AudioEngine audioEngine = new AudioEngine();
	private File file;
	
	/*
	 * (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	/*
	 * Button Events.
	 */
	
	@Override 
	public void event(ActionEvent event){
		System.out.println("Click");

		if (event.getSource().equals(btn_Player_Open)){
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open .mp3 file");
			
			// TODO FIX primarystage!
			file = fileChooser.showOpenDialog(MediaMain.primaryStage);
			if (file != null) {
				System.out.println(file.toString());
				lbl_NowPlaying.setText("Now Playing: " + file.getName().toString());
				audioEngine.playFile(file);
			}
		}
		if (event.getSource().equals(btn_Player_Play)){
			audioEngine.resume();
		}
		if (event.getSource().equals(btn_Player_Stop)){
			audioEngine.pause();
		}
	}
}