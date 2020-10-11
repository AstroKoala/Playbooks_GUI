package com.astrokoala.enums;

import java.io.IOException;

import com.astrokoala.playbook.App;
import com.astrokoala.scenes.play_entry.PlayEntryController;
import com.astrokoala.scenes.landing.LandingController;
import com.astrokoala.scenes.login.LoginController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public enum Pages {
	LOGIN("Login", new LoginController()), 
	PLAY_ENTRY("PlayEntry", new PlayEntryController()),
	LANDING("Landing", new LandingController());
	
	private final String name;
	private final Object type;

  private Pages(String name, Object type) {
      this.name = name;
      this.type = type;
      App.getPages().put(this.name, this.getScene());
  }
  
  public String getName() {
  	return this.name;
  }
  public Class<?> getType() {
  	return this.type.getClass();
  }
	
	public Scene getScene() {
		Scene scene =  null;
		try {
			scene = new Scene(new FXMLLoader(this.type.getClass().getResource(this.name + ".fxml")).load());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return scene;
	}
}


