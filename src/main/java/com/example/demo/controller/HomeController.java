package com.example.demo.controller;

import com.example.demo.components.generator.PlayerGenerator;
import com.example.demo.model.Player;

import com.example.demo.model.SearchCommand;
import com.example.demo.model.Team;
import com.example.demo.service.GeneratorService;
import com.example.demo.service.PlayerService;
import com.example.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
@SessionAttributes({"team", "currentTeam"})
public class HomeController {
	private String appMode;
	private int currentTeam = -1;

	@Autowired
	GeneratorService gs;
	@Autowired
	PlayerService ps;
	@Autowired
	TeamService ts;

	@Autowired
	public HomeController(Environment environment){
		appMode = environment.getProperty("app-mode");
	}


	@RequestMapping(value = "/")
	public String indexForm(ModelMap model){
		List<Player> players = null;
		Team team = null;

		if(gs.countRowsInFirstLast() == 0){
			gs.insertAllNames();
		}
		/*
		PlayerGenerator pg = new PlayerGenerator();
		pg.setGeneratorService(gs);
		for(int i=0; i<23; ++i) {
			Player p = pg.getNext();
			ps.addPlayer(p);
		}*/


		players = ps.findPlayersByTeam(currentTeam);
		team = getTeam(ts.findTeamById(currentTeam), model);

		model.addAttribute("currentTeam", currentTeam);
		model.addAttribute("team", team);
		model.addAttribute("players", players);
		if(!model.containsAttribute("situation")){
			model.addAttribute("situation", "Even Strength 5v5");
		}
		return "index";
	}

	@RequestMapping(value="/changeSituation", method=RequestMethod.POST, params="situation=PP")
	public String powerplay(ModelMap model) {
		model.addAttribute("situation", "Power Play");
		return indexForm(model);
	}


	@RequestMapping(value="/changeSituation", method=RequestMethod.POST, params="situation=5V5")
	public String evenstrength(ModelMap model) {
		model.addAttribute("situation", "Even Strength 5 v 5");
		return indexForm(model);
	}


	@ModelAttribute("searchCommand")
	public SearchCommand getSearchCommand(ModelMap model) {
		return new SearchCommand();
	}
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String Search(@ModelAttribute("searchCommmand") SearchCommand searchCommand,
								ModelMap model) {

		String searchString = searchCommand.getSearchString();

		List<Player> players = null;
		if(searchString != null){
			players = ps.findPlayersByName(searchString);
		}

		model.addAttribute("players", players);

		return "search";
	}
	@RequestMapping(value = "/confirmLines", method = RequestMethod.POST)
		public String saveLines(@ModelAttribute("team") Team team, ModelMap model){
			Team updatedTeam = getTeam(team,model);
			model.addAttribute("team", updatedTeam);
			ts.updateTeamRow(getTeam(team, model));
			return indexForm(model);
	}



	@ModelAttribute("player")
	public Player getPlayer(ModelMap model) {
		Player p = new Player();
		return p;
	}

	@RequestMapping(value = "/team/{teamId}")
	public String getPlayer(@PathVariable String teamId, ModelMap model){

		currentTeam = Integer.valueOf(teamId);

		model.addAttribute("currentTeam", teamId);
		return indexForm(model);
	}

	@RequestMapping(value = "/addPlayer", method = RequestMethod.POST)
	public String addPlayer(@ModelAttribute("player") Player player,
									 ModelMap model) {

		player.setTeamID(currentTeam);
		player.setGoalieskills(0);
		ps.addPlayer(player);

		List<Player> players = null;
		players = ps.findPlayersByTeam(currentTeam);

		model.addAttribute("players", players);
		return indexForm(model);
	}

	@ModelAttribute("team")
	public Team getTeam(@ModelAttribute Team t, ModelMap model){
		if(t == null) {
			t = new Team();
			return t;
		}

		Player C1 = ps.findPlayerByID(t.getC1());
		Player C2 = ps.findPlayerByID(t.getC2());
		Player C3 = ps.findPlayerByID(t.getC3());
		Player C4 = ps.findPlayerByID(t.getC4());

		Player LW1 = ps.findPlayerByID(t.getLW1());
		Player LW2 = ps.findPlayerByID(t.getLW2());
		Player LW3 = ps.findPlayerByID(t.getLW3());
		Player LW4 = ps.findPlayerByID(t.getLW4());

		Player RW1 = ps.findPlayerByID(t.getRW1());
		Player RW2 = ps.findPlayerByID(t.getRW2());
		Player RW3 = ps.findPlayerByID(t.getRW3());
		Player RW4 = ps.findPlayerByID(t.getRW4());

		Player LD1 = ps.findPlayerByID(t.getLD1());
		Player LD2 = ps.findPlayerByID(t.getLD2());
		Player LD3 = ps.findPlayerByID(t.getLD3());

		Player RD1 = ps.findPlayerByID(t.getRD1());
		Player RD2 = ps.findPlayerByID(t.getRD2());
		Player RD3 = ps.findPlayerByID(t.getRD3());

		Player G1 = ps.findPlayerByID(t.getG1());
		Player G2 = ps.findPlayerByID(t.getG2());

		if(C1 != null)
			t.setsC1(C1.getName());
		if(LW1 != null)
			t.setsLW1(LW1.getName());
		if(RW1 != null)
			t.setsRW1(RW1.getName());

		if(C2 != null)
			t.setsC2(C2.getName());
		if(LW2 != null)
			t.setsLW2(LW2.getName());
		if(RW2 != null)
			t.setsRW2(RW2.getName());

		if(C3 != null)
			t.setsC3(C3.getName());
		if(LW3 != null)
			t.setsLW3(LW3.getName());
		if(RW3 != null)
			t.setsRW3(RW3.getName());

		if(C4 != null)
			t.setsC4(C4.getName());
		if(LW4 != null)
			t.setsLW4(LW4.getName());
		if(RW4 != null)
			t.setsRW4(RW4.getName());

		if(LD1 != null)
			t.setsLD1(LD1.getName());
		if(LD2 != null)
			t.setsLD2(LD2.getName());
		if(LD3 != null)
			t.setsLD3(LD3.getName());

		if(RD1 != null)
			t.setsRD1(RD1.getName());
		if(RD2 != null)
			t.setsRD2(RD2.getName());
		if(RD3 != null)
			t.setsRD3(RD3.getName());

		if(G1 != null)
			t.setsG1(G1.getName());
		if(G2 != null)
			t.setsG2(G2.getName());

		return t;
	}

}