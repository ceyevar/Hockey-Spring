package com.example.demo.model;

import com.example.demo.service.PlayerService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Team {
	@Id
	@GeneratedValue
	private int id;
	private String teamName;
	private HashMap<String, Boolean> assignedPositions = new HashMap<String, Boolean>();

	//this is the gametime lineup
	private int C1,LW1,RW1,C2,LW2,RW2, C3, LW3, RW3, C4, LW4, RW4,
			LD1, RD1,LD2, RD2, LD3, RD3, G1, G2;

	public void setEmptyAssignments(){
		String[] positions = {"C1", "LW1", "LW1",
									 "C2", "LW2", "RW2",
									 "C3", "LW3", "RW3",
									 "C4", "LW4", "RW4",
									 "LD1", "RD1", "LD2",
									 "RD2", "LD3", "RD3", "G1", "G2"};

		for(String s: positions){
			assignedPositions.put(s, false);
		}
	}

	public void setLineup(){
		PlayerService sp = new PlayerService();
		for(Map.Entry<String, Boolean> entry : assignedPositions.entrySet()) {
			String key = entry.getKey();
			Boolean isAssigned = entry.getValue();

			if(!isAssigned){
				int id = sp.findPlayerPositionRankNoTeam(key.replaceAll("[0-9]", ""));
				assignedPositions.put(key, true);
				setPosition(key, id);
			}
		}
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getC1() {
		return C1;
	}

	public void setC1(int c1) {
		assignedPositions.put("C1", true);
		C1 = c1;
	}

	public int getLW1() {
		return LW1;
	}

	public void setLW1(int LW1) {
		assignedPositions.put("LW1", true);
		this.LW1 = LW1;
	}

	public int getRW1() {
		return RW1;
	}

	public void setRW1(int RW1) {
		assignedPositions.put("RW1", true);
		this.RW1 = RW1;
	}

	public int getC2() {
		return C2;
	}

	public void setC2(int c2) {
		assignedPositions.put("C2", true);
		C2 = c2;
	}

	public int getLW2() {
		return LW2;
	}

	public void setLW2(int LW2) {
		assignedPositions.put("LW2", true);
		this.LW2 = LW2;
	}

	public int getRW2() {
		return RW2;
	}

	public void setRW2(int RW2) {
		assignedPositions.put("RW2", true);
		this.RW2 = RW2;
	}

	public int getC3() {
		return C3;
	}

	public void setC3(int c3) {
		assignedPositions.put("C3", true);
		C3 = c3;
	}

	public int getLW3() {
		return LW3;
	}

	public void setLW3(int LW3) {
		assignedPositions.put("LW3", true);
		this.LW3 = LW3;
	}

	public int getRW3() {
		return RW3;
	}

	public void setRW3(int RW3) {
		assignedPositions.put("RW3", true);
		this.RW3 = RW3;
	}

	public int getC4() {
		return C4;
	}

	public void setC4(int c4) {
		assignedPositions.put("C4", true);
		C4 = c4;
	}

	public int getLW4() {
		return LW4;
	}

	public void setLW4(int LW4) {
		assignedPositions.put("LW4", true);
		this.LW4 = LW4;
	}

	public int getRW4() {
		return RW4;
	}

	public void setRW4(int RW4) {
		assignedPositions.put("RW4", true);
		this.RW4 = RW4;
	}

	public int getLD1() {
		return LD1;
	}

	public void setLD1(int LD1) {
		assignedPositions.put("LD1", true);
		this.LD1 = LD1;
	}

	public int getRD1() {
		return RD1;
	}

	public void setRD1(int RD1) {
		assignedPositions.put("RD1", true);
		this.RD1 = RD1;
	}

	public int getLD2() {
		return LD2;
	}

	public void setLD2(int LD2) {
		assignedPositions.put("LD2", true);
		this.LD2 = LD2;
	}

	public int getRD2() {
		return RD2;
	}

	public void setRD2(int RD2) {
		assignedPositions.put("RD2", true);
		this.RD2 = RD2;
	}

	public int getLD3() {
		return LD3;
	}

	public void setLD3(int LD3) {
		assignedPositions.put("LD3", true);
		this.LD3 = LD3;
	}

	public int getRD3() {
		return RD3;
	}

	public void setRD3(int RD3) {
		assignedPositions.put("RD3", true);
		this.RD3 = RD3;
	}

	public int getG2() {
		return G2;
	}

	public void setG2(int g2) {
		assignedPositions.put("G2", true);
		G2 = g2;
	}

	public int getG1() {
		return G1;
	}

	public void setG1(int g1) {
		assignedPositions.put("G1", true);
		G1 = g1;
	}
	public void setPosition(String pos, int id){
		if(pos.equals("C1")){
			C1 = id;
		}
		if(pos.equals("C2")){
			C2 = id;
		}
		if(pos.equals("C3")){
			C3 = id;
		}
		if(pos.equals("C4")){
			C4 = id;
		}
		if(pos.equals("LW1")){
			LW1 = id;
		}
		if(pos.equals("LW2")){
			LW2 = id;
		}
		if(pos.equals("LW3")){
			LW3 = id;
		}
		if(pos.equals("LW4")){
			LW4 = id;
		}
		if(pos.equals("RW1")){
			RW1 = id;
		}
		if(pos.equals("RW2")){
			RW2 = id;
		}
		if(pos.equals("RW3")){
			RW3 = id;
		}
		if(pos.equals("RW4")){
			RW4 = id;
		}
		if(pos.equals("LD1")){
			LD1 = id;
		}
		if(pos.equals("LD2")){
			LD2 = id;
		}
		if(pos.equals("LD3")){
			LD3 = id;
		}
		if(pos.equals("RD1")){
			RD1= id;
		}
		if(pos.equals("RD2")){
			RD2 = id;
		}
		if(pos.equals("RD3")){
			RD3 = id;
		}
		if(pos.equals("G2")){
			G2 = id;
		}
		if(pos.equals("G1")){
			G1 = id;
		}

	}
}
