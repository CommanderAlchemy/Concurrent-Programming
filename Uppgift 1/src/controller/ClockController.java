package controller;

import interfaces.controller;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class ClockController implements Initializable, controller {

	@FXML private Text 	 text;
	@FXML private Button btn_Clock_Play;
	@FXML private Button btn_Clock_Stop;

	private Random rand = new Random();
	private Task task;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	/*
	 * Button Events.
	 */
	
	@Override
	public void event(ActionEvent event) {
		System.out.println("Click");

		if (event.getSource().equals(btn_Clock_Play)) {
			CreateTask();
			new Thread(task).start();
		}

		if (event.getSource().equals(btn_Clock_Stop)) {
			task.cancel();
		}
	}

	private void WriteText() {
		System.out.println("Writetext");
		text.relocate(rand.nextInt(100), rand.nextInt(20));
	}

	private void CreateTask() {
		task = new Task<Void>() {
			@Override protected Void call() throws Exception {
				for (int i=0; i<100; i++) {
					if (isCancelled()) 
						break;
					
					Platform.runLater(new Runnable() {
						@Override public void run() {
							WriteText();
						}
					});
					Thread.sleep(100);
				}
				return null;
			}
		};
	}
}