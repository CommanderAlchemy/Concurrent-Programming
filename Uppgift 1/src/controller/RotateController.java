package controller;

import interfaces.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class RotateController implements Initializable, controller{

	@FXML private Rectangle rect;
	@FXML private Button 	btn_Rotate_Play;
	@FXML private Button 	btn_Rotate_Stop;

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

		if (event.getSource().equals(btn_Rotate_Play)) {
			CreateTask();
			new Thread(task).start();
		}

		if (event.getSource().equals(btn_Rotate_Stop)) {
			task.cancel();
		}
	}

	private void Rotate() {
		System.out.println("Rotate");
		rect.getTransforms().add(new Rotate(10,0,0));
	}

	private void CreateTask() {
		task = new Task<Void>() {
			@Override protected Void call() throws Exception {
				for (int i=0; i<100; i++) {
					if (isCancelled()) 
						break;
					
					Platform.runLater(new Runnable() {
						@Override public void run() {
							Rotate();
						}
					});
					Thread.sleep(100);
				}
				return null;
			}
		};
	}
}