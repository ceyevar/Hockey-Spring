package com.example.demo.model;



import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

class CompositeKey implements Serializable {
	private int teamid;
	private int year;
}

@Entity
@IdClass(CompositeKey.class)
public class Record{

	@Id
	private int teamid;

	@Id
	private int year;

	private int wins;
	private int losses;
	private int otl;
	private int row;
	private int GF;
	private int GA;
	private BigDecimal save_percentage;
	private BigDecimal shooting_percentage;
}
