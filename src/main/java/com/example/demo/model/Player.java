package com.example.demo.model;

import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	private String name;

	private int offskills;

	private int defskills;

	private int goalieskills;

	@Formula(value = "OFFSKILLS + DEFSKILLS")
	private int ovlrating;

	private int teamID;
	private String position;



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

	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getGoalieskills() {
		return goalieskills;
	}

	public void setGoalieskills(int goalieskills) {
		this.goalieskills = goalieskills;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOvlrating() {
		return ovlrating;
	}

	public void setOvlrating(int ovlrating) {
		this.ovlrating = ovlrating;
	}
}
