package com.example.demo.components.generator;

import java.lang.invoke.SwitchPoint;
import java.util.Random;

public class NationalityGenerator {

	private static final String nationalities[] = {"american", "czech", "finnish", "russian", "slovak", "swedish", "swiss"};

	private double CANADIAN_PERCENTAGE = .473;
	private double AMERICAN_PERCENTAGE = .257;
	private double SWEDISH_PERCENTAGE = .096;
	private double RUSSIAN_PERCENTAGE = .058;
	private double CZECH_PERCENTAGE = .043;
	private double FINNISH_PERCENTAGE = .043;
	private double SWISS_PERCENTAGE = .010;
	private double SLOVAKIAN_PERCENTAGE = .010;

	private Random rand;

	public NationalityGenerator(){
		rand = new Random();
	}
	public String getNext(){

		double roll = rand.nextDouble();
		double total = CANADIAN_PERCENTAGE;

		if(roll < total)
			return "canadian";

		total += AMERICAN_PERCENTAGE;
		if(roll < total)
			return "american";

		total += SWEDISH_PERCENTAGE;
		if(roll < total)
			return "swedish";

		total += RUSSIAN_PERCENTAGE;
		if(roll < total)
			return "russian";

		total += CZECH_PERCENTAGE;
		if(roll < total)
			return "czech";

		total += FINNISH_PERCENTAGE;
		if(roll<total)
			return "finnish";

		total += SWISS_PERCENTAGE;
		if(roll < total)
			return "swiss";

		total += SLOVAKIAN_PERCENTAGE;
		if(roll<total)
			return "slovak";

		return "canadian";
	}
}
