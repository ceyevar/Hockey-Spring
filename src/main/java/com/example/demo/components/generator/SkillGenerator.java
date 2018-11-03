package com.example.demo.components.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SkillGenerator {

	//Random r = new Random();
	//double mySample = r.nextGaussian()*desiredStandardDeviation+desiredMean;

	private enum Weight{VERYHIGH, HIGH, MEDIUM, LOW};

	private static final int VERY_HIGH_WEIGHT = 30;
	private static final int HIGH_WEIGHT = 15;
	private static final int MEDIUM_WEIGHT = 10;
	private static final int LOW_WEIGHT = 5;

	private static final int STANDARD_DEVIATION = 25;
	private static final int MEAN = 50;

	public SkillGenerator(){

	}

	/**
	 * Gets the corresponding mean adjustment
	 *
	 * @param weight
	 * @param isPostiveAdjustment whether expected weight is positive
	 * @return int value of mean adjustment
	 */
	private int getWeightAdjustment(Weight weight, boolean isPostiveAdjustment){
		int intAdjVal = 0;
		switch(weight){
			case VERYHIGH:
				intAdjVal = VERY_HIGH_WEIGHT;
				break;
			case HIGH:
				intAdjVal = HIGH_WEIGHT;
				break;
			case MEDIUM:
				intAdjVal = MEDIUM_WEIGHT;
				break;
			case LOW:
				intAdjVal = LOW_WEIGHT;
				break;
		}

		if(isPostiveAdjustment)
			return intAdjVal;
		else
			return (-1) * intAdjVal;
	}
	/**
	 *	Gets the next gaussian value for a skill from the base distribution
	 *
	 * @param meanAdjustment
	 * @return randomized skill value
	 */
	public int getNext(int meanAdjustment){
		Random rand = new Random();

		int myRoll = (int)(rand.nextGaussian()*STANDARD_DEVIATION+(MEAN + meanAdjustment));

		if(myRoll > 99)
			myRoll = 99;
		if(myRoll < 1)
			myRoll = 1;
		return myRoll;
	}
	public int getNextOffensiveSkills(String position){
		int offSkillsPosAdj = 0;
		switch(position){
			case "LW":
			case "RW":
			case "C":
				offSkillsPosAdj = getWeightAdjustment(Weight.MEDIUM, true);
				break;
			case "LD":
			case "RD":
				offSkillsPosAdj = getWeightAdjustment(Weight.MEDIUM, false);
			case "G":
				offSkillsPosAdj = getWeightAdjustment(Weight.VERYHIGH, false);
		}
		return getNext(offSkillsPosAdj);
	}
	public int getNextDefensiveSkills(String position){
		int defSkillsPosAdj = 0;
		switch(position){
			case "LW":
			case "RW":
			case "C":
				defSkillsPosAdj = getWeightAdjustment(Weight.MEDIUM, false);
				break;
			case "LD":
			case "RD":
				defSkillsPosAdj = getWeightAdjustment(Weight.MEDIUM, true);
			case "G":
				defSkillsPosAdj = getWeightAdjustment(Weight.VERYHIGH, false);
		}
		return getNext(defSkillsPosAdj);
	}
	public int getNextGoalieSkills(String position){
		int goalieSkillsPosAdj = 0;
		switch(position){
			case "LW":
			case "RW":
			case "C":
			case "LD":
			case "RD":
				goalieSkillsPosAdj = getWeightAdjustment(Weight.VERYHIGH, false);
				break;
		}
		return getNext(goalieSkillsPosAdj);

	}
}
