package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Player {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private int offskills;
	private int defskills;

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

	public int getOffskills() {
		return offskills;
	}

	public void setOffskills(int offskills) {
		this.offskills = offskills;
	}

	public int getDefskills() {
		return defskills;
	}

	public void setDefskills(int defskills) {
		this.defskills = defskills;
	}
}
