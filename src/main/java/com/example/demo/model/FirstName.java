package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@IdClass(FirstName.class)
@Entity
public class FirstName implements Serializable{

	@Id
	private String name;

	@Id
	private String ethnicity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}
}
