package com.example.demo.model;

import com.example.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Team {
	@Id
	@GeneratedValue
	private int id;
	private String teamName;



	//this is the gametime lineup
	private int C1,LW1,RW1,C2,LW2,RW2, C3, LW3, RW3, C4, LW4, RW4,
			LD1, RD1,LD2, RD2, LD3, RD3, G1, G2;

	private String sC1,sLW1,sRW1,sC2,sLW2,sRW2, sC3, sLW3, sRW3, sC4, sLW4, sRW4,
			sLD1, sRD1,sLD2, sRD2, sLD3, sRD3, sG1, sG2;


	public Team(){
		sC1 = "Center";
		sLW1 = "Left Wing";
		sRW1 = "Right Wing";
		sC2 = "Center";
		sLW2 = "Left Wing";
		sRW2 = "Right Wing";
		sC3 = "Center";
		sLW3 = "Left Wing";
		sRW3 = "Right Wing";
		sC4 = "Center";
		sLW4 = "Left Wing";
		sRW4 = "Right Wing";

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

		C1 = c1;
	}

	public int getLW1() {
		return LW1;
	}

	public void setLW1(int LW1) {

		this.LW1 = LW1;
	}

	public int getRW1() {
		return RW1;
	}

	public void setRW1(int RW1) {
		this.RW1 = RW1;
	}

	public int getC2() {
		return C2;
	}

	public void setC2(int c2) {
		C2 = c2;
	}

	public int getLW2() {
		return LW2;
	}

	public void setLW2(int LW2) {
		this.LW2 = LW2;
	}

	public int getRW2() {
		return RW2;
	}

	public void setRW2(int RW2) {
		this.RW2 = RW2;
	}

	public int getC3() {
		return C3;
	}

	public void setC3(int c3) {
		C3 = c3;
	}

	public int getLW3() {
		return LW3;
	}

	public void setLW3(int LW3) {
		this.LW3 = LW3;
	}

	public int getRW3() {
		return RW3;
	}

	public void setRW3(int RW3) {
		this.RW3 = RW3;
	}

	public int getC4() {
		return C4;
	}

	public void setC4(int c4) {
		C4 = c4;
	}

	public int getLW4() {
		return LW4;
	}

	public void setLW4(int LW4) {
		this.LW4 = LW4;
	}

	public int getRW4() {
		return RW4;
	}

	public void setRW4(int RW4) {
		this.RW4 = RW4;
	}

	public int getLD1() {
		return LD1;
	}

	public void setLD1(int LD1) {
		this.LD1 = LD1;
	}

	public int getRD1() {
		return RD1;
	}

	public void setRD1(int RD1) {
		this.RD1 = RD1;
	}

	public int getLD2() {
		return LD2;
	}

	public void setLD2(int LD2) {
		this.LD2 = LD2;
	}

	public int getRD2() {
		return RD2;
	}

	public void setRD2(int RD2) {
		this.RD2 = RD2;
	}

	public int getLD3() {
		return LD3;
	}

	public void setLD3(int LD3) {
		this.LD3 = LD3;
	}

	public int getRD3() {
		return RD3;
	}

	public void setRD3(int RD3) {
		this.RD3 = RD3;
	}

	public int getG2() {
		return G2;
	}

	public void setG2(int g2) {
		G2 = g2;
	}

	public int getG1() {
		return G1;
	}

	public void setG1(int g1) {
		G1 = g1;
	}


	public String getsC1() {
		return sC1;
	}

	public void setsC1(String sC1) {
		this.sC1 = sC1;
	}

	public String getsLW1() {
		return sLW1;
	}

	public void setsLW1(String sLW1) {
		this.sLW1 = sLW1;
	}

	public String getsRW1() {
		return sRW1;
	}

	public void setsRW1(String sRW1) {
		this.sRW1 = sRW1;
	}

	public String getsC2() {
		return sC2;
	}

	public void setsC2(String sC2) {
		this.sC2 = sC2;
	}

	public String getsLW2() {
		return sLW2;
	}

	public void setsLW2(String sLW2) {
		this.sLW2 = sLW2;
	}

	public String getsRW2() {
		return sRW2;
	}

	public void setsRW2(String sRW2) {
		this.sRW2 = sRW2;
	}

	public String getsC3() {
		return sC3;
	}

	public void setsC3(String sC3) {
		this.sC3 = sC3;
	}

	public String getsLW3() {
		return sLW3;
	}

	public void setsLW3(String sLW3) {
		this.sLW3 = sLW3;
	}

	public String getsRW3() {
		return sRW3;
	}

	public void setsRW3(String sRW3) {
		this.sRW3 = sRW3;
	}

	public String getsC4() {
		return sC4;
	}

	public void setsC4(String sC4) {
		this.sC4 = sC4;
	}

	public String getsLW4() {
		return sLW4;
	}

	public void setsLW4(String sLW4) {
		this.sLW4 = sLW4;
	}

	public String getsRW4() {
		return sRW4;
	}

	public void setsRW4(String sRW4) {
		this.sRW4 = sRW4;
	}

	public String getsLD1() {
		return sLD1;
	}

	public void setsLD1(String sLD1) {
		this.sLD1 = sLD1;
	}

	public String getsRD1() {
		return sRD1;
	}

	public void setsRD1(String sRD1) {
		this.sRD1 = sRD1;
	}

	public String getsLD2() {
		return sLD2;
	}

	public void setsLD2(String sLD2) {
		this.sLD2 = sLD2;
	}

	public String getsRD2() {
		return sRD2;
	}

	public void setsRD2(String sRD2) {
		this.sRD2 = sRD2;
	}

	public String getsLD3() {
		return sLD3;
	}

	public void setsLD3(String sLD3) {
		this.sLD3 = sLD3;
	}

	public String getsRD3() {
		return sRD3;
	}

	public void setsRD3(String sRD3) {
		this.sRD3 = sRD3;
	}

	public String getsG1() {
		return sG1;
	}

	public void setsG1(String sG1) {
		this.sG1 = sG1;
	}

	public String getsG2() {
		return sG2;
	}

	public void setsG2(String sG2) {
		this.sG2 = sG2;
	}



}
