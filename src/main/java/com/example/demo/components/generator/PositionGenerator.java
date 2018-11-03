package com.example.demo.components.generator;

import java.util.concurrent.ThreadLocalRandom;

public class PositionGenerator {

	private static final String position[] = {"LW" , "RW", "C", "LD", "RD", "G"};

	public PositionGenerator(){

	}
	public String getNext(){
		// LW, RW, C, LD, RD, G = 6 total positions
		int roll = ThreadLocalRandom.current().nextInt(0, 6 );

		return position[roll];
	}
}
