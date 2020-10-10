package com.astrokoala.playbook;

import java.util.HashMap;
import java.util.Map;

import com.astrokoala.models.Playbook;
import com.sun.javafx.application.HostServicesDelegate;

import javafx.application.HostServices;

public class State {
	
	private static State instance;
  private static String info = "Initial info class";
  private static String userName;
  private static HostServices hostService;
//  private Playbook currentPlaybook;
  private static Map<String, Playbook> playbooks = new HashMap<>();
  
  
  private State() { }
  
  public static State getInstance() {
      if(instance == null) {
      	instance = new State();
      }
      return instance;
  }
  
  public static void setInfo(String str) {
  	info = str;
  }
  
  public static String getInfo() {
  	return info;
  }
  
  public static void setPlaybooks() {
  	
  }
  
  public static Map<String,Playbook> getPlaybooks() {
  	return playbooks;
  }
  
  public static void setUserName(String u) {
  	userName = u;
  }
  
  public static String getUserName() {
  	return userName;
  }
  
  
  public static void addPlaybook(Playbook pb) {
  	getPlaybooks().put(pb.getName(), pb);
  }

	public static void setPlaybooks(Map<String, Playbook> playbooks) {
		State.playbooks = playbooks;
	}
	
	protected static void setHostServices(HostServices hostServices) {
		hostService = hostServices;
	}
	
	public static HostServices getHostServices() {
		return hostService;
	}
  
  // getters and setters
}

