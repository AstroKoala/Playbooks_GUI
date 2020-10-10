package com.astrokoala.scenes.home;

import java.net.URL;
import java.util.ResourceBundle;

import com.astrokoala.enums.Pages;
import com.astrokoala.playbook.App;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class HomeController implements Initializable {
	
	@FXML private javafx.scene.control.Button closeBtn;
	@FXML private javafx.scene.control.Button showModal;
	@FXML private javafx.scene.control.Label playbooks;
	
	@FXML
	private void openPlayEntry() {
		App.getPrimaryStage().setScene(App.getPages().get(Pages.PLAY_ENTRY.getName()));
	}
	
	@FXML
	private void exit() {
		//show exit dialog
		System.exit(0);
	}

	private void watchForPlays() {
//		Runnable helloRunnable = () -> {
//			if (State.getPlaybooks().size() != 1 && State.getPlaybooks().size() >= -1) {
//				System.out.println("There are " + State.getPlaybooks().size() + " playbooks");
//			}
//      if (State.getPlaybooks().size() == 1) {
//      	System.out.println("There are " + State.getPlaybooks().size() + " playbooks");
//      	Platform.runLater(() -> playbooks.setText("fnaofnaonfao"));
//      }
//    };
//  	ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//  	executor.scheduleAtFixedRate(helloRunnable, 0, 10, TimeUnit.MILLISECONDS);
  }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//new Thread(this::watchForPlays).start(); 
	}

	@FXML
	private void updatePlaybooksText() {
		playbooks.setText("Hello");
	}
	
}
