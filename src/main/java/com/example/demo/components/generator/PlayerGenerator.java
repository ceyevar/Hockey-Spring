package com.example.demo.components.generator;

import com.example.demo.model.Player;

public class PlayerGenerator {


	public PlayerGenerator(){

	}

	public Player getNext(){
		Player player = null;

		NameGenerator nameGen = new NameGenerator();
		TeamGenerator teamGen = new TeamGenerator();
		PositionGenerator positionGen = new PositionGenerator();
		SkillGenerator skillGen = new SkillGenerator();

		/*
			To do: make it so team gen distributes league talent so it fills positional needs
		 */
		int teamID = teamGen.getNext();

		String name = nameGen.getNext();
		String position = positionGen.getNext();

		int offSkills = skillGen.getNextOffensiveSkills(position);
		int defSkills = skillGen.getNextDefensiveSkills(position);
		int goalieSkills = skillGen.getNextGoalieSkills(position);

		player.setName(name);
		player.setTeamID(teamID);
		player.setOffskills(offSkills);
		player.setDefskills(defSkills);
		player.setPosition(positionGen.getNext());

		return player;
	}
}
