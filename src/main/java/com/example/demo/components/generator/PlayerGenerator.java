package com.example.demo.components.generator;

import com.example.demo.model.Player;
import com.example.demo.service.GeneratorService;

import java.util.Random;

public class PlayerGenerator {

	GeneratorService gs;

	public PlayerGenerator(){

	}
	public void setGeneratorService(GeneratorService gs){
		this.gs = gs;
	}
	public Player getNext(){
		Player player = new Player();
		Random rand = new Random();

		NameGenerator nameGen = new NameGenerator();
		TeamGenerator teamGen = new TeamGenerator();
		PositionGenerator positionGen = new PositionGenerator();
		SkillGenerator skillGen = new SkillGenerator();
		NationalityGenerator nationalityGenerator = new NationalityGenerator();

		/*
			To do: make it so team gen distributes league talent so it fills positional needs
		 */
		int teamID = teamGen.getNext();
		String ethnicity = nationalityGenerator.getNext();

		String searchEthnicity = ethnicity;
		if(ethnicity == "canadian"){
			searchEthnicity = "american";
		}

		String firstName = nameGen.getNextFirstName(gs.findFirstNameByEthnicty(searchEthnicity));
		String lastName = nameGen.getNextLastName(gs.findLastNameByEthnicty(searchEthnicity));

		String position = positionGen.getNext();

		int offSkills = skillGen.getNextOffensiveSkills(position);
		int defSkills = skillGen.getNextDefensiveSkills(position);
		int goalieSkills = skillGen.getNextGoalieSkills(position);


		player.setName(firstName + " " + lastName);
		player.setTeamID(teamID);
		player.setOffskills(offSkills);
		player.setDefskills(defSkills);
		player.setPosition(positionGen.getNext());

		return player;
	}
}
