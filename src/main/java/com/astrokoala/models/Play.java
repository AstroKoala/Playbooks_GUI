package com.astrokoala.models;

import com.astrokoala.enums.PlayTypes;

public class Play {
	
	private String name;
	private String abbreviation;
	private PlayTypes type;
	private String formation;
	
	public Play(String name, String abbreviation, PlayTypes type, String formation) {
		this.name = name;
		this.abbreviation = abbreviation;
		this.type = type; 
		this.formation = formation;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public PlayTypes getType() {
		return type;
	}

	public void setType(PlayTypes type) {
		this.type = type;
	}

	public String getFormation() {
		return formation;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}
	
}
