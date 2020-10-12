package com.astrokoala.playbook;

import java.util.HashMap;
import java.util.Map;

import com.astrokoala.enums.Pages;
import com.pepperonas.fxiconics.FxIconicsLabel;
import com.pepperonas.fxiconics.MaterialColor;
import com.pepperonas.fxiconics.gmd.FxFontGoogleMaterial;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application { 
	
	private static Stage primaryStage;
	private static Map<String, Scene> pages = new HashMap<>();
	private static State state = State.getInstance();
	//private  HostServicesDelegate hostServices = HostServicesFactory.getInstance(this);
	private HostServices hostServices = this.getHostServices();
	private static Pane activePane = null;
	
  
	public static void main(String[] args) {
    Application.launch(args);
  } 
	
  @Override
  public void start(Stage stage) throws Exception {
  	setPrimaryStage(stage);
  	State.setHostServices(hostServices);
		stage.show();
  }

	/** Returns a Map of the scenes by {@link SceneName} */
	public static Map<String, Scene> getPages() {
		return pages;
	}
  
  public static State getInstance() {
  	return state;
  }
  
  public static Stage getPrimaryStage() {
    return primaryStage;
  }

	private static void setPrimaryStage(Stage stage) {
		primaryStage = stage;
		primaryStage.setScene(pages.get(Pages.LOGIN.getName()));
		primaryStage.setTitle("Fancy Title");
		primaryStage.setWidth(780);
		primaryStage.setHeight(510);
		primaryStage.setResizable(false);
	}
	
	public static void setActivePane(Pane pane) {
		activePane = pane;
	}
	
	public static void showNotice(String str) {
	}
} 
