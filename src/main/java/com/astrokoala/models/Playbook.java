package com.astrokoala.models;

import java.util.Date;
import java.util.List;

public class Playbook {
	private int id;
	private String name;
	private List<Play> plays;
	private Date dateCreated;
	
	public Playbook(int id, String name, List<Play> plays, Date dateCreated) {
		this.id = id; 
		this.name = name;
		this.plays = plays;
		this.dateCreated = dateCreated;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Play> getPlays() {
		return plays;
	}

	public void setPlays(List<Play> plays) {
		this.plays = plays;
	}
	
	public void addPlay(Play play) {
		this.plays.add(play);
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
}
