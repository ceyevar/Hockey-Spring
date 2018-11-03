package com.example.demo.components.sim;


import com.example.demo.model.Player;

import java.util.ArrayList;


public interface State {
	public State next();
	public void setLines(ArrayList<Player> possessorTeam, ArrayList<Player> enemyTeam);
	public int getPossessorTeam();
}
