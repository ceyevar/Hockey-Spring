package com.example.demo.components.generator;

import com.example.demo.model.FirstName;
import com.example.demo.model.LastName;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NameGenerator {

	public NameGenerator(){

	}
	public String getNext(){
		String name = "";

		return name;
	}
	public String getNextFirstName(List<FirstName> names){
		Random rand = new Random();
		String name = names.get(rand.nextInt(names.size())).getName();
		return name;
	}
	public String getNextLastName(List<LastName> names){
		Random rand = new Random();
		String name = names.get(rand.nextInt(names.size())).getName();
		return name;
	}
}
